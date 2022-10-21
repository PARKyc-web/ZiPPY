<template>
  <div class="nav-scroller bg-body shadow-sm">
    <nav class="nav" aria-label="Secondary navigation">
      <a @click="pushCategory($event)" class="nav-link active" aria-current="page" href="#">전체</a>
      <a @click="pushCategory($event)" class="nav-link" href="#">침실</a>
      <a @click="pushCategory($event)" class="nav-link" href="#">수납</a>
      <a @click="pushCategory($event)" class="nav-link" href="#">주방</a>
      <a @click="pushCategory($event)" class="nav-link" href="#">욕실</a>
      <a @click="pushCategory($event)" class="nav-link" href="#">서재</a>
      <a @click="pushCategory($event)" class="nav-link" href="#">다용도실</a>
    </nav>
  </div>
</template>

<script>
  export default {
    data: () => ({
      data: []
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
</style>