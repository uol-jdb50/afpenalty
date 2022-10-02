<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
   
    <head>
        <title>Drivers | AFPenalty</title>
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
        <h1>License Points</h1><br/>
        <a href="/">Home</a><br/>
        <br/>
        <table border="2" width="30%" cellpadding="2">
            <tr><th>Driver Name</th><th>License Points</th><th>View Recent Events</th></tr>
            <c:forEach var="driver" items="${drivers}">
                <tr>
                    <td>${driver.name}</td>
                    <td>${driver.llpts}</td>
                    <td><a href="/viewattendance/${driver.driverID}">View</a></td>
                </tr>
            </c:forEach>
        </table>
        
    </body>
