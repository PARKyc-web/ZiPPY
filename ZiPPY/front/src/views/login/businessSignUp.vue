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
                    <h3>업체 유형</h3>
                    <label class="business-label">
                      <input
                        type="radio"
                        name="business-type"
                        value="property"
                      />
                      <span>공인중개사</span>
                    </label>

                    <label class="business-label">
                      <input type="radio" name="business-type" value="shop" />
                      <span>쇼핑몰</span>
                    </label>

                    <label class="business-label">
                      <input type="radio" name="business-type" value="move" />
                      <span>이사업체</span>
                    </label>

                    <label class="business-label">
                      <input type="radio" name="business-type" value="clean" />
                      <span>청소업체</span>
                    </label>
                  </div>

                  <div class="form-floating mb-3">
                    <input
                      v-model="user_email"
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
                      class="btn btn-outline-success"
                      @click="email_valid_check()"
                    >
                      인증번호 입력
                    </button>
                  </div>

                  <div class="form-floating mb-3">
                    <input
                      type="password"
                      class="form-control"
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

                  <small v-if="pass_valid == true">
                    <p id="passwordCheck">
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

                  <small v-if="pass_confirm == true">
                    <p id="passwordCheck">
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
                      class="btn btn-outline-success"
                      @click="phone_valid_check()"
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
                    <label for="addressInput">주소 (우편번호)</label>
                  </div>

                  <div class="form-floating mb-3">
                    <input
                      type="text"
                      class="form-control"
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
                    />
                    <label for="ceo_name">대표자 이름</label>
                  </div>

                  <div class="form-floating mb-3">
                    <input
                      type="text"
                      class="form-control"
                      id="business_name"
                      placeholder="company_name"
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
                    <button type="button" class="btn btn-outline-success">
                      사업자번호
                    </button>
                  </div>

                  <div class="form-floating mb-3">
                    <input
                      type="email"
                      class="form-control"
                      id="broker_number"
                      placeholder="123456"
                    />
                    <button type="button" class="btn btn-outline-success">
                      중개등록번호 검사
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

                  <div class="form-floating mb-3">
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
                      @click="sign()"
                    >
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
export default {
  data() {
    return {
      user_email: "",
      business_type: "",
      pass_valid: false,
      pass_confirm: false,
    };
  },

  methods: {
    email_validation: function () {
      console.log(this.user_email);
      axios
        .get("zippy/validation/email", {
          params: {
            email: this.user_email,
          },
        })
        .then((res) => {
          console.log(res);
          this.emailCode = res.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },

    email_valid_check: function () {
      console.log("email-valid-check");

      var code = document.querySelector("#emailAuthentication");
      console.log(code.value);

      if (this.emailCode == code.value) {
        console.log("이메일인증 성공!");
      }

      var email = document.querySelector("#inputEmail");
      var valid = document.querySelector("#emailAuthentication");

      email.disabled = true;
      valid.disabled = true;

      this.email_valid = true;
    },

    password_validation: function () {
      console.log("passwd-valid");
      var reg =
        /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-+]).{8,20}$/;
      var password = document.querySelector("#password").value;

      console.log(reg.test(password));
      this.pass_valid = !reg.test(password);
    },

    password_confirm: function () {
      var password = document.querySelector("#password").value;
      var confirm = document.querySelector("#passwd-confirm").value;

      if (password == confirm) {
        this.pass_confirm = false;
      } else {
        this.pass_confirm = true;
      }
    },

    phone_validation: function () {
      console.log("phone-validation");
    },

    phone_valid_check: function () {
      console.log("phone-valid-check");
      var input_phone = document.querySelector("#phone");
      var valid = document.querySelector("#phoneAuthentication");

      input_phone.disabled = true;
      valid.disabled = true;

      this.phone_valid = true;
    },

    find_address: function () {
      new daum.Postcode({
        oncomplete: function (data) {
          console.log(data);
          document.querySelector("#addressInput").value = data.address;
        },
      }).open();
    },

    next: function () {
      console.log("next-run");
      document.querySelector("#first-form").style.display = "none";
      document.querySelector("#second-form").style.display = "block";

      var selected = document.querySelector(
        "input[type=radio][name=business-type]:checked"
      );
      this.business_type = selected.value;
      console.log(this.business_type);
    },

    sign: function () {
      console.log("sign-up RUN");
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
  width: 40%;
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
  width: 22%;
  min-width: 118px;
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
</style>
