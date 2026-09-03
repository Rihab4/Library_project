package com.codewithrihab.bibliotheque.mappers;

import com.codewithrihab.bibliotheque.dtos.CategoryDto;
import com.codewithrihab.bibliotheque.entities.Category;
import com.codewithrihab.bibliotheque.entities.CategoryRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto toDto(Category category);
    Category toEntity(CategoryRequest categoryDto);
}
