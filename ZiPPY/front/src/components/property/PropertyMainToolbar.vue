<template>
  <v-toolbar elevation="4" style="margin-bottom:5px">
    <div style="width: 75vw">
      <v-btn-toggle v-model="houseType" tile color="green lighten-1" group>
        <v-btn value="아파트">아파트</v-btn>
        <v-btn value="빌라">빌라</v-btn>
        <v-btn value="오피스텔">오피스텔</v-btn>
      </v-btn-toggle>
      <v-dialog v-model="dialog" scrollable max-width="600px">
        <template v-slot:activator="{ on, attrs }">
          <!-- 필터 추가 버튼 -->
          <v-btn icon color="primary" dark v-bind="attrs" v-on="on">
            <v-icon color="#B3E3C3">mdi-playlist-plus</v-icon>
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
                  <v-col cols="12" class="py-2">
                    <p>면적</p>
                    <v-btn-toggle v-model="houseSize" tile color="green lighten-1" group class="d-flex flex-row">
                      <v-btn class="flex-grow-1" value="0">전체</v-btn>
                      <v-btn class="flex-grow-1" value="1">10평 이하</v-btn>
                      <v-btn class="flex-grow-1" value="2">10평대</v-btn>
                      <v-btn class="flex-grow-1" value="3">20평대</v-btn>
                    </v-btn-toggle>
                    <v-btn-toggle v-model="houseSize" tile color="green lighten-1" group class="d-flex flex-row">
                      <v-btn class="flex-grow-1" value="4">30평대</v-btn>
                      <v-btn class="flex-grow-1" value="5">40평대</v-btn>
                      <v-btn class="flex-grow-1" value="6">50평대</v-btn>
                      <v-btn class="flex-grow-1" value="7">60평 이상</v-btn>
                    </v-btn-toggle>
                  </v-col>
                  <v-col cols="12" class="px-4">
                    <v-range-slider v-model="priceRange" :max="data.priceMax" :min="data.priceMin" hide-details
                      class="align-center" step="100" thumb-label :thumb-size="50">
                      <template v-slot:prepend>
                        <v-text-field :value="priceRange[0]" class="mt-0 pt-0" hide-details single-line type="number"
                          style="width: 60px" @change="$set(priceRange, 0, $event)"></v-text-field>
                      </template>
                      <template v-slot:append>
                        <v-text-field :value="priceRange[1]" class="mt-0 pt-0" hide-details single-line type="number"
                          style="width: 60px" @change="$set(priceRange, 1, $event)"></v-text-field>
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
            <v-spacer></v-spacer>
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
        v-model="sigungu" color="#B3E3C3"></v-text-field>
    </div>



  </v-toolbar>
</template>

<script>
  export default {
    props: ['sigungu'],
    data() {
      return {
        dialog: false,
        tags: '',
        sizeLevel: [0, 6],
        priceRange: [0, 999999],
        houseSize: 0,
        data: {
          houseType: '아파트',
          saleType: '전체',
          constructionYear: '전체',
          minSize: 0,
          maxSize: 0,
          priceMin: 0,
          priceMax: 999999,
        }
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

        switch (houseSize) {
          case 0: // 전체 
            data.minSize = 0;
            data.maxSize = 10000;
            break;
          case 1: // 10평 이하
            data.minSize = 0;
            data.maxSize = 33;
            break;
          case 2: // 10평대
            data.minSize = 33;
            data.maxSize = 66;
            break;
          case 3: // 20평대
            data.minSize = 66;
            data.maxSize = 99;
            break;
          case 4: // 30평대
            data.minSize = 99;
            data.maxSize = 132;
            break;
          case 5: // 40평대
            data.minSize = 132;
            data.maxSize = 165;
            break;
          case 6: // 50평대
            data.minSize = 165;
            data.maxSize = 198;
            break;
          case 7: // 60평 이상
            data.minSize = 198;
            data.maxSize = 10000;
            break;
        }

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