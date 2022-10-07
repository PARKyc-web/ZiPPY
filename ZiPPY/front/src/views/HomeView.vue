<template>
  <div>  
    <moveInfo></moveInfo>
    <!-- <moveFinalCheck></moveFinalCheck> -->
  </div>
</template>

<script>
//move
import moveFinalCheck from "./move/moveFinalCheck.vue"; //   ./ 같은 폴더 내에 있을 때 & ../ 하나 상위로
import moveNav from "./move/moveNav.vue";
import moveInfo from "./move/moveInfo.vue";

export default {
  name: "Home",

  components: {
    moveFinalCheck,
    moveNav,
    moveInfo,
  },
};
</script>
