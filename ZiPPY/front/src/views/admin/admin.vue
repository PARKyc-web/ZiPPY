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


// Data Level 1에 있는 애들은 사이드바의 첫번째 클릭하는 애들
// 
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
    width: 15%;
    float :left;
}
</style>