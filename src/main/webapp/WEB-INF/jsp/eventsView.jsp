<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
   
    <head>
        <title>Events | AFPenalty</title>
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
        <h1>Events</h1><br/>
        <a href="/viewleagues">Back to Leagues</a>
        <table border="2" width="50%" cellpadding="2">
            <tr><th>Event</th><th>Date</th><th>View</th><th>Edit</th><th>Delete</th></tr>
            <c:forEach var="eve" items="${list}">
                <tr>
                    <td>${eve.name}</td>
                    <td>${eve.date}</td>
                    <td><a href="/viewreports/${eve.eventID}">View</a></td>
                    <td><a href="editevent/${eve.eventID}">Edit</a></td>
                    <td><a href="deleteevent/${eve.eventID}" onclick="confirmDelete(event);">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <a href="eventform/${seasonId}">Add New Event</a>
    </body>
