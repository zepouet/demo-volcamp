<%@ page import="java.util.*, java.sql.*, com.volcanix.utils.DbHaricotBean" %>
<%
    String nomRecherche = request.getParameter("nomRecherche");

    int currentPage = 1;
    int resultsPerPage = 5;  // Nombre de résultats par page
    if (request.getParameter("page") != null) {
        currentPage = Integer.parseInt(request.getParameter("page"));
    }

    DbHaricotBean dbHaricotBean = new DbHaricotBean();
    List<Map<String, String>> chats = dbHaricotBean.searchCatsByNamePaginated(nomRecherche, currentPage, resultsPerPage);  // Utilisation de currentPage
    int totalChats = dbHaricotBean.getTotalCatsByName(nomRecherche);  // Nombre total de résultats

    int totalPages = (int) Math.ceil(totalChats / (double) resultsPerPage);

    if (chats != null && !chats.isEmpty()) {
%>
        <h2>Résultats de la recherche pour "<%= nomRecherche %>"</h2>
        <table border="1">
            <thead>
                <tr>
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
                        <td><%= chat.get("nom") %></td>
                        <td><%= chat.get("date_naissance") %></td>
                        <td><img src="data:image/jpeg;base64,<%= chat.get("photo") %>" alt="photo" width="100"/></td>
                    </tr>
                <%
                    }
                %>
            </tbody>
        </table>

        <div>
            <%
                for (int i = 1; i <= totalPages; i++) {
            %>
                <a href="recherche.jsp?nomRecherche=<%= nomRecherche %>&page=<%= i %>">Page <%= i %></a>
            <%
                }
            %>
        </div>
<%
    } else {
        out.println("Aucun chat trouvé !");
    }
%>
