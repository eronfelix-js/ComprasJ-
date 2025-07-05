package com.pdv.backend.MapperDto.request;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
public record CateroryRequest(String nome) {}
