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
    <link href="http://code.fancygrid.com/fancy.min.css" rel="stylesheet">
    <script src="http://code.fancygrid.com/fancy.min.js"></script>
    <title>Hello World</title>
</head>
<body>
<h2>
    <c:out value="${COOIKE_SESSION}"/>
</h2>
<div id="container"></div>

<script>
//    $(document).ready(function () {
//        var data = [
//            {name: 'Ted', surname: 'Smith', company: 'Electrical Systems', age: 30},
//            {name: 'Ed', surname: 'Johnson', company: 'Energy and Oil', age: 35},
//            {name: 'Sam', surname: 'Williams', company: 'Airbus', age: 38},
//            {name: 'Alexander', surname: 'Brown', company: 'Renault', age: 24},
//            {name: 'Nicholas', surname: 'Miller', company: 'Adobe', age: 33},
//            {name: 'Andrew', surname: 'Thompson', company: 'Google', age: 28},
//            {name: 'Ryan', surname: 'Walker', company: 'Siemens', age: 39},
//            {name: 'John', surname: 'Scott', company: 'Cargo', age: 45},
//            {name: 'James', surname: 'Phillips', company: 'Pro bugs', age: 30},
//            {name: 'Brian', surname: 'Edwards', company: 'IT Consultant', age: 23},
//            {name: 'Jack', surname: 'Richardson', company: 'Europe IT', age: 24},
//            {name: 'Alex', surname: 'Howard', company: 'Cisco', age: 27},
//            {name: 'Carlos', surname: 'Wood', company: 'HP', age: 36},
//            {name: 'Adrian', surname: 'Russell', company: 'Micro Systems', age: 31},
//            {name: 'Jeremy', surname: 'Hamilton', company: 'Big Machines', age: 30},
//            {name: 'Ivan', surname: 'Woods', company: '', age: 24},
//            {name: 'Peter', surname: 'West', company: 'Adobe', age: 26},
//            {name: 'Scott', surname: 'Simpson', company: 'IBM', age: 29},
//            {name: 'Lorenzo', surname: 'Tucker', company: 'Intel', age: 29},
//            {name: 'Randy', surname: 'Grant', company: 'Bridges', age: 30},
//            {name: 'Arthur', surname: 'Gardner', company: 'Google', age: 31},
//            {name: 'Orlando', surname: 'Ruiz', company: 'Apple', age: 32}
//        ];

        $(function () {
            $('#container').FancyGrid({
                width: 500,
                height: 400,
                data: {
                    proxy: {
                        url: '/data'}
                }

//                columns: [{
//                    index: 'company',
//                    title: 'Company',
//                    type: 'string',
//                    width: 100
//                }, {
//                    index: 'name',
//                    title: 'Name',
//                    type: 'string',
//                    width: 100
//                }, {
//                    index: 'surname',
//                    title: 'Sur Name',
//                    type: 'string',
//                    width: 100
//                }, {
//                    index: 'age',
//                    title: 'Age',
//                    type: 'number',
//                    width: 100
//                }]
//            });
        })
    });
</script>
</body>
</html>
