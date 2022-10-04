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
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
  <!-- JavaScript Bundle with Popper -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous">
  </script>

  <!-- 컴포넌트 사용 -->
  <script type="module"
	src="<%=request.getContextPath()%>/resources/js/app.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/style.css"> 

  <!-- font awesome -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css"
    integrity="sha512-xh6O/CkQoPOWDdYTDqeRdPCVd1SpvCA9XXcUnZS2FmJNp1coAFzvtCN9BmamE+4aHK8yyUHUSCcJHgXloTyT2A=="
    crossorigin="anonymous" referrerpolicy="no-referrer" />


  <style type="text/css">
    /*  */

    i {
      width: 100px;
      height: 100px;
    }

    html {
      text-align: center;
      font-family: Georgia, "Times New Roman", serif;
    }

    /* input */
    /* 
input {
  display:block;
  width:100%;
  margin:10px 0;
  padding:10px;
} */
    input {
      margin: 5px;
    }
    .move-furniture1{ 
      display: inline-block;
      margin: 20px;
    }
    .move-furniture2{ 
      display: inline-block;
      margin: 20px;
    }

    .type-2 {
      background-color: #fafafa;
      border: 0;
      box-shadow: 0 0 4px rgba(0, 0, 0, 0.3);
      transition: .3s box-shadow;
      width: 100px;
      border-radius: 5px;
    }

    .type-2:hover {
      box-shadow: 0 0 4px rgba(0, 0, 0, 0.5);
    }


    /* zip */
    .depart-address {
      margin-top: 100px;
    }

    .move-detail-drop {
      display: inline-block;
      width: 210px;
      height: 100px;
    }

    .move-select-input {
      display: inline-block;
      width: 250px;
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
      width: 40px;
      height: 45px;
      font-size: 20px;

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
    .detail-info-radio {
      display: inline-block;
      width: 250px;
    }

    .test_obj input[type="radio"] {
      display: none;
    }

    .test_obj input[type="radio"]+span {
      display: inline-block;
      padding: 10px 10px;
      border: 1px solid #dfdfdf;
      background-color: #ffffff;
      text-align: center;
      cursor: pointer;
      border-radius: 20px;
      width: 80px;


      box-shadow: 0 0 4px rgba(0, 0, 0, 0.2);
      transition: .3s box-shadow;
    }

    .test_obj input[type="radio"]:checked+span {
      background-color: #B3E3C3;
      color: #ffffff;
    }

    /* 드롭 */
    @import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap');

    select {
      -moz-appearance: none;
      -webkit-appearance: none;
      appearance: none;

      font-family: "Noto Sansf KR", sans-serif;
      font-size: 1rem;
      font-weight: 400;
      line-height: 1.5;

      color: #444;
      background-color: #fff;

      padding: .6em 1.4em .5em .8em;
      margin: 0;

      border: 1px solid #aaa;
      border-radius: .5em;
      /* box-shadow: 0 1px 0 1px rgba(0,0,0,.2); */
      box-shadow: 0 0 4px rgba(0, 0, 0, 0.2);
      transition: .3s box-shadow;
      width: 200px;
    }

    select:hover {
      border-color: #888;
    }

    select:focus {
      border-color: #aaa;
      box-shadow: 0 0 1px 3px rgba(59, 153, 252, .7);
      box-shadow: 0 0 0 3px -moz-mac-focusring;
      color: #222;
      outline: none;
    }

    select:disabled {
      opacity: 0.5;
    }

    label {
      font-family: "Noto Sans KR", sans-serif;
      font-size: 1rem;
      font-weight: 600;
      line-height: 1.3;

      color: #444;

      margin-right: 0.5em;
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

    <br>
    <br>

    <h3>이삿짐의 상세 정보를 입력해주세요.</h3>
    <br>
    <form><br>
      <h4>가구</h4><br>
      <div class="move-detail">
        <div class="move-furniture1">
          <input type="checkbox" name="bed"><label>침대</label><br><br>
          <i class="fa-solid fa-bed fa-5x"></i><br>
          <div class="move-detail-drop">
            <select>
              <option value="" selected>-- 사이즈 --</option>
              <option value="1" >싱글</option>
              <option value="2">슈퍼싱글</option>
              <option value="3">퀸</option>
              <option value="4">킹</option>
              <option value="5">킹 이상</option>
            </select>
            </div>
          <button class="custom-btn btn-4" type="button">+</button>
        </div>
        <div class="move-furniture1">
          <input type="checkbox" name="sofa"><label>소파</label><br><br>
          <i class="fa-solid fa-couch fa-5x"></i><br>
          <div class="move-detail-drop">
            <select>
              <option value="" selected>-- 사이즈 --</option>
              <option value="1" >싱글</option>
              <option value="2">슈퍼싱글</option>
              <option value="3">퀸</option>
              <option value="4">킹</option>
              <option value="5">킹 이상</option>
            </select>
            </div>
          <button class="custom-btn btn-4" type="button">+</button>
        </div>
        <div class="move-furniture1">
          <input type="checkbox" name="closet"><label>옷장-단품</label><br><br>
          <i class="fa-solid fa-toilet-portable fa-5x"></i><br>
          <div class="move-detail-drop">
            <select>
              <option value="" selected>-- 사이즈 --</option>
              <option value="1" >싱글</option>
              <option value="2">슈퍼싱글</option>
              <option value="3">퀸</option>
              <option value="4">킹</option>
              <option value="5">킹 이상</option>
            </select>
            </div>
          <button class="custom-btn btn-4" type="button">+</button>
        </div>
        <div class="move-furniture1">
          <input type="checkbox" name="closets"><label>옷장-연결장</label><br><br>
          <i class="fa-solid fa-toilets-portable fa-5x"></i><br>
          <div class="move-detail-drop">
            <select>
              <option value="" selected>-- 사이즈 --</option>
              <option value="1" >싱글</option>
              <option value="2">슈퍼싱글</option>
              <option value="3">퀸</option>
              <option value="4">킹</option>
              <option value="5">킹 이상</option>
            </select>
            </div>
          <button class="custom-btn btn-4" type="button">+</button>
        </div>
      </div>

      <div class="move-detail">  
        <div class="move-furniture2">
          <input type="checkbox" name="closets"><label>의자</label><br><br>
          <i class="fa-solid fa-chair fa-5x"></i><br>
          <div class="move-detail-drop">
            <select>
              <option value="" selected>-- 사이즈 --</option>
              <option value="1" >싱글</option>
              <option value="2">슈퍼싱글</option>
              <option value="3">퀸</option>
              <option value="4">킹</option>
              <option value="5">킹 이상</option>
            </select>
            </div>
          <button class="custom-btn btn-4" type="button">+</button>
        </div>
     
      <div class="move-furniture2">
        <input type="checkbox" name="closets"><label>행거</label><br><br>
        <i class="fa-solid fa-sign-hanging fa-5x"></i><br>
        <div class="move-detail-drop">
          <select>
            <option value="" selected>-- 사이즈 --</option>
            <option value="1" >싱글</option>
            <option value="2">슈퍼싱글</option>
            <option value="3">퀸</option>
            <option value="4">킹</option>
            <option value="5">킹 이상</option>
          </select>
          </div>
        <button class="custom-btn btn-4" type="button">+</button>
      </div>
    
    <div class="move-furniture2">
      <input type="checkbox" name="closets"><label>서랍</label><br><br>
      <i class="fa-solid fa-box-archive fa-5x"></i><br>
      <div class="move-detail-drop">
        <select>
          <option value="" selected>-- 사이즈 --</option>
          <option value="1" >싱글</option>
          <option value="2">슈퍼싱글</option>
          <option value="3">퀸</option>
          <option value="4">킹</option>
          <option value="5">킹 이상</option>
        </select>
        </div>
      <button class="custom-btn btn-4" type="button">+</button>
    </div>
  </div>
</div>



    <br><hr><br>
    <h4>가전</h4><br>
      <div class="move-detail">
        <div class="move-furniture1">
          <input type="checkbox" name="bed"><label>TV</label><br><br>
          <i class="fa-solid fa-tv fa-5x"></i><br>
          <div class="move-detail-drop">
            <select>
              <option value="" selected>-- 사이즈 --</option>
              <option value="1" >싱글</option>
              <option value="2">슈퍼싱글</option>
              <option value="3">퀸</option>
              <option value="4">킹</option>
              <option value="5">킹 이상</option>
            </select>
            </div>
          <button class="custom-btn btn-4" type="button">+</button>
        </div>
        <div class="move-furniture1">
          <input type="checkbox" name="sofa"><label>데스크탑</label><br><br>
          <i class="fa-solid fa-desktop fa-5x"></i><br>
          <div class="move-detail-drop">
            <select>
              <option value="" selected>-- 사이즈 --</option>
              <option value="1" >싱글</option>
              <option value="2">슈퍼싱글</option>
              <option value="3">퀸</option>
              <option value="4">킹</option>
              <option value="5">킹 이상</option>
            </select>
            </div>
          <button class="custom-btn btn-4" type="button">+</button>
        </div>
        <div class="move-furniture1">
          <input type="checkbox" name="closet"><label>옷장-단품</label><br><br>
          <i class="fa-solid fa-toilet-portable fa-5x"></i><br>
          <div class="move-detail-drop">
            <select>
              <option value="" selected>-- 사이즈 --</option>
              <option value="1" >싱글</option>
              <option value="2">슈퍼싱글</option>
              <option value="3">퀸</option>
              <option value="4">킹</option>
              <option value="5">킹 이상</option>
            </select>
            </div>
          <button class="custom-btn btn-4" type="button">+</button>
        </div>
        <div class="move-furniture1">
          <input type="checkbox" name="closets"><label>유모차</label><br><br>
          <i class="fa-solid fa-baby-carriage fa-5x"></i><br>
          <div class="move-detail-drop">
            <select>
              <option value="" selected>-- 사이즈 --</option>
              <option value="1">싱글</option>
              <option value="2">슈퍼싱글</option>
              <option value="3">퀸</option>
              <option value="4">킹</option>
              <option value="5">킹 이상</option>
            </select>
            </div>
          <button class="custom-btn btn-4" type="button">+</button>
        </div>
      
    </div>
    </form>

    <br>
    <hr>
    <br>
    <br>  

    <div class="frame">
      <button id="selectBtn-bday" class="custom-btn btn-3">선택완료</button>
    </div>



    <script>
    
    </script>
  </form>
</body>

</html>