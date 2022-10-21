<template>
  <div class="nav-scroller bg-body shadow-sm">
    <nav class="nav" aria-label="Secondary navigation">

      <v-btn-toggle v-model="text" tile color="success lighten-1" group>
        <v-btn value="견적받기" @click="pushCategory($event)" class="nav-link active" aria-current="page" href="#"> 견적받기</v-btn>
        <v-btn value="업체조회" @click="pushCategory($event)" class="nav-link" href="#">업체조회</v-btn>
        <v-btn value="업체평가하기" @click="pushCategory($event)" class="nav-link" href="#">업체평가하기</v-btn>
        <v-btn value="업체후기" @click="pushCategory($event)" class="nav-link" href="#">업체후기</v-btn>
        <v-btn value="커뮤니티" @click="pushCategory($event)" class="nav-link" href="#">커뮤니티</v-btn>
        <v-btn value="견적관리" @click="pushCategory($event)" class="nav-link" href="#">견적관리</v-btn>
        <v-btn value="예약관리" @click="pushCategory($event)" class="nav-link" href="#">예약관리</v-btn>
<!-- 
      <a @click="pushCategory($event)" class="nav-link active" aria-current="page" href="#">견적받기</a>
      <a @click="pushCategory($event)" class="nav-link" href="#">업체조회</a>
      <a @click="pushCategory($event)" class="nav-link" href="#">업체평가하기</a>
      <a @click="pushCategory($event)" class="nav-link" href="#">업체후기</a>
      <a @click="pushCategory($event)" class="nav-link" href="#">커뮤니티</a>
      <a @click="pushCategory($event)" class="nav-link" href="#">견적관리</a>
      <a @click="pushCategory($event)" class="nav-link" href="#">예약관리</a> -->
    
      </v-btn-toggle>
    </nav>
  </div>
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
      pushCategory: function (e) {
        this.$emit('click', e);
      }
    }
  }
</script>

<style scoped>
  .nav {
    width: 100%;
    z-index: 1000000000;
  }
  
</style>