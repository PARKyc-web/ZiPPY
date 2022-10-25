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
                    <p v-if="product.orderStatus == 1 || product.orderStatus == 2">배송시작</p>
                    <!-- 후기 작성 모달 -->
                    <review-modal :serviceId="product.purPno" :orderStatus="product.orderStatus" :purNo="product.purNo">
                    </review-modal>
                    <!-- <v-btn v-if="product.orderStatus == 2" disabled depressed color=#D6D6D6 width="100px">
                      작성완료
                    </v-btn> -->
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
  import reviewModal from './reviewModal.vue';

  export default {
    components: {
      reviewModal
    },
    data() {
      return {
        pc: '',
        products: [],
        orderInfo: {}
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
    },
    methods: {
      goBack() {
        this.$router.go(-1);
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