<template>
  <div id="container" v-if="this.houseDetail[0]">
    <v-carousel cycle height="400" hide-delimiter-background show-arrows-on-hover>
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
        <h4>가격정보</h4>
        <table>
          <tr>
            <th>{{this.houseDetail[0].saleType}}</th>
            <td>{{this.houseDetail[0].price}}</td>
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
        <h4>상세정보</h4>
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
        <h4>위치 및 주변 시설</h4>
        <p>{{this.houseDetail[0].sigungu}} {{this.houseDetail[0].streetAddress}}</p>
        <basic-marker-map :address="houseDetail[0].streetAddress" :name="houseDetail[0].houseName" />
        <hr>
      </article>
      <article>
        <h4>시세/실거래가</h4>
        아파트의 경우
        <hr>
      </article>
      <article>
        <h4>상세 설명</h4>
        <p>{{this.houseDetail[0].detailContents}}</p>
      </article>
    </section>

    <aside>
      <div id="app">
        <v-app id="inspire">
          <v-card class="mx-auto my-12" max-width="374" style="width: 100%;">
            <v-card-text>
              <v-row align="center" class="mx-0">
                <div>매물번호 {{this.houseDetail[0].productId}}</div>
              </v-row>
              <v-card-title>{{this.houseDetail[0].houseName}} | {{this.houseDetail[0].saleType}} {{this.houseDetail[0].price}}</v-card-title>
              <div class="my-4 text-subtitle-1">
                {{this.houseDetail[0].sigungu}}
              </div>
              <div class="my-4 text-subtitle-1">
                {{this.houseDetail[0].streetAddress}}
              </div>
              <div class="my-4 text-subtitle-1">
                {{this.houseDetail[0].houseType}}
              </div>
              <div class="my-4 text-subtitle-1">
                {{this.houseDetail[0].areaExclusive}}m²
              </div>
              <div class="my-4 text-subtitle-1">
                {{this.houseDetail[0].floor}}층
              </div>
              <hr>
              <v-card-title><a href="">지피공인중개법인</a> </v-card-title>
              <v-btn block color="primary" elevation="2">문의하기</v-btn>
            </v-card-text>

          </v-card>
        </v-app>
      </div>
    </aside>

    <!-- END container -->
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
        // productId: 650
      }
    },
    created() {
      axios({
          url: "http://localhost:8090/zippy/property/houseDetail",
          methods: "GET",
          params: {
            productId: 650 // this.productId
          }
        }).then(response => {
          // 성공했을 때
          console.log('success!');
          console.log(response.data);
          this.houseDetail = response.data;
        })
        .catch(error => {
          // 에러가 났을 때
          console.log('fail!');
          console.log(error);

        });
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

  v-carousel {
    width: 100%;
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
</style>