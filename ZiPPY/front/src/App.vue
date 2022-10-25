<template>  
  <v-app> 
    <mainHeader></mainHeader>
    <div id="body">
      <router-view :key="$route.fullPath" />
    </div>
  </v-app>
</template>

<script>
import mainHeader from '@/components/mainHeader.vue';

export default {
  name: "App",
  components : {
    mainHeader
  },
  data: () => ({
    //
  }),
};
</script>

<style scoped>
@import url(//spoqa.github.io/spoqa-han-sans/css/SpoqaHanSansNeo.css);

* {
  font-family: 'Spoqa Han Sans Neo', 'sans-serif';
}
</style>