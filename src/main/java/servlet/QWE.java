package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by New on 12/17/2016.
 */
public class QWE extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        resp.setContentType("text/html;charset=utf-8");

        PrintWriter pw = resp.getWriter();
        pw.println(" <form action='127.0.0.1:8080' method='get'> ");
        pw.println(" <input type='button' value='Test' > ");
        pw.println(" <input type='text' name='hello' > ");
        pw.println(" <input type='button' value='Test' > ");
        pw.println(" <input type='button' value='Test' > ");
        pw.println(" <input type='button' value='Test' > ");
        pw.println(" <input type='button' value='Test' > ");
        pw.println(" </form> ");

    }
}