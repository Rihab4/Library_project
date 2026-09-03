package com.codewithrihab.bibliotheque.Controllers;

import com.codewithrihab.bibliotheque.entities.CategoryRequest;
import com.codewithrihab.bibliotheque.mappers.CategoryMapper;
import com.codewithrihab.bibliotheque.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
@AllArgsConstructor
public class CategoryController {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @PostMapping("/add")
    public ResponseEntity<?> addNewCategory(@RequestBody CategoryRequest categoryRequest) {
        if (categoryRepository.findByName(categoryRequest.getName()) != null) {
            return ResponseEntity.badRequest().body("Category already exists");
        }
        var category = categoryMapper.toEntity(categoryRequest);
        categoryRepository.save(category);
        var categoryDto = categoryMapper.toDto(category);
        return ResponseEntity.ok().body(categoryDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        if (!categoryRepository.existsById(id)) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Category with id " + id + " not found");
        }

        categoryRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
