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
                        href: '/mypage/MyWishProperties',
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
                        href: '/mypage/myCart',
                        title: '장바구니',
                        icon: 'fa-solid fa-basket-shopping',
                    },
                    {
                        href: '/mypage/myShopWish',
                        title: '관심상품',
                        icon: 'fa-solid fa-heart',                        
                    },      
                    {
                        href: '/mypage/myOrder',
                        title: '구매내역',
                        icon: 'fa-solid fa-credit-card',                        
                    },                    
                    {
                        href: '/mypage/myRv',
                        title: '후기내역',
                        icon: 'fa-solid fa-pen',                        
                    },       
                    {
                        href: '/mypage/myQna',
                        title: '문의내역',
                        icon: 'fa-solid fa-q',                        
                    },      


                    //  중고거래 관련 정보
                    {
                        header: true,
                        title: '중고거래',
                        hiddenOnCollapse: true
                    },
                    {
                        href: '/mypage/used/myPage?email='+this.$store.state.loginInfo.email,
                        title: '마이페이지',
                        icon: 'fa-solid fa-user',
                    },
                    {
                        href: '/mypage/used/insert',
                        title: '상품등록',
                        icon: 'fa-solid fa-square-plus',
                    },
                    {
                        href: '/mypage/used/keyword',
                        title: '키워드',
                        icon: 'fa-solid fa-bookmark',
                    },
                    {
                        href: '/mypage/used/wish',
                        title: '찜 목록',
                        icon: 'fa-solid fa-heart',
                    },


                    // 이사관련 정보
                    {
                        header: true,
                        title: '이사도우미',
                        hiddenOnCollapse: true
                    },
                    {
                        href: '/mypage/move/moveResult',
                        title: '이사요청',
                        icon: 'fa-solid fa-truck-moving',
                    },
                    {
                        href: '/mypage/move/moveMyList',
                        title: '견적서',
                        icon: 'fa-solid fa-paste',
                    },
                    {
                        href: '/mypage/move/moveMyReserve',
                        title: '예약목록',
                        icon: 'fa-solid fa-list',
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
        /* height: 100vh; */
        /* position:fixed;
        top: 80px; */
    }
    #main{
        width: 87%;
        float:left;
        /* height: 100vh;      */
        /* height: 89vh; */
    }

     #maypage{
       border-top:1px solid #D6D6D6;
    }  
</style>