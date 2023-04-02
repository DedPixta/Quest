<%--suppress CssUnknownTarget --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="dev.makos.quest.entity.GameStatus" %>
<c:set var="context" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
    <title>Game</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="${context}/images/favicon.png" type="image/x-icon">
    <%--    Fonts   --%>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Gabriela&display=swap" rel="stylesheet">
    <%--    Styles   --%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
    <link rel="stylesheet" href="${context}/static/game.css"/>
</head>
<body>
<div class="container" style="background-image: url(${context}/images/games/game_${sessionScope.game.id}/util/bg.jpg);">
    <div class="container-fluid d-flex justify-content-center p-5">
        <div class="jumbotron p-3 p-md-5"
             style="background-image: url(${context}/images/games/game_${sessionScope.game.id}/util/bg_card.jpg);">
            <div class="block_name">
                <h1 class="text_name"><c:out value="${sessionScope.game.name}"/></h1>
            </div>
            <img src="${context}/images/games/game_${sessionScope.game.id}/${sessionScope.game.image}" class="image img-fluid mb-4"
                 alt="image">
            <c:if test="${sessionScope.gameSession.gameStatus == GameStatus.PLAY}">
                <div class="col m-2">
                    <a href="level" class="button">Продолжить</a>
                </div>
            </c:if>
            <div class="col m-2">
                <a href="req" class="button">Новая игра</a>
            </div>
            <div class="col m-2">
                <a href="menu" class="button">Выйти</a>
            </div>
        </div>
    </div>
</div>

<c:import url="/WEB-INF/jsp/parts/scripts.jsp"/>
</body>
</html>
