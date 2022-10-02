<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
   
    <head>
        <title>Report | AFPenalty</title>
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
        <h1>Report</h1>
        <br/>
        <a href="/viewreports/${event}">Back to Event Reports</a>
        <br/>
        <b>Session:</b> ${report.session.name}
        <br/>
        <b>Timestamp:</b> ${report.timestamp}
        <br/>
        <b>Description:</b>
        <br/>
        ${report.description}
        <br/>
        <h3>Drivers Involved</h3>
        <a href="addinvolve">Add Involved Driver</a>
        <table border="2" width="20%" cellpadding="2">
            <tr><th>Driver</th><th>Remove</th></tr>
            <c:forEach var="dr" items="${driversinv}">
                <tr>
                    <td>${dr.name}</td>
                    <td><a href="/removeinvolve/${dr.driverID}" onclick="confirmDelete(event);">Remove</a></td>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <br/>
        <h3>Penalties Given</h3>
        <a href="addpenalty">Add Penalty</a>
        <table border="2" width="50%" cellpadding="2">
            <tr><th>Driver</th><th>Violation</th><th>Seconds</th><th>Decision</th><th>LL Points</th><th>Edit</th><th>Remove</th></tr>
            <c:forEach var="pen" items="${penalties}">
                <tr>
                    <td>${pen.penalisedDriver.name}</td>
                    <td>${pen.regulation.text}</td>
                    <td>${pen.seconds}</td>
                    <td>${pen.decision.text}</td>
                    <td>${pen.llpts}</td>
                    <td><a href="editpenalty/${pen.penaltyID}">Edit</a></td>
                    <td><a href="deletepenalty/${pen.penaltyID}" onclick="confirmDelete(event);">Remove</a></td>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <br/>
        <h3>Appeals Granted</h3>
        <table>
            
        </table>
    </body>
