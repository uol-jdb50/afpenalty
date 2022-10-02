<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
   
    <head>
        <title>Decisions | AFPenalty</title>
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
        <h1>Decisions</h1><br/><br/>
        <a href="/">Home</a><br/>
        <table border="2" width="70%" cellpadding="2">
            <tr><th>Decision</th><th>Is variable?</th><th>Edit</th><th>Delete</th></tr>
            <c:forEach var="dec" items="${list}">
                <tr>
                    <td>${dec.text}</td>
                    <td>${dec.variable}</td>
                    <td><a href="editdec/${dec.decisionID}">Edit</a></td>
                    <td><a href="deletedec/${dec.decisionID}" onclick="confirmDelete(event);">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <a href="decform">Add New Decision</a>
    </body>
