<template>
    <header>
      <nav class="navbar navbar-expand-sm navbar-light bg-light">
        <div class="container-xxl">
          <router-link to="/home" class="navbar-brand">
            <img alt="ZiPPY_logo" src="@/assets/zippy_logo.png" style="height:48px;" />
          </router-link>

          <ul class="navbar-nav me-auto">
            <li class="nav-item">
              <router-link to="/home" class="nav-link">Home</router-link>
            </li>
            <li class="nav-item">
              <router-link to="/property" class="nav-link">부동산</router-link>
            </li>
            <li class="nav-item">
              <router-link to="/shop" class="nav-link">쇼핑몰</router-link>
            </li>
            <li class="nav-item">
              <router-link to="/used" class="nav-link">중고거래</router-link>
            </li>             
		  </ul>          
          <button v-if="this.$store.state.login_info != null"><img id="chatIcon" src="@/assets/chat.png"></button>  

          <div v-if="this.$store.state.login_info == null">
            <router-link to="/login" class="nav-link"><img id="chatIcon" src="@/assets/signin.png"></router-link>
          </div> 
          
          <div v-if="this.$store.state.login_info != null">
            <router-link to="/logout" class="nav-link"><img id="chatIcon" src="@/assets/logout.png"></router-link>
          </div>
		</div>
      </nav>
    </header>
</template>

<script>
export default{

}
</script>

<style scoped>
nav .navbar-nav a.router-link-active{
  color: #42b983 !important;
}
nav .navbar-nav a.nav-link {
  font-size: larger;
  font-weight: bold;
  /* color: #2c3e50 !important; */
}
ul {
  margin-top: 10px;
  vertical-align: middle;
}

#chatIcon{
    height:38px;
    margin-right: 20px;
}

#chatIcon:hover {    
    filter: opacity(0.5) drop-shadow(0 0 0 #42b983);    
}

header {
  box-shadow: 0 4px 4px -4px black;
}
</style>