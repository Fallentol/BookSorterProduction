package servlet;

import essence.TagsEntity;
import hibernate.HibernateController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/searchPage")
public class SearchServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if ("searchValue".equals(request.getParameter("action"))) {
            try {

            } catch (Exception e) {
                System.out.println("Search FAILED." + e);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        request.setAttribute("tagsSort", HibernateController.getAllTags());
        request.getRequestDispatcher("/SearchPage.jsp").forward(request, response);
    }

}
