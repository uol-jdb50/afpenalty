<!DOCTYPE HTML>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>New Event | Events | AFPenalty</title>
    </head>
    <body>
        <h1>Add New Event</h1>
        <a href="/viewevents/${leagueID}">Back to Events</a>
        <form:form method="post" action="/saveevent">
            <table>
                <tr>
                    <td>Event Name: </td>
                    <td><form:input path="name"/></td>
                </tr>
                <tr>
                    <td>Event Date: <form:input type="date" path="date"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Save"/></td>
                </tr>
            </table>
        </form:form>
    </body>
</html>