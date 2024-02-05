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
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<table class="table">
				<tr>
					<td>
						<input type="text" class="input-sm" size="25" ref="fd" v-model="fd" @keyup.enter="find()">
						<input type="button" class="btn-sm btn-danger" value="검색" @click="find()">
					</td>
				</tr>
			</table>
		</div>
		<div class="row">
			<div class="col-md-3" v-for="vo in food_list">
			    <div class="thumbnail">
			        <img :src="'https://www.menupan.com'+vo.poster" alt="Lights" style="width:100%" class="images">
			        <div class="caption">
			          <p>{{vo.name}}</p>
			        </div>
			    </div>
			</div>
		</div>
	</div>
<script>
let app=Vue.createApp({
	data(){
		return {
			food_list:[], // ArrayList로 넘어온 값
			fd: '마포'
		}
	},
	// find.do?address=마포
	mounted(){
		this.send()
	},
	methods:{
		send(){
			axios.get("http://localhost:8080/web/food/find.do",{
				params:{
					address:this.fd // 메소드의 매개변수명과 스크립트의 변수명이 일치해야 함 => 자동으로 ? 뒤에 값을 넣어줌
				}
			}).then(response=>{
				console.log(response.data)
				this.food_list=response.data
			})
		},
		find(){
			let fds=this.$refs.fd.value;
			if(fds==""){
				alert("검색어 입력:")
				this.$refs.fd.focus()
				return
			}
			this.send();
		}
	}
}).mount('.container')
</script>
</body>
</html>