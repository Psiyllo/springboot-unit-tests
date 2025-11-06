package io.github.Psiyllo.locadora;

import org.junit.jupiter.api.*;
import org.assertj.core.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseTest {

    static Connection connection;

    @BeforeAll
    static void setUpDatabase() throws Exception {
        connection = DriverManager.getConnection("jdbc:h2:mem:testdb", "user", "");
        connection.createStatement().execute("CREATE TABLE users (id INT, name VARCHAR)");
    }

    @BeforeEach
    void insertUserTest() throws Exception{
        connection.createStatement().execute("insert into users(id, name) values (1, 'paulo')");
    }

    @Test
    //@Disabled = Quando quiser desabilitar um test de uma bateria de test só anotar com @Disable
    void testUserExist() throws Exception{
        var result = connection
                .createStatement()
                .executeQuery("SELECT * from users where id = 1");

        Assertions.assertTrue(result.next());
    }

    @AfterAll
    static void closeDatabase() throws Exception{
        connection.close();
    }
}
