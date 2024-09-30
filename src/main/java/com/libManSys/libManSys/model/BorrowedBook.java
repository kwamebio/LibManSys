package com.libManSys.libManSys.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "borrowed_books")
public class BorrowedBook {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    @Column(name = "borrowed_book_id")
    private long borrowedBookId;

    @Column(name = "borrow_date")
    private LocalDateTime borrowDate;
    
    @Column(name = "return_date")
    private LocalDateTime returnDate;

    @OneToOne
    @JoinColumn(name = "book_id", referencedColumnName = "book_id",nullable = false)
    private Book book;

    public BorrowedBook() {
    }

    

    public BorrowedBook(long borrowed_book_id, LocalDateTime borrowDate, LocalDateTime returnDate, Book book) {
        this.borrowedBookId = borrowed_book_id;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.book = book;
    }



    public long getId() {
        return borrowedBookId;
    }

    public void setId(long borrowedBookId) {
        this.borrowedBookId = borrowedBookId;
    }

    public LocalDateTime getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDateTime borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    

    
}
