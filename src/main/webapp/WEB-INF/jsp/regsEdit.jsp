<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>Edit Regulation</h1>
    <form:form method="POST" action="/editsavereg">
        <table>
            <tr>
                <td><form:hidden path="violationID"/></td>
            </tr>
            <tr>
                <td>Regulation Text:</td>
                <td><form:input path="text"/></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Save Edit"/></td>
            </tr>
        </table>
    </form:form>