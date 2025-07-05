package com.dev.comprasJa.MapperDto.request;

import com.dev.comprasJa.Entity.Category;
import com.dev.comprasJa.Entity.Mark;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record ProductReques(String nome,
                            String descricao,
                            BigDecimal precoCusto,
                            BigDecimal precoVenda,
                            Integer quantidadeEstoque,
                            LocalDate validade,
                            byte[] imagem,
                            long categoryId,
                            long markId ) {
}
