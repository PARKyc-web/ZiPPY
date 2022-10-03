<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
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
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous"></script>

<!-- 컴포넌트 사용 -->
<script type="module"
	src="<%=request.getContextPath()%>/resources/js/app.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/style.css">

<!-- 
	<script src="http://code.jquery.com/jquery-latest.min.js"></script> -->

<style type="text/css">


/*  */
html {
  text-align: center;
  font-family: Georgia, "Times New Roman", serif;
}


.depart-address{
	margin-top: 100px;
}
.move-drop{
	display: inline-block;
	width: 200px;
	height: 200px;
}
.move-select-input{
	display: inline-block;
	width: 300px;
	height: 200px;
}

/* 버튼 */
	
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
    /* font-size: 20px; */
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
  
    .btn-4 {
    background: rgb(163, 162, 162);
    background: linear-gradient(0deg, rgba(163, 162, 162, 1) 0%, rgb(163, 162, 162) 100%);
    border: none;
    width: 80px; 
    height: 32px;
	font-size: 15px;
	
  }

  .btn-4:hover {
    /* background: rgb(136, 221, 164);
      background: linear-gradient(0deg, rgba(136, 221, 164, 1) 0%, rgb(131, 221, 161) 100%); */
    box-shadow: 4px 4px 6px 0 rgba(255, 255, 255, .5),
      -4px -4px 6px 0 rgba(116, 125, 136, .5),
      inset -4px -4px 6px 0 rgba(255, 255, 255, .2),
      inset 4px 4px 6px 0 rgba(0, 0, 0, .4);
  }
  

  .btn-3 {
    background: rgb(163, 162, 162);
    background: linear-gradient(0deg, rgba(163, 162, 162, 1) 0%, rgb(163, 162, 162) 100%);
    border: none;
    height: 50px;
    width: 150px;
	font-size: 20px;
  }

  .btn-3:hover {
    /* background: rgb(136, 221, 164);
      background: linear-gradient(0deg, rgba(136, 221, 164, 1) 0%, rgb(131, 221, 161) 100%); */
    box-shadow: 4px 4px 6px 0 rgba(255, 255, 255, .5),
      -4px -4px 6px 0 rgba(116, 125, 136, .5),
      inset -4px -4px 6px 0 rgba(255, 255, 255, .2),
      inset 4px 4px 6px 0 rgba(0, 0, 0, .4);
  }

/* 라디오 */
/* 
.checks {
    position: relative;
    height: 20px;
    padding: 30px;
    line-height: 20px;
}
.checks label {
    font-size: 20px;
    color: #666;
    margin-right: 10px;
    vertical-align: middle;
}
            
.checks-radio1 input[type="radio"] {
    position: absolute;
    width: 1px;
    height: 1px;
    padding: 0;
    margin: -1px;
    overflow: hidden;
    clip: rect(0, 0, 0, 0);
    border: 0;
}
.checks-radio1 input[type="radio"]+label {
    display: inline-block;
    position: relative;
    padding-left: 25px;
    cursor: pointer;
    -webkit-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
}
            
.checks-radio1 input[type="radio"]+label:before {
    content: '';
    position: absolute;
    left: 4px;
    top: 0px;
    width: 20px;
    height: 20px;
    text-align: center;
    background: #fff;
    border: 1px solid #cacece;
    border-radius: 100%;
    box-shadow: none;
}
.checks-radio1 input[type="radio"]:checked+label:before {
    background: #fff;
    border-color: yellowgreen;
} 
            
.checks-radio1 input[type="radio"]:checked+label:after {
    content: '';
    position: absolute;
    top: 1px;
    left: 5px;
    width: 15px;
    height: 15px;
    background: #B3E3C3;
    border-radius: 100%;
    box-shadow: none;
}
 */

/* 새로운 라디오 */
/* 
body,html{
    background: #efefef;
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: 100%;
    z-index: -1;
} */

.black-btn{
    border: 3px solid #1a1a1a;
    display: inline-block;
    padding: 10px;
    position: relative;
    text-align: center;
    transition: background 600ms ease, color 600ms ease;
    border-radius: 10px;
}

.black-btn:hover{
	cursor: pointer;
}
input[type="radio"].toggle {
    display: none;
    
}

.black-radio input[type="radio"]:checked+label:after{
	background: black;
}


</style>

</head>

<body>

	<!-- 컴포넌트 사용 -->
	
	<div id="app">
		<main-header></main-header>
		<main-nav></main-nav>
	<!-- 	<move-main></move-main> -->
	</div>
	

	<form action="">
		
		<div class="depart-address">
		<h3>출발지 주소를 입력해주세요.</h3>
		<!-- 지도 -->

		<script
			src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

			우편번호 : <input type="text" name="zip"
			style="width: 80px; height: 26px;" />
		<button class="custom-btn btn-4" type="button" 
			onclick="openZipSearch()">검색</button>
		<br> 주소 : <input type="text" name="addr1"
			style="width: 300px; height: 30px;" readonly /><br> 
			상세 : <input
			type="text" name="addr2" style="width: 300px; height: 30px;" />
		</div>	
		
		<br>
		<hr>
		<br>	
			<div class="move-drop">
			<h3>집형태</h3>
			<select>
				<option value="1" selected>빌라/주택</option>
				<option value="2">오피스텔</option>
				<option value="3">아파트</option>
			</select>
			</div>
			
			<div class="move-drop">
			<h3>방구조</h3>
			<select>
				<option value="1" selected>원룸</option>
				<option value="2">투룸</option>
				<option value="3">쓰리룸</option>
				<option value="4">쓰리룸 이상</option>
			</select>
			</div>
			
			<div class="move-drop">
			<h3>집평수</h3>
			<select>
				<option value="1" selected>10평 이하</option>
				<option value="2">10~15평</option>
				<option value="3">15~20평</option>
				<option value="4">20~25평</option>
				<option value="5">25~30평</option>
				<option value="6">30~35평</option>
				<option value="7">35~40평</option>
				<option value="8">40평 이상</option>
			</select>
			</div>
			
			<br>
			<hr>
			<br>
			
			<div class="move-select-input">
			<h3>층수</h3>
				<input id="floor" type="number" min="1"><label for="floor"> 층</label>
			</div>
			
			<div class="move-select-input">
			<h3>화장실 개수</h3>
				<input id="toilet" type="number" min="1"><label for="toilet"> 개</label>
			</div>
			
			<div class="move-select-input">
			<h3>베란다 개수</h3>
				<input id="veranda" type="number" min="1"><label for="veranda"> 개</label>
			</div>
			<hr>
			<br>
			
			<div class="checks-radio1">
				<h3>1층 별도 계단</h3>
			    <input type="radio" id="ex_rd" name="ex_rds">
			    <label for="ex_rd">있음</label>
			    <input type="radio" id="ex_rd2" name="ex_rds">
			    <label for="ex_rd2">없음</label>
			</div>
			
			<div class="black-radio">
			<h3>엘레베이터</h3>
			<input id="toggle-on" class="toggle toggle-left" name="toggle" value="false" type="radio" checked>
			<label for="toggle-on" class="black-btn">Yes</label>
			<input id="toggle-off" class="toggle toggle-right" name="toggle" value="true" type="radio">
			<label for="toggle-off" class="black-btn">No</label>	
			</div>
							
		<div class="frame">
			<button id="selectBtn-bday" class="custom-btn btn-3">선택완료</button>
		</div>



		<script>
			function openZipSearch() {
				new daum.Postcode({
					oncomplete : function(data) {
						$('[name=zip]').val(data.zonecode); // 우편번호 (5자리)
						$('[name=addr1]').val(data.address);
						$('[name=addr2]').val(data.buildingName);
					}
				}).open();
			}
		</script>
	</form>
</body>
</html>