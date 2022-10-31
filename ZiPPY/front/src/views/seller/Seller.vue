<template>
    <div style="border-top:1px solid #D6D6D6;">
        <div id="side">
            <sidebar-menu :menu="property" v-if="$store.state.loginInfo.memberType == 0"></sidebar-menu>
            <sidebar-menu :menu="shop" v-if="$store.state.loginInfo.memberType == 2"></sidebar-menu>
            <sidebar-menu :menu="move" v-if="$store.state.loginInfo.memberType == 3"></sidebar-menu>
        </div>
        <router-view />
        <div style="padding-top:100px"></div>     
    </div>
</template>

<script>
import { SidebarMenu } from 'vue-sidebar-menu';
import '@/css/sidebar.css';

export default{
  data() {
            return {
                property: [
                    {
                        header: true,
                        title: '부동산 판매자 메뉴',
                        hiddenOnCollapse: true
                    },
                    {
                        href: '/seller',
                        title: '정보조회',
                        icon: 'fa fa-user'
                    },
                    {                           
                        href: '/seller/SellerInsertProperty',
                        title: '매물등록',
                        icon: 'fa fa-chart-area'            
                    },
                    {
                        href: '/seller/SellerPropertyList',
                        title: '매물조회',
                        icon: 'fa fa-chart-area',                        
                    }
                ],

                shop : [
                    {
                        header: true,
                        title: '쇼핑몰 판매자 메뉴',
                        hiddenOnCollapse: true
                    },
                    {
                        href: '/seller',
                        title: '정보조회',
                        icon: 'fa fa-chart-area',    
                    },
                    {                           
                        href: '/seller/shopSellerPro',
                        title: '상품목록',
                        icon: 'fa fa-shop'            
                    },
                    {
                        href: '/seller/shopInsertPro',
                        title: '상품등록',
                        icon: 'fa fa-couch',                        
                    },
                    {
                        href: '/seller/shopSellerOrder',
                        title: '판매내역',
                        icon: 'fa fa-truck-fast',                        
                    },
                    {
                        href: '/seller/shopSellerQna',
                        title: 'CS내역',
                        icon: 'fa-solid fa-a',                        
                    }
                ],

                move : [
                    {
                        header: true,
                        title: '이사도우미 판매자 메뉴',
                        hiddenOnCollapse: true
                    },
                    {
                        href: '/seller',
                        title: '정보조회',
                        icon: 'fa fa-chart-area',    
                    },
                    {                           
                        href: '/seller/move/moveEstimate',
                        title: '견적요청조회',
                        icon: 'fa-solid fa-list'            
                    },
                    {                           
                        href: '/seller/move/moveCompanyMy',
                        title: '보낸견적서',
                        icon: 'fa-solid fa-paste'            
                    },                      
                ]
            }
        },
  components : {
    SidebarMenu
  }
}

</script>
<style scoped>
#side {
    width: 13%;
    float :left;
}
</style>