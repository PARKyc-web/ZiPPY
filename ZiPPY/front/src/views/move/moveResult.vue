<template>
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
            <div >이사정보 : <span>{{my(item.movingOption)}}</span></div>


            <div v-if="item.movingMemo != null">
            <div >이사 요청사항 : <span>{{item.movingMemo}}</span></div>
            </div>
            <div v-if="item.estimateType == '대면견적'">
            <div >견적 방문희망일 : <span>{{item.visitDate}}</span></div>
            <div >견적 방문희망시간 : <span>{{item.visitTime}}</span></div>
            </div>
            <div v-if="item.estimateType == '비대면견적'">
            <div >이미지 : <span>{{item.moveImage}}</span></div>
            </div>
          </v-expansion-panel-content>
        </v-expansion-panel>
      </v-expansion-panels>
    </div>
    </form>
  </div>
</template>

<script>
import axios from 'axios';
export default{

  data : function () {
    return{
      
      list : [],
      vo : {
        email : "zippy@naver.com",
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
  created(){
    axios({
          url: "http://localhost:8090/zippy/move/moveEstimate",
          methods: "GET",
          params: {
            email : "zippy@naver.com",
            movingOption : "",
            dropbox: ""

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
        console.log(this.vo.email);
        axios({
          url: "http://localhost:8090/zippy/move/moveResult",
          methods: "GET",
          params: {  
            dropbox : dropValue,
            email : this.vo.email,
            requestDate : this.vo.requestDate,
            estimateType : this.vo.estimateType,
            
          }
        }).then(res => {
          console.log(res);
          this.list = res.data;
        }).catch(err => {
          console.log(err);
        })
      },
      my : function(string){
        let testString = "";
        
        for(let test of string.split(",")){
          console.log(test)
          console.log(test.split(":"));
          if(test.split(":")[0] == 'bedCount'){
            testString += "침대:" + test.split(":")[1];
          }else if(test.split(":")[0] == 'sofaCount'){
            console.log(test.split(":")[0]);
            testString += "소파:" + test.split(":")[1];
          }
        }
        return testString;
      }
  }
}
</script>

<style scoped>
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