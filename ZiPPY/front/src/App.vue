<template>  
  <v-app> 
    <mainHeader></mainHeader>
    <v-main>            
      <router-view />
    </v-main>
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

<style>

</style>