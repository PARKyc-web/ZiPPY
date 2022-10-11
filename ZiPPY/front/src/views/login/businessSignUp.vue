<template>
  <div class="signup-body">
    <div class="general-signup-container">
      <div class="row">
        <div class="col-lg-10 col-xl-9 mx-auto sign-container">
          <div
            class="card flex-row my-5 border-0 shadow rounded-3 overflow-hidden"
          >
            <div class="card-body p-4 p-sm-5">
              <h5 class="card-title text-center mb-5 fw-light fs-5">
                <img src="../../assets/zippy_logo.png" width="150px" />
              </h5>
              <hr class="my-4" />              
              <form id="signup-form" action="">
                <div id="first-form">
                  <div id="business-label-div">
                    <h5>업체 유형</h5>

                    <label class="business-label">
                      <input type="radio" name="business_type" value="property"/>
                      <span>공인중개사</span>
                    </label>

                    <label class="business-label">
                      <input type="radio" name="business_type" value="shop" />
                      <span>쇼핑몰</span>
                    </label>

                    <label class="business-label">
                      <input type="radio" name="business_type" value="move" />
                      <span>이사업체</span>
                    </label>

                    <label class="business-label">
                      <input type="radio" name="business_type" value="clean" />
                      <span>청소업체</span>
                    </label>
                  </div>
                  <hr class="my-4" />
                  <div class="form-floating mb-3">
                    <input                      
                      type="email"
                      class="form-control"
                      id="inputEmail"
                      name="user_email"
                      placeholder="name@example.com"
                      required
                      autofocus
                    />
                    <label for="inputEmail">이메일(아이디)</label>
                    <button
                      id="email_code"
                      type="button"
                      class="btn btn-outline-success"
                      @click="email_validation()"
                    >
                      인증번호 전송
                    </button>
                  </div>

                  <div class="form-floating mb-3">
                    <input
                      type="text"
                      class="form-control"
                      id="emailAuthentication"
                      placeholder="123456"
                      required
                      autofocus
                    />
                    <label for="emailAuthentication">인증번호(6자리)</label>
                    <button
                      id="email-confirm-btn"
                      type="button"
                      class="btn btn-outline-success"
                      @click="email_valid_check()"
                      disabled
                    >
                      인증번호 입력
                    </button>
                  </div>

                  <div class="form-floating mb-3">
                    <input
                      type="password"
                      class="form-control"
                      v-model="user_info.user_password"
                      id="password"
                      name="user_passwd"
                      placeholder="123456"                      
                      @change="password_validation()"
                    />
                    <label for="password">비밀번호</label>
                  </div>

                  <div class="form-floating mb-3">
                    <input
                      type="password"
                      class="form-control"
                      id="passwd-confirm"
                      placeholder="1234567890"
                      @change="password_confirm()"
                    />
                    <label for="passwd-confirm">비밀번호 재확인</label>
                  </div>

                  <small>
                    <p class="passwordCheck">
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        width="16"
                        height="16"
                        fill="currentColor"
                        class="bi bi-check-lg"
                        viewBox="0 0 16 16"
                      >
                        <path
                          d="M12.736 3.97a.733.733 0 0 1 1.047 0c.286.289.29.756.01 1.05L7.88 12.01a.733.733 0 0 1-1.065.02L3.217 8.384a.757.757 0 0 1 0-1.06.733.733 0 0 1 1.047 0l3.052 3.093 5.4-6.425a.247.247 0 0 1 .02-.022Z"
                        />
                      </svg>
                      비밀번호를 확인해 주세요!
                    </p>
                  </small>

                  <small>
                    <p class="passwordCheck">
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        width="16"
                        height="16"
                        fill="currentColor"
                        class="bi bi-check-lg"
                        viewBox="0 0 16 16"
                      >
                        <path
                          d="M12.736 3.97a.733.733 0 0 1 1.047 0c.286.289.29.756.01 1.05L7.88 12.01a.733.733 0 0 1-1.065.02L3.217 8.384a.757.757 0 0 1 0-1.06.733.733 0 0 1 1.047 0l3.052 3.093 5.4-6.425a.247.247 0 0 1 .02-.022Z"
                        />
                      </svg>
                      비밀번호가 일치하지 않습니다!
                    </p>
                  </small>

                  <small>
                    <p>
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        width="16"
                        height="16"
                        fill="currentColor"
                        class="bi bi-check-lg"
                        viewBox="0 0 16 16"
                      >
                        <path
                          d="M12.736 3.97a.733.733 0 0 1 1.047 0c.286.289.29.756.01 
                              1.05L7.88 12.01a.733.733 0 0 1-1.065.02L3.217 8.384a.757.757 
                              0 0 1 0-1.06.733.733 0 0 1 1.047 0l3.052 3.093 5.4-6.425a.247.247 0 0 1 .02-.022Z"
                        />
                      </svg>
                      비밀번호는 영어, 숫자, 특수문자를 1개이상 사용.
                    </p>

                    <p>
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        width="16"
                        height="16"
                        fill="currentColor"
                        class="bi bi-check-lg"
                        viewBox="0 0 16 16"
                      >
                        <path
                          d="M12.736 3.97a.733.733 0 0 1 1.047 0c.286.289.29.756.01 1.05L7.88 12.01a.733.733 0 0 1-1.065.02L3.217 8.384a.757.757 0 0 1 0-1.06.733.733 0 0 1 1.047 0l3.052 3.093 5.4-6.425a.247.247 0 0 1 .02-.022Z"
                        />
                      </svg>
                      비밀번호는 최소 8자 ~ 최대 20자.
                    </p>
                  </small>
                  <hr class="my-4" />

                  <div class="form-floating mb-3">
                    <input
                      type="text"
                      class="form-control"
                      id="phone"
                      placeholder="전화번호 입력"
                    />
                    <label for="phone">전화번호</label>
                    <button
                      id="phone_code"
                      type="button"
                      class="btn btn-outline-success"
                      @click="phone_validation()"
                    >
                      인증번호 전송
                    </button>
                  </div>

                  <div class="form-floating mb-3">
                    <input
                      type="text"
                      class="form-control"
                      id="phoneAuthentication"
                      placeholder="전화번호 입력"
                    />
                    <label for="phoneAuthentication">인증번호</label>
                    <button
                      type="button"
                      id="phoneNumberBtn"
                      class="btn btn-outline-success"
                      @click="phone_valid_check()"
                      disabled
                    >
                      전화번호 인증
                    </button>
                  </div>

                  <div class="form-floating mb-3">
                    <input
                      type="text"
                      class="form-control"
                      id="addressInput"
                      placeholder="주소검색"
                      disabled
                    />
                    <label for="addressInput">업체주소 (우편번호)</label>
                  </div>

                  <div class="form-floating mb-3">
                    <input
                      type="text"
                      class="form-control"
                      id="addressDetail"
                      v-model="user_info.addr_detail"
                      placeholder="상세주소"
                    />
                    <label for="addressDetail">상세주소</label>
                    <button
                      type="button"
                      class="btn btn-outline-success"
                      @click="find_address()"                      
                    >
                      주소검색
                    </button>
                  </div>

                  <br />
                  <hr class="my-4" />
                  <div class="sign-up-btn">
                    <button
                      class="btn btn-lg btn-primary btn-login fw-bold text-uppercase"
                      type="button"
                      @click="next()"
                    >
                      다음 페이지
                    </button>
                  </div>
                  <a class="d-block text-center mt-2 small" href="/"
                    >이미 아이디가 있으신가요?</a
                  >
                </div>

                <!-- 이 부분부터 2번째 입력 페이지 -->
                <div id="second-form">
                  <div class="form-floating mb-3">
                    <input
                      type="text"
                      class="form-control"
                      id="ceo_name"
                      placeholder="ceo"
                      v-model="user_info.ceo_name"
                    />
                    <label for="ceo_name">대표자 이름</label>
                  </div>

                  <div class="form-floating mb-3">
                    <input
                      type="text"
                      class="form-control"
                      id="business_name"
                      placeholder="company_name"
                      v-model = "user_info.company_name"
                    />
                    <label for="business_name">업체명</label>
                  </div>

                  <div class="">
                    <label for="business_explain"> 기업소개 </label>
                    <textarea
                      class="form-control"
                      id="business_explain"
                      placeholder="기업소개를 작성해주세요!"
                      rows="15"
                      v-model = "user_info.company_intro"
                    ></textarea>
                  </div>

                  <hr />
                  <div class="form-floating mb-3">
                    <input
                      type="text"
                      class="form-control"
                      id="business_number"
                      placeholder="123456"
                    />
                    <label for="businessNumber"> 사업자번호 </label>
                    <button type="button" class="btn btn-outline-success" @click="businessNumValid()">
                      유효성검사
                    </button>
                  </div>

                  <div class="form-floating mb-3" v-if="user_info.business_type == 'property'">
                    <input
                      type="email"
                      class="form-control"
                      id="broker_number"
                      placeholder="123456"
                    />
                    <button type="button" class="btn btn-outline-success">
                      유효성검사
                    </button>
                    <label for="brokerNumber">중개등록번호</label>
                  </div>

                  <div class="form-floating mb-3">
                    <input
                      type="file"
                      class="form-control"
                      id="business_number_pic"
                      placeholder="number_pic"
                    />
                    <label for="business_number_pic"> 사업자등록증 </label>
                  </div>

                  <div class="form-floating mb-3" v-if="user_info.business_type == 'property'">
                    
                    <input
                      type="file"
                      class="form-control"
                      id="broker_number_pic"
                      placeholder="number_pic"
                    />
                    <label for="broker_number_pic"> 중개등록증 </label>
                  </div>

                  <div class="sign-up-btn">
                    <button
                      class="btn btn-lg btn-primary btn-login fw-bold text-uppercase"
                      type="button"
                      @click="sign()">
                      회원가입
                    </button>
                  </div>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import loginFunc from '@/script/loginFunc';

export default {
  data() {
    return {
      // 회원가입 시, 필요한 정보
      user_info : {
        user_email: "",
        user_password : "",
        user_phone : "",
        user_addr : "",
        addr_detail : "",
        addr_postzone : "",
        business_type: "",
        ceo_name : "",
        company_name : "",
        company_intro : "",
        business_number : "",
        business_img : "",
        broker_id : "",
        broker_img : ""
      },

      // valid 되었는지 확인하는 정보
      email_valid : false,
      pass_valid: false,
      pass_confirm: false,
      phone_valid : false,

      // 인증코드가 담기는 Data
      emailCode: 12345672387,
      phoneCode: 12301684861,
    };
  },

  methods: {
    email_validation: async function () {      
      var data = await loginFunc.email_validation();
      console.log("이메일 Validation 실행한 후 : ", data);
      if(data != null){
        this.user_info.user_email = data.email;        
        this.emailCode = data.email_code;        
      }      
    },    

    email_valid_check: function(){      
      this.email_valid = loginFunc.email_valid_check(this.emailCode);
      console.log("email_valid :: ", this.email_valid);
      console.log("user_email :: ", this.user_info.user_email);
    },

    password_validation: function () {
      console.log("passwd-valid");
      this.pass_valid = loginFunc.password_validation();
    },

    password_confirm: function () {
      this.pass_confirm = loginFunc.password_confirm();
    },

    phone_validation: async function () {
      var data = await loginFunc.phone_validation();
      if(data != null){
        this.user_info.user_phone = data.phone;
        this.phoneCode = data.phone_code;
      }      
      console.log("인증번호 받기 후 잘 들어감? ",this.user_info.user_phone, this.phoneCode);
    },

    phone_valid_check: function () {
      console.log(this.phoneCode);
      this.phone_valid = loginFunc.phone_valid_check(this.phoneCode);

      console.log("==번호인증 완료 후==");
      console.log("user_phone :: ", this.user_info.user_phone);
    },

    /**
     * 주소를 검색하기 위해 주소검색창을 띄우는 API
     */
    find_address: function () {
      var addr = document.querySelector("#addressInput");
      var outside = this;
      new daum.Postcode({
        oncomplete: function (data) {
          console.log(data);
          addr.value = "(" + data.zonecode + ") " + data.address;
          outside.user_info.user_addr = data.address;
          outside.user_info.addr_postzone = data.zonecode;
        },
      }).open();     
    },

    next: function () {            
      var selected = document.querySelector("input[type=radio][name=business_type]:checked");      

      if(selected == null){        
        alert("업체유형을 선택해 주세요!");

      }else if(this.email_valid == false || this.phone_valid == false || this.pass_valid == false || this.user_info.user_addr == ""){
        alert("모든 정보를 인증 및 확인해주세요!");

      }else {
        this.user_info.business_type = selected.value;
        document.querySelector("#first-form").style.display = "none";
        document.querySelector("#second-form").style.display = "block";      
      }
      console.log(this.user_info);
    },

    businessNumValid : function(){
      console.log("==Business NUMBER VALIDATION==");
      var number = document.querySelector("#business_number").value;
      console.log("NUBMER :: ", number);
      axios({
        url: "https://api.odcloud.kr/api/nts-businessman/v1/status",
        method : "POST",
        params :{
          "serviceKey" : "QZf4Ip/iukGVGGNo2Yp1ei7ISnJ2sOwUgmmBjLQt6mCw73ftY5N0jt6ck1Qz34mGkNv4FQAysldaby08pQpYEg=="
        },
        data :{
          "b_no" : ["123456789"]
        }
      }).then(res => {
        console.log(res);
        console.log(res.data.data);

      }).catch(error =>{
        console.log(error);
      })      
    },

    sign: function () {
      console.log("sign-up RUN");      
      console.log(this.user_info);
      console.log(JSON.stringify(this.user_info));
      console.log("==================");
            
      if (this.pass_valid == true && this.pass_confirm == true &&
          this.email_valid == true && this.phone_valid == true) {    
        alert("회원가입을 축하합니다!!");
        axios({
          url: "http://localhost:8090/zippy/member/bSignUp",
          method: "POST",
          param: {
            info: JSON.stringify(this.user_info),
          },

          data: {
            info: JSON.stringify(this.user_info),            
          },
        })
          .then((res) => {
            console.log(res);
          })
          .catch((error) => {
            console.log(error);
          });
      } else{
        alert("모든 정보를 입력해주세요!");
      }

    },  
  },
};
</script>

<style scoped>
.signup-body {
  background: white;
}

.signup-body button {
  color: black;
  background-color: #b3e3c3;
}

.sign-container {
  width: 35%;
  min-width: 600px;
}

#inputEmail,
#emailAuthentication,
#phone,
#phoneAuthentication,
#phone,
#business_number,
#broker_number {
  min-width: 300px;
  width: 70%;
  display: inline-block;
  margin: 5px;
}

#passwordCheck {
  color: red;
}

.sign-up-btn {
  width: 100%;
  text-align: center;
}

.btn-login {
  width: 50%;
  border: 1px solid black;
}

#second-form {
  display: none;
}

#business-label-div {
  padding: 5px;
  margin: 5px;
  text-align: center;
}

.business-label {
  border: 1px solid black;
  width: 15%;
  min-width: 110px;
  margin: 5px;
}

.business-label input[type="radio"] {
  display: none;
}

.business-label input[type="radio"] + span {
  display: inline-block;
  padding: 15px 10px;
  background-color: #ffffff;
  text-align: center;
  cursor: pointer;
  width: 100%;
}

.business-label input[type="radio"]:checked + span {
  background-color: #b3e3c3;
  color: black;
}
.passwordCheck {
  color: red;
  display: none;
}
</style>
