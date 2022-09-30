import mainHeader from '../components/mainHeader.js';
import mainNav from '../components/mainNav.js';
import shopNav from '../components/shop/shopNav.js';
import slide from '../components/shop/slide.js';
import card from '../components/shop/card.js';
import nav from '../components/mainNav.js';
import usedMain from '../components/used/usedMain.js';
import businessLogin from '../components/login/businessLogin.js';


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