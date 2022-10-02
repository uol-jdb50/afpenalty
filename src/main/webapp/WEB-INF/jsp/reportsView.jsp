<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
   
    <head>
        <title>Reports | AFPenalty</title>
        <script type="text/javascript">
            function confirmDelete(e) {
                var retVal = confirm("Are you sure you want to delete this?");
                if (retVal == true) {
                    return true;
                } else {
                    e.preventDefault();
                    return false;
                }
            }
        </script>
    </head>
    <body>
        <h1>Reports</h1>
        <a href="/viewevents/${season}">Back to Events</a>
        <table border="2" width="50%" cellpadding="2">
            <tr><th>Session</th><th>Timestamp</th><th>View</th><th>Edit</th><th>Delete</th></tr>
            <c:forEach var="report" items="${list}">
                <tr>
                    <td>${report.session.name}</td>
                    <td>${report.timestamp}</td>
                    <td><a href="/judge/${report.reportID}">View</a></td>
                    <td><a href="editreport/${report.reportID}">Edit</a></td>
                    <td><a href="deletereport/${report.reportID}" onclick="confirmDelete(event);">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <a href="reportform/${event}">Add New Report</a>
        <br/>
        <br/>
        <h3>Event Sessions</h3>
        <table border="2" width="20%" cellpadding="2">
            <tr><th>Session</th><th>Edit</th><th>Delete</th></tr>
            <c:forEach var="session" items="${sessions}">
                <tr>
                    <td>${session.name}</td>
                    <td><a href="editsession/${session.sessionID}">Edit</a></td>
                    <td><a href="deletesession/${session.sessionID}" onclick="confirmDelete(event);">Remove</a></td>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <a href="sessionform/${event}">Add New Session</a>
        <br/>
        <br/>
        <h3>Drivers Attended</h3>
        <a href="uploadattend/${id}">Create Attendance List</a><br/>
        <a href="addoneattend/${id}">Add Single Driver</a>
        <table border="2" width="20%" cellpadding="2">
            <tr><th>Driver</th><th>Remove</th></tr>
            <c:forEach var="driver" items="${attend}">
                <tr>
                    <td>${driver.name}</td>
                    <td><a href="/removeattend/${driver.driverID}">Remove</a></td>
                </tr>
            </c:forEach>
        </table>
    </body>
