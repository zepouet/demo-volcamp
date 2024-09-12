package com.volcanix;

import com.volcanix.utils.InsertCats;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebServlet(name = "AdminServlet", urlPatterns = {"/admin"})
public class AdminServlet extends HttpServlet {

    @SuppressWarnings("static-access")
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        InsertCats insertCats = new InsertCats();
        insertCats.perform();
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println("<h1>Inserted!</h1>");
    }
}
