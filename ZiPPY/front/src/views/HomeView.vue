<template>
  <UsedMain></UsedMain>
</template>

<script>
import HelloWorld from "../components/HelloWorld";
import UsedMain from "./used/usedMain.vue";
import UsedDetail from "./used/usedDetail.vue";
import UsedKeyword from "./used/usedKeyword.vue";
export default {
  name: "Home",

  components: {
    HelloWorld,
    UsedMain,
    UsedDetail,
    UsedKeyword,
    UsedMain
},
};
</script>
