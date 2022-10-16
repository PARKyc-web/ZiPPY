<template>
  <div>
    <shop-subbar></shop-subbar>
    <order-complete></order-complete>
  </div>
</template>

<script>
import shopSubbar from "../../components/shop/shopSubbar.vue";
import orderComplete from '../../components/shop/orderComplete.vue';

export default {  
  components: {
    shopSubbar,
    orderComplete
  }
};
</script>