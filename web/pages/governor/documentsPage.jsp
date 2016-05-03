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
    <title>Документы</title>
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
                        <li><a  href=SearchByStatusByGovernor>
                            <h4> <span class="glyphicon glyphicon-arrow-left"> Назад </span></h4>
                        </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <br>
            <c:if test = "${students != null && students.size() != 0}">
                <div class="table-responsive col-lg-12">
                    <table class="table table-hover" >
                        <tr>
                            <td><b>Имя</b></td>
                            <td><b>Отчество</b></td>
                            <td><b>Фамилия</b></td>
                            <td><b>Номер группы</b></td>
                            <td><b>Заявление</b></td>
                            <td><b>Договор</b></td>
                            <c:if test = "${status == 'Settled'}">
                                <td><b>Пропуск</b></td>
                            </c:if>
                        </tr>
                        <c:forEach  var="data" items="${students}" >
                            <tr>
                                <td>${data.getFirstName()}</td>
                                <td>${data.getMidName()}</td>
                                <td>${data.getLastName()}</td>
                                <td>${data.getGroupNumber()}</td>
                                <td>
                                    <form action="DownloadPdf" method="post">
                                        <a href="#">
                                            <input type="submit" value="скачать(pdf)" class="btn btn-info ">
                                            <input type="hidden" value="${data.getStudentId()}" name="student_ID">
                                            <input type="hidden" value="statement" name="doc_type">
                                        </a>
                                    </form>
                                </td>
                                <td>
                                    <form action="DownloadPdf" method="post">
                                        <a href="#">
                                            <input type="submit" value="скачать(pdf)" class="btn btn-info ">
                                            <input type="hidden" value="${data.getStudentId()}" name="student_ID"/>
                                            <input type="hidden" value="contract" name="doc_type">
                                        </a>
                                    </form>
                                </td>
                                <c:if test = "${status == 'Settled'}">
                                    <td>
                                        <form action="DownloadPdf" method="post">
                                            <a href="#">
                                                <input type="submit" value="скачать(pdf)" class="btn btn-info ">
                                                <input type="hidden" value="${data.getStudentId()}" name="student_ID">
                                                <input type="hidden" value="order" name="doc_type">
                                            </a>
                                        </form>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </c:if>

            <c:if test = "${students == null || students.size() == 0}">
                <div class="col-lg-4 col-lg-offset-2">
                    <label class="label-info label">Студентов нет :)</label>
                </div>
            </c:if>

    </div>
</div>
</body>
</html>