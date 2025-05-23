package org.melisits.programming.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.melisits.programming.persistence.Schedule;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class ScheduleRepository implements PanacheRepository<Schedule> {


    public List<Schedule> findInRange(LocalDate from, LocalDate to) {
        return list("scFrom <= ?1 and scUntil >= ?2", to, from);
        // This includes schedules that *overlap* the range
        // Adjust to: "scFrom >= ?1 and scUntil <= ?2" to only include strictly within range
    }

}