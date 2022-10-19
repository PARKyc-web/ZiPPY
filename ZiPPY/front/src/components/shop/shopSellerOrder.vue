<template>
  <div class="mx-auto pt-10" id="cart-con">
    <v-toolbar flat color="white">
      <v-toolbar-title>판매내역</v-toolbar-title>
      <v-spacer></v-spacer>
      <p name="all" class="pa-3">전체</p>/<p class="pa-3" name="pay">결제</p>
    </v-toolbar>

    <v-card>
      <v-card-title>
        <v-text-field @keyup="enterkey()" id="search" append-icon="mdi-magnify" label="주문번호/주문자" single-line
          hide-details>
        </v-text-field>
      </v-card-title>
      <v-data-table :headers="headers" :items="orders">
        <template v-slot:item.update="{ item }">
          <v-btn v-if="item.orderStatus==0" depressed color=#B3E3C3 class="mr-2"
            @click="updateStatus(item.orderNo, item.orderStatus)">
            배송
          </v-btn>
          <v-btn v-if="item.orderStatus==1" disabled depressed color=#D6D6D6 class="mr-2"
            @click="updateStatus(item.orderNo, item.orderStatus)">
            배송완료
          </v-btn>
        </template>
      </v-data-table>
    </v-card>

  </div>
</template>

<script>
  import axios from 'axios';
  import swal from 'sweetalert2';

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
            text: '주문자 이메일',
            value: 'email'
          },
          {
            text: '주문자 성함',
            value: 'buyerName'
          },
          {
            text: '주문상태',
            value: 'update'
          },
          {
            text: '결제완료일',
            value: 'orderDate'
          },
          {
            text: '배송시작일',
            value: 'deliveryDate'
          }
        ],
        orders: []
      }
    },
    methods: {
      //상품상태 0->1로 변경
      updateStatus(no, st) {
        let outside = this;
        if (st == 0) {
          swal.fire({
            icon: 'success',
            title: '해당 상품을 배송하시겠습니까?',
            showConfirmButton: true,
            showCancelButton: true
          }).then((result) => {
            if (result.isConfirmed) {
              //st 0->1로 변경
              st = 1;
              //db에 update
              outside.updateOrdStatus(no, st);
              //update된 내용 변경
              outside.changeStatus(no, st);
              //배송날짜 변경
              //outside.changeDeliveryDate(no)
            } else if (result.isDenied) {
              return;
            }
          });
        }
      },
      updateOrdStatus(no, st) {
        //상태 update
        axios({
          url: "/shop/updateOrdStatus",
          method: "POST",
          data: {
            orderNo: no,
            orderStatus: st
          },
          method: "POST",
        }).then(res => {
          console.log(res);
        }).catch(error => {
          console.log(error);
        })
      },
      //orderStatus의 상태 변경
      changeStatus(no, st) {
        for (var i in this.orders) {
          if (this.orders[i].orderNo == no)
            if (st == 1) {
              this.orders[i].orderStatus = 1;
            }
        }
      },
      //주문번호/고객 이메일 검색(조건조회)
      enterkey() {
        if (window.event.keyCode == 13) {
          var searchValue = document.querySelector("#search").value;
          //키워드 상품 조회
          axios({
            url: "/shop/myOrdList",
            method: "POST",
            data: {
              email: 'shop@mail.com'
            },
            params : {
              keyword : searchValue
            },
            method: "POST",
          }).then(res => {
            console.log(res);
            this.orders = res.data;
            console.log(this.orders);
          }).catch(error => {
            console.log(error);
          })
        }
      }
    },
    created() {
      //판매내역 조회
      axios({
        url: "/shop/myOrdList",
        method: "POST",
        data: {
          email: 'shop@mail.com'
        },
        method: "POST",
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

  .v-image :hover {
    cursor: pointer;
  }
</style>