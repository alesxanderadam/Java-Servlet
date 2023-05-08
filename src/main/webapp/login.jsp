<%@ page import="Models.Users" %>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<%--
    + ! => use create variable: biến
    + <% %> => use controller logic code
    + <%= %> => print variable to screen
 --%>
<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>

<%!
    int count = 0;
    Users users = new Users();
%>

<%
//    int kq = (int) request.getAttribute("Result");
    users.setUsername("Le Tran Quang Huy");
    users.setPassword("123");
    count++;
%>

<%
    if(count % 2 == 0) {
%>

<p><%= count %></p>

<%
    }
    else
    {
%>
<p style="display: none;"><%= count %></p>
<% } %>
<%--<p><%= users.getUsername() %></p>--%>
<%--<br />--%>
<%--<p><%= users.getPassword()%></p>--%>

<b>${user.username}</b>
<br />
<b>${user.getPassword()}</b>
<%--<c:out value="${mess}" />--%>

<c:if test="${not empty list}">
    <c:forEach var="item" items="${list}">
        <c:out value="${item}"/>
    </c:forEach>
</c:if>

<div class="container">
    <div class="row mt-5">
        <div class="col-md-5 m-auto mt-5">
            <h3 class="text-center">ĐĂNG NHẬP HỆ THỐNG</h3>
            <div class="p-4 border mt-4">
                <% String contextPath = request.getContextPath();  %>
                <c:if test="${user.password == '123'}">
                  <p>Mật khẩu của bạn là ${user.getPassword()}</p>
                </c:if>
                <form action="<%=contextPath%>/login" method="post">
                    <div class="form-group">
                        <label>Email</label>
                        <input type="email" class="form-control" name="username" value="${email}" >
                    </div>
                    <div class="form-group">
                        <label>Mật khẩu</label>
                        <input type="password" class="form-control" name="password" value="${password}">
                    </div>
                    <div class="form-check">
                        <input type="checkbox" class="form-check-input" id="exampleCheck1" name="rememberMe">
                        <label class="form-check-label" for="exampleCheck1">Check me out</label>
                    </div>
                    <button type="submit" class="btn btn-primary mt-3">Đăng nhập</button>
                </form>
            </div>
        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
