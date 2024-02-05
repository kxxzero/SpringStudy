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
<script src="http://code.jquery.com/jquery.js"></script>
</head>
<body>
	<div class="container">
		<h3 class="text-center">내용 보기</h3>
		<div class="row">
			<table class="table">
				<tr>
					<th width="20%" class="text-center">번호</th>
					<td width="30%" class="text-center">{{board_detail.no}}</td>
					<th width="20%" class="text-center">작성일</th>
					<td width="30%" class="text-center">{{board_detail.dbday}}</td>
				</tr>
				
				<!--
					board_detail={"no":1, "name":"홍길동", hit:10}
								   == 멤버변수
					=> board_detail.name
	         	-->
				
				<tr>
					<th width=20% class="text-center">이름</th>
	         		<td width=30% class="text-center">{{board_detail.name}}</td>
	         		<th width=20% class="text-center">조회수</th>
	         		<td width=30% class="text-center">{{board_detail.hit}}</td>
				</tr>
				
				<tr>
					<th width=20% class="text-center">제목</th>
					<td colspan="3">{{board_detail.subject}}</td>
		        </tr>
				
				<tr>
					<!-- v-html="" -->
					<td colspan="4" class="text-left" valign="top" height="200" v-pre="board_detail.content">
						<pre style="white-space: pre-wrap;">{{board_detail.content}}</pre>
					</td>
				</tr>
				
				<tr>
					<td colspan="4" class="text-center">
						<input type="button" value="수정" class="btn btn-xs btn-primary" @click="update()">
						<input type="button" value="삭제" class="btn btn-xs btn-info" @click="del()" ref="delBtn"> <!-- 제이쿼리 사용 시 id="delBtn"으로 입력 -->
						<input type="button" value="목록" class="btn btn-xs btn-default" @click="listData()">
					</td>
				</tr>
				
				<!-- 제이쿼리 사용 -->
				<!-- <tr id="del" style="display:none">
					<td colspan="4" class="text-right">
						비밀번호:<input type="password" class="input-sm" ref="pwd">
						<input type="button" class="btn-sm btn-danger" value="삭제">
					</td>
				</tr> -->
				
				<!-- 뷰 사용 -->
				<tr v-show="idShow">
					<td colspan="4" class="text-right">
						비밀번호:<input type="password" class="input-sm" ref="pwd">
						<input type="button" class="btn-sm btn-danger" value="삭제" @click="boardDelete">
					</td>
				</tr>
			</table>
		</div>
	</div>
	
<script>
	let app=Vue.createApp({
		data(){
			return {
				board_detail:{},
				no:${no}, // vue가 아닌 el(${}) 코딩 입력 방식
				change:0,
				isShow:false
			}
		},
		mounted(){
			/*
			    $.ajax({
			    	type:'get',
			    	url:'',
			    	data:{},
			    	=> axios.get()
			    	success:function(res) {
			    		then
			    	},
			    	error:function() {
			    		catch
			    	}
			    })
			*/
			axios.get('../board/detail_vue.do', {
				params:{
					no:this.no
				}
			}).then(result=>{
				console.log(result.data)
				this.board_detail=result.data
			}).catch(error=>{
				console.log(error.response)
			})
			
			/* // mounted = $(function(){}) = function(){} = window.onload
			if(this.change===0){
				this.change=1;
			} else {
				this.change=0;
			} */
		},
		methods:{
			update(){
				location.href="../board/update.do?no="+this.no
			},
			listData(){
				location.href="../board/list.do"
			},
			del(){
				if(this.change===0){
					this.change=1;
					/* 제이쿼리 사용 */
					/* $('#delBtn').val("취소")
					$('#del').show() */
					
					/* 뷰 사용 */
					this.isShow=true
					this.$refs.delBtn.value="취소"
				} else {
					this.change=0;
					/* 제이쿼리 사용 */
					/* $('#delBtn').val("수정")
					$('#del').hide() */
					
					/* 뷰 사용 */
					this.isShow=false
					this.$refs.delBtn.value="수정"
				}
			},
			boardDelete(){
				let pwd=this.$refs.pwd.value;
				if(pwd===""){
					this.$refs.pwd.focus()
					return
				}
				
				axios.get('../board/delete_vue.do', {
					params:{
						no:this.no,
						pwd:pwd
					}
				}).then(response=>{ // 결과갑
					if(response.data==='yes') {
						location.href="../board/list.do"
					} else {
						alert("비밀번호가 틀립니다.")
						this.$refs.pwd.value=""
						this.$refs.pwd.focus()
					}
				}).catch(error=>{
					console.log(error.response)
				})
			}
		}
	}).mount('.container')
</script>
</body>
</html>