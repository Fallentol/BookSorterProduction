package servlet;

import fileUtils.FileController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/fileUtility")
public class FileUtilityServlet extends HttpServlet  {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        /// обработка кнопки DELETE OLD IDENTITY
        if ("deleteOldIdentity".equals(request.getParameter("action"))) {
            try {
                FileController.deleteOldFileIdentity();
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }

        /// обработка кнопки Abort All Threads
        if ("abortAllThreads".equals(request.getParameter("action"))) {
            try {
                FileController.abortDeletingOldFileIdentity();
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        request.getRequestDispatcher("/FileUtils.jsp").forward(request, response);
    }

}
