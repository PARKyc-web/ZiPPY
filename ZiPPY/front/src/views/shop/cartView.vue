<template>
  <div>
    <shop-subbar></shop-subbar>
    <cart></cart>
  </div>
</template>

<script>
import shopSubbar from "../../components/shop/shopSubbar.vue";
import cart from "../../components/shop/cart.vue";

export default {  
  components: {
    shopSubbar,
    cart
  }
};
</script>