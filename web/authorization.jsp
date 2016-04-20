<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<link rel="shortcut icon" href="/img/icon.ico" type="image/x-icon">

	<link href="/Styles/bootstrap.css" rel="stylesheet">
	<link href="/Styles/indexStyle.css" rel="stylesheet">

	<title>Общежитие</title>
</head>

<body>

<div class="container">
	<div class = "row">
		<div class="navbar navbar-inverse">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#menu">
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
				</div>
				<div class="navbar-collapse collapse" id = "menu">
					<ul class="nav navbar-nav ">
						<li><a  href="#" data-toggle="modal"  data-target="#registerModal"><h5>Регистрация</h5></a></li>
						<li><a  href="#" data-toggle="modal"  data-target="#autorizModal"><h5>Авторизация</h5></a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>

	<div class = "bg">
		<div class="first">
			<div class="row">
				<div class =" col-xs-12 content">
					<p class="lead">
						В состав студенческого городка БГУИР входят четыре комфортабельных общежития на 3100 мест,
						расположенных в центре города, в 30 минутах ходьбы от учебных корпусов университета.
						Рядом находятся станции метро «Академия наук», «площадь Якуба Колоса», Комаровский рынок,
						крупные торговые и развлекательные центры. Общежите N4 расположено в Студенческой деревне
						(станция метро «Петровщина»). В общежитиях N1, N2 и N4 работают столовые, в которых можно вкусно
						и недорого питаться. В общежитиях также располагаются тренажерные залы, теннисные комнаты, есть
						возможность пользования стиральными машинами. Студенты проживают в комнатах по 2, 3 и 4 человека.
					</p>
				</div>
			</div>
		</div>

		<div class = "row">

		<div class="col-xs-12  content">

			<div class="col-xs-3 col-sm-3 col-md-3 content" >
				<button class="btn btn-info btn-block" data-toggle="collapse" data-target="#sp1">Общежитие № 1</button>
				<div class="collapse in" id="sp1">
					<div class="col-xs-12 content">
						<img class ="img-responsive" src="img/dorm1.jpg" alt="Общежитие № 1">
						<p class="lead">
							<strong>Адрес:</strong>
							ул. Я. Колоса, 28<br>
							Вахта тел. 292-31-95, 293-21-42<br>
							<strong> Заведующая общежитием</strong><br>
							Наумова Светлана Леонидовна<br>
							рабочий телефон 293-21-48<br>
							рабочее место ком.115
						</p>
					</div>
				</div>
			</div>

			<div class="col-xs-3 col-xs-3 col-sm-3 col-md-3 content" >
				<button class="btn btn-info btn-block" data-toggle="collapse" data-target="#sp2">Общежитие № 2</button>
				<div  class="collapse in" id="sp2">
					<div class="col-xs-12 content">
						<img class ="img-responsive" src="img/dorm2.jpg" alt="Общежитие № 2">
						<p class="lead">
							<strong>Адрес:</strong>
							ул. Л. Беды, 4 <br>
							Вахта тел. 331-38-27, 293-22-43 <br>
							<strong> Заведующая общежитием</strong><br>
							Медведева Ирина Леонидовна<br>
							рабочий телефон 290-99-26<br>
							рабочее место ком.40
						</p>
					</div>
				</div>
			</div>

			<div class="col-xs-3 col-xs-3 col-sm-3 col-md-3 content">
				<button class="btn btn-info btn-block " data-toggle="collapse" data-target="#sp3">Общежитие № 3</button>
				<div class="collapse in" id="sp3">
					<div class="col-xs-12 content">
						<img class ="img-responsive" src="img/dorm3.jpg" alt="Общежитие № 3">
						<p class="lead">
							<strong>Адрес:</strong>
							ул. Л. Беды, 2б <br>
							Вахта тел. 290-99-27 <br>
							<strong> Заведующая общежитием</strong><br>
							Василевская Светлана Петровна<br>
							рабочий телефон 293-22-40<br>
							рабочее место ком.100
						</p>
					</div>
				</div>
			</div>

			<div class="col-xs-3 col-xs-3 col-sm-3 col-md-3 content">
				<button class="btn btn-info btn-block" data-toggle="collapse" data-target="#sp4">Общежитие № 4</button>
				<div class="collapse in" id="sp4">
					<div class="col-xs-12 content">
						<img class ="img-responsive" src="img/dorm4.jpg" alt="Общежитие № 4">
						<p class="lead">
							<strong>Адрес:</strong>
							пр. Дзержинского, 95 <br>
							Вахта тел. 272-73-53<br>
							<strong> Заведующая общежитием</strong><br>
							Денисова Светлана Михайловна<br>
							рабочий телефон 277-2763<br>
							рабочее место ком.211
						</p>
					</div>
				</div>
			</div>
		</div>

		<div class="last">
			<div class =" col-xs-12 content">
				<p class="lead">
					<strong>Право на заселение в общежитие в первую очередь имеют:</strong>
					<ol class="lead">
						<li>Студенты-инвалиды III группы.
						<li>Студенты из многодетных семей (3 и более несовершеннолетних детей или детей, обучающихся вузах, ссузах).</li>
						<li>Студенты из семей инвалидов войны I и II групп и приравненных к ним (отец или мать студента является инвалидом).</li>
						<li>Молодые семьи (семьи, в которых оба члена семьи - студенты и (или) магистранты БГУИР, студент и аспирант БГУИР и т.п.).</li>
						<li>Студенты, у которых родной брат или сестра являются студентом БГУИР.</li>
						<li>Студенты, семьи которых пострадали (потеряли жилье и т.п.) в результате стихийных бедствий, социальных, техногенных и др. катастроф.</li>
						<li>Студенты, которые выбыли из общежития в академический отпуск по состоянию здоровья, и после восстановления вернулись в БГУИР.</li>
						<li>Студенты с более высоким средним баллом или суммой баллов, набранных на вступительных экзаменах (для студентов первого курса).</li>
						<li>Студенты, имеющие высокие показатели в учебе, успехи в научно-исследовательской деятельности, или активно участвующие в общественной, культурно-массовой и спортивной работе, а также победители республиканских и международных конкурсов и олимпиад.</li>
					</ol><br>

					<p class="lead">
						<strong>Оплата за общежитие</strong>
					</p>

					<p class="lead">
						Сумма оплаты за общежитие у студентов-бюджетников вычитается из стипендии (по их письменным заявлениям).
						Студенты, обучающиеся на платной основе, и студенты, потерявшие право на получение стипендии, должны до
						25-ого числа каждого месяца оплачивать и предоставлять квитанцию об оплате воспитателю общежития.
						За неоплату 26-ого числа сведения подаются в деканат.
					</p>

					<p class="lead">
						<strong>Сумма оплаты:</strong>
					</p>

					<ul class="lead">
						<li>0,8 базовые величины - общежитие N1</li>
						<li>0,8 базовые величины - общежитие N2</li>
						<li>1,2 базовые величины - общежитие N3</li>
						<li>1,2 базовые величины - общежитие N4</li>
					</ul>
				</div>
			</div>

			<div class="col-xs-12 footer">
			<hr class="colorgraph">
			<div class="col-xs-4 col-xs-offset-5 ">
				<p class="lead">&copy; By MON </p>
			</div>
		</div>

		</div>
	</div>
</div>

<form action="/Action/Authorization" method="post">
	<div class="modal fade" id="autorizModal" tabindex="-1" role="dialog" class="col-xs-12 col-sm-12 col-md-8">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header"><button class="close" type="button" data-dismiss="modal">x</button>
					<h4 class="modal-title" >Войти</h4>
				</div>
				<div class="modal-body">
					<hr class="colorgraph">

					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12">
							<div class="form-group">
								<input type="text" name="login" class="form-control input-lg" placeholder="Login" tabindex="1">
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-xs-12 col-sm-12 col-md-12">
							<div class="form-group">
								<input type="password" name="password"  class="form-control input-lg" placeholder="Password" tabindex="5">
							</div>
						</div>
					</div>

					<hr class="colorgraph">
				</div>

				<div class="modal-footer">
					<input type="submit" value="Готово" class="btn btn-primary btn-block btn-lg">
				</div>
			</div>
		</div>
	</div>
</form>

<form action="/Action/Registration" method="post">
	<div class="modal fade" id="registerModal" tabindex="-1" role="dialog" class="col-xs-12 col-sm-12 col-md-8">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header"><button class="close" type="button" data-dismiss="modal">x</button>
					<h4 class="modal-title" id="myModalLabel">Регистрация</h4>
				</div>

				<div class="modal-body">

					<hr class="colorgraph">
					<div class="row">
						<div class="col-xs-4 col-sm-4 col-md-4">
							<div class="form-group">
								<input type="text" name="first_name" id="first_name" class="form-control input-lg" placeholder="First Name" tabindex="1">
							</div>
						</div>

						<div class="col-xs-4 col-sm-4 col-md-4">
							<div class="form-group">
								<input type="text" name="mid_name" id="mid_name" class="form-control input-lg" placeholder="Mid Name" tabindex="2">
							</div>
						</div>

						<div class="col-xs-4 col-sm-4 col-md-4">
							<div class="form-group">
								<input type="text" name="last_name" id="last_name" class="form-control input-lg" placeholder="Last Name" tabindex="3">
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-xs-6 col-sm-6 col-md-6">
							<div class="form-group">
								<input type="text" name="birthday" id="birthday" class="form-control input-lg" placeholder="Birthday" tabindex="1">
							</div>
						</div>

						<div class="col-xs-6 col-sm-6 col-md-6">
							<div class="form-group">
								<input type="text" name="group" id="group" class="form-control input-lg" placeholder="Group" tabindex="2">
							</div>
						</div>
					</div>

					<div class="form-group">
						<input type="text" name="display_name" id="display_name" class="form-control input-lg" placeholder="Display Name" tabindex="3">
					</div>

					<div class="row">
						<div class="col-xs-6 col-sm-6 col-md-6">
							<div class="form-group">
								<input type="password" name="password" id="password" class="form-control input-lg" placeholder="Password" tabindex="5">
							</div>
						</div>

						<div class="col-xs-6 col-sm-6 col-md-6">
							<div class="form-group">
								<input type="password" name="password_confirmation" id="password_confirmation" class="form-control input-lg" placeholder="Confirm Password" tabindex="6">
							</div>
						</div>
					</div>

					<hr class="colorgraph">
				</div>

				<div class="modal-footer">
					<input type="submit" value="Готово" class="btn btn-primary btn-block btn-lg">
				</div>

			</div>
		</div>
	</div>
</form>

	<h3><a href="http://localhost:8080/indexSettler.jsp">Student</a></h3>
	<h3><a href="http://localhost:8080/indexDeaneryWorker.jsp">Deanery Worker</a></h3>
	<h3><a href="http://localhost:8080/indexGorverner.jsp">Gorverner</a></h3>
	<h3><a href="http://localhost:8080/indexDoctor.jsp">Doctor</a></h3>

		<form action="Authorization" method="post">
			<input type="submit" value="test"/>
		</form>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="Scripts/bootstrap.js"></script>

	</body>
</html>

