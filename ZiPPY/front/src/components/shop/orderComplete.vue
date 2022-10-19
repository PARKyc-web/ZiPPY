<template>
  <div id="order-con" class="mx-auto pt-10" style="margin-bottom:150px">
    <v-toolbar flat transparent>
      <v-toolbar-title>주문서</v-toolbar-title>
      <v-spacer></v-spacer>
    </v-toolbar>
    <hr>
    <!-- 주문확인 -->
    <div id="orderSheet">
      <h5>주문번호 <span style="font-weight:bold">{{payCode}}</span></h5>
      <v-icon color=#B3E3C3 class="my-5" style="font-size:150px">mdi-gift</v-icon>
      <h2 style="font-weight:bold">결제가 정상적으로 완료되었습니다</h2>
      <p>업체의 주문 확인 후 발송됩니다.</p>
      <div class="mx-auto pt-5">
        <v-btn width="150" height="50" depressed outlined color="#64c481" style=" font-weight: bold; margin-top:50px" @click="goMain">
          <h5 class="pt-2">홈으로</h5>
        </v-btn>
      </div>
    </div>
    <!-- 주문확인 -->
    <hr>
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
            name: 'shopMain'
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