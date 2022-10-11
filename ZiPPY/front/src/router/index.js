import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";
import PropertyView from "../views/property/PropertyView.vue";
import HouseDetail from "../components/property/HouseDetail.vue";
import AgentDetail from "../components/property/AgentDetail.vue";
import shopView from "../views/shopView.vue";

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
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
