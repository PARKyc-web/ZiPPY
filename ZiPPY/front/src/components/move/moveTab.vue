<template>
  <!-- 탭 -->
  <!-- 상품 상세정보 -->
  <div class="mx-auto pb-5" style="width:810px">
    <v-card color="#fff" class="mt-10">
      <v-tabs v-model="tab" background-color="transparent" color="#64c481" grow>
        <v-tab>
          상품정보
        </v-tab>
        <v-tab>
          상품후기
        </v-tab>
        <v-tab>
          상품문의
        </v-tab>
      </v-tabs>
      <v-tabs-items v-model="tab">
        <!-- 상품정보 -->
        <v-tab-item>
          <v-card>
            <v-card flat style="width:810px; min-height:400px">
              <v-card-text>
                {{product.proInfo}}
              </v-card-text>
            </v-card>
          </v-card>
        </v-tab-item>
        <!-- 상품정보 끝 -->
        <!-- 상품후기 -->
        <v-tab-item>
          <v-card flat>
            <div>
              <!-- 별점 박스 -->
              <div id="star-box">
                <div id="star-left">
                  <div class="mt-1">
                    <v-rating
                      v-model="totalRating"
                      background-color="#64c481 lighten-3"
                      color="#64c481"
                      large
                      readonly
                    ></v-rating>
                  </div>
                  <h2 class="ml-3 mt-3" style="font-weight:bold">4.5</h2>
                </div>
                <div id="star-right">
                  <div class="progress">
                    <div class="progress-bar" role="progressbar" aria-valuenow="0" aria-valuemin="0"
                      aria-valuemax="100">
                    </div>
                  </div>
                  <h6>배송</h6>
                  <div class="progress">
                    <div class="progress-bar" role="progressbar" style="width: 25%" aria-valuenow="25" aria-valuemin="0"
                      aria-valuemax="100"></div>
                  </div>
                  <h6>가격</h6>
                  <div class="progress">
                    <div class="progress-bar" role="progressbar" style="width: 50%" aria-valuenow="50" aria-valuemin="0"
                      aria-valuemax="100"></div>
                  </div>
                  <h6>디자인</h6>
                  <div class="progress">
                    <div class="progress-bar" role="progressbar" style="width: 75%" aria-valuenow="75" aria-valuemin="0"
                      aria-valuemax="100"></div>
                  </div>
                  <h6>내구성</h6>
                </div>
              </div>
              <!-- 별점 박스 끝 -->
            </div>
            <!-- 후기박스 -->
            <div id="review-box" class="px-3">
              <hr>
              <!-- 사용자 정보 -->
              <div id="user-info">
                <p>yed***@gmail.com</p>|<p>2022-10-04</p>|
                <div id="report-review">
                  <p>신고<v-icon>mdi-alarm-light</v-icon>
                  </p>
                </div>
              </div>
              <!-- 사용자 정보 끝-->
              <!-- 별점 -->
              <div style="display:flex">
                <p>
                  <v-icon style="font-size:15px; color:#B3E3C3" class="pb-1">mdi-star</v-icon>
                  5.0
                </p>
              </div>
              <!-- 별점 끝 -->
              <p>옵션:white(L size)</p>
              <p>코딩이 잘 되는지 모르겠음 사기당함 코딩이 잘 되는지 모르겠음 사기당함 코딩이 잘 되는지 모르겠음 사기당함 코딩이 잘 되는지 모르겠음 사기당함 코딩이 잘 되는지 모르겠음 사기당함
                코딩이 잘
                되는지
                모르겠음 사기당함 </p>
              <hr>
            </div>
            <!-- 후기박스 끝 -->
            <!-- 페이지네이션 -->
            <div class="pb-5">
              <div class="text-center">
                <v-pagination v-model="page" :length="4" circle color="#B3E3C3"></v-pagination>
              </div>
            </div>
            <!-- 페이지네이션 끝 -->
          </v-card>
        </v-tab-item>
        <!-- 상품후기 끝 -->
        <!-- 상품문의 -->
        <v-tab-item>
          <v-card flat>
            <div id="qna-box" class="px-3">
              <div class="tab-pane" id="qna" role="tabpanel" aria-labelledby="qna-tab">
                <div id="qna-button" class="my-5">
                  <p>문의하기를 통해 궁금증을 해결하세요.</p>
                  <!-- 모달 버튼 -->
                  <div class="ml-auto pr-3">
                    <v-row>
                      <v-dialog v-model="dialog" persistent max-width="500px">
                        <template v-slot:activator="{ on, attrs }">
                          <v-btn depressed color=#B3E3C3 v-bind="attrs" v-on="on" width="100px">
                            상품문의
                          </v-btn>
                        </template>
                        <v-card>
                          <v-card-title>
                            <span class="text-h6">상품문의</span>
                          </v-card-title>
                          <v-card-text>
                            <v-container>
                              <v-row>
                                <v-col cols="11">
                                  <v-select :items="qCate" v-model="selectQcate" label="문의 유형을 선택해주세요" required>
                                  </v-select>
                                </v-col>
                                <v-col cols="12">
                                  문의내용
                                  <v-textarea solo v-model="qContent" name="input-7-4" label="문의내용을 입력해주세요">
                                  </v-textarea>
                                </v-col>
                              </v-row>
                            </v-container>
                            <small>
                              <v-row>
                                <v-col cols="12">
                                  - 문의내용에 대한 답변은 ‘마이페이지 > 나의 쇼핑 > 나의 문의내역’ 또는 ‘상품 상세페이지’에서 확인 가능합니다.
                                  <br>
                                  - 배송,결제,교환/반품 문의는 고객센터로 문의 바랍니다.
                                  <br>
                                  - 상품과 관련 없거나 부적합한 내용을 기재하시는 경우, 사전 고지 없이 삭제 또는 차단될 수 있습니다.
                                </v-col>
                              </v-row>
                            </small>
                          </v-card-text>
                          <v-card-actions>
                            <v-spacer></v-spacer>
                            <v-btn color="#212529" text @click="closeQna">
                              닫기
                            </v-btn>
                            <v-btn color="#212529" text @click="insertQna">
                              등록
                            </v-btn>
                          </v-card-actions>
                        </v-card>
                      </v-dialog>
                    </v-row>
                  </div>
                </div>
                <!-- 모달 버튼 끝-->
                <div>
                  <!-- 아코디언 -->
                  <v-expansion-panels>
                    <v-expansion-panel v-for="(item,index) in qnas" :key="index" expand>
                      <v-expansion-panel-header expand-icon="">
                        <div id="qna-title">
                          <p style="padding-right:5px" v-if="item.answerStatus==0">답변대기</p>
                          <p style="padding-right:5px" v-if="item.answerStatus==1">답변완료</p>
                          <p>| {{item.questionCate}}입니다.</p>
                          <div id="user-info" class="ml-auto">
                            <p>{{item.email}}</p>|
                            <p>{{item.questionDate}}</p>
                          </div>
                        </div>
                      </v-expansion-panel-header>
                      <v-expansion-panel-content>
                        <div class="qna" id=qna-q>
                          <p>{{item.question}}</p>
                        </div>
                        <div class="qna" id="qna-a">
                          <p>
                            <v-icon v-if="item.answer" class="mr-4 mb-2">mdi-subdirectory-arrow-right</v-icon>
                            {{item.answer}}
                          </p>
                        </div>
                      </v-expansion-panel-content>
                    </v-expansion-panel>
                  </v-expansion-panels>
                  <!-- 아코디언 끝 -->
                  <!-- 페이지네이션 -->
                  <div class="pb-5">
                    <div class="text-center">
                      <v-pagination v-model="page" :length="4" circle color="#B3E3C3"></v-pagination>
                    </div>
                  </div>
                  <!-- 페이지네이션 끝 -->
                </div>
                <hr>
                <div id="seller-content">
                  <p style="font-size:smaller">- 먼저 문의하신 다른 고객님들의 질문을 참조하시면, 유용한 정보를 빠르게 확인하실 수 있습니다.</p>
                  <p style="font-size:smaller">- 휴일 및 공휴일, 영업시간 종료 이후에 남겨주신 문의 사항은, 평일 영업시간에 답변을 해드리고 있습니다.</p>
                </div>
              </div>
            </div>
          </v-card>
        </v-tab-item>
        <!-- 상품후기 끝-->
      </v-tabs-items>
    </v-card>
  </div>
</template>

<script>
  import axios from 'axios';
  import swal from 'sweetalert2';

  export default {
    props: ['pno'],
    data: () => ({
      tab: null,
      imgs: [],
      page: 1,
      tabs: null,
      dialog: false,
      product: {},
      proNo: '',
      //별점
      totalRating: 5,

      //조회한 qna
      qnas: [],

      //qna 등록
      //qna 카테고리
      qCate: ['상품문의', '배송문의', '기타문의'],
      //선택 카테고리
      selectQcate: '',
      //질문 내용
      qContent: ''
    }),
    created() {
      //단건조회
      axios({
        url: "/shop/getQnaList",
        method: "GET",
        params: {
          pno: this.pno
        }
      }).then(res => {
        console.log(res);
        this.qnas = res.data;
        console.log(this.qnas);
      }).catch(error => {
        console.log(error);
      })
    },
    methods: {
      insertQna() {
        if (this.$store.state.loginInfo != null) {
          axios({
            url: "/shop/insertQna",
            method: "POST",
            headers: {
              "Content-Type": "application/json; charset=utf-8"
            },
            data: {
              email: this.$store.state.loginInfo.email,
              proNo: this.pno,
              question: this.qContent,
              questionCate: this.selectQcate
            }
          }).then(res => {
            swal.fire({
              icon: 'success',
              title: '문의를 작성하였습니다.',
              showConfirmButton: false,
              timer: 1500
            });
            this.dialog = false;
            this.resetQna();
          }).catch(error => {
            console.log(error);
          })
        }
      },
      closeQna(){
        this.dialog = false;
        this.resetQna();
      },
      resetQna(){
        this.qContent = '',
        this.selectQcate = ''
      }
    },
    // computed: {
    //   aStatus() {
    //     var status = '';
    //     if(this.qnas.questionStatus == 0) {
    //       status = '답변대기'
    //       return status
    //     }else {
    //       status = '답변완료'
    //       return status
    //     }
    //   }
    // }
  }
</script>

<style scoped>
  /* 리뷰 */
  #star-box {
    width: 810px;
    text-align: center;
    display: flex;
  }

  #star-left {
    width: 250px;
    margin: 0 auto;
    display: flex;
    color: #212529;
    padding: 80px 0 80px 10px;
  }

  #star-right {
    width: 250px;
    margin: 0 auto;
    text-align: left;
    padding: 40px 0 10px 0;
  }

  .progress-bar {
    width: 200px;
    background-color: #B3E3C3;
  }

  .progress {
    width: 200px;
  }

  #star-right h6 {
    font-size: smaller;
  }

  #user-info {
    display: flex;
    float: right;
    font-size: small;
  }

  #user-info p {
    padding: 0 5px 0 5px;
  }

  #report-review:hover {
    cursor: pointer;
  }

  /* qna */
  #qna-box {
    width: 810px;
  }

  #qna-button {
    display: flex;
    height: 38px;
  }

  #qna-button .btn {
    margin-left: auto;
  }


  #seller-content {
    font-size: smaller;
    line-height: 2px;
  }

  .accordion-body {
    background-color: #e4e4e4;
    padding: 30px;
  }

  .accordion {
    width: 780px
  }

  #qna-title {
    display: flex;
    width: 780px;
  }

  .accordion-button::after {
    display: none;
  }

  .qna {
    padding: 10px;
  }

  #qna-title p {
    margin: 0;
  }

  .v-expansion-panel-header__icon {
    display: none;
  }
</style>