<template>
  <div>
    <shopMain></shopMain>
    <shop-subbar></shop-subbar>
    <shop-detail></shop-detail>
  </div>
</template>

<script>
  import shopMain from "@/components/shop/shopMain.vue";
  import shopSubbar from "@/components/shop/shopSubbar.vue";
  import categoryList from "@/components/shop/categoryList.vue";
  import keywordList from "@/components/shop/keywordList.vue";
  import shopDetail from "@/components/shop/shopDetail.vue";
  import cart from "@/components/shop/cart.vue";
  import order from "@/components/shop/order.vue"
  import orderComplete from "@/components/shop/orderComplete.vue"

  export default {
  components: {
    shopMain,
    shopSubbar,
    categoryList,
    keywordList,
    shopDetail,
    cart,
    order,
    orderComplete
  },
};
</script>

