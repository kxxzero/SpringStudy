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
	<div class="container" id="goodsListApp">
		<div class="row">
			<div class="col-md-3" v-for="vo in goods_list">
			    <div class="thumbnail">
			      <a href="#">
			        <img :src="vo.poster" style="width:100%">
			        <div class="caption">
			          <p>{{vo.name}}</p>
			        </div>
			      </a>
			    </div>
			</div>
		</div>	
	</div>
<script>
let goodsApp=Vue.createApp ({
	data() {
		return {
			goods_list:[],
			curpage: 1,
			totalpage:0,
			startPage:0,
			endPage:0
		}
	},
	mounted(){
		this.dataRecv()
	},
	updated(){
		
	},
	methods:{
		dataRecv(){
			axios.get('../goods/list_vue.do', {
				params:{
					page:this.curpage
				}
			}).then(response=>{
				console.log(response.data)
				this.goods_list=response.data
				
			})
			
			axios.get('../goods/page_vue.do', {
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
		}
	},
	components:{
		
	}
})
goodsApp.mount('#goodsListApp')
</script>
</body>
</html>