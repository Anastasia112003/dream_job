package com.example.dream_job.service;


import com.example.dream_job.model.Vacancy;
import com.example.dream_job.repository.VacancyRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class VacancyServiceImpl implements VacancyService {
    private final VacancyRepository vacancyRepository;

    public VacancyServiceImpl(VacancyRepository vacancyRepository) {
        this.vacancyRepository = vacancyRepository;
    }

    @Override
    public Vacancy createV(Vacancy vacancy) {
        Vacancy saveVacancy = Vacancy.builder()
                .description(vacancy.getDescription())
                .title(vacancy.getTitle())
                .localDateTime(LocalDateTime.now())
                .build();
        return vacancyRepository.save(saveVacancy);
    }

    @Override
    public Collection<Vacancy> getAllV() {
        return vacancyRepository.findAll();
    }

    @Override
    public Optional<Vacancy> getByIdV(Long id) {
        return Optional.of(vacancyRepository.findById(id)).orElseThrow(() -> new NoSuchElementException("not found"));
    }

    @Override
    public boolean deleteByIdV(Long id) {
        boolean exist = vacancyRepository.existsById(id);
        if (!exist) {
            return false;
        }
        vacancyRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean updateV(Vacancy vacancy) {
        Optional<Vacancy> optionalVacancy = getByIdV(vacancy.getId());
        if (optionalVacancy.isEmpty()) {
            return false;
        }
        Vacancy getVacancy = optionalVacancy.get();
        getVacancy.setTitle(vacancy.getTitle());
        getVacancy.setDescription(vacancy.getDescription());
        vacancyRepository.save(getVacancy);
        return true;

    }
}
