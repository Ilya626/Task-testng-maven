package com.ilya.repository;

import lombok.AllArgsConstructor;
import com.ilya.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class PersonRepository {

    private Connection db;

    public void insertPersons(List<Person> persons) throws SQLException {
        for (Person person : persons) {
            String name = person.getName();
            long uId = person.getUid();
            int age = person.getAge();

            String querySQL = "insert into persons(name, age)" + "values(?,?)";

            PreparedStatement preparedStatement = db.prepareStatement(querySQL,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2, person.getAge());
            preparedStatement.executeUpdate();
        }
    }

     public List<Person> getAllDBPersons() throws SQLException {

        List<Person> result = new ArrayList<>();

        String SQL_SELECT = "Select * from persons";

        PreparedStatement preparedStatement = db.prepareStatement(SQL_SELECT);
        {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                long uId = resultSet.getLong("UID");
                String name = resultSet.getString("NAME");
                int age = resultSet.getInt("AGE");


                Person person = new Person(uId, name, age);


                result.add(person);
            }
        }
        return result;
    }

}
