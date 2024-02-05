<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://unpkg.com/vue@3"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#msg').keyup(function(){
		$('#print').text($('#msg').val())
	})
})
</script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
	margin-top: 50px;
}
.row{
	margin: 0 auto;
	width: 800px;
}
</style>

<!--
	Vue
	 1. 가상 메모리(DOM)을 사용 => 빠른 속도 처리
	 	1) mount : 가상 메모리에 저장
	 	2) String / StringBuffer(임시 저장)
	 2. 생명 주기 => callback
	 	1) vue3(react), vuex(mvc)
	 		= Vue 객체 생성
	 			- beforeCreate()
	 			- created()

	 		= 가상 메모리에 HTML을 올린 경우
	 			- beforeMount()
	 			- mounted() => $(function(){}), window.onload
	 				=> 서버에서 데이터를 읽음 : 멤버 변수에 저장
	 				
	 		= 데이터 갱신
	 			- beforeUpdate()
	 			- updated()
	 				=> Component : Main / Sub => sub에 값을 전송 => $emit
	 				
	 		= 메모리 해제
	 			- beforeDestroy()
	 			- destroyed()
	 			
	 3. 디렉티브 => 제어문
	 4. 서버 연동 => axios
	 5. 출력 형식 => {{}} / :src=
	 6. 양방향 통신 => v-model
	 7. router => 화면 변경
	 =========================
	 8. vue-bootstrap
-->

</head>
<body>
	<div class="container">
		<div class="row">
			<!-- <input type="text" size="30" class="input-sm" id="msg"> -->
			<input type="text" size="30" class="input-sm" v-model="msg">
		</div>
		<!-- 입력과 동시에 출력 => 양방향 통신 -->
		<div class="row">
			<!-- <div id="print"></div> -->
			<div>{{msg}}</div>
		</div>
	</div>
	
<script>
// Vue JS
// Vue 객체 생성
let app=Vue.createApp({
	data(){
		// 멤버 변수 설정
		return {
			msg:'' // msg 해당 변수는 자유롭게 사용 가능
		}
	},
	// Callback 함수(Vue에 의해 자동으로 호출되는 함수)
	beforeCreate(){
		console.log("Vue 객체 생성 전 : beforeCreate() Call")
	},
	created(){
		console.log("Vue 객체 생성 완료 : created() Call")
	},
	beforeMount(){
		console.log("HTML와 데이터를 가상 메모리에 올라가기 전 : beforeMount() Call")
	},
	mounted(){
		console.log("가상 메모리에 HTML이 올라간 상태 : window.onload,$(function), mounted() Call")
	},
	beforeUpdate(){
		console.log("변경 전 : beforeUpdate() Call")
	},
	updated(){
		console.log("변경 완료 : updated() Call")
	},
	beforeDestroy(){
		console.log("Vue 객체 메모리 해제 전 : beforeDestroy() Call")
	},
	destroyed(){
		console.log("Vue 객체 메모리 해제 완료 : destroyed() Call")
	},
	
	// 사용자 정의 메소드 작성 위치
	methods:{
		// 예) 버튼 클릭 또는 마우스 오버 시 이벤트 발생
	}
	
}).mount('.container') // .mount(=onload) : container 영역만큼 제어하겠다는 의미 => 임시 가상 메모리(mount)에 데이터가 올라감 => 변경되는 부분만 제어(Diff) => 속도 빠름
</script>
</body>
</html>