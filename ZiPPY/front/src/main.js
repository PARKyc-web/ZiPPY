import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import vuetify from "./plugins/vuetify";
import axios from "axios";
import VueSweetalert2 from "vue-sweetalert2";
//import IMP from 'vue-iamport';
//import jQuery from 'jquery';;
import { BootstrapVue, IconsPlugin } from "bootstrap-vue";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";
import 'sweetalert2/dist/sweetalert2.min.css'

// Axios Setting
Vue.prototype.$http = axios;
Vue.prototype.$axios = axios;
axios.defaults.crossDomain = true;
// port는 sts의 포트넘버
axios.defaults.baseURL = "http://localhost:8090/zippy";

Vue.use(BootstrapVue);
Vue.use(IconsPlugin);
Vue.use(VueSweetalert2);
Vue.config.productionTip = false;
//Vue.use(IMP, 'imp22120243');
//Vue.IMP().load();

new Vue({
  router,
  store,
  vuetify,  
  render: (h) => h(App),
}).$mount("#app");
