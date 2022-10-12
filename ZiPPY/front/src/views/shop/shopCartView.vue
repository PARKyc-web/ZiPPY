<template>
  <div>
    <shop-subbar></shop-subbar>
    <shop-cart></shop-cart>
  </div>
</template>

<script>
import shopSubbar from "../../components/shop/shopSubbar.vue";
import shopCart from "../../components/shop/shopCart.vue";

export default {  
  components: {
    shopSubbar,
    shopCart
  }
};
</script>