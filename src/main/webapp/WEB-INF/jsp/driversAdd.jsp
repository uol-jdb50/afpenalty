<!DOCTYPE HTML>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>New Driver | Driver | AFPenalty</title>
    </head>
    <body>
        <h1>Add New Driver</h1>
        <a href="/viewdrivers">Back to Drivers</a><br/>
        <form:form method="post" action="/savedriver">
            <table>
                <tr>
                    <td>Driver Name: </td>
                    <td><form:input path="name"/></td>
                </tr>
                <tr>
                    <td>Do they have a League License? </td><td><form:checkbox path="hasll"/></td>
                </tr>
                <tr>
                    <td>League License Start Date: </td><td><form:input type="date" path="licensedate"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Save"/></td>
                </tr>
            </table>
        </form:form>
    </body>
</html>