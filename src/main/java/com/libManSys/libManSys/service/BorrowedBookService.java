package com.libManSys.libManSys.service;

import java.util.List;

import com.libManSys.libManSys.model.Book;
import com.libManSys.libManSys.model.BorrowedBook;
import com.libManSys.libManSys.model.User;

public interface BorrowedBookService {
    
    List<BorrowedBook> getAllBorrowedBooks();
    BorrowedBook getBorrowedBookById(Long id);
    // BorrowedBook saveBorrowedBook(User user, Book book);
    BorrowedBook borrowBook(Long userId, Long bookId);
    BorrowedBook returnBook(Long borrowedBookId);
    List<BorrowedBook> getBorrowedBookHistory(Long userId);
    List<BorrowedBook> generateNotice(User user, Book book);
    BorrowedBook updateBorrowedBook(Long id, BorrowedBook borrowedBook);
    void deleteBorrowedBook(Long id);
}
