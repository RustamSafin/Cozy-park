package ru.cozypark.cozypark.payloads;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

@Data
public class IssueDtoRequest {

    private Long id;

    private String title;

    private String body;

    private Long userId;

    private String lat;
    private String lng;
}
