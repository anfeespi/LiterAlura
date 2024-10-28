package com.alura.challenges.literalura.util;

import com.alura.challenges.literalura.dto.AuthorDTO;
import com.alura.challenges.literalura.dto.BookDTO;
import com.alura.challenges.literalura.model.Author;
import com.alura.challenges.literalura.model.Book;

public class DataMapper {
    public static Author authorDTOToAuthor(AuthorDTO authorDTO) {
        return new Author(authorDTO.name(), authorDTO.birth(), authorDTO.death());
    }

    public static AuthorDTO authorToAuthorDTO(Author author) {
        return new AuthorDTO(author.getName(), author.getBirth(), author.getDeath());
    }

    public static Book bookDTOToBook(BookDTO bookDTO) {
        return new Book(bookDTO.title(), bookDTO.language(), bookDTO.downloads(), authorDTOToAuthor(bookDTO.author()));
    }

    public static BookDTO bookToBookDTO(Book book) {
        return new BookDTO(book.getTitle(), book.getLanguage(), book.getDownloads(), authorToAuthorDTO(book.getAuthor()));
    }
}
