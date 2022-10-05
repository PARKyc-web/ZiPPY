<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<html>

<head>

<meta charset="utf-8">

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

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
   

	<title>Simple Calendar Date Picker Example</title>

	<link href="https://www.cssscript.com/wp-includes/css/sticky.css" rel="stylesheet" type="text/css">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/move/date-picker_old.css" />

	<style>

		body { background: #fafafa; }

		.wrapper { margin: 150px auto; }

		h1 { text-align: center; }


		/* 버튼 */
	
	.wrapper{
	 margin: 100px auto;
	}	
	.frame {
    width: 90%;
    margin: 100px auto;
    text-align: center;
  }

  #selectBtn-bday {
    margin: 70px;
  }

  .custom-btn {
    width: 200px;
    height: 100px;
    color: #fff;
    border-radius: 5px;
    padding: 10px 25px;
    font-family: 'Lato', sans-serif;
    font-weight: bold;
    font-size: 20px;
    background: transparent;
    cursor: pointer;
    transition: all 0.3s ease;
    position: relative;
    display: inline-block;
    box-shadow: inset 2px 2px 2px 0px rgba(255, 255, 255, .5),
      7px 7px 20px 0px rgba(0, 0, 0, .1),
      4px 4px 5px 0px rgba(0, 0, 0, .1);
    outline: none;
  }

  .btn-3 {
    background: rgb(163, 162, 162);
    background: linear-gradient(0deg, rgba(163, 162, 162, 1) 0%, rgb(163, 162, 162) 100%);
    border: none;
    height: 50px;
    width: 150px;

  }

  .btn-3:hover {
    /* background: rgb(136, 221, 164);
      background: linear-gradient(0deg, rgba(136, 221, 164, 1) 0%, rgb(131, 221, 161) 100%); */
    box-shadow: 4px 4px 6px 0 rgba(255, 255, 255, .5),
      -4px -4px 6px 0 rgba(116, 125, 136, .5),
      inset -4px -4px 6px 0 rgba(255, 255, 255, .2),
      inset 4px 4px 6px 0 rgba(0, 0, 0, .4);
  }

	</style>

</head>

<body>

	
	<!-- 컴포넌트 사용 -->
	
	<div id="app">
		<main-header></main-header>
		<main-nav></main-nav>
	</div>
  

	<div class="wrapper">

		<h1>대면 견적 방문 희망 날짜를 선택해주세요.</h1>

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

		</div>

	</div>



		<script src="${pageContext.request.contextPath}/resources/js/date-picker_old.js"></script>

		<script>

try {

  fetch(new Request("https://pagead2.googlesyndication.com/pagead/js/adsbygoogle.js", { method: 'HEAD', mode: 'no-cors' })).then(function(response) {

    return true;

  }).catch(function(e) {

    var carbonScript = document.createElement("script");

    carbonScript.src = "//cdn.carbonads.com/carbon.js?serve=CE7DC2JW&placement=wwwcssscriptcom";

    carbonScript.id = "_carbonads_js";

    document.getElementById("carbon-block").appendChild(carbonScript);

  });

} catch (error) {

  console.log(error);

}

</script>

<script>

  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){

  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),

  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)

  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');



  ga('create', 'UA-46156385-1', 'cssscript.com');

  ga('send', 'pageview');



</script>

<!-- 안보이는 폼(display:none)에 인풋하나 생성해놓고 위의 캘린더에서 onclick이벤트 발생하게 해서 인풋에 날짜값을 담아 submit으로 넘길 수 있게한다. -->

<hr>
<br>
<h1>방문 희망 시간을 선택해주세요.</h1>
	<div class="frame">
		<div>
		<input type='time' name='movingTime' />
		</div>
		<div>
			<button id="selectBtn-bday" class="custom-btn btn-3">선택완료</button>
		</div>
	</div>

</body>

</html>

