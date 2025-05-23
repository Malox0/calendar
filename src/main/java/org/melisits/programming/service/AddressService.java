package org.melisits.programming.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.melisits.programming.dto.AddressDTO;
import org.melisits.programming.mapper.AddressMapper;
import org.melisits.programming.persistence.Address;
import org.melisits.programming.repository.AddressRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<AddressDTO> findAll() {
        List<Address> entities = addressRepository.findAll();
        return entities.stream()
                .map(AddressMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<AddressDTO> findById(String id) {
        Optional<Address> entityOpt = addressRepository.findById(id);
        return entityOpt.map(AddressMapper::toDTO);
    }

    public List<AddressDTO> search(String query, int limit, int offset) {
        List<Address> entities = addressRepository.search(query, limit, offset);
        return entities.stream()
                .map(AddressMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void create(AddressDTO addressDTO) {
        Address entity = AddressMapper.toEntity(addressDTO);
        addressRepository.persist(entity);
        // Optional: Set generated ID back to DTO if needed
        addressDTO.setId(entity.getId());
    }

    @Transactional
    public AddressDTO update(AddressDTO addressDTO) {
        Address entity = AddressMapper.toEntity(addressDTO);
        Address updatedEntity = addressRepository.update(entity);
        return AddressMapper.toDTO(updatedEntity);
    }

    @Transactional
    public boolean delete(String id) {
        return addressRepository.delete(id);
    }
}
