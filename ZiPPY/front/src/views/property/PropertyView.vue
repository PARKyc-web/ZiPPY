<template>
  <!-- <property-main/> -->
  <!-- <houseDetail/> -->
  <AgentDetail></AgentDetail>
</template>

<script>
import HouseDetail from '@/components/property/HouseDetail.vue';
import PropertyMain from './PropertyMain.vue';
import AgentDetail from '@/components/property/AgentDetail.vue';

export default {
  components : {
    PropertyMain,
    HouseDetail,
    AgentDetail
  }
}
</script>
