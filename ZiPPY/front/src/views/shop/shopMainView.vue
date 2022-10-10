<template>
  <div>
    <shop-subbar></shop-subbar>
    <shopMain></shopMain>
  </div>
</template>

<script>
import shopSubbar from "../../components/shop/shopSubbar.vue";
import shopMain from "../../components/shop/shopMain.vue";

export default {  
  components: {
    shopSubbar,
    shopMain
  }
};
</script>