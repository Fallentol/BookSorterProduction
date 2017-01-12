package servlet;

import config.Configurator;
import fileUtils.FileController;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/tagDialog")
public class TagDialogServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /// обработка кнопки SAVE
        if ("saveCard".equals(request.getParameter("action"))) {
            try {

                return;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String param = request.getParameter("listIndex").replace("item", "");
        int listIndex = Integer.valueOf(param);
        String fileName = FileController.getFileBooksByName(Configurator.findFileName).get(listIndex);

        /*int cardId = SQLUtils.fileBaseIdMap.get(fileName) == null ? 0 : SQLUtils.fileBaseIdMap.get(fileName);
        JSONObject resultJSON = null;
        if (cardId == 0) {
            resultJSON = getJSONObjectForNewFile(fileName);
        } else {
            resultJSON = getJSONObjectFromBook(cardId);
        }*/

        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.write("temp");
    }

    private JSONObject getJSONObjectForNewFile(String fileName) {
        return null;
    }

    private JSONObject getJSONObjectFromBook(int identity) {
        return null;
    }

}

