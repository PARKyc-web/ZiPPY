<template>
  <div class="mx-auto pt-10" id="cart-con">
    <h3 style="margin:30px">CS내역</h3>

    <v-card>
      <v-data-table :headers="headers" :items="qnas">
        <template v-slot:item.proMainImg="{ item }">
          <v-img class="ma-5" width="150px" height="150px" :src="'/zippy/common/img/shop/'+item.proMainImg"
            @click="goDetail(item.cartPno)"></v-img>
        </template> -->
        <template v-slot:item.answerStatus="{ item }">
          <div v-if="item.Status==0">
            답변대기
          </div>
          <div v-if="item.answerStatus==1">
            답변완료
          </div>
        </template>
        <template v-slot:item.question="{ item }">
          <div class="mb-10">
            <div style="width:500px; padding:20px 0 20px 0">
              {{item.question}}
            </div>
            <div v-if="item.answerStatus==0" style="margin-bottom:20px">
              <v-textarea filled v-model="answer[item.qnaNo]" height="130" no-border name="input-7-4" label="답변을 입력해주세요"
                style="font-size:1em">
              </v-textarea>
              <v-btn small depressed style="float:right" @click="updateQna(item.qnaNo)">등록</v-btn>
            </div>
            <div v-if="item.answerStatus==1">
              <v-icon class="mr-4 mb-2">mdi-subdirectory-arrow-right</v-icon>
              {{item.answer}}
            </div>
          </div>
        </template>
      </v-data-table>
    </v-card>

  </div>
</template>

<script>
  import axios from 'axios';
  import swal from 'sweetalert2';

  export default {
    data() {
      return {
        qnas: [],
        answer: [],
        headers: [{
            text: '작성일',
            align: '',
            sortable: true,
            value: 'questionDate',
          },
          {
            text: '문의유형',
            value: 'questionCate'

          },
          {
            text: '문의상태',
            value: 'answerStatus',
          },
          {
            text: '상품이름',
            value: 'proName'
          },
          {
            text: '고객이메일',
            sortable: false,
            value: 'email'
          },
          {
            text: '문의내용',
            sortable: false,
            value: 'question'
          }
        ],
      }
    },
    created() {
      //판매자 qna 리스트 조회
      axios({
        url: "/zippy/shop/getSellerQnaList",
        method: "GET",
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
    },
    methods: {
      updateQna(no) {
        axios({
          url: "/zippy/shop/updateQnaAnswer",
          method: "POST",
          data: {
            answer: this.answer[no],
            qnaNo: no
          }
        }).then(res => {
        }).catch(error => {
          console.log(error);
        })
      }
    }
  }
</script>

<style scoped>
  #cart-con {
    width: 70%;
  }

  .v-toolbar__title {
    font-weight: bold;
  }

  .v-application a {
    text-decoration: none;
    color: rgba(0, 0, 0, 0.87) !important;
  }

  .v-application a:hover {
    text-decoration: underline;
  }

  .v-btn {
    font-weight: bold;
  }
</style>