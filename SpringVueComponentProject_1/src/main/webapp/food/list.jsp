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
</style>
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
	</div>
<script>
let foodApp=Vue.createApp({
	// 멤버 변수 => HTML에 값을 전송
	data() {
		return {
			food_list:[],
			curpage: 1,
			totalpage: 0,
			startPage: 0,
			endPage: 0
		}
	},
	mounted(){
		// 콜백 => 시작과 동시에 호출 => window.onload => Vue에 의한 자동 호출
		this.dataRecv()
	},
	updated(){
		// 자동 호출되는 함수 => data()에 등록된 값이 변경이 되는 경우에 호출
	},
	// 사용자 정의 함수 : 이벤트(버튼 클릭, 이미지 클릭)
	methods:{
		// 중복 코딩이 있는 경우 처리하는 용도
		dataRecv(){
			// 서버 연결(스프링) 함수
			axios.get('../food/list_vue.do',{
				params:{
					page:this.curpage // list_vue.do?page=1 => data:{no:1}
				}
			}).then(response=>{
				console.log(response.data)
				this.food_list=response.data
			})
			
			// 페이지 읽기
			axios.get('../food/page_vue.do', {
				params:{
					page:this.curpage
				}
			}).then(response=>{
				// response.data={curpage:1, totalpage:20, startPage:1, endPage:10}
				console.log(response.data)
				this.curpage=response.data.curpage
				this.totalpage=response.data.totalpage
				this.startPage=response.data.startPage
				this.endPage=response.data.endPage
			})
		}
	},
	components:{
		
	}
	// computed, watch, filter
})
foodApp.mount('#foodListApp')
</script>
</body>
</html>