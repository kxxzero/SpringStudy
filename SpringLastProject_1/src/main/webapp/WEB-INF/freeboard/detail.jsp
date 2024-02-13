<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
  google.charts.load("current", {packages:["corechart"]});
  google.charts.setOnLoadCallback(drawChart);
  function drawChart() {
    var data = google.visualization.arrayToDataTable([
      ['Task', 'Hours per Day'],
      ['Work',     11],
      ['Eat',      2],
      ['Commute',  2],
      ['Watch TV', 2],
      ['Sleep',    7]
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
	<div class="wrapper row3">
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
			
		}
	}).mount('#fboardApp')
</script>
</body>
</html>