<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Администратор</title>

  <link href="../../Styles/bootstrap.min.css" rel="stylesheet">
  <link href="../../Styles/template.css" rel="stylesheet">

  <link rel="shortcut icon" href="../../img/icon.ico" type="image/x-icon">
  <!--[if lt IE 9]>
  <script type="text/javascript" src="/Scripts/html5shiv.js"></script>
  <script type="text/javascript" src="/Scripts/respond.min.js"></script>
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
          Администратор
          <ul class="dropdown-menu ">
            <li><form action="Logout" method="post">
              <input type="submit" value="Выход" class="btn btn-link btn-block btn-sm ">
            </form></li>
          </ul>
        </span>
      </li>
      <li>
        <a href="DeleteDormitory">Общежития</a>
      </li>
      <li>
        <a href="GetBlocks">Блоки</a>
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
      <div class="col-lg-offset-2 col-md-offset-2 col-sm-offset-2 col-lg-5">
       <h3 style="color: #28a4c9">
         Вы вошли как администратор приложения! В Ваши обязанности входит следить за актуальной информацией для приложения.
         А именно о жилых и нежилых общежитиях и блоках! Просьба своевременно выполнять свою работу!
       </h3>
        <h2 style="color: #46b8da">
          Спасибо, админушка :)
        </h2>
      </div><!-- /.col-lg-6 -->

      <div class="col-lg-5">
      <div class="img-responsive">
        <img src="../../img/adminCat.jpg" alt="">
      </div>
      </div>
    </div>

    <div class="row">
      <div class="col-xs-12 footer">
        <hr class="colorgraph">
        <div class="col-xs-4 col-xs-offset-5 ">
          <p class="lead">&copy; By MON </p>
        </div>
      </div>
    </div>
  </div>
  <!-- /#page-content-wrapper -->
</div>
<!-- /#wrapper -->

<!-- jQuery -->
<script type="text/javascript" src="/Scripts/jquery.js"></script>
<!-- Bootstrap Core JavaScript -->
<script type="text/javascript" src="/Scripts/bootstrap.min.js"></script>
<!-- Menu Toggle Script -->
<script type="text/javascript">
  $("#menu-toggle").click(function(e) {
    e.preventDefault();
    $("#wrapper").toggleClass("toggled");
  });
</script>

</body>
</html>