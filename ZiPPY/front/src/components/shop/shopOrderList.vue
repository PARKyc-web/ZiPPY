<template>
  <div class="mx-auto pt-10" id="cart-con">
    <v-toolbar flat color="white">
      <v-toolbar-title>구매내역</v-toolbar-title>
    </v-toolbar>

    <v-card>
      <v-data-table :headers="headers" :items="orders">
        <template v-slot:item.payCode="{ item }">
          <div @click="goOrderDetail(item.payCode)" id="paycode">
            {{item.payCode}}
          </div>
        </template>
        <template v-slot:item.update="{ item }">
          <v-btn v-if="item.orderStatus==0" depressed color=#B3E3C3 class="mr-2"
            @click="updateStatus(item.orderNo, item.orderStatus)">
            배송
          </v-btn>
          <v-btn v-if="item.orderStatus==1" disabled depressed color=#D6D6D6 class="mr-2">
            배송완료
          </v-btn>
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
            text: '상품명',
            value: 'proName'
          },
          {
            text: '',
            value: 'proMainImg'
          },
          {
            text: '결제완료일',
            value: 'OrderDate'
          },
          {
            text: '총주문금액',
            value: 'amount'
          }
        ],
        orders: [],
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
      },
      getOrdProInfo(payCode) {
        axios({
        url: "/shop/myPurPro",
        method: "POST",
        params: {
          payCode: payCode
        }
      }).then(res => {
        console.log(res);
        this.products = res.data;
        console.log(this.products);
      }).catch(error => {
        console.log(error);
      })
      }
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
        this.orders = res.data;
        console.log(this.orders);
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

  .text-start :hover {
    text-decoration: underline;
    cursor: pointer;
  }
</style>