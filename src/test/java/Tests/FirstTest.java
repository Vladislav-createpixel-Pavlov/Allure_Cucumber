package Tests;

import BaseTest.BaseTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.*;
import java.util.Objects;

public class FirstTest extends BaseTest {
    Connection connection;
    Statement statement;

    public FirstTest() throws SQLException {
    }
    @Before
    public void SetUp() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:h2:tcp://localhost:9092/mem:testdb",
                "user",
                "pass"
        );
    }
    @After
    public void Exit() throws SQLException{
//        statement.executeUpdate(delete);// завершение теста
//        connection.close();
    }
    @Test
    public void Start() throws SQLException {
        Statement statement = connection.createStatement();
        System.out.printf("Тестовые параметры: %nНазвание:Картофель %nТип:VEGETABLE %nЭкзотический:0 %n");
        ResultSet c = statement.executeQuery(count);
        c.next();
        int v = c.getInt("FOOD_ID");
        statement.executeUpdate(insert);
        ResultSet g = statement.executeQuery(count);
        g.next();
        int f = g.getInt("FOOD_ID");
        if(f > v){
            System.out.println("Добавление прошло успешно");
        }
        ResultSet resultSet = statement.executeQuery(select);
        resultSet.next();
        String s = resultSet.getString("FOOD_NAME");
        if(s.equals("Картофель")){
            System.out.println("Тест пройден");
            statement.executeUpdate(delete);// завершение теста
            connection.close();
        }
    }
}


