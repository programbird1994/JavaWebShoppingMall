 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
           <table class="table table-striped shop_attributes" id="comment">	
											<tbody>
											<tr class="">
													<th>用户名</th>
													<td>评价</td>
													<td>评分</td>
													<th>评价时间</th>
												</tr>
											<c:forEach items="${comments}" var="comment">
											<tr class="alt">
													<th>${comment.userName}</th>
													<td>${comment.commentContent}</td>
													<td>${comment.rank}</td>
													<th>${comment.createTime}</th>
												</tr>
											</c:forEach>
											</tbody>
											</table>
</body>
</html>