package com.dev.comprasJa.MapperDto;

import com.dev.comprasJa.Entity.ProductEntity;
import com.dev.comprasJa.Entity.Sale;
import com.dev.comprasJa.Entity.SaleItem;
import com.dev.comprasJa.MapperDto.response.SaleItemResponse;
import com.dev.comprasJa.MapperDto.response.SaleResponse;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class SaleMapper {

    public static SaleResponse toResponse(Sale sale) {
        return SaleResponse.builder()
                .id(sale.getId())
                .timestamp(sale.getTimestamp())
                .total(sale.getTotal())
                .paymentType(sale.getPaymentMethod().getType())
                .paymentFlag(sale.getPaymentMethod().getFlag())
                .items(toItemResponseList(sale.getItems()))
                .build();
    }

    public static List<SaleItemResponse> toItemResponseList(List<SaleItem> items) {
        return items.stream()
                .map(item -> SaleItemResponse.builder()
                        .productName(item.getProduct().getNome())
                        .quantity(item.getQuantity())
                        .unitPrice(item.getUnitPrice())
                        .subtotal(item.getSubtotal())
                        .build())
                .collect(Collectors.toList());
    }

    public static SaleItem toSaleItem(ProductEntity product, Integer quantity) {
        BigDecimal unitPrice = product.getPrecoVenda();
        BigDecimal subtotal = unitPrice.multiply(BigDecimal.valueOf(quantity));

        return SaleItem.builder()
                .product(product)
                .quantity(quantity)
                .unitPrice(unitPrice)
                .subtotal(subtotal)
                .build();
    }
}
