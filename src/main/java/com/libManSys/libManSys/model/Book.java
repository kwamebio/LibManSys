package com.libManSys.libManSys.model;

import java.time.LocalDate;
import java.util.List;
import com.libManSys.libManSys.enums.BookStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private long book_id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "genre")
    private String genre;

    @Column(name = "isbn") 
    private String isbn;

    @Column(name = "borrow_date")
    private LocalDate borrowDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @Enumerated(EnumType.STRING) 
    @Column(name = "status")
    private BookStatus status;

    @OneToMany(cascade = CascadeType.ALL) 
    private List <BorrowedBook> borrowedBooks;

    public Book(){
        
    }


    
    public Book( String title, String author, String genre, String isbn, LocalDate borrowDate, LocalDate returnDate, BookStatus status) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isbn = isbn;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.status = BookStatus.AVAILABLE;
    }



    public long getBook_id() {
        return book_id;
    }

    public void setBook_id(long book_id) {
        this.book_id = book_id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    // public List<BorrowedBook> getBorrowedBooks() {
    //     return borrowedBooks;
    // }

    // public void setBorrowedBooks(List<BorrowedBook> borrowedBooks) {
    //     this.borrowedBooks = borrowedBooks;
    // }

}
