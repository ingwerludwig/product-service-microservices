package com.javagrind.productservice.controller;

import com.javagrind.productservice.dto.Response;
import com.javagrind.productservice.dto.request.CreateProductRequest;
import com.javagrind.productservice.entity.ProductEntity;
import com.javagrind.productservice.handler.BadRequestExceptionHandler;
import com.javagrind.productservice.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import java.util.logging.Logger;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController {

    private static final Logger logger = Logger.getLogger(ProductController.class.getName());
    private final ProductService productService;

    public ResponseEntity<Response<ProductEntity>> createProduct (@Valid @RequestBody CreateProductRequest request, BindingResult errors){
        Response<ProductEntity> response;

        if (errors.hasErrors()) BadRequestExceptionHandler.handle(errors);

        try {
            Object result = productService.create(request);
            response = new Response<>(HttpStatus.OK.value(), Boolean.TRUE, "Delete "+ result + " successfully", null);
            return ResponseEntity.ok().body(response);
        } catch (HttpClientErrorException.Unauthorized ex) {
            response = new Response<>(HttpStatus.UNAUTHORIZED.value(), Boolean.FALSE, ex.getMessage(), null);
            System.err.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        } catch (HttpClientErrorException.Forbidden ex) {
            response = new Response<>(HttpStatus.FORBIDDEN.value(), Boolean.FALSE, ex.getMessage(), null);
            System.err.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
        } catch (Exception ex) {
            response = new Response<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), Boolean.FALSE, ex.getMessage(), null);
            System.err.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}
