package com.ilya;

import lombok.extern.slf4j.Slf4j;
import com.ilya.model.Person;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.List;

@Slf4j
@Test
public class MyTask1 extends AbstractDBTest {

    @Test
    public void personInsertTest() throws SQLException {
        List<Person> persons = TestDataProvider.createRandomPersons(5);

        dbHelper.insertPersons(persons);
        List<Person> personsFromDb = dbHelper.getAllDBPersons();

        Assert.assertEquals(persons.size(), personsFromDb.size());
    }

}

