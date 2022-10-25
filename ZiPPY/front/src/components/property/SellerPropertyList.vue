<template>
  <div id="container">
    <div>
      <h4 class="title">
        매물 목록
      </h4>

      <div class="row" style="margin-left:35px ;">
        <v-card v-if="properties.length != 0" v-for="item in properties" style="width: 45%; margin: 1%; padding: 20px;"
          @click="goHouseDetail(item.productId)">
          <v-card-text>
            <table>
              <tr>
                <td style="width: 35%;"><img :src="'http://localhost:8090/zippy/common/img/property/' + item.mainImg"
                    style="width: 100%; height: 100%" /></td>
                <td style="width: 65%;">
                  <v-row align="center" class="mx-0">
                    <div>매물번호 {{item.productId}}</div>
                  </v-row>
                  <v-card-title style="font-weight: bold;">{{item.saleType}} {{item.price | oneHundredMillion}}
                  </v-card-title>
                  <p class="card_contents">{{item.houseType}} · {{item.houseName}}</p>
                  <p class="card_contents">{{item.sigungu}}</p>
                  <p class="card_contents">
                    <v-icon>mdi-border-outside</v-icon>{{item.areaExclusive}}m² · <v-icon>mdi-stairs</v-icon>
                    {{item.floor}}층
                  </p>
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
            email: this.$store.state.loginInfo.email
          }
        }).then(response => {
          // 성공했을 때
          // console.log(response);
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
      }
    },
    methods: {
      getPropertyList() {
        axios({
            url: "http://localhost:8090/zippy/property/getAgentProperties",
            methods: "GET",
            params: {
              email: this.$store.state.loginInfo.email,
              pageNum: this.page
            }
          }).then(response => {
            // 성공했을 때
            // console.log(response);
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