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
                   @keyup.enter="enter()"             
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
                   @keyup.enter="enter()"
                 >
                 </v-text-field>
              </v-form>
              <br>
              <div id="login_btn">
               <v-btn @click="login()" color="#b3e3c3" large>Enter</v-btn>
              </div>
             </v-card-text>
             <hr>             
             <ul>
              <li><button @click="signIn()">회원가입</button></li>
              <li><button @click="findInfo(1)">아이디 찾기</button></li>
              <li><button @click="findInfo(2)">비밀번호 찾기</button></li>
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
    login : async function(){  
      if(this.email == "" || this.password =="") {
        swal.fire({
          icon:"error",
          title:"아이디, 비밀번호를 \n 확인해주세요!"
        });        
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
          console.log(outside.$store.state.loginInfo);
          this.$router.push("/home");
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

    findInfo : function(num){
      if(num == 0){
        this.$router.push("/findUserInfo");
      } else{
        this.$router.push("/findUserInfo");
      }
      
    }

  }
}
</script>

<style scoped>
#container {
  width: 35%;
  margin-top: 7%;
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
</style>