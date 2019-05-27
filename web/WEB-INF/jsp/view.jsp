<%@ page import="com.dyun.basejava.model.ListSection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="CSS/Style.css">
    <jsp:useBean id="resume" type="com.dyun.basejava.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <h2>${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/pencil.png" alt="Edit"></a></h2>
    <p>
        <c:forEach var="contactEntry" items="${resume.contacts}">
            <jsp:useBean id="contactEntry"
                         type="java.util.Map.Entry<com.dyun.basejava.model.ContactType, java.lang.String>"/>
                <%=contactEntry.getKey().toHtml(contactEntry.getValue())%><br/>
        </c:forEach>
    <p>
    <hr>
    <p>
        <c:forEach var="sectionEntry" items="${resume.sections}">
            <jsp:useBean id="sectionEntry"
                         type="java.util.Map.Entry<com.dyun.basejava.model.SectionType, com.dyun.basejava.model.Section>"/>
            <c:set var="sectionType" value="${sectionEntry.key}"/>
            <c:set var="section" value="${sectionEntry.value}"/>
            <b>${sectionType.title}</b><br>
                <c:choose>
                    <c:when test="${sectionType=='OBJECTIVE' || sectionType=='PERSONAL'}">
                        ${section}
                    </c:when>
                    <c:when test="${sectionType=='ACHIVEMENT' || sectionType=='QUALIFICATIONS'}">
                        <c:forEach var="item" items="<%=((ListSection) sectionEntry.getValue()).getList()%>">
                            <li>${item}</li>
                        </c:forEach>
                    </c:when>
                </c:choose>
                <br><br>
        </c:forEach>
    <p>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>