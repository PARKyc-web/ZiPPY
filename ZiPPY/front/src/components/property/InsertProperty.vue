<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="600px">
      <template v-slot:activator="{ on, attrs }">
        <v-btn color="primary" dark v-bind="attrs" v-on="on">
          Open Dialog
        </v-btn>
      </template>
      <v-card>
        <v-card-title>
          <span class="text-h5">매물 등록하기</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12" sm="6">
                <v-file-input label='대표 이미지'></v-file-input>
              </v-col>
              <v-col cols="12" sm="6">
                <v-file-input label='상세 이미지'></v-file-input>
              </v-col>
              <v-col cols="12">
                <v-text-field label="건물명" required></v-text-field>
              </v-col>
              <!-- 거래 유형, 금액 -->
              <v-col cols="12" sm="6">
                <v-select :items="['매매', '전세', '월세']" label="거래 유형" required></v-select>
              </v-col>
              <v-col cols="12" sm="6">
                <v-text-field label="금액" hint="월세의 경우 보증금/월세로 입력하세요." required></v-text-field>
              </v-col>
              <v-col cols="12" sm="6">
                <v-text-field label="시군구" hint="대구광역시 중구 남일동" required></v-text-field>
              </v-col>
              <v-col cols="12" sm="6">
                <v-text-field label="도로명 주소" hint="중앙대로 403" required></v-text-field>
              </v-col>
              <v-col cols="12" sm="6" md="4">
                <v-select :items="['아파트', '원룸', '투룸']" label="집종류" required></v-select>
              </v-col>
              <v-col cols="12" sm="6" md="4">
                <v-text-field label="층수" required></v-text-field>
              </v-col>
              <v-col cols="12" sm="6" md="4">
                <v-text-field label="전용면적 m²" required></v-text-field>
              </v-col>
              <v-col cols="12" sm="6">
                <v-select :items="['동향', '서향', '남향', '북향']" label="방향" required></v-select>
              </v-col>
              <v-col cols="12" sm="6">
                <v-text-field label="건축년도" required></v-text-field>
              </v-col>
              <v-col cols="12" sm="6" md="4">
                <v-select :items="['가능', '불가능']" label="주차 여부" required></v-select>
              </v-col>
              <v-col cols="12" sm="6" md="4">
                <v-text-field label="방 수"></v-text-field>
              </v-col>
              <v-col cols="12" sm="6" md="4">
                <v-text-field label="욕실 수" required></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-autocomplete :items="['조용한', '역세권', '학세권', '태그1', '태그2', '태그3', '태그4', '태그5', '태그6']" label="태그"
                  multiple></v-autocomplete>
              </v-col>
              <v-col cols="12">
                <v-textarea label="상세 설명">dsd</v-textarea>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="dialog = false">
            닫기
          </v-btn>
          <v-btn color="blue darken-1" text @click="insert">
            등록
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
  export default {
    data: () => ({
      dialog: false,
    }),
    methods: {
      insert() {
        this.dialog = false;
      }
    }
  }
</script>