<template>
    <div>
        <nav-bar @click="categoryVal=$event.target.innerText"></nav-bar>
        <div id="container">
            <div id="used-seller-main">
                <div id="used-img">
                    <div id="used-img-img">
                        <img :src="'/zippy/common/img/member/'+data.profileImage" width="100px" height="100px" alt="">
                    </div>
                </div>
                <div 판매자정보 부분>
                    <div id="used-seller-name">
                        <div id="used-name-report">
                            <div>{{this.nickName}}</div>
                        </div>
                    </div>
                    <div class="used-point-report">
                        <div>
                            <h4>평점</h4>
                        </div>
                        <div>
                            <b-form-rating variant="warning" v-model="allTotal" no-border color="#64c481" readonly>
                            </b-form-rating>
                        </div>
                    </div>
                </div>
            </div>
            <hr>
            <div>
                <div>
                    <b-card no-body>
                        <b-tabs card>
                            <b-tab id="b-title" title="상품" active>
                                <b-card-text>
                                    <div @click="goDetail(list.productNo)" class="used-main-card"
                                        v-if="data.length != 0" v-for="list in data">
                                        <div>
                                            <div><img :src="'/zippy/common/img/used/'+list.mainImg" width="191px"
                                                    height="194px"></div>
                                            <div class="used-main-card-cont">
                                                <div class="used-main-card-title">{{list.productName}}</div>
                                                <div class="used-main-price-date">
                                                    <div class="used-main-card-price">{{list.productPrice}}원</div>
                                                    <div><span>{{list.productDate}}</span></div>
                                                    <div type="hidden"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="text-center" v-if="data.length >=11">
                                        <v-pagination v-model="page" :length="pageCount" circle color="#B3E3C3">
                                        </v-pagination>
                                    </div>
                                </b-card-text>
                            </b-tab>
                            <b-tab title="거래 후기">
                                <b-card-text>
                                    <h5>상점후기</h5>
                                    <hr>
                                    <div id="used-totla-profile" v-for="vue in list">
                                        <div id="used-profile">
                                            <img :src="'/zippy/common/img/member/'+data.profileImage" width="60px"
                                                height="60px" alt="프로필이미지">
                                        </div>
                                        <div>
                                            <div id="used-name-star" @click="goUserPage(vue.email)">
                                                <div id="">
                                                    <a href="">{{vue.nickName}}</a>
                                                </div>
                                                <div id="start-size">
                                                    <b-form-rating id="b-form" variant="warning"
                                                        v-model="vue.totalRating" no-border color="#64c481" readonly>
                                                    </b-form-rating>
                                                </div>
                                            </div>
                                            <div></div>
                                            <button id="used-user-btn" @click="goDetail(vue.productNo)">
                                                {{vue.productName}}
                                                <img src="	https://m.bunjang.co.kr/pc-static/resource/c5ce9d5a172b0744e630.png"
                                                    width="6px" height="10px" alt="">
                                            </button>
                                            <div id="used-review-cont">{{vue.reviewContent}}</div>
                                        </div>
                                    </div>
                                </b-card-text>
                            </b-tab>
                        </b-tabs>
                    </b-card>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import axios from 'axios';
    import navBar from '../../components/used/navBar.vue';
    export default {
        components: {
            navBar
        },
        data: () => ({
            data: "",
            page: 1,
            pageCount: 1,
            nickName: "",
            list: "",
            allTotal: ""
        }),
        watch: {
            page() {
                this.rewrite();
            }
        },
        async created() {
            this.rewrite();
            await this.showRvAll();
            var tSum = 0;
            for (var i in this.list) {
                tSum += Number(this.list[i].totalRating)

            }
            this.allTotal = Math.round(tSum / this.list.length * 10) / 10
        },
        methods: {
            rewrite() {
                axios({
                    url: "/zippy/used/userMain",
                    methods: "GET",
                    params: {
                        email: this.$route.query.email,
                        pageNum: this.page
                    }
                }).then(res => {
                    console.log(res);
                    this.data = res.data.list;
                    this.pageCount = res.data.pages;
                    this.nickName = this.data[0].nickName;
                    console.log(this.data)
                }).catch(error => {
                    console.log(error);
                })
            },
            async showRvAll() {
                var res = await axios({
                    url: "/zippy/used/rvAll",
                    methods: "GET",
                    params: {
                        email: this.$route.query.email
                    }
                })
                this.list = res.data;
                console.log(this.list)
            },
            goDetail(no) {
                console.log(no);
                this.$router.push('/used/detail?pNo=' + no);
            },
            goUserPage(email) {
                this.$router.push('/used/user?email=' + email);
            }
        }
    }
</script>

<style scoped>
    #container {
        width: 1200px;
        margin: 0 auto;
        margin-top: 20px;
    }

    #b-title {
        color: black;
    }

    #b-form {
        padding-left: 0;
    }

    #start-size {
        width: 90px;
    }

    a {
        color: black;
        text-decoration: none;
    }

    a:link {
        color: black;
        text-decoration: none;
    }

    a:visited {
        color: black;
        text-decoration: none;
    }

    .used-all-stars {
        width: 100px;
    }

    #used-start-num {
        margin-top: 13px;
    }

    .used-rates-star {
        width: 400px;
        display: flex;
        margin-top: 10px;
    }

    #used-total-start {
        width: 300px;
        display: flex;
    }

    #used-total-start span {
        margin-left: 15px;
    }

    .submitBtn {
        border: none;
        background-color: #b3e3c3;
        color: white;
        width: 150px;
        height: 50px;
        border-radius: 5px;
        float: right;
        margin: 10px 30px 10px 0;
    }

    .submitBtn:hover {
        background-color: #6dc78b;
    }

    #used-user-review {
        margin-top: 20px;
    }

    #used-user-review textarea {
        border: 1px solid rgb(238, 238, 238);
    }

    #used-user-star {
        padding-bottom: 20px;
        border-bottom: 1px solid rgb(238, 238, 238);
        margin-bottom: 30px;
    }

    #used-name-report {
        display: flex;
        margin-left: 20px;
    }

    #used-user-report {
        margin-top: 60px;
        margin-left: 20px;
    }

    .used-point-report {
        margin-left: 20px;
        margin-bottom: 20px;
        display: flex;
    }

    #used-img-img {
        width: 200px;
        height: 200px;
        position: inherit;
        display: flex;
        -webkit-box-pack: center;
        justify-content: center;
        -webkit-box-align: center;
        align-items: center;
        flex-direction: column;
        box-shadow: rgb(4 0 0 / 3%) 0px 5px 10px 0px;
        background-color: #B3E3C3;



    }

    #used-detail-report {
        color: rgb(204, 204, 204);
        font-size: 16px;
        display: inline-block;
        -webkit-box-align: center;
        align-items: center;
        border: none;
        margin-bottom: 20px;
    }


    #used-seller-name {
        font-size: 18px;
        height: 75px;
        font-weight: 600;
        display: flex;
        -webkit-box-align: center;
        align-items: center;
        -webkit-box-pack: justify;
        justify-content: space-between;
        flex-shrink: 0;
    }

    #used-img {
        position: inherit;
        height: 100%;
        -webkit-box-pack: center;
        justify-content: center;
        -webkit-box-align: center;
        align-items: center;
        flex-direction: column;
        box-shadow: rgb(4 0 0 / 3%) 0px 5px 10px 0px;
    }

    #used-img img {
        border-radius: 50%;
        margin-bottom: 15px;
    }

    #used-seller-main {
        display: flex;
        width: 100%;
        margin-bottom: 20px;
    }

    #used-review-cont {
        margin: 20px 0px;
        white-space: pre-wrap;
        line-height: 1.5;
    }

    #used-name-star {
        flex-direction: column;
        -webkit-box-pack: center;
        justify-content: center;
        height: 60px;
    }

    #used-totla-profile {
        margin-bottom: 10px;
        display: flex;
        border-bottom: 1px solid rgb(238, 238, 238);
    }

    #used-user-btn {
        padding: 0px 10px;
        height: 30px;
        border: 1px solid rgb(238, 238, 238);
        display: flex;
        -webkit-box-align: center;
        align-items: center;
        -webkit-box-pack: center;
        justify-content: center;
        font-size: 13px;
        background: rgb(255, 255, 255);
        color: rgb(102, 102, 102);
    }

    #used-user-btn img {
        margin-left: 10px;
    }

    #used-profile {
        width: 60px;
        margin-right: 15px;
        display: block;
    }

    #used-profile img {
        border-radius: 50%;
    }


    .used-main-card {
        border: 1px solid #eeeeee;
        width: 193px;
        height: auto;
        display: inline-block;
        margin: 20px;
        border-radius: 0.8rem;
    }

    .used-main-card img{
        border-top-left-radius: 0.8rem;
        border-top-right-radius: 0.8rem;
    }



    .used-main-card:hover {
        cursor: pointer;
    }

    .used-main-card-title {
        overflow: hidden;
        padding-bottom: 20px;
        font-weight: 500;
        text-overflow: ellipsis;
    }

    .used-main-card-cont {
        padding: 15px 10px
    }

    .used-main-price-date {
        display: flex;
        justify-content: space-between;
    }

    .used-main-card-price {
        font-size: 16px;
        font-weight: 600;
        text-overflow: ellipsis;
        white-space: nowrap;
        overflow: hidden;
    }

    .used-main-price-date span {
        color: #888888;
        font-size: smaller;
    }

    /*input 바로 다음의 label*/
    input#chk_top+label {
        cursor: pointer;
    }

    /*input 바로 다음의 label:before 에 체크하기 전 CSS 설정*/
    input#chk_top+label:before {
        content: "";
        display: inline-block;
        width: 17px;
        height: 17px;
        line-height: 17px;
        border: 1px solid #cbcbcb;
        vertical-align: middle;
        /*체크 전과 체크 후 높이 차이 때문에 설정*/
    }

    .chk_box {
        margin-top: 30px;
    }

    /*checked된 input 바로 다음의 label:before 에 체크 후 CSS 설정*/
    input#chk_top:checked+label:before {
        content: "\f00c";
        /*폰트어썸 유니코드*/
        font-family: "Font Awesome 5 free";
        /*폰트어썸 아이콘 사용*/
        font-weight: 900;
        /*폰트어썸 설정*/
        color: #fff;
        background-color: #B3E3C3;
        border-color: #B3E3C3;
        font-size: 13px;
        text-align: center;
    }
</style>