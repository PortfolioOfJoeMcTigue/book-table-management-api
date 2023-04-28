package com.joes.portfolio.book_info.book_rest_api.model;

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
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "isbn")
    private String isbn;
    @Column(name = "title")
    private String title;
    @Column(name = "summary")
    private String summary;
    @Column(name = "page_count")
    private int page_count;
    @Column(name = "publish_date")
    private String publish_date;
    @Column(name = "age_code")
    private String age_code;
    @Column(name = "author_code")
    private String author_code;
    @Column(name = "genre_code")
    private String genre_code;
    @Column(name = "publisher_code")
    private String publisher_code;
    @Column(name = "rating_code")
    private String rating_code;
    @CreationTimestamp
    @Column(name = "date_created")
    private LocalDateTime date_created;
    @UpdateTimestamp
    @Column(name = "date_updated")
    private LocalDateTime date_updated;
}
