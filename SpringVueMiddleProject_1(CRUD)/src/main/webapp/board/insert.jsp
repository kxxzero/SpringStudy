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
	width: 720px;
}
</style>
<script src="https://unpkg.com/vue@3"></script>
<!-- axios : 전송 객체 => 데이터 입·출력 시 사용 -->
<script src="https://unpkg.com/axios/dist/axios.min.js"></script> 
</head>
<body>
	<div class="container">
		<h3 class="text-center">글쓰기</h3>
		<div class="row">
		<!--  
			form => 기본 기능(서버로 데이터 전송)
				 => 화면 reset
				 => 기능을 동작하지 못하게 만듦 @submit.prevent	
		-->
			<form @submit.prevent="submitForm">
				<table class="table">
					<tr>
						<th width="15%" class="text-center">이름</th>
						<td width="85%">
							<input type="text" size="15" class="input-sm" v-model="name" ref="name"> <!-- ref => id 와 같은 역할 -->
						</td>
					</tr>
					
					<tr>
						<th width="15%" class="text-center">제목</th>
						<td width="85%">
							<input type="text" size="50" class="input-sm" v-model="subject" ref="subject">
						</td>
					</tr>
					
					<tr>
						<th width="15%" class="text-center">내용</th>
						<td width="85%">
							<textarea rows="10" cols="52" v-model="content" ref="content"></textarea>
						</td>
					</tr>
					
					<tr>
						<th width="15%" class="text-center">비밀번호</th>
						<td width="85%">
							<input type="password" size="15" class="input-sm" v-model="pwd" ref="pwd">
						</td>
					</tr>
					
					<tr>
						<td colspan="2" class="text-center">
							<input type="submit" value="글쓰기" class="btn-sm btn-success">
							<input type="button" value="취소" class="btn-sm btn-default" onclick="javascript:history.back()">
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
<script>
	let app=Vue.createApp({
		data(){
			return {
				name:'',
				subject:'',
				content:'',
				pwd:''
			}
		},
		methods:{
			submitForm(){
				if(this.name===''){
					this.$refs.name.focus()
					return
				}
				
				if(this.subject===''){
					this.$refs.subject.focus()
					return
				}
				
				if(this.content===''){
					this.$refs.content.focus()
					return
				}
				
				if(this.pwd===''){
					this.$refs.pwd.focus()
					return
				}
				
				/* let for=new FormData()
				form.append("name", this.name)
				form.append("subject", this.subject)
				form.append("content", this.content)
				form.append("pwd", this.pwd) */
				
				// {params:{name:this.name}}
				axios.post('../board/insert_ok.do', null, { // 두 번째 인자에 null값 부여, 데이터는 세 번째 인자에 위치
					params:{
						name:this.name,
						subject:this.subject,
						content:this.content,
						pwd:this.pwd
					}
					
				}).then(response=>{
						location.href="../board/list.do"
					})
					.catch(error=>{
						console.log(error.response)
					})
			}
		}
	}).mount('.container')
</script>
</body>
</html>