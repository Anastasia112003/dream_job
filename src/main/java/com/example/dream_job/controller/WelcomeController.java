package com.example.dream_job.controller;

import com.example.dream_job.model.Candidate;
import com.example.dream_job.model.Vacancy;
import com.example.dream_job.service.CandidateService;
import com.example.dream_job.service.VacancyService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
public class WelcomeController {
    private final CandidateService candidateService;
    private final VacancyService vacancyService;

    public WelcomeController(@Qualifier("candidateServiceImpl") CandidateService candidateService, @Qualifier("vacancyServiceImpl") VacancyService vacancyService) {
        this.vacancyService = vacancyService;
        this.candidateService = candidateService;
    }

    @PostMapping("/save")
    public Candidate save(@RequestBody Candidate candidate) {

        return candidateService.create(candidate);
    }

    @GetMapping("/all")
    public Collection<Candidate> findAll() {
        return candidateService.getAll();

    }

    @GetMapping("/{id}")
    public Optional<Candidate> getByID(@PathVariable long id) {
        return candidateService.getById(id);
    }

    @PostMapping("/saveV")
    public Vacancy save(@RequestBody Vacancy vacancy) {

        return vacancyService.createV(vacancy);
    }

    @GetMapping("/allV")
    public Collection<Vacancy> findAllV() {
        return vacancyService.getAllV();

    }

    @GetMapping("/{id}/V")
    public Optional<Vacancy> getByIDV(@PathVariable long id) {
        return vacancyService.getByIdV(id);
    }


}
