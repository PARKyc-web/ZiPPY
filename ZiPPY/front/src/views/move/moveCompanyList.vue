<template>
  <div class="company-wrap">
    <div class="divv">
      <v-card :loading="loading" class="mx-auto my-12" max-width="374" elevation="10" >
        <template slot="progress">
          <v-progress-linear color="deep-purple" height="10" indeterminate></v-progress-linear>
        </template>

        <!-- <v-img
      height="250"
      src="https://cdn.vuetifyjs.com/images/cards/cooking.png"
    ></v-img> -->

        <v-card-text>
          <div>견적서 번호</div>
        </v-card-text>
        <v-card-title>업체명</v-card-title>

        <v-card-text>
          <v-row text-align="center" class="mx-0">
            <v-rating :value="4.5" color="amber" dense half-increments readonly size="14"></v-rating>

            <div class="grey--text ms-4">
              4.5 (413)
            </div>
          </v-row>
          <v-divider class="mx-4"></v-divider>
          <div>
            02-000-0000
          </div>
          <div>
            서울특별시 송파구 오금동 xxx
          </div>

          <div class="my-4 text-subtitle-1">전문포장이사 20년 경력의 베테랑들이 함께하는 꼼꼼한 이사업체입니다.</div>
        </v-card-text>

        <v-divider class="mx-4"></v-divider>
        <v-card-text>
          <div>견적요청 번호</div>
        </v-card-text>
        <v-card-title>예상견적 : 65만원</v-card-title>

        <!-- <v-card-text>
      <v-chip-group
        v-model="selection"
        active-class="deep-purple accent-4 white--text"
        column
      >
        <v-chip>5:30PM</v-chip>

        <v-chip>7:30PM</v-chip>

        <v-chip>8:00PM</v-chip>

        <v-chip>9:00PM</v-chip>
      </v-chip-group>
    </v-card-text> -->

        <v-card-actions>
          <v-btn color="deep-purple lighten-2" text @click="reserve">
            예약요청
          </v-btn>

          <v-btn color="deep-purple lighten-2" text @click="chat">
            채팅하기
          </v-btn>
        </v-card-actions>
      </v-card>
    </div>


    <!-- <div class="divv">
    <v-card
    :loading="loading"
    class="mx-auto my-12"
    max-width="374"
    elevation="10"
  >
    <template slot="progress">
      <v-progress-linear
        color="deep-purple"
        height="10"
        indeterminate
      ></v-progress-linear>
    </template> -->

    <!-- <v-img
      height="250"
      src="https://cdn.vuetifyjs.com/images/cards/cooking.png"
    ></v-img> -->

    <!-- <v-card-title>업체명</v-card-title>

    <v-card-text>
      <v-row
        text-align="center"
        class="mx-0"
      >
        <v-rating
          :value="4.5"
          color="amber"
          dense
          half-increments
          readonly
          size="14"
        ></v-rating>

        <div class="grey--text ms-4">
          4.5 (413)
        </div>
      </v-row>
      <v-divider class="mx-4"></v-divider>
      <div>
        02-000-0000
      </div>
      <div>
        서울특별시 송파구 오금동 xxx
      </div> 

      <div class="my-4 text-subtitle-1">전문포장이사 20년 경력의 베테랑들이 함께하는 꼼꼼한 이사업체입니다.</div>
    </v-card-text>

    <v-card-title>예상견적 : 65만원</v-card-title> -->

    <!-- <v-card-text>
      <v-chip-group
        v-model="selection"
        active-class="deep-purple accent-4 white--text"
        column
      >
        <v-chip>5:30PM</v-chip>

        <v-chip>7:30PM</v-chip>

        <v-chip>8:00PM</v-chip>

        <v-chip>9:00PM</v-chip>
      </v-chip-group>
    </v-card-text> -->

    <!-- <v-card-actions>
      <v-btn
        color="deep-purple lighten-2"
        text
        @click="reserve"
      >
        예약요청
      </v-btn>

      <v-btn
        color="deep-purple lighten-2"
        text
        @click="chat"
      >
        채팅하기
      </v-btn>
    </v-card-actions>
  </v-card>
</div> -->

  </div>
</template>

<script>
  export default {
    data: () => ({
      loading: false,
      selection: 1,

      list: []
    }),

    methods: {
      reserve() {
        this.loading = true

        setTimeout(() => (this.loading = false), 2000)
      },
      chat() {
        this.loading = true

        setTimeout(() => (this.loading = false), 2000)
      },
    },
  }
</script>

<style scoped>
  .company-wrap {
    display: inline-flex;
    justify-content: space-between;
    margin: 50px;
  }

  .divv {
    padding: 40px;
  }
</style>