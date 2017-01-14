package servlet;

import config.Configurator;
import dataBaseUtils.SQLUtils;
import essence.Book;
import org.json.JSONObject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import static config.Configurator.*;

@WebServlet("/profileDialog")
public class ProfileDialogServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /// обработка SELECT
        userName = request.getParameter("userName");
        userPass = request.getParameter("userPass");
        baseName = request.getParameter("baseName");
        String sqlHost = "jdbc:mysql://" + Configurator.serverURL + "/" + baseName + "?user=" + userName + "&password=" + userPass + "&useSSL=true";
        try {
            SQLUtils.sqlConnection = DriverManager.getConnection(sqlHost);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //подключившись высыпаю перечень профилей данного пользователя
        ArrayList result = SQLUtils.collectorUserProfile(userName);
        /*response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.write(result);*/
    }
}