<template>
  <div>
    <shop-insert-pro></shop-insert-pro>
    <shop-seller-pro></shop-seller-pro>
  </div>
</template>

<script>
  import shopMain from "@/components/shop/shopMain.vue";
  import shopSubbar from "@/components/shop/shopSubbar.vue";
  import shopDetail from "@/components/shop/shopDetail.vue";
  import shopCart from "@/components/shop/shopCart.vue";
  import order from "@/components/shop/order.vue";
  import orderComplete from "@/components/shop/orderComplete.vue";
  import shopInsertPro from "@/components/shop/shopInsertPro.vue";
  import shopSellerPro from "@/components/shop/shopSellerPro.vue";
  import shopSellerOrder from "@/components/shop/shopSellerOrder.vue";
  import ShopSellerPro from '../components/shop/shopSellerPro.vue';

  export default {
    name: "shop",
    components: {
      shopMain,
      shopSubbar,
      shopDetail,
      shopCart,
      order,
      orderComplete,
      shopInsertPro,
      shopSellerPro,
      shopSellerOrder,
      ShopSellerPro
    },
  };
</script>