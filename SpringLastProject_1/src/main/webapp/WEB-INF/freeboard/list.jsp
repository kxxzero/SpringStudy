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
</head>
<body>
	<div class="wrapper row3">
		<main class="container clear">
			<h2 class="sectiontitle">자유게시판</h2>
			<table class="table">
				<tr>
					<td>
						<a href="../freeboard/insert.do" class="btn btn-sm btn-danger">새글</a>
					</td>
				</tr>
			</table>
			<table class="table">
				<tr>
					<th width="10%" class="text-center">번호</th>
					<th width="45%" class="text-center">제목</th>
					<th width="15%" class="text-center">이름</th>
					<th width="20%" class="text-center">작성일</th>
					<th width="10%" class="text-center">조회수</th>
				</tr>
				<tr v-for="vo in board_list">
					<td width="10%" class="text-center">{{vo.no}}</td>
					<td width="45%">{{vo.subject}}</td>
					<td width="15%" class="text-center" v-text="vo.name"></td>
					<td width="20%" class="text-center" v-text="vo.dbday"></td>
					<td width="10%" class="text-center" v-text="vo.hit"></td>
				</tr>
				<tr>
					<td colspan="5" class="text-center">
						<input type="button" class="btn btn-sm btn-info" value="이전">
						{{curpage}} page / {{totalpage}} pages
						<input type="button" class="btn btn-sm btn-info" value="다음">
				</tr>
			</table>
		</main>
	</div>
	<%--
		1. 검색 => 다이얼로그
		2. CRUD => 게시판 / 댓글
		3. 채팅
		4. 추천
		5. 로그인, 아이디 찾기, 비밀번호 찾기
		------------------------------ Vue
		react ===== Spring-boot
	--%>
<script>
	let fboardApp=Vue.createApp({		
		data(){
			return{
				board_list:[]
				curpage:1,
				totalpage:0
			}
		},
		mounted(){
			// 시작과 동시에 처리
			this.dataRecv()
		},
		update(){
			
		},
		methods:{
			// mounted, prev, next
			dataRecv(){
				axios.get('../freeboard/list.do',{
					params:{
						page:this.curpage
					}
				}).then(response=>{
					console.log(response.data)
					this.board_list=response.data
					/* 
						response={
							config: ,
							data: ,
							header: , 
								...
						}
						호출은 response.config, response.data, response.header, ...
					*/
				})
				
				axios.get("../freeboard/page_vue.do", {
					params:{
						page:this.curpage
					}
				}).then(response=>{
					// response={data:{curpage:1, totalpage:10}} => 이런 데이터 형식은 Map으로 묶음
					this.curpage=response.data.curpage
					this.totalpage=response.data.totalpage
				})
			},
			prev(){
				
			},
			next(){
				this.curpage=this.curpage
			}
		}
	}).mount('.fboardApp')
</script>
</body>
</html>