<template>
  <div class="mx-auto pt-10" id="cart-con">
    <v-toolbar flat color="white">
      <v-toolbar-title>판매내역</v-toolbar-title>
      <h6 class="mt-3 ml-5">전체 / 결제</h6>
      <v-spacer></v-spacer>
      <b-form-input id="input-small" size="sm" style="width:200px" placeholder="상품번호/상품명"></b-form-input></v-toolbar>
    <v-data-table v-model="selected" :headers="headers" :items="products" :single-select="singleSelect"
      item-key="cartNo" show-select style="text-align:center" class="elevation-1">
    </v-data-table>
  </div>
</template>

<script>
  import axios from 'axios';

  export default {
    data() {
      return {
        singleSelect: false,
        selected: [],
        select: [],
        headers: [{
            text: '',
            align: '주문번호',
            sortable: false,
            value: 'productVO.proName',
          },
          {
            text: '고객id',
            value: 'productVO.proMainImg'
          },
          {
            text: '주문상태',
            value: 'productVO.proNo'
          },
          {
            text: '결제완료일',
            value: 'productVO.optName'
          },
          {
            text: '배송시작일',
            value: 'update'
          }
        ],
        products: []
      }
    },
    methods: {
    },
    created() {
      //전체 장바구니 조회
      axios({
        url: "/shop/myCartList",
        method: "POST",
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
    font-weight: bold;
  }

  .v-image :hover {
    cursor: pointer;
  }
</style>