<template>
  <div>
    <shop-subbar></shop-subbar>
    <shop-wish></shop-wish>
  </div>
</template>

<script>
import shopSubbar from "../../components/shop/shopSubbar.vue";
import shopWish from '../../components/shop/shopWish.vue';

export default {  
  components: {
    shopSubbar,
    shopWish
  }
};
</script>