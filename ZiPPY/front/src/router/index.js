import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";
import shopMainView from "../views/shop/shopMainView.vue";
import categoryListView from "../views/shop/categoryListView.vue";
import keywordListView from "../views/shop/keywordListView.vue";

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
    path: "/shop/category",
    name: "categoryListView",
    component: categoryListView
  },
  {
    path: "/shop/keyword",
    name: "keywordListView",
    component: keywordListView
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
