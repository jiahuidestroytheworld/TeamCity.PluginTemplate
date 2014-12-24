<%@ include file="/include-internal.jsp" %>
<%@ taglib prefix="props" tagdir="/WEB-INF/tags/props" %>
<%@ taglib prefix="l" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="forms" tagdir="/WEB-INF/tags/forms" %>
<%@ taglib prefix="bs" tagdir="/WEB-INF/tags" %>
<%--
  ~ Copyright 2000-2014 JetBrains s.r.o.
  ~
  ~ Licensed under the Apache License, Version 2.0 (the "License");
  ~ you may not use this file except in compliance with the License.
  ~ You may obtain a copy of the License at
  ~
  ~ http://www.apache.org/licenses/LICENSE-2.0
  ~
  ~ Unless required by applicable law or agreed to in writing, software
  ~ distributed under the License is distributed on an "AS IS" BASIS,
  ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  ~ See the License for the specific language governing permissions and
  ~ limitations under the License.
  --%>

<jsp:useBean id="propertiesBean" scope="request" type="jetbrains.buildServer.controllers.BasePropertiesBean"/>
<jsp:useBean id="eb" class="com.jiahui.emailplugin.EmailBean" />

<c:set var="title" value="Email Generator" scope="request"/>



    <div>Title:
            <props:textProperty name="emailTitle" className="longField" />

    </div>
    <div>From:
            <props:textProperty name="emailFrom" className="longField" />
    </div>
    <div>Send to:
            <props:textProperty name="emailTo" className="longField" />
    </div>
    <div>
         cc:
            <props:textProperty name="emailCC" className="longField" />
    </div>
    <div>bcc:
            <props:textProperty name="emailBCC" className="longField" />
    </div>
    <div>Body:
      <props:multilineProperty name="emailBody"
                               linkTitle="Enter Email body"
                               cols="58" rows="10"
                               expanded="${true}"/>
  </div>

