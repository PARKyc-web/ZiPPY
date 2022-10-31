<template>
  <div class="result-wrap">

    <div class="move-main-title">
      <h3>보낸 견적 관리</h3>

      <!-- 드롭박스 -->
      <div id="used-main-dropbox1">
        <v-select @change="dropVal2()" v-model="select2" :items="drops" item-text="name" item-value="value2" label="정렬"
          color="#212529" persistent-hint single-line dense width="50"></v-select>
      </div>


      <hr />
    </div>
    <div class="panel">
      <v-expansion-panels>
        <v-expansion-panel v-if="list.length != 0" v-for="(item,i) in list">
          <v-expansion-panel-header>
            <span>NO.{{item.estimateNo}}</span> &nbsp;&nbsp; 견적요청일 :
            <span>{{item.requestDate}}</span>&nbsp;&nbsp; 견적 방법 : <span>{{item.estimateType}}</span>
            <v-col cols="12" sm="6" md="4">
              <div id="mus" v-if="item.reservStatuss == 0">견적 상태 : <span>견적전</span></div>
              <div id="mus" v-if="item.reservStatuss == 1">견적 상태 : <span>1차견적</span></div>
              <div id="mus" v-if="item.reservStatuss == 2">견적 상태 : <span>2차견적</span></div>
              <div id="mus" v-if="item.reservStatuss == 3">견적 상태 : <span>예약요청</span></div>
              <div id="mus" v-if="item.reservStatuss == 4">견적 상태 : <span>예약완료</span></div>
              <div id="mus" v-if="item.reservStatuss == 5">견적 상태 : <span>이사완료</span></div>
              <div id="mus" v-if="item.reservStatuss == 9">견적 상태 : <span>후기작성완료</span></div>
            </v-col>

          </v-expansion-panel-header>
          <v-expansion-panel-content>
            <div class="info">
            <div>요청회원 : <span>{{item.email}}</span></div>
            <div>이사 종류 : <span>{{item.moveType}}</span></div>
            <div>이사희망일 : <span>{{item.movingDate}}</span></div>
            <div>이사희망시간 : <span>{{item.movingTime}}</span></div>
            <div>출발지 주소 : <span>(우편번호){{item.departZipCode}}</span> <br><div class="move-info"><span>{{item.departAddress}}</span>
              <span>{{item.departDetail}}</span></div></div>
            <div>도착지 주소 : <span>(우편번호){{item.arriveZipCode}}</span> <br><div class="move-info"><span>{{item.arriveAddress}}</span>
              <span>{{item.arriveDetail}}</span></div></div>
            <div> 이사 정보 : <div class="move-info"><span v-html="par(item.commonOption)"></span></div></div>
            <div v-if="item.estimateType == '비대면견적'">
              <div>이삿짐 정보 :<div class="move-info"><span v-html="my(item.movingOption)"></span></div></div>
            </div>

            <div v-if="item.movingMemo != null">
              <div>이사 요청사항 : <span>{{item.movingMemo}}</span></div>
            </div>
            <div v-if="item.estimateType == '대면견적'">
              <div>견적 방문희망일 : <span>{{item.visitDate}}</span></div>
              <div>견적 방문희망시간 : <span>{{item.visitTime}}</span></div>
            </div>

            <div v-if="item.estimateType == '비대면견적'" id="imgPanel">

              <!-- 사진보기 펼치기 -->

              <v-card-actions>

                <v-spacer></v-spacer>
                <v-label>방사진보기</v-label>
                <v-btn icon @click="show = !show">
                  <v-icon>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
                </v-btn>
              </v-card-actions>

              <v-expand-transition>
                <div v-show="show">
                  <v-divider></v-divider>

                  <div>
                    방 입구에서 찍은 사진<img />
                  </div>
                  <div>
                    방 중앙에서 찍은 사진 <img />
                  </div>
                  <div>
                    내부 구조 사진(짐, 장롱, 창고 등) <img />
                  </div>
                </div>
              </v-expand-transition>

            </div>

            <v-card-actions>
              <v-spacer></v-spacer>

              <v-row justify="center">
                <v-btn v-bind="attrs" v-on="on" width="80">채팅하기</v-btn>
                <v-dialog v-model="dialog" persistent max-width="600px">

                  <template v-slot:activator="{ on, attrs }">

                    <v-btn v-if="item.reservStatuss==1" id="estBtn" @click="modalVal(i)" color="success" dark
                      v-bind="attrs" v-on="on" width="80">
                      견적작성
                    </v-btn>
                    <v-btn v-if="item.reservStatuss==3" @click="updateConfirm(item.estimateNo)" color="success"
                      width="80">
                      예약확정
                    </v-btn>
                    <v-btn v-if="item.reservStatuss==4" @click="updateComplete(item.estimateNo)" color="success"
                      width="80">
                      이사완료
                    </v-btn>
                  </template>

                  <!-- 모달 -->
                  <form id="estimateForm" name="estimateForm">
                    <!-- v-for="i in list" -->
                    <div>
                      <v-card :id="item.estimateNo">
                        <v-card-title>
                          <span class="text-h5">견적서 확인</span> &nbsp;&nbsp;&nbsp;&nbsp;

                        </v-card-title>
                        <v-card-text>
                          <v-container>
                            <v-row>
                              <v-col cols="12" sm="6" md="4">
                                <div>견적요청번호 : <span>NO.{{item.estimateNo}}</span></div>
                              </v-col><br />
                              <v-col cols="12" sm="6" md="4">
                                <div>견적서 번호 : <span>NO.{{item.movingResponseNo}}</span></div>
                              </v-col><br />


                              <v-col cols="12" sm="6" md="4">
                                <div id="mus" v-if="item.reservStatuss == 0">견적 상태 : <span>견적전</span></div>
                                <div id="mus" v-if="item.reservStatuss == 1">견적 상태 : <span>1차견적</span></div>
                                <div id="mus" v-if="item.reservStatuss == 2">견적 상태 : <span>2차견적</span></div>
                                <div id="mus" v-if="item.reservStatuss == 3">견적 상태 : <span>예약완료</span></div>
                                <div id="mus" v-if="item.reservStatuss == 4">견적 상태 : <span>이사완료</span></div>
                                <div id="mus" v-if="item.reservStatuss == 5">견적 상태 : <span>취소</span></div>
                              </v-col>


                              <v-col cols="12">
                                <div id="mus">업체명 : <span>{{item.compName}}</span></div>
                              </v-col>

                              <v-col cols="12">
                                <div id="mus">업체이메일 : <span>{{item.businessEmail}}</span></div>
                              </v-col>
                              <v-col cols="12">

                                <div id="mus">1차 견적 타입 : <span>{{item.firstEstimateType}}</span></div>
                              </v-col>
                              <v-col cols="12">
                                <div id="mus">1차 견적 가격 : <span>{{item.firstEstimatePrice | comma}}원</span></div>
                              </v-col>

                              <v-col cols="12">
                                <div id="mus">업체어필하기 : <span>{{item.responseMemo}}</span></div>
                              </v-col>

                              <form id="secondForm">
                                <v-col cols="12">
                                  <v-autocomplete id="secondType" :value="selectData.secondEstimateType"
                                    :items="['대면견적', '비대면견적']" label="2차 견적타입*"></v-autocomplete>
                                </v-col>


                                <v-col cols="12">
                                  <v-text-field id="secondPrice" :value="selectData.secondEstimatePrice"
                                    label="2차 견적가격*" type="number" required>
                                  </v-text-field>
                                </v-col>

                                <input type="hidden" name="secondEstimatePrice"
                                  v-model="selectData.secondEstimatePrice">
                                <input type="hidden" name="secondEstimateType" v-model="selectData.secondEstimateType">
                              </form>
                            </v-row>
                          </v-container>
                          <small>*필수입력 사항을 입력해주세요.</small>
                        </v-card-text>
                        <v-card-actions>
                          <v-spacer></v-spacer>
                          <v-btn color="gray" text @click="dialog = false">
                            닫기
                          </v-btn>

                          <v-btn id="sendbtn" color="success darken-1" text @click="sendSecondEstimate()">
                            견적보내기
                          </v-btn>
                        </v-card-actions>
                      </v-card>
                    </div>
                    <input type="hidden" name="estimateNo" id="estimateNo" v-model="selectData.estimateNo">
                    <input type="hidden" name="email" id="email" v-model="selectData.email">
                    <input type="hidden" name="businessEmail" id="businessEmail" v-model="selectData.businessEmail">

                    <input type="hidden" name="firstEstimateType" id="firstEstimateType"
                      v-model="selectData.estimateType">
                    <input type="hidden" name="firstEstimatePrice" v-model="selectData.firstEstimatePrice">

                    <input type="hidden" name="reservStatus" value="0" v-model="selectData.reservStatus">
                    <input type="hidden" name="compName" v-model="selectData.compName">
                    <input type="hidden" name="responseMemo" v-model="selectData.responseMemo">
                  </form>

                </v-dialog>
              </v-row>

            </v-card-actions>
          </div>
          </v-expansion-panel-content>

        </v-expansion-panel>
      </v-expansion-panels>
    </div>

    <div class="text-center">
      <v-pagination v-model="page" :length="pageCount" circle color="#B3E3C3"></v-pagination>
    </div>

  </div>
</template>

<script>
  import axios from 'axios';
  import swal from 'sweetalert2';

  export default {


    data: function () {
      return {

        //페이징
        selection: 1,
        heart: 0,
        wish: "",
        bNo: "",
        page: 1,
        pageCount: 1,

        //펼치기
        show: false,

        md: "",
        item: "",
        email: "",
        estimateNo: "",

        secondEstimateType: "",
        secondEstimatePrice: "",

        businessEmail: "",

        list: [],
        MD: [],
        vo: {
          email: "",
          requestDate: "",
          departAddress: "",
          arriveAddress: "",
          compAddress: "",
          estimateNo: "",
          estimateType: "",
          businessEmail: "",
          responseMemo: "",

          firstEstimateType: "",
          firstEstimatePrice: "",
          compName: "456이사",
          reservStatus: 1,
          movingResponseNo: ""
        },


        drops: [{
            name: '전체',
            value: '전체'
          },
          {
            name: '날짜순',
            value: '날짜순'
          },
          {
            name: '견적방법순',
            value: '견적방법순'
          },
          {
            name: '견적상태순',
            value: '견적상태순'
          },
        ],


        data: [],
        selectData: {},
        select: '',
        select2: '', //지역
        dialog: false,
        on: '',
        attrs: '',

      }
    },
    created() {
      axios({
        url: "/zippy/move/moveCompanyEstimate",
        methods: "GET",
        params: {
          businessEmail: this.$store.state.loginInfo.email,
          estimateNo: "",
          reservStatus: "",
        }
      }).then(res => {
        console.log(res);
        this.list = res.data;
      }).catch(error => {
        console.log(error);
      })

      //사진가져오기
      axios({
        url: "/zippy/move/movePhoto",
        methods: "GET",

      }).then(res => {
        console.log(res);
        this.photoList = res.data;
        console.log('lilssstttt', this.photoList)
      }).catch(error => {
        console.log(error);
      })

    },
    filters: {
      comma(val) {
        return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ',')
      }
    },
    watch: {},
    methods: {

      //예약확정으로 상태 업데이트 
      updateConfirm: function (i) {
        this.dialog = false;

        swal.fire({
          title: '예약확정',
          text: "요청된 예약을 확정하시겠습니까?",
          icon: 'warning',
          showCancelButton: true,
          confirmButtonColor: '#96e5b8',
          cancelButtonColor: '#a9a9a9',
          confirmButtonText: 'YES'
        }).then((result) => {
          if (result.isConfirmed) {
            this.$axios({
              url: "/zippy/move/moveStatusFourthUpdate",
              method: "POST",
              params: {
                estimateNo: i,
                email: this.$store.state.loginInfo.email,
                reservStatus: 4
              },
            }).then(res => {
              console.log(res);
              swal.fire("예약이 확정되었습니다.");
              window.location.assign('/move/moveCompanyMy');
            }).catch(err => {
              console.log(err)
            })
          }
        })
      },



      //이사완료로 상태 업데이트
      updateComplete: function (i) {

        swal.fire({
          title: '이사완료',
          text: "이사완료 후에 고객님이 후기를 작성할 수 있습니다.   이사완료로 상태를 변경하시겠습니까?",
          icon: 'warning',
          showCancelButton: true,
          confirmButtonColor: '#96e5b8',
          cancelButtonColor: '#a9a9a9',
          confirmButtonText: 'YES'
        }).then((result) => {
          if (result.isConfirmed) {
            this.$axios({
              url: "/zippy/move/moveStatusFifthUpdate",
              method: "POST",
              params: {
                estimateNo: i,
                email: this.$store.state.loginInfo.email,
                reservStatus: 5
              },
            }).then(res => {
              console.log(res);
              swal.fire("이사완료로 상태가 변경되었습니다.");
              window.location.assign('/move/moveCompanyMy');
            }).catch(err => {
              console.log(err)
            })
          }

        })
      },

      dropVal2: function () {

        var dropValue2 = this.select2;
        console.log(dropValue2);
        console.log(this.vo.email);
        axios({
          url: "/zippy/move/moveCompanyEstimate",
          methods: "GET",
          params: {

            dropbox2: dropValue2, //지역
            businessEmail: this.$store.state.loginInfo.email,
            requestDate: this.list.requestDate,
            estimateType: this.list.estimateType,
            reservStatuss: this.list.reservStatuss
          }
        }).then(res => {
          console.log(res);
          this.list = res.data;
        }).catch(err => {
          console.log(err);
        })
      },
      //모달 닫기
      closeReview() {
        this.dialog = false;

      },
      modalVal: function (i) {

        axios({
          url: "/zippy/move/moveCompanyEstimate",
          methods: "GET",
          params: {
            movingResponseNo: this.vo.movingResponseNo,
            estimateNo: this.vo.estimateNo,
            businessEmail: this.$store.state.loginInfo.email,
            firstEstimatePrice: this.vo.firstEstimatePrice,
            firstEstimateType: this.vo.firstEstimateType,
            reservStatus: this.vo.reservStatus,
            compName: this.vo.compName,
            responseMemo: this.vo.responseMemo,
          }
        }).then(res => {
          this.selectData.estimateNo = this.list[i].estimateNo //{...this.list[i]}
          this.selectData.email = this.$store.state.loginInfo.email
          // this.selectData.businessEmail = this.email
          this.selectData.estimateType = this.list[i].estimateType
          this.selectData.reservStatus = "0"
          this.selectData.compName = this.$store.state.loginInfo.compName
          console.log(res);
          this.list = res.data;
        }).catch(err => {
          console.log(err);
        })
      },

      par: function (string) {
        // var data = '{"bedCount":2,"bed":["","싱글","슈퍼싱글"],"sofaCount":1,"sofa":[""],"closetCount":1,"closet":[""],"closetsCount":1,"closets":[""],"tvCount":1,"tv":[""],"pcCount":1,"pc":[""],"fridgeCount":1,"fridge":[""],"trolleyCount":1,"trolley":[""],"etcCount":1,"etcName":[""],"etcSize":[""],"box":"16-20개","filesPhoto":""}';
        var temp = JSON.parse(string);

        var detailVal = '';
        var detail = '';

        detail += `<div>주거형태 : ${temp.houseType}</div>`
        detail += `<div>방구조 : ${temp.roomNum}</div>`
        detail += `<div>집 평수 : ${temp.spaceOfHome}</div>`
        detail += `<div>층수 : ${temp.floor}</div>`
        detail += `<div>화장실 개수 : ${temp.toilet}</div>`
        detail += `<div>베란다 개수 : ${temp.veranda}</div>`
        detail += `<div>별도 계단 : ${temp.extraStairs}</div>`
        detail += `<div>엘레베이터 : ${temp.elevator}</div>`
        detail += `<div>주차가능 여부 : ${temp.parkable}</div>`

        // detailVal = `<div>회원의 이사 기본 정보</div>` + detail;

        return detail;

      },
      my: function (string) {


        var temp = JSON.parse(string);

        var bedTemp = '침대 개수 : ' + temp.bedCount + ', 침대 사이즈 : ' + temp.bed[0];

        /*
          var fur = ['bed', 'sofa', 'closet', 'closets']

          for(let i of fur){
            if (temp.bed.length > 0  ){
              detail+= `<div>침대 : ${temp[i +'Count']}(${temp[i].join(",")})</div>`
            } 
          }  
        */

        //가구
        var detailVal = '';
        var detail = '';
        //침대
        if (temp.bed.length > 0 && temp.bed[1]) {
          detail += `<div>침대 : ${temp.bedCount}(${temp.bed.join(",")})</div>`
        }
        //소파
        if (temp.sofa.length > 0 && temp.sofa[1]) {
          detail += `<div>소파 : ${temp.sofaCount}(${temp.sofa.join(",")})</div>`
        }
        //옷장
        if (temp.closet.length > 0 && temp.closet[1]) {
          detail += `<div>옷장 : ${temp.closetCount}(${temp.closet.join(",")})</div>`
        }
        //서랍장
        if (temp.closets.length > 0 && temp.closets[1]) {
          detail += `<div>서랍장 : ${temp.closetsCount}(${temp.closets.join(",")})</div>`
        }
        //테이블/책상
        // if (temp.table.length > 0 &&  temp.table[1]){
        //   detail+= `<div>테이블/책상 : ${temp.tableCount}(${temp.table.join(",")})</div>`
        // } 
        //의자
        // if (temp.chair.length > 0 &&  temp.chair[1]){
        //   detail+= `<div>의자 : ${temp.chairCount}(${temp.chair.join(",")})</div>`
        // } 

        if (detail) {
          detailVal = `<div>가구</div>` + detail
        }

        //가전
        detail = ''
        //tv
        if (temp.tv.length > 0 && temp.tv[1]) {
          detail += `<div>tv : ${temp.tvCount}(${temp.tv.join(",")})</div>`
        }
        //데스크탑
        if (temp.pc.length > 0 && temp.pc[1]) {
          detail += `<div>데스크탑 : ${temp.pcCount}(${temp.pc.join(",")})</div>`
        }
        //냉장고
        if (temp.fridge.length > 0 && temp.fridge[1]) {
          detail += `<div>냉장고 : ${temp.fridgeCount}(${temp.fridge.join(",")})</div>`
        }
        //전자레인지
        if (temp.trolley.length > 0 && temp.trolley[1]) {
          detail += `<div>전자레인지 : ${temp.trolleyCount}(${temp.trolley.join(",")})</div>`
        }
        //에어컨
        // if (temp.aircon.length > 0 &&  temp.aircon[1]){
        //   detail+= `<div>에어컨 : ${temp.airconCount}(${temp.aircon.join(",")})</div>`
        // } 
        //세탁기
        // if (temp.laundry.length > 0 &&  temp.laundry[1]){
        //   detail+= `<div>세탁기 : ${temp.laundryCount}(${temp.laundry.join(",")})</div>`
        // } 

        if (detail) {
          detailVal += `<div>가전</div>` + detail

        }

        //기타
        detail = ''
        if (temp.etcName.length > 0) {
          for (let i = 1; i < temp.etcName.length; i++) {
            detail += `<div> ${temp.etcName[i]} : ${temp.etcSize[i]}(사이즈 cm)</div>`
          }
          console.log(detail);

          if (detail) {
            detailVal += `<div>기타</div>` + detail
          }

        }
        return detailVal;
      },

      //2차견적으로 업데이트
      sendSecondEstimate: function () {

        console.log('2차 가격 : ', document.querySelector("#secondPrice").value);
        console.log('2차 타입 : ', document.querySelector("#secondType").value);
        this.selectData.reservStatus = "2";
        //Form data로 보내기
        var formData = new FormData(document.querySelector('#secondForm'));
        formData.forEach((value, key) => {
          console.log('aa', value);
        })
        //2차견적 보내기
        swal.fire({
          title: '최종견적 입력정보를 확인해주세요.',
          text: "견적가를 더이상 수정할 수 없습니다. 이대로 견적서를 보내시겠습니까?",
          icon: 'warning',
          showCancelButton: true,
          confirmButtonColor: '#96e5b8',
          cancelButtonColor: '#a9a9a9',
          confirmButtonText: 'YES'
        }).then((result) => {
          if (result.isConfirmed) {
            this.$axios({
              url: "/zippy/move/moveEstimateUpdate",
              method: "POST",
              params: {
                estimateNo: this.selectData.estimateNo,
                secondEstimateType: document.querySelector("#secondType").value,
                secondEstimatePrice: document.querySelector("#secondPrice").value
              },
            }).then(res => {
              console.log(res);
            }).catch(err => {
              console.log(err)
            })
            //견적상태변경=> 2로 변경
            this.$axios({
              url: "/zippy/move/moveStatusSecondUpdate",
              method: "POST",
              params: {
                estimateNo: this.selectData.estimateNo,
                email: this.selectData.email,
                reservStatus: 2
              },
            }).then(res => {
              console.log(res);
              swal.fire("견적서 보내기 완료!");
              this.closeReview();
            }).catch(err => {
              console.log(err)
            })
          }
        })
      }
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

  .result-wrap {
    margin-left: 150px;
  }

  .move-main-title {
    margin: 50px;
    padding: 70px 100px 30px 100px;
  }


  .panel {
    margin: 100px;

  }
  /* 정보정렬 */
  .info{
  margin: 20px 20px 20px 200px;
}
.move-info{
  padding-left: 100px;
}
div{
  background-color: white;
}

  #used-main-dropbox1 {
    margin-top: 30px;
    width: 200px;
    float: right;
    margin-right: 100px;
  }

  #used-main-dropbox2 {
    margin-top: 30px;
    width: 200px;
    float: right;
    margin-right: 100px;
  }

  span {
    font-weight: bold;
  }

  .form-check {
    margin-top: 30px;
    margin-bottom: 20px;
    margin-left: 50px;
    float: left;
  }

  .form-check-input {
    border: 1px solid #B3E3C3;
  }

  .form-check-input:checked {
    background-color: #B3E3C3;
    border: 1px solid #B3E3C3;
  }
</style>