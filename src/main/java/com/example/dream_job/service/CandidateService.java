package com.example.dream_job.service;

import com.example.dream_job.model.Candidate;

import java.util.Collection;
import java.util.Optional;

public interface CandidateService {
    Candidate create(Candidate candidate);

    Collection<Candidate> getAll();

    Optional<Candidate> getById(Long id);

    boolean deleteById(Long id);

    boolean update(Candidate candidate);

    //метод поиск по имени кандидата
    Candidate getFirstByName(Candidate candidate);

    //найти всех кандидатов, которых зовут(name)
    Collection<Candidate> getByName(Candidate candidate);

    // метод найти кандидата по имени, который начинается с 1 буквы
    Candidate getByNameOrderByName(Candidate candidate);
}
