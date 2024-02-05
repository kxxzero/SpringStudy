<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
.images:hover{
   cursor:pointer;
}
</style>
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axois/dist/axios.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="text-center">
				<input type="button" class="btn-lg, btn-danger" value="일일박스오피스">
				<input type="button" class="btn-lg, btn-success" value="실시간예매율">
				<input type="button" class="btn-lg, btn-info" value="좌석점유율순위">
			</div>
		</div>
		<div style="height:20px"></div>
		<div class="row">
			{{data}}
		</div>
	</div>
	
	<!--
		Vue에 저장된 모든 멤버 변수의 값을 확인할 때 사용 => $data
		=> input 구분 => (class, id)
					=> Vue(ref) => $refs.ref명 => id값을 읽지 못하기 때문에 ref값을 사용
					
	-->
	
<script>
	let app=Vue.createApp({
		data(){
			return {
				movie_list:[],
				no:1
			}
		},
		/* 시작하자마자 호출 */
		mounted():{
			axios.get("http://localhost:8080/web/jsp/movie.jsp", {
				params:{
					no:this.no
				}
			})
			.then(response=>{
				console.log(response.data)
				this.movie_list=response.data
			})
		},
		methods:{
			change(no){
				this.no=no
				axios.get("http://localhost:8080/web/jsp/movie.jsp",{
					params:{
						no:this.no
					}
				})
				.then(response=>{
					console.log(response.data)
					this.movie_list=response.data
				})
			}
		}
	}).mount('.container')
</script>
</body>
</html>