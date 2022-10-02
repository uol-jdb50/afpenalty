<!DOCTYPE HTML>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>Add One Driver | Attendance | AFPenalty</title>
    </head>
    <body>
        <h1>Add One Driver</h1>
        <a href="/viewreports/${event}">Cancel</a>
        <form:form method="post" action="/editsaveoneattend">
            <table>
                <tr>
                    <td>Driver Name: </td>
                    <td><form:select path="driverID">
                        <c:forEach var="dr" items="${drivers}">
                            <form:option value="${dr.driverID}">${dr.name}</form:option>
                        </c:forEach>
                    </form:select></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Save Edit"/></td>
                </tr>
            </table>
        </form:form>
    </body>
</html>