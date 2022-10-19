<template>
    <div id="maypage">
        <div id="side">
            <sidebar-menu :menu="menu"></sidebar-menu>
        </div>
        <router-view />        
    </div>
</template>

<script>
import { SidebarMenu } from 'vue-sidebar-menu';
import '@/css/sidebar.css';


// Data Level 1에 있는 애들은 사이드바의 첫번째 클릭하는 애들
// 
export default{
  data() {
            return {
                menu: [
                    {
                        header: true,
                        title: 'MENU',
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
                    {
                        href: '/mypage/order',
                        title: '구매목록',
                        icon: 'fa-solid fa-basket-shopping',                        
                    },
                    {
                        header: true,
                        title: 'Main Navigation',
                        hiddenOnCollapse: true
                    }
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