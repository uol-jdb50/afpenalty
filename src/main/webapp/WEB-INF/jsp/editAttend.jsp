<!DOCTYPE HTML>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
    <head>
        <title>Edit Driver | Driver | AFPenalty</title>
    </head>
    <body>
        <h1>Edit Driver</h1>
        <a href="viewevents/viewreports/{event}">Cancel</a>
        <form:form method="post" action="/editsaveattend">
            <table>
                <tr>
                    <td><form:hidden path="guid"/></td>
                </tr>
                <tr>
                    <td>Driver Name: </td>
                    <td><form:input path="name"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Save Edit"/></td>
                </tr>
            </table>
        </form:form>
    </body>
</html>