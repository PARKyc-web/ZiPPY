<template>
  <div>
    <div class="wrap"></div>
      <video id="video" autoplay="autoplay" muted="muted" loop>
        <source src="@/assets/mint.mp4" type="video/mp4">
      </video>
  
    <div class="text">
      <p>부동산 검색부터 이사까지 <br> ZIPPY</p>
    </div>
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
@font-face {
    font-family: 'GmarketSans';
    font-weight: 500;
    font-style: normal;
    src: url('https://cdn.jsdelivr.net/gh/webfontworld/gmarket/GmarketSansMedium.eot');
    src: url('https://cdn.jsdelivr.net/gh/webfontworld/gmarket/GmarketSansMedium.eot?#iefix') format('embedded-opentype'),
         url('https://cdn.jsdelivr.net/gh/webfontworld/gmarket/GmarketSansMedium.woff2') format('woff2'),
         url('https://cdn.jsdelivr.net/gh/webfontworld/gmarket/GmarketSansMedium.woff') format('woff'),
         url('https://cdn.jsdelivr.net/gh/webfontworld/gmarket/GmarketSansMedium.ttf') format("truetype");
    font-display: swap;
} 
* {
  font-family: 'GmarketSans';  
}

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

.text {
  position: absolute;
  /* width: 100%; */
  width :fit-content;
  top: 70%;
  left: 50%;
  transform: translate(-50%,-180%);
  border : 5px solid #fff;
  /* border: 5px solid rgb(100, 196, 129, 0.8);  */
  padding : 30px 30px 0px 30px;    
}
.text p {
  text-align: center;
  font-size: 48px;  
  color : #fff;
  /* color : rgb(100, 196, 129, 0.8);  */
}

</style>