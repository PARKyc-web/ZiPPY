<template>
  <v-toolbar dense floating style="z-index: 2;">
    <v-text-field hide-details prepend-icon="mdi-magnify" single-line placeholder=" 지역명 검색"></v-text-field>
    <v-row justify="center">
      <v-dialog v-model="dialog" scrollable max-width="300px">
        <template v-slot:activator="{ on, attrs }">
          <!-- 필터 추가 버튼 -->
          <v-btn icon color="primary" dark v-bind="attrs" v-on="on" style="margin-bottom: 12px;">
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
                <v-checkbox v-model="selected" label="John" value="John"></v-checkbox>
                <v-checkbox v-model="selected" label="Jacob" value="Jacob"></v-checkbox>
                <p>{{ selected }}</p>
              </v-container>
            </template>

          </v-card-text>

          <!--  -->

          <v-divider></v-divider>
          <v-card-actions>
            <v-btn color="blue darken-1" text @click="dialog = false">
              닫기
            </v-btn>
            <v-btn color="blue darken-1" text @click="save">
              저장
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>

  </v-toolbar>
</template>

<script>
  export default {
    data() {
      return {
        dialogm1: '',
        dialog: false,
        selected: [],
      }
    },
    methods : {
      save() {
        this.dialog = false;
      }
    }
  }
</script>