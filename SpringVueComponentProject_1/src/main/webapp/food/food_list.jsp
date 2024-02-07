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
</style>
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script> <!-- axios : 전송 객체 => 데이터 입·출력 시 사용 -->
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="text-center">
				<my-btn1 v-bind:btn1="btn1" @click="btnClick()"/>
			</div>
		</div>
	</div>
<script>
	const mybtn1={
		props:['btn1'],
		template:`<button class="btn-sm btn-danger" @click="btnClick()">{{btn1}}</botton>`
		methods:{
			btnClick(){
				this.$parent.btnClick()
			}
		}
	}
	const mybtn2={
		template:`<button class="btn-sm btn-success">{{btn2}}</botton>`
	}
	const mybtn3={
		template:`<button class="btn-sm btn-info">{{btn3}}</botton>`
	}

	let app=Vue.createApp({
		data(){
			return{
				btn1:'1번버튼',
				btn2:'2번버튼',
				btn3:'3번버튼'
			}
		},
		methods:{
			btnClick(){
				alert("1번 버튼 클릭")
			}
		},
		components:{
			'my-btn1':mybtn1,
			'my-btn2':mybtn2,
			'my-btn3':mybtn3
		}
	})
</script>
</body>
</html>