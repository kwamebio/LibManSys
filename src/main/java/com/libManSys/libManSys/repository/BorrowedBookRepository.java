package com.libManSys.libManSys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libManSys.libManSys.model.BorrowedBook;

@Repository
public interface BorrowedBookRepository extends JpaRepository<BorrowedBook, Integer>  {
    
}
