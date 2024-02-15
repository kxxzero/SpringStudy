<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script> <!-- axios : 전송 객체 => 데이터 입·출력 시 사용 -->
<style>
.row1{
	margin: 0 auto;
	width: 960px;
}
.img-circle{
	width: 120px;
	height: 120px;
}
.icon{
	width: 24px;
}
a.link, .img_click{
	cursor:pointer;
}
</style>
</head>
<body>
	<div class="wrapper row3" id="chefApp">
		<div clas="row1">
		  <main class="container clear"> 
		  	<h2 class="sectiontitle">쉐프 목록</h2>
		  	<table class="table">
		  		<tr>
		  			<td>
		  				<table class="table" v-for="vo in chef_list">
		  					<tr>
		  						<td width="20%" rowspan="2" class="text-center">
		  							<a :href="'../recipe/chef_detail.do?cno='+vo.cno">
		  								<img :src="vo.poster" class="img-circle">
		  							</a>
		  						</td>
		  						<td colspan="4">
		  							<h3 style="color:orange"><a :href="../recipe/chef_detail.do?cno='+vo.cno">{{vo.chef}}</a></h3>
		  						</td>
		  					</tr>
		  					<tr>
		  						<td class="text-center"><img class="icon" src="../images/icon/m1.png">{{vo.mem_cont1.toLocaleString()}}</td>
		  						<td class="text-center"><img class="icon" src="../images/icon/m2.png">{{vo.mem_cont2.toLocaleString()}}</td>
		  						<td class="text-center"><img class="icon" src="../images/icon/m3.png">{{vo.mem_cont7.toLocaleString()}}</td>
		  						<td class="text-center"><img class="icon" src="../images/icon/m4.png">{{vo.mem_cont4.toLocaleString()}}</td>
		  					</tr>
		  				</table>
		  			</td>
		  		</tr>
		  	</table>
		  	<div style="height:20px"></div>
		  	<div class="row">
		  		<div class="pagination">
		  			<ul>
		  				<li v-if="startPage>1"><a class="link" @click="prev()">&laquo;</a></li>
		  				<li v-for="i in range(startPage, endPage)" :class="i===curpage?'current':''"><a class="link" @click="pageChange(page)">{{i}}</a></li>
		  				<li v-if="endPage<totalpage"><a class="link" @click="next()">&raquo;</a></li>
		  			</ul>
		  		</div>
		  	</div>
		  </main>
	  </div>
	</div>
<script>
let chefApp=Vue.createApp({
	data(){
		return{
			chef_list:[],
			curpage:1,
			totalpage:0,
			startPage:0,
			endPage:0
		}
	},
	mounted(){
		this.dataRecv()
	},
	methods:{
		dataRecv(){
			axios.get('../recipe/chef_list_vue.do',{
				params:{
					page:this.curpage
				}
			}).then(response=>{
				console.log(response.data)
				this.chef_list=response.data
			})
			
			// 페이지
			axios.get('../recipe/chef_page_vue.do',{
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
			let arr=[]
			let leng=end-start
			for(let i=0; i<=leng; i++){
				arr[i]=start
				start++
			}
			return arr
		},
		prev(){
			this.curpage=this.endPage-1
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
	}
}).mount('#chefApp')
</script>
</body>
</html>