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
                <img src="" width="150px" />
              </h5>
              <hr class="my-4" />
              <test-bar></test-bar>
              <form id="signup-form" action="">
                <div class="form-floating mb-3">
                  <input
                    type="email"
                    class="form-control"
                    id="gs-inputEmail"
                    name="user_email"
                    placeholder="name@example.com"
                    required
                    autofocus
                  />
                  <label for="gs-inputEmail">이메일(아이디)</label>
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
                    id="gs-emailAuthentication"
                    placeholder="123456"
                    required
                    autofocus
                  />
                  <label for="gs-emailAuthentication">인증번호(6자리)</label>
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
                    id="gs-password"
                    name="user_passwd"
                    placeholder="123456"
                    @change="password_validation()"
                  />
                  <label for="gs-password">비밀번호</label>
                </div>

                <div class="form-floating mb-3">
                  <input
                    type="password"
                    class="form-control"
                    id="gs-passwd-confirm"
                    placeholder="1234567890"
                    @change="password_confirm()"
                  />
                  <label for="gs-passwd-confirm">비밀번호 재확인</label>
                </div>

                <small v-if="pass_valid == true"
                  ><p id="passwordCheck">
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
                  </p></small
                >

                <small v-if="pass_confirm == true"
                  ><p id="passwordCheck">
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
                  </p></small
                >

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
                        d="M12.736 3.97a.733.733 0 0 1 1.047 0c.286.289.29.756.01 1.05L7.88 12.01a.733.733 0 0 1-1.065.02L3.217 8.384a.757.757 0 0 1 0-1.06.733.733 0 0 1 1.047 0l3.052 3.093 5.4-6.425a.247.247 0 0 1 .02-.022Z"
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
                  </p></small
                >
                <hr class="my-4" />

                <div class="form-floating mb-3">
                  <input
                    type="text"
                    class="form-control"
                    id="gs-phone"
                    placeholder="전화번호 입력"
                  />
                  <label for="gs-phone">전화번호</label>
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
                    id="gs-phoneAuthentication"
                    placeholder="전화번호 입력"
                  />
                  <label for="gs-phoneAuthentication">인증번호</label>
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
                <div class="sign-up-btn">
                  <button
                    class="btn btn-lg btn-primary btn-login fw-bold text-uppercase"
                    type="button"
                    @click="signup()"
                  >
                    회원가입
                  </button>
                </div>
                <a class="d-block text-center mt-2 small" href="#"
                  >이미 아이디가 있으신가요?</a
                >

                <hr class="my-4" />
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import bar from "../components/testbar.vue";

export default {
  components: {
    testBar: bar,
  },
  data() {
    return {
      pass_valid: false,
      pass_confirm: false,
      email_valid: false,
      phone_valid: false,
    };
  },

  methods: {
    email_validation: function () {
      /* 
          Ajax로 REST CONTROLLER에 정보 보내기 AND 유효코드 6자리 받기          
          Email 보내기
        */
      console.log("email-validation");
    },

    email_valid_check: function () {
      /*
          email_validation에서 받은 유효코드 6자리, 
        */
      console.log("email-valid-check");

      var email = document.querySelector("#gs-inputEmail");
      var valid = document.querySelector("#gs-emailAuthentication");

      email.disabled = true;
      valid.disabled = true;

      this.email_valid = true;
    },

    password_validation: function () {
      console.log("passwd-valid");
      var reg =
        /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-+]).{8,20}$/;
      var password = document.querySelector("#gs-password").value;

      console.log(reg.test(password));

      this.pass_valid = !reg.test(password);
    },

    password_confirm: function () {
      var password = document.querySelector("#gs-password").value;
      var confirm = document.querySelector("#gs-passwd-confirm").value;
      console.log(password);
      console.log(confirm);

      if (password == confirm) {
        console.log(true);
      } else {
        console.log(false);
      }
    },

    phone_validation: function () {
      console.log("phone-validation");
    },

    phone_valid_check: function () {
      console.log("phone-valid-check");
      var input_phone = document.querySelector("#gs-phone");
      var valid = document.querySelector("#gs-phoneAuthentication");

      input_phone.disabled = true;
      valid.disabled = true;

      this.phone_valid = true;
    },

    find_address: function () {
      // new daum.Postcode({
      //   oncomplete: function (data) {
      //     console.log(data);
      //     alert(data.zonecode);
      //     document.querySelector("#addressInput").value = data.address;
      //   },
      // }).open();
    },

    signup: function () {
      var form = document.querySelector("#signup-form");
      if (this.email_valid == true && this.phone_valid == true) {
        form.submit();
      }
    },
  },
};
</script>
