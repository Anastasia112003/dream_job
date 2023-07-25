package com.example.dream_job;

import com.example.dream_job.dao.VacancyDao;
import com.example.dream_job.model.Vacancy;
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
public class VacancyMemoryImlTest {
    @Autowired
    private VacancyDao vacancyMemory;

    @Test
    public void create() {
        HashMap<Long, Vacancy> vacancyList = new HashMap<>();

        Vacancy vacancy = new Vacancy();
        long id = 1;
        vacancy.setTitle("Work");
        vacancy.setDescription("teacher");
        vacancy.setLocalDateTime(LocalDateTime.now());
        vacancy.setId(id++);
        vacancyList.put(id, vacancy);
        vacancyMemory.create(vacancy);

        assertThat(vacancy).isNotNull();
        assertThat(vacancy.getId()).isGreaterThan(0);
    }

    @Test

    public void findAll() {

        Vacancy vacancy = new Vacancy();
        vacancy.setTitle("Work");
        vacancy.setDescription("teacher");
        vacancy.setLocalDateTime(LocalDateTime.now());


        Vacancy vacancy1 = new Vacancy();
        vacancy.setTitle("Job");
        vacancy.setDescription("developer");
        vacancy.setLocalDateTime(LocalDateTime.now());

        vacancyMemory.create(vacancy);
        vacancyMemory.create(vacancy1);

        Collection<Vacancy> vacancyList = vacancyMemory.findAll();


        assertThat(vacancyList).isNotNull();
        assertThat(vacancyList.size()).isEqualTo(2);
    }

    @Test
    public void findById() {
        HashMap<Long, Vacancy> vacancyList = new HashMap<>();

        Vacancy vacancy = new Vacancy();
        long id = 1;
        vacancy.setTitle("Work");
        vacancy.setDescription("teacher");
        vacancy.setLocalDateTime(LocalDateTime.now());
        vacancy.setId(id++);
        vacancyList.put(id, vacancy);
        vacancyMemory.create(vacancy);

        Vacancy vacancy1 = vacancyMemory.findById(vacancy.getId()).get();

        assertThat(vacancy1).isNotNull();

    }

    @Test
    public void deleteById() {
        HashMap<Long, Vacancy> vacancyList = new HashMap<>();
        Vacancy vacancy = new Vacancy();
        long id = 1;
        vacancy.setTitle("Work");
        vacancy.setDescription("teacher");
        vacancy.setLocalDateTime(LocalDateTime.now());
        vacancy.setId(id++);
        vacancyList.put(id, vacancy);
        vacancyMemory.create(vacancy);

        Optional<Vacancy> candidateOptional = vacancyMemory.findById(vacancy.getId());
        vacancyMemory.deleteById(vacancy.getId());

        assertThat(candidateOptional.isEmpty());

    }

    @Test
    public void update() {
        HashMap<Long, Vacancy> vacancyList = new HashMap<>();
        long id = 1;
        Vacancy vacancy = new Vacancy();
        vacancy.setTitle("Work");
        vacancy.setDescription("teacher");
        vacancy.setLocalDateTime(LocalDateTime.now());
        vacancy.setId(id++);
        vacancyList.put(id, vacancy);
        vacancyMemory.create(vacancy);

        Vacancy savedVacancy = vacancyMemory.findById(vacancy.getId()).get();
        savedVacancy.setDescription("developer");
        savedVacancy.setTitle("Job");
        savedVacancy.setLocalDateTime(LocalDateTime.now());
        vacancyList.put(id, vacancy);
        vacancyMemory.create(savedVacancy);

        assertThat(savedVacancy.getDescription()).isEqualTo("developer");
        assertThat(savedVacancy.getTitle()).isEqualTo("Job");

    }
}
