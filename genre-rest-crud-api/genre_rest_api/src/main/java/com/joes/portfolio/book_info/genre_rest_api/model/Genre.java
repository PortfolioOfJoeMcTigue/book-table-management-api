package com.joes.portfolio.book_info.genre_rest_api.model;

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
@Table(name = "genres")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "genre_code")
    private String genre_code;
    @Column(name = "genre")
    private String genre;
    @CreationTimestamp
    @Column(name = "date_created")
    private LocalDateTime date_created;
    @UpdateTimestamp
    @Column(name = "date_updated")
    private LocalDateTime date_updated;
}
