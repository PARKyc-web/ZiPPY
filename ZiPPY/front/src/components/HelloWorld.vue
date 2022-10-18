<template>
  <div>
    <img :src="file">
    <button @click="getImage()">BUTTON</button>
  </div>
</template>

<script>
  export default {
    name: "HelloWorld",
    data: function () {
      return {
        file : "",
        return: {},
      };
    },

    methods : {
      getImage : function(){
        this.$axios({
          url :"common/image/" + "image1.jpg",
          methods : "GET",          
        }).then(res => {
          console.log(res)
          this.file = res.data;
          console.log(this.file);
                  
        }).catch(error => {
          console.log(error);
        })
      }
    }
  };
</script>

<style scoped>
img {
  width: 100px;
}
</style>