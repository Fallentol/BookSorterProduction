package servlet;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import config.Configurator;
import dataBaseUtils.SQLUtils;
import essence.Tag;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

import static config.Configurator.*;

@WebServlet("/profileDialog")
public class ProfileDialogServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /// обработка кнопки Check Info
        try {
            if ("checkInfoAction".equals(request.getParameter("action"))) {
                ArrayList<String> listProfile = SQLUtils.collectorUserPath(userName);
//                Object[] resultListPath = listProfile.toArray();

                System.out.println("Массив построил");
                System.out.println(listProfile);

                JSONObject resultJSON = getJSONObjectForProfPath(listProfile);
                response.setContentType("text/html;charset=utf-8");
                PrintWriter pwList = response.getWriter();
                pwList.write(resultJSON.toString());
                request.setAttribute("s_profPath", listProfile);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }


        /// обработка кнопки Use Profile
        try {
            if ("useProfileAction".equals(request.getParameter("action"))) {
                userName = request.getParameter("userName");
                profPath = request.getParameter("userPath");

                SQLUtils s = new SQLUtils();
                s.insertUserProfile(SQLUtils.getUserIdFromName(userName), profPath);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

    private JSONObject getJSONObjectForProfPath(ArrayList<String> listProfile) {
        JSONObject json = new JSONObject();
        try {
            int counter = 0;
            for (String str : listProfile) {
                String string = "Item " + ++counter;
                json.put(string, str);
            }
        } catch (Exception e) {
            System.out.println("JSONObject json Exception=" + e);
        }
        return json;
    }
}






