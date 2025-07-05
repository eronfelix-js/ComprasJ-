package com.dev.comprasJa.MapperDto.response;

import com.dev.comprasJa.Entity.Category;
import com.dev.comprasJa.Entity.Mark;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record ProductResponse(Long id,
                              String nome,
                              String descricao,
                              BigDecimal precoCusto,
                              BigDecimal precoVenda,
                              byte[] image,
                              Integer quantidadeEstoque,
                              LocalDate validade,
                              Category category,
                              Mark mark
                              ) {
}
