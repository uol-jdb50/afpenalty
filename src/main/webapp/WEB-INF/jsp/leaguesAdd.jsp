<!DOCTYPE HTML>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>New League | Leagues | AFPenalty</title>
    </head>
    <body>
        <h1>Add New League</h1>
        <a href="/viewleagues">Back to Leagues</a><br/>
        <form:form method="post" action="/saveleague">
            <table>
                <tr>
                    <td>League Name: </td>
                    <td><form:input path="name"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Save"/></td>
                </tr>
            </table>
        </form:form>
    </body>
</html>