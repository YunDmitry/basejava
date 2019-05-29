<%@ page import="com.dyun.basejava.model.ContactType" %>
<%@ page import="com.dyun.basejava.model.OrganizationSection" %>
<%@ page import="com.dyun.basejava.model.SectionType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="CSS/Style.css">
    <link href="CSS/popup.css" rel="stylesheet">
    <script src="js/popup.js"></script>
    <jsp:useBean id="resume" type="com.dyun.basejava.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<div class="popupDivBack" id="div_orgEXPERIENCE">
    <div class="popupDiv" id="popupOrgExp">
        <form class="popupForm" id="form" method="post" name="form">
            <img class="close" src="images/3.png" onclick="div_hide('div_orgEXPERIENCE')">
            <h2>Organization</h2>
            <hr>
            <input class="popupField" id="orgNameEXPERIENCE" name="orgName" placeholder="Name" type="text">
            <input class="popupField" id="orgUrlEXPERIENCE" name="orgUrl" placeholder="URL" type="text">
            <a href="javascript: validatePopup('div_orgEXPERIENCE', 'EXPERIENCE')" class="submit">OK</a>
        </form>
    </div>
</div>
<div class="popupDivBack" id="div_orgEDUCATION">
    <div class="popupDiv" id="popupOrgEdu">
        <form class="popupForm" id="form_edu" method="post" name="form">
            <img class="close" src="images/3.png" onclick="div_hide('div_orgEDUCATION')">
            <h2>Organization</h2>
            <hr>
            <input class="popupField" id="orgNameEDUCATION" name="orgName" placeholder="Name" type="text">
            <input class="popupField" id="orgUrlEDUCATION" name="orgUrl" placeholder="URL" type="text">
            <a href="javascript: validatePopup('div_orgEDUCATION', 'EDUCATION')" class="submit">OK</a>
        </form>
    </div>
</div>
<div class="popupDivBack" id="div_posEXPERIENCE">
    <div class="popupDiv" id="popupPosExp">
        <form class="popupForm" method="post" name="form">
            <img class="close" src="images/3.png" onclick="div_hide('div_posEXPERIENCE')">
            <h2>Position</h2>
            <hr>
            <input class="popupField" id="posDateFromEXPERIENCE" name="posDateFromEXPERIENCE" placeholder="Date From"
                   type="date">
            <input class="popupField" id="posDateToEXPERIENCE" name="posDateToEXPERIENCE" placeholder="Date To"
                   type="date">
            <input class="popupField" id="posNameEXPERIENCE" name="posNameEXPERIENCE" placeholder="Name" type="text">
            <input class="popupField" id="posDescEXPERIENCE" name="posDescEXPERIENCE" placeholder="Description"
                   type="text">
            <a href="javascript: validatePopup('div_posEXPERIENCE', 'EXPERIENCE')" class="submit">OK</a>
        </form>
    </div>
</div>
<div class="popupDivBack" id="div_posEDUCATION">
    <div class="popupDiv" id="popupPosEdu">
        <form class="popupForm" method="post" name="form">
            <img class="close" src="images/3.png" onclick="div_hide('div_posEDUCATION')">
            <h2>Position</h2>
            <hr>
            <input class="popupField" id="posDateFromEDUCATION" name="posDateFromEDUCATION" placeholder="Date From"
                   type="date">
            <input class="popupField" id="posDateToEDUCATION" name="posDateToEDUCATION" placeholder="Date To"
                   type="date">
            <input class="popupField" id="posNameEDUCATION" name="posNameEDUCATION" placeholder="Name" type="text">
            <input class="popupField" id="posDescEDUCATION" name="posDescEDUCATION" placeholder="Description"
                   type="text">
            <a href="javascript: validatePopup('div_posEDUCATION', 'EDUCATION')" class="submit">OK</a>
        </form>
    </div>
</div>
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
            <c:set var="section" value="${resume.getSection(type)}"/>
            <%--            <c:if test="${not empty section}">--%>
            <%--                <jsp:useBean id="section" type="com.dyun.basejava.model.Section"/>--%>
            <dl>
                <dt>${type.title}
                    <c:if test="${type=='EXPERIENCE' || type=='EDUCATION'}">
                        <img class="addSection" src="img/add.png" alt="Add" onclick="div_show('div_org${type}', this)">
                    </c:if>
                </dt>
                <c:choose>
                    <c:when test="${type=='OBJECTIVE' || type=='PERSONAL'}">
                        <dd><textarea rows="2" cols="60" name="${type.name()}">${resume.getSection(type)}</textarea>
                        </dd>
                    </c:when>
                    <c:when test="${type=='ACHIVEMENT' || type=='QUALIFICATIONS'}">
                        <dd><textarea rows="5" cols="60" name="${type.name()}">${resume.getSection(type)}</textarea>
                        </dd>
                    </c:when>
                    <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
                        <input type="hidden" name="${type.name()}" value="${type.name()}">
                        <ul id="orgList${type}">
                            <c:if test="${not empty section}">
                                <jsp:useBean id="section" type="com.dyun.basejava.model.Section"/>
                                <c:forEach var="orgSection" varStatus="orgCount"
                                           items="<%=((OrganizationSection) section).getTable()%>">

                                    <li class="orgElement"><img class="addSection" src="img/delete.png" alt="Delete"
                                             onclick="this.parentNode.remove()">
                                        <input type="hidden" name="${type.name()}OrganizationId"
                                               value="${orgCount.index}">
                                        <input name="${type.name()}OrganizationName"
                                               value="${orgSection.titleLink.name}">
                                        (<input name="${type.name()}OrganizationUrl"
                                                value="${orgSection.titleLink.url}">)
                                        <img class="addSection" src="img/add.png" alt="Add"
                                             onclick="div_show('div_pos${type}', this)">
                                        <ul class="posList">
                                            <c:forEach var="pos" items="${orgSection.list}">
                                                <li><img class="addSection" src="img/delete.png" alt="Delete"
                                                         onclick="this.parentNode.remove()">
                                                    <input type=date size=10
                                                           name="${type.name()}${orgCount.index}PositionFrom"
                                                           value="${pos.dateFrom}">
                                                    <input type=date size=10
                                                           name="${type.name()}${orgCount.index}PositionTo"
                                                           value="${pos.dateTo}">
                                                    <input type=text size=30
                                                           name="${type.name()}${orgCount.index}PositionName"
                                                           value="${pos.name}">
                                                    <input type=text size=30
                                                           name="${type.name()}${orgCount.index}PositionDescription"
                                                           value="${pos.description}">
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </li>

                                </c:forEach>
                            </c:if>
                        </ul>
                    </c:when>
                </c:choose>
            </dl>
            <%--            </c:if>--%>
        </c:forEach>
        <hr>
        <button type="submit">Сохранить</button>
        <button onclick="window.history.back(); return false">Отменить</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>