<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
  
  	<script src="https://code.jquery.com/jquery-3.6.1.js" integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI=" 
                                                          crossorigin="anonymous"></script>                                                        
	<!-- Bootstrap ICON-->
  	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css"> 
		
	<script type="module" src="<%=request.getContextPath()%>/resources/js/app.js"></script>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css">	
</head>


<div id="app">
	<main-header></main-header>
	<shop-nav></shop-nav>
</div>

  <body class="member-body">
    <div class="container">
      <div class="row">
        <div class="col-lg-10 col-xl-9 mx-auto sign-container">
          <div class="card flex-row my-5 border-0 shadow rounded-3 overflow-hidden">            
            <div class="card-body p-4 p-sm-5">
              <h5 class="card-title text-center mb-5 fw-light fs-5"><img src="<%=request.getContextPath()%>/resources/images/zippy_logo.png" width="150px"></h5>
              <form>
                <div class="form-floating mb-3">
                  <input type="email" class="form-control" id="floatingInputEmail" placeholder="name@example.com" required autofocus>
                  <button> 인증번호 전송 </button>
                  <label for="floatingInputEmail">이메일(아이디)</label>           
                </div>

                <div class="form-floating mb-3">
                  <input type="text" class="form-control" id="emailAuthentication" placeholder="123456" required autofocus>                  
                  <label for="emailAuthentication">인증번호(6자리)</label>
                  <button> 인증번호 입력 </button>
                </div>

                <div class="form-floating mb-3">
                  <input type="password" class="form-control" id="floatingPassword" placeholder="비밀번호 입력">
                  <label for="floatingPassword">비밀번호</label>
                </div>

                <div class="form-floating mb-3">
                  <input type="password" class="form-control" id="floatingPasswordConfirm" placeholder="비밀번호 재확인">
                  <label for="floatingPasswordConfirm">비밀번호 재확인</label>
                </div>
  
                <hr>

                <div class="form-floating mb-3">
                  <input type="text" class="form-control" id="floatingInputUsername" placeholder="이름 입력">
                  <label for="floatingInputUsername">이름</label>
                </div> 

                <div class="form-floating mb-3">
                  <input type="text" class="form-control" id="floatingNickName" placeholder="닉네임 입력">
                  <label for="floatingNickName">닉네임</label>
                </div>
  
                <div class="form-floating mb-3">
                  <input type="text" class="form-control" id="floatingPhone" placeholder="전화번호 입력">
                  <label for="floatingPhone">전화번호</label>          
                  <button onclick=""> 문자전송 </button>        
                </div>

                <div class="form-floating mb-3">
                  <input type="text" class="form-control" id="phoneAuthentication" placeholder="전화번호 입력">
                  <label for="phoneAuthentication">인증번호</label>
                  <button onclick=""> 전화번호 인증 </button>
                </div>


                <div class="form-floating mb-3">
                  <input type="text" class="form-control" id="addressInput" placeholder="주소검색" disabled>
                  <label for="addressInput">주소 (우편번호)</label>                                 
                </div>

                <div class="form-floating mb-3">
                  <input type="text" class="form-control" id="addressDetail" placeholder="상세주소">
                  <label for="addressDetail">상세주소</label>                                   
                  <button onclick="find_address()"> 주소검색 </button>
                </div>
                            
                <label> 성별 > </label>
                <input type="radio" name="gender" value="남자">M</input>
                <input type="radio" name="gender" value="여자">F</input>
                
                <div class="d-grid mb-2">
                  <button class="btn btn-lg btn-primary btn-login fw-bold text-uppercase" type="submit">회원가입</button>
                </div>  
                <a class="d-block text-center mt-2 small" href="#">이미 아이디가 있으신가요?</a>
  
                <hr class="my-4">  
                <div class="d-grid mb-2">
                  <button class="btn btn-lg btn-google btn-login fw-bold text-uppercase" type="submit">
                    <i class="fab fa-google me-2"></i> Sign up with Google
                  </button>
                </div>
  
                <div class="d-grid">
                  <button class="btn btn-lg btn-facebook btn-login fw-bold text-uppercase" type="submit">
                    <i class="fab fa-facebook-f me-2"></i> Sign up with Facebook
                  </button>
                </div>
  
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>

	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>  
	<script>
	  function find_address(){
	    new daum.Postcode({
	        oncomplete: function(data) {
	          console.log(data);          
	          alert(data.zonecode);
	          document.querySelector('#addressInput').value = data.address;          
	        }
	    }).open();
	  }
	
	</script>
</body>  
</html>