import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";
import shopMainView from "../views/shop/shopMainView.vue";
import categoryListView from "../views/shop/categoryListView.vue";
import keywordListView from "../views/shop/keywordListView.vue";
import shopDetailView from "../views/shop/shopDetailView.vue";
import cartView from "../views/shop/cartView.vue";
import usedMain from "../views/used/usedMain.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/shop/main",
    name: "shopMain",
    component: shopMainView
  },
  {
    path : "/used",
    name : "used",
    component : usedMain
  },
  {
    path: "/shop/category",
    name: "categoryListView",
    component: categoryListView
  },
  {
    path: "/shop/keyword",
    name: "keywordListView",
    component: keywordListView
  },
  {
    path: "/shop/detail",
    name: "shopDetailView",
    component: shopDetailView
  },
  {
    path: "/shop/cart",
    name: "cartView",
    component: cartView
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
