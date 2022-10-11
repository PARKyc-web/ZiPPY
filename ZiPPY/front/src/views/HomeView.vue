<template>
  <move1 />
</template>

<script>
  import HelloWorld from "../components/HelloWorld";
  import moveNav from "./move/moveNav.vue";
  import moveContactFinalCheck from "./move/moveContactFinalCheck.vue";
  import moveUntactFinalCheck from "./move/moveUntactFinalCheck.vue";
  import moveSelect from "./move/moveSelect";
  import moveDate from "./move/moveDate";
  import moveVisitDate from "./move/moveVisitDate";
  import moveInfo from "./move/moveInfo";
  import moveInfoDetail from "./move/moveInfoDetail";
  import moveTypeSelect from "./move/moveTypeSelect";
  import move1 from "./move/move1";
  import generalSignUp from "./login/generalSignUp.vue";

  export default {
    name: "Home",
    components: {
      HelloWorld,
      moveNav,
      moveSelect,
      moveDate,
      moveVisitDate,
      moveInfo,
      moveInfoDetail,
      moveTypeSelect,
      move1,
      moveContactFinalCheck,
      moveUntactFinalCheck,
      generalSignUp
    },
  };
</script>