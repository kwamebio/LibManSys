package com.libManSys.libManSys.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libManSys.libManSys.model.BorrowedBook;
import com.libManSys.libManSys.service.BorrowedBookService;

@RestController
@RequestMapping("/api/v1/borrowedBook")
public class BorrowedBookController {
    
    private final BorrowedBookService borrowedBookService;

    public BorrowedBookController(BorrowedBookService borrowedBookService) {
        this.borrowedBookService = borrowedBookService;
    }

    @GetMapping
    public List<BorrowedBook> getAllBorrowedBooks(){
        return borrowedBookService.getAllBorrowedBooks(); 
    }

    @GetMapping("/{id}")
    public BorrowedBook getBorrowedBookById(int id){
        return borrowedBookService.getBorrowedBookById(id);
    }

    @PostMapping
    public BorrowedBook saveBorrowedBook(BorrowedBook borrowedBook){
        return borrowedBookService.saveBorrowedBook(borrowedBook);
    }

    @PutMapping("/{id}")
    public BorrowedBook updateBorrowedBook(int id, BorrowedBook borrowedBook){
        return borrowedBookService.updateBorrowedBook(id, borrowedBook);
    }

    @DeleteMapping("/{id}")
    public void deleteBorrowedBook(int id){
        borrowedBookService.deleteBorrowedBook(id);
    }
}
