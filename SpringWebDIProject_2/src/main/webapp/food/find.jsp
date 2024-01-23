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
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<input type="text" size="20" class="input-sm" v-model="fd"><!-- v-model : 변수 값을 채워줌 -->
			<input type="button" value="검색" class="btn-sm btn-danger" @click="find()"> <!-- on 대신 @ 입력 -->
		</div>
		<div style="height: 20px"></div>
		<div class="row">
			<div class="col-md-3" v-for="vo in find_data">
			    <div class="thumbnail">
			      <a href=#">
			        <img :src="'https://www.menupan.com'+vo.poster" alt="" style="width:100%">
			        <div class="caption">
			          <p>{{vo.name}}</p>
			        </div>
			      </a>
			    </div>
			</div>
		</div>
	</div>
	
	<script>
		const {createApp} = Vue
		createApp({
			data(){
				return {
					// 멤버 변수
					fd:'마포',
					find_data:[],
				};
			},
			// $(function(){}) = > window.onload
			mounted(){
				axios.get('http://localhost:8080/web/food/find_vue.do',{
					params:{
						fd:this.fd,
						page:1
					},
				// success
				}).then(response=>{
					this.find_data=response.data
					console.log(response.data)
				});
			},
			methods:{
				find(){
					axios.get('http://localhost:8080/web/food/find_vue.do',{
						params:{
							fd:this.fd,
							page:1
						},
					// success
					}).then(response=>{
						this.find_data=response.data
						console.log(response.data)
					});
				},
			},
			
		}).mount('.container')
	</script>
</body>
</html>