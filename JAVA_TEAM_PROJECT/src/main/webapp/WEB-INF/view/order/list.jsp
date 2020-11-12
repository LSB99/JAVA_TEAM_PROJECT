<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" type="text/css" href="/common.css" />
  <style>
    a.btn { float: right; margin: -20px 0 5px 0; }
    td:nth-child(1) { text-align: center; }
  </style>
</head>
<body>
<div class="container">
  <h1>메뉴</h1>
  <table class="list">
    <thead>
      <tr>
        <th>id</th>
        <th>이름</th>
        <th>가격</th>
        <th>수량</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="order" items="${ orders }">
        <tr data-url="edit?id=${ order.orderId }">
          <td>${ order.orderId }</td>
          <td>${ order.foodName }</td>
          <td>${ order.amount }</td>
          <td>${ order.price }</td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
</div>
</body>
</html>
