<template>
  <div>
    <shopSubBar></shopSubBar>
    <shopMain></shopMain>
  </div>
</template>

<script>
import shopSubBar from "../../components/shop/shopSubBar.vue";
import shopMain from "../../components/shop/shopMain.vue";

export default {
  components: {
    shopSubBar,
    shopMain,
  },
};
</script>
