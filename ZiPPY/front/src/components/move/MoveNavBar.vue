<template>
  <v-toolbar flat style="border-bottom:1px solid #D6D6D6;">
    <div style="width: 75vw">
      <v-btn-toggle v-model="text" tile color="success lighten-1" group>
        <v-btn value="견적받기" @click="goEstimate()" class="nav-link active" aria-current="page"> 견적받기</v-btn>
        <v-btn value="업체조회" @click="goCompany()" class="nav-link">업체조회</v-btn>
        <v-btn value="마이페이지" @click="goManage()" class="nav-link">견적/예약관리</v-btn>
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
        
      },
      goCompany: function () {
        this.$router.push(({
          name: 'moveCompanyList',

        })).catch(() => {});;
       
      },
      goManage: function () {
        this.$router.push(({
          path: 'mypage/move/moveResult',

        })).catch(() => {});;
       
      },
    }
  }
</script>

<style scoped>
  .nav {
    width: 100%;
    z-index: 1000000000;
  }
  .v-btn{
    color:black;
  }
</style>