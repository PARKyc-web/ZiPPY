import Vue from "vue";
import VueRouter from "vue-router";
import HomeView from "../views/HomeView.vue";
import PropertyView from "../views/property/PropertyView.vue";
import HouseDetail from "../components/property/HouseDetail.vue";
import AgentDetail from "../components/property/AgentDetail.vue";

import shopView from "../views/shopView.vue";
import shopMain from "../views/shop/shopMainView.vue";
import shopList from "../views/shop/shopListView.vue";
import shopDetail from "../views/shop/shopDetailView.vue";
import shopCart from "../views/shop/shopCartView.vue";
import order from "../views/shop/orderView.vue";
import orderComplete from "../views/shop/orderCompleteView.vue";
import shopUpdatePro from "../views/shop/shopUpdateProView.vue";

import GeneralSign from '../views/login/generalSignUp.vue';
import BusinessSign from '../views/login/businessSignUp.vue';
import selectSign from '@/views/login/selectSignType.vue';
import login from '@/views/login/login.vue';
import admin from '@/views/admin/admin.vue';
import mypage from '@/views/mypage/mypageMain.vue';
import chat from '@/views/chat/chat.vue';
import chatDetail from '@/views/chat/chatDetail.vue';
import findUserInfo from '@/views/login/findInfo.vue';

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
import moveEstimate from "../views/move/moveEstimate";
import moveCompanyList from "../views/move/moveCompanyList";
import moveResult from "../views/move/moveResult";
import moveMain from "../views/move/moveMain";

import usedUpdate from "../views/used/usedUpdate.vue";
import usedDetail from "../views/used/usedDetail.vue";
import usedMain from "../views/used/usedMain.vue";
import usedInsert from "../views/used/usedInsert.vue";
import usedKeyword from "../views/used/usedKeyword.vue";
import usedwishList from "../views/used/usedWishList.vue";
import usedUserPage from "../views/used/usedUserPage.vue";

import page404 from '@/views/page404.vue';


Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "root",
    component: HomeView,
  },
  {
    path: "/home",
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
    path:"/login",
    name:"login",
    component:login
  },
  {
    path: "/findUserInfo",
    name: "findUserInfo",
    component :findUserInfo
  },


  {
    path:"/mypage",
    name:"mypage",
    component : mypage,
    children : []
  },

  {
    path: "/admin",
    name: "admin",
    component: admin,
    children : [
      {
        path :"home",
        name: "admin-home",
        component: HomeView, 
      },
      {
        path:"login",
        name:"admin-login",
        component:login
      }
    ]
  },

  {
    path:"/chat",
    name:"chat",
    component: chat
  },
  {
    path : "/chatDetail",
    name :"chatDetail",
    component : chatDetail    
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
    path: "/shop/main",
    name: "shopMain",
    component: shopMain,
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
  {
    path: "/shop/order",
    name: "order",
    component: order,
  },
  {
    path: "/shop/orderComplete",
    name: "orderComplete",
    component: orderComplete,
  },
  {
    path: "/shop/shopUpdateProVue",
    name: "shopUpdatePro",
    component: shopUpdatePro
  },

  //move
  {
    path: "/moveMain",
    name: "moveMain",
    component: moveMain,
  },
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
  {
    path: "/moveEstimate",
    name: "moveEstimate",
    component: moveEstimate,
    props: true,
  },
  {
    path: "/moveResult",
    name: "moveResult",
    component: moveResult,
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
  },
  {
    path: "/used/wish",
    name: "usedWishList",
    component: usedwishList
  },
  {
    path: "/used/user",
    name: "usedUserPage",
    component: usedUserPage
  },



  // 위에 있는 라우터의 경로에 아무곳에도 안걸리면 여기에 최종으로 걸린다.
  // 여기에 404 PAGE 하나 만들어서 넣어두면 됨.
  {
    path : "/*",
    name : "Error404",
    component : page404
  }

];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;