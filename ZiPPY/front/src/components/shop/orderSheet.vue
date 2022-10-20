<template>
  <div id="order-con" class="mx-auto pt-10">
    <v-toolbar flat transparent>
      <v-toolbar-title>주문서</v-toolbar-title>
      <v-spacer></v-spacer>
    </v-toolbar>
    <table width="100%">
      <tbody>
        <tr>
          <td style="font-weight:bold">주문번호</td>
          <td>
            paycode
          </td>
        </tr>
        <tr>
          <td style="font-weight:bold">결제완료일</td>
          <td>
            22-04-05
          </td>
        </tr>
        <tr>
          <td style="font-weight:bold">배송시작일</td>
          <td>
            22-04-05
          </td>
        </tr>
        <tr>
          <td style="font-weight:bold">수령인</td>
          <td>{{myInfo.userName}}</td>
        </tr>
        <tr>
          <td style="font-weight:bold">전화번호</td>
          <td>{{myInfo.phoneNumber}}</td>
        </tr>
        <tr>
          <td style="font-weight:bold">배송지주소</td>
          <td>({{myInfo.zipCode}}){{myInfo.userAddress}}</td>
        </tr>
        <tr>
          <td style="font-weight:bold">배송지메모</td>
          <td>
            배송지메모
          </td>
        </tr>
        <tr>
          <td colspan="2" style="font-weight:bold">상품정보</td>
        </tr>
        <!-- 상품정보 tr -->
        <tr style="border:0">
          <td colspan="2" style="padding:0; background-color: #EEEEEE;">
            <!-- 상품정보 테이블 -->
            <table id="productInfo" style="padding:30px; width:100%">
              <tbody>
                <tr style="font-weight:bold">
                  <td class="pl-9">상품명</td>
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
                  <td>주문상태<v-btn class="mr-2" width="160" outlined color="#64c481" @click="">
                      후기작성
                    </v-btn></td>
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
  </div>
</template>


<script>
  import axios from 'axios';

  export default {
    data() {
      return {
        email: 'zippy@naver.com',
        myInfo: {},
        orderInfo: {},
        //기존배송지, 신규배송지
        selected: 0,
        selectedMemo: '',
        options: [{
            text: '기본배송지',
            value: 0
          },
          {
            text: '신규배송지',
            value: 1
          }
        ],
        orderMemos: [{
          text:'배송 시 요청사항을 선택해주세요.',
          value:null
        },
        {
          text:'부재 시 경비실에 맡겨주세요.',
          value:'부재 시 경비실에 맡겨주세요.'
        },
        {
          text:'부재 시 택배함에 넣어주세요.',
          value:'부재 시 택배함에 넣어주세요.'
        },
        {
          text:'부재 시 집 앞에 놔두세요.',
          value:'부재 시 집 앞에 놔두세요.'
        },
        {
          text:'파손의 위험이 있는 상품입니다.',
          value:'파손의 위험이 있는 상품입니다.'
        }
        ],
        products: [],
      }
    },
    created() {
      //내 정보조회
      axios({
        url: "/shop/myInfo",
        method: "POST",
        params: {
          email: this.email
        }
      }).then(res => {
        console.log(res);
        this.myInfo = res.data;
        console.log(this.myInfo);
      }).catch(error => {
        console.log(error);
      })
      //주문상품 정보조회
      axios({
        url: "/shop/myPurPro",
        method: "POST",
        params: {
          payCode: this.$route.query.payCode
        }
      }).then(res => {
        console.log(res);
        this.products = res.data;
        console.log(this.products);
      }).catch(error => {
        console.log(error);
      })
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
    filters : {
      comma(val){
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

  #input-30 {
    font-size: 12px !important;
  }

  input {
    width: 150px !important;
    height: 30px !important;
  }

  input::placeholder {
    font-size: 12px;
  }

  /* 핸드폰번호 */
  #phoneNum {
    display: flex;
  }

  #phoneNum :first-child {
    width: 60px !important;
    height: 30px !important;
  }

  #phoneNum input {
    width: 70px !important;
    height: 30px !important;
    margin-right: 10px;
  }

  /* 주소록 */
  #address input {
    width: 350px !important;
    height: 30px !important;
  }
</style>