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
        <h1>Drivers</h1><br/><br/>
        <a href="/">Home</a><br/>
        <a href="driverform">Add New Driver</a>
        <br/>
        <table border="2" width="50%" cellpadding="2">
            <tr><th>Driver Name</th><th>League License</th><th>LL Start Date</th><th>Edit</th><th>Delete</th></tr>
            <c:forEach var="driver" items="${list}">
                <tr>
                    <td>${driver.name}</td>
                    <td>${driver.hasll}</td>
                    <td><c:choose><c:when test="${driver.hasll==true}">${driver.licensedate}</c:when></c:choose></td>
                    <td><a href="editdriver/${driver.driverID}">Edit</a></td>
                    <td><a href="deletedriver/${driver.driverID}" onclick="confirmDelete(event);">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
        
    </body>
