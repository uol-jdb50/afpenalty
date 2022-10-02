<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>Edit League</h1>
<a href="/viewleagues">Back to Leagues</a><br/>
    <form:form method="POST" action="/editsaveleague">
        <table>
            <tr>
                <td><form:hidden path="seasonID"/></td>
            </tr>
            <tr>
                <td>League Name:</td>
                <td><form:input path="name"/></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Save Edit"/></td>
            </tr>
        </table>
    </form:form>