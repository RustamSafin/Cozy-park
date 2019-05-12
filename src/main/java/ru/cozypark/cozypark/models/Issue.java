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

    private String imageContent;

    private String imageName;

    private String lat;
    private String lng;

    private Boolean active = true;

    @ManyToOne
    private User user;
}
