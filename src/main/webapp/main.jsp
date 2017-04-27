<%@ page import="mshchurkin.Controllers.MainController" %>
<%
    MainController mc = new MainController();
    String gridF5Cols = mc.columnsInit(680031);
    String gridF1Cols=mc.columnsInit(680032);
%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js" charset="UTF-8"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" charset="UTF-8"></script>
    <script src="http://code.gijgo.com/1.3.0/js/gijgo.js" type="text/javascript" charset="UTF-8"></script>
    <link href="http://code.gijgo.com/1.3.0/css/gijgo.css" rel="stylesheet" type="text/css"/>
    <title>Полное заключение</title>
    <meta charset="utf-8"/>
</head>
<style>
    .tabs {
        display: inline-block;
    }

    .tabs > div {
        padding-top: 10px;
    }

    .tabs ul {
        margin: 0px;
        padding: 0px;
    }

    .tabs ul:after {
        content: "";
        display: block;
        clear: both;
        height: 5px;
        background: #46c765;
    }

    .tabs ul li {
        margin: 0px;
        padding: 0px;
        cursor: pointer;
        display: block;
        float: left;
        padding: 10px 15px;
        background: #e9eaeb;
        color: #707070;
    }

    .tabs ul li.active, .tabs ul li.active:hover {
        background: #46c765;
        color: #fff;
    }

    .tabs ul li:hover {
        background: #d6d6d7;
    }
</style>
<body style="background-color: #e7f1fa;">
<div class="tabs">
    <ul>
        <li>Основной раздел ф.5</li>
        <li>Основной раздел ф.1</li>
    </ul>
    <div>
        <div>
            <table id="gridF5"></table>

            <script type="text/javascript" charset="utf-8">
                $(document).ready(function () {
                    var grid = $('#gridF5').grid({
                        title: 'Основной раздел ф.5',
                        columnReorder: true,
                        uiLibrary: 'bootstrap',
                        columns: <%=gridF5Cols%>,
                        dataSource: '/dataF5'
                    });
                });
            </script>
        </div>
        <div>
            <table id="gridF1"></table>

            <script type="text/javascript" charset="utf-8">
                $(document).ready(function () {
                    var grid = $('#gridF1').grid({
                        title: 'Основной раздел ф.1',
                        columnReorder: true,
                        uiLibrary: 'bootstrap',
                        columns: <%=gridF1Cols%>,
                        dataSource: '/dataF1'
                    });
                });
            </script>
        </div>
    </div>
</div>
<script charset="UTF-8">
    $(document).ready(function () {
        $(".tabs").lightTabs();
    });

    (function ($) {
        jQuery.fn.lightTabs = function (options) {

            var createTabs = function () {
                tabs = this;
                i = 0;

                showPage = function (i) {
                    $(tabs).children("div").children("div").hide();
                    $(tabs).children("div").children("div").eq(i).show();
                    $(tabs).children("ul").children("li").removeClass("active");
                    $(tabs).children("ul").children("li").eq(i).addClass("active");
                }
                showPage(0);

                $(tabs).children("ul").children("li").each(function (index, element) {
                    $(element).attr("data-page", i);
                    i++;
                });

                $(tabs).children("ul").children("li").click(function () {
                    showPage(parseInt($(this).attr("data-page")));
                });
            };
            return this.each(createTabs);
        };
    })(jQuery);
</script>
</body>

<h4 style="position:fixed; width:100%; padding:5px; bottom:0px;  color: maroon; ">
    <c:out value="${COOKIE_SESSION}"/>
</h4>

</html>
