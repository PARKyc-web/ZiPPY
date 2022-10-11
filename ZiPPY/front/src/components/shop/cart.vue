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
      style="text-align:center"
      item-key="shop_product_name" show-select class="elevation-1">
      <!-- 상품명 -->
      <template v-slot:item.shop_product_name="{ item }">
        <a href="https://naver.com">
          {{ item.shop_product_name }}
        </a>
      </template>
      <!-- 이미지 -->
      <template v-slot:item.shop_main_img="{ item }">
        <a href="https://naver.com">
          <v-img class="ma-5" width="150px" height="150px" :src="item.shop_main_img"></v-img>
        </a>
      </template>
      <!-- 수량 조절 -->
      <template v-slot:item.basket_product_qty="{ item }">
        <!-- minus -->
        <div>
          <v-btn class="mr-1" fab depressed width="20px" height="20px" color="#F7F7F7"
            @click="minusQty(item.shop_product_no)">
            <v-icon dark>
              mdi-minus
            </v-icon>
          </v-btn>
          {{ item.basket_product_qty }}
          <!-- plus -->
          <v-btn class="ml-1" fab depressed width="20px" height="20px" color="#F7F7F7"
            @click="plusQty(item.shop_product_no)">
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
  export default {
    data() {
      return {
        singleSelect: false,
        selected: [],
        headers: [{
            text: '상품명',
            align: 'start',
            sortable: false,
            value: 'shop_product_name',
          },
          {
            text: '',
            value: 'shop_main_img'
          },
          {
            text: '옵션',
            value: 'shop_product_opt'
          },
          {
            text: '스토어',
            value: 'comp_name'
          },
          {
            text: '가격',
            value: 'shop_product_price'
          },
          {
            text: '수량',
            value: 'basket_product_qty'
          },
          {
            text: '배송비',
            value: 'shop_delivery_cost'
          }
        ],
        products: [{
            shop_product_no: 199,
            shop_product_name: '딱딱의자',
            shop_main_img: "http://image.kyobobook.co.kr/newimages/giftshop_new/goods/400/1315/hot1568959544803.jpg",
            shop_product_opt: 'yellow/L',
            comp_name: '예담가구',
            business_email: 'yedam@mail.com',
            basket_product_qty: 1,
            shop_product_price: 20000,
            shop_delivery_cost: 3000,
          },
          {
            shop_product_no: 200,
            shop_product_name: '말랑의자',
            shop_main_img: 'https://blog.kakaocdn.net/dn/c0nsol/btqXrCOZ6J9/XLlGPEHQoIiwwIClQTkVPk/img.png',
            shop_product_opt: 'white/M',
            comp_name: '예담가구',
            business_email: 'yedam@mail.com',
            basket_product_qty: 2,
            shop_product_price: 10000,
            shop_delivery_cost: 3000,
          },
          {
            shop_product_no: 201,
            shop_product_name: '푹신푹신의자',
            shop_main_img: 'https://tumblbug-pci.imgix.net/160b52664f63ed8512c774ea1a2a0aad7bb2fd37/677affdc260fe9bcd1aa08982c618bb08bbd8334/46d68a3157b755a7c47128402ab50725d976e7d6/fc47e6b6-09b3-439e-adb3-d8f81337154a.png?ixlib=rb-1.1.0&w=1240&h=930&auto=format%2Ccompress&lossless=true&fit=crop&s=c7b532a47ba23a622dc621106416bcb9',
            shop_product_opt: 'dark/L',
            comp_name: '지피가구',
            business_email: 'zippy@mail.com',
            basket_product_qty: 2,
            shop_product_price: 10000,
            shop_delivery_cost: 3000,
          }
        ],
      }
    },
    methods: {
      //선택한 품목 삭제
      deleteProduct() {
        for (var i in this.selected) {
          for (var j in this.products) {
            if (this.products[j].shop_product_no == this.selected[i].shop_product_no) {
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
          if (this.products[i].shop_product_no == no) {
            if (this.products[i].basket_product_qty > 1) {
              this.products[i].basket_product_qty--;
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
          if (this.products[i].shop_product_no == no) {
            if (this.products[i].basket_product_qty > 9) {
              alert('최대수량은 10개입니다.')
              break;
            }
            this.products[i].basket_product_qty++;
          }
        }
      },
    },
    computed: {
      //총 주문금액 계산
      countAmount() {
        let amount = 0;
        for (var i in this.products) {
          amount += this.products[i].shop_product_price * this.products[i].basket_product_qty;
          amount += this.products[i].shop_delivery_cost;
        }
        return amount;
      },
      //배송비 계산
      // countDelCost() {
      //   for (var i in this.products) {
      //   var j=i+1;
      //   for (var j in this.products) {
      //     if(this.products[i].business_email == this.products[j].business_email)
      //     this.products[j].shop_delivery_cost = 0;
      //   }
      //   return;
      // }
      // }
    },
  };
</script>

<style>
  #cart-con {
    width: 70%;
  }
  .v-toolbar__title {
    font-weight: bold;
  }
  .v-application a{
    text-decoration: none;
    color: rgba(0, 0, 0, 0.87) !important;
  }
  .v-application a:hover{
    text-decoration: underline;
  }
</style>