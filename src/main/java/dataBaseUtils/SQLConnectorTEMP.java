package dataBaseUtils;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnectorTEMP {
    private String HOST;
    private String USERNAME;
    private String PASSWORD;

    public SQLConnectorTEMP(String HOST, String USERNAME, String PASSWORD) {

        //аргументы для авторизации в БД на сервере mysql
        this.HOST = HOST;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;

        //создаю драйвер и регистрирую его
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //создаю соединение с БД
        try {
            //System.out.println(HOST + " " + USERNAME + " " + PASSWORD);
            connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
            System.out.println(connection.isClosed() + " - Соединение установлено."); //проверка соединения с БД
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
