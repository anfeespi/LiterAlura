package com.alura.challenges.literalura.dto;

public record BookDTO(String title, String language, Integer downloads, AuthorDTO author) {
    @Override
    public String toString() {
        return "\nLibro: \n" +
                "Titulo: '" + title + "\n" +
                "Idioma: '" + language + "\n" +
                "Cantidad de descargas: " + downloads + "\n" +
                "Autor: " + author.name();
    }
}
