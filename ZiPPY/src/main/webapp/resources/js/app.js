import mainHeader from '../components/mainHeader.js';
import nav from '../components/mainNav.js';
import shopNav from '../components/shopNav.js';
import usedMain from '../components/used/usedMain.js';

let app = new Vue({
    el : '#app',
    components:{
        mainHeader,
        mainNav : nav,
        shopNav,
        usedMain,
        usedDetail
    }
});