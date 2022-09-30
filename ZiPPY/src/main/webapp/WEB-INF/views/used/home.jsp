<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous">
	
</script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
<!--폰트어썸 최신 CDN-->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<style>
      #container {
        width: 1200px;
        margin: 0 auto;
      }

      .used-main-title {
        margin: 50px;
      }

      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width : 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }

      #used-add-drop-search {
        display: flex;
        justify-content: space-between;
        height: 40px;
        margin-top: 10px;
      }

      .b-example-divider {
        height: 3rem;
        background-color: rgba(0, 0, 0, .1);
        border: solid rgba(0, 0, 0, .15);
        border-width: 1px 0;
        box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em rgba(0, 0, 0, .15);
      }

      .b-example-vr {
        flex-shrink: 0;
        width: 1.5rem;
        height: 100vh;
      }

      .bi {
        vertical-align: -.125em;
        fill: currentColor;
      }

      .nav-scroller {
        position: relative;
        z-index: 2;
        height: 2.75rem;
        overflow-y: hidden;
      }

      .nav-scroller .nav {
        display: flex;
        flex-wrap: nowrap;
        padding-bottom: 1rem;
        margin-top: -1px;
        overflow-x: auto;
        text-align: center;
        white-space: nowrap;
        -webkit-overflow-scrolling: touch;
      }

      .nav-link {
        color: black;
      }

      .nav-link dropdown-toggle {
        float: right;
        width: 50px;
      }

      .nav-link:hover {
        color: #B3E3C3;
      }

      .nav-item dropdown {
        float: right;
      }

      .used-dropdown {
        list-style: none;
      }

      .dropdown-menu a:hover {
        background-color: #B3E3C3;
      }

      #navbarDropdown {
        color: #212529;
      }

      .thumbnail-wrap {
        display: block;
      }

      #sold-out {
        float: right;
        padding-top: 22px;
        padding-right: 20px;
      }

      .card-li {
        border: 1px solid black;
        list-style: none;
        float: left;
        margin: 30px 10px 10px 10px;
      }

      #used-price-date {
        display: flex;
        justify-content: space-between;

      }

      .used-img-price {
        align-items: center;
      }

      .container-fluid pt-5 {
        width: 1200px;
      }

      .form-control {
        width: 150px;
      }

      .used-addr {
        cursor: pointer;
      }

      .cat-item d-flex flex-column border mb-4 {
        border-radius: 10px;
      }

      #used-addr {
        border: none;
        background-color: white;
      }

      input#chk_top {
        display: none;
      }

      /*input 바로 다음의 label*/
      input#chk_top+label {
        cursor: pointer;
      }

      /*input 바로 다음의 label:before 에 체크하기 전 CSS 설정*/
      input#chk_top+label:before {
        content: "";
        display: inline-block;
        width: 17px;
        height: 17px;
        line-height: 17px;
        border: 1px solid #cbcbcb;
        vertical-align: middle;
        /*체크 전과 체크 후 높이 차이 때문에 설정*/
      }

      /*checked된 input 바로 다음의 label:before 에 체크 후 CSS 설정*/
      input#chk_top:checked+label:before {
        content: "\f00c";
        /*폰트어썸 유니코드*/
        font-family: "Font Awesome 5 free";
        /*폰트어썸 아이콘 사용*/
        font-weight: 900;
        /*폰트어썸 설정*/
        color: #fff;
        background-color: #B3E3C3;
        border-color: #B3E3C3;
        font-size: 13px;
        text-align: center;
      }

      #dropdownMenuButton11 {
        width: 300px;
        text-align: left;
      }

      #dropdown-opt {
        width: 300px;
      }

      #dropdown-opt a:hover {
        color: #212529;
        background-color: #B3E3C3;
      }
</style>

<script src="https://cdn.jsdelivr.net/npm/vue@2.7.8/dist/vue.js"></script>

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

<!-- Bootstrap ICON-->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">

<script type="module"
	src="<%=request.getContextPath()%>/resources/js/app.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/css/style.css">
</head>
<body>
	<div id="app">
		<main-header></main-header>
		<used-main></used-main>
	</div>
</body>
</html>