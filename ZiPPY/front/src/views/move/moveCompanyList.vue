<template>
  <div>
    <move-nav-bar @click="categoryVal=$event.target.innerText"></move-nav-bar>
  <div class="company-wrap">
    <div class="move-main-title">
      <h3>업체 조회</h3>
    </div>

    <div class="form-check">
      <input @click="checkbox ()" class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
      <label class="form-check-label" for="flexCheckDefault">
        내 지역만 보기
      </label>
    </div>
    <hr />
    <!--  -->
 


    <div id="used-main-dropbox1">
      <v-select @change="dropVal2()" v-model="select2" :items="drops" item-text="name" item-value="value2" label="정렬"
        color="#212529" persistent-hint single-line dense width="50"></v-select>
    </div>

    <div class="divv" v-for="item in list">
      <v-card :loading="loading" class="mx-auto my-12" max-width="374" elevation="10" >
        <template slot="progress">
          <v-progress-linear color="#B3E3C3" height="10" indeterminate></v-progress-linear>
        </template>

        <!-- <v-img
      height="250"
      src="https://cdn.vuetifyjs.com/images/cards/cooking.png"
    ></v-img> -->
        
    <!-- 카드 -->
        
              <!--heart-->
                <!--<div>
                   <button>
                    <div>
                      <v-btn v-if="heart==0" class="mx-2" color='#D6D6D6' fab depressed dark small
                        @click="changeHeart()">
                        <v-icon dark>
                          mdi-heart
                        </v-icon>
                      </v-btn>
                      <v-btn v-if="heart==1" class="mx-2" color='#FF4063' fab depressed dark small
                        @click="changeHeart()">
                        <v-icon dark>
                          mdi-heart
                        </v-icon>
                      </v-btn>
                    </div>
                  </button>
                </div> -->

        <v-card-title>업체명 : <span>{{item.compName}}</span></v-card-title>

        <v-card-text>
          <v-row text-align="center" class="mx-0">
            <v-rating :value="item.totalRating" color="amber" dense half-increments readonly size="20"></v-rating>

            <div class="grey--text ms-4">
              {{item.totalRating}} (0)
            </div>
          </v-row>
          <v-divider class="mx-4"></v-divider>
          <div>
            연락처 : <span>{{item.phone}}</span>
          </div>
          <div>
            주소 : <span>{{item.compAddress}}</span>
          </div>

          <div class="my-4 text-subtitle-1">{{item.compIntro}}</div>
        </v-card-text>

        <v-divider class="mx-4"></v-divider>
        

        <!-- <v-card-text>
      <v-chip-group
        v-model="selection"
        active-class="deep-purple accent-4 white--text"
        column
      >
        <v-chip>5:30PM</v-chip>

        <v-chip>7:30PM</v-chip>

        <v-chip>8:00PM</v-chip>

        <v-chip>9:00PM</v-chip>
      </v-chip-group>
    </v-card-text> -->

        <v-card-actions>
          <v-btn color="#B3E3C3 lighten-2" text @click="chat">
            채팅하기
          </v-btn>

          <v-btn color="#B3E3C3 lighten-2" text @click="goToCompany(item.email)">
            후기보기
          </v-btn>
        </v-card-actions>
      </v-card>
    </div>

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

  
    
    data : function(){
      return{

  icons: {
    iconfont: 'mdi', // 'mdi' || 'mdiSvg' || 'md' || 'fa' || 'fa4' || 'faSvg'
  },

      loading: false,
      selection: 1,
      heart : 0,
      wish: "",

      list: [],

      //받은정보
        item: "",
        select2:"",
        userEmail: "",
        email: "",
        estimateNo: "",
        movingResponseNo: "",
        compName : "",
        compAddress: "",
        totalRating: 0,
        firstEstimatePrice: "",
        reservStatus : "",
        serviceType: "",
        dropbox:"",
        
        drops: [{
            name: '전체',
            value: '전체'
          },
          {
            name: '평점순',
            value: '평점순'
          },
          {
            name: '지역순',
            value: '지역순'
          },
         
         
        ],
      }
    },

    created(){
      axios({
        url: "http://localhost:8090/zippy/move/moveCompanyList",
        methods: "GET",
        params: {
          email: "",
          
          checked: "",
          dropbox: "",
          dropbox2: "",

          serviceType: "",
          serviceId: this.list.email,
          memberType:""

        }
      }).then(res => {
        console.log(res);
        this.list = res.data;
        console.log('listttttttt',this.list);

      }).catch(error => {
        console.log(error);
      })

      
        //   axios({
        //   url: "http://localhost:8090/zippy/move/moveRvCount",
        //   methods: "GET",
        //   params: {
            
        //     serviceId: this.list.email,

        //   }
        // }).then(res => {
        //   console.log(res);
        //   this.list = res.data;
        // }).catch(error => {
        //   console.log(error);
        // })


    },
    methods: {
      //채팅하기
      chat() {
        this.loading = true

        setTimeout(() => (this.loading = false), 2000)
      },
      //후기보기
      goToCompany(email){
        
        this.$router.push('/move/moveReview?serviceId=' + email);
        console.log('이메일 :::',email);
      },
      //체크박스
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
      //드롭박스
      dropVal2: function () {

        var dropValue2 = this.select2;
        console.log(dropValue2);
        
        axios({
          url: "http://localhost:8090/zippy/move/moveCompanyList",
          methods: "GET",
          params: {
            dropbox: this.select,
            dropbox2: dropValue2, //지역
           
            totalRating: this.totalRating,
            compAddress: this.compAddress
          }
        }).then(res => {
          console.log(res);
          this.list = res.data;
        }).catch(err => {
          console.log(err);
        })
      },
    },
  }
</script>

<style scoped>
  .company-wrap {
    
    margin: 50px;
  }

  .divv {
    display: inline-flex;
    justify-content: space-between;
    padding: 40px;
    margin-top: 50px;
  }

  /*  */
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

  .v-btn{
    color: #96daac;
  }
</style>