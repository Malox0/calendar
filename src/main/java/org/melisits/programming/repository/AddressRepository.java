package org.melisits.programming.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.melisits.programming.persistence.Address;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class AddressRepository {

    @PersistenceContext
    EntityManager em;

    public List<Address> findAll() {
        return em.createQuery("SELECT a FROM Address a", Address.class).getResultList();
    }

    public Optional<Address> findById(String id) {
        return Optional.ofNullable(em.find(Address.class, id));
    }

    public List<Address> search(String query) {
        String q = "%" + query.toLowerCase() + "%";
        return em.createQuery("""
            SELECT a FROM Address a 
            WHERE LOWER(a.firstName) LIKE :query 
               OR LOWER(a.lastName) LIKE :query 
               OR LOWER(a.street) LIKE :query
        """, Address.class)
                .setParameter("query", q)
                .getResultList();
    }

    @Transactional
    public void persist(Address address) {
        em.persist(address);
    }

    @Transactional
    public Address update(Address address) {
        return em.merge(address);
    }

    @Transactional
    public boolean delete(String id) {
        Address found = em.find(Address.class, id);
        if (found != null) {
            em.remove(found);
            return true;
        }
        return false;
    }
}
