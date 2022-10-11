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
          <td v-if="selected==0">김쇼핑</td>
          <td v-if="selected==1">
            <b-form-input v-model="text" placeholder="이름"></b-form-input>
          </td>
        </tr>
        <tr>
          <td style="font-weight:bold">전화번호</td>
          <td v-if="selected==0">010-1111-1111</td>
          <td v-if="selected==1">
            <div id="phoneNum">
            <b-form-input v-model="text"></b-form-input><b-form-input v-model="text"></b-form-input><b-form-input v-model="text"></b-form-input>
            </div>
          </td>
        </tr>
        <tr>
          <td style="font-weight:bold">배송지주소</td>
          <td v-if="selected==0">(123-45)중앙로</td>
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
            <b-form-select v-model="selectedAdd" :options="delMemos"></b-form-select>
          </td>
        </tr>
        <tr>
          <td colspan="2" style="font-weight:bold">상품정보</td>
        </tr>
        <!-- 상품정보 tr -->
        <tr style="border:0">
          <td colspan="2" style="padding:0">
            <!-- 상품정보 테이블 -->
            <table id="productInfo" style="width:100%">
              <tbody>
                <tr>
                  <td>상품명</td>
                  <td></td>
                  <td>옵션</td>
                  <td>스토어</td>
                  <td>가격</td>
                  <td>수량</td>
                  <td>배송비</td>
                </tr>
                <tr v-for="product in products" :key="product.shopProductNo">
                  <td>{{product.shoPproductName}}</td>
                  <td>
                    <v-img width="150px" height="150px" :src="product.shopMainImg"></v-img>
                  </td>
                  <td>{{product.shopProductOpt}}</td>
                  <td>대충 스토어이름</td>
                  <td>{{product.shopProductPrice}}</td>
                  <td>대충 수량</td>
                  <td>{{product.shopDeliveryCost}}</td>
                </tr>
              </tbody>
            </table>
          </td>
        </tr>
        <tr>
          <td colspan="2" style="text-align:right">
            총 주문금액 <span style="font-weight:bold; font-size:20px">10000</span>원
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
            <v-btn class="mr-5" depressed color=#B3E3C3>
              네이버페이
            </v-btn>
            <v-btn class="mr-5" depressed color=#B3E3C3>
              카카오페이
            </v-btn>
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
    <div class="mx-auto pt-5" style="width:100px">
      <v-btn depressed color=#B3E3C3>
        결제하기
      </v-btn>
    </div>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        orderInfo: {},
        selected: 0,
        selectedAdd: null,
        options: [{
            text: '기본배송지',
            value: 0
          },
          {
            text: '신규배송지',
            value: 1
          }
        ],
        delMemos: [{
            text: '배송 시 요청사항을 선택해주세요.',
            value: null
          },
          {
            text: '부재 시 경비실에 맡겨주세요.',
            value: 'a'
          },
          {
            text: '부재 시 택배함에 넣어주세요.',
            value: 'b'
          },
          {
            text: '부재 시 집 앞에 놔두세요.',
            value: 'c'
          },
          {
            text: '배송 전 연락 바랍니다.',
            value: 'd'
          },
          {
            text: '파손의 위험이 있는 상품입니다.',
            value: 'e'
          }
        ],
        products: [{
          shopProductNo: 199,
          shoPproductName: '딱딱의자',
          shopMainImg: "http://image.kyobobook.co.kr/newimages/giftshop_new/goods/400/1315/hot1568959544803.jpg",
          shopProductOpt: 'yellow/L',
          compName: '예담가구',
          businessEmail: 'yedam@mail.com',
          basketProductQty: 1,
          shopProductPrice: 20000,
          shopDeliveryCost: 3000,
        }],
      }
    },
    created() {
      console.log(selected.value)
      console.log(option.value)
    }
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
    padding: 10px;
    font-size: 13px;
  }

  #input-30 {
    font-size: 12px !important;
  }
  input{
    width:150px !important;
    height:30px !important;
  }
  input::placeholder{
    font-size: 12px;
  }
  /* 핸드폰번호 */
  #phoneNum {
    display:flex;
  }
  #phoneNum :first-child{
    width:60px !important;
    height:30px !important;
  }
  #phoneNum input{
    width:70px !important;
    height:30px !important;
    margin-right:10px;
  }
  /* 주소록 */
  #address input{
    width:350px !important;
    height:30px !important;
  }
</style>