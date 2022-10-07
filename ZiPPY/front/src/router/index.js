import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";
import shopMainView from "../views/shop/shopMainView.vue";
import categoryListView from "../views/shop/categoryListView.vue";
import keywordListView from "../views/shop/keywordListView.vue";
import shopDetailView from "../views/shop/shopDetailView.vue";
import cartView from "../views/shop/cartView.vue";
import moveFinalCheck from "../views/move/moveFinalCheck.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/move",
    name: "move",
    component: moveFinalCheck,
    props:true, //변수를 받겠다. 
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
