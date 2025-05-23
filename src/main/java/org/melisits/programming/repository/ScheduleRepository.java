package org.melisits.programming.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.melisits.programming.persistence.Schedule;

@ApplicationScoped
public class ScheduleRepository implements PanacheRepository<Schedule> {
    // Basic CRUD is available via PanacheRepository
}