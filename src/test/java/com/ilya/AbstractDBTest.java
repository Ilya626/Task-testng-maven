package com.ilya;

import org.testng.annotations.AfterTest;
import com.ilya.repository.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;
import org.testng.annotations.BeforeTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Slf4j
public class AbstractDBTest {

    final static protected String url = "jdbc:postgresql://localhost:54320/postgres";
    final static protected String username = "postgres";
    final static protected String password = "example";

    protected final Flyway flyway = withConfig(url, username, password);

    protected static Connection db;

    protected static PersonRepository dbHelper;

    @BeforeTest
    protected void prepareDb() throws SQLException {
        flyway.setSchemas("public");
        flyway.clean();
        flyway.migrate();

        db = DriverManager.getConnection(url, username, password);
        dbHelper = new PersonRepository(db);

        log.info("DB ready");
    }

    @AfterTest


    private static Flyway withConfig(String url, String user, String password) {
        Flyway flyway = new Flyway();
        flyway.setDataSource(url, user, password);
        return flyway;
    }


}
