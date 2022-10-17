<template>
    <div id="find"> 
        <b-form-radio-group
          id="btn-radios-2"
          v-model="selected"
          :options="options"          
          button-variant="outline-primary"
          size="lg"
          name="radio-btn-outline"
          buttons
        ></b-form-radio-group>        
        
        <br><br>
        <div v-if="selected == 'radio1'">
            <b-form-radio-group
            id="btn-radios-2"
            v-model="typeSelected"
            :options="memberType"          
            button-variant="outline-primary"
            size="lg"
            name="radio-btn-outline"
            buttons
            ></b-form-radio-group>
            <br><br>
            핸드폰 번호 : <input type="text">
        </div>        

        <div v-if="selected == 'radio2'">
            이메일 : <input type="text">
        </div>
        <input type="button" :value="selected">
    </div>
  </template>
  
<script>
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
          ]


        }
      }
    }
</script>

<style scoped>
#find{
    margin-left: 15%;
    margin-top: 5%;
    width: 70%;
    text-align: center;
    border: 1px solid black;
}

input {
    border : 1px solid black;
}
</style>