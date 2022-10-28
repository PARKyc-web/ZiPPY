<template>
  <div>
    <!-- 캐러셀 시작 -->
    <div>
      <v-carousel cycle height="500" hide-delimiter-background :show-arrows="false" class="mt-5">
        <v-carousel-item v-for="(slide, i) in slides" :key="i">
          <v-img :src="require(`../../assets/shop/${imgs[i]}.jpg`)" height="100%">
            <!-- <v-img :src="imgs[i]" height="100%"> -->
            <v-row class="fill-height" align="center" justify="center">
              <div class="text-h3" id="slide-text">
                {{ slide }}
                <div class="text-h6" id="info-text">
                  {{ info[i] }}
                </div>
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
          <img :src="'/zippy/common/img/shop/'+product.proMainImg">
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
        <div id="main-item" v-for="product in productList" :key="product.proNo" @click="goDetail(product.proNo)">
          <div id="main-product-img">
            <img :src="'/zippy/common/img/shop/'+product.proMainImg">
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
        // imgs: [
        //   'https://img.freepik.com/free-photo/empty-living-room-with-blue-sofa-plants-and-table-on-empty-white-wall-background-3d-rendering_41470-1778.jpg?w=1480&t=st=1666709412~exp=1666710012~hmac=71b8324cababdaf17f969bb1f9a0eeee78ff94e71fd7f36c4eba5cf0bcbcd392',
        //   'https://img.freepik.com/free-photo/mockup-wall-in-the-children39s-room-with-gray-armchair-on-light-white-color-wall_41470-4931.jpg?w=1480&t=st=1666710650~exp=1666711250~hmac=4e3ca8aadeaa744ead36f5b5e7c052dd654201fec4223ed625959e978e812bfb',
        //   '/'
        // ],
        imgs: [
          'main1',
          'main2',
          'main3'
        ],
        slides: [
          '아늑한 거실 꾸미기',
          '소품으로 포인트 주기',
          '그린 인테리어'
        ],
        info: [
          '아늑하게 거실을 꾸며보아요🥰',
          '귀여운 소품 활용🤩',
          '에코 그린 인테리어🤗'
        ],
        products: [],
        productList: []
      }
    },
    created() {
      //1번 리스트
      axios({
        url: "/zippy/shop/main",
        method: "GET"
      }).then(res => {
        console.log(res);
        this.products = res.data;
        console.log(this.products);
      }).catch(error => {
        console.log(error);
      })
      //2번 리스트
      axios({
        url: "/zippy/shop/main",
        method: "GET"
      }).then(res => {
        console.log(res);
        this.productList = res.data;
        console.log(this.productList);
      }).catch(error => {
        console.log(error);
      })
    },
    methods: {
      goDetail(no) {
        this.$router.push('/shop/detail?pno=' + no)
      }
    },
    filters: {
      comma(val) {
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
    margin: 0 auto;
    position: relative;
    z-index: 1;
  }

  #main-background {
    width: 100%;
    margin-top: 64px;
    height: 450px;
    background-color: #B3E3C3;
    position: absolute;
    top: 0;
    z-index: 0;
  }

  #slide-text {
    color: #191919;
    ;
    margin: 0 0 0 300px;
    font-weight: bold;
  }

  #info-text {
    /*font-weight: bold;*/
    padding: 30px 0 0 5px;
  }

  /* 아이템 리스트 */
  /*** 아마 공통? ***/
  #left {
    text-align: left;
  }

  .product-about {
    margin-top: 10px;
    width: 270px;
    height: 66px;
  }

  #product-name:hover {
    text-decoration: underline;
    cursor: pointer;
  }

  /*** 아마 공통? ***/

  .main-item-list {
    margin-top: 150px;
    width: 1455px;
    margin: 0 auto;
    margin-top: 60px;
    text-align: center;
    display:flex;
    flex-wrap: wrap;
  }

  #list-name {
    padding: 70px 30px 30px 0;
    width: 1455px;
  }

  #list-name h2 {
    font-weight: bold;
  }

  #main-color1 {
    background-color: #F7F7F7;
  }

  #main-item {
    margin-right: 21px;
    margin-bottom: 20px;
    /* display: inline-block; */
    cursor: pointer;
    width: 270px;
    height: 384px;
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