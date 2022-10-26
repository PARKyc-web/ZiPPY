<template>
  <v-toolbar flat style="border-bottom:1px solid #D6D6D6"> <!--elevation="4", margin-bottom:5px; -->
    <div style="width: 75vw">
      <v-btn-toggle v-model="data.houseType" tile color="green lighten-1" group>
        <v-btn value="아파트" @click="clickHouseType('아파트')">아파트</v-btn>
        <v-btn value="빌라" @click="clickHouseType('빌라')">빌라</v-btn>
        <v-btn value="오피스텔" @click="clickHouseType('오피스텔')">오피스텔</v-btn>
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
                    <v-select :items="['전체', '매매', '전세', '월세']" label="거래유형" required v-model="data.saleType">
                    </v-select>
                  </v-col>
                  <v-col cols="12" sm="6">
                    <v-select :items="['전체', '1년 이내', '5년 이내', '10년 이내', '15년 이내']" label="준공년도" required
                      v-model="constructionYear">
                    </v-select>
                  </v-col>
                  <v-col cols="12" class="py-2">
                    <p>면적</p>
                    <div style="width:100%">

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
                    </div>
                  </v-col>

                  <v-col class="px-4">
                    <p>금액(만원)</p>
                    <v-range-slider v-model="data.range" :max="maxPrice" :min="minPrice" step="1000" hide-details
                      class="align-center" color="#B3E3C3">
                      <template v-slot:prepend>
                        <v-text-field v-model="data.range[0]" class="mt-0 pt-0" hide-details single-line type="number"
                          style="width: 60px" @change="$set(range, 0, $event)"></v-text-field>
                      </template>
                      <template v-slot:append>
                        <v-text-field v-model="data.range[1]" class="mt-0 pt-0" hide-details single-line type="number"
                          style="width: 60px" @change="$set(range, 1, $event)"></v-text-field>
                      </template>
                    </v-range-slider>
                  </v-col>



                  <v-col cols="12">

                    <v-chip-group multiple active-class="green--text" v-model="selectedTags">
                      <v-chip v-for="tag in tags" :key="tag" filter outlined>
                        {{ tag }}
                      </v-chip>
                    </v-chip-group>

<!-- 
                    <v-autocomplete :items="['조용한', '역세권', '학세권', '태그1', '태그2', '태그3', '태그4', '태그5', '태그6']" label="태그"
                      multiple v-model="tags"></v-autocomplete> -->
                  </v-col>
                </v-row>
                <p>선택한 필터 목록 {{ tags }}</p>
              </v-container>
            </template>
          </v-card-text>
          <v-divider></v-divider>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="green lighten-1" text @click="save">
              저장
            </v-btn>
            <v-btn color="green lighten-1" text @click="dialog = false">
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
        constructionYear: '전체',
        sizeLevel: [0, 6],
        houseSize: 0,
        tags: [
        '조용한',
        '역세권',
        '학세권',
        '신축',
        '즉시입주',
        '풀옵션',
        '올수리',
        '탑층',
        '공원뷰',
        '수변뷰',
      ],
      selectedTags: [],
        minPrice: 0,
        maxPrice: 150000,
        data: {
          houseType: '아파트',
          saleType: '전체',
          minSize: 0,
          maxSize: 999999,
          tagsToString: '',
          year: 1000,
          sigungu: '',
          range: [0, 150000],
        },

      }
    },
    methods: {
      save() {
        this.dialog = false;
        console.log('선택된', this.selectedTags);

        this.data.year = new Date().getFullYear();
        switch (this.constructionYear) {
          case '1년 이내':
            this.data.year -= 1;
            break;
          case '5년 이내':
            this.data.year -= 5;
            break;
          case '10년 이내':
            this.data.year -= 10;
            break;
          case '15년 이내':
            this.data.year -= 15;
            break;
          default:
            this.data.year -= 1000;
            break;
        }
        switch (parseInt(this.houseSize)) {
          case 0: // 전체 
            this.data.minSize = 0;
            this.data.maxSize = 10000;
            break;
          case 1: // 10평 이하
            this.data.minSize = 0;
            this.data.maxSize = 8;
            break;
          case 2: // 10평대
            this.data.minSize = 8;
            this.data.maxSize = 41;
            break;
          case 3: // 20평대
            this.data.minSize = 41;
            this.data.maxSize = 74;
            break;
          case 4: // 30평대
            this.data.minSize = 74;
            this.data.maxSize = 107;
            break;
          case 5: // 40평대
            this.data.minSize = 107;
            this.data.maxSize = 138;
            break;
          case 6: // 50평대
            this.data.minSize = 138;
            this.data.maxSize = 173;
            break;
          case 7: // 60평 이상
            this.data.minSize = 173;
            this.data.maxSize = 10000;
            break;
        }

        let tagsToString = '';
        for (let i = 0; i < this.tags.length; i++) {
          this.data.tagsToString += this.tags[i] + '/';
        }

        this.sendData();
      },
      sendData() {
        this.data.sigungu = this.sigungu;
        if (this.data.saleType == '전체') {
          this.data.saleType = '';
        }
        this.$emit("search-property-list", this.data);
        console.log(this.sigungu);
      },
      clickHouseType(houseType) {
        this.data.houseType = houseType;
        this.sendData();
      }
    }
  }
</script>