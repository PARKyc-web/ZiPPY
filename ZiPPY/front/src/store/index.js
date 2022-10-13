import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    login_info : {}
  },
  getters: {},
  mutations: {
    login(state, info){
      state.login_info = info;
    }
  },
  actions: {},
  modules: {},
});
