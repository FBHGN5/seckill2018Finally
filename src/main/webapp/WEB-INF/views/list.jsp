<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/head.jsp"%>
<%--静态包含1个servlet,动态是2个先head加载再加载本页面--%>
<!DOCTYPE html>
<html>
<head>
    <title>秒杀列表页</title>
</head>
<body>
<div class="container">
  <div class="panel panel-default">
      <div class="panel-heading text-center"><h2>秒杀列表</h2></div>
       <div class="panel-body">
           <table class="table table-hover">
               <thread>
                   <tr>
                       <th>名称</th>
                       <th>库存</th>
                       <th>开始时间</th>
                       <th>结束时间</th>
                       <th>创建时间</th>
                       <th>秒杀详情页</th>
                   </tr>
               </thread>
               <tbody>
               <c:forEach var="sk" items="${page.list}">
                   <tr>
                       <td>${sk.name}</td>
                       <td>${sk.number}</td>
                       <td><fmt:formatDate value="${sk.startTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                       <td><fmt:formatDate value="${sk.endTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                       <td><fmt:formatDate value="${sk.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                       <td><a class="btn btn-info" href="/seckill/${sk.seckillid}/detail" target="_blank">详情查看</a></td>
                   </tr>
               </c:forEach>
              </tbody>
           </table>
           <%--分页实现--%>
           <p>每页${page.pageSize}条  当前页${page.size}条${page.pageNum}/${page.pages}页
           记录数${page.total}
           </p>
           <ul class="pagination">

                   <c:if test="${page.isFirstPage==true}"><li><a>首页</a></li></c:if>
                   <c:if test="${page.isFirstPage==false}">
                       <li><a href="/seckill/list?page=${page.firstPage}">首页</a></li></c:if>
                   <c:if test="${page.hasPreviousPage==true}">
                       <li><a href="/seckill/list?page=${page.prePage}">&laquo;</a></li></c:if>
                   <c:if test="${page.hasPreviousPage==false}">
                       <li><a>&laquo;</a></li></c:if>
               <c:set var="index" value="0" />
            <c:forEach begin="1" end="${page.pages}">
           <c:set var="index" value="${index+1}" />
           <li><a href="/seckill/list?page=${index}">${index}</a></li>
           </c:forEach>


           <c:if test="${page.hasNextPage==true}">
               <li><a href="/seckill/list?page=${page.nextPage}">&raquo;</a></li></c:if>
           <c:if test="${page.hasNextPage==false}">
               <li><a>&raquo;</a></li></c:if>
           <c:if test="${page.isLastPage==true}">
                   <li><a >末页</a></li></c:if>
           <c:if test="${page.isLastPage==false}">
                   <li><a href="/seckill/list?page=${page.lastPage}">末页</a></li></c:if>
           </ul>


</body>
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</html>
