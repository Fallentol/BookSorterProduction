package servlet;

import fileUtils.FileController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/fileStore")
public class FileServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        String str = request.getParameter("findText");
        System.out.println("S T R =" + str);
        FileController fileController = new FileController();
        request.setAttribute("fileTable", fileController.getFileBooksByName(str));
        request.getRequestDispatcher("/FileStore.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        FileController fileController = new FileController();
        request.setAttribute("fileTable", fileController.getFileBooksByName("test"));
        request.getRequestDispatcher("/FileStore.jsp").forward(request, response);
    }
}