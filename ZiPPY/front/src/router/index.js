import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";
import PropertyView from "../views/property/PropertyView.vue";
import HouseDetail from "../components/property/HouseDetail.vue";
import AgentDetail from "../components/property/AgentDetail.vue";

import shopView from "../views/shopView.vue";
import shopList from "../views/shop/shopListView.vue";
import shopDetail from "../views/shop/shopDetailView.vue";
import shopCart from "../views/shop/shopCartView.vue";

import GeneralSign from '../views/login/generalSignUp.vue';
import BusinessSign from '../views/login/businessSignUp.vue';
import selectSign from '@/views/login/selectSignType.vue';
import baseImage from '@/views/login/base64Image.vue';

import moveContactFinalCheck from "../views/move/moveContactFinalCheck.vue";
import moveUntactFinalCheck from "../views/move/moveUntactFinalCheck.vue";
import moveSelect from "../views/move/moveSelect";
import moveDate from "../views/move/moveDate";
import moveVisitDate from "../views/move/moveVisitDate";
import moveInfo from "../views/move/moveInfo";
import moveInfoDetail from "../views/move/moveInfoDetail";
import moveTypeSelect from "../views/move/moveTypeSelect";
import moveImage from "../views/move/moveImage";
import moveTotal from "../views/move/moveTotal";
import moveCompanyList from "../views/move/moveCompanyList";

import usedUpdate from "../views/used/usedUpdate.vue";
import usedDetail from "../views/used/usedDetail.vue";
import usedMain from "../views/used/usedMain.vue";
import usedInsert from "../views/used/usedInsert.vue";
import usedKeyword from "../views/used/usedKeyword.vue";


Vue.use(VueRouter);

const routes = [{
    path: "/",
    name: "home",
    component: HomeView,
  },
  //login
  {
    path: "/signIn",
    name: "signIn",
    component: selectSign,
  },
  {
    path: "/generalSign",
    name: "generalSign",
    component: GeneralSign
  },
  {
    path: "/businessSign",
    name: "businessSign",
    component: BusinessSign
  },
  {
    path: "/baseImage",
    name: "baseImage",
    component: baseImage
  },

  // property
  {
    path: "/property/main",
    name: "property",
    component: PropertyView,
  },
  {
    path: "/property/HouseDetail",
    name: "HouseDetail",
    component: HouseDetail,
  },
  {
    path: "/property/AgentDetail",
    name: "AgentDetail",
    component: AgentDetail,
  },


  // shop
  {
    path: "/shop",
    name: "shop",
    component: shopView,
  },

  {
    path: "/shop/detail",
    name: "shoDetail",
    component: shopDetail,

  },
  {
    path: "/shop/list",
    name: "shopList",
    component: shopList,
  },
  {
    path: "/shop/cart",
    name: "shopCart",
    component: shopCart,
  },



  //move
  {
    path: "/moveTotal",
    name: "moveTotal",
    component: moveTotal,
  },
  {
    path: "/moveContact",
    name: "moveContact",
    component: moveVisitDate,
    props: true,

  },
  {
    path: "/moveUntact",
    name: "moveUntact",
    component: moveInfoDetail,
    props: true,
  },

  {
    path: "/moveContactCheck",
    name: "moveContactCheck",
    component: moveContactFinalCheck,
    props: true, //변수를 받겠다. 
  },
  {
    path: "/moveUntactImg",
    name: "moveUntactImg",
    component: moveImage,
    props: true,
  },
  {
    path: "/moveUntactCheck",
    name: "moveUntactCheck",
    component: moveUntactFinalCheck,
    props: true,
  },
  {
    path: "/moveCompanyList",
    name: "moveCompanyList",
    component: moveCompanyList,
    props: true,
  },

  //used
  {
    path: "/used",
    name: "used",
    component: usedMain,
  },
  {
    path: "/used/detail",
    name: "usedDetail",
    component: usedDetail
  },
  {
    path: "/used/update",
    name: "usedUpdate",
    component: usedUpdate
  },
  {
    path: "/used/insert",
    name: "usedInsert",
    component: usedInsert
  },
  {
    path: "/used/keyword",
    name: "usedKeyword",
    component: usedKeyword
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;