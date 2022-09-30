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
	
		<!-- CSS only -->
  	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
  		  integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
  	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css">
		<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/shop/shopStyle.css">	
  	<!-- JavaScript Bundle with Popper -->
  	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" 
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

  	<!-- Bootstrap ICON-->
  	<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
		
		<script type="module" src="<%=request.getContextPath()%>/resources/js/app.js"></script>

</head>
<body>
	<div id="app">
		<main-header></main-header>
		<shop-nav></shop-nav>
		<slide></slide>
		<card :products="${products}"></card>
	</div>
	<table>
	<c:forEach items="${products }" var="product">
					<tr>
						<td>업체명:${product.businessEmail }</td>
						<td>상품명:${product.shopProductName }</td>
						<td>가격:${product.shopProductPrice }</td>
						<td>${product.shopMainImg }</td>
						<td>사진:<img src="<c:url value='/resources/img/shop/${product.shopMainImg }.jpg'/>" style="width:200px;"></td>
					</tr>
	</c:forEach>
	</table>
</body>
</html>