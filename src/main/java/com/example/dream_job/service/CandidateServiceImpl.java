package com.example.dream_job.service;

import com.example.dream_job.model.Candidate;
import com.example.dream_job.repository.CandidateRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class CandidateServiceImpl implements CandidateService {
    private final CandidateRepository candidateRepository;

    public CandidateServiceImpl(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    @Override
    public Candidate create(Candidate candidate) {
        Candidate saveCandidate = Candidate.builder()
                .name(candidate.getName())
                .description(candidate.getDescription())
                .localDateTime(LocalDateTime.now())
                .build();
        return candidateRepository.save(saveCandidate);
    }

    @Override
    public Collection<Candidate> getAll() {
        return candidateRepository.findAll();
    }

    @Override
    public Optional<Candidate> getById(Long id) {
        return Optional.of(candidateRepository.findById(id).orElseThrow(() -> new NoSuchElementException("not found")));
    }

    @Override
    public boolean deleteById(Long id) {
        boolean exist = candidateRepository.existsById(id);
        if (!exist) {
            return false;
        }
        candidateRepository.deleteById(id);
        return true;
    }
    @Override
    public boolean update(Candidate candidate) {
        Optional<Candidate> optionalCandidate = getById(candidate.getId());
        if (optionalCandidate.isEmpty()) {
            return false;
        }
        Candidate getCandidate = optionalCandidate.get();
        getCandidate.setName(candidate.getName());
        getCandidate.setDescription(candidate.getDescription());
        candidateRepository.save(getCandidate);
        return true;
    }

    //метод поиск по имени кандидата
    @Override
    public Candidate getFirstByName(Candidate candidate) {
        String name = candidate.getName();
        return candidateRepository.findFirstByName(name);
    }

    //найти всех кандидатов, которых зовут(name)
    @Override
    public Collection<Candidate> getByName(Candidate candidate) {
        String name = candidate.getName();
        return candidateRepository.findByName(name);
    }

    // метод найти кандидата по имени, который начинается с 1 буквы
    @Override
    public Candidate getByNameOrderByName(Candidate candidate) {
        String name = candidate.getName();
        return candidateRepository.findByNameOrderByName(name);
    }

}

