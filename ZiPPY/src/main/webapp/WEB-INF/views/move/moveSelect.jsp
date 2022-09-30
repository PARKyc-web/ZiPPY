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
    integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous">
  </script>

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


    .btn-1 {
      background: rgb(144, 198, 235);
      background: linear-gradient(0deg, rgba(144, 198, 235, 1) 0%, rgb(127, 196, 243) 100%);
      border: none;
    }

    .btn-1:hover {
      /* background: rgb(63, 153, 243);
      background: linear-gradient(0deg, rgba(63, 153, 243, 1) 0%, rgba(2, 126, 251, 1) 100%); */
      box-shadow: 4px 4px 6px 0 rgba(255, 255, 255, .5),
        -4px -4px 6px 0 rgba(116, 125, 136, .5),
        inset -4px -4px 6px 0 rgba(255, 255, 255, .2),
        inset 4px 4px 6px 0 rgba(0, 0, 0, .4);
    }


    .btn-2 {
      background: rgb(rgb(150, 229, 184));
      background: linear-gradient(0deg, rgba(150, 229, 184, 1) 0%, rgb(150, 229, 184) 100%);
      border: none;

    }

    .btn-2:before {
      height: 0%;
      width: 2px;
    }

    .btn-2:hover {
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

    }

    .btn-3:hover {
      /* background: rgb(136, 221, 164);
      background: linear-gradient(0deg, rgba(136, 221, 164, 1) 0%, rgb(131, 221, 161) 100%); */
      box-shadow: 4px 4px 6px 0 rgba(255, 255, 255, .5),
        -4px -4px 6px 0 rgba(116, 125, 136, .5),
        inset -4px -4px 6px 0 rgba(255, 255, 255, .2),
        inset 4px 4px 6px 0 rgba(0, 0, 0, .4);
    }



    /* 설명 */

    .btn1 {
      display: inline-block;
    }

    /* .btn1:hover {    
	 	display: block;
	 } */


    .btn2 {
      display: inline-block;
    }

    /*    
	 .kbb-btn2:hover+.explain2 {    
	 	display: block;
	 }
	 */
    .explain1,
    .explain2 {
      height: 200px;
      display: none;
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
    <h3>이사 유형을 선택해주세요.</h3>
    <div class="btn1">
      <button class="custom-btn btn-1" onclick="aaa()">소형이사</button>
    </div>



    <div class="btn2">
      <button class="custom-btn btn-2" onclick="bbb()">가정이사</button>
    </div>
    <br>
    <div class="explain1">
      <ul>
        <li>원룸, 투룸, 20평 미만에 적합</li>
        <li>주요차량 : 1 ~ 2.5톤 트럭</li>
        <li>이사종류 : 일반 / 반포장 / 포장</li>
        <li>평균 작업인원 : 2명이하</li>
      </ul>
    </div>

    <div class="explain2">
      쓰리룸, 20평대 이상에 적합<br>
      주요차량 : 2.5 ~ 5톤 트럭 이상<br>
      이사종류 : 전문 포장이사<br>
      평균 작업인원 : 3명 이상
    </div>

    <div>
      <button class="custom-btn btn-3">선택완료</button>
    </div>

  </div>

 
  <script>
    function aaa() {
      let item = document.querySelector('.explain1');
      item.style.display = 'inline-block';

      let item2 = document.querySelector('.explain2');
      item2.style.display = 'none';
    }

    function bbb() {
      let item = document.querySelector('.explain1');
      item.style.display = 'none';

      let item2 = document.querySelector('.explain2');
      item2.style.display = 'inline-block';
    }
  </script>

</body>




</body>

</html>