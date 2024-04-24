package org.example.taskforpractive.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "news")
public class NewsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "news_id")
    private long id;

    @Column(name = "heading")
    private String heading;

    @Column(name = "content")
    private String content;

    @Column(name = "created_at")
    private LocalDateTime created_at;
}
