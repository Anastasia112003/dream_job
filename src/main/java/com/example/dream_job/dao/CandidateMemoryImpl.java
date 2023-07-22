package com.example.dream_job.dao;

import com.example.dream_job.model.Candidate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CandidateMemoryImpl implements CandidateDao {
    private final Map<Long, Candidate> candidateMap = new HashMap<>();
    private long countId = 1;

    @Override
    public Candidate create(Candidate candidate) {
        Candidate saveCandidate = new Candidate();
        saveCandidate.setName(candidate.getName());
        saveCandidate.setDescription(candidate.getDescription());
        saveCandidate.setId(countId++);
        return candidateMap.put(saveCandidate.getId(), saveCandidate);
    }

    @Override
    public Collection<Candidate> findAll() {
        //List<Candidate> list = new ArrayList<>(candidateMap.values());
        //return list

        return candidateMap.values();
    }

    @Override
    public Optional<Candidate> findById(Long id) {
        return Optional.of(candidateMap.get(id));
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<Candidate> candidate = findById(id);
        if (candidate.isEmpty()) {
            return false;
        }
        Candidate candidate1 = candidate.get();
        candidateMap.remove(candidate1.getId());
        return true;
    }

    @Override
    public boolean update(Candidate candidate) {
        Optional<Candidate> optionalCandidate = findById(candidate.getId());
        if (optionalCandidate.isEmpty()) {
            return false;
        }
        Candidate updateCandidate = optionalCandidate.get();
        updateCandidate.setName(candidate.getName());
        updateCandidate.setDescription(candidate.getDescription());
        return candidateMap.put(updateCandidate.getId(), updateCandidate) != null;
    }

}
