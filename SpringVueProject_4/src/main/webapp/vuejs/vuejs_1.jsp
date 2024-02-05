<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!--
	Vue : Evan You(구글 => AngularJS : 복잡함)
	IBM => OS2, MS 도스창
	=> 단순한 프레임워크
	VueJS : 1) 단순한 
		사용 : MVVM
			M(Model) : 데이터 저장 =>  data() 
			V(View) : 화면 출력
					=> {{}}, v-for, v-model, v-if, v-show, v-if, v-else
			VM(View Model) : 데이터 관리, 연산 처리
							=> 생명주기
								- mounted : onLoad
								- updated : 수정
								- 사용자 정의
									methods:{
										=> 이벤트 처리
									}
								- 기능
									components:{
										=> 이미지 카드, 애니메이션, 재사용 가능 
									}
								- filter : 10,000
										=> Computed : 계산된 경우
										
								=========================
								1. Vue 객체 생성 => 여러 개 생성 가능
									=> 범위 지정 => mount('태그명, 클래스명, ID명')
									
									기본 형식) let app=Vue.createApp({
									
												Model : 데이터 관리
												data(){
													return{
														fno:0, Number
														fd:'', String
														list:[], Array
														obj:{}, Object => 자바스크립트 객체
														isShow:true, Boolean
													}
												}
												--------------------
												ViewModel : 데이터 처리
												
												1) 변수의 초기화 => 서버나 파일
													mounted(){
														서버나 파일 읽기 => data에 저장된 변수에 초기화
														axios.get("서버 URL",{
																	서버로 요청하는 데이터 설정
																		params:{
																			fno:1,
																			id:'admin'
																		}
																	}).then(res(결과값을 받음)=>{
																		멤버변수에 대입
																})
														axios.post("서버 URL",{
																	서버로 요청하는 데이터 설정
																		params:{
																			fno:1,
																			id:'admin'
																		}
																	}).then(res(결과값을 받음)=>{
																		멤버변수에 대입
																})
													}
												--------------------
												사용자 정의 이벤트
												
												
												화면 출력
												
												출력 형식)
													<div>{{data()에 설정}}</div> => text() $)().text("")
													<div :data-no> => :속성명="변수명"
													
												디렉티브
													= v-for=vo in 배열" => v-for="(vo.index)																	
											})
											
											=> react : 단독 처리
											=> JSP / Spring => PR
	
-->
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
  margin: 0px auto;
  width: 960px;
}
</style>
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
	<!-- View -->
	<div class="container">
		<div class="row">
			<h3 class="text-center">{{message}}</h3>
			<input type="button" value="클릭" @click="change()"><br>
			<input type="text" size="20" v-model="message">
		</div>
	</div>
	
<!-- 양방향 통신 -->
<script>
	let app=Vue.createApp({
		// 데이터 설정 => Model
		data(){
			return {
				message:'Hello Vue'
			}
		},
		// 데이터 처리 / 초기화 => ViewModel => MVVM
		methods:{
			change(){
				this.message="변경됨"
			}
		}
	}).mount('.container')
</script>
</body>
</html>