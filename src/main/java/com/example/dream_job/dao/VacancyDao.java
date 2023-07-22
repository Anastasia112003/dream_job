package com.example.dream_job.dao;

import com.example.dream_job.model.Vacancy;

import java.util.Collection;
import java.util.Optional;

public interface VacancyDao {
    Vacancy create(Vacancy vacancy);

    Collection<Vacancy> findAll();

    Optional<Vacancy> findById(Long id);

    boolean deleteById(Long id);

    boolean update(Vacancy vacancy);


}
