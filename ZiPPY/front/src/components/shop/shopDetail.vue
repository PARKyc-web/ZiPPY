<template>
  <div>
    <!-- slide start -->
    <div id="detail-container">
      <v-carousel height="450" hide-delimiter-background :show-arrows="false" class="mt-5">
        <v-carousel-item v-for="(img, i) in imgs" :key="i">
          <v-img :src="'/zippy/common/img/shop/'+imgs[i]" height="100%">
            <v-row class="fill-height" align="center" justify="center">
            </v-row>
          </v-img>
        </v-carousel-item>
      </v-carousel>
      <!-- slide end -->
      <!-- 상품 상세정보 -->
      <div id="detail-info">
        <div style="display:flex">
          <p class="pa-0 mt-5">{{product.compName}}></p>
          <!--heart-->
          <div class="ml-auto">
            <v-btn v-if="heart==0" class="mx-2" color='#D6D6D6' fab depressed dark small
              @click="changeHeart()">
              <v-icon dark>
                mdi-heart
              </v-icon>
            </v-btn>
            <v-btn v-if="heart==1" class="mx-2" color='#FF4063' fab depressed dark small
              @click="changeHeart()">
              <v-icon dark>
                mdi-heart
              </v-icon>
            </v-btn>
          </div>
        </div>
        <h4 style="display:block; padding-bottom: 10px;">{{product.proName}}</h4>
        <h1 style="display:inline-block">{{product.proPrice | comma}}</h1>
        <h5 style="display:inline-block">원</h5>
        <h6 class="pb-5">
          <v-icon color="#B3E3C3" class="pr-2">mdi-truck</v-icon>배송비<span> {{product.deliveryCost | comma}}</span>원
        </h6>
        <!-- 옵션 -->
        <div v-if="opts.length > 0">
          <v-select v-model="selectedOpt" @change="selectOptAc" :items="opts" item-text="optName" item-value="optNo"
            label="옵션" return-object dense outlined width="330"></v-select>
        </div>
        <!-- 옵션 여러개 선택하는 기능 만들기... 나중에 -->
        <!-- 옵션 끝 -->
        <!-- 상품선택 박스 -->
        <div class="pa-5" v-if="selectOption.optNo || opts.length == 0"
          style="background-color:#F7F7F7; border-radius:0.8em">
          <div style="font-size:smaller">
            <div style="display:flex">
              {{product.proName}} {{selectOption.optName}}
              <div v-if="selectOption.optPrice > 0" class="ml-2">(+{{selectOption.optPrice | comma}}원)
              </div>
              <span class="ml-auto" @click="deleteOpt()" style="cursor:pointer" v-if="opts.length > 0">X</span>
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
          <h4>{{countAmount | comma}}</h4>
          <h6>원</h6>
        </div>
        <!-- 총 가격 끝 -->
        <!-- 버튼 -->
        <div id="detail-button" style="margin:0">
          <v-btn class="mr-2" width="160" outlined color="#64c481" @click="goCart()">
            장바구니
          </v-btn>
          <v-btn width="160" depressed color=#B3E3C3 @click="goOrder">
            바로구매
          </v-btn>
        </div>
        <!-- 버튼 끝 -->
      </div>
    </div>
    <!-- 탭 -->
    <!-- 상품 상세정보 -->
    <div class="mx-auto pb-5" style="width:810px">
      <shop-tab :pno="$route.query.pno" :proInfo="product.proInfo"></shop-tab>
    </div>
  </div>
</template>

<script>
  import axios from 'axios';
  import shopTab from './shopTab.vue';
  import swal from 'sweetalert2';

  export default {
    components: {
      shopTab
    },
    data: () => ({
      //상품(DB)
      product: {},
      //상세이미지(DB)
      imgs: [],
      //상품 옵션 리스트(DB)
      opts: [],
      //선택한 옵션
      selectedOpt: {},
      //선택한 옵션이 담김(나중에 []형태로 수정합시다)
      selectOption: {},
      //수량(기본:1)
      qty: 1,
      //장바구니에 담을 상품
      selectPro: {},
      //장바구니에 있는 상품목록(DB)
      cartPros: [],
      //찜(기본:0)
      heart: 0,
      optCheck: '',
      data: {
        email: "",
        serviceId: "",
        bookmarkNo: "",
        serviceType: 2
      }
    }),
    methods: {
      //수량 감소
      minusQty() {
        if (this.qty > 1) {
          this.qty--;
        } else {
          swal.fire({
            icon: 'warning',
            title: '최소 수량은 1개입니다.',
            showConfirmButton: false,
            timer: 1500
          });
        }
      },
      //수량 증가
      plusQty() {
        if (this.qty > 9) {
          swal.fire({
            icon: 'warning',
            title: '최대 수량은 10개입니다.',
            showConfirmButton: false,
            timer: 1500
          });
          return;
        }
        this.qty++;
      },
      //선택한 상품 삭제
      deleteOpt() {
        this.selectedOpt = {};
        this.selectOption = {};
      },
      //찜등록-해제
      changeHeart() {
        if (this.$store.state.loginInfo != null) {
          if (this.heart == 0) { //찜x
            this.addWish();
            this.heart = 1; //찜on
            swal.fire({
              icon: 'success',
              title: '찜 목록에 추가되었습니다.',
              showConfirmButton: false,
              timer: 1500
            });
          } else { //찜on
            this.delWish();
            this.heart = 0; //찜x
            swal.fire({
              icon: 'success',
              title: '찜목록에서 삭제되었습니다.',
              showConfirmButton: false,
              timer: 1500
            });
          }
        } else {
          swal.fire({
            icon: 'warning',
            title: '로그인 정보가 필요합니다.',
            showConfirmButton: false,
            timer: 1500
          });
        }
      },
      //옵션 체크(옵션이 있다고 가정)
      checkOption() {
        //옵션이 존재하는 경우
        if (this.opts.length > 0) {
          if (!this.selectOption.hasOwnProperty("optNo")) {
            //체크용
            this.optCheck = false;
            swal.fire({
            icon: 'warning',
            title: '상품의 옵션을 선택해주세요.',
            showConfirmButton: false,
            timer: 1500
          });
          } else {
            //선택한 옵션이 존재할 경우 등록 진행
            this.optCheck = true;
          }
        } else {
          //옵션이 아예 없는 경우
          this.optCheck = true;
        }
      },
      //장바구니 등록
      goCart() {
        if (this.$store.state.loginInfo != null) {
          //옵션 존재여부 체크
          this.checkOption();

          if (this.optCheck) {
            let check = '';
            //장바구니에 존재한 상품 check
            axios({
              url: "/zippy/shop/myCartList",
              method: "POST",
              params: {
                email: this.$store.state.loginInfo.email
              }
            }).then(res => {
              console.log(res);
              this.cartPros = res.data;

              //옵션이 있는 경우
              if (this.opts.length > 0) {
                for (var i in this.cartPros) {
                  //상품번호, 옵션 둘다 체크
                  if (this.product.proNo == this.cartPros[i].cartPno &&
                    this.selectOption.optNo == this.cartPros[i].cartOptNo) {
                    swal.fire({
                      icon: 'warning',
                      title: '이미 장바구니에 있는 상품입니다.',
                      showConfirmButton: false,
                      timer: 1500
                    });
                    //선택 옵션 비워주기
                    this.selectedOpt = {};
                    this.selectOption = {};
                    //상품등록 불가
                    check = false;
                    return;
                  } else {
                    //상품등록 가능
                    check = true;
                    if (this.selectOption.optNo == 0) {
                      check = false;
                      return;
                    }
                  }
                }
              } else {
                //옵션이 없는 경우
                for (var i in this.cartPros) {
                  //상품번호만 체크
                  if (this.product.proNo == this.cartPros[i].cartPno) {
                    swal.fire({
                      icon: 'warning',
                      title: '이미 장바구니에 있는 상품입니다.',
                      showConfirmButton: false,
                      timer: 1500
                    });
                    //상품등록 불가
                    check = false;
                    return
                  } else {
                    check = true;
                  }
                }
              }
              //장바구니가 비어져있을 경우
              if (this.cartPros.length == 0) {
                check = true;
              }
              console.log(check)
              //상품등록
              if (check) {
                axios({
                  url: "/zippy/shop/insertCart",
                  method: "POST",
                  headers: {
                    "Content-Type": "application/x-www-form-urlencoded; charset=utf-8"
                  },
                  data: {
                    email: this.$store.state.loginInfo.email,
                    cartPno: this.product.proNo,
                    cartOptNo: this.selectOption.optNo,
                    cartQty: this.qty,
                  }
                }).then(res => {
                  console.log(res);
                  //this.selectPro = res.data;
                  swal.fire({
                    icon: 'success',
                    title: '장바구니에 상품이 담겼습니다.',
                    showConfirmButton: false,
                    timer: 1500
                  });
                  //선택했던 상품 삭제
                  this.selectedOpt = {};
                  this.selectOption = {};
                  //장바구니 개수 추가
                  this.$store.state.loginInfo.cartCount = Number(this.$store.state.loginInfo.cartCount) + 1
                }).catch(error => {
                  console.log(error);
                })
              }
            }).catch(error => {
              console.log(error);
            })
          }
        } else {
          swal.fire({
            icon: 'warning',
            title: '로그인 정보가 필요합니다.',
            showConfirmButton: false,
            timer: 1500
          });
        }
      },
      goOrder() {
        if (this.$store.state.loginInfo != null) {
          this.checkOption();
          if (this.optCheck) {
            //payCode 생성
            this.makePayCode();
            //purchase 테이블에 등록(1개)
            axios({
              url: "/zippy/shop/insertPurOne",
              headers: {
                "Content-Type": "application/json"
              },
              method: "POST",
              data: {
                proNo: this.product.proNo,
                selectOptNo: this.selectOption.optNo,
                qty: this.qty,
                purPrice: this.countPurPrice,
                email: this.product.email //업체 이메일
              },
              params: {
                payCode: this.payCode,
                email: this.$store.state.loginInfo.email //구매자 이메일
              }
            }).then(res => {
              console.log(res);
              //주문페이지 이동
              this.$router.push({
                name: 'order',
                query: {
                  payCode: this.payCode
                }
              })

            }).catch(error => {
              console.log(error);
            })

          }
        } else {
          swal.fire({
            icon: 'warning',
            title: '로그인 정보가 필요합니다.',
            showConfirmButton: false,
            timer: 1500
          });
        }
      },
      //payCode 생성
      makePayCode() {
        var arr = new Uint32Array(10);
        this.randNum = window.crypto.getRandomValues(arr);
        this.payCode = this.randNum[0] + new Date().getTime();
      },
      //선택한 옵션을 담는 행위 
      selectOptAc() {
        this.selectOption = {
          ...this.selectedOpt
        }
        this.qty = 1;
      },
      //찜 추가
      addWish: function () {
        axios({
          url: "/zippy/common/addWish",
          method: "POST",
          headers: {
            "Content-Type": "application/json; charset=utf-8"
          },
          data: JSON.stringify(this.data)
        }).then(res => {
          console.log(this.data)
          this.rewrite();
        }).catch(err => {
          console.log(err)
        })
      },
      //찜 삭제
      delWish: function () {
        let bNo = [];
        console.log(this.wish.bookmarkNo)
        bNo.push(this.wish.bookmarkNo);
        console.log(bNo);
        axios({
          url: "/zippy/common/delWish",
          method: "DELETE",
          data: {
            bNo: bNo
          }
        }).then(res => {
          console.log(res);
        }).catch(err => {
          console.log(err)
        })
      },
      rewrite() {
        axios({
          url: "/zippy/common/wishOne",
          method: "GET",
          params: {
            email: this.$store.state.loginInfo.email,
            sId: this.product.proNo,
            serviceType: this.data.serviceType
          }
        }).then(res => {
          this.wish = res.data;
          if (res.data != "") {
            this.heart = 1;
          } else if (res.data == "") {
            this.heart = 0;
          }
        }).catch(err => {
          console.log(err)
        })
      }
    },
    created() {
      //단건조회
      axios({
          url: "/zippy/shop/detail",
          method: "GET",
          params: {
            pno: this.$route.query.pno
          }
        }).then(res => {
          console.log(res);
          this.product = res.data;
          console.log(this.product);
          this.data.serviceId = this.product.proNo;
          this.data.email = this.$store.state.loginInfo.email;
        }).catch(error => {
          console.log(error);
        }),
        //이미지조회
        axios({
          url: "/zippy/shop/img",
          method: "GET",
          params: {
            pno: this.$route.query.pno
          }
        }).then(res => {
          console.log(res);
          this.imgs = res.data;
          this.imgs.unshift(this.product.proMainImg)
        }).catch(error => {
          console.log(error);
        }),
        //옵션조회
        axios({
          url: "/zippy/shop/opt",
          method: "GET",
          params: {
            pno: this.$route.query.pno
          }
        }).then(res => {
          console.log(res);
          this.opts = res.data;
          console.log(this.opts)
        }).catch(error => {
          console.log(error);
        })
      //찜여부조회
      if (this.$store.state.loginInfo != null) {
        axios({
          url: "/zippy/common/wishOne",
          method: "GET",
          params: {
            email: this.$store.state.loginInfo.email,
            sId: this.$route.query.pno,
            serviceType: this.data.serviceType
          }
        }).then(res => {
          this.wish = res.data;
          if (res.data != "") {
            this.heart = 1;
          } else if (res.data == "") {
            this.heart = 0;
          }
        }).catch(err => {
          console.log(err)
        })
      }
    },
    computed: {
      countAmount() {
        let amount = 0;
        amount += this.product.proPrice * this.qty;
        amount += Number(this.product.deliveryCost);
        if (this.selectOption.optNo) {
          amount += Number(this.selectOption.optPrice) * this.qty;
        }
        return amount;
      },
      countPurPrice() {
        let amount = 0;
        amount += this.product.proPrice * this.qty;
        if (this.selectOption.optNo) {
          amount += Number(this.selectOption.optPrice) * this.qty;
        }
        return amount;
      }
    },
    filters: {
      comma(val) {
        return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ',')
      }
    }
  }
</script>

<style scoped>
  p {
    letter-spacing: -1px;
  }

  #detail-container {
    width: 810px;
    display: flex;
    margin: 0 auto;
    flex-wrap: wrap;
    padding-top: 100px;
  }

  /* 디테일 슬라이드 */
  .v-carousel {
    width: 440px;
    border-radius: 1.5em;
  }

  /* 상세설명 */
  #heart {
    margin-left: auto;
  }

  #detail-info {
    width: 370px;
    height: 510px;
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