package com.example.dream_job;

import com.example.dream_job.model.Candidate;
import com.example.dream_job.repository.CandidateRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest

public class CandidateServiceImplTest {

    @Autowired
    private CandidateRepository candidateRepository;


    @Test
    public void creat() {
        Candidate candidate = new Candidate();
        candidate.setName("Kate");
        candidate.setDescription("teacher");
        candidate.setLocalDateTime(LocalDateTime.now());

        Candidate savedCandidate = candidateRepository.save(candidate);

        assertThat(savedCandidate).isNotNull();
        assertThat(savedCandidate.getId()).isGreaterThan(0);
    }

    @Test
    public void getAll() {
        Candidate candidate = new Candidate();
        candidate.setName("Kate");
        candidate.setDescription("teacher");
        candidate.setLocalDateTime(LocalDateTime.now());

        Candidate candidate1 = new Candidate();
        candidate.setName("Oleg");
        candidate.setDescription("developer");
        candidate.setLocalDateTime(LocalDateTime.now());

        candidateRepository.save(candidate1);
        candidateRepository.save(candidate);

        Collection<Candidate> employeeList = candidateRepository.findAll();

        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(47);
    }

    @Test
    public void getById() {
        Candidate candidate = new Candidate();
        candidate.setName("Kate");
        candidate.setDescription("teacher");
        candidate.setLocalDateTime(LocalDateTime.now());
        candidateRepository.save(candidate);

        Candidate candidate1 = candidateRepository.findById(candidate.getId()).get();

        assertThat(candidate1).isNotNull();

    }

    @Test
    public void deleteById() {
        Candidate candidate = new Candidate();
        candidate.setName("Kate");
        candidate.setDescription("teacher");
        candidate.setLocalDateTime(LocalDateTime.now());
        candidateRepository.save(candidate);

        candidateRepository.deleteById(candidate.getId());
        Optional<Candidate> candidateOptional = candidateRepository.findById(candidate.getId());

        assertThat(candidateOptional).isEmpty();
    }

    @Test

    public void update() {
        Candidate candidate = new Candidate();
        candidate.setName("Kate");
        candidate.setDescription("teacher");
        candidate.setLocalDateTime(LocalDateTime.now());
        candidateRepository.save(candidate);

        Candidate savedCandidate = candidateRepository.findById(candidate.getId()).get();
        savedCandidate.setDescription("developer");
        savedCandidate.setName("Oleg");
        savedCandidate.setLocalDateTime(LocalDateTime.now());
        Candidate updatedCandidate = candidateRepository.save(savedCandidate);

        assertThat(updatedCandidate.getDescription()).isEqualTo("developer");
        assertThat(updatedCandidate.getName()).isEqualTo("Oleg");

    }

    @Test
    public void getByName() {
        Candidate candidate = new Candidate();
        candidate.setName("Kate");
        candidate.setDescription("teacher");
        candidate.setLocalDateTime(LocalDateTime.now());
        candidateRepository.save(candidate);

        Collection<Candidate> candidate1 = candidateRepository.findByName(candidate.getName());

        assertThat(candidate1).isNotNull();
    }

    @Test
    public void getFirstByName() {
        Candidate candidate = new Candidate();
        candidate.setName("Kate");
        candidate.setDescription("teacher");
        candidate.setLocalDateTime(LocalDateTime.now());
        candidateRepository.save(candidate);

        Candidate candidate1 = candidateRepository.findFirstByName(candidate.getName());

        assertThat(candidate1).isNotNull();
    }

    @Test
    public void getByNameOrderByName() {
        Candidate candidate = new Candidate();
        candidate.setName("Kate");
        candidate.setDescription("teacher");
        candidate.setLocalDateTime(LocalDateTime.now());
        candidateRepository.save(candidate);

        Candidate candidate1 = candidateRepository.findByNameOrderByName(candidate.getName());

        assertThat(candidate1).isNotNull();

    }
}
