<%@ page import="mshchurkin.Controllers.MainController" %>
<%
    MainController mc = new MainController();
    String res = mc.columnsInit();
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
        <li>Заключение</li>
        <li>Структура бухгалтерского баланаса</li>
        <li>Страхование</li>
        <li>Перестрахование</li>
        <li>Перестрахование по учетным группам</li>
        <li>Сальдо по перестрахованию</li>
        <li>Состав активов</li>
        <li>Страховые резервы</li>
        <li>Финансовый результат</li>
        <li>Контрольные показатели</li>
        <li>Данные статистической отчетности по ф.№1-С страховой огранизации</li>
        <li>Динамика основных показателей</li>
        <li>График динамика основных показателей</li>
    </ul>
    <div>
        <div>
            <table id="grid"></table>

            <script type="text/javascript" charset="utf-8">
                $(document).ready(function () {
                    var grid = $('#grid').grid({
                        title: 'Заключение',
                        columnReorder: true,
                        uiLibrary: 'bootstrap',
                        columns: <%=res%>,
                        dataSource: '/data'
                    });
                });
            </script>
        </div>
        <div>Структура бухгалтерского баланаса</div>
        <div>Страхование</div>
        <div>Перестрахование</div>
        <div>Перестрахование по учетным группам</div>
        <div>Сальдо по перестрахованию</div>
        <div>Состав активов</div>
        <div>Страховые резервы</div>
        <div>Финансовый результа</div>
        <div>Контрольные показатели</div>
        <div>Данные статистической отчетности по ф.№1-С страховой огранизации</div>
        <div>Динамика основных показателей</div>
        <div>График динамика основных показателей</div>
    </div>
</div>
<script>
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
