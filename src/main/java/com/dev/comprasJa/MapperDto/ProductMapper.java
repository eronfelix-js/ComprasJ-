package com.dev.comprasJa.MapperDto;

import com.dev.comprasJa.Entity.ProductEntity;
import com.dev.comprasJa.MapperDto.request.ProductReques;
import com.dev.comprasJa.MapperDto.response.CategoryResponse;
import com.dev.comprasJa.MapperDto.response.MarkResponse;
import com.dev.comprasJa.MapperDto.response.ProductResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductMapper {

    public static ProductEntity toProduct(ProductReques productReques){
        return ProductEntity.builder()
                .nome(productReques.nome())
                .descricao(productReques.descricao())
                .precoCusto(productReques.precoCusto())
                .precoVenda(productReques.precoVenda())
                .quantidadeEstoque(productReques.quantidadeEstoque())
                .validade(productReques.validade())
                .imagem(productReques.imagem())
                .build();
    }

    public static ProductResponse toResponse(ProductEntity productEntity){
        return ProductResponse.builder()
                .id(productEntity.getId())
                .nome(productEntity.getNome())
                .descricao(productEntity.getDescricao())
                .precoCusto(productEntity.getPrecoCusto())
                .precoVenda(productEntity.getPrecoVenda())
                .quantidadeEstoque(productEntity.getQuantidadeEstoque())
                .validade(productEntity.getValidade())
                .image(productEntity.getImagem())
                .category(productEntity.getCategory())
                .mark(productEntity.getMark())
                .build();
    }
}