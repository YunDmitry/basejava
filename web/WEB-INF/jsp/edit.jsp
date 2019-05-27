<%@ page import="com.dyun.basejava.model.ContactType" %>
<%@ page import="com.dyun.basejava.model.ListSection" %>
<%@ page import="com.dyun.basejava.model.SectionType" %>
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
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <dl>
            <dt>Имя:</dt>
            <dd><input type="text" name="fullName" size=50 value="${resume.fullName}"></dd>
        </dl>
        <h3>Контакты:</h3>
        <c:forEach var="type" items="<%=ContactType.values()%>">
            <dl>
                <dt>${type.title}</dt>
                <dd><input type="text" name="${type.name()}" size=30 value="${resume.getContact(type)}"></dd>
            </dl>
        </c:forEach>
        <h3>Секции:</h3>
        <c:forEach var="type" items="<%=SectionType.values()%>">
<%--            <c:set var="section" value="${resume.getSection(type)}"/>--%>
<%--            <jsp:useBean id="section" type="com.dyun.basejava.model.Section"/>--%>
            <dl>
                <dt>${type.title}</dt>
                <c:choose>
                    <c:when test="${type=='OBJECTIVE' || type=='PERSONAL'}">
                        <dd><textarea rows="2" cols="60" name="${type.name()}">${resume.getSection(type)}</textarea></dd>
                    </c:when>
                    <c:when test="${type=='ACHIVEMENT' || type=='QUALIFICATIONS'}">
                        <dd><textarea rows="5" cols="60" name="${type.name()}">${resume.getSection(type)}</textarea></dd>
                    </c:when>
                </c:choose>
            </dl>
        </c:forEach>
        <hr>
        <button type="submit">Сохранить</button>
        <button onclick="window.history.back(); return false">Отменить</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>