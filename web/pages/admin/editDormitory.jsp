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
  <title>Редактировать общаги</title>
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
            <li><a  href=http://localhost:8080/pages/admin/indexAdmin.jsp>
              <h4> <span class="glyphicon glyphicon-arrow-left"> Назад </span></h4>
            </a>
            </li>
          </ul>
        </div>
      </div>
    </div>

    <div class="table-responsive col-lg-12">
        <c:if test = "${dormitories != null && dormitories.size() != 0}">
          <table class="table table-hover" >
            <tr>
              <td><b>Номер общежития</b></td>
              <td><b>Максимально  блоков</b></td>
              <td><b>Свободно блоков</b></td>
              <td> <span class="glyphicon glyphicon-remove" style="color: red"></span></td>
            </tr>
            <c:forEach  var="data" items="${dormitories}" >
              <tr>
                <td>${data.getDormitoryNumber()}</td>
                <td>${data.getMaxBlocksCount()}</td>
                <td>${data.getFreeBlocksCount()}</td>
                <td>
                  <form action="DeleteDormitory" method="post">
                    <input type="submit" value="Удалить">
                    <input type="hidden" name="dormitoryToDel" value="${data.getDormitoryId()}"/>
                  </form>
                </td>
              </tr>
            </c:forEach>
          </table>
        </c:if>

          <c:if test = "${dormitories == null || dormitories.size() == 0}">
            <div class="col-lg-2 col-lg-offset-4">
              <label class="label-info label">Общежитий нет :)</label>
            </div>
          </c:if>
      </div>

    <div class="col-lg-12">
      <hr class="colorgraph">
    </div>

    <form action="AddDormitory" method="post">
      <div class="col-lg-12">
      <div class="row">
        <div class="col-lg-4 col-lg-offset-4">
          <h2><strong>Добавить общежитие:</strong></h2>
        </div>
      </div>
      <div class="row">
        <div class="col-lg-4">
          <h4><strong>Номер общежития:</strong></h4>
        </div>
        <div class="col-lg-4">
          <input type="number" min ="0" maxlength="7" name="numberD" id="numberD" class="form-control" placeholder="Номер общежития" onkeyup="checkParams()" tabindex="1">
        </div>
        <div class="col-lg-4">
         <label class="has-error" id = "numberDError" style="color: red"></label>
        </div>
      </div>
      <div class="row ">
        <div class="col-lg-4">
          <h4><strong>Максимальное количество блоков:</strong></h4>
        </div>
        <div class="col-lg-4">
          <input type="number" min ="0" maxlength="7" name="maxBlock" id="maxBlock" class="form-control" placeholder="Максимальное количество блоков" onkeyup="checkParams()" tabindex="2">
        </div>
        <div class="col-lg-4">
          <label id = "blockError" style="color: red"></label>
        </div>
      </div>
      <div class="row ">
        <div class="col-lg-4">
          <h4><strong>Адрес общежития:</strong></h4>
        </div>
        <div class="col-lg-4">
          <input type="text" name="address" id="address" class="form-control" placeholder="Адрес общежития" onkeyup="checkParams()" tabindex="3">
        </div>
      </div>
      <br>
      <div class="row">
        <div class="col-lg-2 col-lg-offset-5">
           <input type="submit" id="newDormitory" value="Сохранить" class="btn btn-success btn-block " disabled>
        </div>
      </div>
    </div>
    </form>


  </div>
</div>
<script type="text/javascript" src="../../Scripts/formValidate.js"></script>
<script type="text/javascript" src="../../Scripts/jquery.js"></script>


</body>
</html>