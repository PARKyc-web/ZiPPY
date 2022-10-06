<template>
  <div>
    <shopSubBar></shopSubBar>
    <cart></cart>
  </div>
</template>

<script>
import shopSubBar from "../../components/shop/shopSubBar.vue";
import cart from "../../components/shop/cart.vue";

export default {  
  components: {
    shopSubBar,
    cart
  }
};
</script>