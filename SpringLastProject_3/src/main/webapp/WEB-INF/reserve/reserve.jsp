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
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
	<div class="wrapper row3">
	  <main class="container clear"> 
      <h2 class="sectiontitle">맛집 예약</h2>
      
      <table class="table">
        <tr>
          <td>
	          <input type="button" class="btn-xs btn-danger" vlaue="한식" @click="foodSelect('한식')">
	          <input type="button" class="btn-xs btn-danger" vlaue="양식" @click="foodSelect('양식')">
	          <input type="button" class="btn-xs btn-danger" vlaue="일식" @click="foodSelect('일식')">
	          <input type="button" class="btn-xs btn-danger" vlaue="중식" @click="foodSelect('중식')">
          </td>          
        </tr>        
      </table>
      <div style="overflow-y:auto; height: 700px">
        <table class="table">
          <tr v-for="">
        </table>
      </div>
      <table class="teble">
      
      <tr v-show="rShow">
        <td class="text-right" width="60%">
          <input type="button" class="btn-lg, btn-primary" value="예약하기">
      </tr>
      </table>
    </main>
	</div>
<script>
let rApp=Vue.createApp({
  data(){
    return {
      weekNames: ["월요일", "화요일", "수요일","목요일", "금요일", "토요일", "일요일"],
          rootYear: 1904,
          rootDayOfWeekIndex: 4, // 2000년 1월 1일은 토요일
          currentYear: new Date().getFullYear(),
          currentMonth: new Date().getMonth()+1,
          currentDay: new Date().getDate(),
          currentMonthStartWeekIndex: null,
          currentCalendarMatrix: [],
          endOfDay: null,
          memoDatas: [],
          realDay:new Date().getDate(),
          type='한식',
          food_list:[],
          fno:0,
          time_list:['12:00', '13:00', '14:00']
    
          
    }
  },
  mounted(){
    this.init()
  },
  methods:{ 
      init(){
          this.currentMonthStartWeekIndex = this.getStartWeek(this.currentYear, this.currentMonth);
          this.endOfDay = this.getEndOfDay(this.currentYear, this.currentMonth);
          this.initCalendar();
        },
        initCalendar(){
          this.currentCalendarMatrix = [];
          let day=1;
          for(let i=0; i<6; i++){
            let calendarRow = [];
            for(let j=0; j<7; j++){
              if(i==0 && j<this.currentMonthStartWeekIndex){
                calendarRow.push("");
              }
              else if(day<=this.endOfDay){
                calendarRow.push(day);
                day++;
              }
              else{
                calendarRow.push("");
              }
            }
            this.currentCalendarMatrix.push(calendarRow);
          }
        },
        getEndOfDay(year, month){
            switch(month){
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                  return 31;
                  break;
                case 4:
                case 6:
                case 9:
                case 11:
                  return 30;
                  break;
                case 2:
                  if( (year%4 == 0) && (year%100 != 0) || (year%400 == 0) ){
                  return 29;   
                  }
                  else{
                      return 28;
                  }
                  break;
                default:
                  console.log("unknown month " + month);
                  return 0;
                  break;
            }
        },
        getStartWeek(targetYear, targetMonth){
          let year = this.rootYear;
          let month = 1;
          let sumOfDay = this.rootDayOfWeekIndex;
          while(true){
            if(targetYear > year){
              for(let i=0; i<12; i++){
                sumOfDay += this.getEndOfDay(year, month+i);
              }
              year++;
            }
            else if(targetYear == year){
              if(targetMonth > month){
                sumOfDay += this.getEndOfDay(year, month);
                month++;
              }
              else if(targetMonth == month){
                return (sumOfDay)%7;
              }
            }
          }
        },
        onClickPrev(month){
          month--;
          if(month<=0){
            this.currentMonth = 12;
            this.currentYear -= 1;
          }
          else{
            this.currentMonth -= 1;
          }
          this.init();
        },
        onClickNext(month){
          month++;
          if(month>12){
            this.currentMonth = 1;
            this.currentYear += 1;
          }
          else{
            this.currentMonth += 1;
          }
          this.init();
        },
        isToday: function(year, month, day){
          let date = new Date();
          return year == date.getFullYear() && month == date.getMonth()+1 && day == day; 
        },
        change(day){
         this.currentDay=day;
         this.tShow=true;
         //this.isToday(this.currentYear,.this.currentMonth,this.currentDay)
        }
    }
}).mount("#reserveApp")
</script>
</body>
</html>