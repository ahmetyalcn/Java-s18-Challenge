package com.workintech.library.service;

import com.workintech.library.entity.Author;
import com.workintech.library.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AuthorService {
    List<Author> findAll();
    Author find(int id);
    Author save(Author author);
    void delete(Author author);
}
