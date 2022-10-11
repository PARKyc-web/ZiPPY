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
            <v-btn v-if="heart==0" class="mx-2" color='#D6D6D6' fab depressed dark small @click="changeHeart(product.shopProductNo)">
              <v-icon dark>
                mdi-heart
              </v-icon>
            </v-btn>
            <v-btn v-if="heart==1" class="mx-2" color='#FF4063' fab depressed dark small @click="changeHeart(product.shopProductNo)">
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
              <span class="ml-auto" @click="deleteOpt()" style="cursor:pointer">X</span>
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
      <v-card color="#fff" class="mt-10">
        <v-tabs v-model="tab" background-color="transparent" color="#64c481" grow>
          <v-tab>
            상품정보
          </v-tab>
          <v-tab>
            상품후기
          </v-tab>
          <v-tab>
            상품문의
          </v-tab>
        </v-tabs>
        <v-tabs-items v-model="tab">
          <!-- 상품정보 -->
          <v-tab-item>
            <v-card>
              <v-card flat style="width:900px">
                <v-card-text>
                  {{product.shopProductInfo}}
                </v-card-text>
              </v-card>
            </v-card>
          </v-tab-item>
          <!-- 상품정보 끝 -->
          <!-- 상품후기 -->
          <v-tab-item>
            <v-card flat>
              <div>
                <!-- 별점 박스 -->
                <div id="star-box">
                  <div id="star-left">
                    <div class="mt-1">
                      <v-icon>mdi-star</v-icon>
                      <v-icon>mdi-star</v-icon>
                      <v-icon>mdi-star</v-icon>
                      <v-icon>mdi-star</v-icon>
                      <v-icon>mdi-star</v-icon>
                    </div>
                    <h2 class="ml-5" style="font-weight:bold">4.5</h2>
                  </div>
                  <div id="star-right">
                    <div class="progress">
                      <div class="progress-bar" role="progressbar" aria-valuenow="0" aria-valuemin="0"
                        aria-valuemax="100">
                      </div>
                    </div>
                    <h6>배송</h6>
                    <div class="progress">
                      <div class="progress-bar" role="progressbar" style="width: 25%" aria-valuenow="25"
                        aria-valuemin="0" aria-valuemax="100"></div>
                    </div>
                    <h6>가격</h6>
                    <div class="progress">
                      <div class="progress-bar" role="progressbar" style="width: 50%" aria-valuenow="50"
                        aria-valuemin="0" aria-valuemax="100"></div>
                    </div>
                    <h6>디자인</h6>
                    <div class="progress">
                      <div class="progress-bar" role="progressbar" style="width: 75%" aria-valuenow="75"
                        aria-valuemin="0" aria-valuemax="100"></div>
                    </div>
                    <h6>내구성</h6>
                  </div>
                </div>
                <!-- 별점 박스 끝 -->
              </div>
              <!-- 후기박스 -->
              <div id="review-box" class="px-3">
                <hr>
                <!-- 사용자 정보 -->
                <div id="user-info">
                  <p>yed***@gmail.com</p>|<p>2022-10-04</p>|
                  <div id="report-review">
                    <p>신고<v-icon>mdi-alarm-light</v-icon>
                    </p>
                  </div>
                </div>
                <!-- 사용자 정보 끝-->
                <!-- 별점 -->
                <div style="display:flex">
                  <p>
                    <v-icon style="font-size:15px; color:#B3E3C3" class="pb-1">mdi-star</v-icon>
                    5.0
                  </p>
                </div>
                <!-- 별점 끝 -->
                <p>옵션:white(L size)</p>
                <p>코딩이 잘 되는지 모르겠음 사기당함 코딩이 잘 되는지 모르겠음 사기당함 코딩이 잘 되는지 모르겠음 사기당함 코딩이 잘 되는지 모르겠음 사기당함 코딩이 잘 되는지 모르겠음 사기당함
                  코딩이 잘
                  되는지
                  모르겠음 사기당함 </p>
                <hr>
              </div>
              <!-- 후기박스 끝 -->
              <!-- 페이지네이션 -->
              <div class="pb-5">
                <div class="text-center">
                  <v-pagination v-model="page" :length="4" circle color="#B3E3C3"></v-pagination>
                </div>
              </div>
              <!-- 페이지네이션 끝 -->
            </v-card>
          </v-tab-item>
          <!-- 상품후기 끝 -->
          <!-- 상품문의 -->
          <v-tab-item>
            <v-card flat>
              <div id="qna-box" class="px-3">
                <div class="tab-pane" id="qna" role="tabpanel" aria-labelledby="qna-tab">
                  <div id="qna-button" class="my-5">
                    <p>문의하기를 통해 궁금증을 해결하세요.</p>
                    <!-- 모달 버튼 -->
                    <div class="ml-auto pr-3">
                      <v-row>
                        <v-dialog v-model="dialog" persistent max-width="500px">
                          <template v-slot:activator="{ on, attrs }">
                            <v-btn depressed color=#B3E3C3 v-bind="attrs" v-on="on" width="100px">
                              상품문의
                            </v-btn>
                          </template>
                          <v-card>
                            <v-card-title>
                              <span class="text-h6">상품문의</span>
                            </v-card-title>
                            <v-card-text>
                              <v-container>
                                <v-row>
                                  <v-col cols="11">
                                    <v-select :items="['상품문의', '배송문의', '기타문의']" label="문의 유형을 선택해주세요" required>
                                    </v-select>
                                  </v-col>
                                  <v-col cols="12">
                                    문의내용
                                    <v-textarea solo name="input-7-4" label="문의내용을 입력해주세요"></v-textarea>
                                  </v-col>
                                </v-row>
                              </v-container>
                              <small>
                                <v-row>
                                  <v-col cols="12">
                                    - 문의내용에 대한 답변은 ‘마이페이지 > 나의 쇼핑 > 나의 문의내역’ 또는 ‘상품 상세페이지’에서 확인 가능합니다.
                                    <br>
                                    - 배송,결제,교환/반품 문의는 고객센터로 문의 바랍니다.
                                    <br>
                                    - 상품과 관련 없거나 부적합한 내용을 기재하시는 경우, 사전 고지 없이 삭제 또는 차단될 수 있습니다.
                                  </v-col>
                                </v-row>
                              </small>
                            </v-card-text>
                            <v-card-actions>
                              <v-spacer></v-spacer>
                              <v-btn color="#212529" text @click="dialog = false">
                                닫기
                              </v-btn>
                              <v-btn color="#212529" text @click="dialog = false">
                                등록
                              </v-btn>
                            </v-card-actions>
                          </v-card>
                        </v-dialog>
                      </v-row>
                    </div>
                  </div>
                  <!-- 모달 버튼 끝-->
                  <div>
                    <!-- 아코디언 -->
                    <v-expansion-panels>
                      <v-expansion-panel v-for="(item,i) in 5" :key="i">
                        <v-expansion-panel-header>
                          <div id="qna-title">
                            <p style="padding-right:5px">완료</p>|
                            <p style="padding-left:5px">상품문의입니다.</p>
                            <div id="user-info" class="ml-auto">
                              <p>yed***@gmail.com</p>|
                              <p>2022.10.04</p>
                            </div>
                          </div>
                        </v-expansion-panel-header>
                        <v-expansion-panel-content>
                          <div class="qna" id=qna-q>
                            <p>상품이 이상해요</p>
                          </div>
                          <div class="qna" id="qna-a">
                            <p>
                              <v-icon class="mr-4 mb-2">mdi-subdirectory-arrow-right</v-icon>그럴리가
                            </p>
                          </div>
                        </v-expansion-panel-content>
                      </v-expansion-panel>
                    </v-expansion-panels>
                    <!-- 아코디언 끝 -->
                    <!-- 페이지네이션 -->
                    <div class="pb-5">
                      <div class="text-center">
                        <v-pagination v-model="page" :length="4" circle color="#B3E3C3"></v-pagination>
                      </div>
                    </div>
                    <!-- 페이지네이션 끝 -->
                  </div>
                  <hr>
                  <div id="seller-content">
                    <p style="font-size:smaller">- 먼저 문의하신 다른 고객님들의 질문을 참조하시면, 유용한 정보를 빠르게 확인하실 수 있습니다.</p>
                    <p style="font-size:smaller">- 휴일 및 공휴일, 영업시간 종료 이후에 남겨주신 문의 사항은, 평일 영업시간에 답변을 해드리고 있습니다.</p>
                  </div>
                </div>
              </div>
            </v-card>
          </v-tab-item>
          <!-- 상품후기 끝-->
        </v-tabs-items>
      </v-card>
    </div>
  </div>
</template>

<script>
  import axios from 'axios';

  export default {
    data: () => ({
      tab: null,
      imgs: [],
      colors: ['white', 'wood', 'black'],
      selectedColor: '',
      sizes: ['S', 'M', 'L'],
      selectedSize: '',
      page: 1,
      tabs: null,
      dialog: false,
      product: {},
      qty: 1,
      heart: 0,
      opts: [],
      addPrices: [{
        optName : '',
        optPrice : 0
      }],
      ap: {
        optName : '',
        optPrice : 0
      }
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
        if(this.heart == 0){ //찜x
          this.heart=1; //찜on
          alert('상품을 찜했습니다.');
        }else{ //찜on
          this.heart=0;  //찜x
          alert('상품의 찜을 해제하였습니다.')
        }
      },
      goBasket() {
        if(this.selectedColor == '' || this.selectedSize == ''){
          alert('상품의 옵션을 선택해주세요.')
        }
      },
      goOrder() {
        if(this.selectedColor == '' || this.selectedSize == ''){
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
        //색상, 사이즈 분리 작업
        for(var i in this.opts) {
          if(this.opts[i].shopProductOptColor !== ''){
            this.colors.push(this.opts[i].shopProductOptColor)
            if(this.opts[i].shopProductOptPrice !== ''){
              this.ap.optName=this.opts[i].shopProductOptColor;
              this.ap.optPrice=this.opts[i].shopProductOptPrice;
              this.addPrices.push(this.ap)
            }
          }
          if(this.opts[i].shopProductOptSize !== ''){
            this.sizes.push(this.opts[i].shopProductOptSize)
            if(this.opts[i].shopProductOptPrice !== ''){
              this.ap.optName=this.opts[i].shopProductOptSize;
              this.ap.optPrice=this.opts[i].shopProductOptPrice;
              this.addPrices.push(this.ap)
            }
          }
        }
        console.log(this.addPrices)
      }).catch(error => {
        console.log(error);
      })
    },
    computed : {
      countAmount() {
        let amount = 0;
        amount += this.product.shopProductPrice * this.qty;
        amount += Number(this.product.shopDeliveryCost);
        return amount;
      }
    }
  };
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