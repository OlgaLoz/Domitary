<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="Model.StudentStatus" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Деканат</title>

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
                        Работник деканата
                        <ul class="dropdown-menu ">
                            <li><form action="/Action/Logout" method="post">
                                <input type="submit" value="Выход" class="btn btn-link btn-block btn-sm ">
                            </form></li>
                        </ul>
                    </span>
                </li>

                <li>
                    <form action="/Action/ReadAllToDeanery" method="post">
                        <a href="#">
                            <input type="submit" value="Все студенты" class="btn btn-link gray-button ">
                        </a>
                    </form>
                </li>
                <li>
                    <form action="/Action/GetAllByStatus" method="post">
                    <a href="#">
                        <input type="submit" value="Кандидаты" class="btn btn-link gray-button ">
                        <input type="hidden" name="status" value="Candidate"/>
                    </a>
                    </form>
                </li>
                <li>
                    <form action="/Action/GetAllByStatus" method="post">
                    <a href="#">
                        <input type="submit" value="Прошли деканат" class="btn btn-link gray-button ">
                        <input type="hidden" name="status" value="DeaneryPassed"/>
                    </a>
                    </form>
                </li>
                <li>
                    <form action="/Action/GetAllByStatus" method="post">
                    <a href="#">
                        <input type="submit" value="Не прошли деканат" class="btn btn-link gray-button ">
                        <input type="hidden" name="status" value="DeaneryNotPassed"/>
                    </a>
                    </form>
                </li>
                <li>
                    <form action="/Action/GetAllByStatus" method="post">
                    <a href="#">
                        <input type="submit" value="Прошли доктора" class="btn btn-link gray-button " >
                        <input type="hidden" name="status" value="BodyCheckPassed"/>
                    </a>
                    </form>
                </li>
                <li>
                    <form action="/Action/GetAllByStatus" method="post">
                    <a href="#">
                        <input type="submit" value="Не прошли доктора" class="btn btn-link gray-button ">
                        <input type="hidden" name="status" value="BodyCheckNotPassed"/>
                    </a>
                    </form>
                </li>
                <li>
                    <form action="/Action/GetAllByStatus" method="post">
                    <a href="#">
                        <input type="submit" value="Заселены" class="btn btn-link gray-button ">
                        <input type="hidden" name="status" value="Settled"/>
                    </a>
                    </form>
                </li>
                <li>
                    <form action="/Action/GetAllByStatus" method="post">
                    <a href="#">
                        <input type="submit" value="Не заселены" class="btn btn-link gray-button ">
                        <input type="hidden" name="status" value="NotSettled"/>
                    </a>
                    </form>
                </li>
                </form>
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
                <div class="col-xs-12">
                    <form action="/Action/FindByLastNameToDeanery" method="post">
                        <div class="input-group">
                         <span class="input-group-btn">
                              <input type="submit" value="Найти" class="btn btn-info" type="button">
                         </span>
                            <input type="text" name="lastNameInput" class="form-control" placeholder="Введите фамилию...">
                        </div><!-- /input-group -->
                    </form>
                </div><!-- /.col-lg-6 -->
            </div>    <!-- /.row -->

            <br>
     <!--       <form action="/Action/GetAllByStatus" method="post">-->
                <c:if test = "${students != null && students.size() != 0}">

                    <div class="table-responsive">
                        <table class="table table-hover" >
                            <tr>
                                <td><b>Имя</b></td>
                                <td><b>Отчество</b></td>
                                <td><b>Фамилия</b></td>
                                <td><b>Номер группы</b></td>
                                <td><b>Дата рождения</b></td>
                                <td><b>Статус</b></td>
                            </tr>
                            <c:forEach  var="data" items="${students}" >
                                <tr>
                                    <td>${data.getFirstName()}</td>
                                    <td>${data.getMidName()}</td>
                                    <td>${data.getLastName()}</td>
                                    <td>${data.getGroupNumber()}</td>
                                    <td>${data.getDateOfBirth().toString()}</td>
                                    <td>${data.getStudentStatus().toString()}</td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </c:if>
                <c:if test = "${students == null || students.size() == 0}">
                    <div class="col-lg-2 col-lg-offset-5">
                        <label class="label-success label">Студентов нет!</label>
                    </div>
                </c:if>
            </form>

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

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins)-->
  <!--  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>-->
    <!-- jQuery -->
    <script src="Scripts/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="Scripts/bootstrap.min.js"></script>
  <!--  <script src="Scripts/bootstrap.js"></script>-->

    <script type="text/javascript" src="Scripts/indexDeaneryUtils.js"></script>

    <!-- Menu Toggle Script -->
    <script>
        $("#menu-toggle").click(function(e) {
            e.preventDefault();
            $("#wrapper").toggleClass("toggled");
        });
    </script>

    </body>

</html>