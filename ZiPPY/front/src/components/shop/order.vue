<template>
  <div id="order-con" class="mx-auto pt-10">
    <v-toolbar flat transparent>
      <v-toolbar-title>주문서</v-toolbar-title>
      <v-spacer></v-spacer>
    </v-toolbar>
    <table width="100%">
      <tbody>
        <tr>
          <td style="font-weight:bold">배송지 선택</td>
          <td>
            <b-form-group v-slot="{ ariaDescribedby }">
              <b-form-radio-group v-model="selected" :options="options" :aria-describedby="ariaDescribedby"
                name="plain-inline" plain></b-form-radio-group>
            </b-form-group>
          </td>
        </tr>
        <tr>
          <td style="font-weight:bold">수령인</td>
          <td v-if="selected==0">{{myInfo.userName}}</td>
          <td v-if="selected==1">
            <b-form-input v-model="text" placeholder="이름"></b-form-input>
          </td>
        </tr>
        <tr>
          <td style="font-weight:bold">전화번호</td>
          <td v-if="selected==0">{{myInfo.phoneNumber}}</td>
          <td v-if="selected==1">
            <div id="phoneNum">
              <b-form-input v-model="text"></b-form-input>
              <b-form-input v-model="text"></b-form-input>
              <b-form-input v-model="text"></b-form-input>
            </div>
          </td>
        </tr>
        <tr>
          <td style="font-weight:bold">배송지주소</td>
          <td v-if="selected==0">({{myInfo.zipCode}}){{myInfo.userAddress}}</td>
          <td v-if="selected==1">
            <div id="address" style="display:flex">
              <b-form-input v-model="text" class="mb-2"></b-form-input>
              <v-btn class="ml-10" height="30" depressed color=#B3E3C3>
                주소찾기
              </v-btn>
            </div>
            <b-form-input class="mb-2" v-model="text"></b-form-input>
            <div id="address">
              <b-form-input v-model="text" placeholder="상세주소를 입력해주세요"></b-form-input>
            </div>
          </td>
        </tr>
        <tr>
          <td style="font-weight:bold">배송지메모</td>
          <td>
            <!-- 옵션 -->
            <v-select v-model="selectedMemo" :items="orderMemos" item-text="text" item-value="value"
                label="배송지메모" return-object dense outlined style="width:350px"></v-select>
          </td>
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
                    :src="'/zippy/common/img/shop/'+product.productVO.proMainImg"
                      style="cursor:default"></v-img>
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
        <tr>
          <td style="font-weight:bold">결제정보</td>
        </tr>
        <tr>
          <td style="font-weight:bold">결제수단</td>
          <td>
            <v-btn class="mr-5" depressed color=#B3E3C3>
              신용카드
            </v-btn>
            <!--
            <v-btn class="mr-5" depressed color=#B3E3C3>
              실시간계좌이체
            </v-btn>
            <v-btn class="mr-5" depressed color=#B3E3C3>
              가상계좌
            </v-btn>
            <v-btn class="mr-5" depressed color=#B3E3C3>
              휴대폰 결제
            </v-btn>
            -->
          </td>
        </tr>
        <tr style="border-bottom : thin solid rgb(0, 0, 0, 0.12);">
          <td style="font-weight:bold">주문자동의</td>
          <td>회원 본인은 구매 조건, 주문 내용 확인 및 결제에 동의합니다.
            <br>개인정보 수집 및 이용 동의 자세히
            <br>개인정보 제 3자 제공 동의 자세히
            <br>전자결제대행 이용 동의 자세히</td>
        </tr>
      </tbody>
    </table>
    <div class="mx-auto pt-5" style="width:200px; margin-top:30px; margin-bottom:100px">
      <v-btn depressed color=#B3E3C3 style="width:150px; font-size:larger" @click="payItem">
        결제하기
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
    methods: {
      payItem() {
        var IMP = window.IMP;
        let outside = this;
        var countPros = '';
        if (this.products.length > 1) {
          countPros += ' 외 ' + (this.products.length - 1) + '건'
        }

        IMP.init("imp22120243")
        IMP.request_pay({ // param
          pg: "html5_inicis",
          pay_method: "card",
          merchant_uid: outside.products[0].payCode,

          name: outside.products[0].productVO.proName + countPros,
          amount: //outside.countAmount,
            '1000',
          buyer_email: outside.myInfo.email,
          buyer_name: outside.myInfo.userName,
          buyer_tel: outside.myInfo.phoneNumber,
          buyer_addr: outside.myInfo.userAddress,
          buyer_postcode: outside.myInfo.zipCode
        }, rsp => { // callback
          if (rsp.success) {
          
            swal.fire({
            icon: 'success',
            title: '결제가 완료되었습니다.',
            showConfirmButton: false,
            timer: 1500
          });
            //서버에 결제 정보 저장 
            axios({
              url: "/zippy/shop/insertOrder",
              headers: {
                "Content-Type": "application/json"
              },
              method: "POST",
              data: {
                email: outside.myInfo.email,
                payCode: outside.products[0].payCode,
                payMethod: "card",
                amount: outside.countAmount,
                buyerName: outside.myInfo.userName,
                buyerTel: outside.myInfo.phoneNumber,
                buyerAddr: outside.myInfo.userAddress,
                buyerZipcode: outside.myInfo.zipCode,
                orderMemo: outside.selectedMemo.value
              }
            }).then(res => {
              console.log(res);
              this.$router.push({
                name: 'orderComplete',
                query: {
                  payCode: outside.products[0].payCode
                }
              })
            }).catch(error => {
              console.log(error);
            })
          } else {
            console.log(rsp.error_msg)
          }
        });
      }
    },
    created() {
      //내 정보조회
      axios({
        url: "/zippy/shop/myInfo",
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
        url: "/zippy/shop/myPurPro",
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