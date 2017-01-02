package servlet;

import dataBaseUtils.SQLUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import static config.Configurator.*;

/**
 * Created by New on 1/2/2017.
 */
@WebServlet("/AuthorizationServlet")
public class AuthorizationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        userName = request.getParameter("userName");
        userPass = request.getParameter("userPass");
        baseName = request.getParameter("baseName");
        String sqlHost = "jdbc:mysql://localhost/" + baseName + "?user=" + userName + "&password=" + userPass + "&useSSL=true";
        try {
            SQLUtils.sqlConnection = DriverManager.getConnection(sqlHost);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String result = SQLUtils.testConnection();
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.write(result);
    }

}
