<template>
  <!-- 캐러셀 시작 -->
  <div>
    <v-carousel cycle height="500" hide-delimiter-background :show-arrows="false" class="mt-5">
      <v-carousel-item v-for="(slide, i) in slides" :key="i">
        <v-img :src="imgs[i]" height="100%">
          <v-row class="fill-height" text-align="center" justify="center">
            <div class="text-h3" id="slide-text">
              <!-- {{ slide }} -->
              <div class="text-h6" id="info-text">
                {{ info[i] }}
              </div>
            </div>
          </v-row>
        </v-img>
      </v-carousel-item>
    </v-carousel>
    <div id="main-background"></div>
  </div>
  <!-- 캐러셀 끝 -->

</template>
<script>
  import axios from 'axios';

  export default {
    data() {
      return {
        imgs: [
          'https://blog.kakaocdn.net/dn/PgZCo/btqZJ4V7158/Slzsv5Xt4ihVlCXYWrMz51/img.png',
          'https://www.metropolitanmovers.ca/wp-content/uploads/2020/05/blog-15.jpeg',
          'https://s3.ap-northeast-2.amazonaws.com/cloimage/home/rails/clo/public/ckeditor_assets/pictures/3210/content_tip014d15100337.jpg'
        ],
        slides: [
          '깔끔한 시작',
          '전문적인 기술',
          '친근한 서비스'
        ],
        info: [
          '아늑하게 거실을 꾸며보아요',
          '귀여운 소품 활용',
          '에코 그린 인테리어'
        ],
        products: []
      }
    },
    created() {
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
    margin-top: 130px;
    height: 450px;
    background-color: #B3E3C3;
    position: absolute;
    top: 0;
    z-index: 0;
  }

  #slide-text {
    color: #afb4b1;
    ;
    margin: 0 0 0 300px;
    font-weight: bold;
  }

  #info-text {
    font-weight: bold;
    padding: 30px 0 0 5px;
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

  
</style>