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
import com.libManSys.libManSys.repository.UserRepository;
import com.libManSys.libManSys.service.BorrowedBookService;

import jakarta.transaction.Transactional;

@Service
public class BorrowedBookServiceImplement implements BorrowedBookService {

    private final BorrowedBookRepository borrowedBookRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public BorrowedBookServiceImplement(BorrowedBookRepository borrowedBookRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.borrowedBookRepository = borrowedBookRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<BorrowedBook> getAllBorrowedBooks() {
        return borrowedBookRepository.findAll();
    }


    @Override
    public BorrowedBook getBorrowedBookById(Long id) {
        return borrowedBookRepository.findById(id).orElse(null);
    }

    
    @Transactional
    public BorrowedBook borrowBook(Long userId, Long book_id){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Book book = bookRepository.findById(book_id).orElseThrow(() -> new RuntimeException("Book not found"));
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

    @Transactional
    public BorrowedBook returnBook(Long borrowedBookId){
        BorrowedBook borrowedBook = borrowedBookRepository.findById(borrowedBookId).orElseThrow(() -> new RuntimeException("Borrowed Book not found"));
        Book book = borrowedBook.getBook();
        if(book.getStatus() == BookStatus.AVAILABLE){
            throw new RuntimeException("Book is already returned");
        }
        borrowedBook.setReturnDate(LocalDateTime.now());
        book.setStatus(BookStatus.AVAILABLE);
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
    public List<BorrowedBook> getBorrowedBookHistory(Long userId) {
        return borrowedBookRepository.findByUser_UserId(userId);
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
    public BorrowedBook updateBorrowedBook(Long id, BorrowedBook borrowedBook) {
        BorrowedBook existingBorrowedBook = borrowedBookRepository.findById(id).orElseThrow(() -> new RuntimeException("Borrowed Book not found"));

        existingBorrowedBook.setBook(borrowedBook.getBook());
        existingBorrowedBook.setBorrowDate(borrowedBook.getBorrowDate());
        existingBorrowedBook.setReturnDate(borrowedBook.getReturnDate());

        return borrowedBookRepository.save(existingBorrowedBook);
    }

    @Override
    public void deleteBorrowedBook(Long id) {
        borrowedBookRepository.deleteById(id);
    }
    
}
