package com.libManSys.libManSys.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libManSys.libManSys.enums.BookStatus;
import com.libManSys.libManSys.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthorContainingIgnoreCaseOrTitleContainingIgnoreCaseOrIsbnContainingIgnoreCase(String author, String title, String isbn);

    Optional<Book> findById(Long id);

    List<Book> findByStatus(BookStatus status);
    List<Book> findByStatusNot(BookStatus status);
    List<Book> findByReturnDateBeforeAndStatus(LocalDate today, BookStatus status);
}
