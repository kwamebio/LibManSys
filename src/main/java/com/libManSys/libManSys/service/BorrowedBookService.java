package com.libManSys.libManSys.service;

import java.util.List;

import com.libManSys.libManSys.model.BorrowedBook;

public interface BorrowedBookService {
    
    List<BorrowedBook> getAllBorrowedBooks();
    BorrowedBook getBorrowedBookById(int id);
    BorrowedBook saveBorrowedBook(BorrowedBook borrowedBook);
    BorrowedBook updateBorrowedBook(int id, BorrowedBook borrowedBook);
    void deleteBorrowedBook(int id);
}
