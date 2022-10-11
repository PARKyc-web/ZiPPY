import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";
import PropertyView from "../views/property/PropertyView.vue";
import shopView from "../views/shopView.vue";

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
    path: "/shop",
    name: "shop",
    component: shopView,
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
