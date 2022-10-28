<template>
  <div class="result-wrap">
    <move-nav-bar @click="categoryVal=$event.target.innerText"></move-nav-bar>
    <div class="move-main-title">
      <h3>견적요청 조회</h3>
    </div>

    <div class="form-check">
      <input @click="checkbox ()" class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
      <label class="form-check-label" for="flexCheckDefault">
        견적완료된 요청보기
      </label>
    </div>

    <!--  -->
    <div id="used-main-dropbox2">
      <v-select :disabled="select2==false" @change="dropVal()" v-model="select" :items="items" item-text="name"
        item-value="value" label="보기정렬" color="#212529" persistent-hint single-line dense width="50"></v-select>
    </div>

    <div id="used-main-dropbox1">
      <v-select @change="dropVal2()" v-model="select2" :items="drops" item-text="name" item-value="value2" label="지역정렬"
        color="#212529" persistent-hint single-line dense width="50"></v-select>
    </div>


    <hr />
    <div class="panel">
      <v-expansion-panels>
        <v-expansion-panel v-if="list.length != 0" v-for="(item, i) in list">
          <v-expansion-panel-header>
          
            <span>NO.{{item.estimateNo}}</span> &nbsp;&nbsp; 견적요청일 :
            <span>{{item.requestDate}}</span>&nbsp;&nbsp; 견적 방법 : <span>{{item.estimateType}}</span>
            <v-col cols="12" sm="6" md="4">
              <!-- <div id="mus" v-if="item.reservStatus == 0">견적 상태 : <span>견적전</span></div>
              <div id="mus" v-if="item.reservStatus == 1">견적 상태 : <span>견적발송</span></div>
              <div id="mus" v-if="item.reservStatus == 2">견적 상태 : <span>2차견적</span></div>
              <div id="mus" v-if="item.reservStatus == 3">견적 상태 : <span>예약요청</span></div>
              <div id="mus" v-if="item.reservStatus == 4">견적 상태 : <span>예약완료</span></div>
              <div id="mus" v-if="item.reservStatus == 5">견적 상태 : <span>이사완료</span></div>
              <div id="mus" v-if="item.reservStatus == 9">견적 상태 : <span>취소</span></div> -->
            </v-col>
          </v-expansion-panel-header>
          <v-expansion-panel-content>
            <div>요청회원 : <span>{{item.email}}</span></div>
            <div>이사 종류 : <span>{{item.moveType}}</span></div>
            <div>이사희망일 : <span>{{item.movingDate}}</span></div>
            <div>이사희망시간 : <span>{{item.movingTime}}</span></div>
            <div>출발지 주소 : <span>{{item.departZipCode}}</span> <br><span>{{item.departAddress}}</span>
              <span>{{item.departDetail}}</span></div>
            <div>도착지 주소 : <span>{{item.arriveZipCode}}</span> <br><span>{{item.arriveAddress}}</span>
              <span>{{item.arriveDetail}}</span></div>
            <div> 이사 정보 : <span v-html="par(item.commonOption)"></span></div>
            <div v-if="item.estimateType == '비대면견적'">
            <div >이삿짐 정보 :<span v-html="my(item.movingOption)"></span></div>
            </div> 

            <div v-if="item.movingMemo != null">
              <div>이사 요청사항 : <span>{{item.movingMemo}}</span></div>
            </div>
            <div v-if="item.estimateType == '대면견적'">
              <div>견적 방문희망일 : <span>{{item.visitDate}}</span></div>
              <div>견적 방문희망시간 : <span>{{item.visitTime}}</span></div>
            </div>
            <div v-if="item.estimateType == '비대면견적'" id="imgPanel">
            <!-- <div >이미지 : <span>{{item.moveImage}}</span> -->
             
             <!-- 펼치기 -->

              <v-card-actions>

            <v-spacer></v-spacer>
                <v-label >방사진보기</v-label> <v-btn
              icon
              @click="show = !show"
            >
              <v-icon>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
            </v-btn>
          </v-card-actions>

          <v-expand-transition>
            <div v-show="show">
              <v-divider></v-divider>

            <div id="showImg">
              방입구
              <div v-for="img in photoList">
                <img v-if="img.estimateNo == item.estimateNo && img.imgType ==1" 
                :src="'/zippy/common/img/move/' + img.houseImg"/>
              </div>

              방중앙
              <div v-for="img in photoList">
                <img v-if="img.estimateNo == item.estimateNo && img.imgType ==2" 
                :src="'/zippy/common/img/move/' + img.houseImg"/>
              </div>

              내부
              <div v-for="img in photoList">
                <img v-if="img.estimateNo == item.estimateNo && img.imgType ==3" 
                :src="'/zippy/common/img/move/' + img.houseImg"/>
              </div>
              
            </div>
              <!-- <v-card-text>
                I'm a thing. But, like most politicians, he promised more than he could deliver. You won't have time for sleeping, soldier, not with all the bed making you'll be doing. Then we'll go with that data file! Hey, you add a one and two zeros to that or we walk! You're going to do his laundry? I've got to find a way to escape.
              </v-card-text> -->
            </div>
          </v-expand-transition>
          </div>

            <v-card-actions >
              <v-spacer></v-spacer>

              <v-row justify="center">
                <v-btn v-bind="attrs" v-on="on" width="80">채팅하기</v-btn>
                <v-dialog v-model="dialog" persistent max-width="600px">
                  
                  <template v-slot:activator="{ on, attrs }">
                    
                    <v-btn :id="i" id="estBtn" @click="modalVal(i)" color="success" dark width="80">
                      견적작성
                    </v-btn>
                 
                  </template>

                  <!-- 모달 -->
                  <form id="estimateForm" name="estimateForm" >
                    <!-- v-for="i in list" -->
                    <div>
                      <v-card  :id="i">
                        <v-card-title>
                          <span class="text-h5">견적서 작성</span>
                        </v-card-title>
                        <v-card-text>
                          <v-container>
                            <v-row>
                              <v-col cols="12" sm="6" md="4">
                                <v-text-field label="견적번호*" id="estimateNo" required readonly 
                                  v-model="selectData.estimateNo"></v-text-field>                                 
                              </v-col><br />
                              
                              <v-col cols="12" >                              
                                <v-text-field label="업체명*" hint="고객에게 보여주는 업체의 이름입니다." v-model="selectData.compName"></v-text-field>   
                            </v-col>
                          

                              <v-col cols="12">
                                <v-text-field label="업체아이디*" hint="고객에게 보여주는 업체의 이메일입니다." v-model="selectData.email"></v-text-field>
                                <!-- <v-text-field label="요청 회원*" required readonly v-model="selectData.email"> -->
                                <!-- </v-text-field> -->
                              </v-col>
                              <v-col cols="12">
                                <v-text-field label="1차 견적타입*" required readonly 
                                 hint="고객이 기본적으로 요청한 견적 타입입니다." 
                                 v-model="selectData.estimateType"></v-text-field>
                              </v-col>
                              <v-col cols="12">
                                <v-text-field  label="1차 견적가격*" type="number"  id="firstPrice" 
                                hint="고객에게 처음 제시하는 견적가격입니다. 견적제시는 2차까지만 가능합니다. 신중하게 결정해주세요.(추후 수정불가)"
                                required :value="selectData.firstEstimatePrice">
                                </v-text-field>
                              </v-col>

                              <v-col>
                                <v-autocomplete :value="selectData.responseMemo"  id="memo"
                                  :items="['전문적이에요', '꼼꼼해요', '손이 빨라요', '저렴해요', '깔끔해요', '친절해요', '견적네고 가능해요', '고급장비 사용해요', '안전해요']"
                                  label="어필하기" multiple hint="고객에게 어필할 업체의 특징을 선택해주세요."></v-autocomplete>
                              </v-col>
                              
                            </v-row>
                          </v-container>
                          <small>*필수입력 사항을 입력해주세요.</small>
                        </v-card-text>
                        <v-card-actions>
                          <v-spacer></v-spacer>

                          <v-btn color="gray" text @click="dialog = false">
                            닫기
                          </v-btn>
                      

                          <v-btn id="sendbtn" color="success darken-1" text @click="sendEstimate(i)">
                            견적보내기
                          </v-btn>
                        </v-card-actions>
                      </v-card>
                    </div>
                    <input type="hidden" name="estimateNo" id="estimateNo" v-model="selectData.estimateNo">
                    <input type="hidden" name="email" id="email" v-model="selectData.email">
                    <input type="hidden" name="businessEmail" id="businessEmail" v-model="selectData.businessEmail">
                   
                    <input type="hidden" name="firstEstimateType" id="firstEstimateType" v-model="selectData.estimateType"> -->
                    <!-- <input type="hidden" name="secondEstimatePrice" v-model="selectData.secondEstimatePrice">
                      <input type="hidden" name="secondEstimateType"  v-model="selectData.secondEstimateType"> -->
                      <input type="hidden" name="compName" v-model="selectData.compName">

                      <input type="hidden" name="reservStatus" value="1" v-model="selectData.reservStatus">
                      <!-- <input type="hidden" name="firstEstimatePrice" id="firstPrice"  v-model="selectData.firstEstimatePrice">-->
                      <!-- <input type="hidden" name="responseMemo" id="memo" v-model="selectData.responseMemo">  -->
                  </form>

                </v-dialog>
              </v-row>

            </v-card-actions>
          </v-expansion-panel-content>

        </v-expansion-panel>
      </v-expansion-panels>
    </div>

    <!-- 폼에 담을 정보 -->
    <!--       
      <input type="hidden" name="estimateNo" id="estimateNo" :value="item.estimateNo"> 
      <input type="hidden" name="email" id="email" :value="item.email"> 
      <input type="hidden" name="firstEstimateType" id="firstEstimateType" :value="item.firstEstimateType">  -->


      <div class="text-center">
      <v-pagination v-model="page" :length="pageCount" circle color="#B3E3C3"></v-pagination>
    </div>


  </div>
</template>

<script>
  import axios from 'axios';
  import swal from 'sweetalert2';
  import MoveNavBar from '../../components/move/MoveNavBar.vue';

export default {
  components: {
    MoveNavBar
  },
  
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
      
        item: "",
        email: "",
        estimateNo: "",
        responseMemo: "",
        estimateType: "",
        firstEstimateType:"",
        firstEstimatePrice: "",
        secondEstimateType: "",
        secondEstimatePrice: "",
        compName: "82이사",
        reservStatus: '',
        businessEmail: "",

        list: [],
        vo: {
          email: "",
          requestDate: "",
          departAddress: "",
          arriveAddress: "",
          compAddress: "",
          estimateNo: "",
          estimateType: "",
          businessEmail: ""
        },
        

        drops: [{
            name: '전국으로 조회',
            value: '전국으로 조회'
          },
          {
            name: '내 지역만 조회',
            value: '내 지역만 조회'
          },
        ],

        items: [{
            name: '전체조회',
            value: '전체조회'
          },
          {
            name: '대면견적',
            value: '대면견적'
          },
          {
            name: '비대면견적',
            value: '비대면견적'
          },
          {
            name: '최신요청일자순',
            value: '최신요청일자순'
          },
          {
            name: '오래된요청일자순',
            value: '오래된요청일자순'
          },
          {
            name: '현재날짜요청',
            value: '현재날짜요청'
          },
          {
            name: '출발지순',
            value: '출발지순'
          },
          {
            name: '도착지순',
            value: '도착지순'
          },
        ],

        data: [],
        state:"",
        selectData:{},
        select: '',
        select2: '', //지역
        dialog: false,
        on: '',
        attrs: '',
        photoList:[]
      }
    },
    created() {
      axios({
        url: "/zippy/move/moveEstimate",
        methods: "GET",
        params: {
          email: this.$store.state.loginInfo.email,
          movingOption: "",
          commonOption: "",
          checked: "",
          dropbox: "",
          dropbox2: "",
          firstEstimateType : this.selectData.estimateType,
          reservStatus : this.list.reservStatus
        }
      }).then(res => {
        console.log(res);
        this.reservStatus = this.selectData.reservStatus;
        this.list = res.data;
        this.email= this.$store.state.loginInfo.email;
        console.log('lilsss',this.list)
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
        console.log('lilssstttt',this.photoList)
      }).catch(error => {
        console.log(error);
      })

    },
    watch: {
    },
    methods: {
      checkbox: function () {
        const ckbox = document.querySelector(".form-check-input");
        const is_cked = ckbox.checked;
        var isChecked = document.querySelector(".form-check-input").innerText = is_cked
        console.log(isChecked);
        axios({
          url: "/zippy/used/main",
          methods: "GET",
          params: {
            keyword: this.searchValue,
            location: "",
            category: this.categoryVal,
            checked: isChecked,
            dropbox: ""
          }
        }).then(res => {
          console.log(res);
          this.data = res.data;
        }).catch(err => {
          console.log(err)
        })
      },
      dropVal: function () {
        var dropValue = this.select;
        console.log(dropValue);
        console.log(this.vo.email);
        axios({
          url: "/zippy/move/moveEstimate",
          methods: "GET",
          params: {
            dropbox: dropValue,
            dropbox2: this.select2,
            email: this.$store.state.loginInfo.email,
            requestDate: this.vo.requestDate,
            departAddress: this.vo.departAddress,
            arriveAddress: this.vo.arriveAddress,
          }
        }).then(res => {
          console.log(res);
          this.list = res.data;
        }).catch(err => {
          console.log(err);
        })
      },
      dropVal2: function () {

        var dropValue2 = this.select2;
        console.log(dropValue2);
        console.log(this.vo.email);
        axios({
          url: "/zippy/move/moveEstimate",
          methods: "GET",
          params: {
            dropbox: this.select,
            dropbox2: dropValue2, //지역
            email: this.$store.state.loginInfo.email,
            requestDate: this.vo.requestDate,
            departAddress: this.vo.departAddress,
            arriveAddress: this.vo.arriveAddress,
            compAddress: this.vo.compAddress
          }
        }).then(res => {
          console.log(res);
          this.list = res.data;
        }).catch(err => {
          console.log(err);
        })
      },
      modalVal: function (i) {        
        this.selectData.firstEstimatePrice = '';    
        this.selectData.responseMemo='';
        var outside = this; 
        axios({
          url: "/zippy/move/moveEstimate",
          methods: "GET",
          params: {
            businessEmail: this.email,
            estimateNo : this.list[i].estimateNo,
            email: this.$store.state.loginInfo.email,
            estimateType: this.list[i].estimateType,
            // firstEstimatePrice: this.list[i].firstEstimatePrice,
            // responseMemo : this.list[i].responseMemo
          }
        }).then(res => {
          this.selectData.estimateNo = this.list[i].estimateNo; //{...this.list[i]}
          this.selectData.email = this.$store.state.loginInfo.email;
          // this.selectData.businessEmail = this.email
          this.selectData.estimateType = this.list[i].estimateType;
          this.selectData.reservStatus = this.selectData.reservStatus;
          this.selectData.compName = this.$store.state.loginInfo.compName;
          
          this.list = res.data;          
        }).catch(err => {
          console.log(err);
        })
        
        //여기서 여부 액시오스 쓰기 -> 알러트 띄워서 이미 작성한 견적입니다 ,,,,
        //내가 응답을 1번이라도 보낸 견적들을 가져온다
        axios({
            url: "/zippy/move/moveWhether",
            methods: "GET",
            params: {
              email: this.$store.state.loginInfo.email,
              estimateNo : this.list[i].estimateNo
            }
          }).then(res => {
            console.log(res);
            outside.data = res.data;            

            // 결과값 가져오기
            console.log("result : ",outside.data);
            if(outside.data.length == 0){
              
              outside.dialog = true;              
            } else {              
              
              swal.fire("이미 작성한 견적입니다.");
            }
          }).catch(error => {
            console.log(error);
          })
          
    },

      par : function(string){
          // var data = '{"bedCount":2,"bed":["","싱글","슈퍼싱글"],"sofaCount":1,"sofa":[""],"closetCount":1,"closet":[""],"closetsCount":1,"closets":[""],"tvCount":1,"tv":[""],"pcCount":1,"pc":[""],"fridgeCount":1,"fridge":[""],"trolleyCount":1,"trolley":[""],"etcCount":1,"etcName":[""],"etcSize":[""],"box":"16-20개","filesPhoto":""}';
          var temp = JSON.parse(string);
          console.log( "parse : "+temp);
          console.log(temp.bedCount);
         

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
      my : function(string){
      

        var temp= JSON.parse(string);
        console.log('파싱 : ',temp);
        console.log(temp.bedCount);
        var bedTemp ='침대 개수 : '+ temp.bedCount + ', 침대 사이즈 : ' + temp.bed[0];
        console.log(temp.closetCount);

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
        if (temp.bed.length > 0 && temp.bed[1]){
          detail+= `<div>침대 : ${temp.bedCount}(${temp.bed.join(",")})</div>`
        } 
        //소파
        if (temp.sofa.length > 0 &&  temp.sofa[1]){
          detail+= `<div>소파 : ${temp.sofaCount}(${temp.sofa.join(",")})</div>`
        } 
        //옷장
        if (temp.closet.length > 0 &&  temp.closet[1]){
          detail+= `<div>옷장 : ${temp.closetCount}(${temp.closet.join(",")})</div>`
        } 
        //옷장-연결장
        if (temp.closets.length > 0 &&  temp.closets[1]){
          detail+= `<div>옷장-연결장 : ${temp.closetsCount}(${temp.closets.join(",")})</div>`
        } 

        if( detail) {
          detailVal = `<div>가구</div>`+ detail
        }

          //가전
        detail = ''
        //tv
        if (temp.tv.length > 0 &&  temp.tv[1]){
          detail+= `<div>tv : ${temp.tvCount}(${temp.tv.join(",")})</div>`
        } 
        //데스크탑
        if (temp.pc.length > 0 &&  temp.pc[1]){
          detail+= `<div>데스크탑 : ${temp.pcCount}(${temp.pc.join(",")})</div>`
        } 
        //냉장고
        if (temp.fridge.length > 0 &&  temp.fridge[1]){
          detail+= `<div>냉장고 : ${temp.fridgeCount}(${temp.fridge.join(",")})</div>`
        } 
        //유모차
        if (temp.trolley.length > 0 &&  temp.trolley[1]){
          detail+= `<div>유모차 : ${temp.trolleyCount}(${temp.trolley.join(",")})</div>`
        } 

        // if (temp.etcName.length > 0 &&  temp.etcSize[0]){
        //   detail+= `<div>유모차 : ${temp.etcName}(${temp.etcSize.join(",")})</div>`
        // }

        if( detail) {
          detailVal += `<div>가전</div>`+ detail
          
        }
        
          //기타
        detail = '' 
        if(temp.etcName.length > 0){ 
          for(let i=1; i< temp.etcName.length; i++){
          detail+= `<div> ${temp.etcName[i]} : ${temp.etcSize[i]}(사이즈 cm)</div>` 
          }
          console.log(detail);
      
        if( detail) {
          detailVal += `<div>기타</div>`+ detail
        }  
        
        }
        return detailVal;
      },

      sendEstimate: function (i) {

        console.log('1차 가격 : ',document.querySelector("#firstPrice").value);
        console.log('어필 : ',document.querySelector("#memo").value);
        
        
      

        var formData = new FormData(document.querySelector('#estimateForm'));

        // this.estimateNo = document.getElementById(estimateNo).value;
        // this.email = document.getElementById(email).value;
        // this.firstEstimateType = document.getElementById(firstEstimateType).value;

        console.log("견적번호: " + this.selectData.estimateNo);
        console.log("회원: " + this.selectData.email);
        console.log("1차견적가격: " ,document.querySelector("#firstPrice").value);
        console.log("1차견적타입: " + this.selectData.estimateType);
        console.log("2차견적가격: " + this.selectData.secondEstimatePrice);
        console.log("2차견적타입: " + this.selectData.secondEstimateType);
        console.log("견적상태: " + this.selectData.reservStatus);
        console.log("업체명: " + this.selectData.compName);
        console.log("견적어필: ",document.querySelector("#memo").value);

        //견적보내기
        this.$axios({
          url: "http://localhost:8090/zippy/move/moveEstimate",
          method: "POST",
          // headers: {
          //   "Content-Type": "application/json; charset=utf-8"
          // },
          params:{
            email : this.$store.state.loginInfo.email,
            estimateNo : this.selectData.estimateNo,
            responseMemo :document.querySelector("#memo").value,
            firstEstimatePrice : document.querySelector("#firstPrice").value,
            firstEstimateType : this.selectData.estimateType,
            reservStatus : 1,
            compName : this.$store.state.loginInfo.compName
          },
          // data: this.selectData
        }).then(res => {
          console.log(res);
          
          //견적상태변경
        this.$axios({
          url: "http://localhost:8090/zippy/move/moveStatusUpdate",
          method: "POST",
         
          params:{
            estimateNo : this.list[i].estimateNo,
            email : this.selectData.email,
            reservStatus : 1
          },
          
          // data: formData
        }).then(res => {
          console.log(res);

          console.log('list!!',this.list)
          alert("견적서 보내기 완료!");
          

        }).catch(err => {
          console.log(err)
        }),

          //견적상태변경
          this.$axios({
          url: "http://localhost:8090/zippy/move/moveStatusUpdateZero",
          method: "POST",
         
          params:{
            estimateNo : this.selectData.estimateNo,
            email : this.selectData.email,
            reservStatus : 1
          },
          
          // data: formData
        }).then(res => {
          console.log(res);
          alert("견적서 보내기 완료!");
          

        }).catch(err => {
          console.log(err)
        })

        }).catch(err => {
          console.log(err)
        })

        

      }
    }
  }
</script>

<style scoped>
  .move-main-title {
    margin: 50px;
  }

  .panel {
    margin: 100px;

  }

  #used-main-dropbox1 {
    margin-top: 30px;
    width: 100px;
    float: right;
    margin-right: 100px;
  }

  #used-main-dropbox2 {
    margin-top: 30px;
    width: 100px;
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