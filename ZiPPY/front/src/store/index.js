import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    loginInfo : {}
  },
  getters: {},
  mutations: {
    login(state, info){
      state.loginInfo = info;
    },

    logout(state){
      state.loginInfo = null;
    }
  },
  actions: {},
  modules: {},
});
