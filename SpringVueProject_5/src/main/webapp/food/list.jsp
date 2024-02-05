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
.images:hover{
   cursor:pointer;
}
</style>
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script> 
<!-- axios : 전송 객체 -->
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-3" v-for="vo in food_list">
			    <div class="thumbnail">
			      <a href="#">
			        <img :src="'https://www.menupan.com'+vo.poster" style="width:100%">
			        <div class="caption">
			          <p>{{vo.name}}</p>
			        </div>
			      </a>
			    </div>
			</div>
		</div>
		
		<div class="row">
			<div class="text-center">
				<ul class="pagination">
					<li v-if="startPage>1"><a href="#" @click="prev()">&laquo;</a></li> <!-- 1 페이지일 때 이전 버튼 숨김 -->
					<li v-for="i in range(startPage, endPage)" :class="i===curpage?'active':''"><a href="#"  @click="pageChange(i)">{{i}}</a></li> <!-- 현재 페이지는 파란색 처리 => 삼항연산자로 구현 -->
					<li v-if="endPage<totalpage"><a href="#" @click="next()">&raquo;</a></li> <!-- 마지막 페이지가 총 페이지보다 적을 때 다음 버튼 노출 -->
				</ul>
			</div>
		</div>
	</div>
	
<script>
	let app=Vue.createApp({
		data(){
			return {
				food_list :[],
				curpage:1,
				totalpage:0,
				startPage:0,
				endPage:0
			}
		},
		mounted(){
			this.dataSend(); 
			// 반복되는 공통 부분을 함수화 시켜서 편리하게 호출
		},
		// 사용자 정의 함수
		methods:{
			dataSend(){
				axios.get("http://localhost:8080/web/food/list_vue.do", {
					params:{
						page:this.curpage
					}
				}).then(response=>{
					console.log(response.data)
					this.food_list=response.data;
					this.curpage=response.data[0].curpage
					this.totalpage=response.data[0].totalpage
					this.startPage=response.data[0].startPage
					this.endPage=response.data[0].endPage
				})
			},
			
			// 숫자 => 배열로 저장
			range(start, end){
				let arr=[];
				let len=end-start
				for(let i=0; i<=len; i++){
					arr[i]=start;
					start++;	
				}
				return arr;
			},
			
			// 페이지 관련 함수
			pageChange(page){
				this.curpage=page
				this.dataSend()
			},
			
			prev(){
				this.curpage=this.startPage-1
				this.dataSend()
			},
			
			next(){
				this.curpage=this.endPage+1
				this.dataSend()
			}
		}
	}).mount('.container')
</script>
</body>
</html>