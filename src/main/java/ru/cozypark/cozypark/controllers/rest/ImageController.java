package ru.cozypark.cozypark.controllers.rest;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.cozypark.cozypark.models.Issue;
import ru.cozypark.cozypark.service.IssueService;

@RestController
@RequestMapping("/img")
public class ImageController {

    private final IssueService service;

    public ImageController(IssueService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ByteArrayResource> resource(Long issueId) {
//       return new ByteArrayResource(service.findById(issueId).getImageContent());

        Issue issue = service.findById(issueId);

        MediaType type = MediaType.ALL;
        String[] tokens = issue.getImageName().split("\\.");
        if (tokens[tokens.length - 1].toLowerCase().equals("png")) {
            type = MediaType.IMAGE_PNG;
        }

        if (tokens[tokens.length - 1].toLowerCase().equals("jpeg")||tokens[tokens.length - 1].toLowerCase().equals("jpg")) {
            type = MediaType.IMAGE_JPEG;
        }

        if (tokens[tokens.length - 1].toLowerCase().equals("gif")) {
            type = MediaType.IMAGE_GIF;
        }

        return ResponseEntity.ok()
                // Content-Disposition
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + issue.getImageName())
                // Content-Type
                .contentType(type)
                // Contet-Length
                .contentLength(issue.getImageContent().length) //
                .body(new ByteArrayResource(issue.getImageContent()));
    }
}
