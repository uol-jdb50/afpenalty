<!DOCTYPE HTML>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>Add Penalty | Report | AFPenalty</title>
    </head>
    <body>
        <h1>Add Penalty</h1>
        <a href="/judge/${reportID}">Back to Report</a>
        <form:form method="post" action="/savepenalty">
            <table>
                <tr>
                    <td><form:hidden path="reportID"></form:hidden></td>
                </tr>
                <tr>
                    <td>Penalised Driver: </td>
                    <td><form:select path="penalisedDriver">
                        <c:forEach items="${drivers}" var="d">
                            <form:option value="${d.driverID}">${d.name}</form:option>
                        </c:forEach>
                    </form:select></td>
                </tr>
                <tr>
                    <td>Violation: </td>
                    <td><form:select path="regulation">
                        <c:forEach items="${viols}" var="v">
                            <form:option value="${v.violationID}">${v.text}</form:option>
                        </c:forEach>
                    </form:select></td>
                </tr>
                <tr>
                    <td>Decision: </td>
                    <td><form:select path="decision">
                        <c:forEach items="${decs}" var="d">
                            <form:option value="${d.decisionID}">${d.text}</form:option>
                        </c:forEach>
                    </form:select></td>
                </tr>
                <tr>
                    <td>Seconds (if applicable): </td>
                    <td><form:input path="seconds"/></td>
                </tr>
                <tr>
                    <td>League License Points: </td>
                    <td><form:input path="llpts"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Save"/></td>
                </tr>
            </table>
        </form:form>
    </body>
</html>