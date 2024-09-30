package com.libManSys.libManSys.service.implement;

import java.util.List;

import org.springframework.stereotype.Service;

import com.libManSys.libManSys.model.BorrowedBook;
import com.libManSys.libManSys.repository.BorrowedBookRepository;
import com.libManSys.libManSys.service.BorrowedBookService;

@Service
public class BorrowedBookServiceImplement implements BorrowedBookService {

    private final BorrowedBookRepository borrowedBookRepository;

    public BorrowedBookServiceImplement(BorrowedBookRepository borrowedBookRepository) {
        this.borrowedBookRepository = borrowedBookRepository;
    }

    @Override
    public List<BorrowedBook> getAllBorrowedBooks() {
        return borrowedBookRepository.findAll();
    }


    @Override
    public BorrowedBook getBorrowedBookById(int id) {
        return borrowedBookRepository.findById(id).orElse(null);
    }

    @Override
    public BorrowedBook saveBorrowedBook(BorrowedBook borrowedBook) {
        return borrowedBookRepository.save(borrowedBook);
    }

    @Override
    public BorrowedBook updateBorrowedBook(int id, BorrowedBook borrowedBook) {
        BorrowedBook existingBorrowedBook = borrowedBookRepository.findById(id).orElseThrow(() -> new RuntimeException("Borrowed Book not found"));

        existingBorrowedBook.setBook(borrowedBook.getBook());
        existingBorrowedBook.setBorrowDate(borrowedBook.getBorrowDate());
        existingBorrowedBook.setReturnDate(borrowedBook.getReturnDate());

        return borrowedBookRepository.save(existingBorrowedBook);
    }

    @Override
    public void deleteBorrowedBook(int id) {
        borrowedBookRepository.deleteById(id);
    }
    
}
