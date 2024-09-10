package com.volcanix;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/imageServlet")
public class ImageServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // Spécifie le chemin vers le répertoire des images sur le système de fichiers
    private static final String IMAGE_DIRECTORY = "images/";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupère le nom du fichier image à partir du paramètre "filename"
        String filename = request.getParameter("filename");

        // Vérifie si le paramètre "filename" est présent
        if (filename != null && !filename.isEmpty()) {
            // Crée un objet File représentant l'image
            File imageFile = new File(IMAGE_DIRECTORY + filename);

            // Vérifie si l'image existe
            if (imageFile.exists() && !imageFile.isDirectory()) {
                // Définit le type de contenu de la réponse en fonction de l'extension de l'image
                String contentType = getServletContext().getMimeType(imageFile.getName());
                if (contentType == null) {
                    contentType = "application/octet-stream";
                }
                response.setContentType(contentType);

                // Envoie l'image au client
                try (FileInputStream fileInputStream = new FileInputStream(imageFile);
                     OutputStream outputStream = response.getOutputStream()) {
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }
            } else {
                // Si l'image n'existe pas, renvoie une erreur 404
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Image non trouvée.");
            }
        } else {
            // Si le paramètre "filename" est manquant, renvoie une erreur 400 (Bad Request)
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Paramètre 'filename' manquant.");
        }
    }
}
