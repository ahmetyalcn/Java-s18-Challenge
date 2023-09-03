package com.workintech.library.controller;

import com.workintech.library.entity.Author;
import com.workintech.library.entity.Book;
import com.workintech.library.entity.Category;
import com.workintech.library.mapping.BookResponse;
import com.workintech.library.service.AuthorService;
import com.workintech.library.service.BookService;
import com.workintech.library.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    private BookService bookService;
    private AuthorService authorService;
    private CategoryService categoryService;

    @Autowired
    public BookController(BookService bookService, AuthorService authorService, CategoryService categoryService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public List<BookResponse> findAll(){
        List<Book> books = bookService.findAll();
        List<BookResponse> bookResponses = new ArrayList<>();
        for (Book book:books){
            bookResponses.add(new BookResponse(book.getId(),book.getName(),book.getCategory().getName()));
        }
        return bookResponses;
    }

    @GetMapping("/{id}")
    public BookResponse find(@PathVariable int id){
        Book book = bookService.find(id);
        return new BookResponse(book.getId(),book.getName(),book.getCategory().getName());
    }

    @PostMapping("/{categoryId}")
    public BookResponse save(@RequestBody Book book, @PathVariable int categoryId){
        Category category = categoryService.find(categoryId);
        book.setCategory(category);
        bookService.save(book);
        return new BookResponse(book.getId(), book.getName(), category.getName());
    }
    @PostMapping("/saveByAuthor/{categoryId}/{authorId}")
    public BookResponse save(@RequestBody Book book, @PathVariable int categoryId, @PathVariable int authorId){
        Category category = categoryService.find(categoryId);
        Author author = authorService.find(authorId);
        book.setAuthor(author);
        book.setCategory(category);
        Book saved = bookService.save(book);
        return new BookResponse(saved.getId(), saved.getName(), saved.getCategory().getName(), saved.getAuthor().getFirstName(),saved.getAuthor().getLastName());
    }
    @PutMapping("/{id}")
    public BookResponse update(@RequestBody Book book, @PathVariable int id){
        Book founded = bookService.find(id);
        book.setAuthor(founded.getAuthor());
        book.setCategory(founded.getCategory());
        book.setId(id);
        bookService.save(book);
        return new BookResponse(book.getId(), book.getName(), book.getCategory().getName());
    }
    @DeleteMapping("/{id}")
    public BookResponse delete(@PathVariable int id){
        Book book = bookService.find(id);
        bookService.delete(book);
        return new BookResponse(book.getId(), book.getName(), book.getCategory().getName());
    }
}
