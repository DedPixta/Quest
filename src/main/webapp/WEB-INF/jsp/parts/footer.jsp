<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="dev.makos.game.tombofmagic.entity.Role" %>

<footer class="fixed-bottom bg-secondary">
    <ul class="nav justify-content-center border-bottom">
        <li class="nav-item"><a href="${pageContext.request.contextPath}/menu" class="nav-link px-2 text-light">Home</a></li>
        <c:if test="${not empty sessionScope.user.role and Role.GUEST != sessionScope.user.role}">
            <li class="nav-item"><a href="${pageContext.request.contextPath}/accounts" class="nav-link px-2 text-light">Accounts</a></li>
        </c:if>
        <c:if test="${Role.ADMIN == sessionScope.user.role or Role.EDITOR == sessionScope.user.role}">
            <li class="nav-item"><a href="${pageContext.request.contextPath}/edit" class="nav-link px-2 text-light">Edit</a></li>
        </c:if>
        <li class="nav-item"><a href="${pageContext.request.contextPath}/play" class="nav-link px-2 text-light">Play</a></li>
    </ul>
    <p class="text-center my-1 text-light">2022 Company, Inc</p>
</footer>
