<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">	
		<script src="https://cdn.jsdelivr.net/npm/vue@2.7.8/dist/vue.js"></script>
	
	<!-- CSS only -->
  	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
  		  integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
  		          
  	<!-- JavaScript Bundle with Popper -->
  	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" 
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

  	<!-- Bootstrap ICON-->
  	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css"> 
	<script type="module" src="<%=request.getContextPath()%>/resources/js/app.js"></script>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css">
</head>
<body>

	<div id="app">
		<main-header></main-header>
	</div>
	
	<button onClick="location.href='home'">부동산</button>
	<button onClick="location.href='home'">중고거래</button>
	<button onClick="location.href='../zippy/shop/main'">쇼핑몰</button>
	<button onClick="location.href='home'">이사도우미</button>
	<button onClick="location.href='home'">청소도우미</button>
	
</body>
</html>