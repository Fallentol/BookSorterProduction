package servlet;

import config.Configurator;
import dataBaseUtils.SQLUtils;
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

@WebServlet("/tagDialog")
public class TagDialogServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("action=" + request.getParameter("action"));
        if ("getTagInfo".equals(request.getParameter("action"))) {
            String param = request.getParameter("listIndex").replace("item", "");
            SQLUtils sq = new SQLUtils();
            Tag tag = sq.getTagFromId(param);
            JSONObject resultJSON = getJSONObjectForTag(tag);
            response.setContentType("text/html;charset=utf-8");
            PrintWriter pw = response.getWriter();
            pw.write(resultJSON.toString());
        }
    }

    private JSONObject getJSONObjectForTag(Tag tag) {
        JSONObject json = new JSONObject();
        try {
            json.put("tagId", tag.getId());
            json.put("tagName", tag.getName());
            json.put("tagParent", tag.getParent());
        } catch (Exception e) {
            System.out.println("JSONObject json Exception=" + e);
        }
        return json;
    }

}

