<template>
  <div>
    <move-nav-bar @click="categoryVal=$event.target.innerText"></move-nav-bar>
  
    <div class="result-wrap">
    <form>
      <div class="move-main-title">
        <h3>보낸 견적요청 조회</h3>
      </div>
    
      <div id="used-main-dropbox">
          <v-select @change="dropVal()" v-model="select" :items="items" item-text="name" item-value="value" label="보기정렬"
            color="#212529" persistent-hint single-line dense width="50"></v-select>
      </div>

      <hr />
    <div class="panel">
      <v-expansion-panels>
        <v-expansion-panel
        v-if="list.length != 0" v-for="item in list"
        >
          <v-expansion-panel-header>
            <span>NO.{{item.estimateNo}}</span>  &nbsp;&nbsp;&nbsp; 견적요청일 : <span>{{item.requestDate}}</span>&nbsp;&nbsp;&nbsp; 견적 방법 : <span>{{item.estimateType}}</span>
          </v-expansion-panel-header>
          <v-expansion-panel-content>
            <div >이사 종류 : <span>{{item.moveType}}</span></div>
            <div >이사희망일 : <span>{{item.movingDate}}</span></div>
            <div >이사희망시간 : <span>{{item.movingTime}}</span></div>
            <div >출발지 주소 : <span>{{item.departZipCode}}</span> <br><span>{{item.departAddress}}</span> <span>{{item.departDetail}}</span></div>
            <div >도착지 주소 : <span>{{item.arriveZipCode}}</span> <br><span>{{item.arriveAddress}}</span> <span>{{item.arriveDetail}}</span></div>
            <div> 이사 정보 : <span v-html="par(item.commonOption)"></span></div>
            <div v-if="item.estimateType == '비대면견적'">
            <div >이삿짐 정보 :<span v-html="my(item.movingOption)"></span></div>
            </div>  
     
            <div v-if="item.movingMemo != null">
            <div >이사 요청사항 : <span>{{item.movingMemo}}</span></div>
            </div>
            <div v-if="item.estimateType == '대면견적'">
            <div >견적 방문희망일 : <span>{{item.visitDate}}</span></div>
            <div >견적 방문희망시간 : <span>{{item.visitTime}}</span></div>
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

              <div>
                방 입구에서 찍은 사진
                <div v-for="item in list">
                  <img :src="file" >
                </div>
              </div>
              <div>
                방 중앙에서 찍은 사진
              </div>
              <div>
                내부 구조 사진(짐, 장롱, 창고 등)
              </div>
              <!-- <v-card-text>
                I'm a thing. But, like most politicians, he promised more than he could deliver. You won't have time for sleeping, soldier, not with all the bed making you'll be doing. Then we'll go with that data file! Hey, you add a one and two zeros to that or we walk! You're going to do his laundry? I've got to find a way to escape.
              </v-card-text> -->
            </div>
          </v-expand-transition>
          </div>


          </v-expansion-panel-content>
        </v-expansion-panel>
      </v-expansion-panels>
    </div>
    </form>
  </div>
</div>
</template>

<script>
import axios from 'axios';
import MoveNavBar from '../../components/move/MoveNavBar.vue';

export default {
  components: {
    MoveNavBar
  },

  data : function () {
    return{            
      //펼치기
      file:"http://localhost:8090/zippy/common/img/move/image1.jpg",
      show: false,
      email:"",
      //
      list : [],
      vo : {
        email : "",
        requestDate : "",
        estimateType : ""
      },
      items : [{
        name : '견적요청일순',
        value : '견적요청일순'
      },
      {
        name : '견적방법순',
        value : '견적방법순'
      }
    ],

      data : [],
      select : ''
    }
  },
  mounted(){
    axios({
          url: "http://localhost:8090/zippy/move/moveResult",
          methods: "GET",
          params: {
            movingOption : "",
            dropbox: "",
            email: this.$store.state.loginInfo.email
          }
        }).then(res => {
          console.log(res);
          this.vo.email = this.$store.state.loginInfo.email,
          this.list = res.data;
        }).catch(error => {
          console.log(error);
        })
  },

  created(){
    axios({
          url: "http://localhost:8090/zippy/move/movePhoto",
          methods: "GET",
          params: {
            
          }
        }).then(res => {
          console.log(res);
          this.list = res.data;
        }).catch(error => {
          console.log(error);
        })
  },
  methods : {
  
    dropVal: function () {
        var dropValue = this.select;
        console.log(dropValue);
        console.log(this.email);
        axios({
          url: "http://localhost:8090/zippy/move/moveResult",
          methods: "GET",
          params: {  
            dropbox : dropValue,
            email : this.$store.state.loginInfo.email,
            requestDate : this.vo.requestDate,
            estimateType : this.vo.estimateType,
            
          }
        }).then(res => {
          console.log(res);
          this.vo.email = this.$store.state.loginInfo.email,
          this.list = res.data;
        }).catch(err => {
          console.log(err);
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
        // for(let i in temp.etcName.length){
        //   detail+= `<div> ${temp.etcName[i]} (${temp.etcSize[i].join(",")})</div>` 
        //   console.log(detail);
        // }
        if( detail) {
          detailVal += `<div>기타</div>`+ detail
        }  
        
        }
        return detailVal;
        //document.getElementById('detailDiv').innerHTML= detailVal;

        // let testString = "";
        
        // for(let test of string.split(",")){
        //   console.log(test)
        //   console.log(test.split(":"));
        //   if(test.split(":")[0] == 'bedCount' ){
        //     testString += "침대 개수:" + ((test.split(":")[1]));
        //   }else if(test.split(":")[0] == 'sofaCount'){
        //     console.log(test.split(":")[0]);
        //     testString += "소파:" + test.split(":")[1];
        //   }
        // }
        // return testString;

      },

  }
}
</script>

<style scoped>
.imgPanel{
  
}
.move-main-title {
    margin: 50px;
  }
.panel{
  margin: 100px;

}
#used-main-dropbox {
  
    width: 100px;
    float: right;
    margin-right: 100px;
  }
span{
  font-weight: bold;
}  
  
</style>