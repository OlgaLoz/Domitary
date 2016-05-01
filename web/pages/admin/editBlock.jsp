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
  <title>Редактировать блоки</title>
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

    <div class="table-responsive col-lg-5">
      <c:if test = "${dormitories != null && dormitories.size() != 0}">
        <table class="table table-hover" >
          <tr>
            <td><b>Номер общежития</b></td>
            <td><b>Максимально  блоков</b></td>
            <td><b>Свободно блоков</b></td>
            <td> <span class="glyphicon glyphicon-eye-open" style="color: #31b0d5"></span></td>
          </tr>
          <c:forEach  var="data" items="${dormitories}" >
            <tr>
              <td>${data.getDormitoryNumber()}</td>
              <td>${data.getMaxBlocksCount()}</td>
              <td>${data.getFreeBlocksCount()}</td>
              <td>
                <form action="/Action/GetBlocks" method="post">
                  <input type="submit" value="Блоки">
                  <input type="hidden" name="dormitoryId" value="${data.getDormitoryId()}"/>
                </form>
              </td>
            </tr>
          </c:forEach>
        </table>
      </c:if>
    </div>

    <c:if test = "${dormitories == null || dormitories.size() == 0}">
      <div class="col-lg-3 col-lg-offset-2">
        <label class="label-info label">Общежитий нет :)</label>
      </div>
    </c:if>


    <c:if test = "${blocks != null && blocks.size() != 0}">
      <div class="table-responsive col-lg-7">
        <table class="table table-hover" >
          <tr>
            <td><b>Номер блока</b></td>
            <td><b><p>Комната A</p> Мест всего/свободно</b></td>
            <td><b><p>Комната Б</p> Мест всего/свободно</b></td>
            <td> <span class="glyphicon glyphicon-remove" style="color: red"></span></td>
          </tr>
          <c:forEach  var="data" items="${blocks}" >
            <tr>
              <td>${data.getBlockNumber()}</td>
              <c:forEach  var="roomData" items="${data.getRooms()}" >
                <td>${roomData.getMaxPlacesCount()}/${roomData.getFreePlacesCount()}</td>
              </c:forEach>
              <td>
                <form action="/Action/DeleteBlock" method="post">
                  <input type="submit" value="Удалить">
                  <input type="hidden" name="blockToDel" value="${data.getBlockId()}"/>
                </form>
              </td>
            </tr>
          </c:forEach>
        </table>
      </div>
    </c:if>

    <c:if test = "${blocks == null || blocks.size() == 0}">
      <div class="col-lg-4 col-lg-offset-8">
        <label class="label-info label">Блоков нет :)</label>
      </div>
    </c:if>

    <div class="col-lg-12">
      <hr class="colorgraph">
    </div>

    <form action="/Action/AddBlock" method="post">
      <div class="col-lg-12">
        <div class="row">
          <div class="col-lg-4 col-lg-offset-4">
            <h2><strong>Добавить блок:</strong></h2>
          </div>
        </div>
        <div class="row">
          <div class="col-lg-4">
            <h4><strong>Номер общежития:</strong></h4>
          </div>
          <div class="col-lg-4">
            <input type="number" min ="0" maxlength="7" name="numberD" id="numberD"
                   class="form-control" placeholder="Номер общежития" onkeyup="checkBlock()" tabindex="1">
          </div>
          <div class="col-lg-4">
            <label id = "dormError" name="dormError" style="color: red"><%out.println(session.getAttribute("dormError")); session.setAttribute("dormError", "");%></label>
          </div>
        </div>
        <div class="row ">
          <div class="col-lg-4">
            <h4><strong>Номер блока:</strong></h4>
          </div>
          <div class="col-lg-4">
            <input type="number" maxlength="7" min ="0" name="numberB" id="numberB"
                   class="form-control" placeholder="Номер блока" onkeyup="checkBlock()" tabindex="2">
          </div>
          <div class="col-lg-4">
            <label id = "blockError" name = "blockError" style="color: red"><%session.getAttribute("blockError"); session.setAttribute("blockError", "");%></label>
          </div>
        </div>
        <br>
        <div class="row">
          <div class="col-lg-2 col-lg-offset-5">
            <input type="submit" id="newBlock" value="Сохранить" class="btn btn-success btn-block " disabled>
          </div>
        </div>
      </div>
    </form>

  </div>
</div>
<script type="text/javascript" src="../../Scripts/validateBlock.js"></script>
<script type="text/javascript" src="../../Scripts/jquery.js"></script>


</body>
</html>