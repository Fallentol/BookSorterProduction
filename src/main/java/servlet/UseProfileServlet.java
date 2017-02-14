package servlet;

import dataBaseUtils.SQLUtils;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/useProfile")
public class UseProfileServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /// обработка кнопки Use Profile
        if ("useProfileAction".equals(request.getParameter("action"))) {
            String baseName = request.getParameter("baseName");
            String userName = request.getParameter("userName");
            String profPath = request.getParameter("profPath");

            System.out.println("baseName = " + baseName);
            System.out.println("userName = " + userName);
            System.out.println("profPath = " + profPath);

//            try {
//                SQLUtils s = new SQLUtils();
//                s.insertUserProfile(SQLUtils.getUserIdFromName(userName), profPath);
//                return;
//            } catch (Exception e) {
//                e.printStackTrace();
//                System.out.println(e);
//            }
            JSONObject resultJSON = getJSONObjectForProfile(baseName, userName, profPath);
            response.setContentType("text/html;charset=utf-8");
            PrintWriter pw = response.getWriter();
            pw.write(resultJSON.toString());

        }
    }

    public static JSONObject getJSONObjectForProfile(String baseName, String userName, String profPath) {
        JSONObject json = new JSONObject();
        try {
            json.put("baseNameInfoU", baseName);
            json.put("userNameInfoU", userName);
            json.put("profPathInfoU", profPath);
        } catch (Exception e) {
            System.out.println("JSONObject json Exception=" + e);
        }
        System.out.println("getJSONObjectForProfile = " + json);
        return json;
    }
}
