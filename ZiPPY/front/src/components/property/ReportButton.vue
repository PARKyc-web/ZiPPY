<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="290">
      <template v-slot:activator="{ on, attrs }">
        <div id="reportButton" v-bind="attrs" v-on="on">
          <v-icon>mdi-alert-circle-outline</v-icon>신고하기
        </div>
      </template>
      <v-card>
        <v-card-title class="text-h7">
          <b>신고하기</b>
        </v-card-title>
        <v-card-text>중개사로부터 안내 받은 내용을 알려주세요. 바로 반영하겠습니다.</v-card-text>
        <v-card-text>
          <v-radio-group v-model="column" column>
            <v-radio label="매물이 나갔습니다" value="radio-1" color="green lighten-3"></v-radio>
            <v-radio label="표시된 정보와 다릅니다" value="radio-2" color="green lighten-3"></v-radio>
          </v-radio-group>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="green accent-3" text @click="dialog = false">
            취소
          </v-btn>
          <v-btn color="green accent-3" text @click="report">
            신고
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
  import Swal from 'sweetalert2';

  export default {
    data() {
      return {
        dialog: false,
      }
    },
    methods: {
      report() {
        if (this.$store.state.loginInfo) {
          Swal.fire({
            icon: 'success',
            title: '신고가 완료되었습니다.',
            showConfirmButton: false,
            timer: 1500
          })

          console.log(this.productId);
        } else {
          Swal.fire({
            icon: 'warning',
            title: '로그인이 필요한 기능입니다.',
            showConfirmButton: false,
            timer: 1500
          })
        }
        this.dialog = false;
      }
    }
  }
</script>

<style scoped>
  #reportButton {
    text-align: right;
    margin-top: 5px;
  }
</style>