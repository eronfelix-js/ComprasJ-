package com.dev.comprasJa.MapperDto;

import com.dev.comprasJa.Entity.PaymentMethod;
import com.dev.comprasJa.MapperDto.request.PaymentMethodRequest;
import com.dev.comprasJa.MapperDto.response.PaymentMethodResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PaymentMethodMapper {

    public static PaymentMethod toEntity(PaymentMethodRequest req) {
        return PaymentMethod.builder()
                .type(req.type())
                .flag(req.flag())
                .build();
    }

    public static PaymentMethodResponse toResponse(PaymentMethod entity) {
        return PaymentMethodResponse.builder()
                .id(entity.getId())
                .type(entity.getType())
                .flag(entity.getFlag())
                .build();
    }
}
