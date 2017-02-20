package servlet;

import tests.TEMP;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@WebServlet("/s")
public class StartPageServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        super.doPost(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        /*String message = "Worked";
        request.setAttribute("message", message);
        SQLConnection sqlCon = new SQLConnection();
        request.setAttribute("booksSort", sqlCon.getBooks());*/
        TEMP t = new TEMP();
        request.setAttribute("s_baseName", t.getBaseList());
        request.setAttribute("s_userName", t.getUserList());
        request.getRequestDispatcher("/BookSorterPro.jsp").forward(request, response);
    }

}
