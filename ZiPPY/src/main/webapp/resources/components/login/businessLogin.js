let template = `
<div class="member-body">
<div class="container">
  <div class="row">
    <div class="col-lg-10 col-xl-9 mx-auto sign-container">
      <div class="card flex-row my-5 border-0 shadow rounded-3 overflow-hidden">            
        <div class="card-body p-4 p-sm-5">
          <h5 class="card-title text-center mb-5 fw-light fs-5"><img src="components/images/zippy_logo.png" width="150px"></h5>
          <form id="first-form">

            <p><label> 업체 유형 </label></p>
            <label><input type="radio" name="business_type" value="property"> 공인중개사 </input></label>
            <label><input type="radio" name="business_type" value="shop"> 쇼핑몰 </input></label>
            <label><input type="radio" name="business_type" value="move"> 이사업체 </input></label>
            <label><input type="radio" name="business_type" value="clean"> 청소업체 </input></label>

            <button @click="check_business_type">업체유형 확인</button>

            <div class="form-floating mb-3">
              <input type="email" class="form-control" id="floatingInputEmail" placeholder="name@example.com" required autofocus>                  
              <label for="floatingInputEmail">이메일(아이디)</label>           
              <button type="button" class="btn btn-outline-success" @click="email_validation">인증번호 전송</button>
            </div>

            <div class="form-floating mb-3">
              <input type="text" class="form-control" id="emailAuthentication" placeholder="123456" required autofocus>                  
              <label for="emailAuthentication">인증번호(6자리)</label>
              <button type="button" class="btn btn-outline-success" onclick="">인증번호 입력</button>
            </div>

            <div class="form-floating mb-3">
              <input type="password" class="form-control" id="password" placeholder="password" @change="password_validation">
              <label for="password">비밀번호</label>
            </div>
            

            <small><p id="passwordCheck" v-if="pwd_valid == false"> * 비밀번호를 확인해 주세요! </p></small>
            <div class="form-floating mb-3">
              <input type="password" class="form-control" id="password_confirm" placeholder="password" onchange="">              
              <label for="password_confirm">비밀번호 재확인</label>
            </div>            
            
            <small><p> * 비밀번호는 영어, 숫자, 특수문자를 1개이상 전부 사용하여, 8~20자입니다. </p></small>  
            <hr>

            <div class="form-floating mb-3">
              <input type="text" class="form-control" id="floatingPhone" placeholder="전화번호 입력">
              <label for="floatingPhone">전화번호</label>          
              <button type="button" class="btn btn-outline-success" onclick="">인증번호 전송</button>     
            </div>

            <div class="form-floating mb-3">
              <input type="text" class="form-control" id="phoneAuthentication" placeholder="전화번호 입력">
              <label for="phoneAuthentication">인증번호</label>
              <button type="button" class="btn btn-outline-success" onclick="">전화번호 인증</button>           
            </div>


            <div class="form-floating mb-3">
              <input type="text" class="form-control" id="addressInput" placeholder="주소검색" disabled>
              <label for="addressInput">주소 (우편번호)</label>                                 
            </div>

            <div class="form-floating mb-3">
              <input type="text" class="form-control" id="addressDetail" placeholder="상세주소">
              <label for="addressDetail">상세주소</label>                           
              <button type="button" class="btn btn-outline-success" @click="address_find" return false>주소검색</button>                  
            </div>
            
            <div class="d-grid mb-2">
              <button class="btn btn-lg btn-primary btn-login fw-bold text-uppercase" type="button" @click="next_page">다음페이지</button>
            </div>
            <a class="d-block text-center mt-2 small" href="#">이미 아이디가 있으신가요?</a>

            <hr class="my-4">
          </form>


          <!-- =================여기서 부터 2번째 페이지================= -->
          <div id="second-form">
            <div class="form-floating mb-3">
              <input type="text" class="form-control" id="ceo_name" placeholder="ceo">
              <label for="ceo_name">대표자 이름</label>
            </div> 

            <div class="form-floating mb-3">
              <input type="text" class="form-control" id="business_name" placeholder="company_name">
              <label for="business_name">업체명</label>
            </div>

            <div class="form-floating mb-3">
              <textarea class="form-control" id="business_explain" placeholder="explain"
                        rows="25"></textarea>
              <label for="business_explain"> 기업소개 </label>                                    
            </div>     

            <hr>
            <div class="form-floating mb-3">
              <input type="email" class="form-control" id="broker_number" placeholder="123456" required autofocus>
              <button type="button"> 중개등록번호 검사 </button>
              <label for="brokerNumber">중개등록번호</label>           
            </div>

            <div class="form-floating mb-3">
              <input type="text" class="form-control" id="business_number" placeholder="123456" required autofocus>                  
              <label for="businessNumber"> 사업자번호 </label>
              <button type="button"> 사업자번호 </button>
            </div>

            <div class="form-floating mb-3">
              <input type="file" class="form-control" id="business_number_pic" placeholder="number_pic">
              <label for="business_number_pic"> 사업자등록증 </label>
            </div> 

            <div class="form-floating mb-3">
              <input type="file" class="form-control" id="broker_number_pic" placeholder="number_pic">
              <label for="broker_number_pic"> 중개등록증 </label>
            </div>        
            
            <div class="d-grid mb-2">
              <button class="btn btn-lg btn-primary btn-login fw-bold text-uppercase" type="button">회원가입</button>
            </div>                
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
</div>`;

export default {
  template,
  data() {
    return {
      email_validation_code : 0,
      pwd_valid : true
    }
  },
  methods:{
    email_validation: function(){
      console.log("email_validation");
      // $.ajax({
      //   url: "http://127.0.0.1:8090/validation/email",
      //   data : {
      //     email : "erty1201@naver.com"            
      //   },
      //   method: 'GET',
      //   dataType : 'JSON',
      //   type : 'JSON',

      //   success: function(data){
      //     console.log(data);
      //   },

      //   error : function(reject){
      //     console.log(reject);
      //   }
      // });
    },

    address_find: function(){
      new daum.Postcode({
        oncomplete: function(data) {
          console.log(data);          
          alert(data.zonecode);
          document.querySelector('#addressInput').value = data.address + `(${data.zonecode})` ;  
        }
      }).open();
    },

    next_page: function(){
      if(this.password_check() == true){
        console.log('Next Page');
        console.log(document.querySelector('#second-form').style.display);
        document.querySelector('#second-form').style.display = 'block';
        document.querySelector('#first-form').style.display = 'none';
        $(window).scrollTop(0);
      }  
    },

    password_check: function(){
      alert('부족한 정보를 입력해주세요!');
      return true;
    },

    password_validation: function(){
      console.log('===password_validation');
      const regex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,20}$/;      
      let text = document.querySelector("#password").value;      
      this.pwd_valid = regex.test(text);
      return regex.test(text);
    },

    password_again: function(){
      console.log('===password_again');
      let password = document.querySelector("#password").value;
      let password_a = document.querySelector("#password_confirm").value;      

      return (password == password_a) ? true : false;
    },

    check_business_type: function(){
      console.log($('input:radio[name=business_type]').is(':checked').value());
    }
  }
}
