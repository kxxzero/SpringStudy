<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="row">
		<c:forEach var="vo" items="${list}">
		    <div class="thumbnail">
		      <a href=../goods/goods_detail.do?no${vo.no}$type=${type}">
		        <img src="${vo.goods_poster}" title="${vo.goods_name}" style="width:100%">
		        <div class="caption">
		          <p>${vo.goods_price}</p>
		        </div>
		      </a>
		    </div>
		</c:forEach>
	</div>
</body>
</html>