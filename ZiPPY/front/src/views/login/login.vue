<template>
<div id="container">
     <v-container grid-list-xl text-xs-center>
       <v-layout row wrap>         
           <v-card color="dark" elevation-24 style="padding: 20px; border: 1px; border-radius: 30px;">
             <v-card-text>
              <div class="login_img">
                <img src="../../assets/zippy_logo.png" width="150px" />
              </div>
                <br>
                
                <h4 class="display-2 font-weight-bold">Login</h4>
                
               <hr>
               <br>
               <v-form>
                 <v-text-field 
                   clearable
                   v-model="email"
                   label="Username"
                   prepend-icon="face"
                   type="email"                   
                   color="#b3e3c3"                   
                   @keyup="enter()"             
                 >
                 </v-text-field>
                 <v-text-field 
                   clearable
                   v-model="password"
                   label="Password"
                   prepend-icon="lock"
                   type="password"                   
                   color="#b3e3c3"                   
                   counter="20"
                   @keyup="enter()"
                 >
                 </v-text-field>
              </v-form>
              <br>
              <div id="login_btn">
               <v-btn @click="login()" color="#b3e3c3" large>Enter</v-btn>
              </div>
             </v-card-text>
             <hr>

             <div class="login_img">
              <button @click="kLogin()"><img src="../../assets/kakao_login.png" width="200px" height="50px"/></button>                  
              <button @click="nLogin()"><img src="../../assets/naver_login.png" width="200px" height="50px"/></button>
             </div>
             
           </v-card>                    
       </v-layout>
     </v-container>  
</div>
</template>

<script>
import axios from 'axios';

export default{  
  data() {
    return {
      email: '',
      password: '',
    }
  },
  methods: {    
    login : async function(){  
      if(this.email == "" || this.password =="") {
        alert("ID, 비밀번호를 입력하세요!");
        return false;
      }
      var outside = this;
      var temp = await axios({
        url: "http://localhost:8090/zippy/member/login",
        method : "POST",
        data: {
          email: this.email,
          password : this.password
        }
      }).then(res =>{
        console.log(res);

        if(res.data == "" || res.data == null){
          console.log("로그인 실패!");          
        } else{
          alert("로그인 성공!");
          outside.$router.push("/signin");
        }

      }).catch(error =>{
        console.log(error);
      });
    },

    nLogin : function(){
      console.log("Naver_login");
    },

    kLogin : function(){
      console.log("Kakao_login");
    },

    enter : function(){
      if(window.event.keyCode == 13){
        this.login();
      } 
    }
  }
}
</script>

<style scoped>
#container {
  width: 35%;  
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
</style>