<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
<div class="container">
    <div class="header container-fluid">
        <h1 class="header-brand">Tomb of Magic</h1>
    </div>
    <div class="container-fluid d-flex justify-content-center mt-5">
        <div class="jumbotron p-3 p-md-5">
            <img src="images/elements.png" class="image img-fluid" alt="image">
            <div class="col shadow-2">
                <a href="element?element=1" class="button">Вода</a>
            </div>
            <div class="col shadow-2">
                <a href="element?element=2" class="button">Земля</a>
            </div>
            <div class="col shadow-2">
                <a href="element?element=3" class="button">Огонь</a>
            </div>
            <div class="block_text_mini p-3 mt-3">
                <p class="text ">Стихия повлияет на ваши возможности и доступные действия</p>
            </div>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
</body>
</html>
