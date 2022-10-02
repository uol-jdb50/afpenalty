<!DOCTYPE HTML>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>Edit Driver | Driver | AFPenalty</title>
    </head>
    <body>
        <h1>Edit Driver</h1>
        <a href="/viewdrivers">Back to Drivers</a>
        <form:form method="post" action="/editsavedriver">
            <table>
                <form:hidden path="driverID"/>
                <tr>
                    <td>Driver Name: </td>
                    <td><form:input path="name"/></td>
                </tr>
                <tr>
                    <td>Do they have a League License? <form:checkbox path="hasll"/></td>
                </tr>
                <tr>
                    <td>League License Start Date: <form:input type="date" path="licensedate"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Save Edit"/></td>
                </tr>
            </table>
        </form:form>
    </body>
</html>