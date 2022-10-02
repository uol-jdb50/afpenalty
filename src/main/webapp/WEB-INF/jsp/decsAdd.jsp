<!DOCTYPE HTML>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>New Decision | Decisions | AFPenalty</title>
    </head>
    <body>
        <h1>Add New Decision</h1>
        <a href="/viewdecs">Back to Decisions</a><br/>
        <form:form method="post" action="/savedec">
            <table>
                <tr>
                    <td>Decision Text: </td>
                    <td><form:input path="text"/></td>
                </tr>
                <tr>
                    <td>Is variable? <form:checkbox path="variable"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Save"/></td>
                </tr>
            </table>
        </form:form>
    </body>
</html>