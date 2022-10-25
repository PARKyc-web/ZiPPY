<template>
    <div>
        <div id="side">
            <sidebar-menu :menu="property" v-if="$store.state.loginInfo.memberType == 0"></sidebar-menu>
            <sidebar-menu :menu="shop" v-if="$store.state.loginInfo.memberType == 2"></sidebar-menu>
            <sidebar-menu :menu="move" v-if="$store.state.loginInfo.memberType == 3"></sidebar-menu>
        </div>
        <router-view />        
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
                        href: '/seller',
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
                        href: '/seller/home',
                        title: '상품등록',
                        icon: 'fa fa-chart-area'            
                    },
                    {
                        href: '/seller/login',
                        title: '상품조회',
                        icon: 'fa fa-chart-area',                        
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
                        href: '/seller/something',
                        title: '견적서 조회',
                        icon: 'fa fa-chart-area'            
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