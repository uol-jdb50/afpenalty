<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
   
    <head>
        <title>Leagues | AFPenalty</title>
        <script type="text/javascript">
            function confirmDelete(e) {
                var retVal = confirm("Are you sure you want to delete this?");
                if (retVal == true) {
                    return true;
                } else {
                    e.preventDefault();
                    return false;
                }
            }
        </script>
    </head>
    <body>
        <h1>Leagues</h1><br/>
        <a href="/">Home</a><br/>
        <a href="leagueform">Add New League</a>
        <br/>
        <table border="2" width="30%" cellpadding="2">
            <tr><th>League Name</th><th>View</th><th>Edit</th><th>Delete</th></tr>
            <c:forEach var="league" items="${list}">
                <tr>
                    <td>${league.name}</td>
                    <td><a href="viewevents/${league.seasonID}">View</a></td>
                    <td><a href="editleague/${league.seasonID}">Edit</a></td>
                    <td><a href="deleteleague/${league.seasonID}" onclick="confirmDelete(event);">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
    </body>
