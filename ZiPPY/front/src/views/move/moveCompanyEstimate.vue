<template>
  <div class="result-wrap">

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


    <hr />
    <div class="panel">
      
    </div>


  </div>
</template>

<script>
  import axios from 'axios';
  export default {

    data: function () {
      return {
      
        item: "",
        email: "",
        estimateNo: "",
        responseMemo: "",
        estimateType: "",
        firstEstimatePrice: "",
        secondEstimateType: "",
        secondEstimatePrice: "",
        compName: "82이사",
        reservStatus: 0,

        list: [],
        vo: {
          email: "zippy@naver.com",
          requestDate: "",
          departAddress: "",
          arriveAddress: "",
          compAddress: "",
          estimateNo: "",
          estimateType: ""
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
        selectData:{},
        select: '',
        select2: '', //지역
        dialog: false,
        on: '',
        attrs: '',

      }
      
    },
    
    created : function(){

    },

    methods : {

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