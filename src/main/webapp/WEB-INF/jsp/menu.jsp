<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Menu</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.png" type="image/x-icon">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
</head>
<body>
<c:import url="/WEB-INF/jsp/parts/header.jsp"/>

<div class="container marketing my-5">
    <div class="row featurette">
        <div class="col-md-7">
            <h2 class="featurette-heading fw-normal lh-1">Принимай вызов. <span class="text-muted">Играй в любимые квесты.</span></h2>
            <p class="lead">Пользовательские игры на любой выбор вкуса и сложности</p>
        </div>
        <div class="col-md-5">
            <img class="bd-placeholder-img bd-placeholder-img-lg featurette-image img-fluid mx-auto" width="200" height="200" src="${pageContext.request.contextPath}/images/site/play.svg" alt="eagle-logo"/>
        </div>
    </div>

    <hr class="featurette-divider">

    <div class="row featurette">
        <div class="col-md-7 order-md-2">
            <h2 class="featurette-heading fw-normal lh-1">Сохраняй прогресс.<span class="text-muted">Личный профиль</span></h2>
            <p class="lead">Создай свой профиль и продолжай играть с последнего места.</p>
        </div>
        <div class="col-md-5 order-md-1">
            <img class="bd-placeholder-img bd-placeholder-img-lg featurette-image img-fluid mx-auto" width="200" height="200" src="${pageContext.request.contextPath}/images/site/save.svg" alt="eagle-logo"/>
        </div>
    </div>

    <hr class="featurette-divider">

    <div class="row featurette">
        <div class="col-md-7">
            <h2 class="featurette-heading fw-normal lh-1">Погружайся в процесс разработки. <span class="text-muted">Редактор квестов</span></h2>
            <p class="lead">Создавай свой собственный квест. Делись с другими пользователями.</p>
        </div>
        <div class="col-md-5">
            <img class="bd-placeholder-img bd-placeholder-img-lg featurette-image img-fluid mx-auto" width="200" height="200" src="${pageContext.request.contextPath}/images/site/edit.svg" alt="eagle-logo"/>
        </div>
    </div>
    <hr class="featurette-divider">

</div>

<c:import url="/WEB-INF/jsp/parts/footer.jsp"/>
<c:import url="/WEB-INF/jsp/parts/scripts.jsp"/>
</body>
</html>
