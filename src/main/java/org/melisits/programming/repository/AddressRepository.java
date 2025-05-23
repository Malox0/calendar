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

    public List<Address> search(String query, int limit, int offset) {
        String jpql = """
            SELECT a FROM Address a
            WHERE LOWER(a.firstName) LIKE LOWER(CONCAT('%', :query, '%'))
               OR LOWER(a.lastName) LIKE LOWER(CONCAT('%', :query, '%'))
               OR LOWER(a.street) LIKE LOWER(CONCAT('%', :query, '%'))
        """;

        return em.createQuery(jpql, Address.class)
                .setParameter("query", query)
                .setFirstResult(offset)
                .setMaxResults(limit)
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
