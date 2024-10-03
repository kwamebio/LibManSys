package com.libManSys.libManSys.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.libManSys.libManSys.model.Book;
import com.libManSys.libManSys.model.BorrowedBook;
import com.libManSys.libManSys.model.User;
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
    public BorrowedBook getBorrowedBookById(Long id){
        return borrowedBookService.getBorrowedBookById(id);
    }

    @PostMapping("/borrow")
    public BorrowedBook borrowBook(@RequestParam Long userId, @RequestParam Long bookId){
        return borrowedBookService.borrowBook(userId, bookId);
    }

    @PostMapping("/return")
    public BorrowedBook returnBook(@RequestParam Long borrowedBookId){
        return borrowedBookService.returnBook(borrowedBookId);
    }

//    @PostMapping
//     public BorrowedBook saveBorrowedBook(@RequestBody BorrowedBookRequest request) {
//         User user = request.getUser();
//         Book book = request.getBook();
//         return borrowedBookService.saveBorrowedBook(user, book);
// //     }

//     @PostMapping
//     public BorrowedBook saveBorrowedBook(User user, Book book){
//         return borrowedBookService.saveBorrowedBook(user, book);
//     }

    @PostMapping("/history")
    public List<BorrowedBook> getBorrowedBookHistory(Long userId){
        return borrowedBookService.getBorrowedBookHistory(userId);
    }

    @PutMapping("/{id}")
    public BorrowedBook updateBorrowedBook(Long id, BorrowedBook borrowedBook){
        return borrowedBookService.updateBorrowedBook(id, borrowedBook);
    }

    @DeleteMapping("/{id}")
    public void deleteBorrowedBook(Long id){
        borrowedBookService.deleteBorrowedBook(id);
    }
}
