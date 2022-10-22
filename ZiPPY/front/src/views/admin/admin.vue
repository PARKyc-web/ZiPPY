<template>
    <div>
        <div id="side">
            <sidebar-menu :menu="menu"></sidebar-menu>
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
                menu: [
                    {
                        header: true,
                        title: 'Main Navigation',
                        hiddenOnCollapse: true
                    },
                    {
                        href: '/admin',
                        title: 'Dashboard',
                        icon: 'fa fa-user'
                    },
                    {                           
                        href: '/admin/home',
                        title: 'Charts',
                        icon: 'fa fa-chart-area'            
                    },
                    {
                        href: '/admin/login',
                        title: 'HOME',
                        icon: 'fa fa-chart-area',                        
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