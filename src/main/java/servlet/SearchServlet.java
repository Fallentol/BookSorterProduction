package servlet;

import dataBaseUtils.SQLUtils;
import essence.Book;
import essence.Tag;
import essence.TagsEntity;
import hibernate.HibernateController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet("/searchPage")
public class SearchServlet extends HttpServlet {

    private static Set<Integer> tagChildrenIds = new TreeSet<>();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /*if ("searchValue".equals(request.getParameter("action"))) {
            try {

            } catch (Exception e) {
                System.out.println("Search FAILED." + e);
            }
        }*/

        if ("searchBook".equals(request.getParameter("action"))) {
            try {
                String name = request.getParameter("name");
                String tags = request.getParameter("tags");
                System.out.println("name=" + name + " tags=" + tags);
                Set<Integer> tagIds = new TreeSet<>();
                for (String s : tags.split(",")) {
                    tagIds.add(Integer.valueOf(s));
                }
                SQLUtils sqlUtils = new SQLUtils();
                tagChildrenIds = tagIds;
                int setsize = 0;
                while (true) {
                    tagChildrenIds = sqlUtils.getTagChildren(tagChildrenIds);
                    if (setsize == tagChildrenIds.size()) break;
                    setsize = tagChildrenIds.size();
                }
            } catch (Exception e) {
                System.out.println("Search FAILED." + e);
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
            tagOptionsMap.put(String.valueOf(t.getId()), t.getName());
        }
        ArrayList<Book> books = sqlUtils.getBooksFromTagIds(tagChildrenIds);

        request.setAttribute("tags", tagOptionsMap);
        request.setAttribute("books", books);
        request.setAttribute("tagsSort", HibernateController.getAllTags());
        request.getRequestDispatcher("/SearchPage.jsp").forward(request, response);
    }

}
