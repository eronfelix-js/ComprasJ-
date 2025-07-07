package com.dev.comprasJa.Controller;

import com.dev.comprasJa.MapperDto.request.PaymentMethodRequest;
import com.dev.comprasJa.MapperDto.response.PaymentMethodResponse;
import com.dev.comprasJa.Service.PaymentMethodService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/payment-methods")
public class PaymentMethodController {

    private final PaymentMethodService service;

    public PaymentMethodController(PaymentMethodService service) {
        this.service = service;
    }

    @PostMapping("/criar")
    public ResponseEntity<PaymentMethodResponse> create(@RequestBody PaymentMethodRequest request) {
        PaymentMethodResponse response = service.create(request);
        URI location = URI.create("/payment-methods/" + response.id());
        return ResponseEntity.created(location).body(response);
    }

    @GetMapping
    public ResponseEntity<List<PaymentMethodResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentMethodResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PaymentMethodResponse> update(@PathVariable Long id, @RequestBody PaymentMethodRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
