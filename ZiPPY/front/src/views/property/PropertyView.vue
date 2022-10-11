<template>
  <property-main/>
  <!-- <houseDetail/> -->
  <!-- <AgentDetail></AgentDetail> -->
  <!-- <BasicMarkerMap></BasicMarkerMap> -->
</template>

<script>
import HouseDetail from '@/components/property/HouseDetail.vue';
import PropertyMain from './PropertyMain.vue';
import AgentDetail from '@/components/property/AgentDetail.vue';
import BasicMarkerMap from '@/components/property/BasicMarkerMap.vue';

export default {
  components : {
    PropertyMain,
    HouseDetail,
    AgentDetail,
    BasicMarkerMap
  }
}
</script>
