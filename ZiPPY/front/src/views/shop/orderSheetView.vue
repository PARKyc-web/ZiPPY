<template>
  <div>
    <shop-subbar></shop-subbar>
    <order-sheet></order-sheet>
  </div>
</template>

<script>
import shopSubbar from "../../components/shop/shopSubbar.vue";
import orderSheet from "../../components/shop/orderSheet.vue";

export default {  
  components: {
    shopSubbar,
    orderSheet
  }
};
</script>