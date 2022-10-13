<template>
  <div class="mx-auto pt-10" id="cart-con">
    <v-toolbar flat color="white">
      <v-toolbar-title>장바구니</v-toolbar-title>
      <v-spacer></v-spacer>
    </v-toolbar>
    <v-data-table v-model="selected" :headers="headers" :items="products" :single-select="singleSelect"
      item-key="cartNo" show-select style="text-align:center" class="elevation-1">
      <!-- 이미지 -->
      <template v-slot:item.productVO.proMainImg="{ item }">
        <v-img class="ma-5" width="150px" height="150px"
          :src="require(`../../assets/shop/productImg/${item.productVO.proMainImg}.jpg`)"></v-img>
      </template>
      <!-- 수량 조절 -->
      <template v-slot:item.cartQty="{ item }">
        <!-- minus -->
        <div>
          <v-btn class="mr-1" fab depressed width="20px" height="20px" color="#F7F7F7" @click="minusQty(item.cartNo)">
            <v-icon dark>
              mdi-minus
            </v-icon>
          </v-btn>
          {{ item.cartQty }}
          <!-- plus -->
          <v-btn class="ml-1" fab depressed width="20px" height="20px" color="#F7F7F7" @click="plusQty(item.cartNo)">
            <v-icon dark>
              mdi-plus
            </v-icon>
          </v-btn>
        </div>
      </template>
    </v-data-table>
    <!-- 삭제 총금액 -->
    <div style="display:flex">
      <div class="mr-auto pt-5">
        <v-btn depressed outlined color="#64c481" @click="deleteCart">
          삭제
        </v-btn>
      </div>
      <div class="ml-auto pa-5">
        총 주문금액 <span style="font-weight:bold">{{ countAmount }}</span>원
      </div>
    </div>
    <!-- 삭제 총금액 끝 -->
    <!-- 주문하기 -->
    <div class="mb-10" style="display:flex">
      <div class="mx-auto py-6">
        <v-btn depressed color=#B3E3C3 style="width:160px">
          주문하기
        </v-btn>
      </div>
    </div>
  </div>
</template>

<script>
  import axios from 'axios';

  export default {
    data() {
      return {
        singleSelect: false,
        selected: [],
        headers: [{
            text: '',
            align: 'start',
            sortable: false,
            value: 'rownum',
          },
          {
            text: '상품명',
            value: 'productVO.proName'
          },
          {
            text: '',
            value: 'productVO.proMainImg'
          },
          {
            text: '옵션',
            value: 'optName'
          },
          {
            text: '스토어',
            value: 'productVO.compName'
          },
          {
            text: '가격',
            value: 'cartPrice'
          },
          {
            text: '수량',
            value: 'cartQty'
          },
          {
            text: '배송비',
            value: 'productVO.deliveryCost'
          }
        ],
        products: []
      }
    },
    methods: {
      //선택한 품목 삭제
      deleteCart() {
        for (var i in this.selected) {
          for (var j in this.products) {
            if (this.products[j].cartNo == this.selected[i].cartNo) {
              this.products.splice(j, 1);
            }
          }
        }
        if (this.selected.length == 0) {
          alert('장바구니에서 삭제할 상품을 선택하세요.');
        }
      },
      //수량 감소
      minusQty(no) {
        for (var i in this.products) {
          if (this.products[i].cartNo == no) {
            if (this.products[i].cartQty > 1) {
              this.products[i].cartQty--;
            } else {
              //button disabled?
              alert('최소수량은 1개입니다.')
              break;
            }
          }
        }
      },
      //수량 증가
      plusQty(no) {
        for (var i in this.products) {
          if (this.products[i].cartNo == no) {
            if (this.products[i].cartQty > 9) {
              alert('최대수량은 10개입니다.')
              break;
            }
            this.products[i].cartQty++;
          }
        }
      },
    },
    created() {
      //전체 장바구니 조회
      axios({
        url: "http://localhost:8088/zippy/shop/myCartList",
        method: "GET",
        params: {
          email: this.$route.query.email
        }
      }).then(res => {
        console.log(res);
        this.products = res.data;
        console.log(this.products);
        this.selected = this.products.map((item) => item);
      }).catch(error => {
        console.log(error);
      })
    },
    computed: {
      //총 주문금액 계산
      countAmount() {
        let amount = 0;
        for (var i in this.selected) {
          amount += Number(this.selected[i].cartPrice) + Number(this.selected[i].productVO.deliveryCost);
        }
        return amount;
      }
    }
  };
</script>

<style>
  #cart-con {
    width: 70%;
  }

  .v-toolbar__title {
    font-weight: bold;
  }

  .v-application a {
    text-decoration: none;
    color: rgba(0, 0, 0, 0.87) !important;
  }

  .v-application a:hover {
    text-decoration: underline;
  }
  .v-btn {
    font-weight:bold;
  }
</style>