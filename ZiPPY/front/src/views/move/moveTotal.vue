<template>
  <div class="whole-wrap">
    <moveNav></moveNav>
    <form>

  <!-- moveSelect (이사유형 선택) -->
  <div class="moveSelect-wrap">
    <h3>이사 유형을 선택해주세요.</h3>
    <div class="btn1">
      <!-- <button type="button" class="custom-btn btn-1" @click="small()" value="typeSmall">소형이사</button> -->
      <input type="button" class="custom-btn btn-1" @click="small()" value="소형이사">
    </div>

    <div class="btn2">
      <!-- <button type="button" class="custom-btn btn-2" @click="big()" value="typeBig">가정이사</button> -->
      <input type="button" class="custom-btn btn-2" @click="big()" value="가정이사">
    </div>
    <br />
    <div class="explain1">
      <table>
        <thead style="font-weight: bold;">소형이사</thead>
        <tbody>
        <tr>
          <td>• 투룸, 20평 미만에 적합</td>
        </tr>
        <tr>
          <td>• 주요차량 : 1 ~ 2.5톤 트럭</td>
        </tr>
        <tr>
          <td>• 이사종류 : 일반 / 반포장 / 포장</td>
        </tr>
        <tr>
          <td>• 평균 작업인원 : 2명이하</td>
        </tr>
      </tbody>
      </table>
    </div>

    <div class="explain2">
      <table>
        <thead style="font-weight: bold;">가정이사</thead>
        <tbody>
        <tr>
          <td>• 쓰리룸, 20평대 이상에 적합</td>
        </tr>
        <tr>
          <td>• 주요차량 : 2.5 ~ 5톤 트럭 이상</td>
        </tr>
        <tr>
          <td>• 이사종류 : 전문 포장이사</td>
        </tr>
        <tr>
          <td>• 평균 작업인원 : 3명 이상</td>
        </tr>
      </tbody>
      </table>
    </div>

    <div>

      <button type="button" class="custom-btn btn-3" @click="moveSelectNext()">선택완료</button>
    </div>
  </div>
  

<!-- 날짜, 시간 선택 -->
<div class="moveDate-wrap">
    <h3>이사 희망 날짜를 선택해주세요.</h3>
    <v-row justify="space-between" locale="ko-KR">
      <div class="subheading"></div>
      <v-date-picker
        v-model="moveDate.date"
        :events="arrayEvents"
        color="green lighten-1"
        event-color="blue lighten-1"
        header-color="green -1"
      ></v-date-picker>
    </v-row>
    <hr />
    <h3>이사 희망 시간을 선택해주세요.</h3>
    <div class="selectTime">
      <b-row>
        <b-col md="auto">
          <b-time v-model="moveDate.time" locale="ko-KR" @context="onContext"></b-time>
        </b-col>
        <b-col>
          <!-- v-if를 걸어서 널값일때는 시간 선택해달라고 안내문구 -->

          <p>
            선택 시간: <b>{{ moveDate.time }}</b>
          </p>
          <!-- <p class="mb-0">Context:</p>
      <pre v-if="context != null" class="small">{{ context.Value }}</pre> -->
        </b-col>
      </b-row>
    </div>

    <v-btn elevation="7" @click="moveDateNext()">선택완료</v-btn>
  </div>

  

  <!-- moveInfo 이사기본정보 -->
  <div class="moveInfo-wrap">
    <form id="move-form">
    <h2>이사 견적을 위한 기본 정보를 입력해주세요.</h2>
    <div class="depart-address">
      <h3>출발지 주소를 입력해주세요.</h3>
      <!-- 지도 -->

      우편번호 :
      <input        
        class="type-2"
        type="text"
        name="zip1"
        id ="post1"
        style="width: 80px; height: 26px"
      />
      <v-btn
  color="accent"
  elevation="8"
  small
  @click="execDaumPostcode(1)"
>검색</v-btn>
      <br />
      주소 :
      <input
      
        id="address1"
        class="type-2"
        type="text"
        name="addr1"               
        style="width: 300px; height: 30px"
        readonly
      /><br />
      상세 :
      <input
     
        id="detailAddress1"
        class="type-2"
        type="text"
        name="addr2"
        style="width: 300px; height: 30px"
      /><br />
      참고항목 :
      <input
     
        type="text"
        id="extraAddress1"
        class="type-2"
        placeholder="참고항목"
      />
    </div>

    <br />
    <hr />
    <br />

    <br />
    <br />

    <div class="arrive-address">
      <h3>도착지 주소를 입력해주세요.</h3>

      우편번호 :
      <input
        
        class="type-2"
        type="text"
        name="zip2"
        id="post2"  
        style="width: 80px; height: 26px"
      />
      <!-- <button
        class="custom-btn btn-4"
        type="button"
        @click="execDaumPostcode(2)"
      >
        검색
      </button> -->
      <v-btn
  color="accent"
  elevation="8"
  small
  @click="execDaumPostcode(2)"
>검색</v-btn>
      <br />
      주소 :
      <input
       
        id="address2"
        class="type-2"
        type="text"
        name="addr3"
        style="width: 300px; height: 30px"
        readonly
       
      /><br />
      상세 :
      <input
     
        id="detailAddress2"
        class="type-2"
        type="text"
        name="addr4"
        style="width: 300px; height: 30px"
        
      /><br />
      참고항목 :
      <input
       
        type="text"
        id="extraAddress2"
        class="type-2"
        placeholder="참고항목"
        
      
      />
    </div>
   

    <div class="move-drop">
      <h3>집형태</h3>
      <select id="houseType" v-model="moveInfo.houseType">
        <option value="" selected>-- 선택하세요 --</option>
        <option value="빌라/주택">빌라/주택</option>
        <option value="오피스텔">오피스텔</option>
        <option value="아파트">아파트</option>
      </select>
    </div>

    <div class="move-drop">
      <h3>방구조</h3>
      <select id="roomNum" v-model="moveInfo.roomNum">
        <option value="" selected>-- 선택하세요 --</option>
        <option value="원룸">원룸</option>
        <option value="투룸">투룸</option>
        <option value="쓰리룸">쓰리룸</option>
        <option value="쓰리룸이상">쓰리룸 이상</option>
      </select>
    </div>

    <div class="move-drop">
      <h3>집평수</h3>
      <select id="spaceOfHome" v-model="moveInfo.spaceOfHome">
        <option value="" selected>-- 선택하세요 --</option>
        <option value="10평이하">10평 이하</option>
        <option value="11-15평">10~15평</option>
        <option value="16-20평">15~20평</option>
        <option value="21-25평">20~25평</option>
        <option value="26-30평">25~30평</option>
        <option value="31-35평">30~35평</option>
        <option value="36-40평">35~40평</option>
        <option value="40평이상">40평 이상</option>
      </select>
    </div>

    <br />
    <hr />
    <br />

    <div class="move-select-input">
      <h3>층수</h3>
      <input class="type-2" id="floor" type="number" min="1" v-model="moveInfo.floor"/><label
        for="floor"
      >
        층</label
      >
    </div>

    <div class="move-select-input">
      <h3>화장실 개수</h3>
      <input class="type-2" id="toilet" type="number" min="1" v-model="moveInfo.toilet"/><label
        for="toilet"
      >
        개</label
      >
    </div>

    <div class="move-select-input">
      <h3>베란다 개수</h3>
      <input class="type-2" id="veranda" type="number" min="0" v-model="moveInfo.veranda" /><label
        for="veranda" 
      >개</label>
    </div>
    <hr />
    <br />

    <div class="detail-info-radio">
      <h3>1층 별도 계단</h3>
      <label class="test_obj">
        <input type="radio" name="stairs" value="있음" v-model="moveInfo.extraStairs"/>
        <span>있음</span>
      </label>

      <label class="test_obj">
        <input type="radio" name="stairs" value="없음" v-model="moveInfo.extraStairs"/>
        <span>없음</span>
      </label>
    </div>

    <div class="detail-info-radio">
      <h3>엘레베이터</h3>
      <label class="test_obj">
        <input type="radio" name="elev" value="있음" v-model="moveInfo.elevator"/>
        <span>있음</span>
      </label>

      <label class="test_obj">
        <input type="radio" name="elev" value="없음" v-model="moveInfo.elevator" />
        <span>없음</span>
      </label>
    </div>

    <div class="detail-info-radio">
      <h3>주차가능</h3>
      <label class="test_obj">
        <input type="radio" name="parking" value="가능" v-model="moveInfo.parkable"/>
        <span>있음</span>
      </label>

      <label class="test_obj">
        <input type="radio" name="parking" value="불가능" v-model="moveInfo.parkable" />
        <span>없음</span>
      </label>
    </div>
  </form>
    <div class="frame">
      <button type="button" id="selectBtn-bday" class="custom-btn btn-3" @click="moveInfoNext()">선택완료</button>
    </div>
  </div>


  <!-- 견적방법 -->
  <div class="moveType-wrap">
    <h3>견적 방법을 선택해주세요.</h3>
    <div class="contact-btn1">
      <input type="button" class="custom-btn btn-1" @click="contact()" value="대면 방문예약">
    </div>

    <div class="untact-btn2">
      <input type="button" class="custom-btn btn-2" @click="untact()" value="비대면 견적예약">
        
    </div>
    <br />
    <div class="contact-explain1">
      <table>
        <thead style="font-weight: bold;">대면 방문예약</thead>
        <tbody>
        <tr>
          <td>• 이사업체 전문가 직접방문</td>
        </tr>
        <tr>
          <td>• 정확한 견적, 신속한 계약</td>
        </tr>
      </tbody>
      </table>
    </div>

    <div class="untact-explain2">
      <table>
        <thead style="font-weight: bold;">비대면 견적예약</thead>
        <tbody>
        <tr>
          <td>• 이사업체 전문가에 비대면 견적 의뢰</td>
        </tr>
        <tr>
          <td>• 유동적인 견적 가능, 프라이버시 보호</td>
        </tr>
      </tbody>
      </table>
    </div>

    <div>
      <button type="button" class="custom-btn btn-3" @click="sign_in()">선택완료</button>
    </div>
  </div>


</form>
</div>
</template>

<script>
import moveNav from "../../components/move/moveNav.vue";

  export default {

    components:{
      moveNav
    },

    data: () => ({

    //moveDate
    arrayEvents: null,
    date1: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
      .toISOString()
      .substring(0, 10),
    date2: new Date(Date.now() - new Date().getTimezoneOffset() * 60000)
      .toISOString()
      .substring(0, 10),
    value: "",
    context: null,

    //moveType
    moveType : "",

    //moveEstimateType
    moveEstimateType : "",
 
    //moveAddress
    moveAddress : {
          postcode: "",
          address: "",
          detailAddress:"",
          extraAddress: "",
          
          postcode2: "",
          address2: "",
          detailAddress2:"",
          extraAddress2: "",
        },

    //moveDate
    moveDate:{
      
      date : "",
      time : "",

    } ,   

    //moveInfo

    moveInfo : {    

        houseType : "",     
        roomNum : "",
        spaceOfHome : "",
        floor : "",
        toilet : "",
        veranda : "",
        extraStairs : "",
        elevator :"",
        parkable : "",
        veranda:"",

      //   //moveVisitDate
      //   visitDate : "",
      // visitTime : "",

        
      },

    

  }),

  mounted() {
    //moveDate
    this.arrayEvents = [...Array(6)].map(() => {
      const day = Math.floor(Math.random() * 30);
      const d = new Date();
      d.setDate(day);
      return d.toISOString().substring(0, 10);
    });

    
  },

    methods: {
      // moveSelect
      small() {
        let item = document.querySelector(".explain1");
        item.style.display = "inline-block";
  
        let item2 = document.querySelector(".explain2");
        item2.style.display = "none";

        this.moveType = "소형이사";
      },
  
      big() {
        let item = document.querySelector(".explain1");
        item.style.display = "none";
  
        let item2 = document.querySelector(".explain2");
        item2.style.display = "inline-block";

        this.moveType = "가정이사";
      },


      moveSelectNext(){
        console.log(this.moveType);

        if(this.moveType == ""){
          alert("이사유형을 선택해주세요."); 
        }
        else{

        let moveSelectNext = document.querySelector(".moveSelect-wrap");
        moveSelectNext.style.display = "none";

        let moveDateNext = document.querySelector(".moveDate-wrap");
        moveDateNext.style.display = "inline-block";
        window.scrollTo(0,0);
        };
      },

      //moveDate
      onContext(ctx) {
      this.context = ctx;
    },
    functionEvents(date) {
      const [, , day] = date.split("-");
      if ([12, 17, 28].includes(parseInt(day, 10))) return true;
      if ([1, 19, 22].includes(parseInt(day, 10))) return ["red", "#00f"];
      return false;
    },
    moveDateNext(){

      if(this.moveDate.date == ""){
          alert("이사희망 날짜를 선택해주세요.");
        }else if(this.moveDate.time == ""){
          alert("이사희망 시간을 선택해주세요.");
        }else{

        let moveDateNext = document.querySelector(".moveDate-wrap");
        moveDateNext.style.display = "none";

        let moveInfoNext = document.querySelector(".moveInfo-wrap");
        moveInfoNext.style.display = "inline-block";
        window.scrollTo(0,0);
        }
      },

    //moveInfo
    moveInfoNext(){
      console.log(document.getElementById('post1'));
      console.log(document.getElementById('post1').value);

        if( document.getElementById('post1').value=="" || document.getElementById('post2').value==""){
          alert("우편번호 찾기를 해주세요."); 
        } else if(document.getElementById('address1').value=="" || document.getElementById('address2').value==""){
          alert("주소를 입력해주세요.");
        } else if(document.getElementById('detailAddress1').value=="" || document.getElementById('detailAddress2').value==""){
          alert("상세주소를 입력해주세요.");
        } 
        else if(this.moveInfo.houseType==""){
          alert("집형태를 선택해주세요.");
        } else if(this.moveInfo.roomNum ==""){
          alert("방구조를 선택해주세요.");
        } else if(this.moveInfo.spaceOfHome ==""){
          alert("집평수를 선택해주세요.");
        } 
        else if(this.moveInfo.floor ==""){
          alert("층수를 입력해주세요.");
        } else if(this.moveInfo.toilet ==""){
          alert("화장실 개수를 입력해주세요.");
        } else if(this.moveInfo.veranda ==""){
          alert("베란다 개수를 입력해주세요.");
        }  
        else if(this.moveInfo.extraStairs ==""){
          alert("별도계단 유무를 선택해주세요.");
        } else if(this.moveInfo.elevator ==""){
          alert("엘레베이터 유무를 선택해주세요.");
        } else if(this.moveInfo.parkable ==""){
          alert("주차가능 여부를 선택해주세요.");
        } 
          
          else{
          let moveInfoNext = document.querySelector(".moveInfo-wrap");
          moveInfoNext.style.display = "none";

          let moveTypeNext = document.querySelector(".moveType-wrap");
          moveTypeNext.style.display = "inline-block";
          window.scrollTo(0,0);
          }
      },

    execDaumPostcode(number) {  
      console.log(number);
      var postcode = document.querySelector("#post"+number);
      var addr = document.querySelector("#address"+number);
      var detail = document.querySelector("#detailAddress"+number);  
      var extra = document.querySelector("#extraAddress"+number);     

      
      //찾기
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

    //moveVisitDate
    onContext(ctx) {
      this.VisitContext = ctx;
    },
    functionEvents(date) {
      const [, , day] = date.split("-");
      if ([12, 17, 28].includes(parseInt(day, 10))) return true;
      if ([1, 19, 22].includes(parseInt(day, 10))) return ["red", "#00f"];
      return false;
    },

    sign_in : function(){    
      console.log(this.moveEstimateType);
      
      // 출발지
      var postcode = document.querySelector("#post1");
      var addr = document.querySelector("#address1");
      var detail = document.querySelector("#detailAddress1");  
      var extra = document.querySelector("#extraAddress1"); 
      
      this.moveAddress.postcode = postcode.value;
      this.moveAddress.address = addr.value;
      this.moveAddress.detailAddress = detail.value;
      this.moveAddress.extraAddress = extra.value;
      
      //도착지
      var postcode = document.querySelector("#post2");
      var addr = document.querySelector("#address2");
      var detail = document.querySelector("#detailAddress2");  
      var extra = document.querySelector("#extraAddress2"); 
      
      this.moveAddress.postcode2 = postcode.value;
      this.moveAddress.address2 = addr.value;
      this.moveAddress.detailAddress2 = detail.value;
      this.moveAddress.extraAddress2 = extra.value;

      if(this.moveEstimateType == ""){
          alert("견적방법을 선택해주세요."); 
        }else{

      if(this.moveEstimateType == "대면 방문예약"){
        
        this.$router.push({
        name: "moveContact",
        params:{moveInfo:this.moveInfo, moveEstimateType:this.moveEstimateType, moveType:this.moveType, moveDate: this.moveDate, moveAddress: this.moveAddress}

      })
      

      } else if(this.moveEstimateType == "비대면 견적예약"){
        this.$router.push({
        name: "moveUntact",
        params:{moveInfo:this.moveInfo, moveEstimateType:this.moveEstimateType, moveType:this.moveType, moveDate: this.moveDate, moveAddress: this.moveAddress}

      })
      
      }
      
    };

        //moveFinalCheck 페이지로 
      // this.$router.push({
      //   name: "move",
      //   params:{data:this.moveInfo}
      // })
    },

    // 견적방법선택
    contact() {
      let item = document.querySelector(".contact-explain1");
      item.style.display = "inline-block";

      let item2 = document.querySelector(".untact-explain2");
      item2.style.display = "none";

      this.moveEstimateType = "대면 방문예약";

      console.log(this.moveEstimateType);
    },

    untact() {
      let item = document.querySelector(".contact-explain1");
      item.style.display = "none";

      let item2 = document.querySelector(".untact-explain2");
      item2.style.display = "inline-block";

      this.moveEstimateType = "비대면 견적예약";
      console.log(this.moveEstimateType);
    },

    

    },
  };
  
  </script>
  
  <style scoped>
form{
  text-align: center;
}  
/* 기본 display상태 */
.moveSelect-wrap {
  display: inline-block;
}
.moveDate-wrap {
  display: none;
}
.moveInfo-wrap {
  display: none;
}
.moveType-wrap {
  display: none;
}
  
/* moveSelect */

  /* 버튼 */

.moveSelect-wrap {
  width: 90%;
  margin: 100px auto;
  text-align: center;
}

button {
  margin: 70px;
}

.custom-btn {
  width: 200px;
  height: 100px;
  color: #fff;
  border-radius: 5px;
  padding: 10px 25px;
  font-family: "Lato", sans-serif;
  font-weight: bold;
  font-size: 20px;
  background: transparent;
  cursor: pointer;
  transition: all 0.3s ease;
  position: relative;
  display: inline-block;
  box-shadow: inset 2px 2px 2px 0px rgba(255, 255, 255, 0.5),
    7px 7px 20px 0px rgba(0, 0, 0, 0.1), 4px 4px 5px 0px rgba(0, 0, 0, 0.1);
  outline: none;
}

.btn-1 {
  background: rgb(144, 198, 235);
  background: linear-gradient(
    0deg,
    rgba(144, 198, 235, 1) 0%,
    rgb(127, 196, 243) 100%
  );
  border: none;
}

.btn-1:hover {
  /* background: rgb(63, 153, 243);
      background: linear-gradient(0deg, rgba(63, 153, 243, 1) 0%, rgba(2, 126, 251, 1) 100%); */
  box-shadow: 4px 4px 6px 0 rgba(255, 255, 255, 0.5),
    -4px -4px 6px 0 rgba(116, 125, 136, 0.5),
    inset -4px -4px 6px 0 rgba(255, 255, 255, 0.2),
    inset 4px 4px 6px 0 rgba(0, 0, 0, 0.4);
}

.btn-2 {
  background: rgb(rgb(150, 229, 184));
  background: linear-gradient(
    0deg,
    rgba(150, 229, 184, 1) 0%,
    rgb(150, 229, 184) 100%
  );
  border: none;
}

.btn-2:before {
  height: 0%;
  width: 2px;
}

.btn-2:hover {
  box-shadow: 4px 4px 6px 0 rgba(255, 255, 255, 0.5),
    -4px -4px 6px 0 rgba(116, 125, 136, 0.5),
    inset -4px -4px 6px 0 rgba(255, 255, 255, 0.2),
    inset 4px 4px 6px 0 rgba(0, 0, 0, 0.4);
}

.btn-3 {
  background: rgb(163, 162, 162);
  background: linear-gradient(
    0deg,
    rgba(163, 162, 162, 1) 0%,
    rgb(163, 162, 162) 100%
  );
  border: none;
  height: 50px;
  width: 150px;
}

.btn-3:hover {
  /* background: rgb(136, 221, 164);
      background: linear-gradient(0deg, rgba(136, 221, 164, 1) 0%, rgb(131, 221, 161) 100%); */
  box-shadow: 4px 4px 6px 0 rgba(255, 255, 255, 0.5),
    -4px -4px 6px 0 rgba(116, 125, 136, 0.5),
    inset -4px -4px 6px 0 rgba(255, 255, 255, 0.2),
    inset 4px 4px 6px 0 rgba(0, 0, 0, 0.4);
}

/* 설명 */

.btn1 {
  display: inline-block;
}

.btn2 {
  display: inline-block;
}

.explain1,
.explain2 {
  height: 200px;
  display: none;
}


/* moveDate */

.moveDate-wrap {
  width: 500px;
  text-align: center;
  margin: 0 auto;
  margin-top: 100px;
  margin-bottom: 100px;
}
h3 {
  margin-top: 50px;
  padding-bottom: 50px;
}
.col {
  display: flex;
  flex-direction: column;
  justify-content: center;
}
.selectTime {
  height: 200px;
}
.v-btn {
  color: white;
  background-color: rgb(179, 227, 195, 0.8) !important;
  transition: all 0.3s ease !important;
  font-size: 20px;
}
.v-btn:hover {
  background-color: rgb(211, 211, 211, 0.8) !important;
  transition: all 0.3s ease !important;
}

/* moveInfo */
h2 {
  padding-top: 100px;
}

.moveInfo-wrap {
  text-align: center;
}
input {
  margin: 5px;
}

.type-2 {
  background-color: #fafafa;
  border: 0;
  box-shadow: 0 0 4px rgba(0, 0, 0, 0.3);
  transition: 0.3s box-shadow;
  width: 100px;
  border-radius: 5px;
}
.type-2:hover {
  box-shadow: 0 0 4px rgba(0, 0, 0, 0.5);
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

  /* 라디오 */
.detail-info-radio {
  display: inline-block;
  width: 250px;
}

.test_obj input[type="radio"] {
  display: none;
}

.test_obj input[type="radio"] + span {
  display: inline-block;
  padding: 10px 10px;
  border: 1px solid #dfdfdf;
  background-color: #ffffff;
  text-align: center;
  cursor: pointer;
  border-radius: 20px;
  width: 80px;

  box-shadow: 0 0 4px rgba(0, 0, 0, 0.2);
  transition: 0.3s box-shadow;
}

.test_obj input[type="radio"]:checked + span {
  background-color: #b3e3c3;
  color: #ffffff;
}

/* 드롭 */
@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap");

select {
  -moz-appearance: none;
  -webkit-appearance: none;
  appearance: none;

  font-family: "Noto Sansf KR", sans-serif;
  font-size: 1rem;
  font-weight: 400;
  line-height: 1.5;

  color: #444;
  background-color: #fff;

  padding: 0.6em 1.4em 0.5em 0.8em;
  margin: 0;

  border: 1px solid #aaa;
  border-radius: 0.5em;
  /* box-shadow: 0 1px 0 1px rgba(0,0,0,.2); */
  box-shadow: 0 0 4px rgba(0, 0, 0, 0.2);
  transition: 0.3s box-shadow;
}

select:hover {
  border-color: #888;
}

select:focus {
  border-color: #aaa;
  box-shadow: 0 0 1px 3px rgba(59, 153, 252, 0.7);
  box-shadow: 0 0 0 3px -moz-mac-focusring;
  color: #222;
  outline: none;
}

select:disabled {
  opacity: 0.5;
}

label {
  font-family: "Noto Sans KR", sans-serif;
  font-size: 1rem;
  font-weight: 600;
  line-height: 1.3;

  color: #444;

  margin-right: 0.5em;
}

/* moveTypeSelect */
.moveType-wrap {
  width: 90%;
  margin: 100px auto;
  text-align: center;
}

.contact-btn1 {
  display: inline-block;
}

.untact-btn2 {
  display: inline-block;
}
.contact-explain1,
.untact-explain2 {
  height: 100px;
  display: none;
}



  </style>