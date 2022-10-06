<template>
  <div>
  <!-- <hello-world /> -->
  <clustering-map/>
  <!-- <RoadviewMap/> -->
  <!-- <house-detail/> -->
  <!-- <agent-detail/> -->
  <!-- <basic-marker-map/> -->


    <moveFinalCheck />
    <usedDetail></usedDetail>
   </div>

</template>

<script>
// import HelloWorld from "../components/HelloWorld";
import moveFinalCheck from "./move/moveFinalCheck.vue"; //   ./ 같은 폴더 내에 있을 때 & ../ 하나 상위로
//import moveDate from "./move/moveDate.vue";
//import moveVisitDate from "./move/moveVisitDate.vue";
//import moveSelect from "./move/moveSelect.vue";
//import moveTypeSelect from "./move/moveTypeSelect.vue";
//import moveInfoDetail from "./move/moveInfoDetail.vue";
import HelloWorld from "../components/HelloWorld";

import ClusteringMap from "../components/property/ClusteringMap.vue";
import RoadviewMapVue from "@/components/property/RoadviewMap.vue";
import RoadviewMap from "@/components/property/RoadviewMap.vue";
import HouseDetail from "@/components/property/HouseDetail.vue";
import AgentDetail from "@/components/property/AgentDetail.vue";
import BasicMarkerMap from '../components/property/BasicMarkerMap.vue';
import UsedMain from "./used/usedMain.vue";
import UsedDetail from "./used/usedDetail.vue";
import UsedKeyword from "./used/usedKeyword.vue";
import usedInsert from "./used/usedInsert.vue";


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
    HelloWorld,
    ClusteringMap,
    RoadviewMapVue,
    RoadviewMap,
    HouseDetail,
    AgentDetail,
    BasicMarkerMap,
    BasicMarkerMap,
    UsedMain,
    UsedDetail,
    UsedKeyword,
    UsedMain,
    usedInsert

  },
};
</script>