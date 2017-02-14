package servlet;

import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/userInfoCleaner")
public class UserInfoCleanerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /// обработка кнопки Log out
        if ("clearUserInfoAction".equals(request.getParameter("action"))) {
            String userName = "none";
            String baseName = "none";
            String profPath = "none";

            JSONObject resultJSON = getJSONObjectForCleaner(baseName, userName, profPath);
            response.setContentType("text/html;charset=utf-8");
            PrintWriter pw = response.getWriter();
            pw.write(resultJSON.toString());
        }
    }

    private JSONObject getJSONObjectForCleaner(String baseName, String userName, String profPath) {
        JSONObject json = new JSONObject();
        try {
            json.put("baseNameInfoC", baseName);
            json.put("userNameInfoC", userName);
            json.put("profPathInfoC", profPath);
        } catch (Exception e) {
            System.out.println("JSONObject json Exception=" + e);
        }
        return json;
    }
}