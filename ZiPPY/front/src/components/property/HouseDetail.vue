<template>
  <div id="container">
    <v-carousel
      cycle
      height="400"
      hide-delimiter-background
      show-arrows-on-hover
    >
      <v-carousel-item v-for="(slide, i) in slides" :key="i">
        <v-sheet :color="colors[i]" height="100%">
          <v-row class="fill-height" align="center" justify="center">
            <div class="text-h2">{{ slide }} Slide</div>
          </v-row>
        </v-sheet>
      </v-carousel-item>
    </v-carousel>

    <section>
      <article>
        <h4>가격정보</h4>
        <table>
          <tr>
            <th>월세</th>
            <td>500/35</td>
          </tr>
          <tr>
            <th>관리비</th>
            <td>매월 8만원</td>
          </tr>
          <tr>
            <th>주차</th>
            <td>불가능</td>
          </tr>
          <tr>
            <th>한달 예상 주거비용</th>
            <td>
              43만원<br />
              <small>월세 + 관리비 별도 금액으로 부과되는 항목 제외</small>
            </td>
          </tr>
        </table>
        <hr />
      </article>
      <article>
        <h4>상세정보</h4>
        <table>
          <tr>
            <th>방종류</th>
            <td>원룸</td>
          </tr>
          <tr>
            <th>층수</th>
            <td>반지층</td>
          </tr>
          <tr>
            <th>전용면적</th>
            <td>11.28m²</td>
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
            <td>2022년</td>
          </tr>
        </table>
        <hr />
      </article>
      <article>
        <h4>위치 및 주변 시설</h4>
        <p>서울시 은평구 진관동 66</p>
        <basic-marker-map />
        <hr />
      </article>
      <article>
        <h4>시세/실거래가</h4>
        아파트의 경우
        <hr />
      </article>
      <article>
        <h4>상세 설명</h4>
        <p>
          보라매역 도보 3분 역세권, 저렴한 풀옵션 원룸입니다. ★ 특징 ★ ✔ 7호선
          보라매역 도보 3분거리 역세권! ✔ 공동현관 보안 등으로 안전합니다. ✔
          즉시입주 풀옵션 매물로 너무 좋습니다. ✔ 카페, 편의점, 병원, 은행이
          가까워 살기 좋습니다.
        </p>
      </article>
    </section>

    <aside>
      <div id="app">
        <v-app id="inspire">
          <v-card class="mx-auto my-12" max-width="374" style="width: 100%">
            <v-card-text>
              <v-row align="center" class="mx-0">
                <div>매물번호 2358</div>
              </v-row>
              <v-card-title>월세 500/35</v-card-title>
              <div class="my-4 text-subtitle-1">
                서울특별시 동작구 대방동 415-52
              </div>
              <div class="my-4 text-subtitle-1">원룸</div>
              <div class="my-4 text-subtitle-1">11.28m²</div>
              <div class="my-4 text-subtitle-1">반지층</div>
              <hr />
              <v-card-title>지피공인중개법인</v-card-title>
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

export default {
  components: {
    BasicMarkerMap,
  },
  data() {
    return {
      colors: [
        "indigo",
        "warning",
        "pink darken-2",
        "red lighten-1",
        "deep-purple accent-4",
      ],
      slides: ["First", "Second", "Third", "Fourth", "Fifth"],
    };
  },
};
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
