<template>
  <v-toolbar elevation="4" style="margin-bottom:5px">
    <div style="width: 75vw">
      <h6 style="display: inline-block; margin-left: 30px; margin-top:10px; margin-right: 10px">아파트</h6>
      <h6 style="display: inline-block; margin-top:10px; margin-right: 10px">빌라</h6>
      <h6 style="display: inline-block; margin-top:10px; margin-right: 10px">오피스텔</h6>
  
      <v-dialog v-model="dialog" scrollable max-width="600px">
        <template v-slot:activator="{ on, attrs }">
          <!-- 필터 추가 버튼 -->
          <v-btn icon color="primary" dark v-bind="attrs" v-on="on">
            <v-icon>mdi-playlist-plus</v-icon>
          </v-btn>
        </template>
        <v-card>
          <v-card-title>필터 선택</v-card-title>
          <v-divider></v-divider>
          <!--  -->
          <v-card-text>
            <template>
              <v-container fluid>
                <v-row>
                  <v-col cols="12" sm="6">
                    <v-select :items="['전체', '매매', '전세', '월세']" label="거래유형" required v-model="saleType"></v-select>
                  </v-col>
                  <v-col cols="12" sm="6">
                    <v-select :items="['전체', '1년 이내', '5년 이내', '10년 이내', '15년 이내']" label="준공년도" required
                      v-model="constructionYear">
                    </v-select>
                  </v-col>
                  <v-col class="12">
                    <v-range-slider :tick-labels="houseSize" v-model="sizeLevel" min="0" max="6" ticks="always"
                      tick-size="4" ref="sizeLevel">
                      <template v-slot:thumb-label="props">
                      </template>
                    </v-range-slider>
                  </v-col>
                  <v-col cols="12">
                    <v-autocomplete :items="['조용한', '역세권', '학세권', '태그1', '태그2', '태그3', '태그4', '태그5', '태그6']" label="태그"
                      multiple v-model="tags"></v-autocomplete>
                  </v-col>
                </v-row>
                <p>선택한 필터 목록 {{ tags }}</p>
              </v-container>
            </template>
          </v-card-text>
          <v-divider></v-divider>
          <v-card-actions>
            <v-btn color="blue darken-1" text @click="save">
              저장
            </v-btn>
            <v-btn color="blue darken-1" text @click="dialog = false">
              닫기
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>



    </div>
    <div style="width: 22vw">
    <v-text-field hide-details prepend-icon="mdi-magnify" single-line placeholder=" 지역명 검색" @keyup.enter="sendData"
      v-model="sigungu"></v-text-field>
    </div>



  </v-toolbar>
</template>

<script>
  export default {
    props: ['sigungu'],
    data() {
      return {
        dialogm1: '',
        dialog: false,
        tags: '',
        houseSize: [
          '~10평',
          '10평대',
          '20평대',
          '30평대',
          '40평대',
          '50평대',
          '60평~',
        ],
        saleType: '전체',
        constructionYear: '전체',
        sizeLevel: [0, 6]
      }
    },
    methods: {
      save() {
        this.dialog = false;

        let year = new Date().getFullYear();
        switch (this.constructionYear) {
          case '1년 이내':
            year -= 1;
            break;
          case '5년 이내':
            year -= 5;
            break;
          case '10년 이내':
            year -= 10;
            break;
          case '15년 이내':
            year -= 15;
            break;
          default:
            year -= 1000;
            break;
        }

        let minSize = this.$refs.sizeLevel.value[0];
        let maxSize = this.$refs.sizeLevel.value[1];

        switch (minSize) {
          case 0:
            minSize = 0;
            break;
          case 1:
            minSize = 33;
            break;
          case 2:
            minSize = 66;
            break;
          case 3:
            minSize = 99;
            break;
          case 4:
            minSize = 132;
            break;
          case 5:
            minSize = 165;
            break;
          case 6:
            minSize = 10000;
            break;
        }

        switch (maxSize) {
          case 0:
            minSize = 0;
            break;
          case 1:
            maxSize = 33;
            break;
          case 2:
            maxSize = 66;
            break;
          case 3:
            maxSize = 99;
            break;
          case 4:
            maxSize = 132;
            break;
          case 5:
            maxSize = 165;
            break;
          case 6:
            maxSize = 10000;
            break;
        }

        console.log(minSize, maxSize);

        let tagsToString = '';
        for (let i = 0; i < this.tags.length; i++) {
          tagsToString += this.tags[i] + '/';
        }
        console.log(tagsToString);

      },
      sendData() {
        this.$emit("get-property-list", this.sigungu);
      },
    }
  }
</script>