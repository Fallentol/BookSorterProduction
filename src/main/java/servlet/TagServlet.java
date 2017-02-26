package servlet;

import dataBaseUtils.SQLConnection;
import dataBaseUtils.SQLUtils;
import essence.Book;
import essence.Tag;
import essence.TagsEntity;
import fileUtils.FileController;
import hibernate.HibernateController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

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
                int tagId = Integer.valueOf(itemClass.replace("item", ""));
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

        SQLUtils sqlUtils = new SQLUtils();
        Map<String, String> tagOptionsMap = new LinkedHashMap<>();
        for (Tag t : sqlUtils.getAllTags()) {
            System.out.println(t.getName());
            tagOptionsMap.put(String.valueOf(t.getId()), t.getName());
        }

        request.setAttribute("tags", tagOptionsMap);
        request.setAttribute("tagsSort", HibernateController.getAllTags());
        request.getRequestDispatcher("/Tag.jsp").forward(request, response);
    }
}
