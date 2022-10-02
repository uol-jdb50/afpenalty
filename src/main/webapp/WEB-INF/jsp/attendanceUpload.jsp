<!DOCTYPE HTML>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>Attendance | AFPenalty</title>
    </head>
    <body>
        <h1>Upload Results JSON</h1>
        <form:form method="post" action="/uploadattendsave">
            <label>Paste JSON file here</label><br/>
            <form:textarea rows="50" path="file" cols="100"/>
            <br/>
            <input type="submit" value="Upload File"/>
        </form:form>
    </body>
</html>