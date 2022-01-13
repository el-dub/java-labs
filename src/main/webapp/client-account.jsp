<%--
  Created by IntelliJ IDEA.
  User: elizaveta
  Date: 12/30/2021
  Time: 2:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Особистий кабінет</title>
</head>
<body>
    <h1>Accounts</h1>
    <div style="padding: 30px">
        <table>
            <thead>
            <tr>
                <th>Account Number</th>
                <th>Money amount</th>
                <th>Locked</th>
                <th>Name</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${account.account_number}</td>
                <td>${account.money_amount}</td>
                <td>${account.locked}</td>
                <td>${account.account_name}</td>
            </tr>
            <tbody>
        </table>
    </div>
    <h1>Платежі</h1>
    <div style="padding: 30px">
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Username</th>
                <th>Role</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>${account.account_number}</td>
                <td>${account.money_amount}</td>
                <td>${account.locked}</td>
                <td>${account.account_name}</td>
            </tr>
            <tbody>
        </table>
    </div>
</body>
</html>
