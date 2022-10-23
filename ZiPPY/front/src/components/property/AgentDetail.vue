<template>
  <div id="container">
    <div id="agent_profile">
      <div id="agent_profile_left">
        <table>
          <tr>
            <td>
              <h3 id="main_title">{{this.profile[0].compName}}</h3>
            </td>
            <td>
              <update-agent-profile :profile="this.profile[0]"></update-agent-profile>
            </td>
          </tr>
        </table>
        <table>
          <tr>
            <td>
              <h4 class="title">대표자</h4>
            </td>
            <td>
              <h5 style="margin-left: 10px;">
                {{this.profile[0].ceoName}}
              </h5>
            </td>
          </tr>
          <tr>
            <td>
              <h4 class="title">연락처</h4>
            </td>
            <td>
              <h5 style="margin-left: 10px;">
                {{this.profile[0].phone}}
              </h5>
            </td>
          </tr>
        </table>

        <h4 class="title">인사말</h4>
        <p class="contents">{{this.profile[0].compIntro}}</p>
      </div>
      <div id="agent_profile_right">
        <h4 class="title" style="margin-top: 83.6px;">오시는 길</h4>
        <div class="contents">
          <p>{{this.profile[0].compAddress}}</p>
          <!-- 지도 -->
          <basic-marker-map :address="this.profile[0].compAddress" :name="this.profile[0].compName" />
        </div>
      </div>
    </div>
    <hr>
    <div>
      <table>
        <tr>
          <td>
            <h4 class="title">
              매물 목록
            </h4>
          </td>
          <td>
            <insert-property :email="this.profile[0].email" />
          </td>
        </tr>
      </table>
      <div class="row" style="margin-left:35px ;">
        <v-card v-if="properties.length != 0" v-for="item in properties" style="width: 45%; margin: 1%; padding: 20px;">
          <v-card-text>
            <table>
              <tr>
                <td style="width: 35%;">여기에 이미지</td>
                <td style="width: 65%;">
                  <v-row align="center" class="mx-0">
                    <div>매물번호 {{item.productId}}</div>
                  </v-row>
                  <v-card-title style="font-weight: bold;">{{item.saleType}} {{item.price}}
                  </v-card-title>
                  <p class="card_contents">{{item.houseType}} · {{item.houseName}}</p>
                  <p class="card_contents">{{item.sigungu}}</p>
                  <p class="card_contents">{{item.areaExclusive}}m² · {{item.floor}}층</p>
                  <p class="card_contents">{{item.detailContents}}</p>
                  <update-property :productId="item.productId"></update-property>
                </td>
              </tr>
            </table>
          </v-card-text>
        </v-card>
      </div>

      <div class="text-center" style="margin: 25px;">
        <v-pagination v-model="page" :length="pageCount" circle color="#B3E3C3" total-visible="5"></v-pagination>
      </div>

    </div>
  </div>
</template>

<script>
  import BasicMarkerMap from "./BasicMarkerMap.vue";
  import axios from 'axios';
  import InsertProperty from './InsertProperty.vue';
  import UpdateProperty from './UpdateProperty.vue';
  import UpdateAgentProfile from './UpdateAgentProfile.vue';
  import {
    oneHundredMillion
  } from '../../assets/property/propertyPrice';

  export default {
    components: {
      BasicMarkerMap,
      InsertProperty,
      UpdateProperty,
      UpdateAgentProfile,
    },
    data() {
      return {
        properties: [],
        profile: [],
        page: 1,
        pageCount: 1,
      }
    },
    created() {
      this.getPropertyList();

      axios({
          url: "http://localhost:8090/zippy/property/getAgentProfile",
          methods: "GET",
          params: {
            email: this.$route.query.email
          }
        }).then(response => {
          // 성공했을 때
          console.log('getAgentProfile success!');
          console.log(response);
          this.profile = response.data;
        })
        .catch(error => {
          // 에러가 났을 때
          console.log('getAgentProfile fail!');
          console.log(error);

        })
    },
    watch: {
      page() {
        this.getPropertyList();
      }
    },
    methods: {
      getPropertyList() {
        axios({
            url: "http://localhost:8090/zippy/property/getAgentProperties",
            methods: "GET",
            params: {
              email: this.$route.query.email,
              pageNum: this.page
            }
          }).then(response => {
            // 성공했을 때
            console.log('getAgentProperties success!');
            console.log(response);
            this.properties = response.data.list;
            this.pageCount = response.data.pages;
          })
          .catch(error => {
            // 에러가 났을 때
            console.log('getAgentProperties fail!');
            console.log(error);
          })
      }
    }
  };
</script>

<style scoped>
  #container {
    margin: 0 auto;
    width: 70%;
  }

  #agent_profile {
    width: 100%;
    height: 500px;
  }

  #agent_profile_left {
    width: 50%;
    float: left;

    background-color: lightyellow;
  }

  #agent_profile_right {
    width: 50%;
    float: right;
    background-color: lightblue;
  }

  #map {
    width: 90%;
    height: 300px;
  }

  #main_title {
    margin-top: 25px;
    margin-bottom: 25px;
    font-weight: bold;
  }

  .title {
    margin-left: 20px;
    font-weight: bold;
  }

  .contents {
    margin-top: 10px;
    margin-left: 35px;
    margin-right: 35px;
  }

  .card_contents {
    margin-left: 20px;
    font-weight: bold;
  }
</style>