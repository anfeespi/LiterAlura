package com.alura.challenges.literalura.repository;

import com.alura.challenges.literalura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<List<Book>> findBookByTitle(String title);

    List<Book> findBooksByLanguage(String language);
}
