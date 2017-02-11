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
//        userName = request.getParameter("userName");
//        userPass = request.getParameter("userPass");
//        baseName = request.getParameter("baseName");
//
//        String sqlHost = "jdbc:mysql://" + Configurator.serverURL + "/" + baseName + "?user=" + userName + "&password=" + userPass + "&useSSL=true";
//        try {
//            SQLUtils.sqlConnection = DriverManager.getConnection(sqlHost);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        String result = SQLUtils.testConnection();
//        response.setContentType("text/html;charset=utf-8");
//        PrintWriter pw = response.getWriter();
//        pw.write(result);
//
//
//
//        ArrayList<String> listProfile = SQLUtils.collectorUserPath(userName);
//        String[] result1 = (String[]) listProfile.toArray();
//
//        request.setAttribute("s_baseName", new String[]{"BookSotrerPro"});
//        request.setAttribute("s_userName", new String[]{"admin"});
//        request.setAttribute("s_profPath", result1);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        request.setAttribute("s_baseName", new String[]{"BookSotrerPro"});
        request.setAttribute("s_userName", new String[]{"admin"});
    }
}







