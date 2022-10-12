<template>
  <div>
    <shop-subbar></shop-subbar>
    <shop-list></shop-list>
  </div>
</template>

<script>
import shopSubbar from "../../components/shop/shopSubbar.vue";
import shopList from "../../components/shop/shopList.vue";

export default { 
  components: {
    shopSubbar,
    shopList
  }
};
</script>