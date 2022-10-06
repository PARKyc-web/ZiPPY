<template>
  <!-- <hello-world /> -->
  <clustering-map/>
  <!-- <RoadviewMap/> -->
  <!-- <house-detail/> -->
  <!-- <agent-detail/> -->
  <!-- <basic-marker-map/> -->
</template>

<script>
import HelloWorld from "../components/HelloWorld";
import ClusteringMap from "../components/property/ClusteringMap.vue";
import RoadviewMapVue from "@/components/property/RoadviewMap.vue";
import RoadviewMap from "@/components/property/RoadviewMap.vue";
import HouseDetail from "@/components/property/HouseDetail.vue";
import AgentDetail from "@/components/property/AgentDetail.vue";
import BasicMarkerMap from '../components/property/BasicMarkerMap.vue';

export default {
  name: "Home",

  components: {
    HelloWorld,
    ClusteringMap,
    RoadviewMapVue,
    RoadviewMap,
    HouseDetail,
    AgentDetail,
    BasicMarkerMap,
    BasicMarkerMap,
},
};
</script>