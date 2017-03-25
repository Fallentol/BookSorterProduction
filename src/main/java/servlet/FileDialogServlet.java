package servlet;

import config.Configurator;
import dataBaseUtils.SQLUtils;
import essence.Book;
import essence.Link;
import essence.LinksEntity;
import essence.Tag;
import fileUtils.FileController;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
                String tags = request.getParameter("tags");

                int bookId = 0;
                int year;
                try {
                    year = Integer.valueOf(request.getParameter("year"));
                } catch (Exception e) {
                    year = 0;
                }
                String language = request.getParameter("language");
                String format = request.getParameter("format");
                Book book = new Book(0, name, author, language, type, format, path, description, year, 0);
                int cardId = SQLUtils.fileBaseIdMap.get(path) == null ? -1 : SQLUtils.fileBaseIdMap.get(path);
                SQLUtils sqlUtils = new SQLUtils();
                if (cardId == -1) { // книги еще нет в базе
                    bookId = sqlUtils.insertNewBook(book);
                    if (bookId == -1) try {
                        throw new Exception("SQL INSERT ERROR");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    book.setId(bookId);
                } else { // книга уже была в базе
                    book.setId(cardId);
                    sqlUtils.updateBook(book);
                }

                for (String str : tags.split(",")) {
                    Link link = new Link();
                    link.book_id = bookId == 0 ? cardId : bookId;
                    link.tag_id = Integer.valueOf(str);
                    sqlUtils.insertNewLink(link);
                }

                FileController.reserveBook(book);

                return;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String param = request.getParameter("listIndex").replace("item", "");
        int listIndex = Integer.valueOf(param);
        String fileName = FileController.getFileBooksByName(Configurator.findFileName).get(listIndex);

        int cardId = SQLUtils.fileBaseIdMap.get(fileName) == null ? 0 : SQLUtils.fileBaseIdMap.get(fileName);
        JSONObject resultJSON = null;
        if (cardId == 0) {
            resultJSON = getJSONObjectForNewFile(fileName);
        } else {
            resultJSON = getJSONObjectFromBook(cardId);
        }

        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.write(resultJSON.toString());
    }

    private JSONObject getJSONObjectForNewFile(String fileName) {
        String filePath = fileName;
        String fileYear = "2050";
        String fileAuthor = "Author";
        String fileFormat = "Format";

        Pattern p = Pattern.compile("19\\d{2}|20\\d{2}");
        Matcher m = p.matcher(fileName);
        m.find();
        try {
            fileYear = m.group();
        } catch (Exception e) {
            fileYear = "2050";
        }

        p = Pattern.compile("(.jpg|.pdf|.html|.rar|.zip|.txt|.djvu|.doc|.rtf)$"); // ([^\s]+(\.(?i)(jpg|png|gif|bmp|pdf|djvu))$)
        m = p.matcher(fileName);
        m.find();
        try {
            fileFormat = m.group();
        } catch (Exception e) {
            fileFormat = "Format";
        }

        fileName = fileName.replaceAll("(.jpg|.pdf|.html|.rar|.zip|.txt|.djvu|.doc|.rtf)$", "").replaceAll("19\\d{2}|20\\d{2}", "");

        JSONObject json = new JSONObject();
        try {
            json.put("fileName", fileName);
            json.put("fileAuthor", fileAuthor);
            json.put("fileYear", fileYear);
            json.put("fileFormat", fileFormat);
            json.put("filePath", filePath);
            json.put("fileLanguage", "ru");
            json.put("fileType", "book");
        } catch (Exception e) {
            System.out.println("JSONObject json Exception=" + e);
        }

        return json;
    }

    private JSONObject getJSONObjectFromBook(int identity) {
        SQLUtils sqlUtilsObj = new SQLUtils();
        Book book = sqlUtilsObj.getBookFromId(String.valueOf(identity));
        SQLUtils sqlUtils = new SQLUtils();
        ArrayList<Link> links = sqlUtils.getLinkAttachedToBook(String.valueOf(book.getId()));
        String tagNumbers = "";
        for (Link lk : links) {
            if (!tagNumbers.equals("")) tagNumbers += ",";
            tagNumbers += lk.getTag_id();
        }
        System.out.println("tagNumbers=" + tagNumbers);
        JSONObject json = new JSONObject();
        try {
            json.put("fileId", book.getId());
            json.put("filePath", book.getPath());
            json.put("fileName", book.getName());
            json.put("fileAuthor", book.getAuthor());
            json.put("fileYear", book.getYear());
            json.put("fileFormat", book.getFormat());
            json.put("fileLanguage", book.getLanguage());
            json.put("fileDescription", book.getDescription());
            json.put("fileType", book.getType());
            json.put("fileTags", tagNumbers);
        } catch (Exception e) {
            System.out.println("JSONObject json Exception=" + e);
        }
        return json;
    }

}
