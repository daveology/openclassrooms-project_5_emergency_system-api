package emergencysystem.service;

import emergencysystem.dao.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonDeletionService {

    @Autowired
    private PersonRepository personRepository;

    public void deletePersonById(Long id) {

        personRepository.deleteById(id);;
    }
}
