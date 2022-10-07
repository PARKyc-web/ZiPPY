<template>
  <div>
    <shopSubBar></shopSubBar>
    <keywordList></keywordList>
  </div>
</template>

<script>
import shopSubBar from "../../components/shop/shopSubBar.vue";
import keywordList from "../../components/shop/keywordList.vue";

export default {
  components: {
    shopSubBar,
    keywordList,
  },
};
</script>
