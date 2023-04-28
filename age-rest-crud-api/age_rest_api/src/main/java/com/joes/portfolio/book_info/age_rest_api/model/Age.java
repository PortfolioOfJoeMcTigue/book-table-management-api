package com.joes.portfolio.book_info.age_rest_api.model;

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
@Table(name = "suggested_ages")
public class Age {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "age_code")
    private String age_code;
    @Column(name = "age_range")
    private String age_range;
    @CreationTimestamp
    @Column(name = "date_created")
    private LocalDateTime date_created;
    @UpdateTimestamp
    @Column(name = "date_updated")
    private LocalDateTime date_updated;
}
