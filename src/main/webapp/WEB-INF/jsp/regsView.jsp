<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
   
    <head>
        <title>Regulations | AFPenalty</title>
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
        <h1>Regulations</h1>
        <br/><a href="/">Home</a><br/>
        <table border="2" width="70%" cellpadding="2">
            <tr><th>Regulation</th><th>Edit</th><th>Delete</th></tr>
            <c:forEach var="reg" items="${list}">
                <tr>
                    <td>${reg.text}</td>
                    <td><a href="editreg/${reg.violationID}">Edit</a></td>
                    <td><a href="deletereg/${reg.violationID}" onclick="confirmDelete(event);">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <a href="regform">Add New Regulation</a>
    </body>
