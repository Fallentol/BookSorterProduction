package dataBaseUtils;

import java.sql.*;

public class SQLConnection {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver"); // в загрузчик попадает класс из драйвера. Драйвер скачивается и устанавливается бибиотекой к проекту

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/students?user=root&password=214926341qQ!&useSSL=true");

            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM students");

            while (rs.next()) {
                String str = rs.getString(1) + ":" + rs.getString(2);
                System.out.println(str);
            }

            rs = stmt.executeQuery("SHOW FIELDS FROM students");
            while (rs.next()) {
                String str = rs.getString(1);
                System.out.println(str);
            }

            rs.last(); // перемотка индекса в конец очереди
            int size = rs.getRow(); // узнаем какой иднекс
            rs.beforeFirst(); // возвращаем в начало очереди
            System.out.println("Всего записей = " + size );

            rs = stmt.executeQuery("SELECT COUNT(*) FROM students");
            System.out.println(rs.getString(1));

            // Do something with the Connection


        } catch (SQLException ex) {
            // handle any errors
            System.out.println(ex);
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

    }
}