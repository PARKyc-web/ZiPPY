import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    loginInfo : null,
    memberType : null
  },
  getters: {},
  mutations: {
    login(state, info){
      state.loginInfo = info;

      if(state.loginInfo.businessId == null){
        state.memberType = 0;
      }else {
        state.memberType = 1;
      }
    },

    logout(state){
      state.loginInfo = null;
      state.memberType = null;
    }
  },
  actions: {},
  modules: {},
});
