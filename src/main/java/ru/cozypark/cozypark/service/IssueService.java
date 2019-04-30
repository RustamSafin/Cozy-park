package ru.cozypark.cozypark.service;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.multipart.MultipartFile;
import ru.cozypark.cozypark.models.Issue;
import ru.cozypark.cozypark.models.User;
import ru.cozypark.cozypark.payloads.IssueDtoRequest;
import ru.cozypark.cozypark.payloads.IssueDtoResponse;
import ru.cozypark.cozypark.repositories.IssueRepository;

import java.io.IOException;

@Service
public class IssueService {
    private final IssueRepository repository;

    public IssueService(IssueRepository repository) {
        this.repository = repository;
    }


    public void saveDto(IssueDtoRequest dtoRest, User user, MultipartFile file) throws IOException {
        Issue issue = new Issue();

        BeanUtils.copyProperties(dtoRest, issue);

        issue.setUser(user);

        issue.setImageName(file.getOriginalFilename());
        issue.setImageContent(file.getBytes());

        repository.save(issue);
    }

    public Page<IssueDtoResponse> findAllByUser(User user, Pageable pageable) {
        Page<Issue> issues = repository.findByUserId(user.getId(), pageable);

        return issues.map(issue -> {
            IssueDtoResponse res = new IssueDtoResponse();

            BeanUtils.copyProperties(issue, res);
            res.setImageUrl("/img/" + issue.getId());
            res.setUserId(issue.getUser().getId());
            return res;
        });
    }

    public Issue findById(Long issueId) {
        return repository.findById(issueId).orElseThrow(() -> new ResourceAccessException(""));
    }
}
