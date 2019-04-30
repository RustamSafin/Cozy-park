package ru.cozypark.cozypark.controllers.web;

import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.cozypark.cozypark.models.User;
import ru.cozypark.cozypark.payloads.IssueDtoRequest;
import ru.cozypark.cozypark.service.IssueService;

import java.io.IOException;

@Controller
@RequestMapping("/issue")
public class IssueControllerWeb {

    private final IssueService service;

    public IssueControllerWeb(IssueService service) {

        this.service = service;
    }

    @GetMapping("/search")
    public String list(Model model, @RequestParam(required = false) String query, Pageable pageable){
        model.addAttribute("issues", service.search(query, pageable));
        return "issue/search";
    }

    @GetMapping()
    public String post(){
        return "issue/issueForm";
    }

    @PostMapping
    public String save(IssueDtoRequest dtoRest, MultipartFile imageFile, Authentication authentication) throws IOException {
        String mimeType = imageFile.getContentType();
        if (mimeType != null && mimeType.split("/")[0].equals("image")) {
            User user = (User) authentication.getPrincipal();
            dtoRest.setId(null);
            dtoRest.setUserId(user.getId());

            service.saveDto(dtoRest, user, imageFile);
        }
        return "redirect:/issue/search";
    }
}
