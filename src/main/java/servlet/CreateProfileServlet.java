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

@WebServlet("/CreateProfileServlet")
public class CreateProfileServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        userName = request.getParameter("userName");
        userPass = request.getParameter("userPass");
        baseName = request.getParameter("baseName");
        filePath = request.getParameter("filePath");

        SQLUtils s = new SQLUtils();
        String result = s.createUserAP(userName, userPass);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.write(result);
    }
}
