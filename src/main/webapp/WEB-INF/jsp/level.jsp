<%--suppress CssUnknownTarget --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="game" scope="session" type="dev.makos.quest.dto.GameDto"/>
<jsp:useBean id="gameSession" scope="session" type="dev.makos.quest.dto.GameSessionDto"/>
<jsp:useBean id="level" scope="request" type="dev.makos.quest.dto.LevelDto"/>

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
<div class="container d-flex flex-column align-items-center" style="background-image: url(/images/games/game_${game.id}/util/bg.jpg);">
    <div class="header container-fluid">
        <h1 class="header-brand"><c:out value="${game.name}"/></h1>
        <div class="button-menu">
            <a class="header-text" href="${pageContext.request.contextPath}/game?gameId=${game.id}">Menu</a>
        </div>
    </div>
    <div class="container-fluid d-flex justify-content-center">
        <div class="session">
            <p class="text">
            <c:out value="${gameSession.requirement.name}"/></p>
            <p class="text">
            <c:out value="${level.name}"/></p>
        </div>
        <div class="jumbotron p-3 p-md-5" style="background-image: url(/images/games/game_${game.id}/util/bg_card.jpg);">
            <img src="${pageContext.request.contextPath}/images/games/game_${game.id}/${level.image}" class="image img-fluid shadow" alt="image">
            <div class="block_text mt-3 p-3">
                <p class="text text-start lead">${level.description}</p>
            </div>
            <div class="row row-cols-2 mt-3">
                <c:forEach items="${level.buttons}" var="button" varStatus="loopCounter">
                    <c:if test="${not empty button.description}">
                        <div class="col">
                            <a class="button" href="level?button=${loopCounter.index}">
                                <c:out value="${button.description}"/>
                            </a>
                        </div>
                    </c:if>
                </c:forEach>
            </div>
        </div>
    </div>
</div>

<c:import url="/WEB-INF/jsp/parts/scripts.jsp"/>
</body>
</html>
