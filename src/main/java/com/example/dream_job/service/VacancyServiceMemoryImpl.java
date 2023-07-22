package com.example.dream_job.service;

import com.example.dream_job.dao.VacancyDao;
import com.example.dream_job.model.Vacancy;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class VacancyServiceMemoryImpl implements VacancyService {
    private final VacancyDao vacancyDao;

    public VacancyServiceMemoryImpl(VacancyDao vacancyDao) {
        this.vacancyDao = vacancyDao;
    }

    @Override
    public Vacancy createV(Vacancy vacancy) {
        return vacancyDao.create(vacancy);
    }

    @Override
    public Collection<Vacancy> getAllV() {
        return vacancyDao.findAll();
    }

    @Override
    public Optional<Vacancy> getByIdV(Long id) {
        return Optional.of(vacancyDao.findById(id).orElseThrow(() -> new NoSuchElementException("not found")));
    }

    @Override
    public boolean deleteByIdV(Long id) {
        return vacancyDao.deleteById(id);
    }

    @Override
    public boolean updateV(Vacancy vacancy) {
        return vacancyDao.update(vacancy);

    }
}
