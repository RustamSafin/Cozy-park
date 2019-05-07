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

    private String lat;
    private String lng;

    @ManyToOne
    private User user;
}
