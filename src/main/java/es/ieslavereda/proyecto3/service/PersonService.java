package es.ieslavereda.proyecto3.service;

import es.ieslavereda.proyecto3.model.Person;
import es.ieslavereda.proyecto3.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class PersonService {
    @Autowired
    PersonRepository repository;

    public Person getPersonById(int id) throws SQLException{
        return repository.getPersonById(id);
    }
    public List<Person> getAllPerson() throws SQLException {
        return repository.getAllPerson();
    }
    public Person deletePersonById(int id) throws SQLException {
        return repository.deletePersonById(id);
    }
    public Person updatePerson(Person person) throws SQLException {
        return repository.updatePerson(person);
    }
    public Person addPerson(Person person) throws SQLException {
        return repository.addPerson(person);
    }
}
