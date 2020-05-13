<%--
  Created by IntelliJ IDEA.
  User: 张振东
  Date: 2020/5/12
  Time: 23:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>修改新闻</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="js/jquery-2.1.0.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

</head>
<body>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">展示新闻</h3>
    <form action="" method="post">
        <div class="form-group">
            <label for="title">标题</label>
            <input type="text" class="form-control" id="title" name="title"  readonly="readonly" value="${news.title}" />
        </div>


        <div class="form-group">
            <label for="author">作者</label>
            <input type="text" class="form-control" id="author"  name="author" value="${news.author}" readonly="readonly"/>
        </div>

        <label >内容</label>
        <textarea class="form-control" rows="6" placeholder="请输入内容" name="content" readonly="readonly">${news.content}</textarea>

        <div class="form-group">
            <label for="enterdate">发布时间</label>
            <input type="text" id="enterdate" class="form-control" name="enterdate" value="${news.enterdate}" readonly="readonly"/>
        </div>

        <div class="form-group">
            <label for="hot">热度</label>
            <input type="text" id="hot" class="form-control" name="hot" value="${news.hot}" readonly="readonly" />
        </div>

        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交" />
            <input class="btn btn-default" type="reset" value="重置" />
            <input class="btn btn-default" type="button" value="返回" onclick="javascript:history.back(-1);" />
        </div>
    </form>
</div>
</body>
</html>