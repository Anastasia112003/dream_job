package com.example.dream_job.dao;

import com.example.dream_job.model.Candidate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

public interface CandidateDao {
    Candidate create(Candidate candidate);

    Collection<Candidate> findAll();

    Optional<Candidate> findById(Long id);

    boolean deleteById(Long id);

    boolean update(Candidate candidate);
}
