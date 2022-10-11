<template>
  <div>
    <shop-subbar></shop-subbar>
    <category-list></category-list>
  </div>
</template>

<script>
import shopSubbar from "../../components/shop/shopSubbar.vue";
import categoryList from "../../components/shop/categoryList.vue";

export default {
  components: {
    shopSubbar,
    categoryList
  }
};
</script>