<template>
  <div>    
    <g-sign-up></g-sign-up>
  </div>
</template>

<script>
// import HelloWorld from "../components/HelloWorld.vue";
import gSignUp from "./login/generalSignUp.vue";

export default {
  name: "Home",

  components: {
    // HelloWorld,
    gSignUp,
  },
};
</script>
