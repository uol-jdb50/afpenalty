<!DOCTYPE HTML>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>Add Involved Driver | Report | AFPenalty</title>
    </head>
    <body>
        <h1>Add Involved Driver</h1>
        <a href="/viewevents/viewreports/judge/${reportID}">Back to Report</a>
        <form:form method="post" action="/saveinvolve">
            <table>
                <tr>
                    <td>Driver Name: </td>
                    <td><form:select path="driverID">
                        <c:forEach items="${attend}" var="d">
                            <form:option value="${d.driverID}">${d.name}</form:option>
                        </c:forEach>
                    </form:select></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Save"/></td>
                </tr>
            </table>
        </form:form>
    </body>
</html>