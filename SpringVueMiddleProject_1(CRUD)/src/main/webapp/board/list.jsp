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
		<h3 class="text-center">자유게시판</h3>
		<div class="row">
			<table class="table">
				<tr>
					<td>
						<a href="insert.do" class="btn btn-sm btn-danger">새글</a>
					</td>
				</tr>
			</table>
			
			<table class="table">
				<tr class="success">
					<th class="text-center" width="10%">번호</th>
					<th class="text-center" width="45%">제목</th>
					<th class="text-center" width="15%">이름</th>
					<th class="text-center" width="20%">작성일</th>
					<th class="text-center" width="10%">조회수</th>
				</tr>
				
				<tr v-for="vo in board_list">
					<td class="text-center" width="10%">{{vo.no}}</td>
					<td width="45%"><a :href="'detail.do?no='+vo.no">{{vo.subject}}</a></td> <!-- 반드시 ':'을 입력해야 함 -->
					<td class="text-center" width="15%">{{vo.name}}</td>
					<td class="text-center" width="20%">{{vo.dbday}}</td>
					<td class="text-center" width="10%">{{vo.hit}}</td>
				</tr>
				
				<tr>
					<td colspan="5" class="text-center">
						<input type="button" value="이전" class="btn btn-sm btn-primary" @click="prev()">
						{{curpage}} page / {{totalpage}} pages
						<input type="button" value="다음" class="btn btn-sm btn-primary" @click="next()">
					</td>
				</tr>
			</table>
		</div>
	</div>
	
	<!-- 제어 -->
	<script>
		let app=Vue.createApp({
			data(){
				return {
					// 변수 선언
					board_list:[],
					/* page_list:{}, */
					curpage:1,
					totalpage:0
				}
			},
			// 콜백 함수
			mounted(){ // mounted() : 시작하자마자 출력 => 자동 호출 되는 함수
				this.dataSend()
			},
			// 멤버 메소드
			methods:{ // 사용자 정의 함수
				dataSend(){
					axios.get('../board/list_vue.do',{ // RestController에 작성된 것과 연결해 주어야 함
						params:{ // get => 방식 url의 ? 뒤에 붙음 / post 방식은 params 없음
							page:this.curpage // this를 입력해야만 멤버 변수, 멤버 메소드로 인식하여 호출 가능
						}
					}).then(response=>{ // 응답 완
						console.log(response.data) // 실제 값은 data라는 변수 안에 존재 
						this.board_list=response.data // 받은 데이터를 'board_list'에 전달해야 화면에 출력 가능
					})
					
					// 페이지
					axios.get("../board/page_vue.do",{
						params:{
							page:this.curpage
						}
					}).then(response=>{
						console.log(response.data)
						this.curpage=response.data.curpage
						this.totalpage=response.data.totalpage
					})
				},
				prev(){
					this.curpage=this.curpage>1?this.curpage-1:this.curpage
					this.dataSend()
				}, 
				next(){
					this.curpage=this.curpage<this.totalpage?this.curpage+1:this.curpage
					this.dataSend()
				}
			}
		}).mount('.container')
	</script>
</body>
</html>