package servlet;

import config.Configurator;
import dataBaseUtils.SQLUtils;
import essence.Book;
import fileUtils.FileController;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/fileDialog")
public class FileDialogServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /// обработка кнопки SAVE
        if ("saveCard".equals(request.getParameter("action"))) {
            try {
                String name = request.getParameter("name");
                String path = request.getParameter("path");
                String type = request.getParameter("type");
                String description = request.getParameter("description");
                String author = request.getParameter("author");
                int year;
                try {
                    year = Integer.valueOf(request.getParameter("year"));
                } catch (Exception e) {
                    year = 0;
                }
                String language = request.getParameter("language");
                String format = request.getParameter("format");
                Book book = new Book(0, name, author, language, type, format, path, description, year, 0);
                SQLUtils sqlUtils = new SQLUtils();
                int bookId = sqlUtils.insertNewBook(book);
                if (bookId == -1) try {
                    throw new Exception("SQL INSERT ERROR");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                book.setId(bookId);
                FileController.saveFileWithIdentity(book);
                FileController.reserveBook(book);

                response.setContentType("text/html;charset=utf-8");
                PrintWriter pw = response.getWriter();
                pw.write("Success saved");
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String param = request.getParameter("listIndex").replace("item", "");
        int listIndex = Integer.valueOf(param);
        String fileName = FileController.getFileBooksByName(Configurator.findFileName).get(listIndex);
        String filePath = fileName;
        String fileYear = "n/a";
        String fileAuthor = "n/a";
        String fileFormat = "n/a";

        Pattern p = Pattern.compile("19\\d{2}|20\\d{2}");
        Matcher m = p.matcher(fileName);
        m.find();
        try {
            fileYear = m.group();
        } catch (Exception e) {
            fileYear = "n/a";
        }

        p = Pattern.compile("(.jpg|.pdf|.html|.rar|.zip|.txt|.djvu|.doc|.rtf)$"); // ([^\s]+(\.(?i)(jpg|png|gif|bmp|pdf|djvu))$)
        m = p.matcher(fileName);
        m.find();
        try {
            fileFormat = m.group();
        } catch (Exception e) {
            fileFormat = "n/a";
        }

        fileName = fileName.replaceAll("(.jpg|.pdf|.html|.rar|.zip|.txt|.djvu|.doc|.rtf)$", "").replaceAll("19\\d{2}|20\\d{2}", "");

        JSONObject json = new JSONObject();
        try {
            json.put("fileName", fileName);
            json.put("fileAuthor", fileAuthor);
            json.put("fileYear", fileYear);
            json.put("fileFormat", fileFormat);
            json.put("filePath", filePath);
        } catch (Exception e) {
            System.out.println("JSONObject json Exception=" + e);
        }
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        System.out.println("json.toString()=" + json.toString());
        pw.write(json.toString());
    }

}
