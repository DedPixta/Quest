<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
    <link rel="icon" href="images/favicon.ico" type="image/x-icon">

    <%--    Fonts   --%>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Gabriela&display=swap" rel="stylesheet">

    <%--    Styles   --%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="static/style.css">

    <title>Tomb</title>
</head>

<body>
<div class="container d-flex flex-column align-items-center">
    <div class="header container-fluid">
        <h1 class="header-brand">Tomb of Magic</h1>
        <div class="button-menu">
            <a class="header-text" href="${pageContext.request.contextPath}/menu">Menu</a>
        </div>
    </div>
    <div class="container-fluid d-flex justify-content-center">
        <div class="session">
            <p class="text">Стихия: ${sessionScope.element.getName()}</p>
            <p class="text">Местоположение: ${requestScope.level.getName()}</p>
        </div>
        <div class="jumbotron p-3 p-md-5">
            <img src="images/${requestScope.level.getImage()}" class="image img-fluid shadow" alt="image">
            <div class="block_text mt-3 p-3">
                <p class="text text-start lead">${requestScope.level.getDescription()}</p>
            </div>
            <div class="row row-cols-2 mt-3">
                <c:forEach items="${requestScope.level.getButtons()}" var="button" varStatus="loopCounter">
                    <c:set var="id" scope="page" value="${loopCounter.index}"/>
                    <c:set var="buttonDesc" scope="page" value="${button.getDescription(sessionScope.element)}"/>
                    <c:if test="${not empty buttonDesc}">
                        <div class="col">
                            <a class="button" href="level?button=${id}">
                                <c:out value="${buttonDesc}"/>
                            </a>
                        </div>
                    </c:if>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>

</html>
