package com.lms.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "UserBooks")
@NoArgsConstructor
public class UserBookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String bookname;
    private String status;

    public UserBookEntity(String bookname) {
        this.bookname = bookname;
    }
}
