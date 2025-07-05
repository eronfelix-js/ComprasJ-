package com.dev.comprasJa.MapperDto;

import com.dev.comprasJa.Entity.Category;
import com.dev.comprasJa.MapperDto.response.CategoryResponse;
import com.pdv.backend.MapperDto.request.CateroryRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CategoryMapper {

    public static Category toCategory(CateroryRequest cateroryRequest){
        return Category.builder()
                .nome(cateroryRequest.nome())
                .build();
    }

    public static CategoryResponse toCategoryResponse(Category category){
        return CategoryResponse.builder()
                .id(category.getId())
                .nome(category.getNome())
                .build();

    }

}
