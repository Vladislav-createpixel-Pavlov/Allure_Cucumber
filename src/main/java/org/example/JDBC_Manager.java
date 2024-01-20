package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC_Manager {
    private static Connection connection;
    static {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:h2:tcp://localhost:9092/mem:testdb",
                    "user",
                    "user"
            );
            System.out.println("Подключение к СУБД выполнено успешно");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Statement getStatement() throws SQLException{
        return connection.createStatement();
    }
}
