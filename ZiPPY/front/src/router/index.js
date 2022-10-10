import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";
import PropertyView from "../views/property/PropertyView.vue";
import shopView from "../views/shopView.vue";
import moveContactFinalCheck from "../views/move/moveContactFinalCheck.vue";
import moveSelect from "../views/move/moveSelect";
import moveDate from "../views/move/moveDate";
import moveVisitDate from "../views/move/moveVisitDate";
import moveInfo from "../views/move/moveInfo";
import moveInfoDetail from "../views/move/moveInfoDetail";
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
    path:"/moveContact",
    name: "moveContact",
    component : moveVisitDate,
    props : true,
    children : [
      {
        path: "moveContactCheck",
        name: "moveContactCheck",
        component: moveContactFinalCheck,
        props: true, //변수를 받겠다. 
      },
    ]

  },
  {
    path:"/moveUntact",
    name: "moveUntact",
    component : moveInfoDetail,
    props : true,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
