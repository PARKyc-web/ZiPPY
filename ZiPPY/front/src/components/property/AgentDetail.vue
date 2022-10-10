<template>
  <div id="container">
    <div id="agent_profile">
      <table>
        <tr>
          <td>
            <h2>{{this.profile[0].compName}}</h2>
          </td>
          <td>
            <v-btn elevation="2">수정하기</v-btn>
          </td>
        </tr>
      </table>
      <div id="agent_profile_left">
        <h3>{{this.profile[0].ceoName}}
        </h3>
        <h3>{{this.profile[0].phone}}
        </h3>
        <h3>인사말</h3>
        <p>{{this.profile[0].compIntro}}</p>
      </div>
      <div id="agent_profile_right">
        <table>
          <tr>
            <td>
              <h3>오시는 길</h3>
            </td>
            <td>
              <p>{{this.profile[0].compAddress}}</p>
            </td>
          </tr>
        </table>
        <!-- 지도 -->
        <basic-marker-map :address="this.profile[0].compAddress" :name="this.profile[0].compName" />
      </div>
    </div>
    <hr>
    <div>
      <h2>
        매물 목록 <i class="bi bi-plus-circle-fill"></i>
      </h2>
      <div class="row">
        <v-card v-if="properties.length != 0" v-for="item in properties" style="width: 45%; margin: 1%">
          <table>
            <tr>
              <td>여기에 이미지</td>
              <td>
                <p>{{item.houseName}}</p>
                <h4>{{item.saleType}} {{item.price}}</h4>
                <p>{{item.sigungu}}</p>
                <p>{{item.areaExclusive}}m²▫ {{item.floor}}층</p>
                <p>{{item.detailContents}}</p>
              </td>
            </tr>
          </table>
        </v-card>

      </div>
    </div>
    <hr>
    <div>
      <h2>후기</h2>
      <div class="row">
        <v-card style="width: 45%; margin: 1%">
          <table>
            <tr>
              <td><i class="bi bi-person-circle"></i>
                <p>username</p>
              </td>
              <td>
                <p>★ 4.5</p>
                <p>후기 내용</p>
              </td>
            </tr>
          </table>
        </v-card>

        <v-card style="width: 45%; margin: 1%">
          <table>
            <tr>
              <td><i class="bi bi-person-circle"></i>
                <p>username</p>
              </td>
              <td>
                <p>★ 4.5</p>
                <p>후기 내용</p>
              </td>
            </tr>
          </table>
        </v-card>

        <v-card style="width: 45%; margin: 1%">
          <table>
            <tr>
              <td><i class="bi bi-person-circle"></i>
                <p>username</p>
              </td>
              <td>
                <p>★ 4.5</p>
                <p>후기 내용</p>
              </td>
            </tr>
          </table>
        </v-card>


      </div>
    </div>
  </div>
</template>

<script>
  import BasicMarkerMap from "./BasicMarkerMap.vue";
  import axios from 'axios';

  export default {
    components: {
      BasicMarkerMap,
    },
    data() {
      return {
        properties : [],
        profile : [],
      }
    },
    created() {
      axios({
          url: "http://localhost:8090/zippy/property/getAgentProperties",
          methods: "GET",
          params: {
            email : 'youngran@email.com'
          }
        }).then(response => {
          // 성공했을 때
          console.log('success!');
          console.log(response);
          this.properties = response.data;
        })
        .catch(error => {
          // 에러가 났을 때
          console.log('fail!');
          console.log(error);
          
        }),
        axios({
          url: "http://localhost:8090/zippy/property/getAgentProfile",
          methods: "GET",
          params: {
            businessEmail : 'youngran@email.com'
          }
        }).then(response => {
          // 성공했을 때
          console.log('success!');
          console.log(response);
          this.profile = response.data;
        })
        .catch(error => {
          // 에러가 났을 때
          console.log('fail!');
          console.log(error);
          
        })
    },
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
    background-color: lightcoral;
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

  h2 {
    padding: 10px;
  }
</style>