package com.dev.comprasJa.MapperDto;

import com.dev.comprasJa.Entity.Category;
import com.dev.comprasJa.Entity.Mark;
import com.dev.comprasJa.MapperDto.request.MarkRequest;
import com.dev.comprasJa.MapperDto.response.CategoryResponse;
import com.dev.comprasJa.MapperDto.response.MarkResponse;

public class MarkMapper {

    public static Mark toMark(MarkRequest markRequest){
        return Mark.builder()
                .nome(markRequest.nome())
                .build();
    }

    public static MarkResponse toCategoryResponse(Mark mark){
        return MarkResponse.builder()
                .id(mark.getId())
                .nome(mark.getNome())
                .build();

    }
}
