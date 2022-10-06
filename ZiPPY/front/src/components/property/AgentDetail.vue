<template>
  <div id="container">
    <div id="agent_profile">
      <table>
        <tr>
          <td>
            <h2>지피타워부동산중개법인</h2>
          </td>
          <td>
            <v-btn elevation="2">수정하기</v-btn>
          </td>
        </tr>
      </table>
      <div id="agent_profile_left">
        <h3>김부식
        </h3>
        <h3>02-123-1234
        </h3>
        <h3>인사말</h3>
        <p>Lorem ipsum dolor, sit amet consectetur adipisicing elit.
          Reprehenderit, possimus asperiores. Aspernatur aliquam ut iure
          molestias voluptas, quos officia voluptates nisi cumque modi amet
          porro libero quidem, molestiae ratione ea?</p>
      </div>
      <div id="agent_profile_right">
        <table>
          <tr>
            <td>
              <h3>오시는 길</h3>
            </td>
            <td>
              <p>서울 중랑구 면목로 92길</p>
            </td>
          </tr>
        </table>
        <!-- 지도 -->
        <basic-marker-map/>
      </div>
    </div>
    <hr>
    <div>
      <h2>
        매물 목록 <i class="bi bi-plus-circle-fill"></i>
      </h2>
      <div class="row">
        <v-card style="width: 45%; margin: 1%">
          <table>
            <tr>
              <td>여기에 이미지</td>
              <td>
                <h4>월세1000/50</h4>
                <p>대전광역시 중구 문화동</p>
                <p>49.32m²▫ 2층</p>
                <p>매물 상세 설명</p>
              </td>
            </tr>
          </table>
        </v-card>

        <v-card style="width: 45%; margin: 1%">
          <table>
            <tr>
              <td>여기에 이미지</td>
              <td>
                <h4>월세1000/50</h4>
                <p>대전광역시 중구 문화동</p>
                <p>49.32m²▫ 2층</p>
                <p>매물 상세 설명</p>
              </td>
            </tr>
          </table>
        </v-card>

        <v-card style="width: 45%; margin: 1%">
          <table>
            <tr>
              <td>여기에 이미지</td>
              <td>
                <h4>월세1000/50</h4>
                <p>대전광역시 중구 문화동</p>
                <p>49.32m²▫ 2층</p>
                <p>매물 상세 설명</p>
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

  export default {
    components: {
      BasicMarkerMap,
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