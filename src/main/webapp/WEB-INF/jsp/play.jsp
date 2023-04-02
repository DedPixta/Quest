<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Game</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.png" type="image/x-icon">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/play.css"/>
</head>
<body>
<c:import url="/WEB-INF/jsp/parts/header.jsp"/>

<div class="container mt-3">
    <div class="card">
        <div class="card-body bg-secondary">
            <h4 class="card-title text-light">Games</h4>
            <table class="table table-striped table-secondary">
                <tbody>
                <c:forEach var="game" items="${requestScope.games}" varStatus="index">
                    <tr>
                        <th scope="row" class="center"><c:out value="${index.count}"/></th>
                        <td class="d-flex align-items-center">
                            <img src="${pageContext.request.contextPath}/images/games/game_${game.id}/${game.image}" alt="game-image" class="game_logo me-3">
                            <div class="pl-3 container_mini">
                                <span><strong><c:out value="${game.name}"/></strong></span>
                                <span><c:out value="${game.description}"/></span>
                            </div>
                        </td>
                        <td class="center">
                            <a href="${pageContext.request.contextPath}/game?gameId=${game.id}" class="btn btn-danger me-2">Play</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>

<c:import url="/WEB-INF/jsp/parts/footer.jsp"/>
<c:import url="/WEB-INF/jsp/parts/scripts.jsp"/>
</body>
</html>
