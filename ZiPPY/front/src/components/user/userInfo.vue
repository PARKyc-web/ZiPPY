<template>
  <div id="user-container" v-if="info != null">
    <div class="mt--6 container-fluid">
      <div class="row">
        <div class="order-xl-1 col-xl-8">

          <div class="card">

            <div class="card-header">
              <div class="row align-items-center">
                <div class="col-8">
                  <h3 class="mb-0">회원정보</h3>
                </div>
              </div>
            </div>

            <div class="card-body">
              <form class="">
                <h6 class="heading-small text-muted mb-4">프로필</h6>
                <div class="pl-lg-4">
                  <div class="row">

                    <div class="img-form">
                      <img src="http://localhost:8090/zippy/common/img/member/image2.jpg">
                      <input type="button" value="프로필사진 변경" class="form-control">
                    </div>

                    <div class="div-inline-block">
                      <label>이메일(아이디)</label>
                      <input class="form-control" :value="info.email" readonly>

                      <br>
                      <label>현재 비밀번호</label>
                      <input type="password" class="form-control" id="password"><br>
                      <label>새로운 비밀번호</label>
                      <input type="password" class="form-control" id="newPassword">
                      <button type="button" @click="changePassword()" class="form-control">비밀번호 변경</button>
                    </div>

                  </div>
                </div>

                <hr class="my-4">
                <h6 class="heading-small text-muted mb-4">회원정보</h6>
                <div class="pl-lg-4">
                  <div class="row">
                    <div class="col-lg-6"><span placeholder="Username">
                        <fieldset class="form-group" id="__BVID__745">
                          <!---->
                          <div tabindex="-1" role="group" class="bv-no-focus-ring"><label class="form-control-label">
                              회원이름
                            </label>
                            <div class="has-label">
                              <input type="text" placeholder="회원이름" class="form-control" valid="true">
                            </div>
                          </div>
                        </fieldset>
                      </span></div>

                    <div class="col-lg-6"><span placeholder="mike@email.com">
                        <fieldset class="form-group" id="__BVID__748">
                          <!---->
                          <div tabindex="-1" role="group" class="bv-no-focus-ring"><label class="form-control-label">
                              닉네임
                            </label>
                            <div class="has-label">
                              <input type="email" placeholder="닉네임" class="form-control" valid="true">

                            </div>
                            <!---->
                          </div>
                        </fieldset>
                      </span></div>
                  </div>
                  <div class="row">
                    <div class="col-lg-6"><span placeholder="First Name">
                        <fieldset class="form-group" id="__BVID__751">
                          <!---->
                          <div tabindex="-1" role="group" class="bv-no-focus-ring"><label class="form-control-label">
                              생년월일
                            </label>
                            <div class="has-label">
                              <input type="date" placeholder="생년월일" class="form-control" valid="true">
                            </div>
                            <!---->
                          </div>
                        </fieldset>
                      </span></div>
                    <div class="col-lg-6"><span placeholder="Last Name">
                        <fieldset class="form-group" id="__BVID__754">
                          <!---->
                          <div tabindex="-1" role="group" class="bv-no-focus-ring"><label class="form-control-label">
                              성별
                            </label>
                            <div class="has-label">
                              <select class="form-control">
                                <option value="M">남자</option>
                                <option value="F">여자</option>
                              </select>
                            </div>

                          </div>
                        </fieldset>
                      </span></div>
                  </div>
                </div>
                <hr class="my-4">
                <h6 class="heading-small text-muted mb-4"><strong>배송지 정보</strong></h6>
                <div class="pl-lg-4">
                  <div class="row">
                    <div class="col-md-12"><span placeholder="Home Address">
                        <fieldset class="form-group" id="__BVID__757">
                          <!---->
                          <div tabindex="-1" role="group" class="bv-no-focus-ring"><label class="form-control-label">
                              주소
                            </label>
                            <div class="has-label">
                              <input type="text" placeholder="배송지 주소" class="form-control" valid="true">
                            </div>

                          </div>
                        </fieldset>
                      </span></div>
                  </div>
                  <div class="row">
                    <div class="col-md-12"><span placeholder="City">
                        <fieldset class="form-group" id="__BVID__760">
                          <!---->
                          <div tabindex="-1" role="group" class="bv-no-focus-ring"><label class="form-control-label">
                              상세주소
                            </label>
                            <div class="has-label">
                              <input type="text" placeholder="상세주소" class="form-control" valid="true">
                            </div>
                          </div>
                        </fieldset>
                      </span></div>
                  </div>
                </div>
                <hr class="my-4">
                <div id="submit-btn">
                  <button type="button" class="form-control">정보수정</button>
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
  import swal from 'sweetalert2';

  export default {
    data() {
      return {
        info: null
      }
    },

    created: async function () {
      var temp = await this.$axios({
        url: "/member/mypage",
        params: {
          email: this.$store.state.loginInfo.email,
          memberType: this.$store.state.memberType
        }
      });
      this.info = temp.data;
      console.log(this.info);
    },

    methods: {
      changePassword: async function () {
        var password = document.querySelector("#password");
        var newPassword = document.querySelector("#newPassword");

        var temp = await this.$axios({
          url: "/member/password",
          method: "PUT",
          data: {
            email: this.$store.state.loginInfo.email,
            password: password.value,
            newPassword: newPassword.value
          }
        })

        console.log(temp);
        if (temp.data) {
          swal.fire({
            position: 'top-end',
            icon: 'success',
            title: '비밀번호가 변경되었습니다',
            showConfirmButton: false,
            timer: 2500
          })
        }
      }
    }

  }
</script>

<style scoped>
  #user-container {
    margin: 0 auto;
    overflow: auto;
    margin-left: 35%;
  }

  img {
    width: 100%;
    /* border-radius: 50%; */
  }

  .img-form {
    width: 300px;
    height: 300px;
    display: inline-block;
  }

  .div-inline-block {
    width: 50%;
    display: inline-block;
  }

  #submit-btn {
    margin-left: 85%;
    text-align: center;

    width: 15%;
  }
</style>