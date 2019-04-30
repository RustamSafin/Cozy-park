package ru.cozypark.cozypark.controllers.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.cozypark.cozypark.models.Issue;
import ru.cozypark.cozypark.models.User;
import ru.cozypark.cozypark.payloads.IssueDtoRequest;
import ru.cozypark.cozypark.payloads.IssueDtoResponse;
import ru.cozypark.cozypark.service.IssueService;

import java.io.IOException;

@RestController
@RequestMapping("/api/issue")
public class IssueController {

    private final IssueService service;

    public IssueController(IssueService service) {
        this.service = service;
    }

    @PostMapping
    public void save(IssueDtoRequest dtoRest, MultipartFile file, Authentication authentication) throws IOException {
        String mimeType = file.getContentType();
        if (mimeType != null && mimeType.split("/")[0].equals("image")) {
            User user = (User) authentication.getPrincipal();
            dtoRest.setId(null);
            dtoRest.setUserId(user.getId());

            service.saveDto(dtoRest, user, file);
        }
    }

    @GetMapping
    public Page<IssueDtoResponse> getAll(Authentication authentication, Pageable pageable){
        User user = (User) authentication.getPrincipal();

        return service.findAllByUser(user, pageable);
    }
}
