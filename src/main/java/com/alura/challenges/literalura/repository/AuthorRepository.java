package com.alura.challenges.literalura.repository;

import com.alura.challenges.literalura.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
