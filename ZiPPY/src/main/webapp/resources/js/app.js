import mainHeader from '../components/mainHeader.js';
import mainNav from '../components/mainNav.js';
import shopNav from '../components/shop/shopNav.js';
import slide from '../components/shop/slide.js';
import card from '../components/shop/card.js';

let app = new Vue({
    el : '#app',
    components:{
        mainHeader,
        mainNav,
        shopNav,
        slide,
        card
    }
});