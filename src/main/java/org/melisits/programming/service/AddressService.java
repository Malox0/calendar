package org.melisits.programming.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.melisits.programming.persistence.Address;
import org.melisits.programming.repository.AddressRepository;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class AddressService {

    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    public Optional<Address> findById(String id) {
        return addressRepository.findById(id);
    }

    public List<Address> search(String query) {
        return addressRepository.search(query);
    }

    @Transactional
    public void create(Address address) {
        addressRepository.persist(address);
    }

    @Transactional
    public Address update(Address address) {
        return addressRepository.update(address);
    }

    @Transactional
    public boolean delete(String id) {
        return addressRepository.delete(id);
    }
}
