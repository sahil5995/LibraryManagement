package com.lms.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "UserBooks")
public class UserBookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String username;
    String bookname;
    String status;


    public UserBookEntity() {
    }

    public UserBookEntity(String bookname) {
        this.bookname = bookname;
    }
}
