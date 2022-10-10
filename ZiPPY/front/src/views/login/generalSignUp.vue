<template>
  <div class="general-signup-body">
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
                      v-model="user_info.user_name"
                      id="user_name"
                      placeholder="park"
                    />
                    <label for="user_name">사용자 이름</label>
                  </div>

                  <div class="form-floating mb-3">
                    <input
                      type="text"
                      class="form-control"
                      v-model="user_info.user_nick"
                      id="user_nick"
                      placeholder="nick"
                    />
                    <label for="user_nick">닉네임</label>
                  </div>

                  <div class="form-floating mb-3">
                    <input
                      type="date"
                      v-model="user_info.user_birth"
                      class="form-control"
                      id="user_birth"
                      placeholder="11223344"
                    />
                    <label for="user_birth">생년월일</label>
                  </div>                  
                  <div id="gender-label-div">
                  <label class="gender-label">
                      <input type="radio" name="gender" value="M"/>
                      <span>남자</span>
                    </label>

                    <label class="gender-label">
                      <input type="radio" name="gender" value="F" />
                      <span>여자</span>
                    </label>
                  </div>
                  <br />
                  <div class="form-floating mb-3">
                    <input                      
                      type="text"
                      class="form-control"
                      id="addressInput"
                      placeholder="주소검색"
                      v-model="user_info.user_addr"
                      disabled
                    />
                    <label for="addressInput">(우편번호) 주소</label>
                  </div>

                  <div class="form-floating mb-3">
                    <input
                      type="text"
                      class="form-control"
                      v-model="user_info.addr_detail"
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
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { BIconTelephoneMinus } from "bootstrap-vue";

export default {
  data() {
    return {
      // Data : Axios로 back에 보낼 데이터
      user_info: {
        user_email: "",
        user_password: "",
        user_phone: "",
        user_name: "",
        user_nick: "",
        gender : "",
        user_birth: "",
        user_addr: "",        
        addr_detail: "",
        addr_postzone : ""          
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
    /**
     * Eamil 인증을 위한 Email 전송 인증번호 6자리
     */
    email_validation: function () {
      var reg =
        /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
      var email = document.querySelector("#inputEmail").value;      
      if (reg.test(email)) {
        this.user_info.user_email = email;        
        document.querySelector("#email-confirm-btn").disabled = false;
        alert("Email을 전송하였습니다.");
        // axios.get("/validation/email", {
        //     params: {
        //       email: this.user_email,
        //     },
        //   })
        //   .then((res) => {
        //     console.log(res);
        //     this.emailCode = res.data;
        //   })
        //   .catch((error) => {
        //     console.log(error);
        //   });
      } else {
        alert("이메일 양식을 맞춰주세요!");
      }
    },

    /**
     * 인증번호와 유저가 입력한 숫자가 동일한지 확인하는 메소드
     */
    email_valid_check: function () {
      var code = document.querySelector("#emailAuthentication");      

      if (this.emailCode == code.value) {
        alert("이메일인증 성공!");
        document.querySelector("#inputEmail").disabled = true;
        document.querySelector("#emailAuthentication").disabled = true;
        document.querySelector("#email-confirm-btn").disabled = true;
        document.querySelector("#email_code").disabled = true;
        this.email_valid = true;
      } else {
        alert("인증번호가 틀렸습니다!");
      }
    },

    /**
     * 비밀번호가 사이트 규정에 맞는지 확인하는 메소드
     */
    password_validation: function () {
      console.log("passwd-valid");
      var reg =
        /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-+]).{8,20}$/;
      var password = document.querySelector("#password").value;


      if(reg.test(password)){
        this.pass_valid = reg.test(password);
        document.getElementsByClassName("passwordCheck")[0].style.display =
        "none";
      } else{
        document.getElementsByClassName("passwordCheck")[0].style.display =
        "block";
      }      
    },

    /**
     * 비밀번호와 비밀번호 재확인이 동일한지 확인하는 메소드
     */
    password_confirm: function () {
      var password = document.querySelector("#password").value;
      var confirm = document.querySelector("#passwd-confirm").value;      
            
      this.pass_confirm = password == confirm ? true : false;
      if(this.pass_confirm){
        document.getElementsByClassName("passwordCheck")[1].style.display =
        "none";
      } else{
        document.getElementsByClassName("passwordCheck")[1].style.display =
        "block";
      }
      
    },

    /**
     * 핸드폰 번호 인증을 하기 위해서 SMS 문자를 보내는 메소드
     */
    phone_validation: function () {
      var reg = /^010[0-9]{8}$/;
      var number = document.querySelector("#phone").value;

      if (reg.test(number)) {
        alert("인증번호를 전송하였습니다!");
        this.user_info.user_phone = number;
        document.querySelector("#phoneNumberBtn").disabled = false;
        // axios({
        //   url : "/validation/phone",
        //   method : "GET",

        //   param : {
        //     phone : this.user_info.user_phone
        //   }

        // }).then(res => {
        //   console.log(res);

        // }).catch(error =>{
        //   console.log(error);
        // });
      } else {
        alert("휴대폰 번호를 확인해주세요!");
        console.log(number);
      }
    },

    /**
     * 핸드폰으로 보낸 인증번호가 동일한지 확인하는 메소드
     */
    phone_valid_check: function () {
      console.log("phone-valid-check");
      var input_phone = document.querySelector("#phone");
      var valid = document.querySelector("#phoneAuthentication");
      var send_btn = document.querySelector("#phone_code");
      var valid_btn = document.querySelector("#phoneNumberBtn");
      if (valid.value == this.phoneCode) {
        input_phone.disabled = true;
        valid.disabled = true;
        send_btn.disabled = true;
        valid_btn.disabled = true;
        this.phone_valid = true;        
        alert("인증이 완료되었습니다!");

      } else {
        alert("인증번호가 일치하지 않습니다!");
      }
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
          addr.value = "(" + data.zonecode + ")" + data.address;
          outside.user_info.user_addr = data.address;
          outside.user_info.addr_postzone = data.zonecode;
        },
      }).open();     
    },

    /**
     * 모든 값을 입력하고, 인증까지 완료했으면 회원가입을 완료할 수 있는 메소드     *
     */
    signup: function () {            
      var selected = document.querySelector("input[type=radio][name=gender]:checked");
      this.user_info.gender = selected.value;
      console.log(this.user_info);
      console.log(JSON.stringify(this.user_info));
            
      if (this.pass_valid == true && this.pass_confirm == true &&
          this.email_valid == true && this.phone_valid == true &&
          this.user_info.user_addr != "" && this.user_info.user_name != ""  &&
          this.user_info.user_nick != "" && this.user_info.user_birth != "") {    
        alert("회원가입을 축하합니다!!");
        axios({
          url: "http://localhost:8090/zippy/member/gSignUp",
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