<template>
  <div class="mx-auto pt-5" id="cart-con">
    <h3 style="margin:30px">상품목록</h3>
    <v-card>
      <v-card-title>
        <v-text-field @keyup="enterkey()" id="search" append-icon="mdi-magnify" label="상품명/상품번호" single-line
          hide-details>
        </v-text-field>
      </v-card-title>
      <v-data-table :headers="headers" :items="products">
        <!-- 이미지 -->
        <template v-slot:item.proMainImg="{ item }">
          <img class="ma-5" :src="'/zippy/common/img/shop/'+item.proMainImg" width="150" height="150">
        </template>
        <!-- 판매버튼 -->
        <template v-slot:item.update="{ item }">
          <v-btn v-if="item.proStatus==0" depressed color=#B3E3C3 class="mr-2"
            @click="updateStatus(item.proNo, item.proStatus)">
            판매중지
          </v-btn>
          <v-btn v-if="item.proStatus==1" depressed color=#D6D6D6 class="mr-2"
            @click="updateStatus(item.proNo, item.proStatus)">
            판매가능
          </v-btn>
          <v-btn depressed outlined color=#64c481 @click="updateProInfo(item.proNo)">
            수정
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
            text: '상품번호',
            align: '',
            sortable: false,
            value: 'proNo',
          },
          {
            text: '',
            value: 'proMainImg'
          },
          {
            text: '상품이름',
            value: 'proName'
          },
          {
            text: '카테고리',
            value: 'category'
          },
          {
            text: '관리',
            value: 'update'

          }
        ],
        products: []
      }
    },
    methods: {
      //상품상태 0->1로 변경
      updateStatus(no, st) {
        let outside = this;
        if (st == 0) {
          swal.fire({
            icon: 'success',
            title: '해당 상품을 판매중지하시겠습니까?',
            showConfirmButton: true,
            showCancelButton: true
          }).then((result) => {
            if (result.isConfirmed) {
              //st 0->1로 변경
              st = 1;
              //db에 update
              outside.updateProStatus(no, st);
              //update된 내용 변경
              outside.changeStatus(no, st);
            } else if (result.isDenied) {
              return;
            }
          });
        } else {
          swal.fire({
            icon: 'success',
            title: '해당 상품을 다시 판매하시겠습니까?',
            showConfirmButton: true,
            showCancelButton: true
          }).then((result) => {
            if (result.isConfirmed) {
              //st 1->0으로 변경
              st = 0;
              //db에 update
              outside.updateProStatus(no, st);
              //update된 내용 변경
              outside.changeStatus(no, st);
            } else if (result.isDenied) {
              return;
            }
          });
        }
      },
      updateProStatus(no, st) {
        //상태 update
        axios({
          url: "/zippy/shop/updateStatus",
          method: "POST",
          data: {
            proNo: no,
            proStatus: st
          },
          method: "POST",
        }).then(res => {
          console.log(res);
        }).catch(error => {
          console.log(error);
        })
      },
      //proStatus의 상태 변경
      changeStatus(no, st) {
        for (var i in this.products) {
          if (this.products[i].proNo == no)
            if (st == 0) {
              this.products[i].proStatus = 0;
            } else {
              this.products[i].proStatus = 1;
            }
        }
      },
      //수정 페이지로 이동
      updateProInfo(no) {
        this.$router.push({
          name: 'shopUpdatePro',
          query: {
            proNo: no
          }
        })
      },
      //상품명/상품번호 검색(조건조회)
      enterkey() {
        var searchValue = document.querySelector("#search").value;
        if (window.event.keyCode == 13) {
          //키워드 상품 조회
          axios({
            url: "/zippy/shop/myProList",
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
            this.products = res.data;
            console.log(this.products);
          }).catch(error => {
            console.log(error);
          })
        }
      }
    },
    created() {
      //내 상품 조회
      axios({
        url: "/zippy/shop/myProList",
        method: "POST",
        data: {
          email: this.$store.state.loginInfo.email
        },
        method: "POST",
      }).then(res => {
        console.log(res);
        this.products = res.data;
        console.log(this.products);
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