<template>
  <div class="mx-auto pt-5" id="cart-con">
    <h3 style="margin:30px">문의내역</h3>

    <v-card>
      <v-data-table :headers="headers" :items="qnas" :items-per-page="5">
          <!-- 이미지 -->
          <template v-slot:item.PRO_MAIN_IMG="{ item }">
          <img class="ma-5" :src="'/zippy/common/img/shop/'+item.PRO_MAIN_IMG" width="150" height="150">
        </template>
        <!-- 별점 -->
        <!-- 답변상태 -->
        <template v-slot:item.ANSWER_STATUS="{ item }">
          <div v-if="item.ANSWER_STATUS==0">
            답변대기
          </div>
          <div v-if="item.ANSWER_STATUS==1">
            답변완료
          </div>
        </template>
        <!-- 답변내용 -->
        <template v-slot:item.QUESTION="{ item }">
          <div class="mb-10">
            <div style="width:500px; padding:20px 0 20px 0">
              {{item.QUESTION}}
            </div>
            <div v-if="item.ANSWER_STATUS==1">
              <v-icon class="mr-4 mb-2">mdi-subdirectory-arrow-right</v-icon>
              {{item.ANSWER}}
            </div>
          </div>
        </template>
      </v-data-table>
    </v-card>

  </div>
</template>

<script>
  import axios from 'axios';

  export default {
    data() {
      return {
        singleSelect: false,
        qnas: [],
        headers: [{
            text: '문의상태',
            align: '',
            sortable: false,
            value: 'ANSWER_STATUS',
          },
          {
            text: '',
            value: 'PRO_MAIN_IMG'
          },
          {
            text: '문의유형',
            value: 'QUESTION_CATE'
          },
          {
            text: '상품명',
            value: 'PRO_NAME'
          },
          {
            text: '문의내용',
            value: 'QUESTION'
          },
          {
            text: '작성일',
            value: 'QUESTION_DATE'
          }
        ]
      }
    },
    created() {
      //내 후기 조회
      axios({
        url: "/zippy/shop/getMyQna",
        method: "POST",
        params: {
          email: this.$store.state.loginInfo.email
        }
      }).then(res => {
        console.log(res);
        this.qnas = res.data;
        console.log(this.qnas);
      }).catch(error => {
        console.log(error);
      })
    }
  };
</script>

<style scoped>
  #cart-con {
    width: 75%;
    padding-bottom:200px;
  }

  .v-toolbar__title {
    font-weight: bold;
  }

  .v-application a {
    text-decoration: none;
    color: rgba(0, 0, 0, 0.87) !important;
  }
  .v-btn {
    font-weight: bold;
  }

  span:hover{
    text-decoration: underline;
    cursor: pointer;
  }
  
</style>