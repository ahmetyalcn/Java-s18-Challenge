package com.workintech.library.controller;

import com.workintech.library.entity.Author;
import com.workintech.library.entity.Book;
import com.workintech.library.mapping.AuthorResponse;
import com.workintech.library.mapping.BookResponse;
import com.workintech.library.service.AuthorService;
import com.workintech.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    private AuthorService authorService;
    private BookService bookService;

    @Autowired
    public AuthorController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @GetMapping("/")
    public List<AuthorResponse> findAll(){
        List<Author> authors = authorService.findAll();
        List<AuthorResponse> authorResponses = new ArrayList<>();
        for (Author author: authors){
            List<Book> books = author.getBooks();
            List<BookResponse> bookResponses = new ArrayList<>();
            for (Book book :books){
                bookResponses.add(new BookResponse(book.getId(),book.getName(),book.getCategory().getName()));
            }
            authorResponses.add(new AuthorResponse(author.getId(),author.getFirstName(),author.getLastName(),bookResponses));
        }
        return authorResponses;
    }

    @GetMapping("/{id}")
    public AuthorResponse find(@PathVariable int id){
        Author author = authorService.find(id);
        List<Book> books = author.getBooks();
        List<BookResponse> bookResponses = new ArrayList<>();
        for (Book book :books){
            bookResponses.add(new BookResponse(book.getId(),book.getName(),book.getCategory().getName()));
        }
        return new AuthorResponse(author.getId(),author.getFirstName(), author.getLastName(),bookResponses);
    }
    @PostMapping("/")
    public AuthorResponse save(@RequestBody Author author){
        Author savedAuthor = authorService.save(author);
        return new AuthorResponse(savedAuthor.getId(), savedAuthor.getFirstName(),
                savedAuthor.getLastName(), null);
    }
    @PostMapping("/{bookId}")
    public AuthorResponse save(@RequestBody Author author, @PathVariable int bookId){
        Book book = bookService.find(bookId);
        book.setAuthor(author);
        author.addBook(book);
        List<Book> books = author.getBooks();
        List<BookResponse> bookResponses = new ArrayList<>();
        for (Book b :books){
            bookResponses.add(new BookResponse(b.getId(),b.getName(),b.getCategory().getName()));
        }
        authorService.save(author);
        return new AuthorResponse(author.getId(), author.getFirstName(),
                author.getLastName(), bookResponses);
    }

    @PutMapping("/{authorId}")
    public AuthorResponse update(@RequestBody Author author, @PathVariable int authorId){
        Author founded = authorService.find(authorId);
        founded.setId(authorId);
        List<Book> books = author.getBooks();
        List<BookResponse> bookResponses = new ArrayList<>();
        for (Book b :books){
            bookResponses.add(new BookResponse(b.getId(),b.getName(),b.getCategory().getName()));
        }
        authorService.save(author);
        return new AuthorResponse(author.getId(), author.getFirstName(),
                author.getLastName(), bookResponses);
    }
    @DeleteMapping("/{id}")
    public AuthorResponse delete(@PathVariable int id){
        Author author = authorService.find(id);
        List<Book> books = author.getBooks();
        List<BookResponse> bookResponses = new ArrayList<>();
        for (Book b :books){
            bookResponses.add(new BookResponse(b.getId(),b.getName(),b.getCategory().getName()));
        }
        authorService.delete(author);
        return new AuthorResponse(author.getId(), author.getFirstName(),
                author.getLastName(), bookResponses);
    }

}
