<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
   <title>Move Home</title>
   <meta charset="UTF-8">

   <script src="https://cdn.jsdelivr.net/npm/vue@2.7.8/dist/vue.js"></script>
   <!-- 
   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8" crossorigin="anonymous"></script>
     -->
     
       <!-- CSS only -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
  integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
  <!-- JavaScript Bundle with Popper -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" 
  integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
   
   <!-- 컴포넌트 사용 -->
	<script type="module" src="<%=request.getContextPath()%>/resources/js/app.js"></script>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css">
   
  </head>
<body>
	<body>
			<div id="section">
				<button style="color: white; background-color: #4caf50;" onclick="openCalendar()">Calendar</button>
				<input type = "text" id="value"> </button>
            </div>

            <div class="MsgInfoPop">
                <div id="MsgInfoPop-content">
			        <div id="CalendarArea">
		                <table align="center" id="calendarTable">
			                <tr>
				                <td align="center"><div id="beforeMonth"  onclick='beforeMonth()'></div></td>
				                <td colspan="5" align="center"><div id="thisMonth"></div></td>
				                <td align="center"><div id="nextMonth"  onclick='nextMonth()'></div></td>
			                </tr>							
			                <tr>
				                <td align="center"><div class="w_sun">Sun</div></td>
				                <td align="center"><div class="w_day">Mon</div></td>
				                <td align="center"><div class="w_day">Tue</div></td>
				                <td align="center"><div class="w_day">Wed</div></td>
				                <td align="center"><div class="w_day">Thu</div></td>
				                <td align="center"><div class="w_day">Fri</div></td>
				                <td align="center"><div class="w_sat">Sat</div></td>
			                </tr>
		                </table>    
	                </div>
                </div>
            </div>
</body>
	<script type="text/javascript" charset="utf-8"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/move/moveDate.css" />	
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/moveDate.js" charset="utf-8"></script>	
</html>