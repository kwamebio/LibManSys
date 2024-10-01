package com.libManSys.libManSys.service.implement;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.libManSys.libManSys.enums.BookStatus;
import com.libManSys.libManSys.model.Book;
import com.libManSys.libManSys.model.BorrowedBook;
import com.libManSys.libManSys.model.User;
import com.libManSys.libManSys.repository.BookRepository;
import com.libManSys.libManSys.repository.BorrowedBookRepository;
import com.libManSys.libManSys.service.BorrowedBookService;

@Service
public class BorrowedBookServiceImplement implements BorrowedBookService {

    private final BorrowedBookRepository borrowedBookRepository;
    private final BookRepository bookRepository;

    public BorrowedBookServiceImplement(BorrowedBookRepository borrowedBookRepository, BookRepository bookRepository) {
        this.borrowedBookRepository = borrowedBookRepository;
        this.bookRepository = bookRepository;
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
    public BorrowedBook saveBorrowedBook(User user, Book book) {
        if(book.getStatus() == BookStatus.BORROWED){
            throw new RuntimeException("Book is already borrowed");
        }
        BorrowedBook borrowedBook = new BorrowedBook();
            borrowedBook.setBook(book);
            borrowedBook.setUser(user);
            borrowedBook.setBorrowDate(LocalDateTime.now());
            borrowedBook.setReturnDate(LocalDateTime.now().plusDays(7));
            
            borrowedBookRepository.save(borrowedBook);
            book.setStatus(BookStatus.BORROWED);
            bookRepository.save(book);
            
            return borrowedBook;
    }

    // @Override
    // public List<BorrowedBook> getBorrowedBookHistory(User user, Book book){
    //     BorrowedBook borrowedBook = new BorrowedBook();
    //     if(borrowedBook != null){
    //         borrowedBook.setReturnDate(LocalDateTime.now());
    //         if(LocalDateTime.now().isAfter(borrowedBook.getReturnDate())){
    //             System.out.println("Book is overdue");

    //         }
    //         book.setStatus(BookStatus.AVAILABLE);
    //         bookRepository.save(book);

    //         return borrowedBook;
    //     }
    //     throw new RuntimeException("Borrowing record not found.");
    // }

    @Override
    public List<BorrowedBook> getBorrowedBookHistory(User user, Book book) {
        List<BorrowedBook> borrowedBooks = borrowedBookRepository.findByUserAndBook(user, book);
        
        if (borrowedBooks.isEmpty()) {
            throw new RuntimeException("Borrowing record not found.");
        }

        for (BorrowedBook borrowedBook : borrowedBooks) {
            if (LocalDateTime.now().isAfter(borrowedBook.getReturnDate())) {
                System.out.println("Book is overdue");
            }
            book.setStatus(BookStatus.AVAILABLE);
            bookRepository.save(book);
        }

        return borrowedBooks;
    }

    @Override
    public List<BorrowedBook> generateNotice(User user, Book book){
        List<BorrowedBook> overdueBooks = borrowedBookRepository.findByReturnDateIsBefore(LocalDateTime.now());
        for(BorrowedBook borrowedBook : overdueBooks){
            System.out.println("Notice generated for " + borrowedBook.getUser().getName());
        }

        return overdueBooks;
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
