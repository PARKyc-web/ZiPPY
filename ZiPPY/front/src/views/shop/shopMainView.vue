<template>
  <div>
    <v-main>
    <shop-subbar></shop-subbar>
    <shopMain></shopMain>
  </v-main>    
  </div>
</template>

<script>
import shopSubbar from "../../components/shop/shopSubbar.vue";
import shopMain from "../../components/shop/shopMain.vue";

export default {  
  components: {
    //mainHeader,
    shopSubbar,
    shopMain
  }
};
</script>