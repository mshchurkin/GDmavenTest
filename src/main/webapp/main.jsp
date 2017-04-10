<%@ page import="mshchurkin.Controllers.MainController" %>
<%
    MainController mc=new MainController();
    String res= mc.columnsInit();
%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script src="http://code.gijgo.com/1.3.0/js/gijgo.js" type="text/javascript"></script>
    <link href="http://code.gijgo.com/1.3.0/css/gijgo.css" rel="stylesheet" type="text/css"/>

    <title>Полное заключение</title>
    <meta charset="utf-8" />
</head>
<body>

<table id="grid"></table>

<script type="text/javascript" charset="utf-8">
    $(document).ready(function () {
        var grid = $('#grid').grid({
            title: 'Полное заключение',
            columns: <%=res%>,
            dataSource: '/data',
        });
    });
</script>

</body>

<h4 style="position:fixed; width:100%; padding:5px; bottom:0px;">
    <c:out value="${COOKIE_SESSION}"/>
</h4>

</html>
