<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<center>
		<table border="1" border-color="black" width="800" height="720">
			<tr>
				<td colspan="2" height="100">
					<tiles:insertAttribute name="header"/>
				</td>
			</tr>
			<tr>
				<td width="200" height="500">
					<tiles:insertAttribute name="nav"/>
				</td>
				<td width="600" height="500">
				
				</td>
			</tr>
			<tr>
				<td colspan="2" height="100"></td>
			</tr>		
		</table>
	</center>
</body>
</html>