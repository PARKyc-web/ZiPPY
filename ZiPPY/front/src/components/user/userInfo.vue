<template>
  <div id="user-container">
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
                      <img src="http://localhost:8090/zippy/common/img/image1.jpg">
                      <input type="button" value="프로필사진 변경" class="form-control">
                    </div>

                    <div class="div-inline-block">
                      <label>이메일(아이디)</label>
                      <input class="form-control" :value="info.email" readonly>

                      <br><br>
                      <label>비밀번호</label>
                      <input class="form-control" :value="info.email" readonly>

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
                              <!----><input type="text" placeholder="Username" class="form-control" valid="true">
                            </div>
                            <!---->
                          </div>
                        </fieldset>
                      </span></div>

                    <div class="col-lg-6"><span placeholder="mike@email.com">
                        <fieldset class="form-group" id="__BVID__748">
                          <!---->
                          <div tabindex="-1" role="group" class="bv-no-focus-ring"><label class="form-control-label">
                              Email address
                            </label>
                            <div class="has-label">
                              <!----><input type="email" placeholder="mike@email.com" class="form-control" valid="true">
                              <!---->
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
                              First Name
                            </label>
                            <div class="has-label">
                              <!----><input type="text" placeholder="First Name" class="form-control" valid="true">
                              <!---->
                            </div>
                            <!---->
                          </div>
                        </fieldset>
                      </span></div>
                    <div class="col-lg-6"><span placeholder="Last Name">
                        <fieldset class="form-group" id="__BVID__754">
                          <!---->
                          <div tabindex="-1" role="group" class="bv-no-focus-ring"><label class="form-control-label">
                              Last Name
                            </label>
                            <div class="has-label">
                              <!----><input type="text" placeholder="Last Name" class="form-control" valid="true">
                            </div>
                            <!---->
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
                              Address
                            </label>
                            <div class="has-label">
                              <!----><input type="text" placeholder="Home Address" class="form-control" valid="true">
                              <!---->
                            </div>
                            <!---->
                          </div>
                        </fieldset>
                      </span></div>
                  </div>
                  <div class="row">
                    <div class="col-lg-4"><span placeholder="City">
                        <fieldset class="form-group" id="__BVID__760">
                          <!---->
                          <div tabindex="-1" role="group" class="bv-no-focus-ring"><label class="form-control-label">
                              City
                            </label>
                            <div class="has-label">
                              <!----><input type="text" placeholder="City" class="form-control" valid="true">
                            </div>
                            <!---->
                          </div>
                        </fieldset>
                      </span></div>
                    <div class="col-lg-4"><span placeholder="Country">
                        <fieldset class="form-group" id="__BVID__763">
                          <!---->
                          <div tabindex="-1" role="group" class="bv-no-focus-ring"><label class="form-control-label">
                              Country
                            </label>
                            <div class="has-label">
                              <!----><input type="text" placeholder="Country" class="form-control" valid="true">
                            </div>
                            <!---->
                          </div>
                        </fieldset>
                      </span></div>
                    <div class="col-lg-4"><span placeholder="ZIP Code">
                        <fieldset class="form-group" id="__BVID__766">
                          <!---->
                          <div tabindex="-1" role="group" class="bv-no-focus-ring"><label class="form-control-label">
                              Postal Code
                            </label>
                            <div class="has-label">
                              <!----><input type="text" placeholder="ZIP Code" class="form-control" valid="true">
                            </div>
                            <!---->
                          </div>
                        </fieldset>
                      </span></div>
                  </div>
                </div>
                <hr class="my-4">
                <h6 class="heading-small text-muted mb-4">About me</h6>
                <div class="pl-lg-4">
                  <div role="group" class="form-group mb-0" id="__BVID__767"><label for="about-form-textaria"
                      class="d-block form-control-label" id="__BVID__767__BV_label_">About Me</label>
                    <div class="bv-no-focus-ring"><textarea id="about-form-textaria"
                        placeholder="A few words about you ..." rows="4" wrap="soft" class="form-control"></textarea>
                      <!---->
                    </div>
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

    }

  }
</script>

<style scoped>
  #user-container {
    margin: 0 auto;
    overflow: auto;
  }

  img {
    width: 100%;    
    /* border-radius: 50%; */
  }

  .img-form{
    width: 300px;
    height: 300px;
    display: inline-block;
  }

  .div-inline-block{
    width: 50%;
    display: inline-block;
  }
</style>