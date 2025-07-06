package com.dev.comprasJa.MapperDto.request;

import lombok.Builder;

@Builder
public record SaleItemRequest(
        Long productId,
        Integer quantity
) {}
