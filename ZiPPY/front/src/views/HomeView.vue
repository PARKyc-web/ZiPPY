<template>
  <hello-world />
</template>

<script>
// import HelloWorld from "../components/HelloWorld";
// import HelloWorld from './login/generalSignUp.vue';
import HelloWorld from './login/businessSignUp.vue';

  export default {
    name: "Home",
    components: {
      HelloWorld,
    },
  };
</script>