<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ZiPPY : 쇼핑몰</title>
<script src="https://cdn.jsdelivr.net/npm/vue@2.7.8/dist/vue.js"></script>
	<!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>상세페이지</title>
  <!-- Bootstrap CSS -->
 <!-- CSS only -->
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
 integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" 
   integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
  <!-- owl carousel CSS -->
  <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/shop/owl.carousel.min.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/shop/lightslider.min.css">
  <!--bootstrap icon CSS-->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
  <!-- style CSS -->
  <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/shop/detail-style.css">
		<script type="module" src="<%=request.getContextPath()%>/resources/js/app.js"></script>
<style>
	#star {
    background-color:#f8f9fa;
    width:500px;
    height:200px;
    border-radius: 10px;
    text-align: center;
    margin-left:15px;
	}
  	#star i{
    color:#B3E3C3;
	}
  </style>
</head>
<body>
	<div id="app">
		<main-header></main-header>
		<shop-nav></shop-nav>
		<product-detail></product-detail>
	</div>
</body>
<!-- jquery plugins here-->
  <!-- jquery -->
  <script src="<%=request.getContextPath()%>/resources/js/shop/jquery-1.12.1.min.js"></script>
  <!-- popper js -->
  <script src="<%=request.getContextPath()%>/resources/js/shop/popper.min.js"></script>
  <!-- bootstrap js -->
  <script src="<%=request.getContextPath()%>/resources/js/shop/bootstrap.min.js"></script>
  <!-- easing js -->
  <script src="<%=request.getContextPath()%>/resources/js/shop/jquery.magnific-popup.js"></script>
  <!-- swiper js -->
  <script src="<%=request.getContextPath()%>/resources/js/shop/lightslider.min.js"></script>
  <!-- swiper js -->
  <script src="<%=request.getContextPath()%>/resources/js/shop/masonry.pkgd.js"></script>
  <!-- particles js -->
  <script src="<%=request.getContextPath()%>/resources/js/shop/owl.carousel.min.js"></script>
  <script src="<%=request.getContextPath()%>/resources/js/shop/jquery.nice-select.min.js"></script>
  <!-- slick js -->
  <script src="<%=request.getContextPath()%>/resources/js/shop/slick.min.js"></script>
  <script src="<%=request.getContextPath()%>/resources/js/shop/swiper.jquery.js"></script>
  <script src="<%=request.getContextPath()%>/resources/js/shop/jquery.counterup.min.js"></script>
  <script src="<%=request.getContextPath()%>/resources/js/shop/waypoints.min.js"></script>
  <script src="<%=request.getContextPath()%>/resources/js/shop/contact.js"></script>
  <script src="<%=request.getContextPath()%>/resources/js/shop/jquery.ajaxchimp.min.js"></script>
  <script src="<%=request.getContextPath()%>/resources/js/shop/jquery.form.js"></script>
  <script src="<%=request.getContextPath()%>/resources/js/shop/jquery.validate.min.js"></script>
  <script src="<%=request.getContextPath()%>/resources/js/shop/mail-script.js"></script>
  <script src="<%=request.getContextPath()%>/resources/js/shop/stellar.js"></script>
  <!-- custom js -->
  <script src="<%=request.getContextPath()%>/resources/js/shop/theme.js"></script>
  <script src="<%=request.getContextPath()%>/resources/js/shop/custom.js"></script>
</body>

</html>