package ru.cozypark.cozypark.payloads;

import lombok.Data;
import ru.cozypark.cozypark.models.User;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Data
public class IssueDtoResponse {

    private Long id;

    private String title;

    private String body;

    private String imageUrl;

    private Long userId;

    private String lat;
    private String lng;
}
