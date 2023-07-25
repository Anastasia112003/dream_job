package com.example.dream_job;

import com.example.dream_job.dao.CandidateDao;
import com.example.dream_job.model.Candidate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CandidateMemoryImplTest {

    @Autowired
    private CandidateDao candidateMemory;

    @Test
    public void create() {
        HashMap<Long, Candidate> candidateList = new HashMap<>();

        Candidate candidate = new Candidate();
        long id = 1;
        candidate.setName("Kate");
        candidate.setDescription("teacher");
        candidate.setLocalDateTime(LocalDateTime.now());
        candidate.setId(id++);
        candidateList.put(id, candidate);
        candidateMemory.create(candidate);

        assertThat(candidate).isNotNull();
        assertThat(candidate.getId()).isGreaterThan(0);
    }

    @Test

    public void findAll() {

        Candidate candidate = new Candidate();
        candidate.setName("Kate");
        candidate.setDescription("teacher");
        candidate.setLocalDateTime(LocalDateTime.now());


        Candidate candidate1 = new Candidate();
        candidate.setName("Oleg");
        candidate.setDescription("developer");
        candidate.setLocalDateTime(LocalDateTime.now());

        candidateMemory.create(candidate);
        candidateMemory.create(candidate1);

        Collection<Candidate> employeeList = candidateMemory.findAll();


        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);
    }

    @Test
    public void findById() {
        HashMap<Long, Candidate> candidateList = new HashMap<>();

        Candidate candidate = new Candidate();
        long id = 1;
        candidate.setName("Kate");
        candidate.setDescription("teacher");
        candidate.setLocalDateTime(LocalDateTime.now());
        candidate.setId(id++);
        candidateList.put(id, candidate);
        candidateMemory.create(candidate);

        Candidate candidate1 = candidateMemory.findById(candidate.getId()).get();

        assertThat(candidate1).isNotNull();

    }

    @Test
    public void deleteById() {
        HashMap<Long, Candidate> candidateList = new HashMap<>();
        Candidate candidate = new Candidate();
        long id = 1;
        candidate.setName("Kate");
        candidate.setDescription("teacher");
        candidate.setLocalDateTime(LocalDateTime.now());
        candidate.setId(id++);
        candidateList.put(id, candidate);
        candidateMemory.create(candidate);

        Optional<Candidate> candidateOptional = candidateMemory.findById(candidate.getId());
        candidateMemory.deleteById(candidate.getId());

        assertThat(candidateOptional.isEmpty());

    }

    @Test
    public void update() {
        HashMap<Long, Candidate> candidateList = new HashMap<>();
        long id = 1;
        Candidate candidate = new Candidate();
        candidate.setName("Kate");
        candidate.setDescription("teacher");
        candidate.setLocalDateTime(LocalDateTime.now());
        candidate.setId(id++);
        candidateList.put(id, candidate);
        candidateMemory.create(candidate);

        Candidate savedCandidate = candidateMemory.findById(candidate.getId()).get();
        savedCandidate.setDescription("developer");
        savedCandidate.setName("Oleg");
        savedCandidate.setLocalDateTime(LocalDateTime.now());
        candidateList.put(id, candidate);
        candidateMemory.create(savedCandidate);

        assertThat(savedCandidate.getDescription()).isEqualTo("developer");
        assertThat(savedCandidate.getName()).isEqualTo("Oleg");

    }

}
