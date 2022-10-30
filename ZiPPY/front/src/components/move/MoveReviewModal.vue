<template>
  <v-dialog v-model="dialog" persistent :retain-focus="false" max-width="500px">
    <template v-slot:activator="{ on, attrs }">
      <div>
      <v-btn v-if="reservStatus == 5" id="reviewBtn" depressed color=#B3E3C3 width="100px" v-bind="attrs" v-on="on">
        후기작성
      </v-btn>
      <v-btn v-if="reservStatus == 9" disabled depressed color=#D6D6D6 width="100px">
        작성완료
      </v-btn>
    </div>

    </template>
    <div v-if="list.userEmail == this.$store.state.loginInfo.email">
                  <v-card>
                      <v-card-title>
                          <span class="text-h6">견적정보</span>
                      </v-card-title>
                      <v-card-text>
                          <div><span>NO.{{list.movingResponseNo}}</span></div>
                          <div><span>견적일자 : {{list.requestDate}}</span></div>
                          <div><span>글쓴이 : {{list.userEmail}}</span></div>
                          <div v-if="list.secondEstimateType == null">
                              <div><span>견적타입: {{list.firstEstimateType}}</span></div>
                          </div>
                          <div v-if="list.secondEstimateType != null">
                              <div><span>견적타입 : {{list.secondEstimateType}}</span></div>
                          </div>
                      </v-card-text><hr />
    
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
    
                          <v-btn color="#212529" text @click="closeReview()">
                            닫기
                            </v-btn>
    
                          <div >
                          <input type="button" id="subBtn" value="등록"
                              color="success lighten -2" text @click="insertReview(list.movingResponseNo)" />
                            </div>  
    
    
                      </v-card-actions>
                  </v-card>
           
          </div>

  </v-dialog>
</template>

<script>
import swal from 'sweetalert2';
  import MoveNavBar from '../../components/move/MoveNavBar.vue';
    import axios from 'axios';
    export default {
        components: {
            MoveNavBar
        },
        props:['email','movingResponseNo', 'reservStatus'],
        data: function () {
            return {
                dialog: false,
                list: {},
                data: [],
                page: 1,
                pageCount: 1,
                estimateType: "",
                serviceId: "",
                pageNum: "",                


                //은하
                on: "",
                attrs:"",
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
        async created() {
            var outside = this.list;
            //견적정보 가져오기
            let res =  await axios({
                    url: "/zippy/move/moveMyListOne",
                    methods: "GET",
                    params: {
                        email: this.email,
                        userEmail: this.$store.state.loginInfo.email,
                        movingResponseNo : this.movingResponseNo,                        
                        pageNum: this.page,
                        reservStatus : this.reservStatus
                    }
            });
            this.list = res.data;
            this.pageCount = res.data.pages;
        },

        methods: {
            //모달 닫기
            closeReview() {
                this.dialog = false;
                this.rate1 = '',
                this.rate2 = '',
                this.rate3 = '',
                this.rate4 = '',
                this.reviewContent = ''
            },

            //리뷰 등록
            insertReview(i) {
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
                    
                    swal.fire({
                        title: '리뷰작성',
                        text: "후기를 등록하시겠습니까?",
                        icon: 'warning',
                        showCancelButton: true,
                        confirmButtonColor: '#96e5b8',
                        cancelButtonColor: '#a9a9a9',
                        confirmButtonText: 'YES'
                    }).then((result) => {
                    if (result.isConfirmed){

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
                                reviewStatus : 1
                            }
                        }).then(res => {
                            console.log(res);
                            swal.fire({
                                icon: 'success',
                                title: '리뷰가 등록되었습니다.',
                                showConfirmButton: false,
                                timer: 1500
                            });
                            
                            //모달창 닫기
                        this.closeReview();
                        this.orderStatus = 2;
                            //견적상태변경(작성완료로 변경 : 9)
                        this.$axios({
                        url: "/zippy/move/moveStatusCancleUpdate",
                        method: "POST",
                        params: {
                            movingResponseNo : i
                        },
                        }).then(res => {
                        console.log(res);
                        window.location.assign('mypage/move/moveMyReserve');
                        }).catch(err => {
                        console.log(err)
                        })
                     }).catch(error => {
                                console.log(error);
                    })
                 }
            })
        }   
            },



        }
    }
</script>

<style scoped>
  @font-face {
    font-family: 'GmarketSans';
    font-weight: 500;
    font-style: normal;
    src: url('https://cdn.jsdelivr.net/gh/webfontworld/gmarket/GmarketSansMedium.eot');
    src: url('https://cdn.jsdelivr.net/gh/webfontworld/gmarket/GmarketSansMedium.eot?#iefix') format('embedded-opentype'),
         url('https://cdn.jsdelivr.net/gh/webfontworld/gmarket/GmarketSansMedium.woff2') format('woff2'),
         url('https://cdn.jsdelivr.net/gh/webfontworld/gmarket/GmarketSansMedium.woff') format('woff'),
         url('https://cdn.jsdelivr.net/gh/webfontworld/gmarket/GmarketSansMedium.ttf') format("truetype");
    font-display: swap;
} 
* {
  font-family: 'GmarketSans';
}
.v-btn {
    font-weight: bold;
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