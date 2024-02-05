<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script> <!-- axios : 전송 객체 => 데이터 입·출력 시 사용 --> 
</head>
<body>

<jsp:include page="${login_jsp}"></jsp:include>
<div class="container" id="logApp">
	
	<c:if test="${sessionScope.id==null}">
		<div class="row">
			<div class="text-right">
				ID : <input type="text" ref="id" class="input-sm" v-model="id">&nbsp;
				PW : <input type="password" ref="pwd" class="input-sm" v-model="pwd">&nbsp;
				<input type="button" value="로그인" class="btn btn-sm btn-danger" @click="login()">
			</div>
		</div>
	</c:if>

	<c:if test="${sessionScope.id!=null}">
		<div class="row">
			<div class="text-right">
				${sessionScope.name}님 로인되었습니다.
				<input type="button" value="로그아웃" class="btn-sm btn-danger" @click="logout()">
			</div>
		</div>
	</c:if>
</div>

<script>
	let logApp=Vue.createApp({
		data() {
			return {
				id:'',
				pwd:'',
				msg:''
			}
		},
		
		mounted(){
			
		}, 
		
		updated(){
			
		},
		
		methods:{
			login() {
				if(this.id=='') { // 미입력 시					
					this.$refs.id.focus() // refs 속성값 호출 시 $ 입력
					return // 넘어가지 않도록 return 처리
				}
				if(this.pwd=="") {
					this.$refs.pwd.focus()
					return
				}
				
				axios.get('../member/login_vue.do', {
					params:{
						id:this.id,
						pwd:this.pwd
					}
				}).then(response=>{
					if(response.data==='NOID') {
						alert("ID가 존재하지 않습니다.")
						this.id=""
						this.pwd=""
						this.$refs.id.focus()
		
					} else if (response.data==='NOPWD') {
						alert("비밀번호가 틀립니다.")
						this.pwd=""
						this.$refs.pwd.focus()
					
					} else {
						location.href="../food/list.do"
					}
				})
			},
			logout(){
				location.href="../member/logout.do"
			}
		}
	}).mount("#logApp")
</script>
</body>
</html>