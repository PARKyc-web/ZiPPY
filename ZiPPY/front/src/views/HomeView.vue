<template>
  <div>
    <usedMain></usedMain>
  </div>

</template>

<script>
  import UsedMain from "./used/usedMain.vue";
  import UsedDetail from "./used/usedDetail.vue";
  import UsedKeyword from "./used/usedKeyword.vue";
  import usedInsert from "./used/usedInsert.vue";


  export default {
    name: "Home",
    components: {
      UsedMain,
      UsedDetail,
      UsedKeyword,
      UsedMain,
      usedInsert
    },
  };
</script>