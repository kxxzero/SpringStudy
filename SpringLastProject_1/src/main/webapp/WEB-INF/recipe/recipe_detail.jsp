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
a.link, .img_click{
	cursor:pointer;
}
</style>
</head>
<body>
	<div class="wrapper row3">
	  <main class="container clear"> 
	    <h2 class="sectiontitle">레시피 상세보기</h2>
	    <div class="content" id="recipeApp">
		    <table class="table">
		    	<tr>
		    		<td colspan="3" class="text-center">
		    			<img :src="recipe_data.poster" style="width:100%; height: 300px;">
		    		</td>
		    	</tr>
		    	<tr>
		    		<td colspan="3" class="text-center">
		    			<h3>{{recipe_data.title}}</h3>
		    		</td>
		    	</tr>
		    	<tr>
		    		<td colspan="3" class="text-center">
		    			<pre style="white-space:pre-wrap; background: #fff; border:none">{{recipe_data.content}}</pre>
		    		</td>
		    	</tr>
		    	<tr>
		    		<td class="text-center"><img src="../images/icon/a1.png"></td>
		    		<td class="text-center"><img src="../images/icon/a2.png"></td>
		    		<td class="text-center"><img src="../images/icon/a3.png"></td>
		    	</tr>
		    	<tr>
		    		<td class="text-center">{{recipe_data.info1}}</td>
		    		<td class="text-center">{{recipe_data.info2}}</td>
		    		<td class="text-center">{{recipe_data.info3}}</td>
		    	</tr>
		    	<tr>
		    		<td colspan="3">
		    			<h3>[재료]</h3>
		    			<div v-for="s in stuff"><a class="link" @click="find(s)">{{s}}</a></div>
		    		</td>
		    	</tr>
		    	<tr>
		    		<td colspan="3">
		    			<h3>조리 순서</h3>
		    		</td>
		    	</tr>
		    	<tr>
		    		<td colspan="3">
		    			<table class="table" v-for="(m, index) in make">
		    				<tr>
		    					<td width="70%"></td>
		    					<td width="30%">
		    						<img :src="posterPrint(index)" style="width:200px; height: 100px; class="img-rounded">
		    					</td> 
		    				</tr>
		    			</table>
		    		</td>
		    	</tr>
		    	<tr>
		    		<td colspan="3">
		    			<table class="table">
		    				<caption><h3>레시피 작성자</h3></caption>
		    				<tr>
		    					<td width="20%" class="text-center">
		    						<img :src="recipe_data.chef_poster" style="width:150px; height: 80px;" class="img-circle">
		    					</td>
		    					<td width="80%"><b>{{recipe_data.chef}}</b></td>
		    				</tr>
		    				<tr>
		    					<td width="80%">{{recipe_data.chef_profile}}</td>
		    				</tr>
		    			</table>
		    		</td>
		    	</tr>
		    </table>
	    </div>
	  </main>
	  <div id="dialog" title="레시피 관련 상품" v-show="isShow">
	  	<goods-data v-bind:goods="goods"></goods-data>
	  </div>
	</div>
<script>
const goods={
		props:['goods']
		template:
				`<div class="container">
					<div class="row">
					  <div class="col-md-4" v-for="g in goods">
					    <div class="thumbnail">
					        <img :src="g.goods_poster" :title="g.good_name" style="width:100%">
					        <div class="caption">
					          <p>{{g.goods_price}}</p>
					        </div>
					    </div>
					  </div>
					</div>
				</div>`
}

	let recipeApp=Vue.createApp({
		// Model
		data(){
			return{
				recipe_data:{},
				no:${no},
				stuff:[],
				make:[],
				poster:[],
				goods:[]
				isShow:false
			}
		},
		// VM
		mounted(){
			axios.get('../recipe/recipe_detail_vue.do', {
				params:{
					no:this.no
					// recipe_detail_vue.do?no=1&...
				}
			}).then(response=>{
				console.log(response.data)
				this.recipe
				this.stuff=response.data.stuff.split(',')
				let makedata=response.data.foodmake.split('\n')
				let poster=[]
				let make=[]
				// ......^http...
				for(let i=0; i<makedata.length-1; i++){
					let aa=makedata[i].split("\\^")
					make[i]=aa[0]
					poster[i]=aa[1]
				}
				colsole.log(poster)
				colsole.log(make)
			})
		},
		methods:{
			posterPrint(i){
				return this.poster[i]
			},
			find(s){
				let ss=s.substring(0,s.indexOf(''))
				/* alert(ss.trim()) */
				axios.get('../recipe/goods_vue.do,'{
					params:{
						stuff:ss
					}
				}).then(response=>{
					console.log(response.data)
					this.goods=response.data
					
					this.isShow=true
					$('#dialog').dialog({
						autoOpen:false,
						modal:true,
						width:700,
						height:500
					}).dialog('open')
				})
			}
		},
		component(){
			'goods-data':goods
		}
	}).mount('#recipeApp')
</script>
</body>
</html>