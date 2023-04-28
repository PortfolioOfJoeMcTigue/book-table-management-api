package com.joes.portfolio.book_info.author_rest_api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "author_code")
    private String author_code;
    @Column(name = "first_name")
    private String first_name;
    @Column(name = "middle_name")
    private String middle_name;
    @Column(name = "last_name")
    private String last_name;
    @Column(name = "birth_date")
    private String birth_date;
    @Column(name = "birth_place")
    private String birth_place;
    @CreationTimestamp
    @Column(name = "date_created")
    private LocalDateTime date_created;
    @UpdateTimestamp
    @Column(name = "date_updated")
    private LocalDateTime date_updated;
}
