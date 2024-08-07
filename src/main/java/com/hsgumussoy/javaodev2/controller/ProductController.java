package com.hsgumussoy.javaodev2.controller;

import com.hsgumussoy.javaodev2.mapper.ProductMapper;
import com.hsgumussoy.javaodev2.request.ProductRequest;
import com.hsgumussoy.javaodev2.response.ProductResponse;
import com.hsgumussoy.javaodev2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService service;
    @Autowired
    @Lazy
    private ProductMapper productMapper;

    @PostMapping
    public ProductResponse save(@RequestBody ProductRequest request) {
        return productMapper.dtoToResponse(service.save(productMapper.requestToDto(request)));
    }


    @GetMapping("/{id}")
    public ProductResponse get(@PathVariable(name = "id") String id) {
        return productMapper.dtoToResponse(service.get(id));
    }


    @GetMapping
    public List<ProductResponse> getAll() {
        return productMapper.dtosToResponses(service.getAll());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") String id) {
        service.delete(id);
    }

    @PutMapping("/{id}")
    public ProductResponse update(@PathVariable(name = "id") String id, @RequestBody ProductRequest request) {
        return productMapper.dtoToResponse(service.update(id, productMapper.requestToDto(request)));
    }


}
