<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="study.css">
<script type="text/javascript" src="calendar.js"></script>
</head>
<body>
<div id="bookinfo" class="modal modal-calendar">
        <div>
            <h4 class="text-left" style="padding: 30px 100px 0px">날짜 선택 <span class="selectedInfo" :class="{'d-none':selectedDate==0}">{{selectedYear}}년 {{selectedMonth}}월 {{selectedDate}}일 </span></h4>
            <div id="cal-area">
                <table class="Calendar" @click="selected_day">
                    <thead>
                    <tr>
                        <td onClick="prevCalendar();" style="cursor:pointer;">&#60;</td>
                        <td colspan="5">
                            <span id="calYear"></span>년
                            <span id="calMonth"></span>월
                        </td>
                        <td onClick="nextCalendar();" style="cursor:pointer;">&#62;</td>
                    </tr>
                    <tr>
                        <td>일</td>
                        <td>월</td>
                        <td>화</td>
                        <td>수</td>
                        <td>목</td>
                        <td>금</td>
                        <td>토</td>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
</body>
</html>