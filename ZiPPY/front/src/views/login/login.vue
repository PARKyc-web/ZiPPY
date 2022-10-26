<template>
<div id="container">
     <v-container grid-list-xl text-xs-center>
       <v-layout row wrap>         
           <v-card color="dark" elevation-24 style="padding: 20px; border: 1px; border-radius: 30px;">
             <v-card-text>
              <div class="login_img">
                <img src="../../assets/zippy_logo2.png" width="150px" />
              </div>
                <br>
                <!--<h5 color="#B3E3C3 !important" class="font-weight-bold">로그인</h5>-->
               <hr>
               <br>
               <v-form>
                 <v-text-field 
                   clearable
                   v-model="email"
                   label="이메일"
                   prepend-icon="face"
                   type="email"                   
                   color="#b3e3c3"          
                   @keyup.enter="enter()"             
                 >
                 </v-text-field>
                 <v-text-field
                   clearable
                   v-model="password"
                   label="비밀번호"
                   prepend-icon="lock"
                   type="password"                   
                   color="#b3e3c3"                   
                   counter="20"
                   @keyup.enter="enter()"
                 >
                 </v-text-field>
              </v-form>
              <br>
              <div id="login_btn">
               <v-btn depressed @click="login()" width="386" color="#b3e3c3" large style="font-weight:bold">로그인</v-btn>
              </div>
             </v-card-text>
             <hr>             
             <ul>
              <li><button @click="signIn()" style="font-size:small">회원가입</button></li>
              <li><button @click="findInfo()" style="font-size:small">아이디 찾기</button></li>
              <li><button @click="findInfo()" style="font-size:small">비밀번호 찾기</button></li>
             </ul>

           </v-card>                    
       </v-layout>
     </v-container>  
</div>
</template>

<script>
import axios from 'axios';
import swal from 'sweetalert2';

export default{  
  data() {
    return {
      email: '',
      password: '',
    }
  },
  methods: {    
    login : function(){  
      if(this.email == "" || this.password =="") {
        swal.fire({
          icon:"error",
          title:"아이디, 비밀번호를 \n 확인해주세요!"
        });        
        return false;
      }
      var outside = this;
      axios({
        url: "/zippy/member/login",
        method : "POST",
        data: {
          email: this.email,
          password : this.password
        }
      }).then(res =>{
        console.log(res);

        if(res.data == "" || res.data == null){
          swal.fire({
            icon:"error",
            title:"로그인 실패!",
            text:"아이디, 비밀번호를 확인해주세요!"
          })
        } else{
          swal.fire({
            icon:"success",
            title:"로그인 성공!"
          })
          outside.$store.commit('login', res.data);
          console.log(res.data);
          if(this.$store.state.memberType == 0){
            this.$router.push("/mypage");
          }else {
            this.$router.push("/seller");
          }          
        }

      }).catch(error =>{
        console.log(error);
      });
    },

    enter : function(){
      this.login();
    },

    signIn : function(){
      this.$router.push("/signin");
    },

    findInfo : function(){
      this.$router.push("/findUserInfo");      
    }

  }
}
</script>

<style scoped>
#container {
  width: 27%;
  margin: 7% auto;
  /* margin-top: 7%; */  
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
  color : #64c481;
}

select{
  font-size:small;
}
</style>