<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script> <!-- axios : 전송 객체 => 데이터 입·출력 시 사용 -->
<style type="text/css">
.row1{
	margin: 0 auto;
	width: 700px;
}
</style>
</head>
<body>
	<div class="wrapper row3">
		<main class="container clear">
			<h2 class="sectiontitle">글쓰기</h2>
			<table class="table">
				<tr>
					<th width="10%">이름</th>
					<td width="90%"><input type="text" ref="name" size="15" v-model="name"></td>
				</tr>
				<tr>
					<th width="10%">제목</th>
					<td width="90%"><input type="text" ref="subject" size="50" v-model="subject"></td>
				</tr>
				<tr>
					<th width="10%">내용</th>
					<td width="90%"><textarea rows="10" cols="52" ref="content" v-model="content"></textarea>
				</tr>
				<tr>
					
				</tr>
			</table>
		</main>
	</div>
<script>
	let fApp=Vue.createApp({
		data(){
			return{
				name:'',
				subject:'',
				content:'',
				pwd:''
			}
		},
		methods:{
			if(this.name==="") {				
				this.$refs.name.focus()
				return
			}
			if(this.subject==="") {				
				this.$refs.subject.focus()
				return
			}
			if(this.content==="") {				
				this.$refs.content.focus()
				return
			}
			if(this.pwd==="") {				
				this.$refs.pwd.focus()
				return
			}
			
			axios.post('../freeboard/insert_vue.do', null, {
				params:{
					name:this.name,
					subject:this.subject,
					content:this.content,
					pwd:this.pwd
				}
			}).then(response=>{
				if(response.data==="yes"){
					location.href="../freeboard/list.do"
				} else {					
					alert(response.data)	
				}
			})
		}
	})
</script>
</body>
</html>