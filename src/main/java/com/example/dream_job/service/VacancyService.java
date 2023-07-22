package com.example.dream_job.service;

import com.example.dream_job.model.Vacancy;

import java.util.Collection;
import java.util.Optional;

public interface VacancyService {
    Vacancy createV(Vacancy vacancy);

    Collection<Vacancy> getAllV();

    Optional<Vacancy> getByIdV(Long id);

    boolean deleteByIdV(Long id);

    boolean updateV(Vacancy vacancy);


}
