<template>
  <div class="mx-auto pt-10" id="cart-con">
    <v-toolbar flat color="white">
      <v-toolbar-title>상품리스트</v-toolbar-title>
    </v-toolbar>

    <v-card>
      <v-card-title>
        <v-text-field v-model="search" append-icon="mdi-magnify" label="상품명/상품번호" single-line hide-details>
        </v-text-field>
      </v-card-title>
      <v-data-table :headers="headers" :items="products" :search="search">
        <!-- 이미지 -->
        <!-- @click="goDetail(item.proNo)-->
        <!--<template v-slot:item.proMainImg="{ item }">
        <v-img class="ma-5" width="150px" height="150px"
          :src="require(`../../assets/shop/productImg/${item.proMainImg}.jpg`)"
          @click="goDetail(item.cartPno)"></v-img>
      </template>-->
        <!-- 수정 -->
        <template v-slot:item.update="{ item }">
          <v-btn depressed color=#B3E3C3 class="mr-2" @click="updateStatus(item.proNo)">
            {{proStatus(item.proNo)}}
          </v-btn>
          <v-btn depressed color=#B3E3C3>
            수정
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
            text: '상품번호',
            align: '',
            sortable: false,
            value: 'proNo',
          },
          {
            text: '상품이름',
            value: 'proName'
          },
          {
            text: '',
            value: 'proMainImg'
          },
          {
            text: '관리',
            value: 'update'
          }
        ],
        products: [],
        search: ''
      }
    },
    methods: {
      //상품상태 0->1로 변경
      updateStatus(no) {
        alert('판매를 중지하시겠습니까?');
        axios({
          url: "/shop/updateStatus",
          method: "POST",
          data: {
            proNo: no
          },
          method: "POST",
        }).then(res => {
          console.log(res);
        }).catch(error => {
          console.log(error);
        })
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
      }).catch(error => {
        console.log(error);
      })
    },
    computed: {
      proStatus(no) {
        for (var i in this.products) {
          if (this.products[i].proNo == no) {
            if (this.products[i].proStatus == 0) {
              return '판매중지'
            } else {
              return '판매가능'
            }
          }
        }
      }
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