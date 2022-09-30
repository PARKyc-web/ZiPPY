import mainHeader from '../components/common/mainHeader.js';
import mainNav from '../components/common/mainNav.js';
import shopNav from '../components/shop/shopNav.js';
import slide from '../components/shop/slide.js';
import card from '../components/shop/card.js';
import nav from '../components/common/mainNav.js';
import usedMain from '../components/used/usedMain.js';
import businessLogin from '../components/login/businessLogin.js';
import usedDetail from '../components/used/usedDetail.js';

let app = new Vue({
    el : '#app',
    components:{
        mainHeader,
        mainNav,
        shopNav,
        usedMain,
        usedDetail,
        slide,
        card,
        businessLogin
    }
});