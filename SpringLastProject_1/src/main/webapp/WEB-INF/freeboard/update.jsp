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
	<div class="wrapper row3 row1" id="fboardApp">
		<main class="container clear">
			<h2 class="sectiontitle">수정하기</h2>
			<table class="table">
				<tr>
					<th width="10%">이름</th>
					<td width="90%"><input type="text" ref="name" v-model="name" size="15"></td>
				</tr>
				<tr>
					<th width="10%">제목</th>
					<td width="90%"><input type="text" ref="subject" v-model="subject" size="50"></td>
				</tr>
				<tr>
					<th width="10%">내용</th>
					<td width="90%"><textarea rows="10" cols="52" ref="content" v-model="content"></textarea>
				</tr>
				<tr>
					<th width="10%">비밀번호</th>
					<td width="90%"><input type="password" ref="pwd" v-model="pwd" size="15"></td>
				</tr>
				<tr>
					<td colspan="2" class="text-center inline">
			           <input type=button value="수정하기" class="btn-sm btn-danger" @click="update()">
			           <input type=button value="취소" class="btn btn-sm btn-success" @click="goback()">
			         </td>
				</tr>
			</table>
		</main>
	</div>
<script>
	let fboardApp=Vue.createApp({
		data(){
			return{
				update_data:{},
				name:'',
				subject:'',
				content:'',
				pwd:'',
				no:${no} // no는 값을 받아서 옴
			}
		},
		mounted(){
			axios.get('../freeboard/update.do',{
				params:{
					no:this.no
				}
			}).then(response=>{
				console.log(response.data)
				this.update_data=response.data
				this.name=response.data.name
				this.subject=response.data.subject
				this.content=response.data.content
			})
		},
		methods:{
			goback(){
				window.history.back()
			},
			update(){
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
				
				axios.post('../freeboard/update_ok_vue.do', null, {
					params:{
						name:this.name,
						subject:this.subject,
						content:this.content,
						pwd:this.pwd,
						no:this.no
					}
				}).then(response=>{
					if(response.data==="yes"){
						location.href="../freeboard/detail.do?no="+this.no
					} else {
						alert("비밀번호가 틀렸습니다!")
						this.pwd=""
						this.$refs.pwd.focus()
					}
				})
			}
		}
	}).mount('#fboardApp')
</script>
</body>
</html>