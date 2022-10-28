<template>
  <form id="insertProperty" style="width:1200px; margin: 0 auto">
<div id="container">
  <v-row>
        <v-card-title>
          <h4 class="title">
            매물 등록하기
          </h4>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12" sm="6">
                <v-file-input label='대표 이미지' v-model="mainImg" id="mainImage"></v-file-input>
              </v-col>
              <v-col cols="12" sm="6">
                <v-file-input label='상세 이미지' multiple small-chips truncate-length="15"></v-file-input>
              </v-col>
              <v-col cols="12">
                <v-text-field label="건물명" v-model="houseName"></v-text-field>
              </v-col>
              <!-- 거래 유형, 금액 -->
              <v-col cols="12" sm="6">
                <v-select :items="['매매', '전세', '월세']" label="거래 유형" required v-model="saleType"></v-select>
              </v-col>
              <v-col cols="12" sm="6">
                <v-text-field label="금액(만원)" hint="월세의 경우 보증금/월세로 입력하세요." required v-model="price"></v-text-field>
              </v-col>
              <v-col cols="12" sm="6">
                <v-text-field label="시군구" hint="대구광역시 중구 남일동" required v-model="sigungu"></v-text-field>
              </v-col>
              <v-col cols="12" sm="6">
                <v-text-field label="도로명 주소" hint="중앙대로 403" required v-model="streetAddress"></v-text-field>
              </v-col>
              <v-col cols="12" sm="6" md="4">
                <v-select :items="['아파트', '빌라', '오피스텔']" label="집종류" required v-model="houseType"></v-select>
              </v-col>
              <v-col cols="12" sm="6" md="4">
                <v-text-field label="층수" required v-model="floor"></v-text-field>
              </v-col>
              <v-col cols="12" sm="6" md="4">
                <v-text-field label="전용면적 m²" required v-model="areaExclusive"></v-text-field>
              </v-col>
              <v-col cols="12" sm="6">
                <v-select :items="['동향', '서향', '남향', '북향']" label="방향" required v-model="houseFace"></v-select>
              </v-col>
              <v-col cols="12" sm="6">
                <v-text-field label="준공년도" required v-model="constructionYear"></v-text-field>
              </v-col>
              <v-col cols="12" sm="6" md="4">
                <v-select :items="['가능', '불가능']" label="주차 여부" required v-model="parking"></v-select>
              </v-col>
              <v-col cols="12" sm="6" md="4">
                <v-text-field label="방 수" v-model="roomCnt"></v-text-field>
              </v-col>
              <v-col cols="12" sm="6" md="4">
                <v-text-field label="욕실 수" required v-model="bathCnt"></v-text-field>
              </v-col>

              <v-col cols="12">
                <v-chip-group multiple active-class="green--text" v-model="selectedTags" style="margin: 0 auto">
                  <v-chip v-for="tag in tags" :key="tag" filter outlined>
                    {{ tag }}
                  </v-chip>
                </v-chip-group>
              </v-col>

              <v-col cols="12">
                <v-textarea label="상세 설명" v-model="detailContents"></v-textarea>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="click">
            등록
          </v-btn>
        </v-card-actions>        
  </v-row>
</div>
</form>
</template>

<script>
  import axios from 'axios';
  import Swal from 'sweetalert2';

  export default {
    props: {
      email: String
    },
    data() {
      return {
        dialog2: false,
        mainImg: '',
        houseName: '',
        saleType: '',
        price: '',
        sigungu: '',
        houseType: '',
        floor: '',
        areaExclusive: '',
        roomCnt: '',
        detailContents: '',
        streetAddress: '',
        houseFace: '',
        parking: '',
        bathCnt: '',
        constructionYear: '',
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
      }
    },
    methods: {
      click() {
        console.log('선택된건', this.selectedTags);
        let tags = '';
        Array.from(this.selectedTags).forEach(element => {
          tags += element + '/';
        });

        this.insert(tags);
      },
      insert(tags) {
        
        var formData = new FormData(document.querySelector('#insertProperty'));
        let result = 0;

        var temp = document.getElementById("mainImage");

        formData.append("email", this.$store.state.loginInfo.email);
        formData.append("detailContents", this.detailContents);
        formData.append("roomCnt", this.roomCnt);
        formData.append("areaExclusive", this.areaExclusive);
        formData.append("floor", this.floor);
        formData.append("houseType", this.houseType);
        formData.append("sigungu", this.sigungu);
        formData.append("price", this.price);
        formData.append("saleType", this.saleType);  
        formData.append("houseName", this.houseName)
        formData.append("images", temp.files[0]);     

        axios({
            url: "/zippy/property/insertHouseProduct",
            method: "POST",
            data : formData
          }).then(response => {
            // 성공했을 때
            result++;
            if (result == 2) {
              Swal.fire({
                icon: 'success',
                title: '등록되었습니다.',
                showConfirmButton: false,
                timer: 1500
              })
            }
          })
          .catch(error => {
            // 에러가 났을 때
            console.log(error);
          })

        axios({
            url: "/zippy/property/insertHouseDetail",
            method: "POST",
            params: {
              streetAddress: this.streetAddress,
              houseFace: this.houseFace,
              parking: this.parking == '가능' ? 1 : 0,
              bathCnt: this.bathCnt,
              constructionYear: this.constructionYear,
              tags: tags
            }
          }).then(response => {
            // 성공했을 때
            result++;
            if (result == 2) {
              Swal.fire({
                icon: 'success',
                title: '등록되었습니다.',
                showConfirmButton: false,
                timer: 1500
              })
            }
          })
          .catch(error => {
            // 에러가 났을 때
            console.log(error);
          })

      }
    }
  }
</script>

<style scoped>
  .title {
    margin-left: 20px;
    font-weight: bold;
  }
</style>