<%@ page import="Model.Room" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table border="1" width="303">
    <tr>
        <td width="119"><b>ID</b></td>
        <td width="168"><b>?????????</b></td>
    </tr>
    <% ArrayList<Room> data= (ArrayList<Room>)request.getAttribute("rooms");
        for (int i = 0; i < data.size(); i++) {
    %>
    <tr>
        <td width="119"><%=data.get(i).getBlockId()%></td>
        <td width="168"><%=data.get(i).getFreePlacesCount()%></td>
        <td width="119"><%=data.get(i).getRoomId()%></td>
        <td width="168"><%=data.get(i).getRoomNumber()%></td>
        <td width="119"><%=data.get(i).getMaxPlacesCount()%></td>
    </tr>
    <%}%>

</table>
</body>
</html>