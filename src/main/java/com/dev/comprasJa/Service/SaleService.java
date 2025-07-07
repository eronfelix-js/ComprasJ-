package com.dev.comprasJa.Service;

import com.dev.comprasJa.Entity.PaymentMethod;
import com.dev.comprasJa.Entity.ProductEntity;
import com.dev.comprasJa.Entity.Sale;
import com.dev.comprasJa.Entity.SaleItem;
import com.dev.comprasJa.MapperDto.SaleMapper;
import com.dev.comprasJa.MapperDto.request.SaleItemRequest;
import com.dev.comprasJa.MapperDto.request.SaleRequest;
import com.dev.comprasJa.MapperDto.response.SaleResponse;
import com.dev.comprasJa.Repository.PaymentMethodRepository;
import com.dev.comprasJa.Repository.ProductRepository;
import com.dev.comprasJa.Repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleService {

    private final ProductService service;
    private final ProductRepository repository;
    private final SaleRepository saleRepository;
    private final PaymentMethodRepository paymentMethodRepository;

    public SaleResponse registerSale(SaleRequest request){

        List<SaleItem> items = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        PaymentMethod paymentMethod = paymentMethodRepository.findById(request.paymentMethod())
                .orElseThrow(() -> new RuntimeException("forma de pagamento com id: "+ request.paymentMethod()+" não encontrada"));

        for(SaleItemRequest saleRequest : request.items()){
            ProductEntity product = repository.findById(saleRequest.productId())
                    .orElseThrow(()-> new RuntimeException("Produto não encontrado"));

            if (product.getQuantidadeEstoque() < saleRequest.quantity()) {
                throw new RuntimeException("quantodade do produto "+product.getNome()+" insuficiente");
            }
            product.setQuantidadeEstoque(product.getQuantidadeEstoque() - saleRequest.quantity());
            repository.save(product);

            SaleItem saleItem = SaleMapper.toSaleItem(product, saleRequest.quantity());
            items.add(saleItem);
            total = total.add(saleItem.getSubtotal());
        }

        Sale sale = Sale.builder()
                .items(items)
                .timestamp(LocalDateTime.now())
                .total(total)
                .paymentMethod(paymentMethod)
                .build();

        for(SaleItem item : items ){
            item.setSale(sale);
        }
        return SaleMapper.toResponse(saleRepository.save(sale));
    }

    public List<SaleResponse> getAll(){
        return saleRepository.findAll()
                .stream()
                .map(SaleMapper::toResponse)
                .toList();
    }

    public SaleResponse getById(long id){
        Sale sale = saleRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("venda não registrada"));
        return SaleMapper.toResponse(sale);
    }

    public List<SaleResponse> filtrarVendas(LocalDate startDate, LocalDate endDate, Long paymentMethodId, Long productId){
        if (startDate != null && endDate != null && startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Data inicial não pode ser depois da data final.");
        }
        if (paymentMethodId != null && !paymentMethodRepository.existsById(paymentMethodId)) {
            throw new RuntimeException("Forma de pagamento com id: " + paymentMethodId + " não encontrada.");
        }
        if (productId != null && !repository.existsById(productId)) {
            throw new RuntimeException("Produto com id: " + productId + " não encontrado.");
        }
        List<Sale> sales = saleRepository.findAll();
        return sales.stream()
                .filter(sale -> {
                    boolean ok = true;
                            if (startDate != null) {
                                ok &= !sale.getTimestamp().toLocalDate().isBefore(startDate);
                            }
                            if(endDate != null){
                                ok &= !sale.getTimestamp().toLocalDate().isAfter(endDate);
                            }
                            if(paymentMethodId != null){
                                ok &= sale.getPaymentMethod() != null && sale.getPaymentMethod().getId().equals(paymentMethodId);
                            }
                           if (productId != null) {
                                ok &= sale.getItems().stream()
                                .anyMatch(item -> item.getProduct().getId().equals(productId));
                            }
                           return ok;
                }).map(SaleMapper::toResponse)
                .toList();
    }
}
