package mate.academy.service;


import mate.academy.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    List findAll();

    CategoryDto getById(Long id);

    CategoryDto save(CategoryDto categoryDto);

    CategoryDto update(Long id, CategoryDto categoryDto);

    void deleteById(Long id);
}
