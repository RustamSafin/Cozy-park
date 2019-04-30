package ru.cozypark.cozypark.payloads;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class IssueDtoRequest {

    private Long id;

    private String title;

    private String body;

    private Long userId;

    private Long lat;
    private Long lng;
}
