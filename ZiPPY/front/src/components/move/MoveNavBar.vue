<template>
  <v-toolbar flat style="border-bottom:1px solid #D6D6D6;">
    <div style="width: 75vw">
      <v-btn-toggle v-model="text" tile color="success lighten-1" group>
        <v-btn value="견적받기" @click="goEstimate()" class="nav-link active" aria-current="page"> 견적받기</v-btn>
        <v-btn value="업체조회" @click="goCompany()" class="nav-link">업체조회</v-btn>
        <v-btn value="마이페이지" @click="goManage()" class="nav-link">견적/예약관리</v-btn>
      </v-btn-toggle>
    </div>
  </v-toolbar>
</template>

<script>
import swal from 'sweetalert2';
  export default {
    data: () => ({
      data: [],
      //toggle
      text: 'center',
      icon: 'justify',
      toggle_none: null,
      toggle_one: 0,
      toggle_exclusive: 2,
      toggle_multiple: [0, 1, 2],
    }),
    methods: {
      // pushCategory: function (e) {
      //   this.$emit('click', e);
      // }
      goEstimate: function () {
       
        
        if (this.$store.state.loginInfo == null || this.$store.state.loginInfo.email == "") {
          swal.fire({
            title: '로그인이 필요합니다.',
            text: "로그인 하시겠습니까 ?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#96e5b8',
            cancelButtonColor: '#a9a9a9',
            confirmButtonText: 'YES'
          }).then((result) => {
            if (result.isConfirmed) {
              this.$router.push('/login')
            }
          })
          
        } else {
          // window.location.assign('/mypage/move/moveResult');
          this.$router.push(({
          name: 'moveTotal',

        })).catch(() => {});
        }

      },
      goCompany: function () {
        this.$router.push(({
          name: 'moveCompanyList',

        })).catch(() => {});;
       
      },
      goManage: function () {
        // this.$router.push(({
        //   path: '/mypage/move/moveResult',

        // })).catch(() => {});;

        if (this.$store.state.loginInfo == null || this.$store.state.loginInfo.email == "") {
          swal.fire({
            title: '로그인이 필요합니다.',
            text: "로그인 하시겠습니까 ?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#96e5b8',
            cancelButtonColor: '#a9a9a9',
            confirmButtonText: 'YES'
          }).then((result) => {
            if (result.isConfirmed) {
              this.$router.push('/login')
            }
          })
          
        } else {

          this.$router.push(({
          // name: 'moveResult',
          path: '/mypage/move/moveResult'

        })).catch(() => {});
          // window.location.assign('/mypage/move/moveResult');
        }
       
      },
    }
  }
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


  .nav {
    width: 100%;
    z-index: 1000000000;
  }
  .v-btn{
    color:black;
  }
</style>