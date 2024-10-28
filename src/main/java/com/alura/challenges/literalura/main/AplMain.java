package com.alura.challenges.literalura.main;

import com.alura.challenges.literalura.service.BookService;

import java.util.Scanner;

public class AplMain {
    private Scanner sc = new Scanner(System.in);
    private final String mainMenu = "\t --Bienvenido a LiterAlura!--\n" +
            "Digita la opción que quieras realizar: \n" +
            "1. Buscar libro por su titulo\n" +
            "2. Listar los libros que están registrados\n" +
            "3. Listar los autores registrados\n" +
            "4. Que autores estaban vivos en un año?\n" +
            "5. Mostrar los libros que estén por idioma\n" +
            "#. Salir\n" +
            "____________________________________________________";
    private BookService bookService;

    public AplMain(BookService bookService) {
        this.bookService = bookService;
        mainMenu();
    }

    public void mainMenu() {
        System.out.println(mainMenu);
        switch (sc.nextLine()) {
            case "1":
                findByTitle();
                break;
            case "2":
                registeredBooks();
                break;
            case "3":
                registeredAuthors();
                break;
            case "4":
                authorsByYear();
                break;
            case "5":
                booksByLanguage();
                break;
            default:
                System.out.println("Gracias por usar!");
        }
    }

    public void findByTitle() {
        System.out.println("Ingresa el titulo: ");
        String title = sc.nextLine();
        System.out.println(bookService.findBookByTitle(title));
        mainMenu();
    }

    public void registeredBooks() {
        System.out.println(bookService.findAllBooks());
        mainMenu();
    }

    public void registeredAuthors() {
        System.out.println(bookService.findAllAuthors());
        mainMenu();
    }

    public void authorsByYear() {
        System.out.println("Ingresa el año del que quieres consultar: ");
        int year = Integer.parseInt(sc.nextLine());
        System.out.println(bookService.findAuthorsBetween(year));
        mainMenu();
    }

    public void booksByLanguage() {
        System.out.println("Ingresa el código del lenguaje a consultar (ejemplo: 'es'): ");
        System.out.println(bookService.findBooksByLanguage(sc.nextLine()));
        mainMenu();
    }
}
