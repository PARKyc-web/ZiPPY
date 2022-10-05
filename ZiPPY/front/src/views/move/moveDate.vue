<template>
  <div class="wrapper">

<h3>이사 희망 날짜를 선택해주세요.</h3>
<v-row justify="space-between" locale="ko-KR">
    
      <div class="subheading">
      </div>
      <v-date-picker
        v-model="date1"
        :events="arrayEvents"
        color="green lighten-1"
        event-color="blue lighten-1"
        header-color= "green -1"
      ></v-date-picker>  
  </v-row>
<hr>
<h3>이사 희망 시간을 선택해주세요.</h3>
<b-row>
    <b-col md="auto">
      <b-time v-model="value" locale="ko-KR" @context="onContext"></b-time>
    </b-col>
    <b-col>
      <!-- v-if를 걸어서 널값일때는 시간 선택해달라고 안내문구 -->

      <p>선택 시간: <b>{{ value }}</b></p>
      <!-- <p class="mb-0">Context:</p>
      <pre v-if="context != null" class="small">{{ context.Value }}</pre> -->
    </b-col>
  </b-row>

  <v-btn
  color="secondary"
  elevation="2"
></v-btn>
  </div>
</template>
<script>
   export default {
    data: () => ({
      //날짜
      arrayEvents: null,
      date1: (new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10),
      date2: (new Date(Date.now() - (new Date()).getTimezoneOffset() * 60000)).toISOString().substr(0, 10),
      value : '',
      context : null
    }),

    mounted () {
      this.arrayEvents = [...Array(6)].map(() => {
        const day = Math.floor(Math.random() * 30)
        const d = new Date()
        d.setDate(day)
        return d.toISOString().substr(0, 10)
      })
    },

    methods: {
      onContext(ctx) {
        this.context = ctx
      },
      functionEvents (date) {
        const [,, day] = date.split('-')
        if ([12, 17, 28].includes(parseInt(day, 10))) return true
        if ([1, 19, 22].includes(parseInt(day, 10))) return ['red', '#00f']
        return false
      },
    },
  }


</script>

<style scoped>
.wrapper{ 
  width: 500px;
  text-align: center;
  margin: 0 auto;
  margin-top: 100px;
}
h3{
  margin-top: 50px;
  padding-bottom: 50px;
}
.col{
  display: flex;
  flex-direction: column; 
  justify-content: center;
}
</style>