<template>
  <div>
    <move-nav-bar @click="categoryVal=$event.target.innerText"></move-nav-bar>
    <div class="move-main-title">
      <h3>업체 조회</h3>
      <br /><hr />
    </div>
  <div class="company-wrap">
    <!-- 드롭박스 -->
    <div id="used-main-dropbox1">
      <v-select @change="dropVal2()" v-model="select2" :items="drops" item-text="name" item-value="value2" label="정렬"
        color="#212529" persistent-hint single-line dense width="50"></v-select>
    </div>

    <div class="divv" v-for="item in list">
      <v-card :loading="loading" class="mx-auto my-12" max-width="374" elevation="10" >
        <template slot="progress">
          <v-progress-linear color="#B3E3C3" height="10" indeterminate></v-progress-linear>
        </template>
        
    <!-- 카드 -->
        <v-card-title>업체명 : <span>{{item.compName}}</span></v-card-title>
        <v-card-text>
          <v-row text-align="center" class="mx-0">
            <v-rating :value="item.totalRating" color="amber" dense half-increments readonly size="20"></v-rating>
            <div class="grey--text ms-4">
              {{Math.round(item.totalRating * 10)/10}} 
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
import swal from 'sweetalert2';
import MoveNavBar from '@/components/move/MoveNavBar.vue';

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
        serviceType: 3,
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
      //업체조회
      axios({
        url: "/zippy/move/moveCompanyList",
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

        if (this.$store.state.loginInfo == null || this.$store.state.loginInfo.email == "") {
          swal.fire({
            title: '로그인이 필요합니다.',
            text: "로그인 하시겠습니까 ?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#96e5b8',
            cancelButtonColor: '#a9a9a9',
            confirmButtonText: 'YES'
          }).then((result) => {
            if (result.isConfirmed) {
              this.$router.push('/login')
            }
          })
          
        } else {
          // window.location.assign('/mypage/move/moveResult');
          this.$router.push('/move/moveReview?serviceId=' + email );
        }

          
        
      },
      //체크박스
      checkbox: function () {
        const ckbox = document.querySelector(".form-check-input");
        const is_cked = ckbox.checked;
        var isChecked = document.querySelector(".form-check-input").innerText = is_cked
        console.log(isChecked);
        axios({
          url: "/zippy/used/main",
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
          url: "/zippy/move/moveCompanyList",
          methods: "GET",
          params: {
            dropbox2: dropValue2,         
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

  .move-main-title{
    margin: 50px 150px 0 150px;
  }
.company-wrap {
    
    margin: 30px 100px 100px 100px;
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