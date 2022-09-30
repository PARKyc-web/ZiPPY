<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">  

	<script src="https://cdn.jsdelivr.net/npm/vue@2.7.8/dist/vue.js"></script>
	
	<!-- CSS only -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" 
  		  integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
  		          
  	<!-- JavaScript Bundle with Popper -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" 
          integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
  
  <script src="https://code.jquery.com/jquery-3.6.1.js" integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI=" 
                                                        crossorigin="anonymous"></script>                                                        
	<!-- Bootstrap ICON-->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css"> 

  <script type="module" src="<%=request.getContextPath()%>/resources/js/app.js"></script>
  <link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css">

  <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
  <style>
    #second-form {
      display: none;
    }
    
    .member-body {
      background: white;  
    }
    
    .member-body button {
        color : black;
        background-color: #B3E3C3;
    }
    
    .btn-login {
      font-size: 0.9rem;
      letter-spacing: 0.05rem;
      padding: 0.5rem 0.75rem;
    }
    
    .sign-container {
      width: 50%;
    }
    
    #emailAuthentication, #phoneAuthentication, #floatingInputEmail, #floatingPhone{
      width: 70%;
      display: inline-block;
    }    
    </style>

</head>
<body>
  <div id="app">
    <main-header></main-header>
    <business-login></business-login>    
  </div>

</body>
</html>