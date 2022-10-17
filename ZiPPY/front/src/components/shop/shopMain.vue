<template>
  <div>
    <!-- 캐러셀 시작 -->
    <div>
      <v-carousel cycle height="500" hide-delimiter-background :show-arrows="false" class="mt-5">
        <v-carousel-item v-for="(slide, i) in slides" :key="i">
          <v-img :src="imgs[i]" height="100%">
            <v-row class="fill-height" align="center" justify="center">
              <div class="text-h2" id="slide-text">
                {{ slide }}
              </div>
            </v-row>
          </v-img>
        </v-carousel-item>
      </v-carousel>
    </div>
    <!-- 캐러셀 끝 -->
    <div id="main-background"></div>
    <!-- 아이템 리스트 -->
    <div class="main-item-list">
      <div id="list-name">
        <h2 style="color:#B3E3C3">#BEST</h2>
      </div>
      <!-- 아이템 -->
        <div id="main-item" v-for="product in products" :key="product.proNo" @click="goDetail(product.proNo)">
          <div id="main-product-img">
            <img :src="require(`../../assets/shop/productImg/${product.proMainImg}.jpg`)">
          </div>
          <div class="product-about" id="left">
            <h6 id="product-name">{{ product.proName }}</h6>
            <h6>{{ product.proPrice | comma }}</h6>
          </div>
        </div>
      <!-- 아이템 끝 -->
    </div>
    <!-- 아이템 리스트 -->
    <div class="main-item-color" id="main-color1">
      <div class="main-item-list">
        <div id="list-name" style="margin:0 auto">
          <h2 style="color:#212529; text-align: center;">이런 건 어떠세요?</h2>
        </div>
        <div id="main-item" v-for="product in products" :key="product.proNo" @click="goDetail(product.proNo)">
          <div id="main-product-img">
            <img :src="require(`../../assets/shop/productImg/${product.proMainImg}.jpg`)">
          </div>
          <div class="product-about" id="left">
            <h6 id="product-name">{{ product.proName }}</h6>
             <h6>{{ product.proPrice | comma }}</h6>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import axios from 'axios';

  export default {
    data() {
      return {
        imgs: [
          'https://cdn1.epicgames.com/ue/product/Screenshot/SF039-1920x1080-466e1033381d9706717c5d877d67bd29.jpeg?resize=1&w=1920',
          'https://i.pinimg.com/originals/e3/50/d2/e350d23264d7c1bbc0b9c03489e9367d.jpg',
          'https://rare-gallery.com/mocahbig/22117-Living-Room-FurnitureRoom-4k-Ultra-HD-Wallpaper.jpg',
          'https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/best-accent-chair-1647958244.jpg',
          'https://images6.alphacoders.com/647/647568.jpg',
        ],
        slides: [
          'First',
          'Second',
          'Third',
          'Fourth',
          'Fifth',
        ],
        products: []
      }
    },
    created() {
      axios({
        url: "http://localhost:8088/zippy/shop/main",
        method: "GET"
      }).then(res => {
        console.log(res);
        this.products = res.data;
        console.log(this.products);
      }).catch(error => {
        console.log(error);
      })
    },
    methods: {
      goDetail(no) {
        this.$router.push('/shop/detail?pno='+no)
      }
    },
    filters : {
      comma(val){
        return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ',')
      }
    }
  };
</script>

<style scoped>
  /* 슬라이드 */
  .v-carousel {
    width: 70%;
    border-radius: 1.5em;
    margin:0 auto;
    position:relative;
    z-index: 1;
  }
  #main-background {
    width:100%;
    margin-top:68px;
    height:450px;
    background-color:#B3E3C3;
    position:absolute;
    top:0;
    z-index: 0;
  }
  #slide-text{
    color:#fff;
    margin:300px 0 0 200px;
    font-weight: bold;
  }
  /* 아이템 리스트 */
  /*** 아마 공통? ***/
  #left {
    text-align: left;
  }

  .product-about {
    margin-top: 10px;
  }

  #product-name:hover {
    text-decoration: underline;
    cursor: pointer;
  }

  /*** 아마 공통? ***/

  .main-item-list {
    margin-top: 150px;
    width: 80%;
    margin: 0 auto;
    margin-top: 60px;
    text-align: center;
  }

  #list-name {
    padding: 70px 30px 30px 0;
  }

  #list-name h2 {
    font-weight: bold;
  }
  #main-color1 {
    background-color: #F7F7F7;
  }

  #main-item {
    padding-right: 21px;
    display: inline-block;
    cursor: pointer;
  }

  #main-product-img img {
    width: 270px;
    height: 300px;
    max-width: 100%;
    transition: all 0.2s linear;
    border-radius: 0.8em;
  }

  #main-product-img {
    width: 270px;
    height: 300px;
    border-radius: 0.8em;
    margin: 0px auto;
    overflow: hidden;
  }

  #main-product-img:hover img {
    transform: scale(1.05);
  }
</style>