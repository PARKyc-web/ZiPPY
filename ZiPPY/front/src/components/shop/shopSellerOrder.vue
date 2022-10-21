<template>
  <div class="mx-auto pt-10" id="cart-con">
    <v-toolbar flat color="white">
      <v-toolbar-title>판매내역</v-toolbar-title>
    </v-toolbar>

    <v-card>
      <v-card-title>
        <v-text-field @keyup="enterkey()" id="searchPur" append-icon="mdi-magnify" label="주문번호/주문자" single-line
          hide-details>
        </v-text-field>
      </v-card-title>
      <v-data-table :headers="headers" :items="purs">
        <template v-slot:item.payCode="{ item }">
          <div @click="goOrderDetail(item.purNo, item.payCode)" id="paycode">
            {{item.payCode}}
          </div>
        </template>
        <template v-slot:item.update="{ item }">
          <v-btn v-if="item.orderStatus==0" depressed color=#B3E3C3 class="mr-2"
            @click="updateStatus(item.purNo, item.orderStatus)">
            발송
          </v-btn>
          <v-btn v-if="item.orderStatus==1" disabled depressed color=#D6D6D6 class="mr-2">
            발송완료
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
            text: '상품명',
            value: 'proName'
          },
          {
            text: '주문자 이메일',
            value: 'email'
          },
          {
            text: '주문자',
            value: 'userName'
          },
          {
            text: '주문상태',
            value: 'update'
          },
          {
            text: '주문일',
            value: 'orderDate'
          },
          {
            text: ' 발송일',
            value: 'deliveryDate'
          }
        ],
        purs: []
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
              st = 1;
              //db에 update
              outside.updateOrdStatus(no, st);
              //update된 상태 변경
              outside.changeStatus(no, st);
              //update된 날짜 변경
              outside.changeDeldate(no);
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
            purNo: no,
            orderStatus: st
          },
          method: "POST",
        }).then(res => {
          console.log(res);
        }).catch(error => {
          console.log(error);
        })
      },
      //purstatus의 상태 변경
      changeStatus(no, st) {
        for (var i in this.purs) {
          if (this.purs[i].purNo == no)
            if (st == 1) {
              this.purs[i].orderStatus = 1;
            }
        }
      },
      changeDeldate(no){
        var today = new Date();
        var year = String(today.getFullYear());
        var yy = year.substr(2,4);
        var month = ('0' + (today.getMonth() + 1)).slice(-2);
        var day = ('0' + today.getDate()).slice(-2);
        var dateString = yy + '-' + month  + '-' + day;
        for (var i in this.purs) {
          if (this.purs[i].purNo == no)
              this.purs[i].deliveryDate = dateString;
        }
      },
      //주문번호/고객 이메일 검색(조건조회)
      enterkey() {
        var searchValue = document.querySelector("#searchPur").value;
        if (window.event.keyCode == 13) {
          //키워드 상품 조회
          axios({
            url: "/shop/sellerPurList",
            method: "POST",
            data: {
              email: this.$store.state.loginInfo.email
            },
            params: {
              keyword: searchValue
            },
            method: "POST",
          }).then(res => {
            console.log(res);
            this.purs = res.data;
            console.log(this.purs);
          }).catch(error => {
            console.log(error);
          })
        }
      },
      goOrderDetail(purNo, payCode){
        this.$router.push({
          name: 'shopOrderSheet',
          query: {
            purNo : purNo,
            payCode: payCode
          }
        })
      }
    },
    created() {
      //판매내역 조회
      axios({
        url: "/shop/sellerPurList",
        method: "POST",
        data: {
          email: this.$store.state.loginInfo.email
        },
        method: "POST",
      }).then(res => {
        console.log(res);
        this.purs = res.data;
        console.log(this.purs);
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
  .text-start :hover{
    text-decoration: underline;
    cursor: pointer;
  }
</style>