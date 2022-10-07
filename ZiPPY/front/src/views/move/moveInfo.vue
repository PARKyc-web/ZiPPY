<template>
  <div class="wrap-info">
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
    <br />
    <hr />
    <br />
    <br />
    <br />
    <br />

    <div class="move-drop">
      <h3>집형태</h3>
      <select id="houseType" v-model="moveInfo.houseType">
        <option value="" selected>-- 선택하세요 --</option>
        <option value="1">빌라/주택</option>
        <option value="2">오피스텔</option>
        <option value="3">아파트</option>
      </select>
    </div>

    <div class="move-drop">
      <h3>방구조</h3>
      <select id="roomNum" v-model="moveInfo.roomNum">
        <option value="" selected>-- 선택하세요 --</option>
        <option value="1">원룸</option>
        <option value="2">투룸</option>
        <option value="3">쓰리룸</option>
        <option value="over3">쓰리룸 이상</option>
      </select>
    </div>

    <div class="move-drop">
      <h3>집평수</h3>
      <select id="spaceOfHome" v-model="moveInfo.spaceOfHome">
        <option value="" selected>-- 선택하세요 --</option>
        <option value="below10">10평 이하</option>
        <option value="11to15">10~15평</option>
        <option value="11to15">15~20평</option>
        <option value="11to15">20~25평</option>
        <option value="11to15">25~30평</option>
        <option value="11to15">30~35평</option>
        <option value="11to15">35~40평</option>
        <option value="over40">40평 이상</option>
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
        <input type="radio" name="stairs" value="yes" v-model="moveInfo.extraStairs"/>
        <span>있음</span>
      </label>

      <label class="test_obj">
        <input type="radio" name="stairs" value="no" v-model="moveInfo.extraStairs"/>
        <span>없음</span>
      </label>
    </div>

    <div class="detail-info-radio">
      <h3>엘레베이터</h3>
      <label class="test_obj">
        <input type="radio" name="elev" value="yes" v-model="moveInfo.elevator"/>
        <span>있음</span>
      </label>

      <label class="test_obj">
        <input type="radio" name="elev" value="no" v-model="moveInfo.elevator" />
        <span>없음</span>
      </label>
    </div>

    <div class="detail-info-radio">
      <h3>주차가능</h3>
      <label class="test_obj">
        <input type="radio" name="parking" value="available" v-model="moveInfo.parkable"/>
        <span>있음</span>
      </label>

      <label class="test_obj">
        <input type="radio" name="parking" value="notAvailable" v-model="moveInfo.parkable" />
        <span>없음</span>
      </label>
    </div>
  </form>
    <div class="frame">
      <button id="selectBtn-bday" class="custom-btn btn-3" @click="sign_in()">선택완료</button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
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

        addr : {
          postcode: "",
          address: "",
          detailAddress:"",
          extraAddress: "",
          
          postcode2: "",
          address2: "",
          detailAddress2:"",
          extraAddress2: "",
        },
      },

     
    };
  },
  methods: {

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

    sign_in : function(){    
      console.log(this.moveInfo);
      
      // 출발지
      var postcode = document.querySelector("#post1");
      var addr = document.querySelector("#address1");
      var detail = document.querySelector("#detailAddress1");  
      var extra = document.querySelector("#extraAddress1"); 
      
      this.moveInfo.addr.postcode = postcode.value;
      this.moveInfo.addr.address = addr.value;
      this.moveInfo.addr.detailAddress = detail.value;
      this.moveInfo.addr.extraAddress = extra.value;
      
      //도착지
      var postcode = document.querySelector("#post2");
      var addr = document.querySelector("#address2");
      var detail = document.querySelector("#detailAddress2");  
      var extra = document.querySelector("#extraAddress2"); 
      
      this.moveInfo.addr.postcode2 = postcode.value;
      this.moveInfo.addr.address2 = addr.value;
      this.moveInfo.addr.detailAddress2 = detail.value;
      this.moveInfo.addr.extraAddress2 = extra.value;


      this.$router.push({
        name: "move",
        params:{data:this.moveInfo}
      })
    }

  },
};
</script>

<style scoped>
h2 {
  padding-top: 100px;
}

.wrap-info {
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

/* 버튼 */

.frame {
  width: 90%;
  margin: 100px auto;
  text-align: center;
}

#selectBtn-bday {
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
  background: linear-gradient(
    0deg,
    rgba(163, 162, 162, 1) 0%,
    rgb(163, 162, 162) 100%
  );
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
  background: linear-gradient(
    0deg,
    rgba(163, 162, 162, 1) 0%,
    rgb(163, 162, 162) 100%
  );
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
</style>
