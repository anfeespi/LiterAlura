package com.alura.challenges.literalura.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "autores")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre")
    private String name;
    @Column(name = "nacimiento")
    private Integer birth;
    @Column(name = "muerte")
    private Integer death;

    public Author(){

    }

    public Author(String name, Integer birth, Integer death) {
        this.name = name;
        this.birth = birth;
        this.death = death;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getBirth() {
        return birth;
    }

    public void setBirth(Integer birth) {
        this.birth = birth;
    }

    public Integer getDeath() {
        return death;
    }

    public void setDeath(Integer death) {
        this.death = death;
    }
}
