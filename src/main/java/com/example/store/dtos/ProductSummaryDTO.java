package com.example.store.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class ProductSummaryDTO {
    private Long id;
    private String name;
}
