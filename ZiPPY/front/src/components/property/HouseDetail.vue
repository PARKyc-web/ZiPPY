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
                <h6 style="margin-left: 20px;">{{this.houseDetail[0].sigungu}} {{this.houseDetail[0].streetAddress}}
                </h6>
                <br>
                <h5 style="margin-left: 20px;"><v-icon large>mdi-border-outside</v-icon>{{this.houseDetail[0].areaExclusive}}m² · <v-icon large>mdi-stairs</v-icon> {{this.houseDetail[0].floor}}층
                </h5>
                <hr>
                <v-card-title style="font-weight: bold;" @click="goAgentDetail">{{this.houseDetail[0].compName}} 
                <v-icon color="#B3E3C3" style="margin-left: 3px">mdi-information-outline</v-icon>
                </v-card-title>

                <!-- 채팅 버튼 -->
                <v-row justify="center">
                  <v-dialog v-model="dialog" persistent max-width="600px">
                    <template v-slot:activator="{ on, attrs }">
                      <v-btn color="#B3E3C3" dark v-bind="attrs" @click="openChat" style="width: 90%; margin-bottom: 10px ">
                        <b>채팅으로 문의하기</b>
                      </v-btn>
                    </template>
                    <v-card>
                        <v-btn style="width: 100%" color="#B3E3C3" text @click="dialog = false">
                          채팅창 닫기
                        </v-btn>
                      <chat-tap style="width: 100%; height: 100%; padding: 0"></chat-tap>
                    </v-card>
                  </v-dialog>
                </v-row>
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
  import {
    oneHundredMillion
  } from '../../assets/property/propertyPrice';
  import ChatTap from '../../views/chat/chatTap.vue';
  import Swal from 'sweetalert2';

  export default {
    components: {
      BasicMarkerMap,
      CategotyMap,
      ReportButton,
      WishButton,
      ChatTap
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
        dialog: false,
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
    methods: {
      goAgentDetail() {
        this.$router.push({
          name: 'AgentDetail',
          query: {
            email: this.houseDetail[0].email
          }
        })
      },
      openChat() {
        if (this.$store.state.loginInfo) {
          this.dialog = true;
        } else {
          Swal.fire({
            icon: 'warning',
            title: '로그인이 필요한 기능입니다.',
            showConfirmButton: false,
            timer: 1500
          })
        }
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

    position: relative;
    z-index: 1;
  }

  #main-background {
    width: 100vw;
    height: 450px;
    background-color: #B3E3C3;
    position: absolute;
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