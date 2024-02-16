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
	<div class="wrapper row3" id="foodApp">
	  <main class="container clear">
	  	<h2 class="sectiontitle">맛집 상세 보기</h2>
	    <div class="sidebar two third first"> 
			<div class="row">
		      <table class="table">
		        <tr>
		         <td colspan="2" class="text-center">
		           <img :src="'https://www.menupan.com'+food_detail.poster" style="width:500px">
		         </td>
		         <td colspan="2">
		          <h3><span id="name">{{vo.name}}</span>&nbsp;<span style="color: orange">{{vo.score}}</span></h3>
		         </td>
		        </tr>
		        
		        <tr>
		          <td width="15%">음식 종류</td>
		          <td width="55%">{{food_detail.type}}</td>
		        </tr>
		        <tr>
		          <td width="15%">주소</td>
		          <td width="55%">{{food_detail.address}}</td>
		        </tr>
		        <tr>
		          <td width="15%">전화</td>
		          <td width="55%">{{food_detail.phone}}</td>
		        </tr>
		        <tr>
		          <td width="15%">가격대</td>
		          <td width="55%">{{food_detail.price}}</td>
		        </tr>
		        <tr>
		          <td width="15%">좌석</td>
		          <td width="55%">{{food_detail.seat}}</td>
		        </tr>
		        <tr>
		          <th width="15%">테마</th>
		          <td width="55%">{{food_detail.theme}}</td>
		        </tr>
		        <tr>
		          <td colspan="3" class="text-right inline">
		          	<input type=button value="찜하기" class="btn-xs btn-success">
		           	<input type=button value="예약" class="btn-xs btn-info">
		           	<input type=button value="목록" class="btn-xs btn-warning" @click="goback()">
		          </td>
		        </tr>
		      </table>
		    </div>
	    </div>
	    
	    <div class="one third">
			<div id="map" style="width:100%; height:350px;"></div>
		</div>
	    <div class="clear"></div>
	  </main>
	</div>
<script>
let foodApp=Vue.createApp({
	data(){
		return{
			detail_data:{},
			fno:${fno}
		}
	},
	mounted(){
		axios.get("../food/food_detail_vue.do',{
			params:{
				fno:this.fno
			}
		}).then(response=>{
			console.log(response.data)
			this.food_detail=response.data
			if(window.kakao && window.kakao.maps){
				this.initMap()
			} else {
				this.addScript()
			}
		})
	},
	methods:{
		addScript(){
			const script=document.createElement("script")
			/*global kakao*/
			script.onload()=>kakao.maps.load(this.initMap)
			script.src=""
			document.head.appendChile(script)
		},
		ininMap(){
			var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		    mapOption = {
		        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
		        level: 3 // 지도의 확대 레벨
		    };  

			// 지도를 생성합니다    
			var map = new kakao.maps.Map(mapContainer, mapOption); 
	
			// 주소-좌표 변환 객체를 생성합니다
			var geocoder = new kakao.maps.services.Geocoder();
	
			// 주소로 좌표를 검색합니다
			geocoder.addressSearch(this.address, function(result, status) {
	
			    // 정상적으로 검색이 완료됐으면 
			     if (status === kakao.maps.services.Status.OK) {
	
			        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
	
			        // 결과값으로 받은 위치를 마커로 표시합니다
			        var marker = new kakao.maps.Marker({
			            map: map,
			            position: coords
			        });
	
			        // 인포윈도우로 장소에 대한 설명을 표시합니다
			        var infowindow = new kakao.maps.InfoWindow({
			            content: '<div style="width:150px;text-align:center;padding:6px 0;">'+$("name").text()+'</div>'
			        });
			        infowindow.open(map, marker);
	
			        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
			        map.setCenter(coords);
			    } 
			});
		},
		goback(){
			location.href="../food/food_list.do"
		}
	}
}).mount('#foodApp')
</script>
</body>
</html>