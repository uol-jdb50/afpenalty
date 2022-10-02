<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>Verify Attendance</h1>
<a href="/viewevents/viewreports/${event}">Back to Reports</a><br/>
<h3>Found</h3>
All drivers in this list will have their attendance updated for the event.<br/>
If a driver's GUID was not in the database, it will be added.<br/>
<table border="2" width="30%" cellpadding="2">
    <tr><th>GUID</th><th>Name</th></tr>
    <c:forEach var="drf" items="${attendfound}">
        <tr>
            <td>${drf.guid}</td>
            <td>${drf.name}</td>
        </tr>
    </c:forEach>
</table>
<br/>
<h3>Not Found</h3>
If a driver's name is not corrected using the text boxes, a new driver will be added to the database (LL will be set to false by default).<br/>
Attendance for that driver will then be added.<br/>
<table border="2" width="30%" cellpadding="2">
    <tr><th>GUID</th><th>Name</th><th>Edit</th></tr>
    <c:forEach var="drnf" items="${attendnotfound}">
        <tr>
            <td>${drnf.guid}</td>
            <td>${drnf.name}</td>
            <td><a href="/editattend/${drnf.guid}">Edit</a></td>
        </tr>
    </c:forEach>
</table>
<br/>
<a href="/confirmattend">Confirm Attendance</a>