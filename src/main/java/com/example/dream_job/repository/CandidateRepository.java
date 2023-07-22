package com.example.dream_job.repository;

import com.example.dream_job.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    Candidate findFirstByName(String name);

    Collection<Candidate> findByName(String name);

    Candidate findByNameOrderByName(String name);

}
