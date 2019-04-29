package ru.cozypark.cozypark.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@EqualsAndHashCode
@Data
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String body;

    @Lob
    private byte[] imageContent;

    private String imageName;

    private Long lat;
    private Long lng;

    @ManyToOne
    private User user;
}
