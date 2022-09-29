import mainHeader from '../components/mainHeader.js';
import nav from '../components/mainNav.js';
import shopNav from '../components/shopNav.js';
import test from '../components/test.js';

let app = new Vue({
    el : '#app',
    components:{
        mainHeader,
        mainNav : nav,
        shopNav,
        test
    }
});