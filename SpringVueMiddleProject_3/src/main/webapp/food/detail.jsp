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
.row2{
	width: 600px;
}
</style>
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script> <!-- axios : 전송 객체 => 데이터 입·출력 시 사용 -->
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=74c8ca8100e4e559f7de6e3bf17641b2&libraries=services"></script>
</head>
<body>
<jsp:include page="${login_jsp}"></jsp:include>
<!-- fno, score, poster, name, type, address, phone, theme, price, time, seat -->
<div class="container" id="app1">
	<div class="row row1">
		<table class="table">
			<tr>
				<td width=30% class="text-center" rowspan="8">
					<img :src="'https://www.menupan.com/'+vo.poster" style="width:100%">
				</td>
				<td colspan="2">
					<h3><span id="name">{{vo.name}}</span>&nbsp;<span style="color: orange">{{vo.score}}</span></h3>
				</td>
			</tr>
			
			<tr>
				<td class="text-center" width="10%">음식종류</td>
				<td width="60%">{{vo.type}}</td>
			</tr>
			
			<tr>
				<td class="text-center" width="10%">주소</td>
				<td width="60%">{{vo.address}}</td>
			</tr>
			
			<tr>
				<td class="text-center" width="10%">전화</td>
				<td width="60%">{{vo.phone}}</td>
			</tr>
			
			<tr>
				<td class="text-center" width="10%">테마</td>
				<td width="60%">
					<ul>
						<li v-for="t in theme">{{t}}</li>
					</ul>
				</td>
			</tr>
			
			<tr>
				<td class="text-center" width="10%">가격대</td>
				<td width="60%">{{vo.price}}</td>
			</tr>
			<tr>
				<td class="text-center" width="10%">영업시간</td>
				<td width="60%">{{vo.time}}</td>
			</tr>
			<tr>
				<td class="text-center" width="10%">좌석</td>
				<td width="60%">{{vo.seat}}</td>
			</tr>
			<tr>
	        	<td colspan="2" class="text-right">
	        		<input type="button" value="목록" class="btn btn-xs btn-primary" onclick="javascript:history.back()">
	        	</td>
        	</tr>
		</table>
	</div>
    
    <div style="height: 20px"></div>
    
    <div class="row">
		<div id="map" style="width:100%; height:350px;"></div>
    </div>
    
    <div class="container" id="app2">
    	<div class="row row2">
    	
    	</div>
    </div>
</div>
	
<script>
let app=Vue.createAPP({
	data(){
		return{
			vo:{},
			fno:${fno},
			address:'',
			name:'',
			theme:[],
			sessionId:''
		}
	},
	mounted(){
		axios.get('../food/detail_vue.do', {
			params:{
				fno:this.fno
			}
		}).then(response=>{
			console.losg(response.data)
			this.vo=response.data
			this.theme=response.data.theme.split(",")
			this.address=response.data.address
			this.name=response.data.name
			
			if(window.kakao && window.kakao.maps) {
				this.initMap()
			} else {
				this.addScript()
			}
		})
	},
	updated(){
		
	},
	methods:{
		addScript(){
			const script=document.createElement("script") // <script>
			// globel kakao
			script.onload=()=>kakao.maps.load(this.initMap)
			script.src="http://dapi.kakao.com/v2/maps/sdk.js?appkey=74c8ca8100e4e559f7de6e3bf17641b2&libraries=services"
			document.head.appendChild(script)	
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
		            content: '<div style="width:150px;text-align:center;padding:6px 0;">우리회사</div>'
		        });
		        infowindow.open(map, marker);

		        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
		        map.setCenter(coords);
		    } 
		});    
		}
	}
}).mount('#app1')


let app2=Vue.createApp({

}).mount('#app2')
</script>
</body>
</html>