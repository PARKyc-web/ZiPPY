<template>
  <div class="mx-auto pt-5" id="cart-con">
    <h3 style="margin:30px">구매내역</h3>

    <v-card>
      <v-data-table :headers="headers" :items="ords">
        <template v-slot:item.payCode="{ item }">
          <span @click="goOrderDetail(item.payCode)">
            {{item.payCode}}
          </span>
        </template>
        <!-- 이미지 -->
        <template v-slot:item.productVO[0].proMainImg="{ item }">
          <img class="ma-5" :src="'/zippy/common/img/shop/'+item.productVO[0].proMainImg" width="150" height="150">
        </template>
        <!-- 상품이름 -->
        <template v-slot:item.productVO[0].proName="{ item }">
            {{item.productVO[0].proName}}
            <div v-if="item.productVO.length>1">외 {{item.productVO.length-1}}건</div>
        </template>
      </v-data-table>
    </v-card>

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
            text: '주문번호',
            align: '',
            sortable: false,
            value: 'payCode',
          },
          {
            text: '',
            value: 'productVO[0].proMainImg'
          },
          {
            text: '상품명',
            value: 'productVO[0].proName'
          },
          {
            text: '결제완료일',
            value: 'orderDate'
          },
          {
            text: '총주문금액',
            value: 'amount'
          }
        ],
        ords: [],
        products: []
      }
    },
    methods: {
      goOrderDetail(payCode) {
        this.$router.push({
          name: 'shopOrderSheet',
          query: {
            payCode: payCode
          }
        })
      }
    },
    created() {
      //구매내역 조회
      axios({
        url: "/zippy/shop/myOrderList",
        method: "POST",
        params: {
          email: this.$store.state.loginInfo.email
        }
      }).then(res => {
        console.log(res);
        this.ords = res.data;
        console.log(this.ords);
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
  .v-btn {
    font-weight: bold;
  }

  span:hover{
    text-decoration: underline;
    cursor: pointer;
  }
  
</style>