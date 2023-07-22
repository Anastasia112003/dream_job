package com.example.dream_job.dao;

import com.example.dream_job.model.Vacancy;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class VacancyMemoryImpl implements VacancyDao {
    private final Map<Long, Vacancy> vacancyMap = new HashMap<>();
    private long countId = 1;

    @Override
    public Vacancy create(Vacancy vacancy) {
        Vacancy saveVacancy = new Vacancy();
        saveVacancy.setDescription(vacancy.getDescription());
        saveVacancy.setTitle(vacancy.getTitle());
        saveVacancy.setId(countId++);
        return vacancyMap.put(saveVacancy.getId(), saveVacancy);
    }

    @Override
    public Collection<Vacancy> findAll() {
        return vacancyMap.values();
    }

    @Override
    public Optional<Vacancy> findById(Long id) {
        return Optional.of(vacancyMap.get(id));
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<Vacancy> optionalVacancy = findById(id);
        if (optionalVacancy.isEmpty()) {
            return false;
        }
        vacancyMap.remove(optionalVacancy.get().getId());
        return true;
    }

    @Override
    public boolean update(Vacancy vacancy) {
        Optional<Vacancy> optionalVacancy = findById(vacancy.getId());
        if (optionalVacancy.isEmpty()) {
            return false;
        }
        Vacancy updateVacancy = optionalVacancy.get();
        updateVacancy.setDescription(vacancy.getDescription());
        updateVacancy.setTitle(vacancy.getTitle());
        return vacancyMap.put(updateVacancy.getId(), updateVacancy) != null;
    }
}
