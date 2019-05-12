package ru.cozypark.cozypark.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.cozypark.cozypark.models.Issue;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Long> {
    Page<Issue> findByUserId(long id, Pageable pageable);

    Page<Issue> findByTitleLikeOrBodyLike(String qT, String qB,Pageable pageable);

    Page<Issue> findByUserIdAndActiveTrue(Long id, Pageable pageable);
    Page<Issue> findByUserIdAndActiveFalse(Long id, Pageable pageable);
}
