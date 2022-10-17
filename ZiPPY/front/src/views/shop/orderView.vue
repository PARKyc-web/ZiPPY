<template>
  <div>
    <shop-subbar></shop-subbar>
    <order></order>
  </div>
</template>

<script>
import shopSubbar from "../../components/shop/shopSubbar.vue";
import order from "../../components/shop/order.vue";

export default {  
  components: {
    shopSubbar,
    order
  }
};
</script>