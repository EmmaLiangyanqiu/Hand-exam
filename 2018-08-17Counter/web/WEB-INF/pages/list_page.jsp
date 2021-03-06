<%--
  Created by IntelliJ IDEA.
  User: Emma
  Date: 2018/8/20
  Time: 21:15
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/static/css/bootstrap.css" type="text/css">
</head>
<body>
<!--通过bootstrap的栅格系统布局-->
<div class="container">
    <!--标题-->
    <div class="row">
        <div class="col-md-12">
            <h1>SSM-CRUD</h1>
        </div>

    </div>

    <!--按钮-->
    <div class="row">
        <div class="col-md-4 col-md-offset-8">
            <button class="btn btn-primary">新增</button>
            <button class="btn btn-danger">删除</button>
        </div>
    </div>

    <!--显示表格数据-->
    <div class="row">
        <div class="col-md-12">
            <table class="table table-hover">
                <tr>
                    <th>#</th>
                    <th>empName</th>
                    <th>操作</th>
                </tr>


                <c:forEach items="${pagehelper.list}" var="user">
                    <tr>
                        <th>${user.id}</th>
                        <th>${user.username}</th>
                        <th>
                            <button class="btn btn-primary">
                                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                编辑
                            </button>

                            <button class="btn btn-danger">
                                <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                删除
                            </button>

                        </th>
                    </tr>
                </c:forEach>

            </table>
        </div>

    </div>

    <!--显示分页信息-->
    <div class="row">
        <!--文字信息-->
        <div class="col-md-6">
            当前第 ${pagehelper.pageNum} 页.总共 ${pagehelper.pages} 页.一共 ${pagehelper.total} 条记录
        </div>

        <!--点击分页-->
        <div class="col-md-6">
            <nav aria-label="Page navigation">
                <ul class="pagination">

                    <li><a href="${pageContext.request.contextPath}/queryAll?pn=1">首页</a></li>

                    <!--上一页-->
                    <li>
                        <c:if test="${pagehelper.hasPreviousPage}">
                            <a href="${pageContext.request.contextPath}/queryAll?pn=${pagehelper.pageNum-1}" aria-label="Previous">
                                <span aria-hidden="true">«</span>
                            </a>
                        </c:if>
                    </li>

                    <!--循环遍历连续显示的页面，若是当前页就高亮显示，并且没有链接-->
                    <c:forEach items="${pagehelper.navigatepageNums}" var="page_num">
                        <c:if test="${page_num == pagehelper.pageNum}">
                            <li class="active"><a href="#">${page_num}</a></li>
                        </c:if>
                        <c:if test="${page_num != pagehelper.pageNum}">
                            <li><a href="${pageContext.request.contextPath}/queryAll?pn=${page_num}">${page_num}</a></li>
                        </c:if>
                    </c:forEach>

                    <!--下一页-->
                    <li>
                        <c:if test="${pagehelper.hasNextPage}">
                            <a href="${pageContext.request.contextPath}/queryAll?pn=${pagehelper.pageNum+1}"
                               aria-label="Next">
                                <span aria-hidden="true">»</span>
                            </a>
                        </c:if>
                    </li>

                    <li><a href="${pageContext.request.contextPath}/queryAll?pn=${pagehelper.pages}">尾页</a></li>
                </ul>
            </nav>
        </div>

    </div>


</div>

</body>
</html>
