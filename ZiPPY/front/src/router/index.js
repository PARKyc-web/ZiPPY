import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";
import PropertyView from "../views/property/PropertyView.vue";
import shopView from "../views/shopView.vue";
import usedDetail from "../views/used/usedDetail.vue";

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
    component : usedDetail,
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
