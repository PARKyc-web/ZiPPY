<template>
  <div class="mx-auto pt-10" id="cart-con">
    <v-toolbar flat color="white">
      <v-toolbar-title>장바구니</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn depressed color=#B3E3C3 @click="deleteProduct">
        삭제
      </v-btn>
    </v-toolbar>
    <v-data-table v-model="selected" :headers="headers" :items="products" :single-select="singleSelect"
      style="text-align:center" item-key="item.shopProductName" show-select class="elevation-1">
      <!-- 상품명 -->
      <template v-slot:item.shopProductName="{ item }">
        {{ item.shopProductName }}
      </template>
      <!-- 이미지 -->
      <template v-slot:item.shopMainImg="{ item }">
        <v-img class="ma-5" width="150px" height="150px"
          :src="require(`../../assets/shop/productImg/${item.shopMainImg}.jpg`)"></v-img>
      </template>
      <!-- 수량 조절 -->
      <template v-slot:item.basketProductQty="{ item }">
        <!-- minus -->
        <div>
          <v-btn class="mr-1" fab depressed width="20px" height="20px" color="#F7F7F7"
            @click="minusQty(item.shopProductNo)">
            <v-icon dark>
              mdi-minus
            </v-icon>
          </v-btn>
          {{ item.basketProductQty }}
          <!-- plus -->
          <v-btn class="ml-1" fab depressed width="20px" height="20px" color="#F7F7F7"
            @click="plusQty(item.shopProductNo)">
            <v-icon dark>
              mdi-plus
            </v-icon>
          </v-btn>
        </div>
      </template>
    </v-data-table>
    <div class="pa-5" style="float:right">
      총 주문금액 <span style="font-weight:bold">{{ countAmount }}</span>원
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
            text: '상품명',
            align: 'start',
            sortable: false,
            value: 'shopProductName',
          },
          {
            text: '',
            value: 'shopMainImg'
          },
          {
            text: '옵션',
            value: 'shopProductOptName'
          },
          {
            text: '스토어',
            value: 'compName'
          },
          {
            text: '가격',
            value: 'shopProductPrice' //computed로 계산 다시 해야될듯?
          },
          {
            text: '수량',
            value: 'basketProductQty'
          },
          {
            text: '배송비',
            value: 'shopDeliveryCost'
          }
        ],
        products: []
      }
    },
    methods: {
      //선택한 품목 삭제
      deleteProduct() {
        for (var i in this.selected) {
          for (var j in this.products) {
            if (this.products[j].shopProductNo == this.selected[i].shopProductNo) {
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
          if (this.products[i].shopProductNo == no) {
            if (this.products[i].basketProductQty > 1) {
              this.products[i].basketProductQty--;
            } else {
              //button disabled?
              alert('최소수량은 1개입니다.')
            }
          }
        }
      },
      //수량 증가
      plusQty(no) {
        for (var i in this.products) {
          if (this.products[i].shopProductNo == no) {
            if (this.products[i].basketProductQty > 9) {
              alert('최대수량은 10개입니다.')
              break;
            }
            this.products[i].basketProductQty++;
          }
        }
      },
    },
    created() {
      //전체조회
      axios({
          url: "http://localhost:8088/zippy/shop/basket",
          methods: "GET",
          params: {
            email: this.$route.query.email
          }
        }).then(res => {
          console.log(res);
          this.products = res.data;
          console.log(this.products);
        }).catch(error => {
          console.log(error);
        }),
        //옵션조회
        axios({
          url: "http://localhost:8088/zippy/shop/opt",
          methods: "GET",
          params: {
            no: 1
          }
        }).then(res => {
          console.log(res);
          this.opts = res.data;
          console.log(this.opts)
        }).catch(error => {
          console.log(error);
        })
    },
    computed: {
      //총 주문금액 계산
      countAmount() {
        let amount = 0;
        for (var i in this.products) {
          amount += this.products[i].shopProductPrice * this.products[i].basketProduct_qty;
          amount += this.products[i].shopDeliveryCost;
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
</style>