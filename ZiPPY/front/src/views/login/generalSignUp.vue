<template>
  <form id="generalForm">
  <div class="general-signup-body">
    <div class="general-signup-container">
      <div class="row">
        <div class="col-lg-10 col-xl-9 mx-auto sign-container">
          <div class="card flex-row my-5 border-0 shadow rounded-3 overflow-hidden">
            <div class="card-body p-4 p-sm-5">
              <h5 class="card-title text-center mb-5 fw-light fs-5">
                <img src="../../assets/zippy_logo.png" width="150px" />
              </h5>
              <hr class="my-4" />                            
                <div id="first-form">
                  <div class="form-floating mb-3">
                    <input
                      type="email"
                      name="email"
                      class="form-control"
                      id="inputEmail"                      
                      placeholder="name@example.com"
                      v-model="user_info.email"
                    />
                    <label for="inputEmail">이메일(아이디)</label>
                  </div>
                  <button
                      id="email_code"
                      type="button"
                      class="btn btn-outline-success"
                      @click="email_validation()"
                    >
                      인증번호 전송
                    </button>

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
                      type="button"
                      id="email-confirm-btn"
                      class="btn btn-outline-success"
                      disabled
                      @click="email_valid_check()"
                    >
                      인증번호 입력
                    </button>
                  </div>

                  <div class="form-floating mb-3">
                    <input
                      type="password"
                      class="form-control"
                      v-model="user_info.password"
                      id="password"
                      name="password"
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
                      v-model="user_info.phoneNumber"
                      name="phoneNumber"
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
                      name="userName"
                      type="text"
                      class="form-control"
                      v-model="user_info.userName"
                      id="user_name"
                      placeholder="park"
                    />
                    <label for="user_name">사용자 이름</label>
                  </div>

                  <div class="form-floating mb-3">
                    <input
                      name="nickName"
                      type="text"
                      class="form-control"
                      v-model="user_info.nickName"
                      id="user_nick"
                      placeholder="nick"
                    />
                    <label for="user_nick">닉네임</label>
                  </div>

                  <div class="form-floating mb-3">
                    <input
                      name="userBirth"
                      type="date"
                      v-model="user_info.userBirth"
                      class="form-control"
                      id="user_birth"
                      placeholder="11223344"
                    />
                    <label for="user_birth">생년월일</label>
                  </div>                  
                  <div id="gender-label-div">
                  <label class="gender-label">
                      <input type="radio" name="userGender" value="M"/>
                      <span>남자</span>
                    </label>

                    <label class="gender-label">
                      <input type="radio" name="userGender" value="F" />
                      <span>여자</span>
                    </label>
                  </div>
                  <br />
                  <div class="form-floating mb-3">
                    <input
                      name="userAddress"
                      type="text"
                      class="form-control"
                      id="addressInput"
                      placeholder="주소검색"
                      v-model="user_info.userAddress"
                      disabled
                    />
                    <label for="addressInput">(우편번호) 주소</label>
                  </div>

                  <div class="form-floating mb-3">
                    <input
                      name="addressDetail"
                      type="text"
                      class="form-control"
                      v-model="user_info.addressDetail"
                      id="addressDetail"                      
                      placeholder="상세주소"
                    />
                    <label for="addressDetail">상세주소</label>
                    <button
                      type="button"
                      class="btn btn-outline-success"
                      @click="find_address()"
                      return
                      false
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
                      @click="signup()"
                    >
                      회원가입
                    </button>
                  </div>
                  <a class="d-block text-center mt-2 small" href="/"
                    >이미 아이디가 있으신가요?</a
                  >
                </div>              
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</form>
</template>

<script>
import axios from "axios";
import loginFunc from '../../script/loginFunc.js';

export default {
  data() {
    return {
      // Data : Axios로 back에 보낼 데이터
      user_info: {
        email: "",
        password: "",
        phoneNumber: "",
        userName: "",
        nickName: "",
        userGender : "",
        userBirth: "",
        userAddress: "",
        addressDetail: "",
        zipCode : "",
        memberType : 0,
        userState: 0,
        isSocial : 0,
        profileImage : "default.png"
      },

      // 모든 데이터를 정확하게 입력했는지 검사하는 Data
      pass_valid: false,
      pass_confirm: false,
      email_valid: false,
      phone_valid: false,      
      
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
        console.log(data);
        this.user_info.email = data.email;      
        this.emailCode = data.email_code;        
      }      
    },

    email_valid_check: function(){      
      this.email_valid = loginFunc.email_valid_check(this.emailCode);
      console.log("email_valid :: ", this.email_valid);
      console.log("email :: ", this.user_info.email);
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
        this.user_info.phoneNumber = data.phone;
        this.phoneCode = data.phone_code;
      }      
      console.log("인증번호 받기 후 잘 들어감? ",this.user_info.phoneNumber, this.phoneCode);
    },

    phone_valid_check: function () {
      console.log(this.phoneCode);
      this.phone_valid = loginFunc.phone_valid_check(this.phoneCode);

      console.log("==번호인증 완료 후==");
      console.log("user_phone :: ", this.user_info.phoneNumber);
    },

    /**
     * 주소를 검색하기 위해 주소검색창을 띄우는 API
     */
    find_address: function () {      
      var outside = this;
      new daum.Postcode({
        oncomplete: function (data) {
          console.log(data);          
          outside.user_info.userAddress = data.address;
          outside.user_info.zipCode = data.zonecode;
        },
      }).open();     
    },

    /**
     * 모든 값을 입력하고, 인증까지 완료했으면 회원가입을 완료할 수 있는 메소드
     */
    signup: async function () {               
      // var selected = document.querySelector("input[type=radio][name=userGender]:checked");
      // this.user_info.userGender = selected.value;
      // console.log(this.user_info);
      
      if (this.pass_valid == true && this.pass_confirm == true &&
          this.email_valid == true && this.phone_valid == true &&
          this.user_info.userAddressr != "" && this.user_info.userName != ""  &&
          this.user_info.nickName != "" && this.user_info.userBirth != "") {            

          var formData = new FormData(document.querySelector('#generalForm'));          
          console.log(formData);
          console.log(this.user_info);

        alert("회원가입을 축하합니다!!");
        var temp = await axios({
          url: "http://localhost:8090/zippy/member/gSignUp",
          method: "POST",
          // headers :{
          //   'Content-Type': 'multipart/form-data'
          //   "Content-Type" : "application/x-www-form-urlencoded;charset=UTF-8"
          // },
          data: formData          
        })
          .then((res) => {
            console.log(res);
          })
          .catch((error) => {
            console.log(error);
          });          

          // this.$router.push("/");

      } else{
        alert("모든 정보를 입력해주세요!");
      }
    },    
  },
};
</script>

<style scoped>
.general-signup-body {
  background: white;
}

.general-signup-body button {
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
#phone {
  min-width: 300px;
  width: 70%;
  display: inline-block;
}
.passwordCheck {
  color: red;
  display: none;
}

.sign-up-btn {
  width: 100%;
  text-align: center;
}

.btn-login {
  width: 50%;
}

#gender-label-div {
  padding: 5px;
  margin: 5px;
  text-align: center;
}

.gender-label {
  border: 1px solid black;
  width: 22%;
  min-width: 118px;
  margin: 5px;
}

.gender-label input[type="radio"] {
  display: none;
}

.gender-label input[type="radio"] + span {
  display: inline-block;
  padding: 15px 10px;
  background-color: #ffffff;
  text-align: center;
  cursor: pointer;
  width: 100%;
}
.gender-label input[type="radio"]:checked + span {
  background-color: #b3e3c3;
  color: black;
}
</style>