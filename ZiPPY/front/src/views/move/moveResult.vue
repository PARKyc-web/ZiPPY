<template>
  <div>
    <move-nav-bar @click="categoryVal=$event.target.innerText"></move-nav-bar>
  
    <div class="result-wrap">
    <form>
      <div class="move-main-title">
        <h3>보낸 견적요청 조회</h3>
      </div>
        <!-- 드롭박스 -->
      <div id="used-main-dropbox">
          <v-select @change="dropVal()" v-model="select" :items="items" item-text="name" item-value="value" label="보기정렬"
            color="#212529" class="drop-size" persistent-hint single-line dense width="50"></v-select>
      </div>

      <hr />

      <div id="noProduct" class="mx-auto" v-if="list.length == 0" style="text-align:center">
      <v-icon style="font-size:100px; color:#B3E3C3" class="mb-5">mdi-alert-circle-outline</v-icon>
      <h2 style="font-weight:bold">아직 요청한 견적내역이 없습니다.</h2>
    </div>


    <div class="panel">
      <v-expansion-panels>
        <v-expansion-panel
        v-if="list.length != 0" v-for="item in list"
        >
          <v-expansion-panel-header>
            <span>NO.{{item.estimateNo}}</span>  &nbsp;&nbsp;&nbsp; 견적요청일 : <span>{{item.requestDate}}</span>&nbsp;&nbsp;&nbsp; 견적 방법 : <span>{{item.estimateType}}</span>
          </v-expansion-panel-header>
          <v-expansion-panel-content>
            <div class="info">
            <div >이사 종류 : <span>{{item.moveType}}</span></div>
            <div >이사희망일 : <span>{{item.movingDate}}</span></div>
            <div >이사희망시간 : <span>{{item.movingTime}}</span></div>
            <div >출발지 주소 : <span>(우편){{item.departZipCode}}</span>    <span>{{item.departAddress}}</span> <span>{{item.departDetail}}</span></div>
            <div >도착지 주소 : <span>(우편){{item.arriveZipCode}}</span>    <span>{{item.arriveAddress}}</span> <span>{{item.arriveDetail}}</span></div>
            <div> 이사 정보 : <div class="move-info"><span v-html="par(item.commonOption)"></span></div></div>
            <div v-if="item.estimateType == '비대면견적'">
            <div >이삿짐 정보 : <div class="move-info"><span class="move-info" v-html="my(item.movingOption)"></span></div></div>
            </div>  
     
            <div v-if="item.movingMemo != null">
            <div >이사 요청사항 : <span>{{item.movingMemo}}</span></div>
            </div>
            <div v-if="item.estimateType == '대면견적'">
            <div >견적 방문희망일 : <span>{{item.visitDate}}</span></div>
            <div >견적 방문희망시간 : <span>{{item.visitTime}}</span></div>
            </div>
            <div v-if="item.estimateType == '비대면견적'" id="imgPanel">
            </div> 
            
            <div v-if="item.estimateType == '비대면견적'">
          
          <!-- 이미지 펼치기 -->
          <v-card-actions>
            <v-spacer></v-spacer>
                <v-label >방사진보기</v-label> 
                  <v-btn icon @click="show = !show">
                    <v-icon>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
                  </v-btn>
          </v-card-actions>

          <v-expand-transition>
            <div v-show="show">
              <v-divider></v-divider>
                <div id="showImg">
                  방입구<div v-for="img in photoList">
                          <img v-if="img.estimateNo == item.estimateNo && img.imgType ==1" 
                          :src="'/zippy/common/img/move/' + img.houseImg"/>
                        </div>
                  방중앙 <div v-for="img in photoList">
                          <img v-if="img.estimateNo == item.estimateNo && img.imgType ==2" 
                          :src="'/zippy/common/img/move/' + img.houseImg"/>
                        </div>
                  내부 <div v-for="img in photoList">
                          <img v-if="img.estimateNo == item.estimateNo && img.imgType ==3" 
                          :src="'/zippy/common/img/move/' + img.houseImg"/>
                        </div>
                </div>
            </div>
          </v-expand-transition>
          </div>
          </div>


          </v-expansion-panel-content>
        </v-expansion-panel>
      </v-expansion-panels>
    </div>
    </form>
  </div>
  <div class="text-center">
      <v-pagination v-model="page" :length="pageCount" circle color="#B3E3C3"></v-pagination>
    </div>

</div>
</template>

<script>
import axios from 'axios';
import swal from 'sweetalert2';
import MoveNavBar from '@/components/move/MoveNavBar.vue';

export default {
  components: {
    MoveNavBar
  },

  data : function () {
    return{
      
      //페이징
      selection: 1,
        heart: 0,
        wish: "",
        bNo: "",
        page: 1,
        pageCount: 1,

      //펼치기
      show: false,
      email:"",
      item: "",
      //
      photoList:[],
      list : [],
      length: "",
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
    //사용자가 자신이 보낸 견적요청 확인하기
    axios({
          url: "/zippy/move/moveResult",
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
          url: "/zippy/move/movePhoto",
          methods: "GET",
          
        }).then(res => {
          console.log(res);
          this.photoList = res.data;
        }).catch(error => {
          console.log(error);
        })
  },
  methods : {
    //드롭박스 데이터 가져오기
    dropVal: function () {
        var dropValue = this.select;
        
        axios({
          url: "/zippy/move/moveResult",
          methods: "GET",
          //필요한 정보
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
            //Json String 형태로 한 컬럼에 집어넣었던 데이터를 꺼내서 파싱
          var temp = JSON.parse(string);
            //파싱하여 쪼갠 데이터 담을 변수선언
          var detail = '';
            //데이터 내의 필요한 정보들을 이름으로 꺼내어 담기
          detail += `<div>주거형태 : ${temp.houseType}</div>`
          detail += `<div>방구조 : ${temp.roomNum}</div>`
          detail += `<div>집 평수 : ${temp.spaceOfHome}</div>`
          detail += `<div>층수 : ${temp.floor}</div>`
          detail += `<div>화장실 개수 : ${temp.toilet}</div>`
          detail += `<div>베란다 개수 : ${temp.veranda}</div>`
          detail += `<div>별도 계단 : ${temp.extraStairs}</div>`
          detail += `<div>엘레베이터 : ${temp.elevator}</div>`
          detail += `<div>주차가능 여부 : ${temp.parkable}</div>`

          return detail;
        },

        /*
          var fur = ['bed', 'sofa', 'closet', 'closets']

          for(let i of fur){
            if (temp.bed.length > 0  ){
              detail+= `<div>침대 : ${temp[i +'Count']}(${temp[i].join(",")})</div>`
            } 
          }  
        */        
      my : function(string){
          //Json String 형태로 담겨있던 데이터를 꺼내서 파싱
        var temp= JSON.parse(string);
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
        //서랍장
        if (temp.closets.length > 0 &&  temp.closets[1]){
          detail+= `<div>서랍장 : ${temp.closetsCount}(${temp.closets.join(",")})</div>`
        } 
        //테이블/책상
        // if (temp.table.length > 0 &&  temp.table[1]){
        //   detail+= `<div>테이블/책상 : ${temp.tableCount}(${temp.table.join(",")})</div>`
        // } 
        //의자
        // if (temp.chair.length > 0 &&  temp.chair[1]){
        //   detail+= `<div>의자 : ${temp.chairCount}(${temp.chair.join(",")})</div>`
        // } 

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
        //전자레인지
        if (temp.trolley.length > 0 &&  temp.trolley[1]){
          detail+= `<div>전자레인지 : ${temp.trolleyCount}(${temp.trolley.join(",")})</div>`
        } 
        //에어컨
        // if (temp.aircon.length > 0 &&  temp.aircon[1]){
        //   detail+= `<div>에어컨 : ${temp.airconCount}(${temp.aircon.join(",")})</div>`
        // } 
        //세탁기
        // if (temp.laundry.length > 0 &&  temp.laundry[1]){
        //   detail+= `<div>세탁기 : ${temp.laundryCount}(${temp.laundry.join(",")})</div>`
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

.info{
  margin: 20px 20px 20px 200px;
}
.move-info{
  padding-left: 100px;
}
.move-main-title {
    margin: 50px;
  }
.mx-auto{
  margin-top: 200px;
}

.panel{
  margin: 100px;

}
#used-main-dropbox {
  
    width: 100px;
    float: right;
    margin: 50px 100px 0 100px;
  }
span{
  font-weight: bold;
}  
  
</style>