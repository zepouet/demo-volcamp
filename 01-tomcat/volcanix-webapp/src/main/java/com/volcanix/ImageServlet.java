package com.volcanix;

import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.volcanix.utils.DbHaricotBean;

@WebServlet("/imageServlet")
public class ImageServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String chatId = request.getParameter("id");

        // Vérifie si l'ID est valide
        if (chatId != null && !chatId.isEmpty()) {
            try {
                int id = Integer.parseInt(chatId);

                DbHaricotBean dbHaricotBean = new DbHaricotBean();
                byte[] imageBytes = dbHaricotBean.getChatImageById(id);  // Appel à la méthode du Bean

                if (imageBytes != null) {
                    // Configuration de la réponse pour afficher une image
                    response.setContentType("image/jpeg");
                    OutputStream outputStream = response.getOutputStream();
                    outputStream.write(imageBytes);
                    outputStream.flush();
                    outputStream.close();
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Image non trouvée pour ce chat.");
                }
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID invalide.");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID manquant.");
        }
    }
}
