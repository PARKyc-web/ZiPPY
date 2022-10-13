<template>
  <v-row justify="center">
    <v-dialog v-model="dialog" persistent max-width="600px">
      <template v-slot:activator="{ on, attrs }">
        <v-btn color="primary" dark v-bind="attrs" v-on="on">
          수정
        </v-btn>
      </template>
      <v-card>
        <v-card-title>
          <span class="text-h5">매물 정보 수정</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12">
                <v-select :items="['판매중', '거래 완료']" label="거래 상태" required :value="houseDetail[0].productState==1 ? '판매중' : '거래 완료'" ref="productState"></v-select>
              </v-col>
              <v-col cols="12" sm="6">
                <v-file-input label='대표 이미지' v-model="houseDetail[0].mainImg" ref="mainImg"></v-file-input>
              </v-col>
              <v-col cols="12" sm="6">
                <v-file-input label='상세 이미지'></v-file-input>
              </v-col>
              <v-col cols="12">
                <v-text-field label="건물명" required v-model="houseDetail[0].houseName" ref="houseName"></v-text-field>
              </v-col>
              <!-- 거래 유형, 금액 -->
              <v-col cols="12" sm="6">
                <v-select :items="['매매', '전세', '월세']" label="거래 유형" required v-model="houseDetail[0].saleType" ref="saleType"></v-select>
              </v-col>
              <v-col cols="12" sm="6">
                <v-text-field label="금액" hint="월세의 경우 보증금/월세로 입력하세요." required v-model="houseDetail[0].price" ref="price"></v-text-field>
              </v-col>
              <v-col cols="12" sm="6">
                <v-text-field label="시군구" hint="대구광역시 중구 남일동" required v-model="houseDetail[0].sigungu" ref="sigungu"></v-text-field>
              </v-col>
              <v-col cols="12" sm="6">
                <v-text-field label="도로명 주소" hint="중앙대로 403" required :value="houseDetail[0].streetAddress" ref="streetAddress"></v-text-field>
              </v-col>
              <v-col cols="12" sm="6" md="4">
                <v-select :items="['아파트', '원룸', '투룸']" label="집종류" required v-model="houseDetail[0].houseType" ref="houseType"></v-select>
              </v-col>
              <v-col cols="12" sm="6" md="4">
                <v-text-field label="층수" required v-model="houseDetail[0].floor" ref="floor"></v-text-field>
              </v-col>
              <v-col cols="12" sm="6" md="4">
                <v-text-field label="전용면적 m²" required v-model="houseDetail[0].areaExclusive" ref="areaExclusive"></v-text-field>
              </v-col>
              <v-col cols="12" sm="6">
                <v-select :items="['동향', '서향', '남향', '북향']" label="방향" required v-model="houseDetail[0].houseFace" ref="houseFace"></v-select>
              </v-col>
              <v-col cols="12" sm="6">
                <v-text-field label="준공년도" required v-model="houseDetail[0].constructionYear" ref="constructionYear"></v-text-field>
              </v-col>
              <v-col cols="12" sm="6" md="4">
                <v-select :items="['가능', '불가능']" label="주차 여부" required :value="houseDetail[0].parking==1 ? '가능' : '불가능'" ref="parking"></v-select>
              </v-col>
              <v-col cols="12" sm="6" md="4">
                <v-text-field label="방 수" :value="houseDetail[0].roomCnt" ref="roomCnt"></v-text-field>
              </v-col>
              <v-col cols="12" sm="6" md="4">
                <v-text-field label="욕실 수" required v-model="houseDetail[0].bathCnt" ref="bathCnt"></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-autocomplete :items="['조용한', '역세권', '학세권', '태그1', '태그2', '태그3', '태그4', '태그5', '태그6']" label="태그"
                  multiple v-model="houseDetail[0].tags" ref="tags"></v-autocomplete>
              </v-col>
              <v-col cols="12">
                <v-textarea label="상세 설명" v-model="houseDetail[0].detailContents" ref="detailContents"></v-textarea>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="dialog = false">
            닫기
          </v-btn>
          <v-btn color="blue darken-1" text @click="updateProperty">
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
      houseDetail: [],
    }),
    props: {
      productId : Number
    },
    created() {
      axios({
          url: "http://localhost:8090/zippy/property/houseDetail",
          methods: "GET",
          params: {
            productId: this.productId // this.productId
          }
        }).then(response => {
          // 성공했을 때
          console.log('houseDetail success!');
          console.log(response.data);
          this.houseDetail = response.data;
        })
        .catch(error => {
          // 에러가 났을 때
          console.log('houseDetail fail!');
          console.log(error);
        })
    },
    methods: {
      updateProperty() {
        let result = 0; 

        axios({
            url: "http://localhost:8090/zippy/property/updateHouseProduct",
            methods: "PUT",
            params: {
              productState: this.$refs.productState.value =='판매중' ? 1 : 0,
              mainImg: this.$refs.mainImg.value,
              houseName:this.$refs.houseName.value,
              saleType:this.$refs.saleType.value,
              price:this.$refs.price.value,
              sigungu:this.$refs.sigungu.value,
              houseType: this.$refs.houseType.value,
              floor: this.$refs.floor.value,
              areaExclusive: this.$refs.areaExclusive.value,
              roomCnt: this.$refs.roomCnt.value,
              detailContents: this.$refs.detailContents.value,
              productId: this.houseDetail[0].productId
            }
          }).then(response => {
            // 성공했을 때
            console.log('updateHouseProduct success!');
            result++;
            if(result == 2) alert('수정이 완료되었습니다.');
          })
          .catch(error => {
            // 에러가 났을 때
            console.log('updateHouseProduct fail!');
            console.log(error);
          })

        axios({
            url: "http://localhost:8090/zippy/property/updateHouseDetail",
            methods: "PUT",
            params: {
              streetAddress: this.$refs.streetAddress.value,
              houseFace:this.$refs.houseFace.value,
              parking:this.$refs.parking.value == '가능' ? 1 : 0,
              bathCnt:this.$refs.bathCnt.value,
              constructionYear:this.$refs.constructionYear.value,
              tags: this.$refs.tags.value,
              productId: this.houseDetail[0].productId
            }
          }).then(response => {
            // 성공했을 때
            console.log('updateHouseDetail success!');
            result++;
            if(result == 2) alert('수정이 완료되었습니다.');
          })
          .catch(error => {
            // 에러가 났을 때
            console.log('updateHouseDetail fail!');
            console.log(error);
          })
        
      }
    }
  }
</script>