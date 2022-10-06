<template>
  <moveFinalCheck />
</template>

<script>
// import HelloWorld from "../components/HelloWorld";
import moveFinalCheck from "./move/moveFinalCheck.vue"; //   ./ 같은 폴더 내에 있을 때 & ../ 하나 상위로
//import moveDate from "./move/moveDate.vue";
//import moveVisitDate from "./move/moveVisitDate.vue";
//import moveSelect from "./move/moveSelect.vue";
//import moveTypeSelect from "./move/moveTypeSelect.vue";
//import moveInfoDetail from "./move/moveInfoDetail.vue";

export default {
  name: "Home",

  components: {
    // HelloWorld,
    moveFinalCheck,
    //moveDate,
    //moveVisitDate,
    //moveSelect,
    //moveTypeSelect,
    //moveInfoDetail,
  },
};
</script>
