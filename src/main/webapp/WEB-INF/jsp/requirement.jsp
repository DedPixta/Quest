<%--suppress CssUnknownTarget --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="game" scope="session" type="dev.makos.game.tombofmagic.dto.GameDto"/>

<!DOCTYPE html>
<html>
<head>
    <title>Game</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.png" type="image/x-icon">
    <%--    Fonts   --%>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Gabriela&display=swap" rel="stylesheet">
    <%--    Styles   --%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/game.css"/>
</head>
<body>
<div class="container" style="background-image: url(/images/games/game_${game.id}/util/bg.jpg);">
    <div class="header container-fluid">
        <h1 class="header-brand">${game.name}</h1>
    </div>
    <div class="container-fluid d-flex justify-content-center mt-5">
        <div class="jumbotron p-3 p-md-5" style="background-image: url(/images/games/game_${game.id}/util/bg_card.jpg);">
            <%--            TODO remove strict png format ?--%>
            <img src="${pageContext.request.contextPath}/images/games/game_${game.id}/util/requirement.png" class="image img-fluid"
                 alt="image">
            <c:forEach var="req" items="${game.requirements}">
                <div class="col shadow-2">
                    <a href="req?id=${req.id}" class="button"><c:out value="${req.name}"/></a>
                </div>
            </c:forEach>
            <div class="block_text_mini p-3 mt-3">
                <p class="text"><c:out value="${game.requirementDescription}"/></p>
            </div>
            <c:if test="${not empty sessionScope.error}">
                <div class="form-floating text-danger my-3 p-2">
                    <p class="text-center m-0">${sessionScope.error}</p>
                    <c:remove var="error" scope="session"/>
                </div>
            </c:if>
        </div>
    </div>
</div>

<c:import url="/WEB-INF/jsp/parts/scripts.jsp"/>
</body>
</html>
