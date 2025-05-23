package org.melisits.programming.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.melisits.programming.persistence.Address;
import org.melisits.programming.persistence.Schedule;
import org.melisits.programming.repository.AddressRepository;
import org.melisits.programming.repository.ScheduleRepository;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ScheduleService {

    @Inject
    ScheduleRepository scheduleRepository;

    @Inject
    AddressRepository addressRepository;

    public List<Schedule> listAll() {
        return scheduleRepository.listAll();
    }

    public Optional<Schedule> findById(Long id) {
        return scheduleRepository.findByIdOptional(id);
    }


    @Transactional
    public Schedule create(Schedule schedule) {

        // Load the existing Address from the database
        Optional<Address> existingAddress = addressRepository.findById(schedule.getAddress().getId());
        if (existingAddress.isEmpty()) {
            throw new IllegalArgumentException("Address with ID " + schedule.getAddress().getId() + " does not exist");
        }

        // Assign the managed Address instance to the Schedule
        schedule.setAddress(existingAddress.get());

        scheduleRepository.persist(schedule);
        return schedule;
    }


    public Schedule update(Long id, Schedule updatedSchedule) {
        return scheduleRepository.findByIdOptional(id)
                .map(schedule -> {
                    schedule.setTitle(updatedSchedule.getTitle());
                    schedule.setDescription(updatedSchedule.getDescription());
                    schedule.setFrom(updatedSchedule.getFrom());
                    schedule.setUntil(updatedSchedule.getUntil());
                    schedule.setFromTime(updatedSchedule.getFromTime());
                    schedule.setUntilTime(updatedSchedule.getUntilTime());
                    schedule.setAllDay(updatedSchedule.isAllDay());
                    schedule.setAddress(updatedSchedule.getAddress());
                    schedule.setMark(updatedSchedule.getMark());
                    return schedule;
                }).orElseThrow(() -> new RuntimeException("Schedule not found with id " + id));
    }


    @Transactional
    public boolean delete(Long id) {
        return scheduleRepository.deleteById(id);
    }
}