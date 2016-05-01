<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="shortcut icon" href="../../img/icon.ico" type="image/x-icon">
    <link href="../../Styles/tmp.css" rel="stylesheet">
    <link href="../../Styles/bootstrap.css" rel="stylesheet">
    <link href="../../Styles/bootstrap.min.css" rel="stylesheet">
    <title>Распределение</title>
</head>
<body>
    <div class="main">
        <div class="container">
            <div class = "row">
                <div class="navbar navbar-inverse">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#menu">
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                            <span class="icon-bar"></span>
                        </button>
                    </div>
                    <div class="navbar-collapse collapse" id = "menu">
                        <ul class="nav navbar-nav ">
                            <li><a  href=/Action/ReadAllToDeanery>
                                <h4> <span class="glyphicon glyphicon-arrow-left"> Назад </span></h4>
                            </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>

            <form action="/Action/CheckStudentsByDeanery" method="post">

                <c:if test = "${students != null && students.size() != 0}">
                    <div class="table-responsive col-lg-6">
                        <table class="table table-hover" >
                            <tr>
                                <td><b>Имя</b></td>
                                <td><b>Отчество</b></td>
                                <td><b>Фамилия</b></td>
                                <td><b>Номер группы</b></td>
                                <td> <span class="glyphicon glyphicon-ok" style="color: green"></span></td>
                            </tr>
                            <c:forEach  var="data" items="${students}" >
                                <tr>
                                    <td>${data.getFirstName()}</td>
                                    <td>${data.getMidName()}</td>
                                    <td>${data.getLastName()}</td>
                                    <td>${data.getGroupNumber()}</td>
                                    <td>
                                        <div class="radio">
                                            <label><input type="radio" name ="student" value="${data.getStudentId()}"></label>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </c:if>

                <c:if test = "${students == null || students.size() == 0}">
                        <div class="col-lg-4 col-lg-offset-2">
                            <label class="label-info label">Кандидатов нет :)</label>
                        </div>
                    </c:if>


                <c:if test = "${rooms != null && rooms.size() != 0}">
                    <div class="table-responsive col-lg-6">
                        <table class="table table-hover" >
                            <tr>
                                <td><b>Общежитие</b></td>
                                <td><b>Блок</b></td>
                                <td><b>Комната</b></td>
                                <td><b>Всего мест</b></td>
                                <td><b>Свободно мест</b></td>
                                <td> <span class="glyphicon glyphicon-ok" style="color: green"></span></td>
                            </tr>
                            <c:forEach  var="data" items="${rooms}" >
                                <tr>
                                    <td>${data.getDormitoryNumber()}</td>
                                    <td>${data.getBlockNumber()}</td>
                                    <td>${data.getRoomNumber()}</td>
                                    <td>${data.getMaxPlacesCount()}</td>
                                    <td>${data.getFreePlacesCount()}</td>
                                    <td>
                                        <div class="radio">
                                            <label><input type="radio" name ="room" value="${data.getRoomId()}"></label>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </c:if>

                <c:if test = "${rooms == null || rooms.size() == 0}">
                    <div class="col-lg-4 col-lg-offset-8">
                        <label class="label-info label">Комнат нет :(</label>
                    </div>
                </c:if>

                <div class="col-lg-2 col-lg-offset-5">
                    <input type="submit" value="Сохранить" class="btn btn-success btn-block btn-lg">
                </div>
            </form>
        </div>
    </div>
</body>
</html>