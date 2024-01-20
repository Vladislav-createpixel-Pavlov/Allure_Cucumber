package steps;

import io.cucumber.java.PendingException;
import io.cucumber.java.be.I;
import io.cucumber.java.ru.*;

import java.sql.*;
import java.util.concurrent.TimeUnit;

public class Steps {
    protected String count = "SELECT FOOD_ID FROM FOOD ORDER BY FOOD_ID DESC LIMIT 1";
    protected String insert = "INSERT INTO FOOD(FOOD_NAME,FOOD_TYPE,FOOD_EXOTIC) VALUES ('Картофель','VEGETABLE',0)";
    protected String select = "SELECT * FROM FOOD";
    protected String delete = "DELETE FROM FOOD WHERE FOOD_ID = (SELECT MAX(FOOD_ID) FROM FOOD)";
    Connection connection;
    Statement statement;
    @И("выполнено подключение к БД")
    public void выполнено_подключение_к_БД() throws SQLException {
        System.out.println("Подключение к БД");
        connection = DriverManager.getConnection(
                "jdbc:h2:tcp://localhost:9092/mem:testdb",
                "user",
                "pass"
        );


    }
    @И("выполнено подсчёт количства записей")
    public void выполнено_подсчёт_количства_записей() throws SQLException {
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
         }

    }
}
//    @И("выполнено добавление новой записи")
//    public void выполнено_добавление_новой_записи() throws SQLException {
//        Statement statement = connection.createStatement();
//        statement.executeUpdate(insert);
//    }
//    @И("проверка добавления новой записи")
//    public void проверка_добавления_новой_записи() throws SQLException {
//        Statement statement = connection.createStatement();
//        ResultSet g = statement.executeQuery(count);
//        g.next();
//        int f = g.getInt("FOOD_ID");
//        if(f > выполнено_подсчёт_количства_записей()){
//            System.out.println("Добавление прошло успешно");
//        }
//        ResultSet resultSet = statement.executeQuery(select);
//        resultSet.next();
//        String s = resultSet.getString("FOOD_NAME");
//        if(s.equals("Картофель")){
//            System.out.println("Тест пройден");
//    }
//

