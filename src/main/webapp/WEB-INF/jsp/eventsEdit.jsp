<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>Edit Events</h1>
<a href="/viewevents/${leagueID}">Back to Events</a>
    <form:form method="POST" action="/editsaveevent">
        <table>
            <tr>
                <td><form:hidden path="eventID"/></td>
            </tr>
            <tr>
                <td>Event Name:</td>
                <td><form:input path="name"/></td>
            </tr>
            <tr>
                <td>Event Date: <form:input type="date" path="date"/></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Save Edit"/></td>
            </tr>
        </table>
    </form:form>