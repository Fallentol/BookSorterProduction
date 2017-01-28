package servlet;

import config.Configurator;
import dataBaseUtils.SQLUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        if ("checkInfo".equals(request.getParameter("action"))) {
            try {
                userName = request.getParameter("userName");
                userPass = request.getParameter("userPass");
                baseName = request.getParameter("baseName");
                String sqlHost = "jdbc:mysql://" + Configurator.serverURL + "/" + baseName + "?user=" + userName + "&password=" + userPass + "&useSSL=true";
                try {
                    SQLUtils.sqlConnection = DriverManager.getConnection(sqlHost);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            } catch (Exception e) {
                e.printStackTrace();
                return;
            }

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ArrayList<String> listProfile = SQLUtils.collectorUserPath(userName);
        String[] result = (String[]) listProfile.toArray();

        request.setAttribute("baseName", new String[]{"BookSotrerPro"});
        request.setAttribute("userName", new String[]{"admin"});
        request.setAttribute("profPath", result);

        request.getRequestDispatcher("/BookSorterPro.jsp").forward(request, response);
    }
}
