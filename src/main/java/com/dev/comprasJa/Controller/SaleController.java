package com.dev.comprasJa.Controller;

import com.dev.comprasJa.MapperDto.request.SaleRequest;
import com.dev.comprasJa.MapperDto.response.SaleResponse;
import com.dev.comprasJa.Service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/sales")
@RequiredArgsConstructor
public class SaleController {

    private final SaleService saleService;

    @PostMapping("/registrarvenda")
    public ResponseEntity<SaleResponse> registerSale(@RequestBody SaleRequest request){
        SaleResponse response = saleService.registerSale(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/filtros")
    public ResponseEntity<List<SaleResponse>> filtros(
            @RequestParam(required = false) @DateTimeFormat( iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(required = false) Long paymentMethodId,
            @RequestParam(required = false) Long productId
            ){
        List<SaleResponse> listFilter = saleService.filtrarVendas(startDate,endDate, paymentMethodId, productId);
        return ResponseEntity.ok(listFilter);
    }

    @GetMapping
    public ResponseEntity<List<SaleResponse>> getAllSales() {
        return ResponseEntity.ok(saleService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleResponse> getSaleById(@PathVariable Long id) {
        return ResponseEntity.ok(saleService.getById(id));
    }
}

