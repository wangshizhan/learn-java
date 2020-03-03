<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 20/01/2020
  Time: 5:07 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>用户注册</h1>
<hr>
<form name="regForm" action="customHttpServlet/registerServlet" method="post" >
    <table border="0" width="800" cellspacing="0" cellpadding="0">
        <tr>
            <td class="lalel">用户名：</td>
            <td class="controler"><label>
                <input type="text" name="username" />
            </label></td>
        </tr>
        <tr>
            <td class="label">密码：</td>
            <td class="controler"><label>
                <input type="password" name="mypassword" >
            </label></td>

        </tr>
        <tr>
            <td class="label">确认密码：</td>
            <td class="controler">
                <label>
                    <input type="password" name="confirmpass" >
                </label>
            </td>

        </tr>
        <tr>
            <td class="label">电子邮箱：</td>
            <td class="controler">
                <label>
                    <input type="text" name="email" >
                </label>
            </td>

        </tr>
        <tr>
            <td class="label">性别：</td>
            <td class="controler">
                <label>
                    <input type="radio" name="gender" checked="checked" value="Male">
                </label>男
                <label>
                    <input type="radio" name="gender" value="Female">
                </label>女
            </td>
        </tr>
        <tr>
            <td class="label">出生日期：</td>
            <td class="controler">
                <label for="control_date"></label><input name="birthday" type="text" id="control_date" size="10"
                                                         maxlength="10" onclick="new Calendar().show(this);" readonly="readonly" />
            </td>
        </tr>
        <tr>
            <td class="label">爱好：</td>
            <td class="controler">
                <label>
                    <input type="checkbox" name="favorite" value="nba">
                </label> NBA
                <label>
                    <input type="checkbox" name="favorite" value="music">
                </label> 音乐
                <label>
                    <input type="checkbox" name="favorite" value="movie">
                </label> 电影
                <label>
                    <input type="checkbox" name="favorite" value="internet">
                </label> 上网
            </td>
        </tr>
        <tr>
            <td class="label">自我介绍：</td>
            <td class="controler">
                <label>
                    <textarea name="introduce" rows="10" cols="40"></textarea>
                </label>
            </td>
        </tr>
        <tr>
            <td class="label">接受协议：</td>
            <td class="controler">
                <label>
                    <input type="checkbox" name="isAccept" value="true">
                </label>是否接受霸王条款
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="注册"/>
                <input type="reset" value="取消"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
