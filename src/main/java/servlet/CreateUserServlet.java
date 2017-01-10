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

import static config.Configurator.*;

@WebServlet("/CreateUserServlet")
public class CreateUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        userName = request.getParameter("userName");
        userPass = request.getParameter("userPass");
        baseName = request.getParameter("baseName");
        filePath = request.getParameter("filePath");

        String result = SQLUtils.createProfile(userName, userPass, filePath);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.write(result);
    }
}
