<template>
  <div class="wrapper">
    <moveNav></moveNav>

    <form id="contactForm">
    <h2>이사 견적을 위해 입력한 정보를 확인해주세요.</h2>

    <input type="hidden" name="movingOption" v-model="checkk">
    <input type="hidden" name="email" v-model="emailSend">
    <input type="hidden" name="movingMemo" v-model="movingMemoSend">
    
    <input type="hidden" name="departAddress" v-model="moveAddress.address">
    <input type="hidden" name="arriveAddress" v-model="moveAddress.address2">

    <input type="hidden" name="movingDate" v-model="moveDate.date">
    <input type="hidden" name="movingTime" v-model="moveDate.time">
    <input type="hidden" name="estimateType" v-model="moveEstimateType">

    <input type="hidden" name="departZipCode" v-model="moveAddress.postcode">
    <input type="hidden" name="departDetail" v-model="moveAddress.detailAddress">
    <input type="hidden" name="departExtra" v-model="moveAddress.extraAddress">
    <input type="hidden" name="arriveZipCode" v-model="moveAddress.postcode2">
    <input type="hidden" name="arriveDetail" v-model="moveAddress.detailAddress2">
    <input type="hidden" name="arriveExtra" v-model="moveAddress.extraAddress2">

    <input type="hidden" name="moveType" v-model="moveType">
    <input type="hidden" name="visitDate" v-model="moveVisit.date">
    <input type="hidden" name="visitTime" v-model="moveVisit.time">
    <input type="hidden" name="requestDate" v-model="requestDateSend">

    <!-- 이사유형 -->
    <v-expansion-panels>
      <v-expansion-panel>
        <v-expansion-panel-header v-slot="{ open }">
          <v-row no-gutters>
            <v-col cols="4"> 이사유형 </v-col>
            <v-col cols="8" class="text--secondary">
              <v-fade-transition leave-absolute>
                <span v-if="open" key="0"> 선택한 이사유형 </span>
                <span v-else key="1">
                  이사유형:{{ moveType || "Not set" }}
                </span>
              </v-fade-transition>
            </v-col>
          </v-row>
        </v-expansion-panel-header>

        <v-expansion-panel-content>
          <v-row no-gutters>
            <v-spacer></v-spacer>
            <v-col cols="5">
              <v-select v-model="moveType" :items="types" chips flat solo outlined placeholder="선택한 이사유형 불러오기">
              </v-select>

              <div class="drop-btn">
                <v-btn text color="secondary"> 취소 </v-btn>
                <v-btn text color="primary"> 수정 </v-btn>
              </div>
            </v-col>
            
          </v-row>
        </v-expansion-panel-content>
      </v-expansion-panel>

      <!-- 이사날짜 -->
      <v-expansion-panel>
        <v-expansion-panel-header v-slot="{ open }">
          <v-row no-gutters>
            <v-col cols="4"> 이사희망일 및 시간 </v-col>
            <v-col cols="8" class="text--secondary">
              <v-fade-transition leave-absolute>
                <span v-if="open">이사희망 날짜와 시간을 선택해주세요.</span>
                <v-row v-else no-gutters style="width: 100%">
                  <v-col cols="6">
                    이사 희망일: {{ moveDate.date || "Not set" }}
                  </v-col>
                  <v-col cols="6">
                    이사 희망시간: {{ moveDate.time || "Not set" }}
                  </v-col>
                </v-row>
              </v-fade-transition>
            </v-col>
          </v-row>
        </v-expansion-panel-header>

        <v-expansion-panel-content>
          <v-row justify="space-around" no-gutters>
            <v-col cols="3">
              <v-menu ref="startMenu" :close-on-content-click="false" :return-value.sync="moveDate.date" offset-y
                min-width="290px">
                <template v-slot:activator="{ on, attrs }">
                  <v-text-field v-model="moveDate.date" label="이사희망일" prepend-icon="mdi-calendar" readonly v-bind="attrs"
                    v-on="on"></v-text-field>
                </template>
                <v-date-picker v-model="date" no-title scrollable>
                  <v-spacer></v-spacer>
                  <v-btn text color="primary" @click="$refs.startMenu.isActive = false">
                    취소
                  </v-btn>
                  <v-btn text color="primary" @click="$refs.startMenu.save(date)">
                    수정
                  </v-btn>
                </v-date-picker>
              </v-menu>
            </v-col>

            <v-col cols="3">
              <v-menu ref="endMenu" :close-on-content-click="false" :return-value.sync="moveDate.time" offset-y
                min-width="290px">
                <template v-slot:activator="{ on, attrs }">
                  <v-text-field v-model="moveDate.time" label="이사 희망시간" prepend-icon="mdi-clock" readonly v-bind="attrs"
                    v-on="on"></v-text-field>
                </template>
                <!-- <v-date-picker
                v-model="date"
                no-title
                scrollable
              > -->
                <v-time-picker v-model="time" ampm-in-title format="ampm">
                  <v-spacer></v-spacer>
                  <v-btn text color="primary" @click="$refs.endMenu.isActive = false">
                    취소
                  </v-btn>
                  <v-btn text color="primary" @click="$refs.endMenu.save(time)">
                    수정
                  </v-btn>
                </v-time-picker>
              </v-menu>
            </v-col>
          </v-row>
        </v-expansion-panel-content>
      </v-expansion-panel>


      <!-- 방문 희망 날짜 및 시간 -->
      <v-expansion-panel>
        <v-expansion-panel-header v-slot="{ open }">
          <v-row no-gutters>
            <v-col cols="4"> 견적 방문 희망일 및 시간 </v-col>
            <v-col cols="8" class="text--secondary">
              <v-fade-transition leave-absolute>
                <span v-if="open">방문희망 날짜와 시간을 선택해주세요.</span>
                <v-row v-else no-gutters style="width: 100%">
                  <v-col cols="6">
                    방문 희망일: {{ moveVisit.visitDate || "Not set" }}
                  </v-col>
                  <v-col cols="6">
                    방문 희망시간: {{ moveVisit.visitTime || "Not set" }}
                  </v-col>
                </v-row>
              </v-fade-transition>
            </v-col>
          </v-row>
        </v-expansion-panel-header>

        <v-expansion-panel-content>
          <v-row justify="space-around" no-gutters>
            <v-col cols="3">
              <v-menu ref="startMenu" :close-on-content-click="false" :return-value.sync="moveVisit.visitDate" offset-y
                min-width="290px">
                <template v-slot:activator="{ on, attrs }">
                  <v-text-field v-model="moveVisit.visitDate" label="방문희망일" prepend-icon="mdi-calendar" readonly v-bind="attrs"
                    v-on="on"></v-text-field>
                </template>
                <v-date-picker v-model="visitDate" no-title scrollable>
                  <v-spacer></v-spacer>
                  <v-btn text color="primary" @click="$refs.startMenu.isActive = false">
                    취소
                  </v-btn>
                  <v-btn text color="primary" @click="$refs.startMenu.save(visitDate)">
                    수정
                  </v-btn>
                </v-date-picker>
              </v-menu>
            </v-col>

            <v-col cols="3">
              <v-menu ref="endMenu" :close-on-content-click="false" :return-value.sync="moveVisit.visitTime" offset-y
                min-width="290px">
                <template v-slot:activator="{ on, attrs }">
                  <v-text-field v-model="moveVisit.visitTime" label="방문 희망시간" prepend-icon="mdi-clock" readonly v-bind="attrs"
                    v-on="on"></v-text-field>
                </template>
                <!-- <v-date-picker
                v-model="date"
                no-title
                scrollable
              > -->
                <v-time-picker v-model="visitTime" ampm-in-title format="ampm">
                  <v-spacer></v-spacer>
                  <v-btn text color="primary" @click="$refs.endMenu.isActive = false">
                    취소
                  </v-btn>
                  <v-btn text color="primary" @click="$refs.endMenu.save(visitTime)">
                    수정
                  </v-btn>
                </v-time-picker>
              </v-menu>
            </v-col>
          </v-row>
        </v-expansion-panel-content>
      </v-expansion-panel>


      <!-- 3. 주소 -->
      <v-expansion-panel>
        <v-expansion-panel-header v-slot="{ open }">
          <v-row no-gutters>
            <v-col cols="4"> 이사 출발지 도착지 주소 </v-col>
            <v-col cols="8" class="text--secondary">
              <v-fade-transition leave-absolute>
                <span v-if="open">이사 출발지와 도착지 주소를 확인해주세요.</span>
                <v-row v-else no-gutters style="width: 100%">
                  <v-col cols="6">
                    이사 출발지 주소: {{ moveAddress.address, moveAddress.detailAddress || "Not set" }}
                  </v-col>
                  <v-col cols="6">
                    이사 도착지 주소: {{ moveAddress.address2, moveAddress.detailAddress2 || "Not set" }}
                  </v-col>
                </v-row>
              </v-fade-transition>
            </v-col>
          </v-row>
        </v-expansion-panel-header>

        <v-expansion-panel-content>
          <v-row justify="space-around" no-gutters>
            <v-col cols="3">
              <v-menu ref="startMenu" :close-on-content-click="false" :return-value.sync="moveAddress.address" offset-y
                min-width="290px">
                <template v-slot:activator="{ on, attrs }">
                  <v-text-field v-model="moveAddress.address" label="출발지 주소" prepend-icon="mdi-home" readonly v-bind="attrs"
                  v-on="on" ></v-text-field>
                    
                </template>
                
                <v-card>
                  
                  우편번호 :
                <input
                  v-model="moveAddress.postcode"
                  class="type-2"
                  type="text"
                  name="zip1"
                  id ="post1"
                  style="width: 80px; height: 26px"
                />
                <button
                  class="custom-btn btn-4"
                  type="button"
                  @click="execDaumPostcode(1)"
                >
                  검색
                </button>
                <br />
                주소 :
                <input
                v-model="moveAddress.address"
                  id="address1"
                  class="type-2"
                  type="text"
                  name="addr1"               
                  style="width: 300px; height: 30px"
                  readonly
                /><br />
                상세 :
                <input
                v-model="moveAddress.detailAddress"
                  id="detailAddress1"
                  class="type-2"
                  type="text"
                  name="addr2"
                  style="width: 300px; height: 30px"
                /><br />
                참고항목 :
                <input
                v-model="moveAddress.extraAddress"
                  type="text"
                  id="extraAddress1"
                  class="type-2"
                  placeholder="참고항목"
                />

                  <v-spacer></v-spacer>
                  <v-btn text color="primary" @click="$refs.startMenu.isActive = false">
                    취소
                  </v-btn>
                  <v-btn text color="primary" @click="$refs.startMenu.save(address, detailAddress)">
                    수정
                  </v-btn>
                </v-card>
                <!-- </v-date-picker> -->
              </v-menu>
            </v-col>

            <v-col cols="3">
              <v-menu ref="endMenu" :close-on-content-click="false" :return-value.sync="moveAddress.address2" offset-y
                min-width="290px">
                <template v-slot:activator="{ on, attrs }">
                  <v-text-field v-model="moveAddress.address2" label="도착지 주소" prepend-icon="mdi-home" readonly v-bind="attrs"
                    v-on="on"></v-text-field>
                </template>

                <v-card>
                  
                  우편번호 :
      <input
      v-model="moveAddress.postcode2"
        class="type-2"
        type="text"
        name="zip2"
        id="post2"  
        style="width: 80px; height: 26px"
      />
      <button
        class="custom-btn btn-4"
        type="button"
        @click="execDaumPostcode(2)"
      >
        검색
      </button>
      <br />
      주소 :
      <input
      v-model="moveAddress.address2"
        id="address2"
        class="type-2"
        type="text"
        name="addr3"
        style="width: 300px; height: 30px"
        readonly
       
      /><br />
      상세 :
      <input
      v-model="moveAddress.detailAddress2"
        id="detailAddress2"
        class="type-2"
        type="text"
        name="addr4"
        style="width: 300px; height: 30px"
        
      /><br />
      참고항목 :
      <input
      v-model="moveAddress.extraAddress2"
        type="text"
        id="extraAddress2"
        class="type-2"
        placeholder="참고항목"
        
      
      />

                  <v-spacer></v-spacer>
                  <v-btn text color="primary" @click="$refs.endMenu.isActive = false">
                    취소
                  </v-btn>
                  <v-btn text color="primary" @click="$refs.endMenu.save(postcode2)">
                    수정
                  </v-btn>
                </v-card>
                <!-- </v-time-picker> -->
              </v-menu>
            </v-col>
          </v-row>
        </v-expansion-panel-content>
      </v-expansion-panel>

      <!-- 집정보 선택 -->
      <v-expansion-panel>
        <v-expansion-panel-header v-slot="{ open }">
          <v-row no-gutters>
            <v-col cols="4"> 집 정보 </v-col>
            <v-col cols="8" class="text--secondary">
              <!-- <v-fade-transition leave-absolute>
              <span
                v-if="open"
                key="0"
              >
                
              </span>
              <span
                v-else
                key="1"
              >
                {{ move.house }}
                {{ move.square }}
                {{ move.floor }}
              </span>
            </v-fade-transition> -->

              <v-fade-transition leave-absolute>
                <span v-if="open">집 정보를 확인해주세요.</span>
                <v-row v-else no-gutters style="width: 100%">
                  <v-col cols="4">
                    거주형태: {{ moveInfo.houseType || "Not set" }}
                  </v-col>
                  <v-col cols="4"> 평수: {{ moveInfo.spaceOfHome || "Not set" }} </v-col>
                  <v-col cols="4"> 층수: {{ moveInfo.floor || "Not set" }} </v-col>
                </v-row>
              </v-fade-transition>
            </v-col>
          </v-row>
        </v-expansion-panel-header>

        <v-expansion-panel-content>
          <v-row no-gutters>
            <v-spacer></v-spacer>
            <v-col cols="10">
              <v-select class="select-home" v-model="moveInfo.houseType" :items="houses" chips flat solo outlined
                placeholder="선택한 집형태 불러오기"></v-select>

              <v-select v-model="moveInfo.spaceOfHome" :items="squares" chips flat solo outlined placeholder="선택한 집평수 불러오기">
              </v-select>
              <v-select class="select-home" v-model="moveInfo.floor" :items="floors" chips flat solo outlined
                placeholder="선택한 집층수 불러오기"></v-select>

              <div class="drop-btn">
                <v-btn text color="secondary"> 취소 </v-btn>
                <v-btn text color="primary"> 수정 </v-btn>
              </div>
            </v-col>
          </v-row>
        </v-expansion-panel-content>
      </v-expansion-panel>

      <!-- 개수 선택 -->
      <v-expansion-panel>
        <v-expansion-panel-header v-slot="{ open }">
          <v-row no-gutters>
            <v-col cols="4"> 방 개수 정보 </v-col>
            <v-col cols="8" class="text--secondary">
              <v-fade-transition leave-absolute>
                <span v-if="open">방, 화장실, 베란다 개수를 확인해주세요.</span>
                <v-row v-else no-gutters style="width: 100%">
                  <v-col cols="4">
                    방 개수: {{ moveInfo.roomNum || "Not set" }}
                  </v-col>
                  <v-col cols="4">
                    화장실 개수: {{ moveInfo.toilet || "Not set" }}
                  </v-col>
                  <v-col cols="4">
                    베란다 개수: {{ moveInfo.veranda || "Not set" }}
                  </v-col>
                </v-row>
              </v-fade-transition>
            </v-col>
          </v-row>
        </v-expansion-panel-header>

        <v-expansion-panel-content>
          <v-row no-gutters>
            <v-spacer></v-spacer>
            <v-col cols="10">
              <v-select class="select-home" v-model="moveInfo.roomNum" :items="rooms" chips flat solo outlined
                placeholder="선택한 방 개수 불러오기"></v-select>

              <v-select class="select-home" v-model="moveInfo.toilet" :items="toilets" chips flat solo outlined
                placeholder="선택한 화장실 개수 불러오기"></v-select>
              <v-select class="select-home" v-model="moveInfo.veranda" :items="verandas" chips flat solo outlined
                placeholder="선택한 베란다 개수 불러오기"></v-select>

              <div class="drop-btn">
                <v-btn text color="secondary"> 취소 </v-btn>
                <v-btn text color="primary"> 수정 </v-btn>
              </div>
            </v-col>
          </v-row>
        </v-expansion-panel-content>
      </v-expansion-panel>

      <!-- 기타 정보 -->
      <v-expansion-panel>
        <v-expansion-panel-header v-slot="{ open }">
          <v-row no-gutters>
            <v-col cols="4"> 기타 시설 정보 </v-col>
            <v-col cols="8" class="text--secondary">
              <v-fade-transition leave-absolute>
                <span v-if="open">1층 별도계단, 엘레베이터, 주차장 유무를 확인해주세요.</span>
                <v-row v-else no-gutters style="width: 100%">
                  <v-col cols="4">
                    1층 별도계단: {{ moveInfo.extraStairs || "Not set" }}
                  </v-col>
                  <v-col cols="4">
                    엘레베이터: {{ moveInfo.elevator || "Not set" }}
                  </v-col>
                  <v-col cols="4">
                    주차장: {{ moveInfo.parkable || "Not set" }}
                  </v-col>
                </v-row>
              </v-fade-transition>
            </v-col>
          </v-row>
        </v-expansion-panel-header>

        <v-expansion-panel-content>
          <v-row no-gutters>
            <v-spacer></v-spacer>
            <v-col cols="10">
              <v-select class="select-home" v-model="moveInfo.extraStairs" :items="extraStairs" chips flat solo outlined
                placeholder="1층 별도 계단"></v-select>

              <v-select class="select-home" v-model="moveInfo.elevator" :items="elevator" chips flat solo outlined
                placeholder="엘레베이터"></v-select>

              <v-select class="select-home" v-model="moveInfo.parkable" :items="parkable" chips flat solo outlined
                placeholder="주차가능 여부"></v-select>

              <div class="drop-btn">
                <v-btn text color="secondary"> 취소 </v-btn>
                <v-btn text color="primary"> 수정 </v-btn>
              </div>
            </v-col>
          </v-row>
        </v-expansion-panel-content>        
      </v-expansion-panel>
    
    </v-expansion-panels>

    <v-btn
  color="success"
  elevation="10"
  
  @click="moveInfoCheck()"
>확인완료</v-btn>

    <!-- <v-sheet color="white" elevation="3" height="250" width="250"></v-sheet> -->
    <div class="table">
      
    <table>
      <thead><h4>유의사항을 확인해주세요.</h4></thead>
      <tbody>
        <tr><td>※ 비대면 견적 서비스는 이사출발지와 도착지의 집구조와, 옮겨야하는 짐의 자세한 정보가 있어야 정확한 견적이 가능합니다. </td></tr>
        <tr><td>※ 견적 요청에 기재한 정보가 실제 집구조와 이삿짐 정보와 다를 경우, 이삿날 추가 비용이 발생할 수 있습니다. </td></tr>
        <tr><td>※ 부정확한 정보 기재와 소비자의 단순변심으로 인한 추가비용발생에 대해서 본 웹사이트는 책임이 없습니다. </td></tr>
        <tr><td>※ 한 번 견적요청을 신청하면 영업일 기준 3일동안 견적 요청이 유효합니다. </td></tr>
      </tbody>
    </table>
  
    <div class="agree">
      <v-checkbox
              v-model="ex4"
              label="유의사항을 확인하고 숙지하였습니다."
              color="success"
              value="success"
              hide-details
              class="agree-check"
            ></v-checkbox>
    </div>
  </div> 
  <div class="final-btn">
  <v-btn
  type="button"
  color="success"
  elevation="10"
  
  @click="finalSend()"
>확인완료</v-btn>



</div>

  
    


</form>
  </div>
</template>

<script>
  import moveNav from '../../components/move/moveNav.vue';
  export default {

    props : ['moveVisit', 'moveEstimateType', 'moveType', 'moveInfo', 'moveDate', 'moveAddress'],   
    components: {
      moveNav,
    },

    data: () => ({
      checkk: "",
      emailSend : "zippy@naver.com",
      movingMemoSend: "MemoMemo",
      requestDateSend: "2022-10-14",

      date: null,
      time: null,
      visitDate: null,
      visitTime: null,
      postcode: null,
      postcode2: null,
      
      //items
      types: ["소형이사", "가정이사"],
      houses: ["빌라/주택", "오피스텔", "아파트"],
      squares: [
        "10평이하",
        "11-15평",
        "16-20평",
        "21-25평",
        "26-30평",
        "31-35평",
        "36-40평",
        "40평이상",
      ],
      floors: [
        "1","2","3","4","5","6","7","8","9","10","11","12","13","14","15",
        "16","17","18","19","20","21","22","23","24","25","26","27","28","29",
        "30","30층 이상",
      ],
      rooms: ["원룸", "투룸", "쓰리룸", "쓰리룸이상"],
      toilets: ["1", "2", "3", "4", "5개 이상"],
      verandas: ["0","1", "2", "3", "4", "5개 이상"],
      extraStairs: ["있음", "없음"],
      elevator: ["있음", "없음"],
      parkable: ["가능", "불가능"],

      // 우편번호
          postcode: "",
          address: "",
          detailAddress:"",
          extraAddress: "",
          
          postcode2: "",
          address2: "",
          detailAddress2:"",
          extraAddress2: "",

      // checkbox
      ex4: ['success'],
    }),

    methods: {

      finalSend : function(){
        
        contactForm.movingOption.value= "["+ JSON.stringify(this.moveInfo) +"]";
        //form으로 데이터보내기
        var formData = new FormData(document.querySelector('#contactForm')); 
        
        console.log(this.checkk);

        this.$axios({
          url: "http://localhost:8090/zippy/move/moveContactCheck",
          method: "POST",
          // headers: {
          //   "Content-Type": "application/json; charset=utf-8"
          // },
          data: formData
        }).then(res => {
          console.log(res);
        }).catch(err => {
          console.log(err)
        })


        var confirmVal = confirm("견적 요청을 보내시겠습니까?");
        if(confirmVal == true){
        this.$router.push({

          name: "moveResult",
          params: {
            moveImage: this.moveImage,
            moveDetail: this.moveDetail,
            moveEstimateType: this.moveEstimateType,
            moveInfo: this.moveInfo,
            moveType: this.moveType,
            moveDate: this.moveDate, 
            moveAddress: this.moveAddress
          
          }
          })
        } else {

        } 

      },

      moveInfoCheck : function(){   
        
        //값이 잘 넘어가는지 확인

        this.checkk= "["+JSON.stringify(this.moveDetail[0])+","+ JSON.stringify(this.moveInfo) +"]";

        console.log(JSON.stringify(this.moveDetail[0]));
        
        console.log(JSON.stringify(this.moveInfo))
        console.log(moveInfo);
        console.log(this.checkk);
        // console.log(JSON.stringify(this.moveInfo));
        // console.log(this.contactCheckk);

        // console.log(this.moveVisit);
        // console.log(this.moveInfo);
        // console.log(this.moveType);
        // console.log(this.moveEstimateType);        
      },

      execDaumPostcode(number) {  
      console.log(number);
      var postcode = document.querySelector("#post"+number);
      var addr = document.querySelector("#address"+number);
      var detail = document.querySelector("#detailAddress"+number);  
      var extra = document.querySelector("#extraAddress"+number);     

      new window.daum.Postcode({
        oncomplete: (data) => {
          console.log(data);
          if (data.userSelectedType === "R") {
            // 사용자가 도로명 주소를 선택했을 경우
            // this.moveInfo.addr1.address = data.roadAddress;
            addr.value = data.roadAddress;
          } else {
            // 사용자가 지번 주소를 선택했을 경우(J)
            addr.value = data.jibunAddress;
          }

          // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
          if (data.userSelectedType === "R") {
            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
              detail.value += data.bname;
            }

            // 건물명이 있고, 공동주택일 경우 추가한다.
            if (data.buildingName !== "" && data.apartment === "Y") {
              detail.value +=
              (detail.value !== "") ? `, ${data.buildingName}` : data.buildingName;
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if (detail.value !== "") {
              detail.value = `(${detail.value})`;
            }
          } else {
            detail.value = "";
          }
          // 우편번호를 입력한다.
          postcode.value = data.zonecode;
        },
      }).open();
      
    },

    },
  };
</script>

<style scoped>
  table{
    width: 100%;
    text-align: center;
  }
  thead{
    text-align: center;
    padding-bottom: 50px;
  }
  .agree{
    text-align: center;
    padding-top: 50px;
    padding-bottom: 50px;
  }
.agree-check{
  display: inline-block;
}
.final-btn{
  text-align: center;
  padding-bottom: 50px;
}


  h2 {
    text-align: center;
    padding-top: 100px;
    padding-bottom: 100px;
  }

  .drop-btn {
    /* float: right; */
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);
    margin-left: 500px;
    border: 1px solid #ddd;
    border-radius: 5px;
  }

  .v-select {
    float: left;
    margin: 50px;
    /* margin-left: 100px; */
  }

  .v-expansion-panels {
    padding-bottom: 100px;
  }

  /* zip */
  .depart-address {
    margin-top: 100px;
  }

  .move-drop {
    display: inline-block;
    width: 250px;
    height: 200px;
  }

  .move-select-input {
    display: inline-block;
    width: 250px;
    height: 200px;
  }

  /* 버튼 */

  .custom-btn {
    width: 200px;
    height: 100px;
    color: #fff;
    border-radius: 5px;
    padding: 10px 25px;
    font-family: "Lato", sans-serif;
    font-weight: bold;
    /* font-size: 20px; */
    background: transparent;
    cursor: pointer;
    transition: all 0.3s ease;
    position: relative;
    display: inline-block;
    box-shadow: inset 2px 2px 2px 0px rgba(255, 255, 255, 0.5),
      7px 7px 20px 0px rgba(0, 0, 0, 0.1), 4px 4px 5px 0px rgba(0, 0, 0, 0.1);
    outline: none;
  }

  .btn-4 {
    background: rgb(163, 162, 162);
    background: linear-gradient(0deg,
        rgba(163, 162, 162, 1) 0%,
        rgb(163, 162, 162) 100%);
    border: none;
    width: 80px;
    height: 32px;
    font-size: 15px;
  }

  .btn-4:hover {
    /* background: rgb(136, 221, 164);
      background: linear-gradient(0deg, rgba(136, 221, 164, 1) 0%, rgb(131, 221, 161) 100%); */
    box-shadow: 4px 4px 6px 0 rgba(255, 255, 255, 0.5),
      -4px -4px 6px 0 rgba(116, 125, 136, 0.5),
      inset -4px -4px 6px 0 rgba(255, 255, 255, 0.2),
      inset 4px 4px 6px 0 rgba(0, 0, 0, 0.4);
  }

  .btn-3 {
    background: rgb(163, 162, 162);
    background: linear-gradient(0deg,
        rgba(163, 162, 162, 1) 0%,
        rgb(163, 162, 162) 100%);
    border: none;
    height: 50px;
    width: 150px;
    font-size: 20px;
  }

  .btn-3:hover {
    /* background: rgb(136, 221, 164);
      background: linear-gradient(0deg, rgba(136, 221, 164, 1) 0%, rgb(131, 221, 161) 100%); */
    box-shadow: 4px 4px 6px 0 rgba(255, 255, 255, 0.5),
      -4px -4px 6px 0 rgba(116, 125, 136, 0.5),
      inset -4px -4px 6px 0 rgba(255, 255, 255, 0.2),
      inset 4px 4px 6px 0 rgba(0, 0, 0, 0.4);
  }
</style>
