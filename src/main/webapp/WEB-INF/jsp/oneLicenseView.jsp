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
        <h1>License Report | ${driver.name}</h1>
        <br/>
        <a href="/viewlicensescache">Return to Full List</a>
        <br/>
        <br/>
        <b>Name:</b> ${driver.name}
        <br/>
        <b>Has a League License?</b> ${driver.hasll}
        <br/>
        <b>League License Start:</b> ${driver.licensedate}
        <br/>
        <b>Current LL Points:</b> ${points}
        <br/>
        <c:forEach var="eve" items="${events}">
            <table border="2" width="30%" cellpadding="2">
                <tr><th width="40%">Season</th><td>${eve.season.name}</td></tr>
                <tr><th>Event</th><td>${eve.name}</td></tr>
                <tr><th>Date</th><td>${eve.date}</td></tr>
                <tr><th>License Points</th><td>${eve.driverllpts}</td></tr>
            </table>
            <br/>
        </c:forEach>
    </body>