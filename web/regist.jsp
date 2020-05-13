<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>新闻管理系统</title>
    <style>
        .td_left {
            padding-top: 50px;
            width: 100px;
            text-align: right;
            height: 45px;
        }

        .td_right {
            /*    本身就是表格，就是内边距*/
            padding-left: 50px;
            padding-top: 50px;

        }

        #uid, #uemail, #uname, #upassword {
            width: 251px;
            height: 33px;
            border: 1px solid;
            /*    边框圆角*/
            border-radius: 5px;
            padding-left: 10px;
        }

        .rg_layout {
            width: 1000px;
            height: 700px;
            background: white;
            border: 8px solid #EEEEEE;
            /*div居中*/
            margin: auto;
            margin-top: 20px;

        }

        .rg_left {

            float: left;
            margin-top: 15px;
        }

        .rg_center {
            margin-left: 200px;
            margin-top: 10px;
            /*border: 1px solid #FF0000;*/
            float: left;
            width: 450px;
        }

        .rg_right {
            margin: 15px;

            float: right;
        }

        * {
            margin: 0px;
            padding: 0px;
            box-sizing: border-box;
        }

        .rg_left > p:first-child {
            color: red;
            font-size: 20px;

        }

        .rg_left > p:last-child {
            color: #A6A6A6;
            font-size: 20px;

        }

        .rg_right p {
            font-size: 15px;
        }

        .rg_right p a {
            color: pink;
        }

        #btn_submit {
            width: 150px;
            height: 50px;
            margin-top: 50px;
            border: 1px solid yellow;
            background-color: yellow;

        }

    </style>
    <script src="js/jquery-2.1.0.min.js"></script>
    <script>
        function checkUid() {
            var uid = $("#uid").val();
            //定义正则
            var reg_uid = /^\w{1,10}$/;
            var flag = reg_uid.test(uid);
            if (flag) {
                $("#uid").css("border", "");

            } else {
                $("#uid").css("border", "1px solid red");

            }
            return flag;
        }

        function checkUpassword() {
            var password = $("#upassword").val();

            //定义正则
            var reg_password = /^\w{8,16}$/;
            //判断
            var flag = reg_password.test(password);
            if (flag) {
                //用户名合法
                $("#upassword").css("border", "");
            } else {
                $("#upassword").css("border", "1px solid red");
            }
            return flag;

        }

        function checkUemail() {
            var email = $("#uemail").val();
            var reg_email = /^\w+@\w+\.\w+$/;
            var flag = reg_email.test(email);
            if (flag) {
                //用户名合法
                $("#uemail").css("border", "");
            } else {
                $("#uemail").css("border", "1px solid red");
            }
            return flag;
        }

        function checkUname() {
            var name = $("#uname").val();
            var reg_name = /^[\u4E00-\u9FA5\uf900-\ufa2d·s]{2,20}$/;
            var flag = reg_name.test(name);
            if (flag) {
                //用户名合法
                $("#uname").css("border", "");
            } else {
                $("#uname").css("border", "1px solid red");
            }
            return flag;
        }


        $(function () {
            $("#uid").blur(checkUid);
            $("#upassword").blur(checkUpassword);
            $("#uemail").blur(checkUemail);
            $("#uname").blur(checkUname);
            $("#registForm").submit(function () {
                if (checkUid() && checkUemail() && checkUname() && checkUpassword()) {
                    return true;
                }
                return false;
            })
        })
    </script>
</head>
<body>
<div class="rg_layout">
    <div class="rg_left">
        <p>新闻管理系统</p>
        <p>USER REGISTER</p>
    </div>
    <div class="rg_center">
        <div style="color: green;text-align: center">${successMsg}</div>
        <div style="color: red;text-align: center">${errorMsg}</div>
        <div class="rg_from">
            <form action="user/regist" method="post" id="registForm">
                <table border="0">
                    <tr>
                        <td class="td_left"><label for="uid">用户ID</label></td>
                        <td class="td_right"><input type="text" name="uid" id="uid" placeholder="请输入用户ID,1-10位"></td>
                    </tr>
                    <tr>
                        <td class="td_left"><label for="uname">真实姓名</label></td>
                        <td class="td_right"><input type="text" name="uname" id="uname" placeholder="请输入真实姓名"></td>
                    </tr>

                    <tr>
                        <td class="td_left"><label for="upassword">密码</label></td>
                        <td class="td_right"><input type="password" name="upassword" id="upassword"
                                                    placeholder="请输入密码，8-16位"></td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="sex">性别</label>
                        </td>
                        <td class="td_right ">
                            <input type="radio" id="sex" name="usex" value="男" checked> 男 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <input type="radio" name="usex" value="女"> 女
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">
                            <label for="uemail">Email</label>
                        </td>
                        <td class="td_right">
                            <input type="text" id="uemail" name="uemail" placeholder="请输入Email">
                        </td>
                    </tr>
                    <tr>
                        <td align="center" colspan="2"><input type="submit" value="注册" id="btn_submit"></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    <div class="rg_right">
        <p>注册成功？<a href="login.jsp">立即登录</a></p></div>
</div>
</body>
</html>