<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
  google.charts.load("current", {packages:["corechart"]});
  google.charts.setOnLoadCallback(drawChart);
  function drawChart() {
    var data = google.visualization.arrayToDataTable([
      ['단어', '단어 횟수'],
      <c:forEach var="vo" items="${list}">
		['<c:out value="$"{vo.word}"/>',	<c:out value="${vo.count}"/>], // 배열이기 때문에 ,를 붙여줌 => 자바스크립트는 ,를 붙여도 상관 없음
	  </c:forEach>
    ]);

    var options = {
      title: '내용 분석',
      is3D: true,
    };

    var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
    chart.draw(data, options);
  }
</script>
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script> <!-- axios : 전송 객체 => 데이터 입·출력 시 사용 -->
</head>
<body>
	<div class="wrapper row3 row1" id="fboardApp">
		<main class="container clear">
			<h2 class="sectiontitle">내용 보기</h2>
			<table class="table">
				<tr>
					<th width="20%" class="text-center">번호</th>
					<td width="30%" class="text-center"></td>
					<th width="20%" class="text-center">작성일</th>
					<td width="30%" class="text-center"></td>
				</tr>
				<tr>
					<th width="20%" class="text-center">이름</th>
					<td width="30%" class="text-center"></td>
					<th width="20%" class="text-center">조회수</th>
					<td width="30%" class="text-center"></td>
				</tr>
				<tr>
					<th width="20%" class="text-center">제목</th>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td colspan="4" class="text-center" valign="top" height="200"></td>
					<pre style="white-space:pre-wrap; border:none; background-color:white;"></pre>
				</tr>
				<tr v-show="isShow">
					
				</tr>
			</table>
			<div id="piechart_3d" style="width: 900px; height: 500px;"></div>
		</main>
	</div>
<script>
	let fApp=Vue.createApp({
		data(){
			return{
				no:${no},
				detail_data:{},
				data_list:[]
			}
		},
		mounted(){
			
		},
		update(){
			
		},
		methods:{
			boardDelete(){
				if(this.pwd===""){
					this.$refs.pwd.focus()
					// $('#pwd').focus()
					return
				}
				// delete_vue.do?no=1&pwd=1234 => params의 역할
				axios.get('../freeboard/delete_vue.do',{
					params:{
						no:this.no,
						pwd:this.pwd
					}
				}).then(response=>{
					if(response.data==="yes") {
						location.href="../freeboard/list.do"
					} else {
						alert("비밀번호가 틀립니다!")
						this.pwd=""
						this.$refs.pwd.focus()
					}
				})
			},
			updateForm(){
				location.href="../freeboard/update.do?no="this+no
			}
		}
	}).mount('#fboardApp')
</script>
</body>
</html>