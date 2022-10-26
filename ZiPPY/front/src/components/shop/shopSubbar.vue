<template> 
  <div class="pb-1">
    <v-app-bar flat color="#fff" style="border-bottom:1px solid #D6D6D6">
      <!-- 카테고리 -->
      <v-menu left bottom>
        <template v-slot:activator="{ on, attrs }">
          <v-btn icon v-bind="attrs" v-on="on">
            <v-app-bar-nav-icon></v-app-bar-nav-icon>
          </v-btn>
        </template>

        <v-list>
          <v-list-item v-for="cate in categories" :key="cate" @click="goList(cate)" font-size="0.8em">
            <v-list-item-title>
              {{ cate }}
            </v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>
      <v-spacer></v-spacer>
      <!--검색창-->
      <v-icon>mdi-magnify</v-icon>
      <v-form onsubmit="return false">
        <v-container class="mt-3">
          <v-text-field @keyup="enterkey()" color="#B3E3C3" id="search"></v-text-field>
        </v-container>
      </v-form>
      <!-- 검색창 끝 -->
      <!-- 장바구니 -->
      <!---<v-badge :value="hover" color="#B3E3C3" :content="this.$store.state.loginInfo.cartCount" left transition="slide-x-transition">
        <v-hover v-model="hover">-->
          <v-icon color="rgba(0, 0, 0, 0.54)" @click="goCart" v-if="this.$store.state.loginInfo">
            mdi-cart
          </v-icon>
        <!--</v-hover>
      </v-badge>-->
      <!-- 장바구니-->
    </v-app-bar>
  </div>
</template>

<script>
import axios from 'axios';

  export default {
    data: () => ({
      categories: [
        '침대',
        '토퍼/매트리스',
        '소파',
        '테이블/식탁/책상',
        '서랍/수납장',
        '책장',
        '의자',
        '거울',
        '조명',
        '소품'
      ],
      hover: false
    }),
    methods: {
      //카테고리 이동
      goList(cate) {
        this.$router.push({
          name: 'shopList',
          query: {
            cate: cate
          }
        })
      },
      //키워드 검색
      enterkey: function () {
        var searchValue = document.querySelector("#search").value;
        if (window.event.keyCode == 13) {
          this.$router.push(({
            name: 'shopList',
            query: {
              keyw: searchValue
            }
          })).catch(() => {});
        }
      },
      //장바구니 이동
      goCart() {
        this.$router.push({
          name: 'shopCart',
          query: {
            email: this.$store.state.loginInfo.email
          }
        })
      },
      getProductList() {

      }
    },
    //카트 개수 조회
    // created() {
    //   axios({
    //       url: "/shop/myCart",
    //       method: "GET",
    //       params: {
    //         email: this.$store.state.loginInfo.email
    //       }
    //     }).then(res => {
    //       console.log(res);
    //       this.$store.state.loginInfo.cartCount = res.data;
    //       console.log(this.count);
    //     }).catch(error => {
    //       console.log(error);
    //     })
    // }
  }
</script>

<style scoped>
</style>