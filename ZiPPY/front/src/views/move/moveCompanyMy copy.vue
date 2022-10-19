<template>

  <div class="compMy-wrap">

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
    

    <div id="used-main-dropbox1">
      <v-select @change="dropVal2()" v-model="select2" :items="drops" item-text="name" item-value="value2" label="정렬"
        color="#212529" persistent-hint single-line dense width="50"></v-select>
    </div>


    <hr />

    <div class="divv">
    <form id="estimateForm" name="estimateForm" >
           
      <template >
        <div v-if="list.length != 0" v-for="(item,i) in list">
        <v-card 
    class="mx-auto"
    elevation="5"
    max-width="500"
  >
    

    <v-card-title>
      견적서번호 <div id="mus">NO.{{item.movingResponseNo}}</div>
    </v-card-title>

    <v-card-subtitle>
      견적요청 번호 <div id="mus">NO.{{item.estimateNo}}</div>
    </v-card-subtitle>
    <v-card-subtitle>
      견적상태 <div id="mus">{{item.reservStatus}}</div>
    </v-card-subtitle>
    <v-card-text>
          1차 견적 타입<div id="mus">{{item.firstEstimateType}}</div>
        </v-card-text>
        <v-card-text>
          1차 견적가<div id="mus">{{item.firstEstimatePrice}}</div>
        </v-card-text>
        <v-card-text>
          업체어필<div id="mus">{{item.responseMemo}}</div>
        </v-card-text>
    <v-card-actions>
      <v-btn 
        color="orange lighten-2"
        text
      >
        요청정보보기
      </v-btn>
      <v-btn 
        color="success lighten-2"
        text
      >
        견적확정보내기
      </v-btn>

      <v-spacer></v-spacer>

      <v-btn
        icon
        @click="item.show = !item.show"
      >
        <v-label>재견적</v-label><v-icon>{{ item.show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
      </v-btn>
    </v-card-actions>

    <v-expand-transition>
      <div v-show="item.show">
        <v-divider></v-divider>

        


        <v-col >
        <v-autocomplete v-model="selectData.secondEstimateType"
                                  :items="['대면견적', '비대면견적']"
                                  label="2차 견적타입*"></v-autocomplete>
        </v-col>
        <v-col>
          <v-text-field v-model="selectData.secondEstimatePrice" label="2차 견적가격*" type="number" required>
          </v-text-field>
        </v-col>

      </div>
    </v-expand-transition>
  </v-card>
</div>
</template>


    </form>
  </div>
  </div>

</template>

<script>
  import axios from 'axios';

export default{
  data: function () {
      return {
      
        //카드
        show: false,

        item: "",
        email: "",
        estimateNo: "",
        responseMemo: "",
        firstEstimateType: "",
        firstEstimatePrice: "",
        secondEstimateType: "",
        secondEstimatePrice: "",
        compName: "82이사",
        reservStatus: 0,
        

        list: [],
        vo: {
          email: "move123@move.com",
          requestDate: "",
          departAddress: "",
          arriveAddress: "",
          compAddress: "",
          estimateNo: "",
          estimateType: "",
          
        },
        

        drops: [{
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
        selectData:{},
        select: '',
        select2: '', //지역
        dialog: false,
        on: '',
        attrs: '',

      }
    },
    created() {
      axios({
        url: "http://localhost:8090/zippy/move/moveCompanyEstimate",
        methods: "GET",
        params: {
          email: "move123@move.com",
          checked: "",
          dropbox: "",
          dropbox2: ""

        }
        
      }).then(res => {
        console.log(res);
        for(let i=0; i < res.data.length; i++) {
          res.data[i].show = false;
        }
        this.list = res.data;
      }).catch(error => {
        console.log(error);
      })
    },
    methods: {
      checkbox: function () {
        const ckbox = document.querySelector(".form-check-input");
        const is_cked = ckbox.checked;
        var isChecked = document.querySelector(".form-check-input").innerText = is_cked
        console.log(isChecked);
        axios({
          url: "http://localhost:8088/zippy/used/main",
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
      
      dropVal2: function () {

        var dropValue2 = this.select2;
        console.log(dropValue2);
        console.log(this.vo.email);
        axios({
          url: "http://localhost:8090/zippy/move/moveEstimate",
          methods: "GET",
          params: {
            dropbox: this.select,
            dropbox2: dropValue2, //지역
            email: this.vo.email,
            requestDate: this.vo.requestDate,
            
          }
        }).then(res => {
          console.log(res);
          for(let i=0; i < res.data.length; i++) {
            res.data[i].show = false;
          }
          this.list = res.data;
        }).catch(err => {
          console.log(err);
        })
      },
     

      sendEstimate: function () {

        this.selectData.reservStatus = "1";

        var formData = new FormData(document.querySelector('#estimateForm'));

        // this.estimateNo = document.getElementById(estimateNo).value;
        // this.email = document.getElementById(email).value;
        // this.firstEstimateType = document.getElementById(firstEstimateType).value;

        console.log("견적번호: " + this.selectData.estimateNo);
        console.log("회원: " + this.selectData.email);
        console.log("1차견적가격: " + this.selectData.firstEstimatePrice);
        console.log("1차견적타입: " + this.selectData.estimateType);
        console.log("2차견적가격: " + this.selectData.secondEstimatePrice);
        console.log("2차견적타입: " + this.selectData.secondEstimateType);
        console.log("견적상태: " + this.selectData.reservStatus);
        console.log("업체명: " + this.selectData.compName);
        console.log("견적어필: " + this.selectData.responseMemo);

        this.$axios({
          url: "http://localhost:8090/zippy/move/moveEstimate",
          method: "POST",
          // headers: {
          //   "Content-Type": "application/json; charset=utf-8"
          // },
          // params:{

          // },
          data: formData
        }).then(res => {
          console.log(res);
          
        }).catch(err => {
          console.log(err)
        })
      }
    }
}
</script>

<style scoped>
/* card */
.compMy-wrap{
  display: inline;
    justify-content: space-between;
    margin: 50px;
}
.divv {
    padding: 40px;
  }
.mx-auto{
  margin: 100px;
}


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