<template>
  <div class="mx-auto pt-5" id="cart-con">
    <h3 style="margin:30px">후기내역</h3>

    <v-card>
      <v-data-table :headers="headers" :items="reviews" :items-per-page="5">
        <!-- 이미지 -->
        <!-- 별점 -->
        <template v-slot:item.PRO_MAIN_IMG="{ item }">
          <img class="ma-5" :src="'/zippy/common/img/shop/'+item.PRO_MAIN_IMG" width="150" height="150">
        </template>
        <!-- 별점 -->
        <template v-slot:item.REVIEW_CONTENT="{ item }">
          <div class="pa-5">
            <v-rating v-model="item.TOTAL_RATING" half-increments background-color="#64c481 lighten-3" color="#64c481"
              small readonly></v-rating>
            <div class="ml-2">{{item.REVIEW_CONTENT}}</div>
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
        reviews: [],
        headers: [{
            text: '',
            align: '',
            sortable: false,
            value: 'PRO_MAIN_IMG',
          },
          {
            text: '상품명',
            sortable: false,
            value: 'PRO_NAME'
          },
          {
            text: '후기내용',
            sortable: false,
            value: 'REVIEW_CONTENT'
          },
          {
            text: '작성일',
            value: 'REVIEW_DATE'
          }
        ]
      }
    },
    created() {
      //내 후기 조회
      axios({
        url: "/zippy/shop/getMyRv",
        method: "POST",
        params: {
          email: this.$store.state.loginInfo.email
        }
      }).then(res => {
        console.log(res);
        this.reviews = res.data;
        console.log(this.reviews);
      }).catch(error => {
        console.log(error);
      })
    }
  };
</script>

<style scoped>
  #cart-con {
    width: 70%;
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

  span:hover {
    text-decoration: underline;
    cursor: pointer;
  }
</style>