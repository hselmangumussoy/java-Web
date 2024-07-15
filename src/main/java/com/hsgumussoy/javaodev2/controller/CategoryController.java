package com.hsgumussoy.javaodev2.controller;


import com.hsgumussoy.javaodev2.mapper.CategoryMapper;
import com.hsgumussoy.javaodev2.request.CategoryRequest;
import com.hsgumussoy.javaodev2.response.CategoryResponse;
import com.hsgumussoy.javaodev2.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    private final CategoryMapper categoryMapper;

    @PostMapping
    public CategoryResponse save(@RequestBody CategoryRequest request) {
        return categoryMapper.dtoToResponse(service.save(categoryMapper.requestToDto(request)));
    }


    @GetMapping("/{id}")
    public CategoryResponse get(@PathVariable String id) {
        return categoryMapper.dtoToResponse(service.get(id));
    }


    @GetMapping
    public List<CategoryResponse> getAll(){
        return categoryMapper.mapDtosToResponses(service.getAll());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(name = "id") String id) {
        service.delete(id);
    }

    @PutMapping("/{id}")
    public CategoryResponse update(@PathVariable(name = "id") String id, @RequestBody CategoryRequest request) {
        return categoryMapper.dtoToResponse(service.update(id, categoryMapper.requestToDto(request)));
    }


}
