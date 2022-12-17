package ua.dragunov.accountingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.dragunov.accountingservice.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
