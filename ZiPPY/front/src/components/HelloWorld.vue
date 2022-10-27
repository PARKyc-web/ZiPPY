<template>
  <div>
    <div class="wrap"></div>
      <video id="video" autoplay="autoplay" muted="muted" loop>
        <source src="../assets/zippyMain.mp4" type="video/mp4">
      </video>
  </div>
</template>

<script>
  export default {
    name: "HelloWorld",
    data: function () {
      return {
        return: {},
      };
    },
    mounted() {
     document.getElementById('video').playbackRate = 0.8;
    },
    methods: {}
  };
</script>

<style scoped>
video {
  width: 100%; 
  height: 100vh;
  object-fit: cover;
  position: fixed;
  right: 0;
  bottom: 0;
  background-size: cover;
}
/* .wrap {
  width: 100%;
  height: 100vh;
  background: linear-gradient(red, black);
  
} */

</style>