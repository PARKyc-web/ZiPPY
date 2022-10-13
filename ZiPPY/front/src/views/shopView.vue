<template>
  <div>    
    <shop-subbar></shop-subbar>
    <shopMain></shopMain>
  </div>
</template>

<script>
  import shopMain from "@/components/shop/shopMain.vue";
  import shopSubbar from "@/components/shop/shopSubbar.vue";
  import categoryList from "@/components/shop/categoryList.vue";
  import keywordList from "@/components/shop/keywordList.vue";
  import shopDetail from "@/components/shop/shopDetail.vue";
  import shopCart from "@/components/shop/shopCart.vue";
  import order from "@/components/shop/order.vue"
  import orderComplete from "@/components/shop/orderComplete.vue"
  
  export default {
  components: {
    shopMain,
    shopSubbar,
    categoryList,
    keywordList,
    shopDetail,
    shopCart,
    order,
    orderComplete
  },
};
</script>

