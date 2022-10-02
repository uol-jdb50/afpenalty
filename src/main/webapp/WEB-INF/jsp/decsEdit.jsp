<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>Edit Decision</h1>
<a href="/viewdecs">Back to Decisions</a><br/>
    <form:form method="POST" action="/editsavedec">
        <table>
            <tr>
                <td><form:input path="decisionID"/></td>
            </tr>
            <tr>
                <td>Regulation Text:</td>
                <td><form:input path="text"/></td>
            </tr>
            <tr>
                <td>Is variable? <form:checkbox path="variable"/></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Save Edit"/></td>
            </tr>
        </table>
    </form:form>