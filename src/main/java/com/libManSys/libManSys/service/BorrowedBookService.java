package com.libManSys.libManSys.service;

import java.util.List;

import com.libManSys.libManSys.model.Book;
import com.libManSys.libManSys.model.BorrowedBook;
import com.libManSys.libManSys.model.User;

public interface BorrowedBookService {
    
    List<BorrowedBook> getAllBorrowedBooks();
    BorrowedBook getBorrowedBookById(int id);
    BorrowedBook saveBorrowedBook(User user, Book book);
    List<BorrowedBook> getBorrowedBookHistory(User user, Book book);
    List<BorrowedBook> generateNotice(User user, Book book);
    BorrowedBook updateBorrowedBook(int id, BorrowedBook borrowedBook);
    void deleteBorrowedBook(int id);
}
