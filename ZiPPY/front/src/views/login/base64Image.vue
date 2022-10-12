<template>
<div>
  <form id="testform">
    <div>
      <input
                      type="email"
                      name="email"
                      class="form-control"
                      id="inputEmail"                      
                      placeholder="name@example.com"
                      v-model="data.email"
                      required
                      autofocus
                    />
    <!-- <input type="email" name="email" v-model="data.email"> -->
    </div>
    <input type="password" name="password" v-model="data.password">

    <input multiple type="file" name="images" id="imgtest">    

    <button type="button" @click="imageUploaded()">button</button>
  </form>
  
</div>
</template>


<script>
import axios from 'axios';
export default {

  data(){
    return {
      data : {
        email : "test",
        password : "1234",
        isSocial : 0,
        nickName : "123444",        
      },
      images: []
    }
  },
  methods : {
    imageUploaded : function(){

      var formData = new FormData(document.querySelector('#testform'));
      
      console.log(this.data);

      axios({
          url: "http://localhost:8090/zippy/member/gSignUp",
          method: "POST",    
          // headers :{
          //   // 'Content-Type': 'multipart/form-data'
          //   // "Content-Type" : "application/x-www-form-urlencoded;charset=UTF-8"
          // },
          data: formData
          
        })
          .then((res) => {
            console.log(res);
          })
          .catch((error) => {
            console.log(error);
          });
    },   
  }

}
</script>