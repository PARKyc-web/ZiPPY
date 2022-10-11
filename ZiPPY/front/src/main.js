import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import vuetify from "./plugins/vuetify";
import axios from "axios";

import { BootstrapVue, IconsPlugin } from "bootstrap-vue";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";

// Axios Setting
Vue.prototype.$http = axios;
Vue.prototype.$axios = axios;
axios.defaults.crossDomain = true;
// port는 sts의 포트넘버
axios.defaults.baseURL = "http://localhost:8090/";


Vue.use(BootstrapVue);
Vue.use(IconsPlugin);
Vue.config.productionTip = false;


Vue.use(router);


new Vue({
  router,
  store,
  vuetify,
  render: (h) => h(App),
}).$mount("#app");
