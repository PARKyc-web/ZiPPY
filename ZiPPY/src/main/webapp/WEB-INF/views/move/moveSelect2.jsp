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

<style type="text/css">

/* 버튼 */
.frame {
  width: 90%;
  margin: 40px auto;
  text-align: center;
}
button {
  margin: 20px;
}
.custom-btn {
  width: 130px;
  height: 40px;
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
@import "reset.css";

@import url("https://fonts.googleapis.com/css2?family=Gaegu&display=swap");

/* 나중에 이미지와 텍스트가 겹쳐질 수 있도록 위 아래로 나란히 놓아준다. */
body {
  display: flex;
  flex-direction: column;
  height: 100vh;
  justify-content: center;
  align-items: center;
  font-family: "Gaegu", cursive;
  background-color: #e6e9ee;
}

button {
  width: 400px;
  height: 300px;
  padding: 20px 12px;
}

h3 {
  font-size: 30px;
  font-weight: bold;
  padding-bottom: 20px;
  padding-top: 150px; /* 너무 위에 붙어 있어서 조정해주었다. */
}

.detail {
  display: flex;
  flex-direction: row;
  line-height: 1.5;
  font-size: 20px;
}

.detail div {
  width: 390px; /* padding: 5px+5px=10px */
  height: 290px; /* padding: 5px+5px=10px */
  border: 2px solid #434a54;
  padding: 5px;
  margin: 20px 14px;
  transform: translateY(-100px); /* 텍스트를 이미지 위에 덮어준다. */
  transition: all 0.5s cubic-bezier(0, 0.04, 0.43, 0.91);
  color: #434a54;
  background-color: #f5c4ed;
  text-align: center;
  opacity: 0; /* 평소에는 보이지 않도록 만든다. */
}

.detail div:hover {
  opacity: 1; /* 불투명하게 만든다. 그래서 보이도록 만들어준다. */
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




    <h3>마우스를 올려두시면 그 상품에 해당하는 상품 설명이 보입니다.</h3>
	<div class="frame">
	  <button class="custom-btn btn-1">Read More</button>
	  <button class="custom-btn btn-2">Read More</button>
	</div>
    <div class="detail">
      <div>
        체리 케이크는 체리 성분이 함유된 케이크이다. 제과점에서 파는 흔하지 않은
        케이크 중 하나이며 생일 파티, 축하 파티 같은 모임 때 후식으로 쓰인다.<br />
        Price : <strong>15,000 won</strong>
      </div>
      <div>
        초콜릿 케이크는 초콜릿 성분이 함유된 케이크이다. 제과점에서 파는 흔한
        케이크 중 하나이며 생일 파티, 축하 파티 같은 모임 때 후식으로 쓰인다.<br />
        Price : <strong>12,000 won</strong>
      </div>
    </div>



	<!-- 카드 -->
	<div class="card border-secondary mb-3" style="max-width: 18rem;">
	  <div class="card-header">Header</div>
	  <div class="card-body text-secondary">
	    <h5 class="card-title">Secondary card title</h5>
	    <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
	  </div>
	 </div>

</body>
</html>