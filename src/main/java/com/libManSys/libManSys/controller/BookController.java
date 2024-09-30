package com.libManSys.libManSys.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.libManSys.libManSys.model.Book;
import com.libManSys.libManSys.service.BookService;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {
    
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks(){
        return bookService.getAllBooks(); 
    }

    @GetMapping("/borrowed")
    public List<Book> getAllBorrowedBooks(){
        List<Book> books = bookService.getAllBorrowedBooks();
        return books;
    }

    @GetMapping("/available")
    public List<Book> getAllAvailableBooks(){
        return bookService.getAllAvailableBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable("id")Long id){
        return bookService.getBookById(id);
    }

    @PostMapping
    public Book saveBook(@RequestBody Book book){
        return bookService.saveBook(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        return new ResponseEntity<>(bookService.updateBook(id, book), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable("id")Long id){
        bookService.deleteBook(id);
    }

    @GetMapping("/search")
public List<Book> searchBooks(@RequestParam String keyword) {
    return bookService.searchBooks(keyword);
}
}
