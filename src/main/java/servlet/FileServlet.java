package servlet;

import config.Configurator;
import dataBaseUtils.SQLUtils;
import essence.FileBookLink;
import fileUtils.FileController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/fileStore")
public class FileServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*response.setContentType("text/html");
        Configurator.findFileName = request.getParameter("findText");
        request.setAttribute("fileTable", FileController.getFileBooksByName(Configurator.findFileName));
        request.getRequestDispatcher("/FileStore.jsp").forward(request, response);*/
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        Configurator.findFileName = request.getParameter("findText") == null ? "test" : request.getParameter("findText");
        FileController fileController = new FileController();
        ArrayList<FileBookLink> fileBookLinks = new ArrayList<>();
        for (String filePath : fileController.getFileBooksByName(Configurator.findFileName)) {
            FileBookLink fileBookLink = new FileBookLink();
            fileBookLink.setFileName(filePath);
            int bookId = SQLUtils.fileBaseIdMap.get(filePath) == null ? 0 : SQLUtils.fileBaseIdMap.get(filePath);
            fileBookLink.setBaseId(bookId);
            fileBookLinks.add(fileBookLink);
        }

        request.setAttribute("fileTable", fileBookLinks);
        //request.setAttribute("bookId", "Some Number");
        request.setAttribute("bookTypes", new String[]{"n/a", "article", "book", "magazine", "encyclopedia"});
        request.setAttribute("bookLanguage", new String[]{"n/a", "ru", "en", "ua"});

        request.getRequestDispatcher("/FileStore.jsp").forward(request, response);
    }
}
