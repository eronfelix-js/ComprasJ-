package com.dev.comprasJa.MapperDto.response;

import lombok.Builder;

@Builder
public record MarkResponse(long id, String nome) {
}
