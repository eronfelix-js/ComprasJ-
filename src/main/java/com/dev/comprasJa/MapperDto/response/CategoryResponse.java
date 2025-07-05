package com.dev.comprasJa.MapperDto.response;

import lombok.Builder;

@Builder
public record CategoryResponse(long id, String nome) {
}
