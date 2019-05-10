package ru.cozypark.cozypark.service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import ru.cozypark.cozypark.models.Issue;
import ru.cozypark.cozypark.models.User;
import ru.cozypark.cozypark.payloads.IssueDtoRequest;
import ru.cozypark.cozypark.payloads.IssueDtoResponse;
import ru.cozypark.cozypark.repositories.IssueRepository;

import java.io.IOException;
import java.util.Collections;
import java.util.function.Function;

@Service
public class IssueService {
    private final IssueRepository repository;
    private final RestTemplate restTemplate = new RestTemplate();

    public IssueService(IssueRepository repository) {
        this.repository = repository;
    }


    public void saveDto(IssueDtoRequest dtoRest, User user, MultipartFile file) throws IOException {
        Issue issue = new Issue();

        BeanUtils.copyProperties(dtoRest, issue);

        issue.setUser(user);

        issue.setImageName(file.getOriginalFilename());

        MultiValueMap body = new LinkedMultiValueMap<>();
        body.put("image", Collections.singletonList(new InputStreamResource(file.getInputStream())));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
        httpHeaders.set("Authorization", "Client-ID d2bebc8db7e9093");
        HttpEntity requestEntity = new HttpEntity<>(body, httpHeaders);
        ResponseEntity<ObjectNode> responseEntity= restTemplate.exchange("https://api.imgur.com/3/upload", HttpMethod.POST, requestEntity, ObjectNode.class);
        issue.setImageContent(responseEntity.getBody().get("data").get("link").asText());
        repository.save(issue);
    }

    public Page<IssueDtoResponse> findAllByUser(User user, Pageable pageable) {
        Page<Issue> issues = repository.findByUserId(user.getId(), pageable);

        return issues.map(mapToDto());
    }

    public Issue findById(Long issueId) {
        return repository.findById(issueId).orElseThrow(() -> new ResourceAccessException(""));
    }

    public Page<IssueDtoResponse> search(String query, Pageable pageable) {
        return query == null || query.isEmpty() ? repository.findAll(pageable).map(mapToDto()) : repository.findByTitleLikeOrBodyLike("%" + query + "%","%" + query + "%",pageable).map(mapToDto());
    }

    private Function<Issue, IssueDtoResponse> mapToDto(){
        return issue -> {
            IssueDtoResponse res = new IssueDtoResponse();

            BeanUtils.copyProperties(issue, res);
            res.setImageUrl(issue.getImageContent());
            res.setUserId(issue.getUser().getId());
            return res;
        };
    }
}
