package com.alura.challenges.literalura.service;

import com.alura.challenges.literalura.dto.AuthorDTO;
import com.alura.challenges.literalura.dto.BookDTO;
import com.alura.challenges.literalura.model.Author;
import com.alura.challenges.literalura.model.Book;
import com.alura.challenges.literalura.repository.AuthorRepository;
import com.alura.challenges.literalura.repository.BookRepository;
import com.alura.challenges.literalura.util.DataMapper;
import com.alura.challenges.literalura.util.RequestMaker;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    public String findAllAuthors() {
        StringBuilder sb = new StringBuilder();

        List<AuthorDTO> authors = authorRepository.findAll().stream().map(DataMapper::authorToAuthorDTO).toList();

        for (AuthorDTO author : authors) {
            sb.append(author.toString());
        }

        return sb.toString();
    }

    public String findAllBooks() {
        StringBuilder sb = new StringBuilder();

        List<BookDTO> books = bookRepository.findAll().stream().map(DataMapper::bookToBookDTO).toList();

        for (BookDTO book : books) {
            sb.append(book.toString());
        }

        return sb.toString();
    }

    public String findAuthorsBetween(Integer year) {
        StringBuilder sb = new StringBuilder();

        List<AuthorDTO> authors = authorRepository.findAll()
                .stream()
                .map(DataMapper::authorToAuthorDTO)
                .filter(a -> a.birth() < year && a.death() > year).
                toList();
        for (AuthorDTO author : authors) {
            sb.append(author.toString());
        }

        return sb.isEmpty() ? "No existían autores vivos registrados para ese año" : sb.toString();
    }

    public String findBooksByLanguage(String language) {
        StringBuilder sb = new StringBuilder();

        List<BookDTO> books = bookRepository.findBooksByLanguage(language).stream().map(DataMapper::bookToBookDTO).toList();

        for (BookDTO book : books) {
            sb.append(book.toString());
        }

        return sb.toString();
    }

    public String findBookByTitle(String title) {
        Optional<List<Book>> book = bookRepository.findBookByTitle(title);
        if (book.isPresent() && !book.get().isEmpty())
            return DataMapper.bookToBookDTO(book.get().get(0)).toString();

        System.out.println("El libro no está guardado, un momento mientras lo buscamos en el repositorio ....");

        try {
            return saveBooksNAuthors(title);
        } catch (JsonProcessingException e) {
            System.out.println("No se ha encontrado el libro :(");
            return "";
        }
    }

    public String saveBooksNAuthors(String title) throws JsonProcessingException {
        String json = RequestMaker.doARequest(title);

        System.out.println(json);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(json);

        JsonNode firstBook = rootNode.path("results").get(0);

        String newTitle = firstBook.path("title").asText();
        Integer downloads = firstBook.path("download_count").asInt();
        String language = firstBook.path("languages").get(0).asText();

        JsonNode authorNode = firstBook.path("authors").get(0);
        String authorName = authorNode.get("name").asText();
        int birth = authorNode.get("birth_year").asInt();
        int death = authorNode.get("death_year").asInt();

        AuthorDTO authorDTO = new AuthorDTO(authorName, birth, death);

        BookDTO bookDTO = new BookDTO(newTitle, language, downloads, authorDTO);

        Author toSet = authorRepository.save(DataMapper.authorDTOToAuthor(authorDTO));
        Book toSave = DataMapper.bookDTOToBook(bookDTO);
        toSave.setAuthor(toSet);
        bookRepository.save(toSave);

        return bookDTO.toString();
    }
}
