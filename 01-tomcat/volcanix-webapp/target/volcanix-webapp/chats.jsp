<%@ page import="java.util.*, java.sql.*, com.volcanix.utils.DbHaricotBean" %>
<link rel="stylesheet" type="text/css" href="css/style.css"> <!-- Lien vers le fichier CSS -->
<%
    int currentPage = 1;
    int resultsPerPage = 5;  // Nombre de chats par page
    if (request.getParameter("page") != null) {
        currentPage = Integer.parseInt(request.getParameter("page"));
    }

    DbHaricotBean dbHaricotBean = new DbHaricotBean();
    List<Map<String, String>> chats = dbHaricotBean.getAllCatsPaginated(currentPage, resultsPerPage);  // Utilisation de currentPage
    int totalChats = dbHaricotBean.getTotalCats();
    int totalPages = (int) Math.ceil(totalChats / (double) resultsPerPage);
%>

<html>
<head>
    <title>Liste des Chats</title>
</head>
<body>
    <h1>Liste des Chats</h1>

    <!-- Formulaire de recherche -->
    <form class="well form-search" action="recherche.jsp" method="get">
        <label for="nomRecherche">Rechercher un chat par nom :</label>
        <input class="input-medium search-query" type="text" name="nomRecherche" id="nomRecherche" />
        <input class="btn" type="submit" value="Rechercher" />
    </form>

    <br/>

    <% if (chats != null && !chats.isEmpty()) { %>
        <table border="1" class="table table-bordered table-striped table-hover">
            <thead>
                <tr>
                    <th>id</th>
                    <th>Nom</th>
                    <th>Date de Naissance</th>
                    <th>Photo</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (Map<String, String> chat : chats) {
                %>
                    <tr>
                        <td><%= chat.get("id") %></td>
                        <td><%= chat.get("nom") %></td>
                        <td><%= chat.get("date_naissance") %></td>
                        <td><img src="imageServlet?filename=<%= chat.get("id") %>" alt="photo" width="100"/></td>
                    </tr>
                <%
                    }
                %>
            </tbody>
        </table>

        <!-- Pagination -->
        <div>
            <%
                for (int i = 1; i <= totalPages; i++) {
            %>
                <a href="chats.jsp?page=<%= i %>">Page <%= i %></a>
            <%
                }
            %>
        </div>
    <% } else { %>
        <p>Aucun chat trouvé !</p>
    <% } %>

</body>
</html>
