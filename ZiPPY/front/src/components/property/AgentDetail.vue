<template>
  <div>
    <div id="main-background"></div>
    <div id="container">
      <div id="agent_profile">
        <div id="agent_profile_left">
          <table>
            <tr>
              <td>
                <h3 id="main_title">{{this.profile[0].compName}}</h3>
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
          <!-- <h4 class="title" style="margin-top: 83.6px;">오시는 길</h4> -->
          <h4 class="title" style="margin-top: 20px;">오시는 길</h4>
          <div class="contents">
            <p>{{this.profile[0].compAddress}}</p>
            <!-- 지도 -->
            <basic-marker-map :address="this.profile[0].compAddress" :name="this.profile[0].compName" />
          </div>
        </div>
      </div>
      <div>
        <table>
          <tr>
            <td>
              <h4 class="title" style="margin-left:95px ;"> 
                매물 목록
              </h4>
            </td>
          </tr>
        </table>
        <div class="row" style="margin-left:130px ;">
          <v-card v-if="properties.length != 0" v-for="item in properties"
            style="width: 40%;  margin: 1%; padding: 20px;" @click="goHouseDetail(item.productId)">
            <v-card-text>
              <table>
                <tr>
                  <td style="width: 50%;"><img :src="'/zippy/common/img/property/' + item.mainImg"
                      style="width: 100%; height: 100%" /></td>
                  <td style="width: 50%;">
                    <div style="margin-left:15px">
                      <v-row align="center" class="mx-0">
                        <div>
                          <v-chip class="ma-2" color="green lighten-2" small outlined style="left: -10px; top: 10px">
                            매물번호
                            {{item.productId}} </v-chip>
                        </div>
                      </v-row>
                      <v-card-title style="font-weight: bold;">{{item.saleType}} {{item.price | oneHundredMillion}}
                      </v-card-title>
                      <p class="card_contents">{{item.houseType}} · {{item.houseName}}</p>
                      <p class="card_contents">{{item.sigungu}}</p>
                      <p class="card_contents">
                        <v-icon>mdi-border-outside</v-icon>{{item.areaExclusive}}m² · <v-icon>mdi-stairs</v-icon>
                        {{item.floor}}층
                      </p>
                      <p class="card_contents">{{item.detailContents | showContents}}</p>
                    </div>
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
  </div>
</template>

<script>
  import BasicMarkerMap from "./BasicMarkerMap.vue";
  import axios from 'axios';
  import InsertProperty from './InsertProperty.vue';
  import UpdateProperty from './UpdateProperty.vue';
  import UpdateAgentProfile from './UpdateAgentProfile.vue';

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
          url: "/zippy/property/getAgentProfile",
          method: "GET",
          params: {
            email: this.$route.query.email
          }
        }).then(response => {
          // 성공했을 때
          this.profile = response.data;
        })
        .catch(error => {
          // 에러가 났을 때
          console.log(error);

        })
    },
    watch: {
      page() {
        this.getPropertyList();
      }
    },
    filters: {
      oneHundredMillion: function (price) {
        let result = "";

        if (price.length > 4) {
          result += price.substr(0, price.length - 4) + '억';
          price = price.substr(price.length - 4, price.length);
          if (price == '0000') return result;
        }
        price = price.substr(price.length - 4, price.length - 3) + ',' + price.substr(price.length - 3, price.length);
        if (price[0] == '0') {
          price = price.substr(price.length - 3, price.length);
        }
        result += price;

        return result;
      },
      showContents: function (contents) {
        let result = '';
        result = contents.substr(0, 13) + '...';

        return result;
      }
    },
    methods: {
      getPropertyList() {
        axios({
            url: "/zippy/property/getAgentProperties",
            method: "GET",
            params: {
              email: this.$route.query.email,
              pageNum: this.page
            }
          }).then(response => {
            // 성공했을 때
            this.properties = response.data.list;
            this.pageCount = response.data.pages;
          })
          .catch(error => {
            // 에러가 났을 때
            console.log(error);
          })
      },
      goHouseDetail(productId) {
        this.$router.push({
          name: 'HouseDetail',
          query: {
            productId: productId
          }
        })
      },
    }
  };
</script>

<style scoped>
  #container {
    margin: 0 auto;
    width: 70%;
    position: relative;
    z-index: 1;
  }

  #agent_profile {
    width: 100%;
    height: 395px;
    margin-top: 25px;
    margin-bottom: 60px;
    background-color: white;
    border-radius: 1.5em;
    z-index: 1;
  }

  #agent_profile_left {
    width: 30%;
    float: left;
    margin-left: 70px;
  }

  #agent_profile_right {
    width: 50%;
    float: right;

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

  #main-background {
    margin-top: 76px;
    width: 100vw;
    height: 450px;
    background-color: #B3E3C3;
    position: absolute;
    top: 0;
    z-index: 0;
  }
</style>
