<!DOCTYPE HTML>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>New Regulation | Regulations | AFPenalty</title>
    </head>
    <body>
        <h1>Add New Regulation</h1>
        <form:form method="post" action="/savereg">
            <table>
                <tr>
                    <td>Regulation Text: </td>
                    <td><form:input path="text"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Save"/></td>
                </tr>
            </table>
        </form:form>
    </body>
</html>