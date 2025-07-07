package com.dev.comprasJa.Service;

import com.dev.comprasJa.Entity.PaymentMethod;
import com.dev.comprasJa.MapperDto.PaymentMethodMapper;
import com.dev.comprasJa.MapperDto.request.PaymentMethodRequest;
import com.dev.comprasJa.MapperDto.response.PaymentMethodResponse;
import com.dev.comprasJa.Repository.PaymentMethodRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentMethodService {

    private final PaymentMethodRepository repository;

    public PaymentMethodService(PaymentMethodRepository repository) {
        this.repository = repository;
    }

    public PaymentMethodResponse create(PaymentMethodRequest request) {
        PaymentMethod method = PaymentMethodMapper.toEntity(request);
        return PaymentMethodMapper.toResponse(repository.save(method));
    }

    public List<PaymentMethodResponse> getAll() {
        return repository.findAll()
                .stream()
                .map(PaymentMethodMapper::toResponse)
                .toList();
    }

    public PaymentMethodResponse getById(Long id) {
        PaymentMethod method = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("o metodo de pagamento não foi encontrado com o id: "+ id));
        return PaymentMethodMapper.toResponse(method);
    }

    public PaymentMethodResponse update(Long id, PaymentMethodRequest request) {
        PaymentMethod method = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("o metodo de pagamento não foi encontrado com o id: " + id));

        method.setType(request.type());
        method.setFlag(request.flag());

        return PaymentMethodMapper.toResponse(repository.save(method));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
