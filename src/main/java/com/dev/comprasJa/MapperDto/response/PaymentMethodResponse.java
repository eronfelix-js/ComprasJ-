package com.dev.comprasJa.MapperDto.response;

import lombok.Builder;

@Builder
public record PaymentMethodResponse(
        Long id,
        String type,
        String flag
) {}
