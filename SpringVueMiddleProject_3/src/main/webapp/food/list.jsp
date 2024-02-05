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
a.link:hover{
	cursor: pointer;
}
</style>
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script> <!-- axios : 전송 객체 => 데이터 입·출력 시 사용 --> 
</head>
<body>
	<jsp:include page="${login_jsp}"></jsp:include>
	<div class="container" id="listApp">
		<div class="row">
			<div class="col-md-3" v-for="vo in food_list">
				<a :href="'../food/detail.do?fno='+vo.fno">
				    <div class="thumbnail">
				    	<!-- 속성값에 데이터를 가져올 때는 '클론(:)' 사용 -->
				        <img :src="'https://www.menupan.com/'+vo.poster" alt="" style="width:100%">
				        <div class="caption">
				        	<!-- 태그와 태그 사이에 데이터를 가져올 때는 '{{}}' 사용 -->
				          	<p>{{vo.name}}</p>
				        </div>
				    </div>
			    </a>
			</div>
		</div>
		
		<div style="height:20px;"></div>
		
		<div class="row">
			<div class="text-center">
				<ul class="pagination">
					<li v-if="startPage>1"><a class="link" @click="prev()">&laquo;</a></li> <!-- 1 페이지일 때 이전 버튼 숨김 -->
					<li v-for="i in range(startPage, endPage)" :class="curpage===i?'active':''"><a href="#"  @click="pageChange(i)">{{i}}</a></li> <!-- 현재 페이지는 파란색 처리 => 삼항연산자로 구현 -->
					<li v-if="endPage<totalpage"><a class="link" @click="next()">&raquo;</a></li> <!-- 마지막 페이지가 총 페이지보다 적을 때 다음 버튼 노출 -->
				</ul>
			</div>
		</div>
	</div>

<!--
	Model
		
-->

<script>
	let app=Vue.createApp({
		// Model : 멤버 변수 설정 => 변경된 내용을 View
		data(){
			return{
				food_list:[],
				curpage:1,
				totalpage:0,
				startPage:0,
				endPage:0
			}
		},
		// ViewModel : 기능 처리
		// 시작과 동시에 데이터 처리
		mounted(){
			this.dataRecv();
		},
		// 사용자 정의 메소드 => 이벤트 처리
		methods:{
			dataRecv(){
				axios.get('../food/list_vue.do', {
					params:{
						page:this.curpage
					}
				}).then(response=>{
					console.log(response.data)
					this.food_list=response.data
				})
				
				// 페이지
				axios.get('../food/page_vue.do', {
					params:{
						page:this.curpage
					}
				}).then(response=>{
					console.log(response.data)
					this.curpage=response.data.curpage
					this.totalpage=response.data.totalpage
					this.startPage=response.data.startPage
					this.endPage=response.data.endPage
				})
			},
			
			range(start, end){
				let arr=[];
				let len=end-start;
				for(let i=0; i<=len; i++) {
					arr[i]=start;
					start++;
				}
				return arr;
			},
			
			prev(){
				this.curpage=this.startPage-1
				this.dataRecv()
			},
			
			next(){
				this.curpage=this.endPage+1
				this.dataRecv()
			},
			
    		pageChange(page){
    			this.curpage=page
    			this.dataRecv()
    		}
		},
		// 상태가 변경되었을 때 => data(return{}) 안에 있는 데이터 값이 변경되었을 때)
		updated(){
			
		}
	}).mount('.container')
</script>
</body>
</html>