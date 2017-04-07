<%--
  Created by IntelliJ IDEA.
  User: smd
  Date: 06-Apr-17
  Time: 11:59
  To change this template use File | Settings | File Templates.
--%>
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
    <title>Hello World</title>
</head>
<body>
<h2>
    <c:out value="${COOKIE_SESSION}"/>
</h2>
<table id="grid"></table>
<script type="text/javascript">
    $(document).ready(function () {
        var grid = $('#grid').grid({
            dataSource: '/data',
            columns: [
                { field: 'id', width: 32 },
                { field: 'name', sortable: true },
                { field: 'firstQuestion', sortable: true }
            ],
            pager: { limit: 5 }
        });
    });
</script>

</body>
</html>
