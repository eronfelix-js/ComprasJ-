package com.dev.comprasJa.MapperDto.response;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record SaleResponse(
        Long id,
        LocalDateTime timestamp,
        BigDecimal total,
        List<SaleItemResponse> items
) {}
