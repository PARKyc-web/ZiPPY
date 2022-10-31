<template>
<form id="updateProperty">
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
                <v-file-input label="프로필 사진" required v-model="profile.profilePic" ref="profilePic"></v-file-input>
              </v-col>
              <v-col cols="12" sm="6">
                <v-text-field label="공인중개법인명" required v-model="profile.compName" ref="compName" ></v-text-field>
              </v-col>
              <v-col cols="12" sm="6">
                <v-text-field label="주소" required v-model="profile.compAddress" ref="compAddress"></v-text-field>
              </v-col>
              <v-col cols="12" sm="6">
                <v-text-field label="이름" :value="profile.ceoName" readonly></v-text-field>
              </v-col>
              <v-col cols="12" sm="6">
                <v-text-field label="전화번호" required v-model="profile.phone" ref="phone"></v-text-field>
              </v-col>
              <v-col cols="12" sm="6">
                <v-file-input label="사업자등록증" required v-model="profile.businessImg" ref="businessImg"></v-file-input>
              </v-col>
              <v-col cols="12" sm="6">
                <v-file-input label="중개등록증" required v-model="profile.brokerImg" ref="brokerImg"></v-file-input>
              </v-col>
              <v-col cols="12" sm="6">
                <v-text-field label="사업자등록번호" required v-model="profile.businessId" ref="businessId"></v-text-field>
              </v-col>
              <v-col cols="12" sm="6">
                <v-text-field label="중개등록번호" required v-model="profile.brokerId" ref="brokerId"></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-textarea label="인사말" required v-model="profile.compIntro" ref="compIntro"></v-textarea>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="updateAgentProfile">
            저장
          </v-btn>
          <v-btn color="blue darken-1" text @click="dialog = false">
            닫기
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</form>
</template>

<script>
  import axios from 'axios';
  import Swal from 'sweetalert2';

  export default {
    data: () => ({
      dialog: false,
    }),
    props: {
      profile: Object
    },
    methods: {
      updateAgentProfile() {
        axios({
            url: "/zippy/property/updateAgentProfile",
            method: "PUT",
            params: {
              compName: this.$refs.compName.value,
              compIntro:this.$refs.compIntro.value,
              compAddress:this.$refs.compAddress.value,
              profilePic:this.$refs.profilePic.value.name,
              phone:this.$refs.phone.value,
              businessImg: this.$refs.businessImg.value.name,
              brokerImg: this.$refs.brokerImg.value.name,
              businessId: this.$refs.businessId.value,
              brokerId: this.$refs.brokerId.value,
              email: this.profile.email
            }
          }).then(response => {
            // 성공했을 때
              Swal.fire({
                icon: 'success',
                title: '수정되었습니다.',
                showConfirmButton: false,
                timer: 1500
              })
          })
          .catch(error => {
            // 에러가 났을 때
            console.log('updateAgentProfile fail!');
            alert('수정에 실패했습니다.');
            console.log(error);
          })
      }
    }
  }
</script>