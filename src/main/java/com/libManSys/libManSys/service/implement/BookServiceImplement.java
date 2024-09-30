package com.libManSys.libManSys.service.implement;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.libManSys.libManSys.enums.BookStatus;
import com.libManSys.libManSys.model.Book;
import com.libManSys.libManSys.repository.BookRepository;
import com.libManSys.libManSys.service.BookService;


@Service
public class BookServiceImplement implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImplement(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }



    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> getAllBorrowedBooks() {
        return bookRepository.findByStatus(BookStatus.BORROWED);
    }
    
    @Override
    public List<Book> getAllAvailableBooks() {
        return bookRepository.findByStatus(BookStatus.AVAILABLE);
    }

    @Override
    public List<Book> getAllOverDueBooks() {
        LocalDate today = LocalDate.now();
        return bookRepository.findByReturnDateBeforeAndStatus(today, BookStatus.BORROWED);
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public Book saveBook(Book book) {
        book.setBorrowDate(null);
        book.setReturnDate(null);
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long id, Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {   
        bookRepository.deleteById(id);
    }



    @Override
    public List<Book> searchBooks(String searchText) {
        return bookRepository.findByAuthorContainingIgnoreCaseOrTitleContainingIgnoreCaseOrIsbnContainingIgnoreCase(searchText, searchText, searchText);
    }

    
}
