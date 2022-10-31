<template>
  <div class="wrap">
    <h3>이삿짐의 상세 정보를 입력해주세요.</h3>
    
    <div class="move-detail">
      <br />
    <h4>가구</h4>
    <br />
      <div class="move-furniture1">
          <!-- 체크박스 선택하지 않을 시, + - 버튼과 input 비활성화 -->
        <input type="checkbox" name="bed" v-model="bedAvail"/><label>침대</label>
          <!-- + 누르면 사이즈 추가 기입할 수 있는 input 생성 -->
        <button v-bind:disabled="bedAvail==false" 
        class="custom-btn btn-4" type="button" @click="addBed()" >+</button><br><br />
        <i class="material-icons md-54">bed</i><br /><br />
          <!-- 사이즈 선택하는 select box -->
        <div class="move-detail-drop" v-for="i in moveDetail[0].bedCount">
          <div >
          <select v-bind:disabled="bedAvail==false" :id="i" v-model="moveDetail[0].bed[i]" >
            <option value="" selected>-- 사이즈 --</option>
            <option value="싱글">싱글</option>
            <option value="슈퍼싱글">슈퍼싱글</option>
            <option value="퀸">퀸</option>
            <option value="킹">킹</option>
            <option value="킹이상">킹 이상</option>
          </select>
            <!-- - 누르면 생성된 사이즈 input칸 제거 -->
          <button v-bind:disabled="bedAvail==false" 
          class="custom-btn btn-4" type="button"  @click="removeBed(i)">-</button>
        </div>
        </div>
      </div>

      <div class="move-furniture1">
        <input type="checkbox" name="sofa" v-model="sofaAvail"/><label>소파</label>
        <button v-bind:disabled="sofaAvail==false" 
        class="custom-btn btn-4" type="button" @click="addSofa()">+</button><br><br />
        <i class="material-icons md-54">weekend</i><br /><br />
        <div class="move-detail-drop" v-for="i in moveDetail[0].sofaCount">
          <select v-bind:disabled="sofaAvail==false" :id="i" v-model="moveDetail[0].sofa[i]">
            <option value="" selected>-- 사이즈 --</option>
            <option value="1인용">1인용</option>
            <option value="2~3인용">2~3인용</option>
            <option value="4인용">4인용</option>
            <option value="5인용">5인용</option>
            <option value="6인용이상">6인용이상</option>
          </select>
            <button v-bind:disabled="sofaAvail==false" 
            class="custom-btn btn-4" type="button"  @click="removeSofa(i)">-</button>
        </div>
      </div>

      <div class="move-furniture1">
        <input type="checkbox" name="closet" v-model="closetAvail" /><label>옷장</label>
        <button v-bind:disabled="closetAvail==false" 
        class="custom-btn btn-4" type="button" @click="addCloset()">+</button><br /><br />
        <i class="material-icons md-54">door_sliding</i><br /><br />
        <div class="move-detail-drop" v-for="i in moveDetail[0].closetCount">
          <select v-bind:disabled="closetAvail==false" :id="i" v-model="moveDetail[0].closet[i]">
            <option value="" selected>-- 사이즈 --</option>
            <option value="양문형 단품">양문형 단품</option>
            <option value="(문한짝 기준)2~3자">(문한짝 기준)2~3자</option>
            <option value="4자">4자</option>
            <option value="5~6자">5~6자</option>
            <option value="7자 이상">7자 이상</option>
          </select>
          <button v-bind:disabled="closetAvail==false" 
           class="custom-btn btn-4" type="button"  @click="removeCloset(i)">-</button>
        </div>
      </div>

      <div class="move-furniture1">
        <input type="checkbox" name="closets" v-model="closetsAvail"/><label>서랍장</label>
        <button v-bind:disabled="closetsAvail==false" 
        class="custom-btn btn-4" type="button" @click="addClosets()">+</button><br /><br />
        <i class="material-icons md-54">sensor_window</i><br />
        <div class="move-detail-drop" v-for="i in moveDetail[0].closetsCount">
          <select v-bind:disabled="closetsAvail==false" :id="i" v-model="moveDetail[0].closets[i]">
            <option value="" selected>-- 사이즈 --</option>
            <option value="플라스틱 1~2단">플라스틱 1~2단</option>
            <option value="플라스틱 3~4단">플라스틱 3~4단</option>
            <option value="플라스틱 5단 이상">플라스틱 5단 이상</option>
            <option value="원목 1~2단">원목 1~2단</option>
            <option value="원목 3~4단">원목 3~4단</option>
            <option value="원목 5단 이상">원목 5단 이상</option>
          </select>
          <button v-bind:disabled="closetsAvail==false" 
          class="custom-btn btn-4" type="button"  @click="removeClosets(i)">-</button>
        </div>
      </div>

      <!-- 추가 -->
      <div class="move-furniture1">
        <input type="checkbox" name="table" v-model="tableAvail"/><label>테이블/책상</label>
        <button v-bind:disabled="tableAvail==false" 
        class="custom-btn btn-4" type="button" @click="addTable()">+</button><br /><br />
        <i class="material-icons md-54">table_restaurant</i><br />
        <div class="move-detail-drop" v-for="i in moveDetail[0].tableCount">
          <select v-bind:disabled="tableAvail==false" :id="i" v-model="moveDetail[0].table[i]">
            <option value="" selected>-- 사이즈 --</option>
            <option value="플라스틱 1~2인용">플라스틱 1~2인용</option>
            <option value="플라스틱 3~4인용">플라스틱 3~4인용</option>
            <option value="플라스틱 4인용 이상">플라스틱 4인용 이상</option>
            <option value="유리 1~2인용">유리 1~2인용</option>
            <option value="유리 3~4인용">유리 3~4인용</option>
            <option value="유리 4인용 이상">유리 4인용 이상</option>
            <option value="원목 1~2인용">원목 1~2인용</option>
            <option value="원목 3~4인용">원목 3~4인용</option>
            <option value="원목 4인용 이상">원목 4인용 이상</option>
          </select>
          <button v-bind:disabled="tableAvail==false" 
          class="custom-btn btn-4" type="button"  @click="removeTable(i)">-</button>
        </div>
      </div>

      <div class="move-furniture1">
        <input type="checkbox" name="chair" v-model="chairAvail"/><label>의자</label>
        <button v-bind:disabled="chairAvail==false" 
        class="custom-btn btn-4" type="button" @click="addChair()">+</button><br /><br />
        <i class="material-icons md-54">chair_alt</i><br />
        <div class="move-detail-drop" v-for="i in moveDetail[0].chairCount">
          <select v-bind:disabled="chairAvail==false" :id="i" v-model="moveDetail[0].chair[i]">
            <option value="" selected>-- 사이즈 --</option>
            <option value="플라스틱">플라스틱</option>
            <option value="원목">원목</option>
            <option value="바퀴달린 책상의자">바퀴달린 책상의자</option>
            <option value="접이식">접이식</option>
          </select>
          <button v-bind:disabled="chairAvail==false" 
          class="custom-btn btn-4" type="button"  @click="removeChair(i)">-</button>
        </div>
      </div>

    </div>


   
    
    <div class="move-detail">
      <br />
    <h4>가전</h4>
    <br />
      <div class="move-furniture1">
        <input type="checkbox" name="tv" v-model="tvAvail"/><label>TV</label>
        <button v-bind:disabled="tvAvail==false" 
        class="custom-btn btn-4" type="button" @click="addTv()" >+</button><br /><br />
    
        <i class="material-icons md-54">tv_gen</i><br />
        <div class="move-detail-drop" v-for="i in moveDetail[0].tvCount">
          <!-- v-for="(row, index) in rows" -->
          <select v-bind:disabled="tvAvail==false" :id="i" v-model="moveDetail[0].tv[i]" >
            <option value="" selected>-- 사이즈 --</option>
            <option value="35인치 이하">35인치 이하</option>
            <option value="35~45인치">35~45인치</option>
            <option value="45~55인치">45~55인치</option>
            <option value="55~65인치">55~65인치</option>
            <option value="65~75인치">65~75인치</option>
            <option value="75인치 이상">75인치 이상</option>
          </select>
      
          <button v-bind:disabled="tvAvail==false" 
          class="custom-btn btn-4" type="button"  @click="removeTv(i)">-</button>
        </div>
      </div>

      <div class="move-furniture1">
        <input type="checkbox" name="pc" v-model="pcAvail"/><label>데스크탑</label>
        <button v-bind:disabled="pcAvail==false"  
        class="custom-btn btn-4" type="button" @click="addPc()">+</button><br /><br />
        <i class="material-icons md-54">laptop</i><br /> 
        <div class="move-detail-drop" v-for="i in moveDetail[0].pcCount">
          <select v-bind:disabled="pcAvail==false" :id="i" v-model="moveDetail[0].pc[i]">
            <option value="" selected>-- 사이즈 --</option>
            <option value="노트북(랩탑)">노트북(랩탑)</option>
            <option value="컴퓨터(데스크탑) 본체 + 모니터">컴퓨터(데스크탑) 본체 + 모니터</option>
            <option value="모니터만">모니터만</option>
            <option value="본체만">본체만</option>
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
            <option value="미니냉장고">미니냉장고</option>
            <option value="단문형">단문형</option>
            <option value="양문형">양문형</option>
            <option value="김치냉장고 단문형">김치냉장고 단문형</option>
            <option value="김치냉장고 양문형">김치냉장고 양문형</option>
          </select>
          <button v-bind:disabled="fridgeAvail==false" 
          class="custom-btn btn-4" type="button"  @click="removeFridge(i)">-</button>
        </div>
      </div>

      <div class="move-furniture1">
        <input type="checkbox" name="trolley" v-model="trolleyAvail" /><label>전자레인지</label>
        <button v-bind:disabled="trolleyAvail==false"
         class="custom-btn btn-4" type="button" @click="addTrolley()">+</button><br /><br />
         <i class="material-icons md-54">microwave_gen</i><br />
        <div class="move-detail-drop" v-for="i in moveDetail[0].trolleyCount">
          <select v-bind:disabled="trolleyAvail==false" :id="i" v-model="moveDetail[0].trolley[i]">
            <option value="" selected>-- 사이즈 --</option>
            <option value="미니 전자레인지">미니 전자레인지</option>
            <option value="일반형 전자레인지">일반형 전자레인지</option>
            <option value="오븐겸용 전자레인지">오븐겸용 전자레인지</option>
          </select>
          <button v-bind:disabled="trolleyAvail==false" 
          class="custom-btn btn-4" type="button"  @click="removeTrolley(i)">-</button>
        </div>
      </div>

      <!--  추가  -->
      <div class="move-furniture1">
        <input type="checkbox" name="aircon" v-model="airconAvail" /><label>에어컨</label>
        <button v-bind:disabled="airconAvail==false"
         class="custom-btn btn-4" type="button" @click="addAircon()">+</button><br /><br />
         <i class="material-icons md-54">air_purifier_gen</i><br />
        <div class="move-detail-drop" v-for="i in moveDetail[0].airconCount">
          <select v-bind:disabled="airconAvail==false" :id="i" v-model="moveDetail[0].aircon[i]">
            <option value="" selected>-- 사이즈 --</option>
            <option value="인버터형">인버터형</option>
            <option value="스탠드형">스탠드형</option>
            <option value="시스템(천장형)">시스템(천장형)</option>
          </select>
          <button v-bind:disabled="airconAvail==false" 
          class="custom-btn btn-4" type="button"  @click="removeAircon(i)">-</button>
        </div>
      </div>

      <div class="move-furniture1">
        <input type="checkbox" name="laundry" v-model="laundryAvail" /><label>세탁기</label>
        <button v-bind:disabled="laundryAvail==false"
         class="custom-btn btn-4" type="button" @click="addLaundry()">+</button><br /><br />
         <i class="material-icons md-54">gas_meter</i><br />
        <div class="move-detail-drop" v-for="i in moveDetail[0].laundryCount">
          <select v-bind:disabled="laundryAvail==false" :id="i" v-model="moveDetail[0].laundry[i]">
            <option value="" selected>-- 사이즈 --</option>
            <option value="일반통돌이">일반통돌이</option>
            <option value="드럼형 저용량">드럼형 저용량</option>
            <option value="드럼형 고용량">드럼형 고용량</option>
            <option value="건조기">건조기</option>
          </select>
          <button v-bind:disabled="laundryAvail==false" 
          class="custom-btn btn-4" type="button"  @click="removeLaundry(i)">-</button>
        </div>
      </div>

    </div>


    <div>
      
      <div class="move-detail-2">
        <br />
        <h4>기타</h4>
        <br />
        <div class="move-furniture6">
          <h5>
            ※위의 이삿짐 이외의 항목 기재가 필요한 이삿짐 정보를 입력해주세요.
          </h5>
          <br />
          <div class="move-detail-drop">
              <!-- +버튼 누르면 기타 짐 정보의 이름과 사이즈 기입하는 input 추가 -->
            <button class="custom-btn btn-4" type="button" @click="addEtc()">+</button><br><br>
            <div class="etc-in" v-for="i in moveDetail[0].etcCount">
              <label >사용자 기타</label><br />
              <input v-model="moveDetail[0].etcName[i]" class="type-2" type="text" placeholder="기타이삿짐 이름" /><br />
              <input v-model="moveDetail[0].etcSize[i]" class="type-2" type="text" placeholder="이삿짐 사이즈(cm * cm)" /><br />
                <!-- -버튼 누르면 추가된 input 제거 -->
              <button class="custom-btn btn-4" type="button" :id="i" @click="removeEtc(i)">-</button>
          </div>
          </div>
        </div>
      </div>

     
      <div class="move-detail-2">
        <br />
        <h4>박스 수량</h4>
        <br />
        <div class="move-furniture5">
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
          <div><img :src="img" alt="boxExample" /></div>
        </div>
      </div>

      
      <div class="move-detail-3">
        <br />
        <h4>전달사항(선택)</h4>
        <br />
        <div class="move-furniture5">
          <br />
          <h5>
            ※이사업체에 전달하고 싶은 사항이 있으면 작성해주세요.
          </h5>
          <br />
          <div class="move-detail-drop">
            <v-textarea background-color="grey lighten-2"
            color="cyan" v-model="movingMemo" placeholder="전달사항을 입력해주세요.">
            </v-textarea>          
          </div>
        </div>
      </div>
    </div>

    <div class="frame">
      <button id="selectBtn-bday" class="custom-btn btn-3" @click="un_final_signIn()">작성완료</button>
    </div>
  </div>
</template>

<script>
  import swal from 'sweetalert2';
  export default {
    props: ['moveInfo','moveEstimateType','moveType', 'moveDate', 'moveAddress'],
    data: () => ({
      
      sofaAvail: false,
      closetAvail: false,
      closetsAvail: false,
      tableAvail : false,
      chairAvail : false,
      
      tvAvail: false,
      pcAvail: false,
      fridgeAvail: false,
      trolleyAvail: false,
      airconAvail : false,
      laundryAvail : false,
      bedAvail: false,

      moveDetail: [{

        bedCount:1,
        bed: [""],
        sofaCount:1,
        sofa: [""],
        closetCount:1,
        closet: [""],
        closetsCount:1,
        closets: [""],
        tableCount:1,
        table: [""],
        chairCount:1,
        chair: [""],

        tvCount:1,
        tv: [""],
        pcCount:1,
        pc: [""],
        fridgeCount:1,
        fridge: [""],  
        trolleyCount:1,
        trolley: [""],
        airconCount:1,
        aircon: [""],
        laundryCount:1,
        laundry: [""],

        etcCount:1,
        etcName: [""],
        etcSize: [""],

        box: "",
        filesPhoto: "",
      }],
      movingMemo: "",
      
      // 사진
      img: require("@/assets/box.jpg"),
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

      addTable: function(){
        this.moveDetail[0].table.push();
        this.moveDetail[0].tableCount++;        
      },
      removeTable: function(i){
       if(i>1){
        this.moveDetail[0].table[i]="";
        this.moveDetail[0].tableCount--;
       }else if(i<1){
        this.moveDetail[0].tableCount = 1;
       }
      },

      addChair: function(){
        this.moveDetail[0].chair.push();
        this.moveDetail[0].chairCount++;        
      },
      removeChair: function(i){
       if(i>1){
        this.moveDetail[0].chair[i]="";
        this.moveDetail[0].chairCount--;
       }else if(i<1){
        this.moveDetail[0].chairCount = 1;
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

      addAircon: function(){
        
        this.moveDetail[0].aircon.push();
        this.moveDetail[0].airconCount++;        
      },
      removeAircon: function(i){
       if(i>1){
        this.moveDetail[0].aircon[i]="";
        this.moveDetail[0].airconCount--;
       }else if(i<1){
        this.moveDetail[0].airconCount = 1;
       }
      },

      addLaundry: function(){
        
        this.moveDetail[0].laundry.push();
        this.moveDetail[0].laundryCount++;        
      },
      removeLaundry: function(i){
       if(i>1){
        this.moveDetail[0].laundry[i]="";
        this.moveDetail[0].laundryCount--;
       }else if(i<1){
        this.moveDetail[0].laundryCount = 1;
       }
      },
      //기타짐 추가 및 삭제 버튼 기능
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
          swal.fire("필요한 박스수량을 선택해주세요."); 
        }

        else{

        this.$router.push({
          //다음 페이지(사진선택)로 넘어가기 -> params에 필요한 데이터 같이 보낸다 
          name: "moveUntactImg",
          params: {                                    
                  moveDetail:this.moveDetail,
                  moveEstimateType:this.moveEstimateType, 
                  moveInfo : this.moveInfo,
                  moveType:this.moveType,
                  moveDate: this.moveDate, 
                  moveAddress: this.moveAddress,
                  movingMemo : this.movingMemo
          }
        })
      }
    }

    }
  };
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
  
  h4{
    padding: 20px;
    color: #42b983;
    font-weight: bold;
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

.move-detail{
  
  overflow: hidden;
  padding: 50px 0 100px 50px;
  border-bottom: 1px solid #aaa;
}
.move-detail-2{
  height: 800px;
  overflow: hidden;
  padding: 100px 0 100px 50px;
  border-bottom: 1px solid #aaa;
  text-align: center;
}
.move-detail-3{
  
  overflow: hidden;
  padding: 100px 0 0 50px;
  text-align: center;
}

  .move-furniture1 {
    /* display: inline-block; */
    float: left;
    margin: 20px;
    width: 30%;
  
  }

  .move-furniture3 {
    float: left;
    /* display: inline-block; */
    margin: 20px;
    width: 250px;
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



  /* zip */
  .depart-address {
    margin-top: 100px;
  }

  .move-detail-drop {
    /* display: inline-block; */
    float: left;
    width: 100%;
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
    margin: 30px auto;
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