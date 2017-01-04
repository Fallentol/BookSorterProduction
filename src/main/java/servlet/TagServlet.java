package servlet;

import dataBaseUtils.SQLConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tagStore")
public class TagServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        super.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        String message = "Worked";
        request.setAttribute("message", message);
        SQLConnection sqlCon = new SQLConnection();
        request.setAttribute("tagsSort", sqlCon.getTags());
        request.getRequestDispatcher("/Tag.jsp").forward(request, response);
    }
}
