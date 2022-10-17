import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    loginInfo : null,
    cartCount : 0
  },
  getters: {},
  mutations: {
    addCart(state, num){
      state.cartCount += num;
    },

    removeCart(state, num){
      state.cartCount -= num;
    },

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
