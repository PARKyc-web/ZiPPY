<template>
  <div>
    <h1>ZiPPY에 오신걸 환영합니다!</h1>
    <img :src="file">    
    <!-- <a href="http://localhost:8090/zippy/common/img/member/image1.jpg">아니 이것 안서줬네</a> -->
  </div>
</template>

<script>
  export default {
    name: "HelloWorld",
    data: function () {
      return {
        file : "http://localhost:8090/zippy/common/img/member/" + "image3.jpg", 
        return: {},
      };
    },

    methods : {
      getImage : function(){
        console.log("dfd");        
      }
    }
  };
</script>

<style scoped>
img {
  width: 500px;
}
</style>