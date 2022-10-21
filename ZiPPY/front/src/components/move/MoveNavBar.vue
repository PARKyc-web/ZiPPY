<template>
  <v-toolbar elevation="4" style="margin-bottom:5px">
    <div style="width: 75vw">
      <v-btn-toggle v-model="text" tile color="success lighten-1" group>
        <v-btn value="견적받기" @click="goEstimate()" class="nav-link active" aria-current="page"> 견적받기</v-btn>
        <v-btn value="업체조회" @click="goCompany()" class="nav-link">업체조회</v-btn>
        <v-btn value="업체후기" @click="goReview()" class="nav-link">업체후기</v-btn>
        <v-btn value="커뮤니티" @click="goBoard()" class="nav-link">커뮤니티</v-btn>
        <v-btn value="마이페이지" @click="goManage()" class="nav-link">마이페이지</v-btn>
      </v-btn-toggle>
    </div>
  </v-toolbar>
</template>

<script>
  export default {
    data: () => ({
      data: [],
      //toggle
      text: 'center',
      icon: 'justify',
      toggle_none: null,
      toggle_one: 0,
      toggle_exclusive: 2,
      toggle_multiple: [0, 1, 2],
    }),
    methods: {
      // pushCategory: function (e) {
      //   this.$emit('click', e);
      // }
      goEstimate: function () {
        this.$router.push(({
          name: 'moveTotal',

        })).catch(() => {});;
        this.$router.go(0);
      },
      goCompany: function () {
        this.$router.push(({
          name: 'moveEstimate',

        })).catch(() => {});;
        this.$router.go(0);
      },
      goReview: function () {
        this.$router.push(({
          name: 'moveMyList',

        })).catch(() => {});;
        this.$router.go(0);
      },
      goBoard: function () {
        this.$router.push(({
          name: 'moveResult',

        })).catch(() => {});;
        this.$router.go(0);
      },
      goManage: function () {
        this.$router.push(({
          name: 'moveMyReserve',

        })).catch(() => {});;
        this.$router.go(0);
      },
    }
  }
</script>

<style scoped>
  .nav {
    width: 100%;
    z-index: 1000000000;
  }
</style>