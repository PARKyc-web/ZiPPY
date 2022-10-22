<template>
  <div id="order-con" class="mx-auto pt-10">
    <v-toolbar flat transparent>
      <v-toolbar-title>주문서</v-toolbar-title>
      <v-spacer></v-spacer>
    </v-toolbar>
    <table width="100%" style="margin-bottom:80px">
      <tbody>
        <tr>
          <td style="font-weight:bold">주문번호</td>
          <td>
            {{this.$route.query.payCode}}
          </td>
        </tr>
        <tr>
          <td style="font-weight:bold">주문일</td>
          <td>{{orderInfo.orderDate}}</td>
        </tr>
        <tr>
          <td style="font-weight:bold">수령인</td>
          <td>{{orderInfo.buyerName}}</td>
        </tr>
        <tr>
          <td style="font-weight:bold">전화번호</td>
          <td>{{orderInfo.buyerTel}}</td>
        </tr>
        <tr>
          <td style="font-weight:bold">배송지주소</td>
          <td>({{orderInfo.buyerZipcode}}){{orderInfo.buyerAddr}}</td>
        </tr>
        <tr>
          <td style="font-weight:bold">배송지메모</td>
          <td>{{orderInfo.orderMemo}}</td>
        </tr>
        <tr>
          <td colspan="2" style="font-weight:bold">상품정보</td>
        </tr>
        <!-- 상품정보 tr -->
        <tr style="border:0">
          <td colspan="2" style="padding:0; background-color: #EEEEEE;">
            <!-- 상품정보 테이블 -->
            <table id="productInfo" style="padding:30px; width:100%; text-align:center">
              <tbody>
                <tr style="font-weight:bold">
                  <td>상품명</td>
                  <td></td>
                  <td>주문상태</td>
                  <td>옵션</td>
                  <td>스토어</td>
                  <td>가격</td>
                  <td>수량</td>
                  <td>배송비</td>
                </tr>
                <tr v-for="product in products" :key="product.proNo">
                  <td class="pl-9">{{product.productVO.proName}}</td>
                  <td>
                    <v-img width="150px" height="150px"
                      :src="require(`../../assets/shop/productImg/${product.productVO.proMainImg}.jpg`)"
                      style="cursor:default"></v-img>
                  </td>
                  <td>
                    <p v-if="product.orderStatus == 0">결제완료</p>
                    <p v-if="product.orderStatus == 1">배송시작</p>
                    <!-- <v-btn depressed color=#B3E3C3 ">
                      후기작성
                    </v-btn> -->
                    <v-dialog v-model="dialog" persistent :retain-focus="false" max-width="500px">
                      <template v-slot:activator="{ on, attrs }">
                        <v-btn id="reviewBtn" depressed color=#B3E3C3 v-bind="attrs" v-on="on" width="100px"
                          v-if="product.orderStatus == 1">
                          후기작성
                        </v-btn>
                      </template>
                      <v-card>
                        <v-card-title>
                          <span class="text-h6">상품후기</span>
                        </v-card-title>
                        <v-card-text>
                          <v-container style="color:#212529">
                            <v-row>
                              <!-- 별점선택 -->
                              <v-col cols="11">
                                <table>
                                  <tbody>
                                    <tr style="border: 0">
                                      <td>가격</td>
                                      <td>
                                        <b-form-rating @click="$event.preventDefault()" v-model="rate1" no-border
                                          color="#64c481" size="sm"></b-form-rating>
                                      </td>
                                    </tr>
                                    <tr style="border: 0">
                                      <td>배송</td>
                                      <td>
                                        <b-form-rating @click="$event.preventDefault()" v-model="rate2" no-border
                                          color="#64c481" size="sm"></b-form-rating>
                                      </td>
                                    </tr>
                                    <tr style="border: 0">
                                      <td>디자인</td>
                                      <td>
                                        <b-form-rating @click="$event.preventDefault()" v-model="rate3" no-border
                                          color="#64c481" size="sm"></b-form-rating>
                                      </td>
                                    </tr>
                                    <tr style="border: 0">
                                      <td>내구성</td>
                                      <td>
                                        <b-form-rating @click="$event.preventDefault()" v-model="rate4" no-border
                                          color="#64c481" size="sm"></b-form-rating>
                                      </td>
                                    </tr>
                                  </tbody>
                                </table>
                              </v-col>
                              <v-col cols="12">
                                리뷰
                                <v-textarea @click="$event.preventDefault()" solo v-model="reviewContent" no-border
                                  name="input-7-4" label="리뷰를 입력해주세요">
                                </v-textarea>
                              </v-col>
                            </v-row>
                          </v-container>
                        </v-card-text>
                        <v-card-actions>
                          <v-spacer></v-spacer>
                          <v-btn color="#212529" text @click="closeReview()">
                            닫기
                          </v-btn>
                          <v-btn color="#212529" text @click="insertReview(product.purPno)">
                            등록
                          </v-btn>
                        </v-card-actions>
                      </v-card>
                    </v-dialog>
                  </td>
                  <td>{{product.optName}}</td>
                  <td>{{product.productVO.compName}}</td>
                  <td>{{product.purPrice | comma}}</td>
                  <td>{{product.purQty}}</td>
                  <td>{{product.productVO.deliveryCost | comma}}</td>
                </tr>
              </tbody>
            </table>
          </td>
        </tr>
        <tr>
          <td colspan="2" style="text-align:right">
            총 주문금액 <span style="font-weight:bold; font-size:20px">{{countAmount}}</span>원
          </td>
        </tr>
        <tr style="border-bottom:thin solid rgb(0, 0, 0, 0.12)">
          <td style="font-weight:bold;">결제수단</td>
          <td>
            신용카드
          </td>
        </tr>
      </tbody>
    </table>
    <div class="mx-auto pt-5" style="width:150px; margin-top:10px; margin-bottom:100px">
      <v-btn depressed color=#B3E3C3 style="width:150px; font-size:larger" @click="goBack">
        주문내역
      </v-btn>
    </div>
  </div>
</template>


<script>
  import axios from 'axios';
  import swal from 'sweetalert2';

  export default {
    data() {
      return {
        pc: '',
        products: [],
        orderInfo: {},
        //후기 
        reviewContent: '',
        //serviceId: '',
        totalRating: '',
        rate1: '',
        rate2: '',
        rate3: '',
        rate4: '',
        //모달
        dialog: false
      }
    },
    created() {
      //주문상품 조회
      axios({
        url: "/shop/myPurPro",
        method: "POST",
        params: {
          purNo: this.$route.query.purNo,
          payCode: this.$route.query.payCode
        }
      }).then(res => {
        console.log(res);
        this.products = res.data;
        console.log(this.products);
      }).catch(error => {
        console.log(error);
      })
      //주문자 정보 조회
      axios({
        url: "/shop/oneOrder",
        method: "POST",
        params: {
          payCode: this.$route.query.payCode
        }
      }).then(res => {
        console.log(res);
        this.orderInfo = res.data;
        console.log(this.orderInfo);
      }).catch(error => {
        console.log(error);
      })
      // //리뷰 조회
      //  axios({
      //     url: "/common/RvOne",
      //     method: "GET",
      //     params: {
      //       email: this.$store.state.loginInfo.email,
      //       serviceId: this.$route.query.purNo,
      //       serviceType: 2
      //     }
      //   }).then(res => {
      //     this.wish = res.data;
      //     if (res.data != "") {
      //       this.heart = 1;
      //     } else if (res.data == "") {
      //       this.heart = 0;
      //     }
      //   }).catch(err => {
      //     console.log(err)
      //   })
    },
    methods: {
      goBack() {
        this.$router.go(-1);
      },
      //모달 닫기
      closeReview() {
        this.dialog = false;
        this.rate1 = '',
          this.rate2 = '',
          this.rate3 = '',
          this.rate4 = '',
          this.reviewContent = ''
      },
      //리뷰 등록
      insertReview(no) {
        const rBtn = document.getElementById("reviewBtn");
        let outside = this;
        if (!this.rate1 || !this.rate2 || !this.rate3 || !this.rate4 || !this.reviewContent) {
          //totalRate 입력
          this.totalRating =
            (Number(this.rate1) +
              Number(this.rate2) +
              Number(this.rate3) +
              Number(this.rate4)) / 4
          swal.fire({
            icon: 'warning',
            title: '별점과 리뷰를 입력해주세요.',
            showConfirmButton: false,
            timer: 1500
          });
        } else {
          axios({
            url: "/common/addRv",
            method: "POST",
            headers: {
              "Content-Type": "application/json"
            },
            data: {
              email: this.$store.state.loginInfo.email,
              reviewTitle: 0,
              reviewContent: this.reviewContent,
              serviceType: 2,
              serviceId: no,
              viewCnt: 0,
              totalRating: this.totalRating,
              rate1: this.rate1,
              rate2: this.rate2,
              rate3: this.rate3,
              rate4: this.rate4,
            }
          }).then(res => {
            console.log(res);
            //모달창 닫기
            outside.dialog = false;
            //버튼 비활성화

            rBtn.disabled = true;
            rBtn.style.backgroundColor = '#D6D6D6';
            rBtn.innerText = '작성완료';
          }).catch(error => {
            console.log(error);
          })
        }
      }
    },
    computed: {
      countAmount() {
        var am = 0;
        for (var i in this.products) {
          am += Number(this.products[i].purPrice * this.products[i].purQty)
          am += Number(this.products[i].productVO.deliveryCost)
        }
        return am;
      },
    },
    filters: {
      comma(val) {
        return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ',')
      }
    },
  }
</script>

<style scoped>
  #order-con {
    width: 70%;
  }

  .v-toolbar__title {
    font-weight: bold;
  }

  tr {
    border-top: thin solid rgb(0, 0, 0, 0.12);
  }

  td {
    padding: 12px;
    font-size: 13px;
  }

  .v-btn {
    font-weight: bold;
  }
</style>