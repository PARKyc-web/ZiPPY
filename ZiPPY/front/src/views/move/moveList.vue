<template>
  <div class="list-wrap">

    <div v-for="item in list">
    <ul>
      <li>{{item.email}}</li>
      <li>{{item.movingOption}}</li>
      <li>{{item.moveType}}</li>
      
      <li>{{item.estimateType}}</li>
      <li>{{item.movingMemo}}</li>
      <li>{{item.departZipCode}}</li>
      <li>{{item.departAddress}}</li>
      <li>{{item.departDetail}}</li>
      <li>{{item.departExtra}}</li>
      <li>{{item.arriveZipCode}}</li>
      <li>{{item.arriveAddress}}</li>
      <li>{{item.arriveDetail}}</li>
      <li>{{item.arriveExtra}}</li>
      <li>{{item.movingDate}}</li>
      <li>{{item.movingTime}}</li>
      <li>{{item.requestDate}}</li>
      <li>{{item.visitDate}}</li>
      <li>{{item.visitTime}}</li>
      <li>{{item.commonOption}}</li> 
      
    </ul>
  </div>
  </div>
</template>

<script>

import axios from 'axios';
export default{
  data: function(){
    return{
      //담을 공간 만들어주기 -> 단일값이면 객체여도 상관없음 / 여러개면 배열로
      list : [],

      

    }
  },
  created() {
        axios({
          url: "http://localhost:8090/zippy/move/moveEstimate",
          methods: "GET",
          params: {
            email : "zippy@naver.com",
            movingOption : "",
            moveType : "",
            // estimateType : "",
            // movingMemo : "",
            // departZipCode : "",
            // departAddress : "",
            // departDetail : "",
            // departExtra : "",
            // arriveZipCode : "",
            // arriveAddress : "",
            // arriveDetail : "",
            // arriveExtra : "",
            // movingDate : "",
            // movingTime : "",
            // requestDate : "",
            // visitDate : "",
            // visitTime : "",
            // commonOption : "",



           
          }
        }).then(res => {
          console.log(res);
          this.list = res.data;
        }).catch(error => {
          console.log(error);
        })
},
    };
</script>

<style scoped>

</style>