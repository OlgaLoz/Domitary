<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Студент</title>

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
                                ${login}
                                <ul class="dropdown-menu ">
                                    <li><form action="/Action/Logout" method="post">
                                        <input type="submit" value="Выход" class="btn btn-link btn-block btn-sm">
                                    </form></li>
                                </ul>
                            </span>
                    </li>
                    <li>
                        <a href=documentsPage.jsp>Документы на заселение</a>
                    </li>
                    <li>
                        <hr>
                        <form action="/Action/Download" method="post">
                            <a href="#">
                                <input type="submit" value="Списки на заселение(csv)" class="btn btn-link gray-button ">
                                <input type="hidden" value="DeaneryPassed" name="student_status">
                                <input type="hidden" value="csv" name="doc_type">
                            </a>
                        </form>
                    </li>
                    <li>
                        <form action="/Action/Download" method="post">
                            <a href="#">
                                <input type="submit" value="Списки заселенных(csv)" class="btn btn-link gray-button ">
                                <input type="hidden" value="Settled" name="student_status">
                                <input type="hidden" value="csv" name="doc_type">
                            </a>
                        </form>
                    </li>
                </ul>
            </div>
            <!-- /#sidebar-wrapper -->

            <!-- Page Content -->
            <div id="page-content-wrapper">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-lg-2">
                            <a href="#menu-toggle" class="btn btn-info" id="menu-toggle">
                                <span class="glyphicon glyphicon-plus"></span>
                            </a>
                        </div>
                        <div class="col-lg-10">
                            <div class="row"> <button class="btn btn-info col-lg-6" data-toggle="collapse" data-target="#hide-me">Информация обо мне</button></div>

                            <div id="hide-me" class="collapse in">
                                <div class="row"></div>
                                <div class="row blue">
                                    <div class="col-lg-2">
                                        <h3> Текущий статус:</h3>
                                    </div>
                                    <div class="col-lg-4">
                                        <h2 id="status"> ${student_status}</h2>
                                        <input type="hidden" id="hideStatus" value="${student_status}" />
                                    </div>
                                </div>
                                <div class="row blue">
                                    <div class="col-lg-2">
                                        <h3>ФИО:</h3>
                                    </div>
                                    <div class="col-lg-10">
                                        <h3> ${first_name} ${mid_name} ${last_name}</h3>
                                    </div>
                                </div>
                                <div class="row blue">
                                    <div class="col-lg-2">
                                        <h3> Дата рождения:</h3>
                                    </div>
                                    <div class="col-lg-2">
                                        <h3> ${birthday}</h3>
                                    </div>
                                </div>
                                <div class="row blue">
                                    <div class="col-lg-2">
                                        <h3> Номер группы:</h3>
                                    </div>
                                    <div class="col-lg-2">
                                        <h3> ${group}</h3>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

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
        <script type="text/javascript" src="/Scripts/jquery.js"></script>
        <script type="text/javascript" src="/Scripts/statusHelper.js"></script>
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