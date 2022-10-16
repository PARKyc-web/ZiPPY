<template>
  <div id="order-con" class="mx-auto pt-10">
    <v-toolbar flat transparent>
      <v-toolbar-title>주문서</v-toolbar-title>
      <v-spacer></v-spacer>
    </v-toolbar>
    <div id="orderSheet">
      <h6>주문번호 {{payCode}}</h6>
      <v-icon color=#B3E3C3 style="font-size:100px">mdi-gift</v-icon>
      <h2 style="font-weight:bold">결제가 정상적으로 완료되었습니다</h2>
      <p>업체의 주문 확인 후 발송됩니다</p>
    </div>
    <div class="mx-auto pt-5" style="width:100px">
      <v-btn depressed color=#B3E3C3 @click="goMain">
        홈으로
      </v-btn>
    </div>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        payCode : this.$route.query.payCode
      }
    },
    methods: {
      goMain() {
        this.$router.push({
            name: 'main'
          })
      }
    }
  }
</script>

<style scoped>
  #order-con {
    width: 70%;
  }
  .v-toolbar__title {
    font-weight: bold;
  }
  #orderSheet{
    text-align: center;
    padding:100px;
  }
</style>