<template>

    <div class="review-wrap">

        <move-nav-bar @click="categoryVal=$event.target.innerText"></move-nav-bar>


        <v-card>
            <div id="used-seller-main">

                <!-- 업체정보 -->
                <div class="loaf">
                    <div class="intro">
                        <div>{{list[0].compName}}</div>
                        <div>{{list[0].phone}}</div>
                        <div>{{list[0].compAddress}}</div>
                        <div>{{list[0].compIntro}}</div>
                    </div>

                    <div class="loaf">
                        <div class="used-point-report">


                            <!-- 별점 박스 -->
                            <div id="star-box">
                                <div id="star-left">
                                    <div class="mt-1">
                                        <v-rating v-model="proTotalRating" half-increments
                                            background-color="#64c481 lighten-3" color="#64c481" large readonly>
                                        </v-rating>
                                    </div>
                                    <h2 class="ml-3 mt-3" style="font-weight:bold" v-if="list.length != 0">
                                        평점{{Math.round(list[0].companyTotalRating * 10)/10}}</h2>
                                </div>
                                <div id="star-right">
                                    <v-progress-linear color="#64c481" height="20" :value=rate1 style="width:200px">
                                    </v-progress-linear>
                                    <h6>손이 빨라요</h6>
                                    <v-progress-linear color="#64c481" height="20" :value=rate2 style="width:200px">
                                    </v-progress-linear>
                                    <h6>전문적이에요</h6>
                                    <v-progress-linear color="#64c481" height="20" :value=rate3 style="width:200px">
                                    </v-progress-linear>
                                    <h6>친절해요</h6>
                                    <v-progress-linear color="#64c481" height="20" :value=rate4 style="width:200px">
                                    </v-progress-linear>
                                    <h6>마무리가 깔끔해요</h6>
                                </div>
                            </div>
                            <!-- 별점 박스 끝 -->
                        </div>

                    </div>

                </div>
            </div>

        </v-card>


        <div class="review_area">
            <b-tabs content-class="mt-3">
                <b-tab title="이사후기">
                    <v-card>
                        <v-data-table :headers="headers" :items="list">
                            <!-- 별점 -->
                            <template v-for="item in list" v-slot:item.reviewContent="{ item }">
                                <div class="pa-5">
                                    <v-rating v-model="item.totalRating" half-increments
                                        background-color="#64c481 lighten-3" color="#64c481" small readonly></v-rating>
                                    <div class="ml-2">{{item.reviewContent}}</div>
                                </div>
                            </template>
                        </v-data-table>
                    </v-card>
                </b-tab>
            </b-tabs>
        </div>


    </div>
</template>

<script>
    import MoveNavBar from '@/components/move/MoveNavBar.vue';
    import axios from 'axios';
    export default {
        components: {
            MoveNavBar
        },
        data: function () {
            return {
                nickName: this.$store.state.loginInfo.nickName,
                list: [],

                data: [],
                page: 1,
                pageCount: 1,
                estimateType: "",
                serviceId: "",
                pageNum: "",
                compName: "",
                compAddress: "",
                compIntro: "",
                phone: "",
                //은하

                //후기 
                reviewContent: '',
                serviceType: 3,
                totalRating: '',
                companyTotalRating : '',
                rate1: '',
                rate2: '',
                rate3: '',
                rate4: '',
                writer: '',
                singleSelect: false,
                reviews: [],
                headers: [{
                        text: '',
                        align: '',
                        sortable: false,
                        value: 'PRO_MAIN_IMG',
                    },
                    {
                        text: '후기번호',
                        sortable: false,
                        value: 'reviewNo'
                    },
                    {
                        text: '작성자',
                        sortable: false,
                        value: 'writer'
                    },
                    {
                        text: '후기내용',
                        sortable: false,
                        value: 'reviewContent'
                    },
                    {
                        text: '작성일',
                        value: 'reviewDate'
                    }
                ],
                /* 리뷰 */
                proTotalRating: '',
                rate1: '',
                rate2: '',
                rate3: '',
                rate4: '',
            }
        },
        watch: {
            page() {
                this.rewrite();
            }
        },
        async created() {

            //후기 가져오기
            let res = await axios({
                url: "/zippy/move/moveReview",
                methods: "GET",
                params: {
                    serviceId: this.$route.query.serviceId,
                    pageNum: this.page,
                }
            })
            this.list = res.data;
            this.pageCount = res.data.pages;
            this.nickName = this.$store.state.loginInfo.nickName;
            this.companyTotalRating = res.data.companyTotalRating;

            //별점 계산
            var tSum = 0;
            var r1Sum = 0;
            var r2Sum = 0;
            var r3Sum = 0;
            var r4Sum = 0;
            for (var i in this.list) {
                tSum += Number(this.list[i].totalRating)
                r1Sum += Number(this.list[i].rate1)
                r2Sum += Number(this.list[i].rate2)
                r3Sum += Number(this.list[i].rate3)
                r4Sum += Number(this.list[i].rate4)
            }
            this.proTotalRating = Math.round(tSum / this.list.length * 10) / 10
            this.rate1 = Math.round(r1Sum / this.list.length) * 10
            this.rate2 = Math.round(r2Sum / this.list.length) * 10
            this.rate3 = Math.round(r3Sum / this.list.length) * 10
            this.rate4 = Math.round(r4Sum / this.list.length) * 10
            console.log('총:', this.proTotalRating);
            console.log('1:', this.rate1);
            console.log('2:', this.rate2);
            console.log('3:', this.rate3);
            console.log('4:', this.rate4);
        },

        methods: {

            //리뷰 등록
            insertReview() {
                if (!this.rate1 || !this.rate2 || !this.rate3 || !this.rate4 || !this.reviewContent) {
                    swal.fire({
                        icon: 'warning',
                        title: '별점과 리뷰를 입력해주세요.',
                        showConfirmButton: false,
                        timer: 1500
                    });
                } else {
                    //totalRating 입력
                    this.totalRating =
                        (Number(this.rate1) +
                            Number(this.rate2) +
                            Number(this.rate3) +
                            Number(this.rate4)) / 4

                    //리뷰등록        
                    axios({
                        url: "/zippy/common/addRv",
                        method: "POST",
                        headers: {
                            "Content-Type": "application/json"
                        },
                        data: {
                            email: this.$store.state.loginInfo.email,
                            reviewTitle: 0,
                            reviewContent: this.reviewContent,
                            serviceType: 3,
                            serviceId: this.list.email,
                            viewCnt: 0,
                            totalRating: this.totalRating,
                            rate1: this.rate1,
                            rate2: this.rate2,
                            rate3: this.rate3,
                            rate4: this.rate4,
                        }
                    }).then(res => {
                        console.log(res);
                        swal.fire({
                            icon: 'success',
                            title: '리뷰가 등록되었습니다.',
                            showConfirmButton: false,
                            timer: 1500
                        });
                    }).catch(error => {
                        console.log(error);
                    })
                }
            },



        }
    }
</script>

<style scoped>
    .loaf {
        display: flex;
        width: 500px;
    }

    /* 은하 */
    #cart-con {
        width: 70%;
    }

    .v-toolbar__title {
        font-weight: bold;
    }

    .v-application a {
        text-decoration: none;
        color: rgba(0, 0, 0, 0.87) !important;
    }

    .v-btn {
        font-weight: bold;
    }

    span:hover {
        text-decoration: underline;
        cursor: pointer;
    }

    /*  */
    .review-wrap {
        width: 100%;
    }

    .v-card {
        margin: 50px;
        height: auto;
    }

    .review_area {
        margin: 100px;
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

    .intro {
        font-size: 20px;
        height: 100px;
        font-weight: 600;
        -webkit-box-align: center;
        align-items: center;
        -webkit-box-pack: justify;
        justify-content: space-between;
        flex-shrink: 0;
        margin: 50px 300px 50px 500px;
        line-height: 50px;
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
        display: inline-block;
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