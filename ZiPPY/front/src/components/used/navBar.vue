<!-- <template>
  <v-app-bar color="#fff">
    <a @click="pushCategory($event)" class="nav-link active" aria-current="page" href="#">전체</a>
    <a @click="pushCategory($event)" class="nav-link" href="#">침실</a>
    <a @click="pushCategory($event)" class="nav-link" href="#">수납</a>
    <a @click="pushCategory($event)" class="nav-link" href="#">주방</a>
    <a @click="pushCategory($event)" class="nav-link" href="#">욕실</a>
    <a @click="pushCategory($event)" class="nav-link" href="#">서재</a>
    <a @click="pushCategory($event)" class="nav-link" href="#">다용도실</a>
  </v-app-bar>
</template> -->
<template>
  <nav class="py-2 border-bottom">
    <div class="container d-flex flex-wrap">
      <ul class="nav me-auto">
        <li class="nav-item">
          <a @click="pushCategory($event)" href="#" class="nav-link link-dark px-2 active" aria-current="page">침실</a>
        </li>
        <li class="nav-item">
          <a @click="pushCategory($event)" href="#" class="nav-link link-dark px-2">수납</a>
        </li>
        <li class="nav-item">
          <a @click="pushCategory($event)" href="#" class="nav-link link-dark px-2">주방</a>
        </li>
        <li class="nav-item">
          <a @click="pushCategory($event)" href="#" class="nav-link link-dark px-2">욕실</a>
        </li>
        <li class="nav-item">
          <a @click="pushCategory($event)" href="#" class="nav-link link-dark px-2">서재</a>
        </li>
        <li class="nav-item">
          <a @click="pushCategory($event)" href="#" class="nav-link link-dark px-2">다용도실</a>
        </li>
      </ul>
    </div>
  </nav>
</template>

<script>
  export default {
    data: () => ({
      data: [],
      cate: [
        '침실',
        '수납',
        '주방',
        '욕실',
        '서재',
        '다용도실',
      ]
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
  }

  .nav-item{
    margin-right: 10px;
  }
  .nav-item:hover{
    background-color: #b3e3c3;
    border-radius: 20%;
    
  }
</style>