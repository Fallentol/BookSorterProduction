package servlet;

import config.Configurator;
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
        Configurator.findFileName = request.getParameter("findText");
        request.setAttribute("fileTable", FileController.getFileBooksByName(Configurator.findFileName));
        request.getRequestDispatcher("/FileStore.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        FileController fileController = new FileController();
        request.setAttribute("fileTable", fileController.getFileBooksByName(Configurator.findFileName));
        request.getRequestDispatcher("/FileStore.jsp").forward(request, response);
    }
}
