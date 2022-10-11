<template>
  <div>
    <shop-subbar></shop-subbar>
    <keywordList></keywordList>
  </div>
</template>

<script>
import shopSubbar from "../../components/shop/shopSubbar.vue";
import keywordList from "../../components/shop/keywordList.vue";

export default {  
  components: {
    shopSubbar,
    keywordList
  }
};
</script>