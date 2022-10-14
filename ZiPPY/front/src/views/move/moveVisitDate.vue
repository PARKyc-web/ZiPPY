<template>
  <div class="wrapper">
    <h3>견적 방문 희망 날짜를 선택해주세요.</h3>
    <v-row justify="space-between" locale="ko-KR">
      <div class="subheading"></div>
      <v-date-picker
        v-model="moveVisit.visitDate"
        :events="arrayEvents"
        color="green lighten-1"
        event-color="blue lighten-1"
        header-color="green -1"
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
          <!-- v-if를 걸어서 널값일때는 시간 선택해달라고 안내문구 -->

          <p>
            선택 시간: <b>{{ moveVisit.visitTime }}</b>
          </p>
          <!-- <p class="mb-0">Context:</p>
      <pre v-if="context != null" class="small">{{ context.Value }}</pre> -->
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
</template>
<script>
export default {
  // props : ['moveContact', ''],
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
    this.arrayEvents = [...Array(6)].map(() => {
      const day = Math.floor(Math.random() * 30);
      const d = new Date();
      d.setDate(day);
      return d.toISOString().substr(0, 10);
    });
  },

  methods: {
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
        alert("견적 방문 희망 날짜를 선택해주세요.")
      } else if(this.moveVisit.visitTime==""){
        alert("견적 방문 희망 시간을 선택해주세요.")
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
