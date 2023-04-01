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

<div class="container my-3 d-flex justify-content-center">

    <img src="${pageContext.request.contextPath}/images/site/profile.png"
         class="image img-fluid shadow mb-5 bg-white rounded w-75" alt="image">

</div>

<c:import url="/WEB-INF/jsp/parts/footer.jsp"/>
<c:import url="/WEB-INF/jsp/parts/scripts.jsp"/>
</body>
</html>
