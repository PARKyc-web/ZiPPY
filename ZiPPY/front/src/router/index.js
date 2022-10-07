import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";
import PropertyView from "../views/property/PropertyView.vue";
import shopView from "../views/shopView.vue";
import moveFinalCheck from "../views/move/moveFinalCheck.vue";
import moveSelect from "../views/move/moveSelect";
import moveDate from "../views/move/moveDate";
import moveInfo from "../views/move/moveInfo";
import moveTypeSelect from "../views/move/moveTypeSelect";

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
    path: "/move",
    name: "move",
    component: moveFinalCheck,
    props: true, //변수를 받겠다. 
  },
  {
    path: "/move",
    name: "moveSelect",
    component: moveSelect,
    children: [
      {
        path: "moveDate",
        component: moveDate,
      },
      {
        path: "moveInfo",
        component: moveInfo,
      },
      {
        path: "moveTypeSelect",
        component: moveTypeSelect,
      },
    ]
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
