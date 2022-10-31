<template>
  <header>
    <nav class="navbar navbar-expand-sm navbar-light">
      <div class="container-fluid">

        <!-- 회원으로 로그인할 시 메인버튼을 누르면 홈화면으로 갈 수 있도록 한다.-->
        <router-link to="/home" class="navbar-brand" v-if="$store.state.memberType == 0 || $store.state.memberType == null">
          <img alt="ZiPPY_logo" src="@/assets/zippy_logo2.png" style="height:50px;" />
        </router-link>

        <!-- 판매자로 로그인할 시 메인버튼을 누르면 판매자 페이지로 돌아갈 수 있도록 한다.-->
        <router-link to="/seller" class="navbar-brand" v-if="$store.state.memberType != 0 && $store.state.memberType != null">
          <img alt="ZiPPY_logo" src="@/assets/zippy_logo2.png" style="height:50px;" />
        </router-link>

        <ul class="navbar-nav me-auto" v-if="$store.state.memberType == 0 || $store.state.memberType == null">
          <li class="nav-item">
            <router-link to="/home" class="nav-link">Home</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/property/main" class="nav-link">부동산</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/shop" class="nav-link">쇼핑몰</router-link>
          </li>
          <li class="nav-item" @click="RmSession()">
            <router-link to="/used"  class="nav-link">중고거래</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/move" class="nav-link">이사도우미</router-link>
          </li>
        </ul>
      </div>
      
      <div v-if="this.$store.state.loginInfo == null">
        <router-link to="/login" class="nav-link"><v-icon id="Icon">mdi-login-variant</v-icon></router-link>
      </div>

      <div id="userNameTag" v-if="this.$store.state.loginInfo != null">
        <div v-if="this.$store.state.memberType == 1">
          <strong style="color:#64c481">{{$store.getters.getName}}</strong>님 환영합니다!
        </div>

        <div v-if="this.$store.state.memberType == 0">
          <strong style="color:#64c481">{{$store.state.loginInfo.nickName}}</strong>님 환영합니다!
        </div>
      </div>

      <button v-if="this.$store.state.loginInfo != null">
        <!-- <router-link to="/chat" class="nav-link"><img id="Icon" src="@/assets/chat.png"></router-link> -->
        <router-link to="/chat" class="nav-link"><v-icon id="Icon">mdi-wechat</v-icon></router-link>
      </button>

      <div v-if="$store.state.memberType == 0 && this.$store.state.loginInfo != null">
        <!-- <router-link to="/mypage" class="nav-link">          
          <img id="Icon" src="@/assets/user.png">
        </router-link> -->
        <router-link to="/mypage" class="nav-link">          
          <v-icon id="Icon">mdi-account-circle</v-icon>
        </router-link>
      </div>

      <div v-if="$store.state.memberType != 0 && this.$store.state.loginInfo != null">
        <router-link to="/seller" class="nav-link">          
          <!-- <img id="Icon" src="@/assets/user.png"> -->
          <!-- <v-icon id="Icon">mdi-logout-variant</v-icon> -->
        </router-link>
      </div>

      <div v-if="this.$store.state.loginInfo != null">
        <button class="nav-link" @click="logout()"><v-icon id="Icon">mdi-logout-variant</v-icon></button>
      </div>
    </nav>
  </header>
</template>

<script>
  import swal from 'sweetalert2';

  export default {
    methods: {
      logout: function () {
        this.$store.commit('logout');
        swal.fire({
          icon: "info",
          title: "성공적으로 로그아웃하였습니다.!"
        });
        this.$router.push({
          name: "home"
        });
      },
      RmSession(){
        sessionStorage.removeItem("cate");
      }
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

  .v-application a{
    color : black !important;
  }

.v-application a{
    color : black !important;
  }

  header {
    z-index: 3;
  }
  nav .navbar-nav a.router-link-active {
    color: #64c481 !important;
  }

  nav .navbar-nav a.nav-link {
    font-size: larger;
    font-weight: bold;
    text-decoration: none;    
  }

  ul {
    margin-top: 10px;
    vertical-align: middle;
  }

  #Icon {
    height: 38px;
    margin-right: 20px;
    font-size:30px;
  }

  /* #Icon:hover {
    filter: opacity(0.3) drop-shadow(0 0 0 #64c481);
  } */
  #Icon {
    color:#191919;
  }
  #Icon:hover {
    color: #64c481;
  }
  /* header {
    box-shadow: 0 4px 2px -4px black;
  } */

  #userNameTag{
    width: 100%;
    text-align: right;
    padding-right: 2%;
  }
  /*******/
  /* .navbar-brand {
    padding-left: 0px;
  } */
  .nav-item{
    font-size:0.8em;
    color:#191919;
  }
</style>