<template>
  <div>
    <shopSubBar></shopSubBar>
    <shopDetail></shopDetail>
  </div>
</template>

<script>
import shopSubBar from "../../components/shop/shopSubBar.vue";
import shopDetail from "../../components/shop/shopDetail.vue";

export default {
  components: {
    shopSubBar,
    shopDetail,
  },
};
</script>
