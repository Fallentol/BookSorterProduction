package servlet;

import dataBaseUtils.SQLConnection;
import dataBaseUtils.SQLUtils;
import essence.Book;
import essence.TagsEntity;
import fileUtils.FileController;
import hibernate.HibernateController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tagStore")
public class TagServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if ("saveCard".equals(request.getParameter("action"))) {
            try {
                String name = request.getParameter("name");
                int parent = Integer.valueOf(request.getParameter("parent"));
                TagsEntity te = new TagsEntity(name, parent);
                HibernateController.insertNewTag(te);
                te = null;
            } catch (Exception e) {
                System.out.println("INSERT FAILED CUSTOM MESSAGE " + e);
            }
        }

        if ("deleteTag".equals(request.getParameter("action"))) {
            try {
                String itemClass = request.getParameter("listIndex");
                int tagId = Integer.valueOf(itemClass.replace("item",""));
                HibernateController.deleteTag(tagId);
            } catch (Exception e) {
                System.out.println("DELETE FAILED CUSTOM MESSAGE " + e);
            }
        }


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        String message = "Worked";
        request.setAttribute("message", message);
        request.setAttribute("tagsSort", HibernateController.getAllTags());
        request.getRequestDispatcher("/Tag.jsp").forward(request, response);
    }
}
