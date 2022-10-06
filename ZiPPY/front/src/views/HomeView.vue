<template>
  <div>
    <g-sign-up></g-sign-up>
    <!-- <b-sign-up></b-sign-up> -->
  </div>
</template>

<script>
// import HelloWorld from "../components/HelloWorld.vue";
import gSignUp from "./login/generalSignUp.vue";
// import bSignUp from "./login/businessSignUp.vue";

export default {
  name: "Home",

  components: {
    // HelloWorld,
    gSignUp
    // bSignUp,
  },
};
</script>
