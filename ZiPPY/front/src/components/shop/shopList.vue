<template>
  <div id="main-container">
    <!-- 카테고리 -->
    <div id="category">
      <hr>
      <div id="category-list">
        <h6 v-for="cate in categories" :key="cate" @click="goList(cate)">{{cate}}</h6>
      </div>
      <hr>
    </div>
    <!-- 카테고리 끝 -->
    <!-- list container -->
    <div id="list-container">
      <!-- 키워드 확인 -->
      <div id="keyword" v-if="this.$route.query.keyw">
        <h6>'<span>{{this.$route.query.keyw}}</span>'에 대한 검색 결과</h6>
      </div>
      <!-- 키워드 확인 끝 -->
      <!-- 옵션 -->
      <div style="width:100px" class="ml-auto py-10">
        <v-select v-model="select" :items="items" item-text="state" item-value="abbr" label="판매순" color="#212529"
          persistent-hint return-object single-line dense width="50"></v-select>
      </div>
      <!-- 상품리스트 -->
      <div id="product-list">
        <!-- 상품 없을 때-->
        <div id="noProduct" class="mx-auto pt-10" v-if="products.length == 0">
          <v-icon style="font-size:100px; color:#B3E3C3" class="mb-5">mdi-alert-circle-outline</v-icon>
          <h2 style="font-weight:bold">고객님께서 찾으시는 상품이 없습니다.</h2>
          <p>다시 검색해주세요.</p>
        </div>
        <!-- 상품정보 -->
        <div id="product-info" v-for="product in products" :key="product.proNo"
          @click="goDetail(product.proNo)">
          <div id="product-img">
            <img :src="require(`../../assets/shop/productImg/${product.proMainImg}.jpg`)">
          </div>
          <div class="product-about">
            <h6 class="product-seller-name">{{product.compName}}</h6>
            <h6 style="font-weight:bold" class="product-seller-name" id="product-name">{{product.proName}}</h6>
            <h5 style="display:inline-block">{{product.proPrice}}</h5>
            <p class="ma-0" style="display:inline-block; font-size:small">원</p>
          </div>
          <h6 class="product-seller-name">
            <v-icon style="font-size:15px">mdi-star</v-icon> 4.5(10)
          </h6>
        </div>
      </div>
      <!-- 페이지네이션 -->
      <div class="pb-5">
        <div class="text-center">
          <v-pagination v-model="page" :length="4" circle color="#B3E3C3"></v-pagination>
        </div>
      </div>
      <!-- 페이지네이션 끝 -->
    </div>
    <!-- 상품리스트 끝 -->
  </div>
  <!-- list container end -->
</template>

<script>
  import axios from 'axios';

  export default {
    data: () => ({
      items: ['판매순', '최신순', '리뷰순', '별점순'],
      page: 1,
      categories: [
        '침대',
        '토퍼/매트리스',
        '소파',
        '테이블/식탁/책상',
        '서랍/수납장',
        '책장',
        '의자',
        '거울',
        '조명',
        '소품'
      ],
      select: [],
      products: []
    }),
    methods: {
      //디테일 페이지로 이동
      goDetail(no) {
        this.$router.push('/shop/detail?pno=' + no)
      },
      goList(cate) {
        this.$router.push(({
          name: 'shopList',
          query: {cate: cate}
        })).catch(()=>{});;
        this.$router.go(0);
      },
    },
    created() {
      if (this.$route.query.keyw ) {
        axios({
          url: "http://localhost:8088/zippy/shop/keyword",
          methods: "GET",
          params: {
            keyw: this.$route.query.keyw
          }
        }).then(res => {
          console.log(res);
          this.products = res.data;
          console.log(this.products);
        }).catch(error => {
          console.log(error);
        })
      }else if(this.$route.query.cate) {
        axios({
        url: "http://localhost:8088/zippy/shop/category",
        methods: "GET",
        params: {
          cate: this.$route.query.cate
        }
      }).then(res => {
        console.log(res);
        this.products = res.data;
        console.log(this.products);
      }).catch(error => {
        console.log(error);
      })
      }
    }
  };
</script>

<style scoped>
  #main-container {
    display: flex;
    margin: 0;
  }

  /* 카테고리 */
  #category {
    width: 400px;
    height: 600px;
    padding: 60px;
  }

  #category h6 {
    padding: 10px;
  }

  #category-list h6 {
    font-weight: bold;
  }

  #category-list h6:hover {
    color: #858585;
    cursor: pointer;
  }

  /* 드롭다운(옵션) */
  .v-application .primary--text {
    color: #212529 !important;
  }

  /* 상품리스트 */
  #list-container {
    width: 950px;
    margin-left: 30px;
  }

  #list-container h5,
  h4,
  h6 {
    font-weight: bold;
    margin-bottom: 3px;
  }

  /* 상품없을 시 표현 박스 */
  #noProduct {
    height: 400px;
    text-align: center;
  }

  #product-list {
    clear: both;
    margin-right: auto;
    display: flex;
    flex-wrap: wrap;
  }

  #product-list i {
    color: #B3E3C3;
  }

  .product-seller-name {
    font-size: 12px;
    color: black;
    font-weight: normal;
  }

  #product-name {
    font-size: 14px;
  }

  #product-info {
    height: 440px;
    padding-left: 20px;
    margin-bottom: 50px;
  }

  #product-name:hover {
    text-decoration: underline;
    cursor: pointer;
  }

  .product-about {
    margin-top: 10px;
  }

  #product-info:hover {
    cursor: pointer;
  }

  #product-info span {
    color: 858585;
  }

  #product-info img {
    width: 290px;
    height: 320px;
    max-width: 100%;
    transition: all 0.2s linear;
    border-radius: 0.8em;
  }

  #product-img {
    width: 290px;
    height: 320px;
    margin: 0px auto;
    overflow: hidden;
    border-radius: 0.8em;
  }

  #product-info:hover img {
    transform: scale(1.05);
  }

  /* 키워드 */
  #keyword {
    text-align: center;
    margin-top: 50px;
  }

  #keyword span {
    color: #B3E3C3;
  }
</style>