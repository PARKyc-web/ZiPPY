<template>
    <div>
        <nav-bar @click="search($event)"></nav-bar>
        <div id="container">
            <div id="used-seller-main">
                <div id="used-img">
                    <div id="used-img-img">
                        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQkW5iRqvi6VdPWVWYswwWoUYhmW-AA2W8P0tExfMLx3wWPiwVFHegzq29vq8KoN1jKVxQ&usqp=CAU"
                            width="100px" height="100px" alt="">
                    </div>
                </div>
                <div 판매자정보 부분>
                    <div id="used-seller-name">
                        <div id="used-name-report">
                            <div>닉네임 부분</div>

                        </div>
                    </div>
                    <div class="used-point-report">
                        <div>
                            <h4>평점</h4>
                        </div>
                        <div>
                            <img src="	https://m.bunjang.co.kr/pc-static/resource/44c1240e63c64f221877.png" width="30px"
                                height="29px" alt="별점 1">
                            <img src="	https://m.bunjang.co.kr/pc-static/resource/44c1240e63c64f221877.png" width="30px"
                                height="29px" alt="별점 1">
                            <img src="	https://m.bunjang.co.kr/pc-static/resource/44c1240e63c64f221877.png" width="30px"
                                height="29px" alt="별점 1">
                            <img src="	https://m.bunjang.co.kr/pc-static/resource/982587b0e24b8bccea13.png" width="30px"
                                height="29px" alt="별점 0">
                            <img src="	https://m.bunjang.co.kr/pc-static/resource/982587b0e24b8bccea13.png" width="30px"
                                height="29px" alt="별점 0">
                        </div>
                    </div>
                    <div id="used-user-report">
                        <button id="used-detail-report">
                            <img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAbBJREFUeNrsls9LAkEcxb+au+JSGIEQyGbBdg881CmConsE9Qd07S/p0qG69RcUdOzSoUt0UujS3c0kEQULU7R17c2wyhazNbvaesgHH9xZh3kzb35tpNfrUV/5fJ7+Stls9ks5FqCNKTDv/L6AjyAdiUrWi4AdcAXewDMwwTu4AQdAHbXxCrh3TJm55vpPAVvgHDyC7VEZM6M7sCbRlgGuwaGM8U9zvAkunbn0M/8nzvNpkBHr4MKnqVvHYDWI8RGY84wpFiNFUX5L8sxZlNJRL4E9UeVkMknpdJoSiQQvt9ttKpfLVK1WhVsXbIBb2RHvi96nUikyDGNgyhSPxymTyZCu614D2/UT9bqooqZpnrm6OyNYoNLGy6KKxWKRR/td3W6XCoWCV/sLfoyFJ5Bt29zAfbb3O9TpdLzaZzFND3NkcjUaDapUKoNyvV6nWq0ms7eHM2YqlUrUarXIsiwyTTPwbeX7dmJRs8hVVeXmoRkzNZtNzjCK0pg0MQ5NosX1BKwRetjCb6lcLje2ES+GHDk72G1m/MCu2hCNZ8HrZDv9j33MVtlMiJ58X38KMADfFnDPWur9bAAAAABJRU5ErkJggg=="
                                width="15" height="15" alt="신고 아이콘" />신고하기
                        </button>
                    </div>
                </div>
            </div>
            <hr>
            <div>
                <div>
                    <b-card no-body>
                        <b-tabs card>
                            <b-tab title="상품" active>
                                <b-card-text>
                                    <div @click="goDetail(list.productNo)" class="used-main-card"
                                        v-if="data.length != 0" v-for="list in data">
                                        <div>
                                            <div><img src="/used/FIzrqY3agAI_Nyy.jpg" width="194px" height="194px">
                                            </div>
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
                                </b-card-text>
                            </b-tab>
                            <b-tab title="거래 후기">
                                <b-card-text>
                                    <h5>상점후기</h5>
                                    <hr>
                                    <div id="used-totla-profile">
                                        <div id="used-profile">
                                            <a href="">
                                                <img src="data:image/png;base64,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"
                                                    width="60px" height="60px" alt="">
                                            </a>
                                        </div>
                                        <div>
                                            <div id="used-name-star">
                                                <div>
                                                    <a href="">test1</a>
                                                </div>
                                                <a href="">
                                                    <div>
                                                        <img src="	https://m.bunjang.co.kr/pc-static/resource/44c1240e63c64f221877.png"
                                                            width="15px" height="14px" alt="별점 1">
                                                        <img src="	https://m.bunjang.co.kr/pc-static/resource/60405218836726829fe3.png"
                                                            width="15px" height="14px" alt="별점 반">
                                                        <img src="	https://m.bunjang.co.kr/pc-static/resource/982587b0e24b8bccea13.png"
                                                            width="15px" height="14px" alt="별점 0">
                                                        <img src="	https://m.bunjang.co.kr/pc-static/resource/982587b0e24b8bccea13.png"
                                                            width="15px" height="14px" alt="별점 0">
                                                        <img src="	https://m.bunjang.co.kr/pc-static/resource/982587b0e24b8bccea13.png"
                                                            width="15px" height="14px" alt="별점 0">
                                                    </div>
                                                </a>
                                            </div>
                                            <div></div>
                                            <a href="">
                                                <button id="used-user-btn">
                                                    제품명 section
                                                    <img src="	https://m.bunjang.co.kr/pc-static/resource/c5ce9d5a172b0744e630.png"
                                                        width="6px" height="10px" alt="">
                                                </button>
                                            </a>
                                            <div id="used-review-cont">후기 내용 나올 파트</div>
                                        </div>
                                    </div>
                                    <div id="used-totla-profile">
                                        <div id="used-profile">
                                            <a href="">
                                                <img src="data:image/png;base64,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"
                                                    width="60px" height="60px" alt="">
                                            </a>
                                        </div>
                                        <div>
                                            <div id="used-name-star">
                                                <div>
                                                    <a href="">test1</a>
                                                </div>
                                                <a href="">
                                                    <div>
                                                        <img src="	https://m.bunjang.co.kr/pc-static/resource/44c1240e63c64f221877.png"
                                                            width="15px" height="14px" alt="별점 1">
                                                        <img src="	https://m.bunjang.co.kr/pc-static/resource/60405218836726829fe3.png"
                                                            width="15px" height="14px" alt="별점 반">
                                                        <img src="	https://m.bunjang.co.kr/pc-static/resource/982587b0e24b8bccea13.png"
                                                            width="15px" height="14px" alt="별점 0">
                                                        <img src="	https://m.bunjang.co.kr/pc-static/resource/982587b0e24b8bccea13.png"
                                                            width="15px" height="14px" alt="별점 0">
                                                        <img src="	https://m.bunjang.co.kr/pc-static/resource/982587b0e24b8bccea13.png"
                                                            width="15px" height="14px" alt="별점 0">
                                                    </div>
                                                </a>
                                            </div>
                                            <div></div>
                                            <a href="">
                                                <button id="used-user-btn">
                                                    제품명 section
                                                    <img src="	https://m.bunjang.co.kr/pc-static/resource/c5ce9d5a172b0744e630.png"
                                                        width="6px" height="10px" alt="">
                                                </button>
                                            </a>
                                            <div id="used-review-cont">후기 내용 나올 파트</div>
                                        </div>
                                    </div>
                                </b-card-text>
                            </b-tab>
                            <b-tab title="후기 작성">
                                <b-card-text>
                                    <div>
                                        <div id="used-user-star">평점 3/5
                                            <img src="	https://m.bunjang.co.kr/pc-static/resource/44c1240e63c64f221877.png"
                                                width="20px" height="19px" alt="별점 1">
                                            <img src="	https://m.bunjang.co.kr/pc-static/resource/44c1240e63c64f221877.png"
                                                width="20px" height="19px" alt="별점 1">
                                            <img src="	https://m.bunjang.co.kr/pc-static/resource/44c1240e63c64f221877.png"
                                                width="20px" height="19px" alt="별점 1">
                                            <img src="	https://m.bunjang.co.kr/pc-static/resource/982587b0e24b8bccea13.png"
                                                width="20px" height="19px" alt="별점 0">
                                            <img src="	https://m.bunjang.co.kr/pc-static/resource/982587b0e24b8bccea13.png"
                                                width="20px" height="19px" alt="별점 0">
                                        </div>
                                        <div>
                                            <div>
                                                <h4>짧은 글 후기</h4>
                                            </div>
                                            <div id="used-user-review">
                                                <textarea name="" id="" cols="140" rows="10"
                                                    placeholder="후기를 남겨주세요"></textarea>
                                            </div>
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
            data: [],

        }),
        created() {
            axios({
                url: "http://localhost:8088/zippy/used/main",
                methods: "GET",
                params: {
                    location: "",
                    keyword: "",
                    category: "",
                    checked: "",
                    dropbox: ""
                }
            }).then(res => {
                console.log(res);
                this.data = res.data;
                console.log(this.data);
            }).catch(error => {
                console.log(error);
            })
        },
        methods: {
            goDetail(no) {
                console.log(no);
                // var cardNo = this.data.findIndex(i => i.productNo == productNo);
                // console.log(cardNo);
                this.$router.push('/used/detail?pNo=' + no);
            }
        }
    }
</script>

<style scoped>
    #container {
        width: 1200px;
        margin: 0 auto;
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
        width: 194px;
        height: auto;
        display: inline-block;
        margin: 20px;
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