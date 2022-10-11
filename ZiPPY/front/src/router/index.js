import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";
import PropertyView from "../views/property/PropertyView.vue";
import HouseDetail from "../components/property/HouseDetail.vue";
import AgentDetail from "../components/property/AgentDetail.vue";
import shopView from "../views/shopView.vue";
import moveContactFinalCheck from "../views/move/moveContactFinalCheck.vue";
import moveUntactFinalCheck from "../views/move/moveUntactFinalCheck.vue";
import moveSelect from "../views/move/moveSelect";
import moveDate from "../views/move/moveDate";
import moveVisitDate from "../views/move/moveVisitDate";
import moveInfo from "../views/move/moveInfo";
import moveInfoDetail from "../views/move/moveInfoDetail";
import moveTypeSelect from "../views/move/moveTypeSelect";
import moveImage from "../views/move/moveImage";

import usedUpdate from "../views/used/usedUpdate.vue";
import usedDetail from "../views/used/usedDetail.vue";
import usedMain from "../views/used/usedMain.vue";
import usedInsert from "../views/used/usedInsert.vue";

import shopDetailView from "../views/shop/shopDetailView.vue";
import categoryList from "../views/shop/categoryListView.vue";
import keywordList from "../views/shop/keywordListView.vue";


Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/login",
    name: "login",
    component : HomeView,
  },
  {
    path: "/property/main",
    name : "property",
    component : PropertyView,
  },
  {
    path: "/property/HouseDetail",
    name : "HouseDetail",
    component : HouseDetail,
  },
  {
    path: "/property/AgentDetail",
    name : "AgentDetail",
    component : AgentDetail,
  },
  {
    path: "/shop",
    name: "shop",
    component: shopView,
  },

  {
    path:"/moveContact",
    name: "moveContact",
    component : moveVisitDate, 
    props : true,
 
  },
  {
    path:"/moveUntact",
    name: "moveUntact",
    component : moveInfoDetail,
    props : true,
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
    path : "/used",
    name : "used",
    component : usedMain,
  },
  {
    path : "/used/detail",
    name : "detail",
    component : usedDetail
  },
  {
    path : "/used/update",
    name : "update",
    component : usedUpdate
},
  /** 삭제 에정 **/
  {
    path: "/shop/detail",
    name: "detail",
    component: shopDetailView,
  },
  {
    path: "/shop/category",
    name: "category",
    component: categoryList,
  },
  {
    path: "/shop/keyword",
    name: "keyword",
    component: keywordList,
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
