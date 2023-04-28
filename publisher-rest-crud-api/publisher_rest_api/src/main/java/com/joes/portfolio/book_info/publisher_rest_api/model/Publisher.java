package com.joes.portfolio.book_info.publisher_rest_api.model;

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
@Table(name = "publishers")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "publisher_code")
    private String publisher_code;
    @Column(name = "publisher_name")
    private String publisher_name;
    @CreationTimestamp
    @Column(name = "date_created")
    private LocalDateTime date_created;
    @UpdateTimestamp
    @Column(name = "date_updated")
    private LocalDateTime date_updated;
}
