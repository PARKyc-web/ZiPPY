<template>
  <div class="mx-auto pt-10" id="cart-con">
    <v-toolbar flat color="white">
      <v-toolbar-title>상품리스트</v-toolbar-title>
      <v-spacer></v-spacer>
      <b-form-input id="input-small" size="sm" style="width:200px" placeholder="상품번호/상품명"></b-form-input></v-toolbar>
    <v-data-table v-model="selected" :headers="headers" :items="products" :single-select="singleSelect"
      item-key="proNo" show-select style="text-align:center" class="elevation-1">
      <!-- 상품명 -->
      <template v-slot:item.proName="{ item }" @click="goDetail(item.cartPno)">
        {{ item.proName }}
      </template>
      <!-- 이미지 -->
      <!--<template v-slot:item.proMainImg="{ item }">
        <v-img class="ma-5" width="150px" height="150px"
          :src="require(`../../assets/shop/productImg/${item.proMainImg}.jpg`)"
          @click="goDetail(item.cartPno)"></v-img>
      </template>-->
    </v-data-table>
    <div style="display:flex">
      <div class="mr-auto pt-5">
        <v-btn depressed outlined color="#64c481" @click="deleteCart">
          삭제
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
        select: [],
        headers: [{
            text: '',
            align: '상품명',
            sortable: false,
            value: 'proName',
          },
          {
            text: '',
            value: 'proMainImg'
          },
          {
            text: '상품번호',
            value: 'proNo'
          },
          {
            text: '옵션',
            value: 'optName'
          },
          {
            text: '관리',
            value: 'update'
          }
        ],
        products: []
      }
    },
    created() {
      //내 상품 조회
      axios({
        url: "/shop/myProList",
        method: "POST",
        data: {
          email: 'shop@mail.com'
        },
          method: "POST",
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