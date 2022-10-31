<template>
  <!-- 캐러셀 시작 -->
  <div>
    <v-carousel cycle height="500" hide-delimiter-background :show-arrows="false" class="mt-5">
        <v-carousel-item v-for="(slide, i) in slides" :key="i">
          <v-img :src="imgs[i]" height="100%">
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
          
          // 'https://sirelo.co.za/wp-content/uploads/2019/08/moving-boxes-in-conservatory.jpeg',
          // 'https://media.istockphoto.com/photos/clothes-in-cardboard-box-on-a-blue-background-closeup-picture-id1387211107?b=1&k=20&m=1387211107&s=170667a&w=0&h=OPHsC9ake6U34XHrbnVD4AIqk0W_7sNvr_aGLORMXx8=',
          // 'https://images.unsplash.com/photo-1624137527136-66e631bdaa0e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NHx8bW92aW5nfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=600&q=60',
          
          
          //빈티지분위기
          'https://media.istockphoto.com/photos/empty-room-full-of-cardboard-boxes-picture-id1322709181?k=20&m=1322709181&s=612x612&w=0&h=TWWDsJqO004F8lvkNagR4YL2LsRljqaMW3nV5yaLjUU=',
          
          //박스들
          // 'https://media.istockphoto.com/photos/cardboard-boxs-in-front-of-the-blue-wall-stock-photo-picture-id1312824481?k=20&m=1312824481&s=612x612&w=0&h=Cg_MukemuLabcUXb3qR32xwnOkAD278BTHxEsqy5BZw=',
          // 'https://media.istockphoto.com/photos/cardboard-boxes-picture-id157677192?k=20&m=157677192&s=612x612&w=0&h=E97RSZApRMDETIwv6HdxfeSdV-VbcTaxW5KJGhof-2Y='
          'https://media.istockphoto.com/photos/cardbcardboard-boxes-in-front-of-anthracite-colored-wall-stock-photo-picture-id1314029836?k=20&m=1314029836&s=612x612&w=0&h=2lmwonPzyUqsJV2bPEooQ87MZKsU0N0s6HtxpCjZSjk='
          ,'https://media.istockphoto.com/photos/young-couple-placing-sofa-in-new-home-picture-id1316499359?k=20&m=1316499359&s=612x612&w=0&h=M8x_5m3qohDx_MdO3-s7ft89q1qCuxDswaOMhoUM7KU='
        ],
        slides: [
          '어려운 이사',
          '깔끔한 이사',
          '친근한 이사'
        ],
        info: [
          '전문가의 손길로 손쉽게',
          '시작부터 마무리까지 깔끔하게',
          '가족같이 친근한 서비스'
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
    color:black;
    ;
    margin: 0 0 150px 260px;
    font-weight: bold;
  }

  #info-text {
    font-weight: bold;
    padding: 20px 0 0 5px;
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