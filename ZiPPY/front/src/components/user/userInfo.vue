<template>
  <div id="user-container" v-if="info != null">
    <div class="mt--6 container-fluid">
      <div class="row">
        <div class="order-xl-1 col-xl-8">

          <!-- <div class="card"> -->

            <div class="card-header">
              <div class="row align-items-center">
                <div class="col-8">
                  <h3 class="mb-0">회원정보</h3>
                </div>
              </div>
            </div>

            <div class="card-body">
              <form id="user_info">
                <h6 class="heading-small text-muted mb-4">프로필</h6>
                <div class="pl-lg-4">
                  <div class="row">

                    <div class="img-form">
                      <img src="/zippy/common/img/member/test.jpg" id="profile_img">                                            
                      <v-btn
                        color="primary"
                        class="form-control"
                        dark
                        @click="dialog2 = true"
                      >
                        프로필사진 변경
                      </v-btn>                        
                      <v-dialog
                        v-model="dialog2"
                        max-width="500px">
                        <v-card>
                          <v-card-title>
                            프로필사진 변경
                          </v-card-title>
                          <input type="file" name="image" class="form-control" id="temp_img">
                          <v-card-actions>
                            <v-btn
                              color="primary"
                              text
                              @click="dialog2 = false">
                              취소
                            </v-btn>
                            <v-btn
                              color="primary"
                              text
                              @click="profile_save()">
                              적용
                            </v-btn>
                          </v-card-actions>
                        </v-card>
                      </v-dialog>
                    </div>

                    <div class="div-inline-block">
                      <label>이메일(아이디)</label>
                      <input class="form-control" :value="info.email" name="email" readonly>

                      <br>
                      <label>현재 비밀번호</label>
                      <input type="password" class="form-control" id="password" ><br>
                      <label>새로운 비밀번호</label>
                      <input type="password" class="form-control" id="newPassword" @change="validation()">
                      <button type="button" @click="changePassword()" class="form-control">비밀번호 변경</button>
                      <div v-if="!passValid" style="color:red">
                        <ul>
                          <li><small>비밀번호는 8 ~ 20자까지 입니다</small></li>
                          <li><small>영어 대/소문자, 숫자, 특수문자 1개씩 사용되어야 합니다.</small></li>                     
                        </ul>                        
                      </div>
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
                              <input type="text" name="userName" placeholder="회원이름" class="form-control" valid="true" v-model="info.userName">
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
                              <input type="email" name="nickName" placeholder="닉네임" class="form-control" valid="true" v-model="info.nickName">
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
                              <input type="date" name="userBirth" placeholder="생년월일" class="form-control" valid="true" v-model="date">
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
                              <select class="form-control" name="userGender" v-model="info.userGender">
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
                              <input @click="findAddress()" type="text" placeholder="배송지 주소" 
                                    class="form-control" valid="true" v-model="inputAddress" readonly>
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
                              <input type="text" name="addressDetail" placeholder="상세주소" class="form-control" valid="true" v-model="info.addressDetail">
                            </div>
                          </div>
                        </fieldset>
                      </span></div>
                  </div>
                </div>
                <hr class="my-4">
                <div id="submit-btn">
                  <button type="button" class="form-control" @click="updateInfo()">정보수정</button>
                </div>
              </form>
            </div>
          <!-- </div> -->

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
        temp_img : "",
        dialog2 : false,
        info: null,
        passValid : true,
        inputAddress : "",
        date : ""
      }
    },

    created: async function () {
      var temp = await this.$axios({
        url: "/zippy/member/mypage",
        params: {
          email: this.$store.state.loginInfo.email,
          memberType: this.$store.state.memberType
        }
      });
      this.info = temp.data;
      console.log(this.info);
      this.inputAddress = "(" + this.info.zipCode + ") " + this.info.userAddress;
      this.date = this.info.userBirth.substring(0, 10);
    },

    methods: {
      changePassword: async function () {
        var password = document.querySelector("#password");
        var newPassword = document.querySelector("#newPassword");
        
        if(this.passValid && password.value != ""){
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
              timer: 2000
            })
          }else{
            swal.fire({
              position: 'top-end',
              icon: 'error',
              title: '비밀번호 변경에 실패하였습니다',
              showConfirmButton: false,
              timer: 2000
            })
          }
        }else{
          swal.fire({
              position: 'top-end',
              icon: 'error',
              title: '비밀번호 규칙을 확인해주세요',
              showConfirmButton: false,
              timer: 2000
            })
        }  
      },

      validation : function(){
        var reg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-+]).{8,20}$/;
        var password = document.querySelector("#newPassword").value;
        this.passValid = reg.test(password);
      },

      findAddress: function () {      
      console.log(this.info.userBirth.substring(0, 10));
      var outside = this;
      new daum.Postcode({
        oncomplete: function (data) {
          console.log(data);          
          outside.info.userAddress = data.address;
          outside.info.zipCode = data.zonecode;
          outside.inputAddress = "(" + data.zonecode + ") " + data.address;
        },
      }).open();     
    },

    updateInfo : function(){      
      var formData = new FormData(document.querySelector('#user_info'));
      console.log("여기?1");
      formData.append("zipCode", this.info.zipCode);
      formData.append("userAddress", this.info.userAddress);
      console.log("여기?2");

      this.$axios({
        url : "/zippy/member/userInfo",
        method:"POST",
        data : formData        
      }).then(res =>{
        this.$store.commit('login', res.data);
        console.log(res);
        console.log(res.data);
        swal.fire({
              position: 'top-end',
              icon: 'success',
              title: '회원정보가 수정되었습니다',
              showConfirmButton: false,
              timer: 2000
            })
          
      }).catch((error) => {
        swal.fire({
              position: 'top-end',
              icon: 'error',
              title: '수정에 실패했습니다',
              showConfirmButton: false,
              timer: 2000
            })
      });

    },

    profile_save : function(){
      this.dialog2 = false;
      var temp = document.getElementById("temp_img");
      console.log(temp);            

      var formData = new FormData(document.querySelector('#user_info'));
      formData.append("image", temp.files[0]);
      this.$axios({
        url : "/zippy/member/image",
        method : "POST",
        data : formData
      }).then(res => {
        console.log(res.data);
        this.info.profileImage = res.data;
        this.$store.commit('login', this.info);
      })
    }


    }
  }
</script>

<style scoped>
  #user-container {
    margin: 0 auto;
    overflow: auto;
    margin-left: 35%;
    overflow: hidden;
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

  img{
    width: 275px;
    height: 80%;
    /* object-fit: cover; */
    /* object-fit: fill; */
  }

</style>