<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Студент</title>

    <link href="/Styles/bootstrap.min.css" rel="stylesheet">

    <link href="/Styles/template.css" rel="stylesheet">

    <script src="/Scripts/jquery.js"></script>

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
                                Тут имя студента
                                <ul class="dropdown-menu ">
                                    <li><form action="/Action/Logout" method="post">
                                        <input type="submit" value="Выход" class="btn btn-link btn-block btn-sm">
                                    </form></li>
                                </ul>
                            </span>
                    </li>
                    <li>
                        <a href=http://localhost:8080/Action/GetSettlers>Списки на заселение</a>
                    </li>
                    <li>
                        <a href=http://localhost:8080/docsPage.jsp>Документы</a>
                    </li>
                    <li>
                        <a href="#">Списки заселенных</a>
                    </li>
                    <li>
                        <a class="dropdown-link" href="#">Options</a>
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

                <div>
                    login: ${login}<br>
                    first name: ${first_name}<br>
                    mid name: ${mid_name}<br>
                    last name: ${last_name}<br>
                    birthday: ${birthday}<br>
                    group: ${group}
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