<%@ include file="/include.jsp" %>
<%@ taglib prefix="props" tagdir="/WEB-INF/tags/props" %>
<%@ taglib prefix="l" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="forms" tagdir="/WEB-INF/tags/forms" %>
<%@ taglib prefix="bs" tagdir="/WEB-INF/tags" %>
<jsp:useBean id="propertiesBean" scope="request" type="jetbrains.buildServer.controllers.BasePropertiesBean"/>
<jsp:useBean id="eb" class="com.jiahui.emailplugin.EmailBean" />

<c:set var="title" value="Email Generator" scope="request"/>


<l:settingsGroup title="Email Set up">
    <tr>
        <th><label>Title:</label></th>
        <td>
            <props:textProperty name="emailTitle" className="longField" />
        </td>
    </tr>
    <tr>
        <th><label>From: </label></th>
        <td>
            <props:textProperty name="emailFrom" className="longField" />
        </td>
    </tr>
    <tr>
        <th><label>Send to:</label></th>
         <td>
            <props:textProperty name="emailTo" className="longField" />
         </td>
    </tr>
    <tr>
         <th><label>cc:</label></th>
         <td>
            <props:textProperty name="emailCC" className="longField" />
        </td>
    </tr>
    <tr>
        <th><label>bcc: </label></th>
         <td>
            <props:textProperty name="emailBCC" className="longField" />
         </td>
    </tr>
    <tr>
        <th><label>Body: </label></th>
         <td>
            <props:textProperty name="emailBody" className="longField" />
        </td>
    </tr>
</l:settingsGroup>
