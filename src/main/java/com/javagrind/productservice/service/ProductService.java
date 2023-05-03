package com.javagrind.productservice.service;

import com.javagrind.productservice.dto.request.CreateProductRequest;

public interface ProductService {
    Object create(CreateProductRequest request);
}
