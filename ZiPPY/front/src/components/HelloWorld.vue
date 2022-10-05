<template>
  <div>
    <test-bar></test-bar>
    <h1>text</h1>
  </div>
</template>

<script>
import bar from "./testbar.vue";

export default {
  name: "HelloWorld",
  components: {
    testBar: bar,
  },
  data: function () {
    return {
      return: {},
    };
  },
};
</script>
