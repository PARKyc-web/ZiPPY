<template>
  <move1 />
</template>

<script>
  import HelloWorld from "../components/HelloWorld";
  import moveNav from "./move/moveNav.vue";
  import moveFinalCheck from "./move/moveFinalCheck.vue";
  import moveSelect from "./move/moveSelect";
  import moveDate from "./move/moveDate";
  import moveInfo from "./move/moveInfo";
  import moveTypeSelect from "./move/moveTypeSelect";
  import move1 from "./move/move1";

  export default {
    name: "Home",
    components: {
      HelloWorld,
      moveNav,
      moveFinalCheck,
      moveSelect,
      moveDate,
      moveInfo,
      moveTypeSelect,
      move1
    },
  };
</script>