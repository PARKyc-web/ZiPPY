<template>
  <div class="wrap">
    <h2>자세한 견적을 받을 수 있도록 사진을 첨부해주세요.</h2>
    <form id="img-form">


      <v-stepper v-model="e6" vertical>

        <v-stepper-step :complete="e6 > 1" step="1" color="success lighten-2">
          각 방입구에서 사진을 촬영해주세요.
          <small>(거실 포함)</small>
        </v-stepper-step>
        <v-stepper-content step="1">
          <v-card color="white" class="mb-12" height="400px">
            <img src="@/assets/enter.jpg">
            <div id="step1">
                <!-- 사진 첨부 -->
              <v-file-input outlined v-model="moveImage.files1" placeholder="Upload your documents" label="사진첨부"
                multiple prepend-icon="mdi-paperclip">
                <template v-slot:selection="{ text }">
                  <!-- 첨부한 파일명 미리보기 chip -->
                  <v-chip small label color="success lighten-2">
                    {{ text }}
                  </v-chip>
                </template>
              </v-file-input>
            </div>
          </v-card>
          <v-btn color="grey lighten-1" @click="e6 = 2">
            다음
          </v-btn>
          <v-btn color="#B3E3C3 lighten-2" @click="e6 = 1">
            취소
          </v-btn>
        </v-stepper-content>

        <v-stepper-step :complete="e6 > 2" step="2" color="success lighten-2">
          각 방 중앙에서 1, 2, 3, 4면의 사진을 촬영해주세요.
        </v-stepper-step>

        <v-stepper-content step="2">
          <v-card color="white lighten-5" class="mb-12" height="400px">
            <img src="@/assets/center.jpg">

            <div id="step2">
              <v-file-input outlined v-model="moveImage.files2" placeholder="Upload your documents" label="사진첨부"
                multiple prepend-icon="mdi-paperclip">
                <template v-slot:selection="{ text }">
                  <v-chip small label color="success lighten-2">
                    {{ text }}
                  </v-chip>
                </template>
              </v-file-input>
            </div>

          </v-card>
          <v-btn color="grey lighten-1" @click="e6 = 3">
            다음
          </v-btn>
          <v-btn color="#B3E3C3 lighten-2" @click="e6 = 1">
            이전
          </v-btn>
        </v-stepper-content>

        <v-stepper-step :complete="e6 > 3" step="3" color="success lighten-2">
          옷장이나 냉장고 같이 내부구조 확인이 필요한 짐의 내부를 촬영해주세요.
        </v-stepper-step>

        <v-stepper-content step="3">
          <v-card color="white lighten-5" class="mb-12" height="400px">
            <img src="@/assets/inside.jpg">

            <div id="step3">
              <v-file-input outlined v-model="moveImage.files3" placeholder="Upload your documents" label="사진첨부"
                multiple prepend-icon="mdi-paperclip">
                <template v-slot:selection="{ text }">
                  <v-chip small label color="success lighten-2">
                    {{ text }}
                  </v-chip>
                </template>
              </v-file-input>
            </div>

          </v-card>
          <v-btn color="grey lighten-1" @click="e6 = 4">
            완료
          </v-btn>
          <v-btn color="#B3E3C3 lighten-2" @click="e6 = 2">
            이전
          </v-btn>
        </v-stepper-content>

      </v-stepper>

      <div class="next-btn">
        <v-btn @click="un_final_signIn()" color="success lighten-3" elevation="11">다음페이지로</v-btn>
      </div>
    </form>
  </div>
</template>

<script>
  import swal from 'sweetalert2';
  export default {
    props: [
      'moveDetail', 'moveEstimateType', 'moveType', 'moveInfo', 'moveDate', 'moveAddress', 'movingMemo'
    ],
    data() {
      return {
        e6: 1,
        moveImage: {
          files1: [],
          files2: [],
          files3: [],
        },
        img: require("@/assets/box.jpg")
      }
    },

    methods: {
      un_final_signIn: function () {
        console.log(this.moveInfo);
        console.log(this.moveImage);

        if (this.moveImage.files1 == "" || this.moveImage.files2 == "" || this.moveImage.files3 == "") {
          swal.fire("사진을 첨부하셔야 정확한 견적을 받을 수 있습니다. 사진을 첨부해주세요.");
        } else {
          this.$router.push({
            //다음페이지로 보내기 
            name: "moveUntactCheck",
            params: {
              moveImage: this.moveImage,
              moveDetail: this.moveDetail,
              moveEstimateType: this.moveEstimateType,
              moveInfo: this.moveInfo,
              moveType: this.moveType,
              moveDate: this.moveDate, 
              moveAddress: this.moveAddress,
              movingMemo : this.movingMemo
             
            }
          })
        }

      }
    }
  }
</script>

<style scoped>
  @font-face {
    font-family: 'GmarketSans';
    font-weight: 500;
    font-style: normal;
    src: url('https://cdn.jsdelivr.net/gh/webfontworld/gmarket/GmarketSansMedium.eot');
    src: url('https://cdn.jsdelivr.net/gh/webfontworld/gmarket/GmarketSansMedium.eot?#iefix') format('embedded-opentype'),
         url('https://cdn.jsdelivr.net/gh/webfontworld/gmarket/GmarketSansMedium.woff2') format('woff2'),
         url('https://cdn.jsdelivr.net/gh/webfontworld/gmarket/GmarketSansMedium.woff') format('woff'),
         url('https://cdn.jsdelivr.net/gh/webfontworld/gmarket/GmarketSansMedium.ttf') format("truetype");
    font-display: swap;
} 
* {
  font-family: 'GmarketSans';
}

  h2 {
    text-align: center;
    padding-top: 100px;
    padding-bottom: 100px;
  }
  #img-form{
    padding: 0 100px 100px 100px;
  }

  .v-stepper {
    width: 100%;
    text-align: center;
  }

  .v-file {
    width: 100%;
    text-align: center;

  }

  .v-input {

    padding-top: 20px;
    width: 600px;
  }

  .next-btn {
    text-align: center;
    padding: 100px;
  }

  #step1 {
    float: right;
    margin-top: 90px;
    margin-right: 200px;
  }

  #step2 {
    float: right;
    margin-top: 90px;
    margin-right: 200px;
  }

  #step3 {
    float: right;
    margin-top: 90px;
    margin-right: 200px;
  }
</style>