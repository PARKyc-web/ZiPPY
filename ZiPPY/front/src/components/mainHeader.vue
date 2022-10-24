<template>
  <header>
    <nav class="navbar navbar-expand-sm navbar-light bg-light">
      <div class="container-fluid">

        <!-- 회원으로 로그인할 시 메인버튼을 누르면 홈화면으로 갈 수 있도록 한다.-->
        <router-link to="/home" class="navbar-brand" v-if="$store.state.memberType == 0 || $store.state.memberType == null">
          <img alt="ZiPPY_logo" src="@/assets/zippy_logo.png" style="height:36px;" />
        </router-link>

        <!-- 판매자로 로그인할 시 메인버튼을 누르면 판매자 페이지로 돌아갈 수 있도록 한다.-->
        <router-link to="/mypage" class="navbar-brand" v-if="$store.state.memberType != 0 && $store.state.memberType != null">
          <img alt="ZiPPY_logo" src="@/assets/zippy_logo.png" style="height:36px;" />
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
          <li class="nav-item">
            <router-link to="/used" class="nav-link">중고거래</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/move" class="nav-link">이사도우미</router-link>
          </li>
        </ul>
      </div>
      
      <div v-if="this.$store.state.loginInfo == null">
        <router-link to="/login" class="nav-link"><img id="Icon" src="@/assets/signin.png"></router-link>
      </div>

      <div id="userNameTag" v-if="this.$store.state.loginInfo != null">
        <strong>{{$store.getters.getName}}</strong>님 환영합니다!
      </div>

      <button v-if="this.$store.state.loginInfo != null" @click="">
        <router-link to="/chat" class="nav-link"><img id="Icon" src="@/assets/chat.png"></router-link>
      </button>

      <div v-if="this.$store.state.loginInfo != null">
        <router-link to="/mypage" class="nav-link">          
          <img id="Icon" src="@/assets/user.png">
        </router-link>
      </div>

      <div v-if="this.$store.state.loginInfo != null">
        <router-link to="/mypage" class="nav-link">
          <img id="Icon" src="@/assets/box.jpg">           
        </router-link>
      </div>

      <div v-if="this.$store.state.loginInfo != null">
        <button class="nav-link" @click="logout()"><img id="Icon" src="@/assets/logout.png"></button>
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
      }
    }
  }
</script>

<style scoped>
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
    color: #42b983 !important;
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
  }

  #Icon:hover {
    filter: opacity(0.3) drop-shadow(0 0 0 #42b983);
  }

  header {
    box-shadow: 0 4px 2px -4px black;
  }

  #userNameTag{
    width: 100%;
    text-align: right;
    padding-right: 2%;
  }
</style>