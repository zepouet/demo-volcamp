package com.volcanix.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class InsertCats {
    public static void main(String[] args) {
        perform();
    }

    public static void perform() {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://postgres:5432/volcanix", "admin", "changeit");

            String query = "INSERT INTO chats (nom, date_naissance, photo) VALUES (?, ?, ?)";

            ps = conn.prepareStatement(query);

            // Exemple d'insertion de chat
            ps.setString(1, "Minou");
            ps.setDate(2, java.sql.Date.valueOf("2019-04-12"));
            ps.setString(3, "dGVzdA==");  // "test" encodé en base64 pour une photo

            ps.executeUpdate();

            System.out.println("Chat inséré avec succès !");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

