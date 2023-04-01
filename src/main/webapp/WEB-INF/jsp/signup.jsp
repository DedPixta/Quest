<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="dev.makos.game.tombofmagic.entity.Role" %>


<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.png" type="image/x-icon">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/signin.css"/>
</head>
<body>
<c:import url="/WEB-INF/jsp/parts/header.jsp"/>

<main class="form-register w-100 m-auto">
    <form class="form-horizontal" action="${pageContext.request.contextPath}/signup" method="post">

        <fieldset>
            <img class="mb-3 mx-auto d-block" src="${pageContext.request.contextPath}/images/site/eagle-black.svg" alt="logo" width="100" height="100">
            <legend class="text-center">Register</legend>

            <div class="form-group">
                <label class="col control-label" for="input_username"><strong>Username</strong></label>
                <div class="col d-flex flex-column mb-2">
                    <input id="input_username" name="login" type="text" placeholder="username"
                           class="form-control input-md" required
                           minlength="1" maxlength="20" pattern="[A-Za-z0-9]+">
                    <span class="form-text text-center"><small>Latin letters or digits</small></span>
                </div>
            </div>

            <div class="form-group">
                <label class="col control-label" for="input_password"><strong>Password</strong></label>
                <div class="col d-flex flex-column mb-2">
                    <input id="input_password" name="password" type="password" placeholder="********"
                           class="form-control input-md" required
                           minlength="1" maxlength="8" pattern="[A-Za-z0-9]+">
                    <span class="form-text text-center"><small>Latin letters or digits</small></span>
                </div>
            </div>

            <div class="form-group">
                <label class="col control-label" for="select_roles"><strong>Role</strong></label>
                <div class="col">
                    <select id="select_roles" name="role" class="form-control">
                        <c:forEach var="role" items="${Role.values()}">
                            <c:choose>
                                <c:when test="${Role.GUEST == role}"/>
                                <c:when test="${Role.ADMIN != role}">
                                    <option value="${role}"><c:out value="${role}"/></option>
                                </c:when>
                                <c:otherwise>
                                    <c:if test="${Role.ADMIN == sessionScope.role}">
                                        <option value="${role}"><c:out value="${role}"/></option>
                                    </c:if>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label class="col control-label" for="singlebutton"></label>
                <div class="col d-flex">
                    <button id="singlebutton" name="singlebutton" class="btn btn-primary w-50 mx-auto">Submit</button>
                </div>
            </div>
            <c:if test="${not empty requestScope.error}">
                <div class="form-floating text-danger my-3 p-2">
                    <p class="text-center m-0">${requestScope.error}</p>
                </div>
            </c:if>
        </fieldset>
    </form>
</main>

<c:import url="/WEB-INF/jsp/parts/footer.jsp"/>
<c:import url="/WEB-INF/jsp/parts/scripts.jsp"/>
</body>

</html>
