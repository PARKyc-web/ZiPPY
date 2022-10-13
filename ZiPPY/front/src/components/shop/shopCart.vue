<template>
  <div class="mx-auto pt-10" id="cart-con">
    <v-toolbar flat color="white">
      <v-toolbar-title>장바구니</v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn depressed outlined color="#64c481" @click="deleteCart">
        삭제
      </v-btn>
    </v-toolbar>
    <v-data-table v-model="selected" :headers="headers" :items="cartProInfos" :single-select="singleSelect"
      item-key="cartNo" show-select style="text-align:center" class="elevation-1">
      <!-- 상품명 -->
      <template v-slot:item.proName="{ item }">
        {{ item.proName }}
      </template>
      <!-- 이미지 -->
      <template v-slot:item.proMainImg="{ item }">
        <v-img class="ma-5" width="150px" height="150px"
          :src="require(`../../assets/shop/productImg/${item.proMainImg}.jpg`)"></v-img>
      </template>
      <!-- 수량 조절 -->
      <template v-slot:item.cartQty="{ item }">
        <!-- minus -->
        <div>
          <v-btn class="mr-1" fab depressed width="20px" height="20px" color="#F7F7F7" @click="minusQty(item.proNo)">
            <v-icon dark>
              mdi-minus
            </v-icon>
          </v-btn>
          {{ item.cartQty }}
          <!-- plus -->
          <v-btn class="ml-1" fab depressed width="20px" height="20px" color="#F7F7F7" @click="plusQty(item.proNo)">
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
            text: '',
            align: 'start',
            sortable: false,
            value: 'cartNo',
          },
          {
            text: '상품명',
            value: 'proName'
          },
          {
            text: '',
            value: 'proMainImg'
          },
          {
            text: '옵션',
            value: 'optName'
          },
          {
            text: '스토어',
            value: 'compName'
          },
          {
            text: '가격',
            value: 'proPrice' //computed로 계산 다시 해야될듯?
          },
          {
            text: '수량',
            value: 'cartQty'
          },
          {
            text: '배송비',
            value: 'deliveryCost'
          }
        ],
        products: [],
        cartProInfos: []
      }
    },
    methods: {
      //선택한 품목 삭제
      deleteCart() {
        for (var i in this.selected) {
          for (var j in this.cartPros) {
            if (this.cartPros[j].proNo == this.selected[i].proNo) {
              this.cartPros.splice(j, 1);
            }
          }
        }
        if (this.selected.length == 0) {
          alert('장바구니에서 삭제할 상품을 선택하세요.');
        }
      },
      //수량 감소
      minusQty(no) {
        for (var i in this.cartPros) {
          if (this.cartPros[i].proNo == no) {
            if (this.cartPros[i].cartQty > 1) {
              this.cartPros[i].cartQty--;
            } else {
              //button disabled?
              alert('최소수량은 1개입니다.')
            }
          }
        }
      },
      //수량 증가
      plusQty(no) {
        for (var i in this.cartPros) {
          if (this.cartPros[i].proNo == no) {
            if (this.cartPros[i].cartQty > 9) {
              alert('최대수량은 10개입니다.')
              break;
            }
            this.cartPros[i].cartQty++;
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
        this.products = res.data;
      }).catch(error => {
        console.log(error);
      })
      
      for(var i in this.products){
        console.log('이거 돔?'+i)
          var cartProInfo = {
            cartNo : this.products[i].cartNo,
            proName : this.products[i].productVO.proName,
            proMainImg : this.products[i].productVO.proMainImg,
            optName : this.products[i].option.optName,
            compName : this.products[i].productVO.compName,
            proPrice : this.products[i].productVO.proPrice + this.products[i].option.optPrice,
            cartQty : this.this.products[i].cartQty,
            deliveryCost : this.products[i].productVO.deliveryCost,
          }
          console.log('이거 돔?'+i)
          this.cartProInfos[i].push(cartProInfo)
        }
    },
    computed: {
      //총 주문금액 계산
      countAmount() {
        let amount = 0;
        for (var i in this.cartPros) {
          amount += this.cartPros[i].proPrice * this.cartPros[i].cartQty;
          amount += this.cartPros[i].deliveryCost;
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