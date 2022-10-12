<template>
  <div>
    <!-- slide start -->
    <div id="detail-container">
      <v-carousel height="500" hide-delimiter-background :show-arrows="false" class="mt-5">
        <v-carousel-item v-for="(img, i) in imgs" :key="i">
          <v-img :src="require(`../../assets/shop/productImg/${imgs[i]}.jpg`)" height="100%">
            <v-row class="fill-height" align="center" justify="center">
            </v-row>
          </v-img>
        </v-carousel-item>
      </v-carousel>
      <!-- slide end -->
      <!-- 상품 상세정보 -->
      <div id="detail-info">
        <div style="display:flex">
          <p>{{product.compName}}></p>
          <!--heart-->
          <div class="ml-auto">
            <v-btn v-if="heart==0" class="mx-2" color='#D6D6D6' fab depressed dark small
              @click="changeHeart(product.shopProductNo)">
              <v-icon dark>
                mdi-heart
              </v-icon>
            </v-btn>
            <v-btn v-if="heart==1" class="mx-2" color='#FF4063' fab depressed dark small
              @click="changeHeart(product.shopProductNo)">
              <v-icon dark>
                mdi-heart
              </v-icon>
            </v-btn>
          </div>
        </div>
        <h4 style="display:block; padding-bottom: 10px;">{{product.shopProductName}}</h4>
        <h1 style="display:inline-block">{{product.shopProductPrice}}</h1>
        <h5 style="display:inline-block">원</h5>
        <h6 class="pb-5">
          <v-icon color="#B3E3C3" class="pr-2">mdi-truck</v-icon>배송비<span> {{product.shopDeliveryCost}}</span>원
        </h6>
        <!-- 옵션 -->
        <div>
          <!--colors-->
          <v-select v-model="selectedColor" :items="colors" label="색상" dense outlined width="330"></v-select>
        </div>
        <div>
          <!--sizes-->
          <v-select v-model="selectedSize" :items="sizes" label="사이즈" dense outlined width="330"></v-select>
        </div>
        <!-- 옵션 끝 -->
        <div class="pa-5" v-if="selectedColor !== '' && selectedSize !== ''" id="optionBox">
          <div style="font-size:smaller">
            <div style="display:flex">
              {{product.shopProductName}} {{selectedColor}} / {{selectedSize}}
              <div v-if="countOptPrice > 0" class="ml-2">(+{{countOptPrice}}원)</div>
              <span class="ml-auto" @click="deleteOpt()" style="cursor:pointer">X</span>
            </div>
          </div>
          <!-- 수량조절 -->
          <div>
            <v-btn class="mr-1" fab depressed width="20px" height="20px" color="#fff" @click="minusQty()">
              <v-icon dark>
                mdi-minus
              </v-icon>
            </v-btn>
            {{ qty }}
            <!-- plus -->
            <v-btn class="ml-1" fab depressed width="20px" height="20px" color="#fff" @click="plusQty()">
              <v-icon dark>
                mdi-plus
              </v-icon>
            </v-btn>
          </div>
        </div>
        <!-- 총 가격 -->
        <div id="total-price">
          <h6 style="padding-right:10px">총 상품금액</h6>
          <h4>{{countAmount}}</h4>
          <h6>원</h6>
        </div>
        <!-- 총 가격 끝 -->
        <!-- 버튼 -->
        <div id="detail-button" style="margin:0">
          <v-btn class="mr-2" width="163" outlined color="#64c481" @click="goBasket()">
            장바구니
          </v-btn>
          <v-btn width="163" depressed color=#B3E3C3 @click="goOrder()">
            바로구매
          </v-btn>
        </div>
        <!-- 버튼 끝 -->
      </div>
    </div>
    <!-- 탭 -->
    <!-- 상품 상세정보 -->
    <div class="mx-auto pb-5" style="width:900px">
      <shop-tab :no="product.shopProductNo"></shop-tab>
    </div>
  </div>
</template>

<script>
  import axios from 'axios';
  import shopTab from './shopTab.vue'

  export default {
    components : {
      shopTab
    },
    data: () => ({
      //상품
      product: {},
      //상세이미지
      imgs: [],
      //상품 옵션(색상, 사이즈, 추가가격)
      opts: [],
      //옵션색상
      colors: [],
      //선택색상
      selectedColor: '',
      //옵션사이즈
      sizes: [],
      //추가가격
      addPrices: [],
      //선택사이즈
      selectedSize: '',
      //수량(기본:1)
      qty: 1,
      //찜(기본:0)
      heart: 0
    }),
    methods: {
      //수량 감소
      minusQty() {
        if (this.qty > 1) {
          this.qty--;
        } else {
          alert('최소수량은 1개입니다.')
        }
      },
      //수량 증가
      plusQty() {
        if (this.qty > 9) {
          alert('최대수량은 10개입니다.')
          return;
        }
        this.qty++;
      },
      deleteOpt() {
        this.selectedColor = '';
        this.selectedSize = '';
        this.qty = 1;
      },
      changeHeart() {
        if (this.heart == 0) { //찜x
          this.heart = 1; //찜on
          alert('상품을 찜했습니다.');
        } else { //찜on
          this.heart = 0; //찜x
          alert('상품의 찜을 해제하였습니다.')
        }
      },
      goBasket() {
        if (this.selectedColor == '' || this.selectedSize == '') {
          alert('상품의 옵션을 선택해주세요.')
        }
      },
      goOrder() {
        if (this.selectedColor == '' || this.selectedSize == '') {
          alert('상품의 옵션을 선택해주세요.')
        }
      }
    },
    created() {
      //단건조회
      axios({
          url: "http://localhost:8088/zippy/shop/detail",
          methods: "GET",
          params: {
            no: this.$route.query.no
          }
        }).then(res => {
          console.log(res);
          this.product = res.data;
          console.log(this.product);
        }).catch(error => {
          console.log(error);
        }),
        //이미지조회
        axios({
          url: "http://localhost:8088/zippy/shop/img",
          methods: "GET",
          params: {
            no: this.$route.query.no
          }
        }).then(res => {
          console.log(res);
          this.imgs = res.data;
          this.imgs.unshift(this.product.shopMainImg)
        }).catch(error => {
          console.log(error);
        }),
        //옵션조회
        axios({
          url: "http://localhost:8088/zippy/shop/opt",
          methods: "GET",
          params: {
            no: this.$route.query.no
          }
        }).then(res => {
          console.log(res);
          this.opts = res.data;
          // //색상, 사이즈 분리 작업
          // for (var i in this.opts) {
          //   if (this.opts[i].shopProductOptColor !== null) {
          //     this.colors.push(this.opts[i].shopProductOptColor)
          //     //추가가격
          //     if (this.opts[i].shopProductOptPrice !== null) {
          //       this.addPrices.push({
          //         optName: this.opts[i].shopProductOptSize,
          //         optPrice: this.opts[i].shopProductOptPrice
          //       });
          //     }
          //   }
          //   if (this.opts[i].shopProductOptSize !== null) {
          //     this.sizes.push(this.opts[i].shopProductOptSize)
          //     //추가가격
          //     if (this.opts[i].shopProductOptPrice !== null) {
          //       this.addPrices.push({
          //         optName: this.opts[i].shopProductOptSize,
          //         optPrice: this.opts[i].shopProductOptPrice
          //       });
          //     }
          //   }
          // }
          // console.log(this.addPrices)
        }).catch(error => {
          console.log(error);
        })
    },
    computed: {
      countAmount() {
        let amount = 0;
        amount += this.product.shopProductPrice * this.qty;
        amount += Number(this.product.shopDeliveryCost);
        amount += Number(this.countOptPrice) * this.qty;
        return amount;
      },
      // countOptPrice() {
      //   let amount = 0;
      //   for (var i in this.addPrices) {
      //     if (this.selectedColor == this.addPrices[i].optName) {
      //       amount += Number(this.addPrices[i].optPrice);
      //     } else if (this.selectedSize == this.addPrices[i].optName) {
      //       amount += Number(this.addPrices[i].optPrice);
      //     }
      //   }
      //   return amount;
      // }
    }
  }
</script>

<style scoped>
  p {
    letter-spacing: -1px;
  }

  #detail-container {
    width: 900px;
    display: flex;
    margin: 0 auto;
    flex-wrap: wrap;
    padding-top: 100px;
    padding-bottom: 50px;
  }

  /* 디테일 슬라이드 */
  .v-carousel {
    width: 500px;
    border-radius: 1.5em;
  }

  /* 상세설명 */
  #heart {
    margin-left: auto;
  }

  #detail-info {
    width: 390px;
    height: 500px;
    padding: 40px 0 0 40px;
  }

  #detail-button {
    clear: both;
    padding-top: 20px;
  }

  #total-price {
    padding-top: 30px;
    text-align: right;
  }

  #total-price h6,
  h4 {
    display: inline-block;
  }


  #detail-info span {
    font-weight: bold;
  }

  #detail-info h3 {
    font-weight: bold;
  }

  #detail-button {
    text-align: right;
  }

  #detail-button button {
    height: 38px;
  }

  /* 옵션 */

  #optionBox {
    width: 350px;
    height: 80px;
    border-radius: 0.8em;
    background-color: #F7F7F7;
  }

  /* 리뷰 */
  #star-box {
    width: 900px;
    text-align: center;
    display: flex;
  }

  #star-left {
    width: 250px;
    margin: 0 auto;
    display: flex;
    color: #212529;
    padding: 80px 0 80px 30px;
  }

  #star-right {
    width: 250px;
    margin: 0 auto;
    text-align: left;
    padding: 40px 0 10px 0;
  }

  .progress-bar {
    width: 200px;
    background-color: #B3E3C3;
  }

  .progress {
    width: 200px;
  }

  #star-right h6 {
    font-size: smaller;
  }

  #user-info {
    display: flex;
    float: right;
    font-size: small;
  }

  #user-info p {
    padding: 0 5px 0 5px;
  }

  #report-review:hover {
    cursor: pointer;
  }

  /* qna */
  #qna-box {
    width: 900px;
  }

  #qna-button {
    display: flex;
    height: 38px;
  }

  #qna-button .btn {
    margin-left: auto;
  }


  #seller-content {
    font-size: smaller;
    line-height: 2px;
  }

  .accordion-body {
    background-color: #e4e4e4;
    padding: 30px;
  }

  .accordion {
    width: 780px
  }

  #qna-title {
    display: flex;
    width: 780px;
  }

  .accordion-button::after {
    display: none;
  }

  .qna {
    padding: 10px;
  }

  #qna-title p {
    margin: 0;
  }
</style>