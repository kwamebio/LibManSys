package com.libManSys.libManSys.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libManSys.libManSys.model.Book;
import com.libManSys.libManSys.model.BorrowedBook;
import com.libManSys.libManSys.model.User;

@Repository
public interface BorrowedBookRepository extends JpaRepository<BorrowedBook, Integer>  {
    List<BorrowedBook> findByUserAndBook(User user, Book book);
    List<BorrowedBook> findByReturnDateIsBefore(LocalDateTime date);
}
