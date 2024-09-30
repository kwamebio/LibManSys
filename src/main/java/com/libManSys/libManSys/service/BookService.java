package com.libManSys.libManSys.service;

import java.util.List;
import com.libManSys.libManSys.model.Book;

public interface BookService {
    List<Book> getAllBooks();
    Book getBookById(Long id);
    Book saveBook(Book book);
    Book updateBook(Long id, Book book);
    void deleteBook(Long id);
    List<Book> searchBooks(String search);
    List<Book> getAllBorrowedBooks();
    List<Book> getAllAvailableBooks();
    List<Book> getAllOverDueBooks();
    
}
