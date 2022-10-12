<template>
  <div class="pb-1">
    <v-app-bar color="#fff">
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
      <v-badge :value="hover" color="#B3E3C3" content="10" left transition="slide-x-transition">
        <v-hover v-model="hover">
          <v-icon color="rgba(0, 0, 0, 0.54)">
            mdi-cart
          </v-icon>
        </v-hover>
      </v-badge>
      <!-- 장바구니-->
    </v-app-bar>
  </div>
</template>

<script>
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
      hover: false,
    }),
    methods: {
      // 수정전
      // goList(cate) {
      //   this.$router.push('/shop/category?cate=' + cate);
      //   this.$router.go(0);
      // },
      // enterkey: function () {
      //   var searchValue = document.querySelector("#search").value;
      //   if (window.event.keyCode == 13) {
      //     this.$router.push('/shop/keyword?keyw=' + searchValue);
      //     this.$router.go(0);
      //   }
      // }
      goList(cate) {
        this.$router.push(({
          name: 'shopList',
          query: {cate: cate}
        })).catch(()=>{});;
        this.$router.go(0);
      },
      enterkey: function () {
        var searchValue = document.querySelector("#search").value;
        if (window.event.keyCode == 13) {
          this.$router.push(({
          name: 'shopList',
          query: {keyw: searchValue}
        }));
        this.$router.go(0);
        }
      }
    }
  }
</script>

<style scoped>
</style>