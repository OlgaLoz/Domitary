<%@ page import="Model.Student" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Доктор</title>

    <link href="/Styles/bootstrap.min.css" rel="stylesheet">

    <link href="/Styles/template.css" rel="stylesheet">

    <link rel="shortcut icon" href="/img/icon.ico" type="image/x-icon">
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

<div id="wrapper">

    <!-- Sidebar -->
    <div id="sidebar-wrapper">
        <ul class="sidebar-nav">
            <li class="sidebar-brand">
                    <span class="dropdown">
                        <span class="glyphicon glyphicon-user" data-toggle="dropdown"></span>
                        Доктор
                        <ul class="dropdown-menu ">
                            <li><form action="/Action/Logout" method="post">
                                <input type="submit" value="Выход" class="btn btn-link btn-block btn-sm">
                            </form></li>
                        </ul>
                    </span>
            </li>
            <li>
                <a href="#">Список всех</a>
            </li>
            <li>
                <a href="#">Прошли медосмотр</a>
            </li>
            <li>
                <a href="#">Не прошли медосмотр</a>
            </li>
            <li>
                <a href="#">Отмечать</a>
            </li>
            <li>
                <a href="#">...</a>
            </li>
        </ul>
    </div>
    <!-- /#sidebar-wrapper -->

    <!-- Page Content -->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-12">
                    <a href="#menu-toggle" class="btn btn-info" id="menu-toggle">
                        <span class="glyphicon glyphicon-plus"></span>
                    </a>
                </div>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-xs-6">
                <div class="input-group">
                     <span class="input-group-btn">
                          <form action="/Action/FindUsersToDoctor" method="post">
                              <input type="submit" value="Найти" class="btn btn-info" type="button">
                          </form>
                     </span>
                     <input type="text" class="form-control" placeholder="Введите фамилию...">
                </div><!-- /input-group -->
            </div><!-- /.col-lg-6 -->
            <div class="col-xs-6">
                <form action="/Action/FindUsersToDoctor" method="post">
                    <input type="submit" value="Показать всех" class="btn btn-info">
                </form>
            </div>
        </div>    <!-- /.row -->

        <c:if test = "${students != null}">

            <div class="table-responsive">
                <table class="table table-hover" >
                    <tr>
                        <td><b>Имя</b></td>
                        <td><b>Отчество</b></td>
                        <td><b>Фамилия</b></td>
                        <td><b>Номер группы</b></td>
                        <td><b>Статус</b></td>
                    </tr>
                    <c:forEach  var="data" items="${students}" >
                    <tr>
                        <td>${data.getFirstName()}</td>
                        <td>${data.getMidName()}</td>
                        <td>${data.getLastName()}</td>
                        <td>${data.getGroupNumber()}</td>
                        <td>${data.getStudentStatus().toString()}</td>
                    </tr>
                    </c:forEach>
                </table>
            </div>
        </c:if>

        <div class="col-xs-12 footer">
            <hr class="colorgraph">
            <div class="col-xs-4 col-xs-offset-5 ">
                <p class="lead">&copy; By MON </p>
            </div>
        </div>
    </div>
    <!-- /#page-content-wrapper -->

</div>
<!-- /#wrapper -->

<!-- jQuery -->
<script src="Scripts/jquery.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="Scripts/bootstrap.min.js"></script>

<!-- Menu Toggle Script -->
<script>
    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");
    });
</script>

</body>

</html>