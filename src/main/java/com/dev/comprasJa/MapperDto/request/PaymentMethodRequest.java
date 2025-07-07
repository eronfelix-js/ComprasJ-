package com.dev.comprasJa.MapperDto.request;

import lombok.Builder;

@Builder
public record PaymentMethodRequest(
        String type,
        String flag
) {}
