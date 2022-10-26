<template>
  <div class="mx-auto pt-5" id="cart-con">
      <h3 style="margin:30px">장바구니</h3>
    <v-data-table v-model="selected" :headers="headers" :items="products" :single-select="singleSelect"
      item-key="cartNo" show-select style="text-align:center" class="elevation-1">
      <!-- 상품명 -->
      <template v-slot:item.productVO.proName="{ item }">
        <div @click="goDetail(item.cartPno)">
          {{ item.productVO.proName }}
        </div>
      </template>
      <!-- 이미지 -->
      <template v-slot:item.productVO.proMainImg="{ item }">
        <v-img class="ma-5" width="150px" height="150px"
          :src="require(`../../assets/shop/productImg/${item.productVO.proMainImg}.jpg`)"
          @click="goDetail(item.cartPno)"></v-img>
      </template>
      <!-- 상품가격 -->
      <template v-slot:item.cartPrice="{ item }">
        {{ item.cartPrice | comma }}
      </template>
      <!-- 수량 조절 -->
      <template v-slot:item.cartQty="{ item }">
        <!-- minus -->
        <div>
          <v-btn class="mr-1" fab depressed width="20px" height="20px" color="#fff" @click="minusQty(item.cartNo)">
            <v-icon dark>
              mdi-minus
            </v-icon>
          </v-btn>
          {{ item.cartQty }}
          <!-- plus -->
          <v-btn class="ml-1" fab depressed width="20px" height="20px" color="#fff" @click="plusQty(item.cartNo)">
            <v-icon dark>
              mdi-plus
            </v-icon>
          </v-btn>
        </div>
      </template>
      <!-- 배송비 -->
      <template v-slot:item.productVO.deliveryCost="{ item }">
        {{ item.productVO.deliveryCost | comma }}
      </template>
    </v-data-table>
    <!-- 삭제 총금액 -->
    <div style="display:flex">
      <div class="mr-auto pt-5">
        <v-btn depressed outlined color="#64c481" @click="deleteCart">
          삭제
        </v-btn>
      </div>
      <div class="ml-auto pa-5">
        <h6>총 주문금액 <span style="font-weight:bold">{{ countAmount | comma }}</span>원</h6>
      </div>
    </div>
    <!-- 삭제 총금액 끝 -->
    <!-- 주문하기 -->
    <div class="mb-10" style="display:flex">
      <div class="mx-auto py-6">
        <v-btn depressed color=#B3E3C3 style="width:160px" @click="goOrder">
          주문하기
        </v-btn>
      </div>
    </div>
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
        check: false,
        randNom: [],
        payCode: '',
        headers: [{
            text: '',
            align: '',
            sortable: false,
            value: 'productVO.proMainImg',
          },
          {
            text: '상품명',
            value: 'productVO.proName'
          },
          {
            text: '옵션',
            sortable: false,
            value: 'optName'
          },
          {
            text: '스토어',
            value: 'productVO.compName'
          },
          {
            text: '가격',
            value: 'cartPrice'
          },
          {
            text: '수량',
            value: 'cartQty'
          },
          {
            text: '배송비',
            value: 'productVO.deliveryCost'
          }
        ],
        products: []
      }
    },
    methods: {
      //주문
      goOrder() {
        //선택됐는지 체크
        if (this.selected.length == 0) {
          swal.fire({
            icon: 'warning',
            title: '선택된 상품이 존재하지 않습니다.',
            showConfirmButton: false,
            timer: 1500
          });
          return;
        }

        //payCode 생성
        this.makePayCode();

        //purchase 테이블에 등록
        axios({
          url: "/zippy/shop/insertPur",
          headers: {
            "Content-Type": "application/json"
          },
          method: "POST",
          data: JSON.stringify(this.selected),
          params: {
            payCode: this.payCode
          }
        }).then(res => {
          console.log(res);
          //성공시 삭제
          //장바구니에서 삭제 & 주문 alert 처리
          this.check = true
          this.deleteCart();

          //주문페이지 이동
          this.$router.push({
            name: 'order',
            query: {
              payCode: this.payCode
            }
          })

        }).catch(error => {
          console.log(error);
        })

      },
      //선택한 품목 삭제
      deleteCart() {
        //선택됐는지 체크
        if (this.selected.length == 0) {
          swal.fire({
            icon: 'warning',
            title: '선택된 상품이 존재하지 않습니다.',
            showConfirmButton: false,
            timer: 1500
          });
          return;
        }
        console.log(JSON.stringify(this.selected))
        //삭제
        axios({
          url: "/zippy/shop/delCart",
          headers: {
            "Content-Type": "application/json"
          },
          method: "POST",
          data: JSON.stringify(this.selected)
        }).then(res => {
          console.log(res);
        }).catch(error => {
          console.log(error);
        })

        //보는 것 삭제
        for (var i in this.selected) {
          for (var j in this.products) {
            if (this.products[j].cartNo == this.selected[i].cartNo) {
              this.products.splice(j, 1);
            }
          }
        }
        //삭제 alert
        this.checkOrder()

        //장바구니 개수
        this.$store.state.loginInfo.cartCount = Number(this.$store.state.loginInfo.cartCount) - this.selected.length;
      },
      //payCode 생성
      makePayCode() {
        var arr = new Uint32Array(10);
        this.randNum = window.crypto.getRandomValues(arr);
        this.payCode = this.randNum[0]+new Date().getTime();
      },
      //수량 감소
      minusQty(no) {
        for (var i in this.products) {
          if (this.products[i].cartNo == no) {
            if (this.products[i].cartQty > 1) {
              this.products[i].cartQty--;
            } else {
              //button disabled?
              swal.fire({
            icon: 'warning',
            title: '최소 수량은 1개입니다.',
            showConfirmButton: false,
            timer: 1500
          });
              break;
            }
          }
        }
      },
      //수량 증가
      plusQty(no) {
        for (var i in this.products) {
          if (this.products[i].cartNo == no) {
            if (this.products[i].cartQty > 9) {
              swal.fire({
            icon: 'warning',
            title: '최대 수량은 10개입니다.',
            showConfirmButton: false,
            timer: 1500
          });
              break;
            }
            this.products[i].cartQty++;
          }
        }
      },
      //디테일 페이지로 이동
      goDetail(no) {
        this.$router.push('/shop/detail?pno=' + no)
      },
      //삭제시에만 alert창 띄우기
      checkOrder() {
        if (!this.check) {
          swal.fire({
            icon: 'success',
            title: '삭제완료',
            showConfirmButton: false,
            timer: 1500
          });
        }
      }
    },
    created() {
      //전체 장바구니 조회
      axios({
        url: "/zippy/shop/myCartList",
        method: "POST",
        params: {
          email: this.$store.state.loginInfo.email
        }
      }).then(res => {
        console.log(res);
        this.products = res.data;
        console.log(this.products);
        this.selected = this.products.map((item) => item);
      }).catch(error => {
        console.log(error);
      })
    },
    computed: {
      //총 주문금액 계산
      countAmount() {
        var am = 0;
        for (var i in this.selected) {
          am += Number(this.selected[i].cartPrice) * this.selected[i].cartQty + Number(this.selected[i].productVO
            .deliveryCost);
        }
        //this.amount = am;
        return am;
      }
    },
    filters : {
      comma(val){
        return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ',')
      }
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

  .text-상품명 :hover{
    cursor: pointer;
  }

</style>