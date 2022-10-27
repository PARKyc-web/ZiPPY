<template>
  <form id="shopInsert">
    <div>
      <div class="mx-auto pt-5" style="width:485px">
        <h3 style="margin:30px">상품수정</h3>
        <!-- 상품입력 테이블 -->
        <hr>
        <table id="insertTable" width="100%">
          <tbody>
            <tr>
              <td style="font-weight:bold">상품번호</td>
              <td>
                <b-form-input name="proNo" readonly :value="product.proNo"></b-form-input>
              </td>
            </tr>
            <tr>
              <td style="font-weight:bold">상품명<span>*</span></td>
              <td>
                <b-form-input name="proName" :value="product.proName"></b-form-input>
              </td>
            </tr>
            <tr>
              <td style="font-weight:bold">상품가격<span>*</span></td>
              <td>
                <b-form-input name="proPrice" :value="product.proPrice"></b-form-input>
              </td>
            </tr>
            <tr>
              <td style="font-weight:bold">배송비<span>*</span></td>
              <td>
                <b-form-input name="deliveryCost" :value="product.deliveryCost"></b-form-input>
              </td>
            </tr>
            <tr>
              <td style="font-weight:bold">카테고리<span>*</span></td>
              <td>
                <v-select name="category" :items="cates" item-text="cates" item-value="cates" label="카테고리" return-object
                  dense outlined width="350" height="30" :value="product.category"></v-select>
              </td>
            </tr>
            <td style="font-weight:bold; position:relative">
              <div class="mt-3" style="position:absolute; top:0">상품설명</div>
            </td>
            <td>
              <v-textarea width="350" solo name="proInfo" :value="product.proInfo"></v-textarea>
            </td>
            <tr>
              <td style="font-weight:bold">대표이미지<span>*</span></td>
              <td style="padding-top:0; padding-bottom:0">
                <v-file-input name="image" accept="image/*" :label="product.proMainImg" style="width:350px">
                </v-file-input>
              </td>
            </tr>
            <tr>
              <td style="font-weight:bold">상세이미지</td>
              <td style="padding-top:0; padding-bottom:0">
                <v-file-input name="images" multiple :label="imgs" style="width:350px"></v-file-input>
              </td>
            </tr>
            <tr>
              <td style="font-weight:bold">상품옵션</td>
              <!-- 추가 삭제 버튼 -->
              <div style="width:68px" class="mt-2 ml-auto pr-4">
                <!-- 추가 -->
                <div class="mr-2" style="display:inline-block">
                  <v-btn fab depressed dark color="#D6D6D6" @click="addOpt">
                    <v-icon dark>
                      mdi-plus
                    </v-icon>
                  </v-btn>
                </div>
                <!-- 추가 -->
                <!-- 삭제 -->
                <div style="display:inline-block">
                  <v-btn fab depressed dark small color="#D6D6D6" @click="delOpt">
                    <v-icon dark>
                      mdi-minus
                    </v-icon>
                  </v-btn>
                </div>
                <!-- 삭제 -->
              </div>
              <!-- 추가 삭제 버튼 -->
            </tr>
          </tbody>
        </table>
        <div v-for="(item, index) in option" :key="item.optName" style="width:485px">
          <!-- 옵션명 -->
          <div class="pl-3 mt-5" style="width:100px; display:inline-block">옵션이름</div>
          <div style="padding:10px; width:354px; display:inline-block">
            <input data-v-656fe1d6 type="text" class="form-control" v-model="option[index].optName">
          </div>
          <!-- 추가 가격-->
          <div class="pl-3" style="width:100px;  display:inline-block">추가가격</div>
          <div class="mb-5" style="padding:10px; width:354px;  display:inline-block">
            <input data-v-656fe1d6 type="text" class="form-control" v-model="option[index].optPrice">
          </div>
        </div>
        <!-- 상품입력 테이블 -->
        <input data-v-656fe1d6 hidden type="text" class="form-control" name="email" value="shop@mail.com">
        <hr>
        <div>
          <div style="width:150px; margin-top:50px; margin-bottom:120px" class="mx-auto">
            <v-btn outlined color="#64c481" class="mr-3" @click="updatePro">
              수정
            </v-btn>
            <v-btn depressed color=#B3E3C3 @click="goProList">
              취소
            </v-btn>
          </div>
        </div>
      </div>
    </div>
  </form>
</template>

<script>
  import axios from 'axios';
  import swal from 'sweetalert2';

  export default {
    data() {
      return {
        //담을 상품
        product: {},
        //null 체크용
        pro: [],
        //상세이미지
        images: [],
        //라벨에 표현용 상세이미지
        imgs: '',
        //카테고리 종류
        cates: ['침대', '토퍼/매트리스', '소파', '서랍/수납장', '책장', '의자', '거울', '조명', '소품'],
        //옵션
        option: []
      }
    },
    //옵션 추가
    methods: {
      addOpt() {
        this.option.push({
          optName: '',
          optPrice: ''
        })
      },
      //옵션 삭제
      delOpt() {
        this.option.splice(this.option.length - 1, 1);
      },
      updatePro() {
        //null 검사
        if (!this.product.proName || !this.product.proPrice || !this.product.deliveryCost || !this.product.category || !
          this.product
          .proMainImg) {
          swal.fire({
            position: 'top-end',
            icon: 'warning',
            title: '상품수정시 필수 정보를 입력해주세요.',
            showConfirmButton: true
          })
          return;
        }
        var formData = new FormData(document.querySelector('#shopInsert'));
        formData.append('option', "" + JSON.stringify(this.option));

        axios({
          url: "/zippy/shop/updatePro",
          method: "POST",
          data: formData
        }).then(res => {
          console.log(res);
          swal.fire({
            position: 'top-end',
            icon: 'success',
            title: '상품수정이 완료되었습니다.',
            showConfirmButton: true
          })
        }).catch(error => {
          swal.fire({
            position: 'top-end',
            icon: 'error',
            title: '상품수정시 필요한 정보를 모두 입력해주세요.',
            showConfirmButton: true
          })
        })
      },
      goProList() {
        this.$router.go(-1);
      }
    },
    created() {
      //단건조회
      axios({
          url: "/zippy/shop/detail",
          method: "GET",
          params: {
            pno: this.$route.query.proNo
          }
        }).then(res => {
          console.log(res);
          this.product = res.data;
          console.log(this.product);
        }).catch(error => {
          console.log(error);
        }),
        //이미지조회
        axios({
          url: "/zippy/shop/img",
          method: "GET",
          params: {
            pno: this.$route.query.proNo
          }
        }).then(res => {
          console.log(res);
          this.images = res.data;
          this.imgs = this.images.toString();
        }).catch(error => {
          console.log(error);
        }),
        //옵션조회
        axios({
          url: "/zippy/shop/opt",
          method: "GET",
          params: {
            pno: this.$route.query.proNo
          }
        }).then(res => {
          console.log(res);
          this.option = res.data;
          console.log(this.option)
        }).catch(error => {
          console.log(error);
        })
    }
  }
</script>

<style scoped>
  input {
    width: 350px !important;
    height: 30px !important;
  }

  td {
    padding: 10px;
  }

  .v-btn {
    width: 20px;
    height: 20px;
  }

  span {
    color: #64c481;
  }
</style>