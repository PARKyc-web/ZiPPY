<template>
  <div class="mx-auto pt-10" id="cart-con">
    <v-toolbar flat color="white">
      <v-toolbar-title>구매내역</v-toolbar-title>
    </v-toolbar>

    <v-card>
      <v-data-table :headers="headers" :items="ords">
        <template v-slot:item.payCode="{ item }">
          <span @click="goOrderDetail(item.payCode)">
            {{item.payCode}}
          </span>
        </template>
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
      // },
      // getOrdProInfo(payCode) {
      //   axios({
      //   url: "/shop/myPurPro",
      //   method: "POST",
      //   params: {
      //     payCode: payCode
      //   }
      // }).then(res => {
      //   console.log(res);
      //   this.products = res.data;
      //   console.log(this.products);
      // }).catch(error => {
      //   console.log(error);
      // })
      // }
    },
    created() {
      //구매내역 조회
      axios({
        url: "/shop/myOrderList",
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