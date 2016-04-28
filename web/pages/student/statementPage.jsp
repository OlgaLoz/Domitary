<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="shortcut icon" href="../../img/icon.ico" type="image/x-icon">
    <link href="../../Styles/tmp.css" rel="stylesheet">
    <link href="../../Styles/bootstrap.css" rel="stylesheet">
    <link href="../../Styles/bootstrap.min.css" rel="stylesheet">
    <title>Заявление</title>
</head>
<body>
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
                        <li><a  href="indexStudent.jsp">
                            <h4> <span class="glyphicon glyphicon-arrow-left"> Назад </span></h4>
                        </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>

        <div class="lead">
            <div class="text-center">
                <h1>
                    <strong>Заявление на заселение</strong>
                </h1>
                <hr class="colorgraph">
            </div>

            <form class="form-horizontal" action="/Action/Statement" method="post">
                <div class="col-lg-7 col-lg-offset-5">
                    <div class="form-group">
                        <div class="col-xs-2">Декану </div>
                        <div class="col-xs-10">
                            <input type="text" name="dean" id="dean" required class="form-control input-lg" placeholder="Фамилия И.О. декана" tabindex="1">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-4">Студента(тки) гр. </div>
                        <div class="col-xs-8">
                            <input type="text" name="group" id="group" required class="form-control input-lg" placeholder="Группа" tabindex="2">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-2">Ф.И.О. </div>
                        <div class="col-xs-10">
                            <input type="text" name="lastname" id="lastname" required class="form-control input-lg" placeholder="Фамилия" tabindex="3">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-6">
                            <input type="text" name="firstname" id="firstname" required class="form-control input-lg" placeholder="Имя" tabindex="4">
                        </div>
                        <div class="col-xs-6">
                            <input type="text" name="midname" id="midname" class="form-control input-lg" placeholder="Отчество" tabindex="5">
                        </div>
                    </div>
                    <br/>
                </div>

                <div class ="text-center">
                    <h2>Заявление</h2>
                    <br/>
                </div>

                <div class="col-xs-12">
                    <div class="form-group">
                        <div class="col-xs-offset-1 col-xs-11">
                            Прошу предоставить койко-место в студенческом общежитии БГУиР.
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-2">Проживаю в </div>
                        <div class="col-xs-8">
                            <input type="text" name="region" id="region" required class="form-control input-lg" placeholder="Область" tabindex="6">
                        </div>
                        <div class="col-xs-2"> области</div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-1">город </div>
                        <div class="col-xs-2">
                            <input type="text" name="city" id="city" required class="form-control input-lg" placeholder="Город" tabindex="7">
                        </div>
                        <div class="col-xs-1">улица </div>
                        <div class="col-xs-2">
                            <input type="text" name="street" id="street" required class="form-control input-lg" placeholder="Улица" tabindex="8">
                        </div>
                        <div class="col-xs-1">дом </div>
                        <div class="col-xs-2">
                            <input type="text" name="house" id="house" required class="form-control input-lg" placeholder="Дом" tabindex="9">
                        </div>
                        <div class="col-xs-1">квартира </div>
                        <div class="col-xs-2">
                            <input type="text" name="flat" id="flat" required class="form-control input-lg" placeholder="Квартира" tabindex="10">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-3">Форма обучения: </div>
                        <div class="col-xs-6">
                            <input type="text" name="training" id="training" required class="form-control input-lg" placeholder="Бюджетная/Платная" tabindex="11">
                        </div>
                        <div class="col-xs-3"> (документы прилагаются)</div>
                    </div>
                    <br/>
                    <div class="form-group">
                        <div class ="col-xs-6">
                            <div class="form-group">
                                <div class="col-xs-5">Мобильный телефон </div>
                                <div class="col-xs-7">
                                    <input type="text" name="mobile_phone" id="mobile_phone" required class="form-control input-lg" placeholder="(+XXX XX) XXX-XX-XX" tabindex="12">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-5">Домашний телефон </div>
                                <div class="col-xs-7">
                                    <input type="text" name="home_phone" id="home_phone" required class="form-control input-lg" placeholder="XXX-XX-XX" tabindex="13">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-12">Ф.И.О.(родителей) </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-12">
                                    <input type="text" name="mother" id="mother" required class="form-control input-lg" placeholder="Ф.И.О. матери" tabindex="14">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-xs-12">
                                    <input type="text" name="father" id="father" required class="form-control input-lg" placeholder="Ф.И.О. отца" tabindex="15">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class ="col-lg-4 col-lg-offset-8">
                            <div class="form-group">
                                <div class="col-xs-3">Дата </div>
                                <div class="col-xs-9">
                                    <input type="text" name="filling_date" id="filling_date" required class="form-control input-lg" placeholder="дд.мм.гггг" tabindex="16">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="text-center">
                    <input type="submit" id="apply" value="Подать заявление" class="btn btn-primary btn-lg">
                </div>
            </form>
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

    <input type="hidden" id="studentStatus" value="${student_status}">
    <script type="text/javascript" src="/Scripts/jquery.js"></script>
    <script type="text/javascript">
        $(document).ready(function($) {
            if ($('#studentStatus').val() != 'Default') {
                $('#apply').hide();
            }
        });
    </script>

</body>
</html>