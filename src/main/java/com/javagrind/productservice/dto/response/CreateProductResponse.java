package com.javagrind.productservice.dto.response;

import com.javagrind.productservice.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductResponse {
    private ProductEntity product;
}
