package servlet;

import config.Configurator;
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
import java.util.ArrayList;

import static config.Configurator.*;

@WebServlet("/profileDialog")
public class ProfileDialogServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /// обработка кнопки Check Info
        if ("checkInfoAction".equals(request.getParameter("action"))) {
            userName = request.getParameter("userName");
            userPass = request.getParameter("userPass");
            baseName = request.getParameter("baseName");
            String sqlHost = "jdbc:mysql://" + Configurator.serverURL + "/" + baseName + "?user=" + userName + "&password=" + userPass + "&useSSL=true";
            try {
                SQLUtils.sqlConnection = DriverManager.getConnection(sqlHost);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String result = SQLUtils.testConnection();
            response.setContentType("text/html;charset=utf-8");
            PrintWriter pw = response.getWriter();
            pw.write(result);

            do {
                ArrayList<String> listProfile = SQLUtils.collectorUserPath(userName);
                String[] resultListPath = (String[]) listProfile.toArray();

                request.setAttribute("s_profPath", resultListPath);
            } while (result == "All parameters are correct");
        }

        /// обработка кнопки Use Profile
        if ("useProfileAction".equals(request.getParameter("action"))) {
            userName = request.getParameter("userName");
            userPass = request.getParameter("userPass");
            baseName = request.getParameter("baseName");
            profPath = request.getParameter("userPath");
            String sqlHost = "jdbc:mysql://" + Configurator.serverURL + "/" + baseName + "?user=" + userName + "&password=" + userPass + "&useSSL=true";
            try {
                SQLUtils.sqlConnection = DriverManager.getConnection(sqlHost);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            String result = SQLUtils.testConnection();
            response.setContentType("text/html;charset=utf-8");
            PrintWriter pw = response.getWriter();
            pw.write(result);

            do {
                //добавляю в базу новую рабочую папку для ПРОВЕРЕННОГО пользователя
                SQLUtils s = new SQLUtils();
                s.insertUserProfile(SQLUtils.getUserIdFromName(userName), profPath);

                //использую введенные данные РАБОЧЕЙ ПАПКИ и ПОЛЬЗОВАТЕЛЯ для работы

            } while (result == "All parameters are correct");


        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        request.getRequestDispatcher("/BookSorterPro.jsp").forward(request, response);
    }
}









