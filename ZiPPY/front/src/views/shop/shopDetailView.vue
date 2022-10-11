<template>
    <div>
    <shop-subbar></shop-subbar>
    <shop-detail></shop-detail>
  </div>
</template>

<script>
import shopSubbar from "../../components/shop/shopSubbar.vue";
import shopDetail from "../../components/shop/shopDetail.vue";

export default {  
  components: {
    shopSubbar,
    shopDetail
  }
};
</script>
