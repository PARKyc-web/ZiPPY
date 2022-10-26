<template>

    <div class="review-wrap">

        <move-nav-bar @click="categoryVal=$event.target.innerText"></move-nav-bar>


        <v-card>
            <div id="used-seller-main">
                <!-- <div id="used-img">
                    <div id="used-img-img">
                        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQkW5iRqvi6VdPWVWYswwWoUYhmW-AA2W8P0tExfMLx3wWPiwVFHegzq29vq8KoN1jKVxQ&usqp=CAU"
                            width="100px" height="100px" alt="">
                    </div>
                </div> -->

                <!-- 업체정보 -->
                <div class="loaf">
                    <div id="used-seller-name">

                        <div id="used-name-report">
                            <!-- <div>{{this.nickName}}</div> -->
                            <div>NO.{{list.compName}}</div>
                        </div>
                    </div>
                    <div id="used-seller-name">

                        <div id="used-name-report">
                            <div>{{list.phone}}</div>
                        </div>
                    </div>
                    <div id="used-seller-name">

                        <div id="used-name-report">
                            <div>{{list.compAddress}}</div>
                        </div>
                    </div>
                    <div id="used-seller-name">

                        <div id="used-name-report">
                            <div>{{list.compIntro}}</div>
                        </div>
                    </div>

                    <div class="right-loaf">
                        <div class="used-point-report">


                            <!-- 별점 박스 -->
                            <div id="star-box">
                                <div id="star-left">
                                    <div class="mt-1">
                                        <v-rating v-model="proTotalRating" half-increments
                                            background-color="#64c481 lighten-3" color="#64c481" large readonly>
                                        </v-rating>
                                    </div>
                                    <h2 class="ml-3 mt-3" style="font-weight:bold" v-if="reviews.length != 0">
                                        {{proTotalRating}}</h2>
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

                        <!-- <div>
                            <h4>평점</h4>
                        </div>
                        <div>
                            <img src="https://m.bunjang.co.kr/pc-static/resource/44c1240e63c64f221877.png" width="30px"
                                height="29px" alt="별점 1">
                            <img src="	https://m.bunjang.co.kr/pc-static/resource/44c1240e63c64f221877.png" width="30px"
                                height="29px" alt="별점 1">
                            <img src="	https://m.bunjang.co.kr/pc-static/resource/44c1240e63c64f221877.png" width="30px"
                                height="29px" alt="별점 1">
                            <img src="	https://m.bunjang.co.kr/pc-static/resource/982587b0e24b8bccea13.png" width="30px"
                                height="29px" alt="별점 0">
                            <img src="	https://m.bunjang.co.kr/pc-static/resource/982587b0e24b8bccea13.png" width="30px"
                                height="29px" alt="별점 0">
                        </div> -->
                    </div>

                </div>
            </div>

        </v-card>


        <div class="review_area">
            <b-tabs content-class="mt-3">
                <!-- <b-tab title="견적정보" active>
                    
                </b-tab> -->


                <b-tab title="이사후기">

                    <v-card>
                        <v-data-table :headers="headers" :items="data">
                            <!-- 별점 -->
                            <template v-for="item in data" v-slot:item.REVIEW_CONTENT="{ item }">

                                <div class="pa-5">

                                    <v-rating v-model="item.TOTAL_RATING" half-increments
                                        background-color="#64c481 lighten-3" color="#64c481" small readonly></v-rating>
                                    <div class="ml-2">{{item.REVIEW_CONTENT}}</div>
                                </div>

                            </template>
                        </v-data-table>

                    </v-card>



                </b-tab>


                <div v-if="list.userEmail == this.$store.state.loginInfo.email">
                    <b-tab title="후기작성">
                        <v-card>

                            <v-card-title>
                                <span class="text-h6">견적정보</span>
                            </v-card-title>
                            <v-card-text>
                                <div><span>NO.{{list.movingResponseNo}}</span></div>
                                <div><span>견적일자 : {{list.requestDate}}</span></div>
                                <div><span>글쓴이 : {{nickName}}</span></div>
                                <div v-if="list.secondEstimateType == null">
                                    <div><span>견적타입: {{list.firstEstimateType}}</span></div>
                                </div>
                                <div v-if="list.secondEstimateType != null">
                                    <div><span>견적타입 : {{list.secondEstimateType}}</span></div>
                                </div>

                            </v-card-text>

                            <hr />

                            <v-card-title>
                                <span class="text-h6">이사후기</span>
                            </v-card-title>
                            <v-card-text>
                                <v-container style="color:#212529">
                                    <v-row>
                                        <!-- 별점선택 -->
                                        <v-col cols="11">
                                            <table>
                                                <tbody>
                                                    <tr style="border: 0">
                                                        <td>손이 빨라요</td>
                                                        <td>
                                                            <b-form-rating v-model="rate1" no-border color="#64c481"
                                                                size="sm"></b-form-rating>
                                                        </td>
                                                    </tr>
                                                    <tr style="border: 0">
                                                        <td>전문적이에요</td>
                                                        <td>
                                                            <b-form-rating v-model="rate2" no-border color="#64c481"
                                                                size="sm"></b-form-rating>
                                                        </td>
                                                    </tr>
                                                    <tr style="border: 0">
                                                        <td>친절해요</td>
                                                        <td>
                                                            <b-form-rating v-model="rate3" no-border color="#64c481"
                                                                size="sm"></b-form-rating>
                                                        </td>
                                                    </tr>
                                                    <tr style="border: 0">
                                                        <td>마무리가 깔끔해요</td>
                                                        <td>
                                                            <b-form-rating v-model="rate4" no-border color="#64c481"
                                                                size="sm"></b-form-rating>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </v-col>
                                        <v-col cols="12">
                                            리뷰
                                            <v-textarea solo v-model="reviewContent" no-border name="input-7-4"
                                                label="리뷰를 입력해주세요">
                                            </v-textarea>
                                        </v-col>
                                    </v-row>
                                </v-container>
                            </v-card-text>
                            <v-card-actions>
                                <v-spacer></v-spacer>

                                <input type="button" id="subBtn" value="등록" disabled="disabled"
                                    color="success lighten -2" text @click="insertReview()" />



                            </v-card-actions>
                        </v-card>
                    </b-tab>
                </div>


            </b-tabs>
        </div>


    </div>
</template>

<script>
    import MoveNavBar from '../../components/move/MoveNavBar.vue';
    import axios from 'axios';
    export default {
        components: {
            MoveNavBar
        },
        data: function () {
            return {
                nickName: this.$store.state.loginInfo.nickName,
                list: {},
                data: [],
                page: 1,
                pageCount: 1,
                estimateType: "",
                serviceId: "",
                pageNum: "",
                rv: {
                    reviewNo: "",
                    email: "",
                    reviewTitle: "none",
                    reviewContent: "",
                    reviewDate: "",
                    serviceType: 3,
                    serviceId: '',
                    viewCnt: 0,
                    totalRating: 0,
                    rate1: 0,
                    rate2: 0,
                    rate3: 0,
                    rate4: 0,
                    deleteState: ""
                },


                //은하

                //후기 
                reviewContent: '',
                serviceType: 3,
                totalRating: '',
                rate1: '',
                rate2: '',
                rate3: '',
                rate4: '',

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
                        value: 'email'
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
        created() {


            // await this.getReviewList();

            //별점 계산
            var tSum = 0;
            var r1Sum = 0;
            var r2Sum = 0;
            var r3Sum = 0;
            var r4Sum = 0;
            for (var i in this.reviews) {
                tSum += Number(this.reviews[i].totalRating)
                r1Sum += Number(this.reviews[i].rate1)
                r2Sum += Number(this.reviews[i].rate2)
                r3Sum += Number(this.reviews[i].rate3)
                r4Sum += Number(this.reviews[i].rate4)
            }
            this.proTotalRating = Math.round(tSum / this.reviews.length * 10) / 10
            this.rate1 = Math.round(r1Sum / this.reviews.length) * 10
            this.rate2 = Math.round(r2Sum / this.reviews.length) * 10
            this.rate3 = Math.round(r3Sum / this.reviews.length) * 10
            this.rate4 = Math.round(r4Sum / this.reviews.length) * 10



            axios({
                url: "http://localhost:8090/zippy/move/moveMyListOne",
                methods: "GET",
                params: {

                    userEmail: this.$store.state.loginInfo.email,
                    // movingResponseNo : this.list.movingResponseNo,
                    movingResponseNo: 14,
                    pageNum: this.page

                }
            }).then(res => {
                this.list = res.data;
                this.pageCount = res.data.pages;
                console.log("res", res);
                console.log(res.data);
                console.log("list!!!!!!!", this.list);

                // this.data.email = this.$store.state.loginInfo.email;
                // this.data.serviceId= this.list[i].email;
                // console.log(this.data.serviceId);


                //후기 가져오기
                axios({
                    url: "http://localhost:8090/zippy/move/moveReview",
                    methods: "GET",
                    params: {

                        // email: this.$store.state.loginInfo.email,
                        // movingResponseNo : 14,
                        serviceId: this.list.email,
                        // serviceType : this.rv.serviceType,
                        pageNum: this.page,


                    }
                }).then(res => {
                    this.data = res.data;
                    this.pageCount = res.data.pages;
                    this.nickName = this.$store.state.loginInfo.nickName;
                    console.log("res", res);
                    console.log(res.data);
                    console.log("list!!!!!!!", this.data);

                    // this.data.email = this.$store.state.loginInfo.email;
                    // this.data.serviceId= this.list[i].email;
                    // console.log(this.data.serviceId);
                }).catch(error => {
                    console.log(error);
                })

            }).catch(error => {
                console.log(error);
            })

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
                    axios({
                        url: "/common/addRv",
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

                        const btn = document.getElementById('subBtn');
                        btn.disabled = true;

                    }).catch(error => {
                        console.log(error);
                    })
                }
                //상태 변경
                axios({
                    url: "/shop/updateRvStatus",
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    data: {
                        purNo: this.purNo
                    }
                }).then(res => {
                    console.log(res);
                }).catch(error => {
                    console.log(error);
                })
            },



        }
    }
</script>

<style scoped>
    /* .loaf{
    float: left;
}
.right-loaf{
    float: right;
} */

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