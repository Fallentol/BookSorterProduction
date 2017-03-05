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
    private static String bookname = null;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if ("searchBook".equals(request.getParameter("action"))) {
            try {
                SQLUtils sqlUtils = new SQLUtils();
                Set<Integer> tagIds = new TreeSet<>();

                String name = request.getParameter("name");
                String tags = request.getParameter("tags");
                boolean needToIncludeChildren = Boolean.valueOf(request.getParameter("include"));
                System.out.println("name=" + name + " tags=" + tags + " bool=" + needToIncludeChildren);

                if (name.equals("none") && tags.equals("none")) {
                    bookname = null;
                    tagChildrenIds = new TreeSet<>();
                    return;
                }

                if (name.equals("none")) {
                    bookname = null;
                } else {
                    bookname = name;
                }
                if (tags.equals("none")) {
                    tagChildrenIds = new TreeSet<>();
                } else {
                    for (String s : tags.split(",")) {
                        tagIds.add(Integer.valueOf(s));
                    }
                    tagChildrenIds = tagIds;
                }

                if (needToIncludeChildren && tagChildrenIds.size() > 0) {
                    int setsize = 0;
                    while (true) {
                        tagChildrenIds = sqlUtils.getTagChildren(tagChildrenIds);
                        if (setsize == tagChildrenIds.size()) break;
                        setsize = tagChildrenIds.size();
                    }
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
        ArrayList<Book> books = new ArrayList<>();
        if (bookname != null) {
            books = sqlUtils.getBooksFromName(bookname);
            System.out.println("На выходе bookName=" + bookname);
            System.out.println("bookname book.size=" + books.size());
        } else {
            books = sqlUtils.getBooksFromTagIds(tagChildrenIds);
            System.out.println("bookTag book.size=" + books.size());
        }

        request.setAttribute("tags", tagOptionsMap);
        request.setAttribute("books", books);
        request.setAttribute("tagsSort", HibernateController.getAllTags());
        request.getRequestDispatcher("/SearchPage.jsp").forward(request, response);
    }

}
