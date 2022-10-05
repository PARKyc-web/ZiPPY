<template>
  <div>
    <shopSubBar></shopSubBar>
    <categoryList></categoryList>
  </div>
</template>

<script>
import shopSubBar from "../../components/shop/shopSubBar.vue";
import categoryList from "../../components/shop/categoryList.vue";

export default {  
  components: {
    shopSubBar,
    categoryList
  }
};
</script>