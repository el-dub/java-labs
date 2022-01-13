<%--
  Created by IntelliJ IDEA.
  User: elizaveta
  Date: 12/30/2021
  Time: 3:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>

<body>
<h2>Login</h2>
<div>
  <form autocomplete="off" action="/login" method="post" class="form-horizontal" role="form">
    <div class="form-group">
      <div class="col-sm-9" align="center">

        <div class="form-group">
          <div class="col-sm-9">
            <input type="text" placeholder="Username" name="username"
                   class="form-control" minlength="1" maxlength="30"/>
          </div>
        </div>

        <div class="form-group">
          <div class="col-sm-9">
            <input type="text" placeholder="Password" name="password"
                   class="form-control" minlength="1" maxlength="30"/>
          </div>
        </div>


        <div class="form-group">
          <div class="col-sm-9">
            <button type="submit" class="btn btn-primary btn-block">Login</button>
          </div>
        </div>
      </div>
    </div>
  </form>
</div>
</body>
</html>