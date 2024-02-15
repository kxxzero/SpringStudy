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
<style>
a.link, .img_click{
	cursor:pointer;
}
</style>
</head>
<body>
	<div class="wrapper row3">
	  <main class="container clear"> 
	    <!-- main body --> 
	    <div class="content" id="recipeApp">
	      <h2 class="sectiontitle">레시피</h2>
	      <div id="gallery">
	        <figure>
	          <header class="heading">총 <span style="font-size:20px; color: green">{{count||currency}}</span>개의 맛있는 레시피가 있습니다.</header>
	          <ul class="nospace clear">
	            <li v-for="(vo,index) in recipe_list" class="index%4==0?'one_quarter first':'one quarter'"><a :href="'../recipe/recipe_list_detail.do?fno='+vo.fno"><img :src="vo.poster" :title="vo.title"></a></li>
	          </ul>
	          <figcaption></figcaption>
	        </figure>
	      </div>
	      <nav class="pagination">
	        <ul>
	          <li v-if="startPage>1"><a @click="prev()" class="link">&laquo; Previous</a></li>
	          <li v-for="i in range(startPage, endPage)" class="i==curpage?'current':''"><a @click="pageChange()">{{i}}</a></li>
	          <li v-if="endPage<totalpage"><a @click="next()" class="link">Next &raquo;</a></li>
	        </ul>
	      </nav>
	    </div>
	    <!-- / main body -->
	    <div class="clear"></div>
	  </main>
	</div>
<script>
let recipeApp=Vue.createApp({
	data(){
		return{
			recipe_list:[],
			curpage:1,
			totalpage:0,
			startPage:0,
			endPage:0,
			count:''
		}
	}, 
	filters:{
		currency:function(value){
			let num=new Number(value);
			return num.toFixed(0).replace(num.toFixed(0).replace(/(\d)(?=(\d{3})+(?:\.\d+)?$)/g, "1,"))
		}
	},		
	mounted(){
		// 브라우저에서 화면에 HTML이 실행된 경우에 처리 => 자동 호출 함수
		/*
			자동 호출 함수 => Vue의 생명 주기
				beforeCreate()
				created()
				-------------------- Vue 객체 생성
				beforeMount() => mount : 가상 메모리에 HTNL을 올리는 경우
				*** mounted() => window.onload, $(function(){}), componentDidMount()
																=> HOOKS
																=> useEffect()
																=> class/function = 권장
				beforeUpdate
				***updated()
		*/
		this.dataRecv()
	},
	updated(){
		
	},
	methods:{
		dataRecv(){
			axios.get('../recipe/recipe_test_vue.do',{
				params:{
					page:this.curpage
				}
			}).then(response=>{
				console.log(response.data)
				this.recipe_list=response.data.list
				this.curpage=response.data.pages.curpage
				this.totalpage=response.data.pages.totalpage
				this.startPage=response.data.pages.startPage
				this.endPage=response.data.pages.endPage
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
		pageChange(){
			this.curpage=page
			this.dataRecv()
		}
	}
}).mount('#recipeApp')
</script>	
</body>
</html>