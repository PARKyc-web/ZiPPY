<template>
    <div id="maypage">
        <div id="side">
            <sidebar-menu :menu="menu"></sidebar-menu>
        </div>
        <div id="main">
            <router-view />
        </div>
    </div>
</template>

<script>
    import {
        SidebarMenu
    } from 'vue-sidebar-menu';
    import '@/css/sidebar.css';


    // Data Level 1에 있는 애들은 사이드바의 첫번째 클릭하는 애들
    // 
    export default {
        data() {
            return {
                menu: [
                    // 회원의 개인정보
                    {
                        header: true,
                        title: '회원정보',
                        hiddenOnCollapse: true
                    },
                    {
                        href: '/mypage',
                        title: '회원정보',
                        icon: 'fa fa-user'
                    },
                    {
                        href: '/mypage/chat',
                        title: '채팅목록',
                        icon: 'fa-solid fa-comment'
                    },


                    // 부동산 관련 정보
                    {
                        header: true,
                        title: '부동산',
                        hiddenOnCollapse: true
                    },
                    {
                        href: '/mypage',
                        title: '관심매물',
                        icon: 'fa-solid fa-house-circle-check'
                    },


                    //  쇼핑몰 관련 정보
                    {
                        header: true,
                        title: '쇼핑몰',
                        hiddenOnCollapse: true
                    },
                    {
                        href: '/mypage/order',
                        title: '장바구니',
                        icon: 'fa-solid fa-basket-shopping',
                    },
                    {
                        href: '/mypage/order',
                        title: '구매목록',
                        icon: 'fa-solid fa-list',
                    },


                    //  중고거래 관련 정보
                    {
                        header: true,
                        title: '중고거래',
                        hiddenOnCollapse: true
                    },
                    {
                        href: '/mypage/order',
                        title: '구매목록',
                        icon: 'fa-solid fa-list',
                    },
                    {
                        href: '/mypage/used/insert',
                        title: '상품등록',
                        icon: 'fa-solid fa-square-plus',
                    },


                    // 이사관련 정보
                    {
                        header: true,
                        title: '이사도우미',
                        hiddenOnCollapse: true
                    },
                    {
                        href: '/mypage',
                        title: '견적서',
                        icon: 'fa-solid fa-paste',
                    },
                    {
                        href: '/mypage',
                        title: '견적서',
                        icon: 'fa-solid fa-truck-moving',
                    },

                ]
            }
        },
        components: {
            SidebarMenu
        }
    }
</script>
<style scoped>
    #side {
        width: 13%;
        float: left;
    }
    #main{
        margin-left: 150px;
    }
</style>