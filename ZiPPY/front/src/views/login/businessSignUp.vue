<template>
  <div style="border-top:1px solid #D6D6D6;">
    <form id="businessForm">
      <div class="signup-body">
        <div class="general-signup-container">
          <div class="row">
            <div class="col-lg-10 col-xl-9 mx-auto sign-container">
              <div class="card flex-row my-5 border-0 shadow rounded-3 overflow-hidden">
                <div class="card-body p-4 p-sm-5">
                  <h5 class="card-title text-center mb-5 fw-light fs-5">
                    <img src="@/assets/zippy_logo2.png" width="150px" />
                  </h5>
                  <hr class="my-4" />

                  <div id="first-form">
                    <div id="business-label-div">
                      <h5>업체 유형</h5>

                      <label class="business-label">
                        <input type="radio" name="memberType" value="0" />
                        <span>공인중개사</span>
                      </label>

                      <label class="business-label">
                        <input type="radio" name="memberType" value="2" />
                        <span>쇼핑몰</span>
                      </label>

                      <label class="business-label">
                        <input type="radio" name="memberType" value="3" />
                        <span>이사업체</span>
                      </label>

                    </div>
                    <hr class="my-4" />
                    <div class="form-floating mb-3">
                      <input type="email" class="form-control" id="inputEmail" name="email"
                        placeholder="name@example.com" required autofocus />
                      <label for="inputEmail">이메일(아이디)</label>
                      <button id="email_code" type="button" class="btn btn-outline-success" @click="email_validation()">
                        인증번호 전송
                      </button>
                    </div>

                    <div class="form-floating mb-3">
                      <input type="text" class="form-control" id="emailAuthentication" placeholder="123456" required
                        autofocus />
                      <label for="emailAuthentication">인증번호(6자리)</label>
                      <button id="email-confirm-btn" type="button" class="btn btn-outline-success"
                        @click="email_valid_check()" disabled>
                        인증번호 입력
                      </button>
                    </div>

                    <div class="form-floating mb-3">
                      <input type="password" class="form-control" v-model="user_info.userPassword" id="password"
                        name="password" placeholder="123456" @change="password_validation()" />
                      <label for="password">비밀번호</label>
                    </div>

                    <div class="form-floating mb-3">
                      <input type="password" class="form-control" id="passwd-confirm" placeholder="1234567890"
                        @change="password_confirm()" />
                      <label for="passwd-confirm">비밀번호 재확인</label>
                    </div>

                    <small>
                      <p class="passwordCheck">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                          class="bi bi-check-lg" viewBox="0 0 16 16">
                          <path
                            d="M12.736 3.97a.733.733 0 0 1 1.047 0c.286.289.29.756.01 1.05L7.88 12.01a.733.733 0 0 1-1.065.02L3.217 8.384a.757.757 0 0 1 0-1.06.733.733 0 0 1 1.047 0l3.052 3.093 5.4-6.425a.247.247 0 0 1 .02-.022Z" />
                        </svg>
                        비밀번호를 확인해 주세요!
                      </p>
                    </small>

                    <small>
                      <p class="passwordCheck">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                          class="bi bi-check-lg" viewBox="0 0 16 16">
                          <path
                            d="M12.736 3.97a.733.733 0 0 1 1.047 0c.286.289.29.756.01 1.05L7.88 12.01a.733.733 0 0 1-1.065.02L3.217 8.384a.757.757 0 0 1 0-1.06.733.733 0 0 1 1.047 0l3.052 3.093 5.4-6.425a.247.247 0 0 1 .02-.022Z" />
                        </svg>
                        비밀번호가 일치하지 않습니다!
                      </p>
                    </small>

                    <small>
                      <p>
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                          class="bi bi-check-lg" viewBox="0 0 16 16">
                          <path d="M12.736 3.97a.733.733 0 0 1 1.047 0c.286.289.29.756.01 
                              1.05L7.88 12.01a.733.733 0 0 1-1.065.02L3.217 8.384a.757.757 
                              0 0 1 0-1.06.733.733 0 0 1 1.047 0l3.052 3.093 5.4-6.425a.247.247 0 0 1 .02-.022Z" />
                        </svg>
                        비밀번호는 영어, 숫자, 특수문자를 1개이상 사용.
                      </p>

                      <p>
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                          class="bi bi-check-lg" viewBox="0 0 16 16">
                          <path
                            d="M12.736 3.97a.733.733 0 0 1 1.047 0c.286.289.29.756.01 1.05L7.88 12.01a.733.733 0 0 1-1.065.02L3.217 8.384a.757.757 0 0 1 0-1.06.733.733 0 0 1 1.047 0l3.052 3.093 5.4-6.425a.247.247 0 0 1 .02-.022Z" />
                        </svg>
                        비밀번호는 최소 8자 ~ 최대 20자.
                      </p>
                    </small>
                    <hr class="my-4" />

                    <div class="form-floating mb-3">
                      <input type="text" class="form-control" id="phone" name="phone" placeholder="전화번호 입력" />
                      <label for="phone">전화번호</label>
                      <button id="phone_code" type="button" class="btn btn-outline-success" @click="phone_validation()">
                        인증번호 전송
                      </button>
                    </div>

                    <div class="form-floating mb-3">
                      <input type="text" class="form-control" id="phoneAuthentication" placeholder="전화번호 입력" />
                      <label for="phoneAuthentication">인증번호</label>
                      <button type="button" id="phoneNumberBtn" class="btn btn-outline-success"
                        @click="phone_valid_check()" disabled>
                        전화번호 인증
                      </button>
                    </div>

                    <div class="form-floating mb-3">
                      <input type="text" class="form-control" id="addressInput" placeholder="주소검색" name="compAddress"
                        v-model="user_info.userAddress" readonly @click="find_address()" />
                      <label for="addressInput">업체주소 (우편번호)</label>
                    </div>

                    <div class="form-floating mb-3">
                      <input type="text" class="form-control" id="addressDetail" name="addressDetail"
                        v-model="user_info.addressDetail" placeholder="상세주소" />
                      <label for="addressDetail">상세주소</label>
                      <button type="button" class="btn btn-outline-success" @click="find_address()">
                        주소검색
                      </button>
                    </div>

                    <br />
                    <hr class="my-4" />
                    <div class="sign-up-btn">
                      <button class="btn btn-lg btn-primary btn-login fw-bold text-uppercase" type="button"
                        @click="next()">
                        다음 페이지
                      </button>
                    </div>
                    <a class="d-block text-center mt-2 small" href="/login">이미 아이디가 있으신가요?</a>
                  </div>

                  <!-- 이 부분부터 2번째 입력 페이지 -->
                  <div id="second-form">
                    <div class="form-floating mb-3">
                      <input type="text" class="form-control" id="ceo_name" placeholder="ceo" name="ceoName"
                        v-model="user_info.ceoName" />
                      <label for="ceo_name">대표자 이름</label>
                    </div>

                    <div class="form-floating mb-3">
                      <input type="text" class="form-control" id="business_name" placeholder="company_name"
                        name="compName" v-model="user_info.compName" />
                      <label for="business_name">업체명</label>
                    </div>

                    <div class="">
                      <label for="business_explain"> 기업소개 </label>
                      <textarea class="form-control" id="business_explain" placeholder="기업소개를 작성해주세요!" rows="15"
                        name="compIntro" v-model="user_info.compIntro"></textarea>
                    </div>

                    <hr />
                    <div class="form-floating mb-3">
                      <input type="number" class="form-control" id="businessId" name="businessId"
                        placeholder="123456" />
                      <label for="businessNumber"> 사업자번호 </label>
                      <button type="button" class="btn btn-outline-success" @click="businessNumValid()">
                        유효성검사
                      </button>
                    </div>

                    <div class="form-floating mb-3" v-if="user_info.memberType == 0">
                      <input type="email" class="form-control" id="brokerNumber" name="brokerId" placeholder="123456" />
                      <button type="button" class="btn btn-outline-success" @click="brokerNumberValidation()">
                        유효성검사
                      </button>
                      <label for="brokerNumber">중개등록번호</label>
                    </div>

                    <div class="form-floating mb-3">
                      <input type="file" class="form-control" id="business_number_pic" name="images"
                        placeholder="number_pic" required />
                      <label for="business_number_pic"> 사업자등록증 </label>
                    </div>

                    <div class="form-floating mb-3" v-if="user_info.memberType == 0">

                      <input type="file" class="form-control" id="broker_number_pic" name="images"
                        placeholder="number_pic" required />
                      <label for="broker_number_pic"> 중개등록증 </label>
                    </div>

                    <div class="sign-up-btn">
                      <button class="btn btn-lg btn-primary btn-login fw-bold text-uppercase" type="button"
                        @click="sign()">
                        회원가입
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <input type="hidden" name="isSocial" value="0">
      <input type="hidden" name="profilePic" value="default.png">
      <input type="hidden" name="zipCode" v-model="user_info.zipCode">
      <input type="hidden" name="impUid" value="1234567890">
      <input type="hidden" name="businessState" value="0">
    </form>
  </div>
</template>

<script>
  import axios from 'axios';
  import loginFunc from '@/script/loginFunc';
  import swal from 'sweetalert2';
  import {
    originalPositionFor
  } from '@jridgewell/trace-mapping';

  export default {
    data() {
      return {
        // 회원가입 시, 필요한 정보
        user_info: {
          email: "",
          userPassword: "",
          userPhone: "",
          userAddress: "",
          addressDetail: "",
          zipCode: "",
          memberType: "",
          ceoName: "",
          compName: "",
          compIntro: "",
          businessId: 0,
          businessImg: "",
          brokerId: 0,
          brokerImg: "",
          profilePic: "default.png",
          impUid: "1234567890",
          businessState: 0
        },

        // valid 되었는지 확인하는 정보
        email_valid: false,
        pass_valid: false,
        pass_confirm: false,
        phone_valid: false,
        business_valid: false,
        broker_valid: false,

        // 인증코드가 담기는 Data
        emailCode: 12345672387,
        phoneCode: 12301684861,
      };
    },

    methods: {
      email_validation: async function () {
        var data = await loginFunc.email_validation();
        console.log("이메일 Validation 실행한 후 : ", data);
        if (data != null) {
          this.user_info.email = data.email;
          this.emailCode = data.email_code;
        }
      },

      email_valid_check: function () {
        this.email_valid = loginFunc.email_valid_check(this.emailCode);
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
        if (data != null) {
          this.user_info.userPhone = data.phone;
          this.phoneCode = data.phone_code;
        }
      },

      phone_valid_check: function () {
        console.log(this.phoneCode);
        this.phone_valid = loginFunc.phone_valid_check(this.phoneCode);

        console.log("==번호인증 완료 후==");
        console.log("user_phone :: ", this.user_info.userPhone);
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
            outside.user_info.userAddress = data.address;
            outside.user_info.zipCode = data.zonecode;
          },
        }).open();
      },

      next: function () {
        var selected = document.querySelector("input[type=radio][name=memberType]:checked");

        if (selected == null) {
          swal.fire({
            icon: "error",
            title: "업체유형을 선택해주세요!"
          });

        } else if (this.email_valid == false || this.phone_valid == false || this.pass_valid == false || this
          .user_info.userAddress == "" ||
          this.pass_confirm == false) {
          swal.fire({
            icon: "error",
            title: "모든 정보를 \n 입력 및 인증해주세요!"
          });
        } else {
          this.user_info.memberType = selected.value;
          document.querySelector("#first-form").style.display = "none";
          document.querySelector("#second-form").style.display = "block";
        }
        console.log(this.user_info);
      },

      businessNumValid: function () {
        var number = document.querySelector("#businessId").value;
        var reg = /^[0-9]{10}/;

        var result = reg.test(number);
        if (!result) {
          swal.fire({
            icon: "error",
            title: "사업자번호를 확인해주세요!",
            text: "-(하이픈)빼고 숫자만 10자리 입력해주세요!"
          });
          return false;
        }

        axios({
          url: "https://api.odcloud.kr/api/nts-businessman/v1/status",
          method: "POST",
          params: {
            "serviceKey": "QZf4Ip/iukGVGGNo2Yp1ei7ISnJ2sOwUgmmBjLQt6mCw73ftY5N0jt6ck1Qz34mGkNv4FQAysldaby08pQpYEg=="
          },
          data: {
            "b_no": [number]
          }
        }).then(res => {
          console.log(res.data.match_cnt);
          if (res.data.match_cnt != null) {
            this.user_info.businessId = number;
            this.business_valid = true;
            swal.fire({
              icon: "success",
              title: "인증성공!"
            })

          } else {
            swal.fire({
              icon: "error",
              title: "사업자번호를 확인해주세요!"
            });
          }

        }).catch(error => {
          console.log(error);
        })
      },


      brokerNumberValidation: function () {
        var outside = this;
        var number = document.querySelector("#brokerNumber").value;
        var reg = /^[0-9]{14}/;

        var result = reg.test(number);
        if (!result) {
          swal.fire({
            icon: "error",
            title: "중개등록번호를 확인해주세요!",
            text: "-(하이픈)빼고 숫자만 14자리 입력해주세요!"
          });
          return false;
        }

        this.$axios({
          url: "http://apis.data.go.kr/5690000/sjReatEstate/sj_00000460",

          params: {
            serviceKey: "QZf4Ip/iukGVGGNo2Yp1ei7ISnJ2sOwUgmmBjLQt6mCw73ftY5N0jt6ck1Qz34mGkNv4FQAysldaby08pQpYEg==",
            pageIndex: 1,
            pageUnit: 1,
            dataType: "json",
            searchCondition: "reg_no",
            searchKeyword: number
          }
        }).then(res => {
          if (res.data.body.items.length == 0) {
            swal.fire({
              icon: "error",
              title: "중개등록번호를 확인해주세요!",
              text: "등록되지 않은 중개등록번호입니다!"
            });

          } else {
            swal.fire({
              icon: "success",
              title: "인증이 완료되었습니다!"
            });

            outside.broker_valid = true;
          }

        }).catch(error => {
          console.log(error);
        })
      },

      sign: function () {
        var bfile = document.getElementById("business_number_pic").value;
        if (!bfile) {
          swal.fire({
            icon: "error",
            title: "사업자 등록증을 등록해주세요!"
          });

          return false;
        }

        if (this.user_info.memberType == 0) {
          var broker_file = document.getElementById("broker_number_pic").value;
          if (!broker_file) {
            swal.fire({
              icon: "error",
              title: "중개사업자 등록증을 등록해주세요!"
            });

            return false;
          }

          var brokerNumber = document.getElementById("brokerNumber").value;
          if (!brokerNumber) {
            this.brokerNumberValidation();
            return false;
          }
        }

        var formData = new FormData(document.querySelector('#businessForm'));
        if (this.business_valid == true && this.user_info.ceoName != "" &&
          this.user_info.compName != "" && this.user_info.compIntro != "") {
          axios({
              url: "/member/bSignUp",
              method: "POST",
              data: formData
            })
            .then((res) => {
              console.log(res);
            })
            .catch((error) => {
              console.log(error);
            });
        } else {
          swal.fire({
            icon: "error",
            title: "모든 정보를 \n 입력 및 인증해주세요!"
          });
        }

      },
    },
  };
</script>

<style scoped>
  a {
    color: #64c481;
    text-decoration: none;
  }

  a:visited {
    color: #64c481;
  }

  a:hover {
    color: #64c481;
  }

  a:active {
    color: #64c481;
  }

  .general-signup-body {
    background: white;
  }

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
  #businessId,
  #brokerNumber {
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

  .business-label input[type="radio"]+span {
    display: inline-block;
    padding: 15px 10px;
    background-color: #ffffff;
    text-align: center;
    cursor: pointer;
    width: 100%;
  }

  .business-label input[type="radio"]:checked+span {
    background-color: #b3e3c3;
    color: black;
  }

  .passwordCheck {
    color: red;
    display: none;
  }
</style>