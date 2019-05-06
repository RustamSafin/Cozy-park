package ru.cozypark.cozypark.controllers.rest;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.cozypark.cozypark.models.Issue;
import ru.cozypark.cozypark.models.User;
import ru.cozypark.cozypark.payloads.IssueDtoRequest;
import ru.cozypark.cozypark.payloads.IssueDtoResponse;
import ru.cozypark.cozypark.service.IssueService;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;

@RestController
@RequestMapping("/api/issue")
public class IssueController {

    private final IssueService service;

    public IssueController(IssueService service) {
        this.service = service;
    }

    @PostMapping
    public void save(IssueDtoRequest dtoRest, MultipartFile file, @ApiIgnore Authentication authentication) throws IOException {
        String mimeType = file.getContentType();
        if (mimeType != null && mimeType.split("/")[0].equals("image")) {
            User user = (User) authentication.getPrincipal();
            dtoRest.setId(null);
            dtoRest.setUserId(user.getId());

            service.saveDto(dtoRest, user, file);
        }
    }

    @ApiOperation(value = "Список операций")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page.")
    })
    @GetMapping
    public Page<IssueDtoResponse> getAll(@ApiIgnore Authentication authentication, @ApiIgnore Pageable pageable){
        User user = (User) authentication.getPrincipal();

        return service.findAllByUser(user, pageable);
    }
}
