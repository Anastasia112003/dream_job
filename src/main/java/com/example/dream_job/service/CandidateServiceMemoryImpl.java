package com.example.dream_job.service;

import com.example.dream_job.dao.CandidateDao;
import com.example.dream_job.model.Candidate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CandidateServiceMemoryImpl implements CandidateService {
    private final CandidateDao candidateDao;

    public CandidateServiceMemoryImpl(CandidateDao candidateDao) {
        this.candidateDao = candidateDao;
    }

    @Override
    public Candidate create(Candidate candidate) {
        return candidateDao.create(candidate);
    }

    @Override
    public Collection<Candidate> getAll() {
        return candidateDao.findAll();
    }

    @Override
    public Optional<Candidate> getById(Long id) {
        return Optional.of(candidateDao.findById(id).orElseThrow(() -> new NoSuchElementException("not found")));
    }

    @Override
    public boolean deleteById(Long id) {
        return candidateDao.deleteById(id);
    }

    @Override
    public boolean update(Candidate candidate) {
        return candidateDao.update(candidate);
    }

    @Override
    public Candidate getFirstByName(Candidate candidate) {
        return null;
    }

    @Override
    public Collection<Candidate> getByName(Candidate candidate) {
        return null;
    }

    @Override
    public Candidate getByNameOrderByName(Candidate candidate) {
        return null;
    }

}

