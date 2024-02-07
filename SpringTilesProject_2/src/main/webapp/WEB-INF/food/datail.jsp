<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script> <!-- axios : 전송 객체 => 데이터 입·출력 시 사용 -->
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<style>
a.alink{
	cursor: pointer;
}
</style>
</head>
<body>
	<div class="container" id="detailApp">
		<div class="row">
			<table class="table">
				<tr>
					<td width="30%" class="text-center" rowspan="8">
						<img :src="'https://www.menupan.com'+detail_data.poster" style="width:100%">
					</td>
					<td colspan="2">
						<h3>{{detail_data.name}}&nbsp;<span style="color:orange">{{detail_data.score}}</span></h3>
					</td>
				</tr>
				<tr>
					<td width="10%">주소</td>
					<td width="60%">{{detail_data.address}}</td>
				</tr>
				<tr>
					<td width="10%">전화</td>
					<td width="60%">{{detail_data.phone}}</td>
				</tr>
				<tr>
					<td width="10%">음식 종류</td>
					<td width="60%">{{detail_data.type}}</td>
				</tr>
				<tr>
					<td width="10%">가격대</td>
					<td width="60%">{{detail_data.price}}</td>
				</tr>
				<tr>
					<td width="10%">영업 시간</td>
					<td width="60%">{{detail_data.time}}</td>
				</tr>
				<tr>
					<td width="10%">좌석</td>
					<td width="60%">{{detail_data.seat}}</td>
				</tr>
				<tr>
					<td width="10%">테마</td>
					<td width="60%">{{detail_data.theme}}</td>
				</tr>
				<tr>
					<td colspan="3" class="text-right">
						<a href="javascript:history.back()" class="btn btn-sm btn-danger">목록</a>
					</td>
				</tr>
			</table>
		</div>
	</div>
	
	<!-- component -->
	<div class="container" id="replyApp">
		<div class="row">
			<table class="table">
				<tr>
					<td>
						<table class="table" v-for="vo in replyt_list">
							<tr>
								<td class="text-left">◑{{vo.name}}({{vo.dbday}})</td>
								<td class="text-right">
									<span v-if="vo.id===session">
										<input type="button" value="수정" class="btn-xs btn-success" style="margin-right:5px" :id="''+vo.rno" @click="replyUpdataForm(vo.rno)">
										<input type="button" value="삭제" class="btn-xs btn-info" @click="replyDelete(vo.rno)">
									</span>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<pre style="white-space:pre-wrap; background-color:white; border:none">{{vo.msg}}</pre>
								</td>
							</tr>
							
							<tr :id="'u'+vo.rno" class="ups" style="display:none">
								<td colspan="2">
									<textarea rows="5" cols="90" style="float:left" v-model="umsg" ref="umsg">{{vo.msg}}</textarea>
									<input type="button" value="댓글수정" style="float: left; height:96px;" class="btn-danger">
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<table class="table" v-show="sessionId!=''">
				<tr>
					<td class="text-left">
						<textarea rows="5" cols="90" style="float:left" v-model="msg" ref="msg"></textarea>
						<input type="button" value="댓글쓰기" style="float: left; height:96px;" class="btn-danger" @click="replyInsert()">
					</td>
				</tr>
			</table>
		</div>
	</div>
<script>
	let detailApp=Vue.createApp({
		data(){
			return {
				fno:${fno},
				detail_data:{}
			}
		},
		mounted(){
			axios.get('../food/detail_vue.do'.{
				params:{
					fno:this.fno
				}
			}).then(response=>{
				console.log(response.data)
				this.detail_data=response.data
			}
		},
		updated(){
			
		},
		methods:{
			
		}
	}).mount('#detailApp')
	
	
	let replyApp=Vue.createApp({
		data(){
			return {
				fno:${fno},
				session:'${id}',
				reply_list:[],
				rno:0,
				msg:'',
				umsg:'',
				/* isShow:false, */
				bCheck:true
			}
		},
		mounted(){
			// 시작과 동시에 실행
			axios.get('../reply/list_vue.do', {
				params:{
					fno:this.fno
				}
			}).then(response=>{
				this.reply_list=response.data
			})
		},
		methods:{
			// 수정
			replyUpdateForm(rno){
				$('.ups').hide();
				$('.updates').text("수정")
				if(bCheck===true){
					%('#u'+rno).show()
					$('#up'+rno).text("취소")
					this.bCheck=false
				} else {
					%('#u'+rno).hide()
					$('#up'+rno).text("수정")
					this.bCheckt=true
				}
			},
			replyUpdate(rno){
				let msg=$('msg'+rno).val();
				axios.get('../reply/update_vue.do',{
					params:{
						rno:rno,
						fno:this.fno,
						msg:msg
					}				
				}).then(response=>{
					this.reply_list=response.data
					$('#u'+rno).hide()
					$('#up'+rno).attr("value","수정")
				})
			}
			
			// 삭제
			replyDelete(rno){
				axios.get("../reply/delete_vue.do", {
					params:{
						rno:rno, // vue.do?rno=1
						fno:fno
					}
				}).then(response=>{
					this.reply_list=response.data
				})
			},
			
			// 추가
			replyInsert({
				if(this.msg===''){
					this.$refs.msg.focus()
					return
				}
				
				axios.get('../reply/insert_vue.do', {
					params:{
						fno:this.fno,
						msg:this.msg
					}
				}).then(response=>{
					this.reply_list=response.data
					this.msg=''
				})
			})
		}
	}).mount('#replyApp')
</script>
</body>
</html>