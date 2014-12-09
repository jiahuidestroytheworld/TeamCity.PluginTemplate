<%@ include file="/include.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="props" tagdir="/WEB-INF/tags/props" %>
<%@ taglib prefix="l" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="forms" tagdir="/WEB-INF/tags/forms" %>
<%@ taglib prefix="bs" tagdir="/WEB-INF/tags" %>
<%--<jsp:useBean id="propertiesBean" scope="request" type="jetbrains.buildServer.controllers.BasePropertiesBean"/>--%>
<%--<jsp:useBean id="xsollaProp" type="xsollacustomrunner.server.XsollaBean"/>--%>

<c:set var="title" value="Email Generator" scope="request"/>


<l:settingsGroup title="Email Set up">
    <tr>
        <td>
            <span>Title:</span>
            <br>
            <props:textProperty name="emailTitle"/>
            <br>
            <span>Send to:</span>
            <br>
            <props:textProperty name="sendTo" />
            <br>
            <span>cc:</span>
            <br>
            <props:textProperty name="ccTo"/>
            <span>cc:</span>
            <br>
            <props:textProperty name="bccTo"/>
            <span>cc:</span>
            <br>
            <props:textProperty name="emailBody"/>
        </td>
    </tr>
</l:settingsGroup>

<script type="text/javascript">
</script>