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
<style>

/* 배경 비디오 */
 
body {
  padding: 0px;
  margin: 0px;
}
.bg {
  width: 100%;
  height: 400px;
  overflow: hidden;
  margin: 0px auto;
  position: relative;
}
video {
  /* width: 100%; */
  position: fixed; right: 0; bottom: 0;
            width: 100%; height: 100%;
            /* width: auto; height: auto; */ z-index: -100;
          
            background-size: cover;
}
.text {
  position: absolute;
  width: 100%;
  top: 50%;
  left: 50%;
  transform: translate(-50%,-50%);
}
.text p {
  text-align: center;
  font-size: 48px;
  color: #ffffff;
}

    
/* 버튼 */
/*  */
.frame {
  width: 90%;
  margin-top: 100px;
 /*  margin: 0 auto; */
  text-align: center;

  /* 
  position: relative;
    top: 35%;
    left: 50%; 
   transform: translate(-50%, -50%);  
   */
}


/* 
.custom-btn btn-3{
	float:left;
}
.custom-btn btn-8{
	float:right;
}
.frame div{
	width: 50%; height: 100px;
    border:1px solid #f00;
	
}
 */
button {
  margin: 20px;
}
.custom-btn {
  width: 200px;
  height:100px;
  color: #fff;
  border-radius: 5px;
  padding: 10px 25px;
  font-family: 'Lato', sans-serif;
  font-weight: 500;
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


/* 3 */
.btn-3 {
  background: rgb(0,172,238);
  background: linear-gradient(0deg, rgba(0,172,238,1) 0%, rgba(2,126,251,1) 100%);

  line-height: 42px;
  padding: 0;
  border: none;

}
.btn-3 span {
  position: relative;
  display: block;
  width: 100%;
  height: 100%;
  
  align-items:center; 
  display: flex;
  justify-content: center;
  
  font-size: 20px;
  font-weight: bold;
}
.btn-3:before,
.btn-3:after {
  position: absolute;
  content: "";
  right: 0;
  top: 0;
   background: rgba(2,126,251,1);
  transition: all 0.3s ease;
}
.btn-3:before {
  height: 0%;
  width: 2px;
}
.btn-3:after {
  width: 0%;
  height: 2px;
}
.btn-3:hover{
   background: transparent;
  box-shadow: none;
}
.btn-3:hover:before {
  height: 100%;
}
.btn-3:hover:after {
  width: 100%;
}
.btn-3 span:hover{
   color: rgba(2,126,251,1);
}
.btn-3 span:before,
.btn-3 span:after {
  position: absolute;
  content: "";
  left: 0;
  bottom: 0;
   background: rgba(2,126,251,1);
  transition: all 0.3s ease;
}
.btn-3 span:before {
  width: 2px;
  height: 0%;
}
.btn-3 span:after {
  width: 0%;
  height: 2px;
}
.btn-3 span:hover:before {
  height: 100%;
}
.btn-3 span:hover:after {
  width: 100%;
}


/* 8 */
.btn-8 {
 background-color: #89d8d3;
background-image: linear-gradient(315deg, #89d8d3 0%, #03c8a8 74%);  
line-height: 42px;
  padding: 0;
  border: none;
}
.btn-8 span {
  position: relative;
  display: block;
  width: 100%;
  height: 100%;
     
  align-items:center; 
  display: flex;
  justify-content: center;
  
  font-size: 20px;
  font-weight: bold;
}
.btn-8:before,
.btn-8:after {
  position: absolute;
  content: "";
  right: 0;
  bottom: 0;
  background-color: #4dccc6;
background-image: linear-gradient(315deg, #4dccc6 0%, #96e4df 74%);
  box-shadow:
   -7px -7px 20px 0px #fff9,
   -4px -4px 5px 0px #fff9,
   7px 7px 20px 0px #0002,
   4px 4px 5px 0px #0001;
  transition: all 0.3s ease;
}
.btn-8:before{
   height: 0%;
   width: 2px;
}
.btn-8:after {
  width: 0%;
  height: 2px;
}
.btn-8:hover:before {
  height: 100%;
}
.btn-8:hover:after {
  width: 100%;
}
.btn-8:hover{
  background: transparent;
  box-shadow: none;
}
.btn-8 span:hover{
  color: #89d8d3;
}
.btn-8 span:before,
.btn-8 span:after {
  position: absolute;
  content: "";
  left: 0;
  top: 0;
  background: #89d8d3;
  /*box-shadow:  4px 4px 6px 0 rgba(255,255,255,.5),
              -4px -4px 6px 0 rgba(116, 125, 136, .2), 
    inset -4px -4px 6px 0 rgba(255,255,255,.5),
    inset 4px 4px 6px 0 rgba(116, 125, 136, .3);*/
  transition: all 0.3s ease;
}
.btn-8 span:before {
  width: 2px;
  height: 0%;
}
.btn-8 span:after {
  height: 2px;
  width: 0%;
}
.btn-8 span:hover:before {
  height: 100%;
}
.btn-8 span:hover:after {
  width: 100%;
}

/*  */

</style>

	<!-- 컴포넌트 사용 -->
	
	<div id="app">
		<main-header></main-header>
		<main-nav></main-nav>
	<!-- 	<move-main></move-main> -->
	</div>
	
	

	<div class="frame">
	  <button class="custom-btn btn-3"><span>이사 견적받기</span></button>
	  <button class="custom-btn btn-8"><span>청소 견적받기</span></button>
	</div>	
    
	<div class="bg">
		<video muted autoplay loop>
			<source src="${pageContext.request.contextPath}/resources/video/이사.mp4" type="video/mp4" />
		</video>
	</div>
	
</body>
</html>