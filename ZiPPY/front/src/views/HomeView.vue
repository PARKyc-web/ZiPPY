<template>
  <div>
    <g-sign-up></g-sign-up>
    <!-- <b-sign-up></b-sign-up> --> 
   </div>
</template>

<script>
import gSignUp from "./login/generalSignUp.vue";
// import bSignUp from "./login/businessSignUp.vue";

export default {
  name: "Home",

  components: {
    gSignUp

  },
};
</script>