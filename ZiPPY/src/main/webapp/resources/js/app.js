import mainHeader from '../components/mainHeader.js';
import nav from '../components/mainNav.js';
import shopNav from '../components/shopNav.js';
import slide from '../components/shop/slide.js';
import card from '../components/shop/card.js';
import businessLogin from '../components/login/businessLogin.js';

let app = new Vue({
    el : '#app',
    components:{
        mainHeader,
        mainNav : nav,
        shopNav,
        slide,
        card,
        businessLogin
    }
});