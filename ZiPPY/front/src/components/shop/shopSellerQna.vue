<template>
  <div class="mx-auto pt-10" id="cart-con">
    <v-toolbar flat color="white">
      <v-toolbar-title>QNA</v-toolbar-title>
      <h6 class="mt-3 ml-5">답변대기 / 답변완료</h6>
      </v-toolbar>
    <v-data-table v-model="selected" :headers="headers" :items="products" :single-select="singleSelect"
      item-key="cartNo" show-select style="text-align:center" class="elevation-1">
      <!-- 상품명 -->
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
            align: '질문번호',
            sortable: false,
            value: 'productVO.proName',
          },
          {
            text: '고객id',
            value: 'productVO.proMainImg'
          },
          {
            text: '상품명',
            value: 'productVO.proNo'
          },
          {
            text: '내용',
            value: 'productVO.optName'
          },
          {
            text: '문의유형',
            value: 'update'
          },
          {
            text: '작성일',
            value: 'update'
          }
        ]
      }
    },
    methods: {
    },
    created() {
      //전체 qna 조회 // 수정해야함
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

<style scoped>
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