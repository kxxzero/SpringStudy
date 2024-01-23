<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
	margin-top: 50px;
}
.row{
	margin: 0 auto;
	width: 960px;
}
</style>
</head>
<body>
	<div class="container">
		<div class="col-sm-9">
			<h3 class="text-center">내용 보기</h3>
			<table class="table">
				<tr>
					<th class="text-center success" width="20%">번호</th>
					<td class="text-center" width="30%">${vo.no}</td>
					<th class="text-center success" width="20%">작성일</th>
					<td class="text-center" width="30%">${vo.dbday}</td> 
				</tr>
				<tr>
					<th class="text-center success" width="20%">이름</th>
					<td class="text-center" width="30%">${vo.name}</td>
					<th class="text-center success" width="20%">조회수</th>
					<td class="text-center" width="30%">${vo.hit}</td> 
				</tr>
				<tr>
					<th class="text-center success" width="20%">제목</th>
					<td colspan="3">${vo.subject}</td>
				</tr>
				<tr>
					<td colspan="4" class="text-left" valign="top" height="200"><pre style="white-space:pre-wrap; border:none; background-color:white;">${vo.content}</pre></td>
				</tr>
				<tr>
					<td colspan="4" class="text-right">
						<a href="#" class="btn btn-xs btn-danger">답변</a>
						<a href="update.do?no=${vo.no}" class="btn btn-xs btn-success">수정</a>
						<a href="#" class="btn btn-xs btn-info">삭제</a>
						<a href="list.do" class="btn btn-xs btn-warning">목록</a>
					</td>
				</tr>
			</table>
		</div>
		<jsp:include page="top.jsp"></jsp:include>
	</div>
</body>
</html>