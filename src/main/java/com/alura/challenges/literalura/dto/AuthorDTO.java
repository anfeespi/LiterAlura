package com.alura.challenges.literalura.dto;

public record AuthorDTO(String name, Integer birth, Integer death) {
    @Override
    public String toString() {
        return "\nAuthor: \n" +
                "Nombre: " + name + "\n" +
                "Año de nacimiento: " + birth + "\n" +
                "Año de fallecimiento: " + death;
    }
}
