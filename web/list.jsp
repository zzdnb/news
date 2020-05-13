<%--
  Created by IntelliJ IDEA.
  User: 张振东
  Date: 2020/4/13
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
    <script>
        function deleteNews(id){
            //用户安全提示
            if(confirm("您确定要删除吗？")){
                //访问路径
                location.href="/news/delNews?id="+id;
            }
        }
    </script>
</head>
<body>
<div class="container">

    <h7 style="text-align: left">欢迎 <font color="red">${user.uname}
    </font>来到新闻管理系统,请文明发言
    </h7>
    <h3 style="text-align: center">新闻管理系统</h3>
    <div style="text-align: center">

        <form class="form-inline" action="${pageContext.request.contextPath}/findUserByPageServlet" method="post">
            <div class="form-group">
                <label for="exampleInputName2">姓名</label>
                <input type="text" name="name" value="${condition.name[0]}" class="form-control" id="exampleInputName2" >
            </div>

            <div class="form-group">
                <label for="exampleInputEmail2">邮箱</label>
                <input type="text" name="email" value="${condition.email[0]}" class="form-control" id="exampleInputEmail2"  >
            </div>
            <button type="submit" class="btn btn-default">查询</button>
        </form>

    </div>
    <table border="1" class="table table-bordered table-hover">
        <tr class="success">
            <th>编号</th>
            <th>标题</th>
            <th>作者</th>
            <th>更新时间</th>
            <th>热度</th>

        </tr>
        <c:forEach items="${pb.list}" var="ly" varStatus="s">
            <tr>
                <td>${s.count}</td>
                <td>${ly.title}</td>
                <td>${ly.author}</td>
                <td>${ly.enterdate}</td>
                <td>${ly.hot}</td>
                <td><a class="btn btn-default btn-sm"
                       href="/news/findNews1?id=${ly.id}">查看</a>
                    <a class="btn btn-default btn-sm" href="/news/findNews2?id=${ly.id}">修改</a>&nbsp;
                    <a class="btn btn-default btn-sm"  href="javascript:deleteNews(${ly.id});">删除</a></td>
            </tr>
        </c:forEach>


        <tr>
            <td colspan="8" align="center"><a class="btn btn-primary"
                                              href="add.jsp">添加新闻</a>
            </td>
        </tr>
    </table>
</div>
<nav aria-label="Page navigation">
    <ul class="pagination">
        <c:if test="${pb.currentPage==1}">
            <li class="disabled">
                <a href="/news/findNewsByPage?currentPage=${pb.currentPage-1}&rows=7" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
        </c:if>
        <c:if test="${pb.currentPage!=1}">
            <li><a href="/news/findNewsByPage?currentPage=${pb.currentPage-1}&rows=7" aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
            </a></li>
        </c:if>

        <c:forEach begin="1" end="${pb.totalPage}" var="i">

            <c:if test="${i==pb.currentPage}">
                <li class="active"><a href="/news/findNewsByPage?currentPage=${i}&rows=7">${i}</a></li>
            </c:if>
            <c:if test="${i!=pb.currentPage}">
                <li><a href="/news/findNewsByPage?currentPage=${i}&rows=7">${i}</a></li>
            </c:if>
        </c:forEach>

        <c:if test="${pb.currentPage==pb.totalPage}">
            <li class="disabled"><a href="/news/findNewsByPage?currentPage=${pb.currentPage+1}&rows=7"
                                    aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
            </a>
            </li>
        </c:if>
        <c:if test="${pb.currentPage!=pb.totalPage}">
            <li><a href="/news/findNewsByPage?currentPage=${pb.currentPage+1}&rows=7" aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
            </a>
            </li>
        </c:if>

        <span style="font-size: 25px;margin-left: 5px ">
            共${pb.totalCount}条记录，共${pb.totalPage}页
        </span>
    </ul>
</nav>
</body>
</html>
