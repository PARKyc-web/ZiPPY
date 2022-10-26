<template>
  <v-toolbar elevation="4" style="margin-bottom:5px">
    <div style="width: 75vw">
      <v-btn-toggle v-model="text" tile color="success lighten-1" group>
        <v-btn value="침실" @click="pushCategory($event)" class="nav-link active" aria-current="page">전체</v-btn>
        <v-btn value="수납" @click="pushCategory($event)" class="nav-link">침실</v-btn>
        <v-btn value="옷장/수납" @click="pushCategory($event)" class="nav-link">옷장/수납</v-btn>
        <v-btn value="주방" @click="pushCategory($event)" class="nav-link">주방</v-btn>
        <v-btn value="욕실" @click="pushCategory($event)" class="nav-link">욕실</v-btn>
        <v-btn value="서재" @click="pushCategory($event)" class="nav-link">서재</v-btn>
        <v-btn value="다용도실" @click="pushCategory($event)" class="nav-link">다용도실</v-btn>
      </v-btn-toggle>
    </div>
  </v-toolbar>
</template>

<script>
  export default {
    data: () => ({
      data: [],
      text : ""
    }),
    methods: {
      pushCategory: function (e) {
        this.$emit('click', e);
      }
    }
  }
</script>