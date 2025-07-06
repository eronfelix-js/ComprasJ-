package com.dev.comprasJa.MapperDto.request;

import lombok.Builder;

import java.util.List;

@Builder
public record SaleRequest(
        List<SaleItemRequest> items
) {}
