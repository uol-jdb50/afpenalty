<!DOCTYPE HTML>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>New Report | Event | AFPenalty</title>
    </head>
    <body>
        <h1>Add New Report</h1>
        <form:form method="post" action="/savereport">
            <table>
                <tr>
                    <td>Session: </td>
                    <td><form:select path="session">
                        <c:forEach items="${sessions}" var="d">
                            <form:option value="${d.sessionID}">${d.name}</form:option>
                        </c:forEach>
                    </form:select></td>
                </tr>
                <tr>
                    <td>Timestamp: </td>
                    <td><form:input path="timestamp"/></td>
                </tr>
                <tr>
                    <td>Description: </td>
                    <td><form:textarea path="description" rows="10" cols="60"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Save"/></td>
                </tr>
            </table>
        </form:form>
    </body>
</html>