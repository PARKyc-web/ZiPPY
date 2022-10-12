<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="600px">
      <template v-slot:activator="{ on, attrs }">
        <v-btn color="primary" dark v-bind="attrs" v-on="on">
          수정하기
        </v-btn>
      </template>
      <v-card>
        <v-card-title>
          <span class="text-h5">회원 정보 수정</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12" sm="6">
                <v-text-field label="이메일" :value="profile.email" readonly></v-text-field>
              </v-col>
              <v-col cols="12" sm="6">
                <v-file-input label="프로필 사진" required></v-file-input>
              </v-col>
              <v-col cols="12" sm="6">
                <v-text-field label="공인중개법인명" required :value="profile.compName"></v-text-field>
              </v-col>
              <v-col cols="12" sm="6">
                <v-text-field label="주소" required :value="profile.compAddress"></v-text-field>
              </v-col>
              <v-col cols="12" sm="6">
                <v-text-field label="이름" :value="profile.ceoName"></v-text-field>
              </v-col>
              <v-col cols="12" sm="6">
                <v-text-field label="전화번호" required :value="profile.phone"></v-text-field>
              </v-col>
              <v-col cols="12" sm="6">
                <v-file-input label="사업자등록증" required :value="profile.businessImg"></v-file-input>
              </v-col>
              <v-col cols="12" sm="6">
                <v-file-input label="중개등록증" required :value="profile.brokerImg"></v-file-input>
              </v-col>
              <v-col cols="12" sm="6">
                <v-text-field label="사업자등록번호" required :value="profile.businessId"></v-text-field>
              </v-col>
              <v-col cols="12" sm="6">
                <v-text-field label="중개등록번호" required :value="profile.brokerId"></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-textarea label="인사말" required :value="profile.compIntro"></v-textarea>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="dialog = false">
            닫기
          </v-btn>
          <v-btn color="blue darken-1" text @click="dialog = false">
            저장
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
  import axios from 'axios';

  export default {
    data: () => ({
      dialog: false,

    }),
    props: {
      profile: Object
    },
    created() {
      // axios({
      //     url: "http://localhost:8090/zippy/property/",
      //     methods: "GET",
      //     params: {
      //       email: this.email
      //     }
      //   }).then(response => {
      //     // 성공했을 때
      //     console.log(' success!');
      //     console.log(response.data);
      //     this.agentProfile = response.data;
      //   })
      //   .catch(error => {
      //     // 에러가 났을 때
      //     console.log(' fail!');
      //     console.log(error);
      //   })
    },
  }
</script>