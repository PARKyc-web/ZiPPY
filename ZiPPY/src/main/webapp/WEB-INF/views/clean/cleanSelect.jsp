<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
   <title>Move Home</title>
   <meta charset="UTF-8">

<!--  -->
	<link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link rel="stylesheet" href="cssMouseDetail.css" />
<!--  -->

   <script src="https://cdn.jsdelivr.net/npm/vue@2.7.8/dist/vue.js"></script>
   
     <!-- CSS only -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
  <!-- JavaScript Bundle with Popper -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" 
          integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

	<!-- 컴포넌트 사용 -->
	<script type="module" src="<%=request.getContextPath()%>/resources/js/app.js"></script>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css">

<style type="text/css">

/* 버튼 */
.frame {
  width: 90%;
  margin: 100px auto;
  text-align: center;
}
button {
  margin: 70px;
}
.custom-btn {
  width: 200px;
  height:100px;
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
   box-shadow:inset 2px 2px 2px 0px rgba(255,255,255,.5),
   7px 7px 20px 0px rgba(0,0,0,.1),
   4px 4px 5px 0px rgba(0,0,0,.1);
  outline: none;
}

/* 1 */
.btn-1 {
  background: rgb(6,14,131);
  background: linear-gradient(0deg, rgba(6,14,131,1) 0%, rgba(12,25,180,1) 100%);
  border: none;
}
.btn-1:hover {
   background: rgb(0,3,255);
background: linear-gradient(0deg, rgba(0,3,255,1) 0%, rgba(2,126,251,1) 100%);
}

/* 2 */
.btn-2 {
  background: rgb(96,9,240);
  background: linear-gradient(0deg, rgba(96,9,240,1) 0%, rgba(129,5,240,1) 100%);
  border: none;
  
}
.btn-2:before {
  height: 0%;
  width: 2px;
}
.btn-2:hover {
  box-shadow:  4px 4px 6px 0 rgba(255,255,255,.5),
              -4px -4px 6px 0 rgba(116, 125, 136, .5), 
    inset -4px -4px 6px 0 rgba(255,255,255,.2),
    inset 4px 4px 6px 0 rgba(0, 0, 0, .4);
}




/* 설명 */
	 
	 .explain1 {    
	 	display: none;
	 	
	 } 
	 .btn1{
	 	display: inline-block;
	 }
	 .btn1:hover+.explain1 {    
	 	display: block;
	 }


	 .explain2 {    
	 	display: none;
	 	
	 } 
	 .btn2{
	 	display: inline-block;
	 }
	 .btn2:hover+.explain2 {    
	 	display: block;
	 }


</style>

</head>
<body>

	<!-- 컴포넌트 사용 -->
	
	<div id="app">
		<main-header></main-header>
		<main-nav></main-nav>
	</div>
	

	<!-- 버튼 -->




    <div class="frame">       
     	<h3>청소 유형을 선택해주세요.</h3>
     	<div class="btn1">    		  
     		  <button class="custom-btn btn-1">입주청소</button>
     	</div>
    
     	<div class="explain1">
     	집 안에 짐이 없는 상태에서 청소<br>
     	이사를 나가거나 들어오는 집에 적합<br>				
     	</div>
     	
     	
     	<div class="btn2">    		  
     		  <button class="custom-btn btn-2">거주청소</button>
     	</div>
    
     	<div class="explain2">
     	집 안에 짐이 있는 상태에서 청소<br>
     	현재 거주 중인 집에 적합<br>				
     	</div>

     </div>
  </body>




</body>
</html>