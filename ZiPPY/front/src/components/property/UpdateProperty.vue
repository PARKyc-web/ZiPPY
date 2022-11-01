<template>
  <form id="updateProperty">
    <v-row justify="center">
      <v-dialog v-model="dialog" persistent max-width="600px">
        <template v-slot:activator="{ on, attrs }">
          <v-btn class="mx-2" fab depressed dark x-small color="#B3E3C3" v-bind="attrs" v-on="on">
            <v-icon dark>
              mdi-pencil
            </v-icon>
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
                  <v-select :items="['판매중', '거래 완료']" label="거래 상태" required v-model="state" ref="productState">
                  </v-select>
                </v-col>
                <v-col cols="12" sm="6">
                  <!-- <v-file-input label='이미지' v-model="houseDetail[0].mainImg" ref="mainImg" id="mainImg" ></v-file-input> -->
                  <v-file-input :label="houseDetail[0].mainImg" id="mainImg"></v-file-input>
                </v-col>
                <!-- <v-col cols="12" sm="6">
                  <v-file-input label='상세 이미지' multiple small-chips truncate-length="15"></v-file-input>
                </v-col> -->
                <v-col cols="12" sm="6">
                  <v-text-field label="건물명" required v-model="houseDetail[0].houseName" ref="houseName"></v-text-field>
                </v-col>
                <!-- 거래 유형, 금액 -->
                <v-col cols="12" sm="6">
                  <v-select :items="['매매', '전세', '월세']" label="거래 유형" required v-model="houseDetail[0].saleType"
                    ref="saleType"></v-select>
                </v-col>
                <v-col cols="12" sm="6">
                  <v-text-field label="금액(만원)" hint="월세의 경우 보증금/월세로 입력하세요." required v-model="houseDetail[0].price"
                    ref="price"></v-text-field>
                </v-col>
                <v-col cols="12" sm="6">
                  <v-text-field label="시군구" hint="대구광역시 중구 남일동" required v-model="houseDetail[0].sigungu" ref="sigungu">
                  </v-text-field>
                </v-col>
                <v-col cols="12" sm="6">
                  <v-text-field label="도로명 주소" hint="중앙대로 403" required :value="houseDetail[0].streetAddress"
                    ref="streetAddress"></v-text-field>
                </v-col>
                <v-col cols="12" sm="6" md="4">
                  <v-select :items="['아파트', '원룸', '투룸']" label="집종류" required v-model="houseDetail[0].houseType"
                    ref="houseType"></v-select>
                </v-col>
                <v-col cols="12" sm="6" md="4">
                  <v-text-field label="층수" required v-model="houseDetail[0].floor" ref="floor"></v-text-field>
                </v-col>
                <v-col cols="12" sm="6" md="4">
                  <v-text-field label="전용면적 m²" required v-model="houseDetail[0].areaExclusive" ref="areaExclusive">
                  </v-text-field>
                </v-col>
                <v-col cols="12" sm="6">
                  <v-select :items="['동향', '서향', '남향', '북향']" label="방향" required v-model="houseDetail[0].houseFace"
                    ref="houseFace"></v-select>
                </v-col>
                <v-col cols="12" sm="6">
                  <v-text-field label="준공년도" required v-model="houseDetail[0].constructionYear" ref="constructionYear">
                  </v-text-field>
                </v-col>
                <v-col cols="12" sm="6" md="4">
                  <v-select :items="['가능', '불가능']" label="주차 여부" required v-model="parking" ref="parking"></v-select>
                </v-col>
                <v-col cols="12" sm="6" md="4">
                  <v-text-field label="방 수" :value="houseDetail[0].roomCnt" ref="roomCnt"></v-text-field>
                </v-col>
                <v-col cols="12" sm="6" md="4">
                  <v-text-field label="욕실 수" required v-model="houseDetail[0].bathCnt" ref="bathCnt"></v-text-field>
                </v-col>
                <v-col cols="12">
                  <v-chip-group multiple active-class="green--text" v-model="selectedTags">
                    <v-chip v-for="tag in tags" :key="tag" filter outlined>
                      {{ tag }}
                    </v-chip>
                  </v-chip-group>
                </v-col>
                <v-col cols="12">
                  <v-textarea label="상세 설명" v-model="houseDetail[0].detailContents" ref="detailContents"></v-textarea>
                </v-col>
              </v-row>
            </v-container>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="green lighten-1" text @click="updateProperty">
              저장
            </v-btn>
            <v-btn color="green lighten-1" text @click="dialog = false">
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
      houseDetail: [],
      tags: [
        '조용한',
        '역세권',
        '학세권',
        '신축',
        '즉시입주',
        '풀옵션',
        '올수리',
        '탑층',
        '공원뷰',
        '수변뷰',
      ],
      selectedTags: [],
      parking: '',
      ////
      state: ''
    }),
    props: {
      productId: Number
    },
    created() {
      axios({
          url: "/zippy/property/houseDetail",
          method: "GET",
          params: {
            productId: this.productId
          }
        }).then(response => {
          this.houseDetail = response.data;
          this.state = this.houseDetail[0].productState == 1 ? '판매중' : '거래 완료';
          this.parking = this.houseDetail[0].parking == 1 ? '가능' : '불가능';
          let temp = this.houseDetail[0].tags.split('/');
          for (let i = 0; i < temp.length; i++) {
            if (temp[i] != '') {
              this.selectedTags.push(parseInt(temp[i]));
            }
          }
        })
        .catch(error => {
          console.log(error);
        })
    },
    methods: {
      updateProperty() {

        var formData = new FormData(document.querySelector('#updateProperty'));
        var mainImg = document.getElementById("mainImg");

        let tags = '';
        this.selectedTags.sort();
        Array.from(this.selectedTags).forEach(element => {
          if (element != 'NaN') {
            tags += element + '/';

          }
        });54
        console.log(tags);

        formData.append("productState", this.state == '판매중' ? 1 : 0);
        formData.append("image", mainImg.files[0]);
        formData.append("houseName", this.$refs.houseName.value);
        formData.append("saleType", this.$refs.saleType.value);
        formData.append("price", this.$refs.price.value);
        formData.append("sigungu", this.$refs.sigungu.value);
        formData.append("houseType", this.$refs.houseType.value);
        formData.append("floor", this.$refs.floor.value);
        formData.append("areaExclusive", this.$refs.areaExclusive.value);
        formData.append("roomCnt", this.$refs.roomCnt.value);
        formData.append("detailContents", this.$refs.detailContents.value);
        formData.append("productId", this.houseDetail[0].productId);
        //
        formData.append("streetAddress", this.$refs.streetAddress.value);
        formData.append("houseFace", this.$refs.houseFace.value);
        formData.append("parking", this.parking == '가능' ? 1 : 0);
        formData.append("bathCnt", this.$refs.bathCnt.value);
        formData.append("constructionYear", this.$refs.constructionYear.value);
        formData.append("tags", tags);

        let result = 0;
        axios({
            url: "/zippy/property/updateHouseProduct",
            method: "POST",
            data: formData
          }).then(response => {
            Swal.fire({
              icon: 'success',
              title: '수정되었습니다.',
              showConfirmButton: false,
              timer: 1500
            })

            this.$router.go();
          })
          .catch(error => {
            console.log(error);
          })
      }
    }
  }
</script>