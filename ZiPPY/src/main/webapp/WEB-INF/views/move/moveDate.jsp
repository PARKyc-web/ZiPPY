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
	
	<!-- 달력 -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/move/date-picker.css" />
	<script src="${pageContext.request.contextPath}/resources/js/date-picker.js"></script>
	
   
  </head>
<body>

<!-- 컴포넌트 사용 -->
	
	<div id="app">
		<main-header></main-header>
		<main-nav></main-nav>
	<!-- 	<move-main></move-main> -->
	</div>
	
<div class="container-calendar">
			<div class="button-container-calendar">
				<button id="previous">‹</button>
				<button id="next">›</button>
				<h3 id="monthHeader">Oct</h3>
				<p id="yearHeader">2022</p>
			</div>

			<table class="table-calendar" id="calendar">
				<thead id="thead-month"><tr><th data-days="Sun" class="red-text">Sun</th><th data-days="Mon" class="">Mon</th><th data-days="Tue" class="">Tue</th><th data-days="Wed" class="">Wed</th><th data-days="Thu" class="">Thu</th><th data-days="Fri" class="">Fri</th><th data-days="Sat" class="">Sat</th></tr></thead>
				<tbody id="calendar-body"><tr><td></td><td></td><td></td><td></td><td></td><td></td><td data-date="1" data-month="10" data-year="2022" data-month-name="October" class="date-picker"><span>1</span></td></tr><tr><td data-date="2" data-month="10" data-year="2022" data-month-name="October" class="date-picker"><span>2</span></td><td data-date="3" data-month="10" data-year="2022" data-month-name="October" class="date-picker"><span>3</span></td><td data-date="4" data-month="10" data-year="2022" data-month-name="October" class="date-picker"><span>4</span></td><td data-date="5" data-month="10" data-year="2022" data-month-name="October" class="date-picker selected"><span>5</span></td><td data-date="6" data-month="10" data-year="2022" data-month-name="October" class="date-picker"><span>6</span></td><td data-date="7" data-month="10" data-year="2022" data-month-name="October" class="date-picker"><span>7</span></td><td data-date="8" data-month="10" data-year="2022" data-month-name="October" class="date-picker"><span>8</span></td></tr><tr><td data-date="9" data-month="10" data-year="2022" data-month-name="October" class="date-picker"><span>9</span></td><td data-date="10" data-month="10" data-year="2022" data-month-name="October" class="date-picker"><span>10</span></td><td data-date="11" data-month="10" data-year="2022" data-month-name="October" class="date-picker"><span>11</span></td><td data-date="12" data-month="10" data-year="2022" data-month-name="October" class="date-picker"><span>12</span></td><td data-date="13" data-month="10" data-year="2022" data-month-name="October" class="date-picker"><span>13</span></td><td data-date="14" data-month="10" data-year="2022" data-month-name="October" class="date-picker"><span>14</span></td><td data-date="15" data-month="10" data-year="2022" data-month-name="October" class="date-picker"><span>15</span></td></tr><tr><td data-date="16" data-month="10" data-year="2022" data-month-name="October" class="date-picker"><span>16</span></td><td data-date="17" data-month="10" data-year="2022" data-month-name="October" class="date-picker"><span>17</span></td><td data-date="18" data-month="10" data-year="2022" data-month-name="October" class="date-picker"><span>18</span></td><td data-date="19" data-month="10" data-year="2022" data-month-name="October" class="date-picker"><span>19</span></td><td data-date="20" data-month="10" data-year="2022" data-month-name="October" class="date-picker"><span>20</span></td><td data-date="21" data-month="10" data-year="2022" data-month-name="October" class="date-picker"><span>21</span></td><td data-date="22" data-month="10" data-year="2022" data-month-name="October" class="date-picker"><span>22</span></td></tr><tr><td data-date="23" data-month="10" data-year="2022" data-month-name="October" class="date-picker"><span>23</span></td><td data-date="24" data-month="10" data-year="2022" data-month-name="October" class="date-picker"><span>24</span></td><td data-date="25" data-month="10" data-year="2022" data-month-name="October" class="date-picker"><span>25</span></td><td data-date="26" data-month="10" data-year="2022" data-month-name="October" class="date-picker"><span>26</span></td><td data-date="27" data-month="10" data-year="2022" data-month-name="October" class="date-picker"><span>27</span></td><td data-date="28" data-month="10" data-year="2022" data-month-name="October" class="date-picker"><span>28</span></td><td data-date="29" data-month="10" data-year="2022" data-month-name="October" class="date-picker"><span>29</span></td></tr><tr><td data-date="30" data-month="10" data-year="2022" data-month-name="October" class="date-picker"><span>30</span></td><td data-date="31" data-month="10" data-year="2022" data-month-name="October" class="date-picker"><span>31</span></td></tr></tbody>
			</table>

			<div class="footer-container-calendar">
				<label for="month">Jump To: </label>
				<select id="month">
					<option value="0">Jan</option>
					<option value="1">Feb</option>
					<option value="2">Mar</option>
					<option value="3">Apr</option>
					<option value="4">May</option>
					<option value="5">Jun</option>
					<option value="6">Jul</option>
					<option value="7">Aug</option>
					<option value="8">Sep</option>
					<option value="9">Oct</option>
					<option value="10">Nov</option>
					<option value="11">Dec</option>
				</select>
				<select id="year"><option value="1970">1970</option><option value="1971">1971</option><option value="1972">1972</option><option value="1973">1973</option><option value="1974">1974</option><option value="1975">1975</option><option value="1976">1976</option><option value="1977">1977</option><option value="1978">1978</option><option value="1979">1979</option><option value="1980">1980</option><option value="1981">1981</option><option value="1982">1982</option><option value="1983">1983</option><option value="1984">1984</option><option value="1985">1985</option><option value="1986">1986</option><option value="1987">1987</option><option value="1988">1988</option><option value="1989">1989</option><option value="1990">1990</option><option value="1991">1991</option><option value="1992">1992</option><option value="1993">1993</option><option value="1994">1994</option><option value="1995">1995</option><option value="1996">1996</option><option value="1997">1997</option><option value="1998">1998</option><option value="1999">1999</option><option value="2000">2000</option><option value="2001">2001</option><option value="2002">2002</option><option value="2003">2003</option><option value="2004">2004</option><option value="2005">2005</option><option value="2006">2006</option><option value="2007">2007</option><option value="2008">2008</option><option value="2009">2009</option><option value="2010">2010</option><option value="2011">2011</option><option value="2012">2012</option><option value="2013">2013</option><option value="2014">2014</option><option value="2015">2015</option><option value="2016">2016</option><option value="2017">2017</option><option value="2018">2018</option><option value="2019">2019</option><option value="2020">2020</option><option value="2021">2021</option><option value="2022">2022</option><option value="2023">2023</option><option value="2024">2024</option><option value="2025">2025</option><option value="2026">2026</option><option value="2027">2027</option><option value="2028">2028</option><option value="2029">2029</option><option value="2030">2030</option><option value="2031">2031</option><option value="2032">2032</option><option value="2033">2033</option><option value="2034">2034</option><option value="2035">2035</option><option value="2036">2036</option><option value="2037">2037</option><option value="2038">2038</option><option value="2039">2039</option><option value="2040">2040</option><option value="2041">2041</option><option value="2042">2042</option><option value="2043">2043</option><option value="2044">2044</option><option value="2045">2045</option><option value="2046">2046</option><option value="2047">2047</option><option value="2048">2048</option><option value="2049">2049</option><option value="2050">2050</option></select>
			</div>

			<p id="date-picked"></p>
		</div>
	
<!-- 
<div class="container-calendar">
  <div class="button-container-calendar">
    <button id="previous">&#8249;</button>
    <button id="next">&#8250;</button>
    <h3 id="monthHeader"></h3>
    <p id="yearHeader"></p>
  </div>
    
  <table class="table-calendar" id="calendar">
    <thead id="thead-month"></thead>
    <tbody id="calendar-body"></tbody>
  </table>
    
  <div class="footer-container-calendar">
    <label for="month">Jump To: </label>
    <select id="month">
      <option value=0>Jan</option>
      <option value=1>Feb</option>
      <option value=2>Mar</option>
      <option value=3>Apr</option>
      <option value=4>May</option>
      <option value=5>Jun</option>
      <option value=6>Jul</option>
      <option value=7>Aug</option>
      <option value=8>Sep</option>
      <option value=9>Oct</option>
      <option value=10>Nov</option>
      <option value=11>Dec</option>
    </select>
    <select id="year"></select>       
  </div>
<p id="date-picked"></p>
</div> -->
</body>
</html>