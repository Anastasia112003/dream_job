package com.example.dream_job;

import com.example.dream_job.model.Vacancy;
import com.example.dream_job.repository.VacancyRepository;
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

public class VacancyServiceImplTest {

    @Autowired
    private VacancyRepository vacancyRepository;


    @Test
    public void creat() {
        Vacancy vacancy = new Vacancy();
        vacancy.setTitle("Work");
        vacancy.setDescription("teacher");
        vacancy.setLocalDateTime(LocalDateTime.now());

        Vacancy savedVacancy = vacancyRepository.save(vacancy);

        assertThat(savedVacancy).isNotNull();
        assertThat(savedVacancy.getId()).isGreaterThan(0);
    }

    @Test
    public void getAll() {
        Vacancy vacancy = new Vacancy();
        vacancy.setTitle("Work");
        vacancy.setDescription("teacher");
        vacancy.setLocalDateTime(LocalDateTime.now());

        Vacancy vacancy1 = new Vacancy();
        vacancy1.setTitle("Job");
        vacancy1.setDescription("developer");
        vacancy1.setLocalDateTime(LocalDateTime.now());

        vacancyRepository.save(vacancy1);
        vacancyRepository.save(vacancy);

        Collection<Vacancy> employeeList = vacancyRepository.findAll();

        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(16);
    }

    @Test
    public void getById() {
        Vacancy vacancy = new Vacancy();
        vacancy.setTitle("Work");
        vacancy.setDescription("teacher");
        vacancy.setLocalDateTime(LocalDateTime.now());
        vacancyRepository.save(vacancy);

        Vacancy vacancy1 = vacancyRepository.findById(vacancy.getId()).get();

        assertThat(vacancy1).isNotNull();

    }

    @Test
    public void deleteById() {
        Vacancy vacancy = new Vacancy();
        vacancy.setTitle("Work");
        vacancy.setDescription("teacher");
        vacancy.setLocalDateTime(LocalDateTime.now());
        vacancyRepository.save(vacancy);

        vacancyRepository.deleteById(vacancy.getId());
        Optional<Vacancy> vacancyOptional = vacancyRepository.findById(vacancy.getId());

        assertThat(vacancyOptional).isEmpty();
    }

    @Test

    public void update() {
        Vacancy vacancy = new Vacancy();
        vacancy.setTitle("Work");
        vacancy.setDescription("teacher");
        vacancy.setLocalDateTime(LocalDateTime.now());
        vacancyRepository.save(vacancy);

        Vacancy savedVacancy = vacancyRepository.findById(vacancy.getId()).get();
        savedVacancy.setDescription("developer");
        savedVacancy.setTitle("Job");
        savedVacancy.setLocalDateTime(LocalDateTime.now());
        Vacancy updatedVacancy = vacancyRepository.save(savedVacancy);

        assertThat(updatedVacancy.getDescription()).isEqualTo("developer");
        assertThat(updatedVacancy.getTitle()).isEqualTo("Job");

    }

}
