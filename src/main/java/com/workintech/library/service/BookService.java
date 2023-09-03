package com.workintech.library.service;

import com.workintech.library.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;


public interface BookService  {
    List<Book> findAll();
    Book find(int id);
    Book save(Book book);
    void delete(Book book);
}
