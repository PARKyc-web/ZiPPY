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
import { SidebarMenu } from 'vue-sidebar-menu';
import '@/css/sidebar.css';

    export default {
        data() {
            return {
                menu: [
                    {
                        header: true,
                        title: '정보조회',
                        hiddenOnCollapse: true
                    },
                    {
                        href: '/admin',
                        title: 'Dashboard',
                        icon: 'fa fa-user'
                    },
                    {                           
                        href: '/admin/general',
                        title: '일반회원 조회',
                        icon: 'fa fa-chart-area'            
                    },
                    {
                        href: '/admin/business',
                        title: '기업회원 조회',
                        icon: 'fa fa-chart-area',                        
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
        max-width: 87%;
        /* margin-left: 150px; */
        /* margin-left: 5%; */
    }
</style>