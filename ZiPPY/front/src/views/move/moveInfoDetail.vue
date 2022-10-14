<template>
  <div class="wrap">
    <h3>이삿짐의 상세 정보를 입력해주세요.</h3>
    <br /><br />
    <h4>가구</h4>
    <br />
    <div class="move-detail">
      <div class="move-furniture1">
        <input type="checkbox" name="bed" v-model="bedAvail"/><label>침대</label>
        <button v-bind:disabled="bedAvail==false" 
        class="custom-btn btn-4" type="button" @click="addBed()" >+</button><br><br />
        <i class="fa-solid fa-bed fa-5x"></i><br />
    
        <div class="move-detail-drop" v-for="i in moveDetail[0].bedCount">
          <!-- v-for="(row, index) in rows" -->
          <div >
          <select v-bind:disabled="bedAvail==false" :id="i" v-model="moveDetail[0].bed[i]" >
            <option value="" selected>-- 사이즈 --</option>
            <option value="싱글">싱글</option>
            <option value="슈퍼싱글">슈퍼싱글</option>
            <option value="퀸">퀸</option>
            <option value="킹">킹</option>
            <option value="킹이상">킹 이상</option>
          </select>
          <button v-bind:disabled="bedAvail==false" 
          class="custom-btn btn-4" type="button"  @click="removeBed(i)">-</button>
        </div>
        </div>
      </div>

      <div class="move-furniture1">
        <input type="checkbox" name="sofa" v-model="sofaAvail"/><label>소파</label>
        <button v-bind:disabled="sofaAvail==false" 
        class="custom-btn btn-4" type="button" @click="addSofa()">+</button><br><br />
        <i class="fa-solid fa-couch fa-5x"></i><br />
        <div class="move-detail-drop" v-for="i in moveDetail[0].sofaCount">
          <select v-bind:disabled="sofaAvail==false" :id="i" v-model="moveDetail[0].sofa[i]">
            <option value="" selected>-- 사이즈 --</option>
            <option value="싱글">싱글</option>
            <option value="슈퍼싱글">슈퍼싱글</option>
            <option value="퀸">퀸</option>
            <option value="킹">킹</option>
            <option value="킹이상">킹 이상</option>
          </select>
            <button v-bind:disabled="sofaAvail==false" 
            class="custom-btn btn-4" type="button"  @click="removeSofa(i)">-</button>
        </div>
      </div>

      <div class="move-furniture1">
        <input type="checkbox" name="closet" v-model="closetAvail" /><label>옷장-단품</label>
        <button v-bind:disabled="closetAvail==false" 
        class="custom-btn btn-4" type="button" @click="addCloset()">+</button><br /><br />
        <i class="fa-solid fa-toilet-portable fa-5x"></i><br />
        <div class="move-detail-drop" v-for="i in moveDetail[0].closetCount">
          <select v-bind:disabled="closetAvail==false" :id="i" v-model="moveDetail[0].closet[i]">
            <option value="" selected>-- 사이즈 --</option>
            <option value="싱글">싱글</option>
            <option value="슈퍼싱글">슈퍼싱글</option>
            <option value="퀸">퀸</option>
            <option value="킹">킹</option>
            <option value="킹이상">킹 이상</option>
          </select>
          <button v-bind:disabled="closetAvail==false" 
           class="custom-btn btn-4" type="button"  @click="removeCloset(i)">-</button>
        </div>
      </div>

      <div class="move-furniture1">
        <input type="checkbox" name="closets" v-model="closetsAvail"/><label>옷장-연결장</label>
        <button v-bind:disabled="closetsAvail==false" 
        class="custom-btn btn-4" type="button" @click="addClosets()">+</button><br /><br />
        <i class="fa-solid fa-toilets-portable fa-5x"></i><br />
        <div class="move-detail-drop" v-for="i in moveDetail[0].closetsCount">
          <select v-bind:disabled="closetsAvail==false" :id="i" v-model="moveDetail[0].closets[i]">
            <option value="" selected>-- 사이즈 --</option>
            <option value="싱글">싱글</option>
            <option value="슈퍼싱글">슈퍼싱글</option>
            <option value="퀸">퀸</option>
            <option value="킹">킹</option>
            <option value="킹이상">킹 이상</option>
          </select>
          <button v-bind:disabled="closetsAvail==false" 
          class="custom-btn btn-4" type="button"  @click="removeClosets(i)">-</button>
        </div>
      </div>
    </div>


    <br />
    <hr />
    <br />
    <h4>가전</h4>
    <br />
    <div class="move-detail">

      <div class="move-furniture1">
        <input type="checkbox" name="tv" v-model="tvAvail"/><label>TV</label>
        <button v-bind:disabled="tvAvail==false" 
        class="custom-btn btn-4" type="button" @click="addTv()" >+</button><br /><br />
    
        <i class="fa-solid fa-tv fa-5x"></i><br />
        <div class="move-detail-drop" v-for="i in moveDetail[0].tvCount">
          <!-- v-for="(row, index) in rows" -->
          <select v-bind:disabled="tvAvail==false" :id="i" v-model="moveDetail[0].tv[i]" >
            <option value="" selected>-- 사이즈 --</option>
            <option value="싱글">싱글</option>
            <option value="슈퍼싱글">슈퍼싱글</option>
            <option value="퀸">퀸</option>
            <option value="킹">킹</option>
            <option value="킹이상">킹 이상</option>
          </select>
      
          <button v-bind:disabled="tvAvail==false" 
          class="custom-btn btn-4" type="button"  @click="removeTv(i)">-</button>
        </div>
      </div>

      <div class="move-furniture1">
        <input type="checkbox" name="pc" v-model="pcAvail"/><label>데스크탑</label>
        <button v-bind:disabled="pcAvail==false"  
        class="custom-btn btn-4" type="button" @click="addPc()">+</button><br /><br />
        <i class="fa-solid fa-desktop fa-5x"></i><br /> 
        <div class="move-detail-drop" v-for="i in moveDetail[0].pcCount">
          <select v-bind:disabled="pcAvail==false" :id="i" v-model="moveDetail[0].pc[i]">
            <option value="" selected>-- 사이즈 --</option>
            <option value="싱글">싱글</option>
            <option value="슈퍼싱글">슈퍼싱글</option>
            <option value="퀸">퀸</option>
            <option value="킹">킹</option>
            <option value="킹이상">킹 이상</option>
          </select>
          <button v-bind:disabled="pcAvail==false"   
          class="custom-btn btn-4" type="button"  @click="removePc(i)">-</button>
        </div>
      </div>

      <div class="move-furniture1">
        <input type="checkbox" name="fridge" v-model="fridgeAvail"/><label>냉장고</label>
        <button v-bind:disabled="fridgeAvail==false"
        class="custom-btn btn-4" type="button" @click="addFridge()">+</button><br /><br />
        <i class="material-icons md-54">kitchen</i><br />
        <div class="move-detail-drop" v-for="i in moveDetail[0].fridgeCount">
          <select v-bind:disabled="fridgeAvail==false" :id="i" v-model="moveDetail[0].fridge[i]">
            <option value="" selected>-- 사이즈 --</option>
            <option value="싱글">싱글</option>
            <option value="슈퍼싱글">슈퍼싱글</option>
            <option value="퀸">퀸</option>
            <option value="킹">킹</option>
            <option value="킹이상">킹 이상</option>
          </select>
          <button v-bind:disabled="fridgeAvail==false" 
          class="custom-btn btn-4" type="button"  @click="removeFridge(i)">-</button>
        </div>
      </div>

      <div class="move-furniture1">
        <input type="checkbox" name="trolley" v-model="trolleyAvail" /><label>유모차</label>
        <button v-bind:disabled="trolleyAvail==false"
         class="custom-btn btn-4" type="button" @click="addTrolley()">+</button><br /><br />
        <i class="fa-solid fa-baby-carriage fa-5x"></i><br />
        <div class="move-detail-drop" v-for="i in moveDetail[0].trolleyCount">
          <select v-bind:disabled="trolleyAvail==false" :id="i" v-model="moveDetail[0].trolley[i]">
            <option value="" selected>-- 사이즈 --</option>
            <option value="싱글">싱글</option>
            <option value="슈퍼싱글">슈퍼싱글</option>
            <option value="퀸">퀸</option>
            <option value="킹">킹</option>
            <option value="킹이상">킹 이상</option>
          </select>
          <button v-bind:disabled="trolleyAvail==false" 
          class="custom-btn btn-4" type="button"  @click="removeTrolley(i)">-</button>
        </div>
      </div>
    </div>


    <div>
      <br />
      <hr />
      <br />
      <h4>기타</h4>
      <br />
      <div class="move-detail-2">
        <div class="move-furniture1">
          <h5>
            ※위의 이삿짐 이외의 항목 기재가 필요한 이삿짐 정보를 입력해주세요.
          </h5>
          <br />
          <div class="move-detail-drop">
            <button class="custom-btn btn-4" type="button" @click="addEtc()">+</button><br><br>
            <div v-for="i in moveDetail[0].etcCount">
              <label >사용자 기타</label>
              <button class="custom-btn btn-4" type="button" :id="i-1" @click="removeEtc(i)">-</button>
            <input v-model="moveDetail[0].etcName[i]" class="type-2" type="text" placeholder="기타이삿짐 이름" /><br />
            <input v-model="moveDetail[0].etcSize[i]" class="type-2" type="text" placeholder="이삿짐 사이즈(cm * cm)" /><br />
          </div>
          </div>
        </div>
      </div>

      <br />
      <hr />
      <br />
      <h4>박스 수량</h4>
      <br />
      <div class="move-detail-2">
        <div class="move-furniture3">
          <br />
          <h5>
            ※이외의 이삿짐 이동에 사용할 박스의 개수를 선택해주세요. <br />
            (수량을 넉넉하게 선택하는 것이 좋습니다.)
          </h5>
          <br />
          <div class="move-detail-drop">
            <select v-model="moveDetail[0].box">
              <option value="" selected>-- 박스개수 --</option>
              <option value="5개이하">5개이하</option>
              <option value="5-10개">5~10개</option>
              <option value="11-15개">11~15개</option>
              <option value="16-20개">16~20개</option>
              <option value="21-25개">21~25개</option>
              <option value="26-30개">26~30개</option>
              <option value="30개이상">30개 이상</option>
            </select>
          </div>
          <!-- <div><img src="../../assets/박스예시.jpg" style="width: 200px;"></div> -->
          <div><img :src="img" alt="boxExample" /></div>
        </div>
      </div>

      <br />
      <hr />
      <br />
      <h4>짐사진 첨부(선택)</h4>
      <br />
      <div class="move-detail-2">
        <div class="move-furniture1">
          <br />
          <h5>
            ※측정하기 힘든 짐은 사진을 첨부하면 더 정확한 견적을 받을 수
            있습니다.
          </h5>
          <br />
          <div class="move-detail-drop">
            <v-file-input outlined v-model="moveDetail.filesPhoto" placeholder="Upload your documents" label="사진첨부" 
              prepend-icon="mdi-paperclip">
              <template v-slot:selection="{ text }">
                <v-chip small label color="success">
                  {{ text }}
                </v-chip>
              </template>
            </v-file-input>
          </div>
        </div>
      </div>
    </div>

    <div class="frame">
      <button id="selectBtn-bday" class="custom-btn btn-3" @click="un_final_signIn()">선택완료</button>
    </div>
  </div>
</template>

<script>
  export default {
    props: ['moveInfo','moveEstimateType','moveType', 'moveDate', 'moveAddress'],
    data: () => ({
      
      bedAvail: false,
      sofaAvail: false,
      closetAvail: false,
      closetsAvail: false,
      tvAvail: false,
      pcAvail: false,
      fridgeAvail: false,
      trolleyAvail: false,

      moveDetail: [{

        bedCount:1,
        sofaCount:1,
        closetCount:1,
        closetsCount:1,
        tvCount:1,
        pcCount:1,
        fridgeCount:1,  
        trolleyCount:1,
        etcCount:1,

        bed: [""],
        sofa: [""],
        closet: [""],
        closets: [""],

        tv: [""],
        pc: [""],
        fridge: [""],
        trolley: [""],

        etcName: [""],
        etcSize: [""],

        box: "",
        filesPhoto: "",

      }],

      // 사진
      img: require("../../assets/box.jpg"),
    }),

    computed:{
      isDisabled: function(){
        return !this.terms;
      }
    },

    methods: {

      //짐 추가 및 삭제
      addBed: function () {
        this.moveDetail[0].bed.push();
        this.moveDetail[0].bedCount++;
      },
      removeBed: function (i) {
        if (i > 1) {
          this.moveDetail[0].bed[i]="";
          this.moveDetail[0].bedCount--;
        } else if (i < 1) {
          
          this.moveDetail[0].bedCount = 1;
        }
      },

      addSofa: function(){
        this.moveDetail[0].sofa.push();
        this.moveDetail[0].sofaCount++;        
      },
      removeSofa: function(i){
       if(i>1){
        this.moveDetail[0].sofa[i]="";
        this.moveDetail[0].sofaCount--;
       }else if(i<1){
        this.moveDetail[0].sofaCount = 1;
       }
      },

      addCloset: function(){
        this.moveDetail[0].closet.push();
        this.moveDetail[0].closetCount++;        
      },
      removeCloset: function(i){
       if(i>1){
        this.moveDetail[0].closet[i]="";
        this.moveDetail[0].closetCount--;
       }else if(i<1){
        this.moveDetail[0].closetCount = 1;
       }
      },

      addClosets: function(){
        this.moveDetail[0].closets.push();
        this.moveDetail[0].closetsCount++;        
      },
      removeClosets: function(i){
       if(i>1){
        this.moveDetail[0].closets[i]="";
        this.moveDetail[0].closetsCount--;
       }else if(i<1){
        this.moveDetail[0].closetsCount = 1;
       }
      },

      addTv: function(){
        this.moveDetail[0].tv.push();
        this.moveDetail[0].tvCount++;        
      },
      removeTv: function(i){
       if(i>1){
        this.moveDetail[0].tv[i]="";
        this.moveDetail[0].tvCount--;
       }else if(i<1){
        this.moveDetail[0].tvCount = 1;
       }
      },

      addPc: function(){
        this.moveDetail[0].pc.push();
        this.moveDetail[0].pcCount++;        
      },
      removePc: function(i){
       if(i>1){
        this.moveDetail[0].pc[i]="";
        this.moveDetail[0].pcCount--;
       }else if(i<1){
        this.moveDetail[0].pcCount = 1;
       }
      },

      
      addFridge: function(){
        this.moveDetail[0].fridge.push();
        this.moveDetail[0].fridgeCount++;        
      },
      removeFridge: function(i){
       if(i>1){
        this.moveDetail[0].fridge[i]="";
        this.moveDetail[0].fridgeCount--;
       }else if(i<1){
        this.moveDetail[0].fridgeCount = 1;
       }
      },

      addTrolley: function(){
        
        this.moveDetail[0].trolley.push();
        this.moveDetail[0].trolleyCount++;        
      },
      removeTrolley: function(i){
       if(i>1){
        this.moveDetail[0].trolley[i]="";
        this.moveDetail[0].trolleyCount--;
       }else if(i<1){
        this.moveDetail[0].trolleyCount = 1;
       }
      },

      addEtc: function(){
        this.moveDetail[0].etcName.push();
        this.moveDetail[0].etcSize.push();
        this.moveDetail[0].etcCount++;        
      },
      removeEtc: function(i){
       if(i>1){
        this.moveDetail[0].etcName[i]="";
        this.moveDetail[0].etcSize[i]="";
        this.moveDetail[0].etcCount--;
       }else if(i<1){
        this.moveDetail[0].etcCount = 1;
       }
      },





      //라우터
      un_final_signIn: function () {
        console.log(this.moveInfo);

        console.log(this.moveDetail[0].box);

        if(this.moveDetail[0].box == ""){
          alert("필요한 박스 수량을 선택해주세요."); 
        }

        else{

        this.$router.push({

          name: "moveUntactImg",
          params: {                                    
                  moveDetail:this.moveDetail,
                  moveEstimateType:this.moveEstimateType, 
                  moveInfo : this.moveInfo,
                  moveType:this.moveType,
                  moveDate: this.moveDate, 
                  moveAddress: this.moveAddress
          }
        })
        }
      }

    }
  };
</script>

<style scoped>
  .head {
    display: inline-block;
  }

  /* google font */

  .material-icons {
    font-family: "Material Icons";
    font-weight: normal;
    font-style: normal;
    font-size: 40px;
    /* Preferred icon size */
    display: inline-block;
    line-height: 1;
    text-transform: none;
    letter-spacing: normal;
    word-wrap: normal;
    white-space: nowrap;
    direction: ltr;

    /* Support for all WebKit browsers. */
    -webkit-font-smoothing: antialiased;

    /* Support for Safari and Chrome. */
    text-rendering: optimizeLegibility;

    /* Support for Firefox. */
    -moz-osx-font-smoothing: grayscale;

    /* Support for IE. */
    font-feature-settings: "liga";
  }

  .material-icons.md-18 {
    font-size: 18px;
  }

  .material-icons.md-24 {
    font-size: 24px;
  }

  .material-icons.md-36 {
    font-size: 36px;
  }

  .material-icons.md-48 {
    font-size: 48px;
  }

  .material-icons.md-54 {
    font-size: 80px;
  }

  /* font awesome */
  i {
    width: 100px;
    height: 100px;
  }

  .wrap {
    text-align: center;
  }

  h3 {
    padding-top: 100px;
  }
  

  /* input */

  .type-2 {
    background-color: #fafafa;
    border: 0;
    box-shadow: 0 0 4px rgba(0, 0, 0, 0.3);
    transition: 0.3s box-shadow;
    width: 300px;
    border-radius: 5px;
  }

  .type-2:hover {
    box-shadow: 0 0 4px rgba(0, 0, 0, 0.5);
  }

  input {
    margin: 5px;
  }

  .move-furniture1 {
    display: inline-block;
    margin: 35px;
    width: 250px;
  }

  .move-furniture2 {
    display: inline-block;
    margin: 20px;
  }

  .type-2 {
    background-color: #fafafa;
    border: 0;
    box-shadow: 0 0 4px rgba(0, 0, 0, 0.3);
    transition: 0.3s box-shadow;
    width: 200px;
    height: 40px;
    border-radius: 5px;
  }

  .type-2:hover {
    box-shadow: 0 0 4px rgba(0, 0, 0, 0.5);
  }

  .move-detail-2 {
    text-align: center;
  }


  /* zip */
  .depart-address {
    margin-top: 100px;
  }

  .move-detail-drop {
    display: inline-block;
    width: 250px;
    height: 100px;
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
    background: linear-gradient(0deg,
        rgba(163, 162, 162, 1) 0%,
        rgb(163, 162, 162) 100%);
    border: none;
    width: 40px;
    height: 45px;
    font-size: 20px;
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

  /* 라디오 */
  .detail-info-radio {
    display: inline-block;
    width: 250px;
  }

  .test_obj input[type="radio"] {
    display: none;
  }

  .test_obj input[type="radio"]+span {
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

  .test_obj input[type="radio"]:checked+span {
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
    width: 200px;
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

  /* 사진 */
  img {
    width: 300px;
  }
</style>