package com.lms.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "Book")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    int quantity;

    public BookEntity() {
    }

    public BookEntity(String name, int quantity) {
        this.name=name;
        this.quantity = quantity;
    }
}