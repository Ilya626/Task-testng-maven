package com.ilya;

import com.ilya.model.Person;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MyTask2 extends AbstractDBTest {

    @Test
    public void oldestPersonTest() throws Exception {
        List<Person> persons = TestDataProvider.createRandomPersons(5);

        dbHelper.insertPersons(persons);
        List<Person> personsFromDb = dbHelper.getAllDBPersons();
        Optional<Person> oldestPerson = personsFromDb.stream().max(Person::compareTo);
//        List<Person> oldestPersons = personsFromDb.stream().filter(person -> person.equals(oldestPerson.get())).collect(Collectors.toList());
//        При необходимости поиска всех наибольших.
        if (oldestPerson.isPresent()) {
            System.out.println(oldestPerson.get().getName());
        } else {
            throw new Exception("No person found.");
        }
    }

}

