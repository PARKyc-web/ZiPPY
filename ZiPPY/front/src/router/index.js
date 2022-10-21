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
import orderSheet from "../views/shop/orderSheetView.vue";
import shopOrderList from "@/views/shop/shopOrderListView.vue";

import GeneralSign from '../views/login/generalSignUp.vue';
import BusinessSign from '../views/login/businessSignUp.vue';
import selectSign from '@/views/login/selectSignType.vue';
import login from '@/views/login/login.vue';
import admin from '@/views/admin/admin.vue';
import mypage from '@/views/mypage/mypageMain.vue';
import chat from '@/views/chat/chatTap.vue';
import chatDetail from '@/views/chat/chatTap.vue';
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
import moveMyList from "../views/move/moveMyList";
import moveResult from "../views/move/moveResult";
import moveMain from "../views/move/moveMain";
import moveCompanyEstimate from "../views/move/moveCompanyEstimate";
import moveCompanyMy from "../views/move/moveCompanyMy";
import moveMyReserve from "../views/move/moveMyReserve";

import usedUpdate from "../views/used/usedUpdate.vue";
import usedDetail from "../views/used/usedDetail.vue";
import usedMain from "../views/used/usedMain.vue";
import usedInsert from "../views/used/usedInsert.vue";
import usedKeyword from "../views/used/usedKeyword.vue";
import usedwishList from "../views/used/usedWishList.vue";
import usedUserPage from "../views/used/usedUserPage.vue";


import userInfo from '@/views/mypage/userInfo.vue';
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
    children : 
    [
      {
        path : "/",
        name : "mypage-main",
        component : userInfo
      },
      {
        path : "chat",
        name : "mypage-chat",
        component : chat
      },
      {
        path : "order",
        name : "mypage-order",
        component : order
      },
      {
        path : "myOrder",
        name : "shopOrderList",
        component : shopOrderList
      },
      {
        path : "myCart",
        name : "shopCart",
        component : shopCart
      },
      {
        path : "used/insert",
        name : "mypage-insert-product",
        component : usedInsert

      }
    ]
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
    component: shopMain,
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
    path: "/shop/shopUpdatePro",
    name: "shopUpdatePro",
    component: shopUpdatePro
  },
  {
    path: "/shop/orderSheet",
    name: "shopOrderSheet",
    component: orderSheet
  },

  //move
  {
    path: "/move",
    name: "moveMain",
    component: moveMain,
  },
  {
    path: "/move/moveTotal",
    name: "moveTotal",
    component: moveTotal,
  },
  {
    path: "/move/moveContact",
    name: "moveContact",
    component: moveVisitDate,
    props: true,

  },
  {
    path: "/move/moveUntact",
    name: "moveUntact",
    component: moveInfoDetail,
    props: true,
  },

  {
    path: "/move/moveContactCheck",
    name: "moveContactCheck",
    component: moveContactFinalCheck,
    props: true, //변수를 받겠다. 
  },
  {
    path: "/move/moveUntactImg",
    name: "moveUntactImg",
    component: moveImage,
    props: true,
  },
  {
    path: "/move/moveUntactCheck",
    name: "moveUntactCheck",
    component: moveUntactFinalCheck,
    props: true,
  },
  {
    path: "/move/moveMyList",
    name: "moveMyList",
    component: moveMyList,
    props: true,
  },
  {
    path: "/move/moveEstimate",
    name: "moveEstimate",
    component: moveEstimate,
    props: true,
  },
  {
    path: "/move/moveResult",
    name: "moveResult",
    component: moveResult,
    props: true,
  },
  {
    path: "/move/moveCompanyEstimate",
    name: "moveCompanyEstimate",
    component: moveCompanyEstimate,
    props: true,
  },
  {
    path: "/move/moveCompanyMy",
    name: "moveCompanyMy",
    component: moveCompanyMy,
    props: true,
  },
  {
    path: "/move/moveMyReserve",
    name: "moveMyReserve",
    component: moveMyReserve,
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