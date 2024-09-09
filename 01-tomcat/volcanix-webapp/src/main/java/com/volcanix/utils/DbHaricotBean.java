package com.volcanix.utils;

import java.sql.*;
import java.util.*;

public class DbHaricotBean {

    // Méthode pour récupérer tous les chats avec pagination
    public List<Map<String, String>> getAllCatsPaginated(int page, int resultsPerPage) {
        List<Map<String, String>> chats = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int offset = (page - 1) * resultsPerPage;

        try {
            conn = getConnection();
            String query = "SELECT id, nom, date_naissance, photo FROM chats LIMIT ? OFFSET ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, resultsPerPage);
            ps.setInt(2, offset);
            rs = ps.executeQuery();

            while (rs.next()) {
                Map<String, String> chat = new HashMap<>();
                chat.put("id", rs.getString("id"));
                chat.put("nom", rs.getString("nom"));
                chat.put("date_naissance", rs.getString("date_naissance"));
                chat.put("photo", rs.getString("photo"));
                chats.add(chat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return chats;
    }

    // Méthode pour compter le nombre total de chats
    public int getTotalCats() {
        int total = 0;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT COUNT(*) AS total FROM chats");

            if (rs.next()) {
                total = rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return total;
    }

    // Méthode pour rechercher des chats par nom avec pagination
    public List<Map<String, String>> searchCatsByNamePaginated(String nomRecherche, int page, int resultsPerPage) {
        List<Map<String, String>> chats = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int offset = (page - 1) * resultsPerPage;

        try {
            conn = getConnection();
            String query = "SELECT id, nom, date_naissance, photo FROM chats WHERE nom LIKE ? LIMIT ? OFFSET ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + nomRecherche + "%");
            ps.setInt(2, resultsPerPage);
            ps.setInt(3, offset);
            rs = ps.executeQuery();

            while (rs.next()) {
                Map<String, String> chat = new HashMap<>();
                chat.put("nom", rs.getString("nom"));
                chat.put("date_naissance", rs.getString("date_naissance"));
                chat.put("photo", rs.getString("photo"));
                chats.add(chat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return chats;
    }

    // Méthode pour compter le nombre de résultats pour une recherche
    public int getTotalCatsByName(String nomRecherche) {
        int total = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            String query = "SELECT COUNT(*) AS total FROM chats WHERE nom LIKE ?";
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + nomRecherche + "%");
            rs = ps.executeQuery();

            if (rs.next()) {
                total = rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return total;
    }

    // Méthode de connexion
    private Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://postgres:5432/volcanix", "admin", "changeit");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    // Méthode pour récupérer l'image d'un chat sous forme de tableau de bytes
    public byte[] getChatImageById(int chatId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        byte[] imageBytes = null;

        try {
            conn = getConnection();
            String query = "SELECT photo FROM chats WHERE id = ?";
            ps = conn.prepareStatement(query);
            ps.setInt(1, chatId);
            rs = ps.executeQuery();

            if (rs.next()) {
                imageBytes = rs.getBytes("photo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return imageBytes;
    }
}
