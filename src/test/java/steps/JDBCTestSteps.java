package steps;

import io.cucumber.java.bg.И;
import java.sql.*;

public class JDBCTestSteps {
    String count = "SELECT FOOD_ID FROM FOOD ORDER BY FOOD_ID DESC LIMIT 1";

    String insert = "INSERT INTO FOOD(FOOD_NAME,FOOD_TYPE,FOOD_EXOTIC) VALUES (?,?,?)";
    String select = "SELECT * FROM FOOD";
    String delete = "DELETE FROM FOOD WHERE FOOD_ID = (SELECT MAX(FOOD_ID) FROM FOOD)";
    Connection connection;
    DriverManager driverManager;

    @И("Создано соединение с БД")
    public void before() throws SQLException {
        System.out.println("Подключение к БД");// Вывод в консоль для отслеживания действий
        DriverManager.registerDriver(new org.h2.Driver());
        connection = DriverManager.getConnection(
                "jdbc:h2:tcp://localhost:9092/mem:testdb",
                "user",
                "pass"
        );
    }
    @И("Выполнение подсчета количества записей до добавления")
    public int CountBefore() throws SQLException {
        System.out.println("Считаю записи до добавления");
        PreparedStatement stCount = connection.prepareStatement(count);
        ResultSet resultSet = stCount.executeQuery();
        resultSet.next();
        int before_add = resultSet.getInt("FOOD_ID");
        return before_add;
        }
    @И("Добавление товара c параметрами: Наименование: {string}, Тип: {string}, Экзотический: {int}")
    public void AddPossition(String name,String type,int exotic) throws SQLException {
        System.out.printf("Выполняю добавление товара");
        PreparedStatement stAdd = connection.prepareStatement(insert);
        stAdd.setString(1,name);
        stAdd.setString(2,type);
        stAdd.setInt(3,exotic);
        stAdd.executeUpdate();
    }
    @И("Выполнение подсчета количества записей после добавления")
    public int CountAfter() throws SQLException {
        System.out.println("Считаю записи после добавления");
        PreparedStatement stCount = connection.prepareStatement(count);
        ResultSet resultSet = stCount.executeQuery();
        resultSet.next();
        int after_add = resultSet.getInt("FOOD_ID");
        return after_add;
    }
    @И("Проверка добавления записи")
    public void Check() throws SQLException {
        if( CountBefore() > CountAfter()){
            System.out.println("Добавление прошло успешно");
        }
        PreparedStatement stCheck = connection.prepareStatement(select);
        ResultSet resultSet = stCheck.executeQuery();
        resultSet.next();
        String name = resultSet.getString("FOOD_NAME");
        String type = resultSet.getString("FOOD_TYPE");
        Boolean exotic = resultSet.getBoolean("FOOD_EXOTIC");

        if(name.equals("Картофель")){
            if(type.equals("VEGETABLE")){
                if(exotic.equals(false)){
                    System.out.println("Тест пройден");
                }
            }
        }
    }
    @И("Удаление записи")
    public void DeleteAdd() throws SQLException {
        PreparedStatement pstDelete = connection.prepareStatement(delete);
        pstDelete.execute();
    }
    @И("Закрытие содинения")
    public void KillConnect() throws SQLException {
        connection.close();
    }

}
