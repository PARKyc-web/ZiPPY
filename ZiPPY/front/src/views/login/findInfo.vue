<template>     
<div id="container">             
     <v-container grid-list-xl text-xs-center>
       <v-layout row wrap>         
           <v-card color="dark" elevation-24 style="padding: 20px; border: 1px; border-radius: 30px;">
             <v-card-text>
              <div class="login_img">
                <img src="@/assets/zippy_logo.png" width="150px" />
              </div>
                <br>
                <hr>
                <div id="typeBtn">
                <b-form-radio-group                  
                  id="btn-radios-2"
                  v-model="selected"
                  :options="options"          
                  button-variant="outline-primary"
                  size="lg"
                  name="radio-btn-outline"              
                  buttons                  
                ></b-form-radio-group>                                     
                </div>
               
               <br>
               <v-form v-if="selected == 'radio1'">
                <v-text-field 
                   clearable                   
                   label="사용자 이름(CEO 이름)"
                   prepend-icon="person"
                   type="text"                   
                   color="#b3e3c3"
                   id="userName" 
                 >
                 </v-text-field>

                 <v-text-field
                   id="find_phone"
                   clearable                   
                   label="핸드폰 번호"
                   prepend-icon="phone_iphone"
                   type="number"                   
                   color="#b3e3c3"
                 >
                 </v-text-field>
                 <div id="login_btn">
                  <v-btn @click="sendValidationNum(1)" color="#b3e3c3" large>인증번호 받기</v-btn>
                  </div>
                  <br>
                 <v-text-field
                   id="phoneValid"
                   clearable                   
                   label="인증번호"
                   prepend-icon="lock"
                   type="number"                   
                   color="#b3e3c3"                                                
                 >                 
                 </v-text-field>
              </v-form>

              <v-form v-if="selected == 'radio2'">
                <v-text-field 
                   clearable  
                   id="email"                 
                   label="이메일"
                   prepend-icon="email"
                   type="email"
                   name="userEmail"
                   color="#b3e3c3"                                                         
                 >
                 </v-text-field>       
              </v-form>
              <br>

              
              <hr>    
              <div id="login_btn" v-if="selected == 'radio1'">
               <v-btn @click="checkValid()" color="#b3e3c3" large>아이디 찾기</v-btn>
              </div>

              <div id="login_btn" v-if="selected == 'radio2'">
               <v-btn type="button" @click="sendValidationNum(0)" color="#b3e3c3" large>비밀번호 찾기</v-btn>
              </div>
             </v-card-text>
             
           </v-card>                    
       </v-layout>
     </v-container>  
</div>
</template>
  
<script>
import swal from 'sweetalert2';


    export default {      
      data() {
        return {
          selected: 'radio1',
          options: [
            { text: '아이디 찾기', value: 'radio1' },
            { text: '비밀번호 찾기', value: 'radio2' }
          ],

          typeSelected : 'general',        
          memberType: [
            { text: '일반회원', value: 'general' },
            { text: '기업회원', value: 'business' }
          ],

          phoneValidNum : 123123123,          
        }
      },

      methods:{
        enter : function(){
          
        },

        sendValidationNum : function(type){          
          var emailReg = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
          var phoneReg = /^010[0-9]{8}$/;
          var result = false;

          // type =0 >> email,   type =1 >> phone
          if(type == 0){
            var email = document.querySelector("#email");
            result = emailReg.test(email.value);

            if(result){
              this.$axios({
                url : "validation/password",
                params : {
                  email : email.value
                }
              }).then(res =>{     
                console.log(res);
                if(res.data == false){
                  swal.fire({
                    icon:"error",
                    title : "등록되지 않은 이메일입니다."                    
                  });
                } else {
                  swal.fire({
                    icon:"success",
                    title : "임시 비밀번호 발행",
                    text : "등록된 이메일로 임시 비밀번호를 전송하였습니다."
                  });
                  console.log()

                  this.$router.push("/login");
                }

              }).catch(error =>{
                console.log(error);
              })
            }else {
              swal.fire({
                icon:"error",
                title : "이메일양식을 확인해주세요!"                
              });
            }

            // 아이디 찾기할때 휴대폰 전화 인증하는 메소드
          }else if(type == 1){
            var phone = document.querySelector("#find_phone");                 
            result = phoneReg.test(phone.value);            

            if(result){
              this.$axios({
                url : "validation/phone",
                params : {                  
                  phone : phone.value
                }
              }).then(res =>{
                swal.fire({
                  icon: "success",
                  title:"인증번호를 전송하였습니다!"
                })                
                console.log(res);
                this.phoneValidNum = res.data;
              }).catch(error => {
                console.log(error);
              })
            }else{
              swal.fire({
                icon : "error",
                title : "휴대폰 번호를 확인해주세요!",
                text : "-(하이픈)을 제외한 11자리 숫자를 입력해주세요"
              });
            }
          }          
        },

        // 인증버
        checkValid: function(){
          var userInput = document.querySelector("#phoneValid");
          var userName = document.querySelector("#userName");
          var phone = document.querySelector("#find_phone");

          if(this.phoneValidNum == userInput.value){            
            this.$axios({
              url : "/validation/findEmail",
              params:{
                userName : userName.value,
                phoneNumber : phone.value
              }
            }).then(res => {
              console.log(res);
              if(res.data == null || res.data == ""){
                swal.fire({
                  icon:"info",
                  title: "조건에 맞는 아이디가 없습니다",
                  text: "이름과 핸드폰번호를 확인해주세요"
                })
              }else {
                swal.fire("이메일 : "+ res.data);
              }
            }).catch(error =>{
              console.log(error);
            })

          }else {
            swal.fire({
              icon: "error",
              title: "인증에 실패했습니다",
              text : "인증번호를 다시 확인해주세요"
            })
          }

        }
      }
    }
</script>

<style scoped>
#container {
  width: 35%;
  margin: 7% auto;
}

.login_img{
  text-align: center;
}

#login_btn{
  text-align: right;
}

.login_img button{
  margin : 10px;
}

.display-2 {
  color : #b3e3c3;
}

ul {
  list-style:none;
  text-align: right;
  font-size: large;
  font-weight: bold;
}

ul li {
  display: inline-block;
  margin: 0 5px 0 5px;
}

ul li:hover {
  color : #b3e3c3;
}

#typeBtn {
  text-align: center;
}
</style>

