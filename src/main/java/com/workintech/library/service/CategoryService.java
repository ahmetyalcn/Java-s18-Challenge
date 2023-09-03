package com.workintech.library.service;

import com.workintech.library.entity.Book;
import com.workintech.library.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category find(int id);
    Category save(Category category);
    void delete(Category category);
}
