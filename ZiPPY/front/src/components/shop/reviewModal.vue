<template>
  <v-dialog v-model="dialog" persistent :retain-focus="false" max-width="500px">
    <template v-slot:activator="{ on, attrs }">
      <v-btn v-if="orderStatus == 1 && !os" id="reviewBtn" depressed color=#B3E3C3 v-bind="attrs" v-on="on" width="100px">
        후기작성
      </v-btn>
      <v-btn v-if="orderStatus == 2" disabled depressed color=#D6D6D6 width="100px">
        작성완료
      </v-btn>
    </template>
    <v-card>
      <v-card-title>
        <span class="text-h6">상품후기</span>
      </v-card-title>
      <v-card-text>
        <v-container style="color:#212529">
          <v-row>
            <!-- 별점선택 -->
            <v-col cols="11">
              <table>
                <tbody>
                  <tr style="border: 0">
                    <td>가격</td>
                    <td>
                      <b-form-rating v-model="rate1" no-border color="#64c481" size="sm"></b-form-rating>
                    </td>
                  </tr>
                  <tr style="border: 0">
                    <td>배송</td>
                    <td>
                      <b-form-rating v-model="rate2" no-border color="#64c481" size="sm"></b-form-rating>
                    </td>
                  </tr>
                  <tr style="border: 0">
                    <td>디자인</td>
                    <td>
                      <b-form-rating v-model="rate3" no-border color="#64c481" size="sm"></b-form-rating>
                    </td>
                  </tr>
                  <tr style="border: 0">
                    <td>내구성</td>
                    <td>
                      <b-form-rating v-model="rate4" no-border color="#64c481" size="sm"></b-form-rating>
                    </td>
                  </tr>
                </tbody>
              </table>
            </v-col>
            <v-col cols="12">
              리뷰
              <v-textarea solo v-model="reviewContent" no-border name="input-7-4" label="리뷰를 입력해주세요">
              </v-textarea>
            </v-col>
          </v-row>
        </v-container>
      </v-card-text>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="#212529" text @click="closeReview()">
          닫기
        </v-btn>
        <v-btn color="#212529" text @click="insertReview()">
          등록
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script>
  import axios from 'axios';
  import swal from 'sweetalert2';
  export default {
    props: ['serviceId', 'orderStatus', 'purNo'],
    data() {
      return {
        //후기 
        reviewContent: '',
        serviceType: 2,
        totalRating: '',
        rate1: '',
        rate2: '',
        rate3: '',
        rate4: '',
        //모달
        dialog: false,
        os: ''
      }
    },
    methods: {
      //모달 닫기
      closeReview() {
        this.rate1 = '',
          this.rate2 = '',
          this.rate3 = '',
          this.rate4 = '',
          this.reviewContent = ''
        this.dialog = false;
      },
      //리뷰 등록
      insertReview() {
        let outside = this;
        if (!this.rate1 || !this.rate2 || !this.rate3 || !this.rate4 || !this.reviewContent) {
          swal.fire({
            icon: 'warning',
            title: '별점과 리뷰를 입력해주세요.',
            showConfirmButton: false,
            timer: 1500
          });
        } else {
          //totalRating 입력
          this.totalRating =
            (Number(this.rate1) +
              Number(this.rate2) +
              Number(this.rate3) +
              Number(this.rate4)) / 4
          axios({
            url: "/zippy/common/addRv",
            method: "POST",
            headers: {
              "Content-Type": "application/json"
            },
            data: {
              email: this.$store.state.loginInfo.email,
              reviewTitle: 0,
              reviewContent: this.reviewContent,
              serviceType: 2,
              serviceId: this.serviceId,
              viewCnt: 0,
              totalRating: this.totalRating,
              rate1: this.rate1,
              rate2: this.rate2,
              rate3: this.rate3,
              rate4: this.rate4,
            }
          }).then(res => {
            //모달창 닫기
            this.dialog = false;
            console.log(this.dialog);
            this.os = 2;
            //상태 변경
            axios({
              url: "/zippy/shop/updateRvStatus",
              method: "POST",
              headers: {
                "Content-Type": "application/json"
              },
              data: {
                purNo: outside.purNo
              }
            }).then(res => {
              console.log(res);
            }).catch(error => {
              console.log(error);
            })

          }).catch(error => {
            console.log(error);
          })
          swal.fire({
            icon: 'success',
            title: '리뷰가 등록되었습니다.',
            showConfirmButton: false,
            timer: 1500
          });
        }
      }
    }
  }
</script>

<style scoped>
  .v-btn {
    font-weight: bold;
  }
</style>