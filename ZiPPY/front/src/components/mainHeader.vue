<template>
    <header v-if="this.$store.state.memberType == 0 || this.$store.state.memberType == null">
      <nav class="navbar navbar-expand-sm navbar-light bg-light">
        <div class="container-fluid">
          <router-link to="/home" class="navbar-brand">
            <img alt="ZiPPY_logo" src="@/assets/zippy_logo.png" style="height:36px;" />
          </router-link>

          <ul class="navbar-nav me-auto">
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
              <router-link to="/moveMain" class="nav-link">이사도우미</router-link>
            </li>             
		  </ul>               
          <div v-if="this.$store.state.loginInfo == null">
            <router-link to="/login" class="nav-link"><img id="Icon" src="@/assets/signin.png"></router-link>
          </div> 
          
          <button v-if="this.$store.state.loginInfo != null" @click="">
            <router-link to="/chat" class="nav-link"><img id="Icon" src="@/assets/chat.png"></router-link>
          </button>

          <div v-if="this.$store.state.loginInfo != null">
            <router-link to="/mypage" class="nav-link"><img id="Icon" src="@/assets/user.png"></router-link>
          </div>

          <div v-if="this.$store.state.loginInfo != null">
            <button class="nav-link" @click="logout()"><img id="Icon" src="@/assets/logout.png"></button>            
          </div>
		</div>
      </nav>
    </header>
</template>

<script>
import swal from 'sweetalert2';

export default{
  methods :{
    logout : function(){
      this.$store.commit('logout');
      swal.fire({
        icon:"info",
        title:"성공적으로 로그아웃하였습니다.!"
      });
      this.$router.push({
        name : "home"
      });
    }
  }
}
</script>

<style scoped>

nav .navbar-nav a.router-link-active{
  color: #42b983 !important;  
}

nav .navbar-nav a.nav-link {
  font-size: larger;
  font-weight: bold;
  text-decoration: none;
  /* color: #2c3e50 !important; */
}

ul {
  margin-top: 10px;
  vertical-align: middle;
}

#Icon{
    height:38px;
    margin-right: 20px;
}

#Icon:hover {    
    filter: opacity(0.3) drop-shadow(0 0 0 #42b983);    
}

header {
  box-shadow: 0 4px 2px -4px black;
}

</style>