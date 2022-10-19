<template>
  <div>
    <img :src="file">    
    <a href="http://localhost:8090/zippy/common/img/image1.jpg">아니 이것 안서줬네</a>
  </div>
</template>

<script>
  export default {
    name: "HelloWorld",
    data: function () {
      return {
        file : "http://localhost:8090/zippy/common/img/image1.jpg",
        return: {},
      };
    },

    methods : {
      getImage : function(){
        console.log("dfd");
        window.location.href = "http://localhost:8090/zippy/common/img/image2.jpg";
        // location.href = "http://localhost:8090/zippy/common/img/image2.jpg";
      }
    }
  };
</script>

<style scoped>
img {
  width: 500px;
}
</style>