import mainHeader from '../components/common/mainHeader.js';
import mainNav from '../components/common/mainNav.js';
import shopNav from '../components/shop/shopNav.js';
import shopMain from '../components/shop/shopMain.js';
import nav from '../components/common/mainNav.js';
import usedMain from '../components/used/usedMain.js';
import businessLogin from '../components/login/businessLogin.js';
import usedDetail from '../components/used/usedDetail.js';
import shopDetail from '../components/shop/shopDetail.js';
import categoryList from '../components/shop/categoryList.js';
import keywordList from '../components/shop/keywordList.js';

let app = new Vue({
    el : '#app',
    components:{
        mainHeader,
        mainNav,
        shopNav,
        usedMain,
        usedDetail,
        shopMain,
        businessLogin,
        shopDetail,
        categoryList,
        keywordList
    }
});