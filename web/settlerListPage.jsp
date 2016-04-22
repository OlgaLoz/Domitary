<%@ page import="Model.Room" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link href="/Styles/tmp.css" rel="stylesheet">
    <link href="/Styles/bootstrap.css" rel="stylesheet">
    <link href="/Styles/bootstrap.min.css" rel="stylesheet">
<title>Список</title>
</head>
    <body>
        <table class="table table-hover" >
            <tr>
                <td><b>ID</b></td>
                <td><b>?????????</b></td>
            </tr>
            <% ArrayList<Room> data= (ArrayList<Room>)request.getAttribute("rooms");
                for (int i = 0; i < data.size(); i++) {
            %>
            <tr>
                <td><%=data.get(i).getBlockId()%></td>
                <td><%=data.get(i).getFreePlacesCount()%></td>
                <td><%=data.get(i).getRoomId()%></td>
                <td><%=data.get(i).getRoomNumber()%></td>
                <td><%=data.get(i).getMaxPlacesCount()%></td>
            </tr>
            <%}%>

        </table>
    </body>
</html>