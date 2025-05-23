package org.melisits.programming.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.melisits.programming.dto.ScheduleDTO;
import org.melisits.programming.dto.AddressDTO;
import org.melisits.programming.dto.ScheduleMapper;
import org.melisits.programming.mapper.AddressMapper;
import org.melisits.programming.persistence.Address;
import org.melisits.programming.persistence.Schedule;
import org.melisits.programming.repository.AddressRepository;
import org.melisits.programming.repository.ScheduleRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class ScheduleService {

    @Inject
    ScheduleRepository scheduleRepository;

    @Inject
    AddressRepository addressRepository;

    public List<ScheduleDTO> listAll() {
        List<Schedule> schedules = scheduleRepository.listAll();
        return schedules.stream()
                .map(ScheduleMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<ScheduleDTO> findById(Long id) {
        return scheduleRepository.findByIdOptional(id)
                .map(ScheduleMapper::toDTO);
    }

    public List<ScheduleDTO> findInRange(LocalDate from, LocalDate to) {
        List<Schedule> schedules = scheduleRepository.findInRange(from, to);
        return schedules.stream()
                .map(ScheduleMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ScheduleDTO create(ScheduleDTO scheduleDTO) {
        AddressDTO addressDTO = scheduleDTO.getAddress();
        if (addressDTO == null || addressDTO.getId() == null) {
            throw new IllegalArgumentException("Schedule must have an Address with a valid ID");
        }

        Optional<Address> existingAddress = addressRepository.findById(addressDTO.getId());
        if (existingAddress.isEmpty()) {
            throw new IllegalArgumentException("Address with ID " + addressDTO.getId() + " does not exist");
        }

        Schedule schedule = ScheduleMapper.toEntity(scheduleDTO);
        schedule.setAddress(existingAddress.get());

        scheduleRepository.persist(schedule);
        return ScheduleMapper.toDTO(schedule);
    }

    public ScheduleDTO update(Long id, ScheduleDTO updatedSchedule) {
        return scheduleRepository.findByIdOptional(id)
                .map(schedule -> {
                    if (updatedSchedule.getTitle() != null) schedule.setTitle(updatedSchedule.getTitle());
                    if (updatedSchedule.getDescription() != null) schedule.setDescription(updatedSchedule.getDescription());
                    if (updatedSchedule.getScFrom() != null) schedule.setScFrom(updatedSchedule.getScFrom());
                    if (updatedSchedule.getScUntil() != null) schedule.setScUntil(updatedSchedule.getScUntil());
                    if (updatedSchedule.getFromTime() != null) schedule.setFromTime(updatedSchedule.getFromTime());
                    if (updatedSchedule.getUntilTime() != null) schedule.setUntilTime(updatedSchedule.getUntilTime());
                    // similarly for all other fields...
                    return ScheduleMapper.toDTO(schedule);
                }).orElseThrow(() -> new RuntimeException("Schedule not found with id " + id));
    }


    @Transactional
    public boolean delete(Long id) {
        return scheduleRepository.deleteById(id);
    }
}
