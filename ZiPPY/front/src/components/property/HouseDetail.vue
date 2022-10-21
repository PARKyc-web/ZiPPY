<template>
  <div>
    <div id="main-background"></div>
    <div id="container" v-if="this.houseDetail[0]">
      <v-carousel id="imgSlide" cycle hide-delimiter-background show-arrows-on-hover>
        <v-carousel-item v-for="(slide, i) in slides" :key="i">
          <v-sheet :color="colors[i]" height="100%">
            <v-row class="fill-height" align="center" justify="center">
              <div class="text-h2">
                {{ slide }} Slide
              </div>
            </v-row>
          </v-sheet>
        </v-carousel-item>
      </v-carousel>
      <section>
        <article>
          <h4 class="title">가격정보</h4>
          <table>
            <tr>
              <th>{{this.houseDetail[0].saleType}}</th>
              <td>{{this.price}}</td>
            </tr>
            <tr>
              <th>관리비</th>
              <td>매월 8만원</td>
            </tr>
            <tr>
              <th>주차</th>
              <td v-if="houseDetail[0].parking == 1 ">가능</td>
              <td v-else>불가능</td>
            </tr>
            <tr>
              <th>한달 예상 주거비용</th>
              <td>43만원<br> <small>월세 + 관리비 별도 금액으로 부과되는 항목 제외</small>
              </td>
            </tr>
          </table>
          <hr>
        </article>
        <article>
          <h4 class="title">상세정보</h4>
          <table>
            <tr>
              <th>집종류</th>
              <td>{{this.houseDetail[0].houseType}}</td>
            </tr>
            <tr>
              <th>층수</th>
              <td>{{this.houseDetail[0].floor}}층</td>
            </tr>
            <tr>
              <th>전용면적</th>
              <td>{{this.houseDetail[0].areaExclusive}}m²</td>
            </tr>
            <tr>
              <th>난방종류</th>
              <td>개별난방</td>
            </tr>
            <tr>
              <th>빌트인</th>
              <td>빌트인 주방</td>
            </tr>
            <tr>
              <th>건축년도</th>
              <td>{{this.houseDetail[0].constructionYear}}년</td>
            </tr>
          </table>
          <hr>
        </article>
        <article>
          <h4 class="title">위치 및 주변 시설</h4>
          <div class="contents" style="height: 550px">
            <p>{{this.houseDetail[0].sigungu}} {{this.houseDetail[0].streetAddress}}</p>
            <categoty-map :address="houseDetail[0].streetAddress" :name="houseDetail[0].houseName"></categoty-map>
          </div>
          <hr>
        </article>
        <article>
          <h4 class="title">상세 설명</h4>
          <p class="contents">{{this.houseDetail[0].detailContents}}</p>
          <hr>
          <report-button :productId="this.houseDetail[0].productId"></report-button>
        </article>
      </section>

      <aside>
        <div id="app">
          <v-app id="inspire">
            <v-card class="mx-auto my-12" max-width="374" style="width: 100%;">
              <v-card-text>
                <wish-button :productId="this.houseDetail[0].productId"></wish-button>
                <v-row align="center" class="mx-0">
                  <div>매물번호 {{this.houseDetail[0].productId}}</div>
                </v-row>
                <v-card-title style="font-weight: bold;">{{this.houseDetail[0].saleType}} {{this.price}}</v-card-title>
                <p style="margin-left: 20px;">{{this.houseDetail[0].houseType}} · {{this.houseDetail[0].houseName}}</p>
                <h6 style="margin-left: 20px;">{{this.houseDetail[0].sigungu}} {{this.houseDetail[0].streetAddress}}</h6>
                <br>
                <h5 style="margin-left: 20px;">{{this.houseDetail[0].areaExclusive}}m² · {{this.houseDetail[0].floor}}층</h5>
                <hr>
                <v-card-title style="font-weight: bold;" @click="goAgentDetail">{{this.houseDetail[0].compName}}
                </v-card-title>
                <v-btn block color="#B3E3C3" elevation="2"><span style="color: white;">문의하기</span></v-btn>
              </v-card-text>
            </v-card>
          </v-app>
        </div>
      </aside>

      <!-- END container -->
    </div>
  </div>
</template>

<script>
  import BasicMarkerMap from "./BasicMarkerMap.vue";
  import axios from 'axios';
  import CategotyMap from "./CategotyMap.vue";
  import ReportButton from './ReportButton.vue';
  import WishButton from './WishButton.vue';
  import {oneHundredMillion} from '../../assets/property/propertyPrice';

  export default {
    components: {
      BasicMarkerMap,
      CategotyMap,
      ReportButton,
      WishButton
    },
    data() {
      return {
        colors: [
          'indigo',
          'warning',
          'pink darken-2',
          'red lighten-1',
          'deep-purple accent-4',
        ],
        slides: [
          'First',
          'Second',
          'Third',
          'Fourth',
          'Fifth',
        ],
        houseDetail: [],
        compName: '',
        price: '',
      }
    },
    created() {
      axios({
          url: "http://localhost:8090/zippy/property/houseDetail",
          method: "GET",
          params: {
            productId: this.$route.query.productId // this.productId
          }
        }).then(response => {
          // 성공했을 때
          console.log('houseDetail success!');
          console.log(response.data);
          this.houseDetail = response.data;

          this.price = oneHundredMillion(this.houseDetail[0].price);
        })
        .catch(error => {
          // 에러가 났을 때
          console.log('houseDetail fail!');
          console.log(error);
        })
    },
    mounted() {
      /* Javascript 샘플 코드 */
      var xhr = new XMLHttpRequest();
      var url =
        'http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev'; /*URL*/
      var queryParams = '?' + encodeURIComponent('serviceKey') + '=' +
        'fXQYcvdVSA+RwkypajVwQIt+pKA9zxOYqk6TqzDPacsANCI+suXZErHKSpIcvmqXarHrMNo5Kp80SvDkolPg/g=='; /*Service Key*/
      // 'fXQYcvdVSA%2BRwkypajVwQIt%2BpKA9zxOYqk6TqzDPacsANCI%2BsuXZErHKSpIcvmqXarHrMNo5Kp80SvDkolPg%2Fg%3D%3D';
      queryParams += '&' + encodeURIComponent('pageNo') + '=' + encodeURIComponent('1'); /**/
      queryParams += '&' + encodeURIComponent('numOfRows') + '=' + encodeURIComponent('10'); /**/
      queryParams += '&' + encodeURIComponent('LAWD_CD') + '=' + encodeURIComponent('11110'); /**/
      queryParams += '&' + encodeURIComponent('DEAL_YMD') + '=' + encodeURIComponent('201512'); /**/
      xhr.open('GET', url + queryParams);
      xhr.onreadystatechange = function () {
        if (this.readyState == 4) {
          // alert('Status: ' + this.status + 'nHeaders: ' + JSON.stringify(this.getAllResponseHeaders()) + 'nBody: ' +
          //   this.responseText);
        }
      };
      xhr.send('');

    },
    methods: {
      goAgentDetail() {
        this.$router.push({
          name: 'AgentDetail',
          query: {
            email: this.houseDetail[0].email
          }
        })
      }
    }
  }
</script>

<style scoped>
  #container {
    margin: 0 auto;
    width: 70%;

    display: flex;
    flex-wrap: wrap;
    /* 줄 바꿈 */
  }

  #imgSlide {
    width: 100%;
    margin-top: 25px;
    margin-bottom: 25px;

    position:relative;
    z-index: 1;
  }

  #main-background {
    width: 100vw;
    height: 450px;
    background-color: #B3E3C3;
    position:absolute;
    top: 0;
    z-index: 0;
  }

  section {
    width: 65%;
    padding: 2%;
  }

  article {
    margin-bottom: 60px;
  }


  table tr th {
    width: 200px;
    padding: 20px;
  }

  table tr td {
    width: 400px;
    padding: 20px;
  }

  aside {
    width: 35%;
    padding: 2%;
  }

  .title {
    margin-left: 10px;
    font-weight: bold;
  }

  .contents {
    padding: 20px;
  }

  #heart {
    position: absolute;
    right: 10px;
  }
</style>