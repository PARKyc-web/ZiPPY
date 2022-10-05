<template>
  <!-- <hello-world /> -->
  <!-- <clustering-map/> -->
  <RoadviewMap/>
</template>

<script>
import HelloWorld from "../components/HelloWorld";
import ClusteringMap from "../components/property/ClusteringMap.vue";
import RoadviewMapVue from "@/components/property/RoadviewMap.vue";
import RoadviewMap from "@/components/property/RoadviewMap.vue";

export default {
  name: "Home",

  components: {
    HelloWorld,
    ClusteringMap,
    RoadviewMapVue,
    RoadviewMap
},
};
</script>
