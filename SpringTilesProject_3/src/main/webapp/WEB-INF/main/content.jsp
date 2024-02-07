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
	<div class="container" id="foodListApp">
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
		
		<div style="height:20px"></div>
		
		<div class="row">
			<div class="text-center">
				<ul class="pagination">
					<li v-if="startPage>1"><a href="#">&laquo;</a></li>
					<li v-for="i in range(startPage, endPage)" :class="i===curpage?'active':''"><a href="#">{{i}}</a></li>
					<li v-if="endPage<totalpage"><a href="#">&raquo;</a></li>
				</ul>
			</div>
		</div>
	</div>
	
<script>
	let foodlistApp=Vue.createApp({
		data(){
			return{
				food_list:[],
				curpage:1,
				totalpage:0,
				startPage:0,
				endPage:0
			}
		},
		mounted(){
			// 첫페이지 로딩 => 자동 호출
			this.dataRecv()
		},
		methods:{
			dataRecv(){
				axios.get('../food/list_vue.do', {
					params:{
						page:this.curpage
					}
				}).then(result=>{ // result => 변수
					console.log(result.data)
					this.food_list=result.data
				})
				
				// 페이지 읽기
				/*
					axios.get => @GetMapping
					axios.post => @PostMapping
					
					=> ajax({
						type:'get', 'post'
					})
					=> <form method="get, post"> 
				*/
				axios.get('../food/page_vue.do', {
					params:{
						page:this.curpage
					}
				}).then(response=>{
					// {curpage:1, totalpage:10, startPage:1, endPage:10} => Map
					this.curpage=response.data.curpage
					this.totalpage=response.data.totalpage
					this.startPage=response.data.startPage
					this.endPage=response.data.endPage
				})
			},
			
			// 블록별 페이지 출력 => v-for="받는 변수 in 배열" => th:each="#number{startPage, endPage}"
			range(start, end){
				let arr=[]
				let leng=end-start
				for(let i=0; i<=leng; i++){
					arr[i]=start
					start++
				}
				return arr
				
			}
		}
	}).mount('#foodListApp')
</script>
</body>
</html>