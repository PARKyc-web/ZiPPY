import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from 'vuex-persistedstate';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    loginInfo : null,
    memberType : null,
    cartCount : 0,
  },
  getters: {
    getName : function(state){
      if(state.memberType == 0){
        return state.loginInfo.userName;
      } else {
        return state.loginInfo.compName;
      }
    }
  },
  mutations: {
    login(state, info){
      state.loginInfo = info;

      if(state.loginInfo.businessId == null || state.loginInfo.businessId == ""){
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
  plugins:[createPersistedState({storage:window.sessionStorage})],
});
