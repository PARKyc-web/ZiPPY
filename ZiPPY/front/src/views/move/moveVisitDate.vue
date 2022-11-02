<template>
  <div>
    <move-nav-bar @click="categoryVal=$event.target.innerText"></move-nav-bar>
  <div class="wrapper">
    <h3>견적 방문 희망 날짜를 선택해주세요.</h3>
    <v-row justify="space-between" locale="ko-KR">
      <div class="subheading"></div>
      <v-date-picker
        v-model="moveVisit.visitDate"
        :events="arrayEvents"
        color="green lighten-1"
        event-color="blue lighten-1"
        header-color="green lighten-2"
        locale="ko-KR"
        :allowed-dates="disablePastDates"
      ></v-date-picker>
    </v-row>
    <hr />
    <h3>방문 희망 시간을 선택해주세요.</h3>    
    <div class="selectTime">
      <b-row>
        <b-col md="auto">
          <b-time v-model="moveVisit.visitTime" locale="ko-KR" @context="onContext"></b-time>
        </b-col>
        <b-col>
          <p>
            선택 시간: <b>{{ moveVisit.visitTime }}</b>
          </p>
        </b-col>
      </b-row>
    </div>

    <v-flex xs12>
    <v-btn type="button" elevation="7" @click="final_signIn()">선택완료</v-btn>
  </v-flex>
  <v-flex xs12>
    <router-view></router-view>
  </v-flex>
</div>
  </div>
</template>
<script>
import MoveNavBar from '@/components/move/MoveNavBar.vue';
import swal from 'sweetalert2';
export default {
  components: {
    MoveNavBar
  },

  props : ['moveInfo','moveEstimateType','moveType', 'moveDate', 'moveAddress'],
  data: () => ({
    //날짜
    arrayEvents: null,
    date1: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
      .toISOString()
      .substring(0, 10),
    date2: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
      .toISOString()
      .substring(0, 10),
    value: "",
    context: null,

    //moveInfo
    moveVisit : {
      visitDate : "",
      visitTime : "",
    },
    
  }),

  mounted() {
    
  },

  methods: {

     //현재일보다 과거 선택 불가
     disablePastDates(val){
      

        return val >= new Date().toISOString().substring(0,10);
      },

    check_pro : function(){
      console.log(this.moveContact);
    },
    onContext(ctx) {
      this.context = ctx;
    },
    functionEvents(date) {
      const [, , day] = date.split("-");
      if ([12, 17, 28].includes(parseInt(day, 10))) return true;
      if ([1, 19, 22].includes(parseInt(day, 10))) return ["red", "#00f"];
      return false;
    },

    final_signIn: function(){
      console.log(this.moveInfo);
      console.log(this.moveVisit);
      
      if(this.moveVisit.visitDate==""){
        swal.fire("견적 방문 희망 날짜를 선택해주세요.")
      } else if(this.moveVisit.visitTime==""){
        swal.fire("견적 방문 희망 시간을 선택해주세요.")
      } 
      else{
        this.$router.push({
          
            name : "moveContactCheck", 
            params:{moveVisit: this.moveVisit,                                    
                    moveEstimateType:this.moveEstimateType, 
                    moveInfo : this.moveInfo,
                    moveType:this.moveType,
                    moveDate: this.moveDate, 
                    moveAddress: this.moveAddress
            }
          })
      }  
    }
  },
};
</script>

<style scoped>
  @font-face {
    font-family: 'GmarketSans';
    font-weight: 500;
    font-style: normal;
    src: url('https://cdn.jsdelivr.net/gh/webfontworld/gmarket/GmarketSansMedium.eot');
    src: url('https://cdn.jsdelivr.net/gh/webfontworld/gmarket/GmarketSansMedium.eot?#iefix') format('embedded-opentype'),
         url('https://cdn.jsdelivr.net/gh/webfontworld/gmarket/GmarketSansMedium.woff2') format('woff2'),
         url('https://cdn.jsdelivr.net/gh/webfontworld/gmarket/GmarketSansMedium.woff') format('woff'),
         url('https://cdn.jsdelivr.net/gh/webfontworld/gmarket/GmarketSansMedium.ttf') format("truetype");
    font-display: swap;
} 
* {
  font-family: 'GmarketSans';
}

.wrapper {
  width: 500px;
  text-align: center;
  margin: 0 auto;
  margin-top: 100px;
  margin-bottom: 100px;
}
h3 {
  margin-top: 50px;
  padding-bottom: 50px;
}
.col {
  display: flex;
  flex-direction: column;
  justify-content: center;
}
.selectTime {
  height: 200px;
}
.v-btn {
  color: white;
  background-color: rgb(179, 227, 195, 0.8) !important;
  transition: all 0.3s ease !important;
  font-size: 20px;
}
.v-btn:hover {
  background-color: rgb(211, 211, 211, 0.8) !important;
  transition: all 0.3s ease !important;
}
</style>
