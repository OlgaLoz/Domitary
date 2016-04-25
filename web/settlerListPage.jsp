<%@ page import="java.util.ArrayList" %>
<%@ page import="Model.Student" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="shortcut icon" href="img/icon.ico" type="image/x-icon">
    <link href="Styles/tmp.css" rel="stylesheet">
    <link href="Styles/bootstrap.css" rel="stylesheet">
    <link href="Styles/bootstrap.min.css" rel="stylesheet">
<title>Список</title>
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
                            <li><a  href="#" data-toggle="modal"  data-target="#registerModal">
                                <h4> <span class="glyphicon glyphicon-arrow-left"> Назад </span></h4>
                            </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>

        <div class="table-responsive">
            <h1 class="header">Список студентов</h1>
            <hr class="colorgraph">

            <table class="table table-hover" >

                <tr>
                    <td><b>Имя</b></td>
                    <td><b>Отчество</b></td>
                    <td><b>Фамилия</b></td>
                    <td><b>Номер группы</b></td>
                    <td><b>Статус</b></td>
                </tr>
                <% ArrayList<Student> data= (ArrayList<Student>)request.getAttribute("students");
                    for (int i = 0; i < data.size(); i++) {
                %>
                <tr>
                    <td><%=data.get(i).getFirstName()%></td>
                    <td><%=data.get(i).getMidName()%></td>
                    <td><%=data.get(i).getLastName()%></td>
                    <td><%=data.get(i).getGroupNumber()%></td>
                    <td><%=data.get(i).getStudentStatus().toString()%></td>
                </tr>
                <%}%>

            </table>
            <hr class="colorgraph">
            </div>
        </div>
    </div>
    </body>
</html>