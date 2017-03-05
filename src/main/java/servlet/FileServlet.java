package servlet;

import config.Configurator;
import dataBaseUtils.SQLUtils;
import essence.FileBookLink;
import essence.Tag;
import fileUtils.FileController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@WebServlet("/fileStore")
public class FileServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if ("openFile".equals(request.getParameter("action"))) {
            try {
                String param = request.getParameter("listIndex").replace("item", "");
                int listIndex = Integer.valueOf(param);
                String fileName = FileController.getFileBooksByName(Configurator.findFileName).get(listIndex);
                FileController.openFile(fileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if ("deleteFile".equals(request.getParameter("action"))) {
            try {
                String param = request.getParameter("listIndex").replace("item", "");
                int listIndex = Integer.valueOf(param);
                String fileName = FileController.getFileBooksByName(Configurator.findFileName).get(listIndex);
                FileController.deleteFile(fileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        String findName = null;
        try {
            findName = new String(request.getParameter("findText").getBytes("ISO-8859-1"), "UTF-8");
        } catch (Exception e) {
            findName = "test";
        }
        Configurator.findFileName = findName;
        FileController fileController = new FileController();
        SQLUtils.initFileBaseIdMap();
        ArrayList<FileBookLink> fileBookLinks = new ArrayList<>();
        for (String filePath : fileController.getFileBooksByName(Configurator.findFileName)) {
            FileBookLink fileBookLink = new FileBookLink();
            fileBookLink.setFileName(filePath);
            int bookId = SQLUtils.fileBaseIdMap.get(filePath) == null ? 0 : SQLUtils.fileBaseIdMap.get(filePath);
            fileBookLink.setBaseId(bookId);
            fileBookLinks.add(fileBookLink);
        }

        SQLUtils sqlUtils = new SQLUtils();
        Map<String, String> tagOptionsMap = new TreeMap<>();
        for (Tag t : sqlUtils.getAllTags()) {
            tagOptionsMap.put(String.valueOf(t.getId()), t.getName());
        }

        request.setAttribute("tags", tagOptionsMap);
        request.setAttribute("totalFiles", FileController.filesQuantity);
        request.setAttribute("totalBooks", SQLUtils.bookQuantity);
        request.setAttribute("fileTable", fileBookLinks);
        request.setAttribute("bookTypes", new String[]{"n/a", "article", "book", "magazine", "encyclopedia"});
        request.setAttribute("bookLanguage", new String[]{"n/a", "ru", "en", "ua"});

        request.getRequestDispatcher("/FileStore.jsp").forward(request, response);
    }
}
