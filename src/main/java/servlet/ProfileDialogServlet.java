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

@WebServlet("/profileDialog")
public class ProfileDialogServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /// обработка SELECT
        if ("saveCard".equals(request.getParameter("action"))) {
            try {
                String name = request.getParameter("userName");

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
            resultJSON = getJSONObjectForUserName(fileName);
        } else {
            resultJSON = null;
        }

        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.write(resultJSON.toString());
    }
    private JSONObject getJSONObjectForUserName(String userName) {
        String userN = userName;
        JSONObject json = new JSONObject();

        /*try {
            json.put("user_id", );
        } catch (Exception e) {
            System.out.println("JSONObject json Exception=" + e);
        }*/


        return json;
    }

    private JSONObject getJSONObjectForUserProfile(int identity) {
        SQLUtils sqlUtilsObj = new SQLUtils();
        Book book = sqlUtilsObj.getBookFromId(String.valueOf(identity));
        System.out.println("Book=" + book);
        JSONObject json = new JSONObject();
        try {
            json.put("fileId", book.getId());
            json.put("filePath", book.getPath());
        } catch (Exception e) {
            System.out.println("JSONObject json Exception=" + e);
        }
        return json;
    }
}