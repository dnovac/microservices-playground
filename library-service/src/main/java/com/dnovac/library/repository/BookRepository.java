package com.dnovac.library.repository;

import com.dnovac.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * @author Dan Novac on 18/10/2020
 * @project microservices-playground
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

}
