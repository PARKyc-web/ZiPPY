<template>
  <form id="shopInsert">
    <div>
      <div class="mx-auto pt-10" style="width:485px">
        <h5 style="font-weight:bold">상품등록</h5>
        <!-- 상품입력 테이블 -->
        <hr>
        <table id="insertTable" width="100%">
          <tbody>
            <tr>
              <td style="font-weight:bold">상품명<span>*</span></td>
              <td>
                <b-form-input name="proName" placeholder="상품명을 입력해주세요"></b-form-input>
              </td>
            </tr>
            <tr>
              <td style="font-weight:bold">상품가격<span>*</span></td>
              <td>
                <b-form-input name="proPrice" placeholder="상품가격을 입력해주세요"></b-form-input>
              </td>
            </tr>
            <tr>
              <td style="font-weight:bold">배송비<span>*</span></td>
              <td>
                <b-form-input name="deliveryCost" placeholder="배송비를 입력해주세요"></b-form-input>
              </td>
            </tr>
            <tr>
              <td style="font-weight:bold">카테고리<span>*</span></td>
              <td>
                <v-select name="category" :items="cates" item-text="cates" item-value="cates" label="카테고리" return-object
                  dense outlined width="350" height="30"></v-select>
              </td>
            </tr>
            <td style="font-weight:bold; position:relative">
              <div class="mt-3" style="position:absolute; top:0">상품설명</div>
            </td>
            <td>
              <v-textarea width="350" solo name="proInfo" label="상품설명을 등록해주세요"></v-textarea>
            </td>
            <tr>
              <td style="font-weight:bold">대표이미지<span>*</span></td>
              <td style="padding-top:0; padding-bottom:0">
                <v-file-input name="image" accept="image/*" label="대표이미지" style="width:350px">
                </v-file-input>
              </td>
            </tr>
            <tr>
              <td style="font-weight:bold">상세이미지</td>
              <td style="padding-top:0; padding-bottom:0">
                <v-file-input name="images" multiple label="상세이미지" style="width:350px"></v-file-input>
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
          <div class="pl-3" style="width:100px; display:inline-block">옵션이름</div>
          <div style="padding:10px; width:354px; display:inline-block">
            <input data-v-656fe1d6 type="text" class="form-control" v-model="option[index].optName">
          </div>
          <!-- 추가 가격-->
          <div class="pl-3" style="width:100px;  display:inline-block">추가가격</div>
          <div style="padding:10px; width:354px;  display:inline-block">
            <input data-v-656fe1d6 type="text" class="form-control" v-model="option[index].optPrice">
          </div>
        </div>
        <!-- 상품입력 테이블 -->
        <input data-v-656fe1d6 hidden type="text" class="form-control" name="email" value="shop@mail.com"></input>
        <hr>
        <div>
          <div style="width:150px; margin-top:50px; margin-bottom:120px" class="mx-auto">
            <v-btn outlined color="#64c481" class="mr-3" @click="insertPro">
              등록
            </v-btn>
            <v-btn depressed color=#B3E3C3>
              취소
            </v-btn>
          </div>
        </div>
      </div>
    </div>
  </form>
</template>주문

<script>
  import axios from 'axios';

  export default {
    data() {
      return {
        //담을 상품
        product: {},
        //상세이미지
        images: [],
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
      insertPro() {
        var formData = new FormData(document.querySelector('#shopInsert'));
        formData.append('option', new Blob([JSON.stringify(this.option)] , {type: "application/json"}));

        console.log(formData);
        axios({
          url: "/shop/insertPro",
          method: "POST",
          data: formData
        }).then(res => {
          console.log(res);
        }).catch(error => {
          console.log(error);
        })
      },
      inputName() {

      },
      inputPrice() {

      }
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

  #option input {
    width: 265px !important;
  }

  #option {
    font-size: smaller
  }

  .v-btn {
    width: 20px;
    height: 20px;
  }

  span {
    color: #64c481;
  }
</style>