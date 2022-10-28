<template>
  <form id="usedUpdate">        
    <div>
      <div id="container">
        <div>
          <div class="used-main-title">
            <h3>상품 수정</h3>
          </div>
        </div>
        <div class="used-insert-addr">
          <div id="used-addrs">
            <current-position-label @sigu="test"></current-position-label>
          </div>
          <!-- <button id="used-addr"> -->
            <i class="fa-solid fa-location-dot fa-2x"></i>
          <!-- </button> -->
        </div>
        <hr />
        <div>
          <div>
            <div id="used-insert-main">
              <div class="used-insert-img" id="used-insert-img-div">
                <span>이미지</span> {{files.length}}/6
              </div>
              
              <div v-if="!files.length" class="room-file-upload-example-container">
              <div class="room-file-upload-example">
                <div class="room-file-image-example-wrapper"></div>
                <div class="room-file-notice-item room-file-upload-button">
                  <div class="image-box">
                    <label for="file">일반 사진 등록</label>
                    <input type="file" name="images" id="file" ref="files" @change="imageUpload" multiple
                      accept="image/*" />
                  </div>
                </div>
              </div>
            </div>
            <div v-else class="file-preview-content-container">
              <div class="file-preview-container">
                <div v-for="(file, index) in files" :key="index" class="file-preview-wrapper">
                  <div class="file-close-button" @click="fileDeleteButton" :name="file.number">
                    x
                  </div>                  
                  <img v-if="file.type == 'new'" :src="file.image" />
                  <img v-if="file.type == 'old'" :src="'/zippy/common/img/used/' +file.image" />
                  <!-- <input class="hidden_image" id="apapa" name="images" type="image" :src="'/zippy/common/img/used/' +file.image" v-if="file.type == 'old'"> -->
                </div>
                <div class="file-preview-wrapper-upload">
                  <div class="image-box">
                    <label for="file" v-if="files.length<6">추가 사진 등록</label>
                    <input type="file" name="images" id="file" ref="files" @change="imageAddUpload" multiple
                      accept="image/*" />
                  </div>
                </div>
              </div>
            </div>
            </div>
          </div>
        </div>
        <hr />
        <div class="used-insert-img">
          <span>제목
            <input id="used-product-name" name="productName" type="text" placeholder="상품 제목을 2글자 이상 입력해주세요"
              v-model="product.productName" />
          </span>
        </div>
        <hr />
        <div id="used-insert-main">
          <div class="used-insert-img">
            <span>카테고리</span>
          </div>
          <div class="dropdown">
            <div id="used-main-dropbox">
              <v-select @change="dropVal()" class="cate-sell-drop" v-model="select" :items="items" item-text="name" item-value="value"
                :label="product.productCategory" color="#212529" persistent-hint single-line dense width="50">
              </v-select>
            </div>
          </div>
        </div>
        <hr>
        <div id="used-insert-main">
          <div class="used-insert-img">
            <span>판매 여부</span>
          </div>
          <div class="dropdown">
            <div id="used-main-dropbox">
              <v-select @change="dropVal()" class="cate-sell-drop" v-model="select2" :items="isSell" item-text="name" item-value="value"
                :label="product.isSell" color="#212529" persistent-hint single-line dense width="50">
              </v-select>
            </div>
          </div>
        </div>
        <hr />
        <div class="used-insert-img">
          <span>가격<input id="used-insert-price" name="productPrice" type="number" v-model="product.productPrice" />
            원</span>
        </div>
        <hr />
        <div class="used-wish-detailInfo">
          <div class="used-insert-img">
            <span>설명</span>
          </div>
          <div>
            <textarea id="used-insert-textarea" name="productInfo" cols="110" v-model="product.productInfo"
              rows="10"></textarea>
          </div>
        </div>
        <div class="used-insert-submit">
          <button type="button" @click="updateSubmit()">수정</button>
        </div>
      </div>
    </div>
    <input type="hidden" name="productNo" v-model="this.$route.query.pNo">
    <input type="hidden" name="email" v-model="product.email">
    <input type="hidden" name="productLocation" v-model="product.productLocation">
    <input type="hidden" name="productCategory" v-model="product.productCategory">
    <input type="hidden" name="views" v-model="product.views">
    <input type="hidden" name="productDate" v-model="product.productDate">
    <input type="hidden" name="isSell" v-model="product.isSell">
  </form>
</template>

<script>
  import axios from 'axios';
  import navBar from '@/components/used/navBar.vue';
  import CurrentPositionLabel from '@/components/used/CurrentPositionLabel.vue';
  import swal from 'sweetalert2';
  export default {
    components: {
      navBar,
      CurrentPositionLabel
    },
    data: () => ({
      items: [{
          name: '침실',
          value: '침실'
        },
        {
          name: '옷장/수납',
          value: '옷장/수납'
        },
        {
          name: '주방',
          value: '주방'
        },
        {
          name: '욕실',
          value: '욕실'
        },
        {
          name: '서재',
          value: '서재'
        },
        {
          name: '다용도실',
          value: '다용도실'
        },
      ],
      isSell: [{
          name: '판매중',
          value: "0"
        },
        {
          name: '판매완료',
          value: "1"
        },
        {
          name: '예약중',
          value: "2"
        },
      ],
      select: '',
      select2: "",
      product: "",
      files: [], //업로드용 파일
      filesPreview: [],
      uploadImageIndex: 0, // 이미지 업로드를 위한 변수
      img: {},
      oldImage:[]
    }),
    created() {
      axios({
        url: "/zippy/used/detail",
        methods: "GET",
        params: {
          pNo: this.$route.query.pNo
        }
      }).then(res => {
        this.product = res.data;
        this.product.isSell += "";
        this.select = this.product.productCategory;
        this.select2 = this.isSell[this.product.isSell].value;
      }).catch(error => {
        console.log(error);
      })
      axios({
          url: "/zippy/used/getImg",
          methods: "GET",
          params: {
            pNo: this.$route.query.pNo
          }
        }).then(res => {
          console.log("before push data :: ", res.data);
          var num = -1;
          for(var i=0; i<res.data.length; i++){
            this.files.push({
              image : res.data[i].image,
              number : i,
              type : 'old'
            })
            num = i;
          }
          this.uploadImageIndex = num + 1;          
          
          console.log("CREATE :: ", this.files);
        }).catch(err => {
          console.log(err)
        })
    },
    methods: {      
      imageUpload() {               
        //하나의 배열로 넣기
        let num = -1;
        for (let i = 0; i < this.$refs.files.files.length; i++) {
          this.files.push(
            // ...this.files,
            //이미지 업로드
            {
              //실제 파일
              file: this.$refs.files.files[i],
              //이미지 프리뷰
              image: URL.createObjectURL(this.$refs.files.files[i]),
              //삭제및 관리를 위한 number
              number: i,
              type: 'new'
            });
          num = i;          
        }
        this.uploadImageIndex = num + 1; //이미지 index의 마지막 값 + 1 저장
        console.log(this.files);        
      },
      imageAddUpload() {
        console.log(this.$refs.files.files);        
        //하나의 배열로 넣기c
        let num = -1;
        for (let i = 0; i < this.$refs.files.files.length; i++) {
          console.log(this.uploadImageIndex);
          this.files.push(
            // ...this.files,
            //이미지 업로드
            {
              //실제 파일
              file: this.$refs.files.files[i],
              //이미지 프리뷰
              image: URL.createObjectURL(this.$refs.files.files[i]),
              //삭제및 관리를 위한 number
              number: i + this.uploadImageIndex,
              type:'new'
            });
          num = i;
        }
        this.uploadImageIndex = this.uploadImageIndex + num + 1;   
             
      },

      fileDeleteButton(e) {
        const name = e.target.getAttribute("name");
        this.files = this.files.filter(data => data.number !== Number(name));        
      },

      updateSubmit: function () {
        this.test();
        this.dropVal();
        var formData = new FormData(document.querySelector('#usedUpdate'));    
        //display none 애를 가지고 와서        
        for(let key of formData.keys()){
          console.log(`${key}, :: ${formData.get(key)}`);
        }
        axios({
          url: "/zippy/used/update",
          method: "POST",
          data: formData
        }).then(res => {
          swal.fire({
            icon: 'success',
            title: '수정되었습니다 !',
            showConfirmButton: false,
            timer: 1500
          });
          window.location.assign('/used/detail?pNo=' + this.$route.query.pNo);
        }).catch(err => {
          console.log(err)
        })
      },
      dropVal: function () {
        this.product.productCategory = this.select;
        this.product.isSell = this.select2;
      },
      test(sigu) {
        this.product.productLocation = sigu;
      }
    }
  };
</script>

<style scoped>
  .room-file-upload-example {
    height: 100%;
  }

  .cate-sell-drop{
    width: 120px;
  }

  #used-addrs{
    margin-left: 25px;
  }

  .room-file-upload-example-container {
    display: flex;
    align-items: center;
    justify-content: center;    
  }

  .room-file-image-example-wrapper {
    text-align: center;
  }

  .room-file-notice-item {
    margin-top: 5px;
    text-align: center;
  }


  .image-box {
    margin-top: 30px;
    padding-bottom: 20px;
    text-align: center;
  }

  .image-box input[type='file'] {
    position: absolute;
    width: 0;
    height: 0;
    padding: 0;
    overflow: hidden;
    border: 0;
  }

  .image-box label {
    display: inline-block;
    padding: 10px 20px;
    background-color: #b3e3c3;
    color: black;
    vertical-align: middle;
    font-size: 15px;
    cursor: pointer;
    border-radius: 5px;
    margin-left: 16px;
  }

  .file-preview-wrapper {
    padding: 10px;
    position: relative;
  }

  .file-preview-wrapper>img {
    position: relative;
    width: 190px;
    height: 190px;
    z-index: 10;
  }

  .file-close-button {
    position: absolute;    
    line-height: 18px;
    z-index: 99;
    font-size: 18px;
    right: 5px;
    top: 10px;
    color: #fff;
    font-weight: bold;
    background-color: #666666;
    width: 20px;
    height: 20px;
    text-align: center;
    cursor: pointer;
  }

  .file-preview-container {
    height: 100%;
    display: flex;
    flex-wrap: wrap;
  }

  .file-preview-wrapper-upload {
    margin: 10px;
    padding-top: 20px;
    background-color: #ffffff;
    width: 190px;
    height: 190px;
  }


  #container {
    width: 1200px;
    margin: 0 auto;
  }

  .used-main-title {
    margin: 50px;
  }

  .bd-placeholder-img {
    font-size: 1.125rem;
    text-anchor: middle;
    -webkit-user-select: none;
    -moz-user-select: none;
    user-select: none;
  }

  @media (min-width: 768px) {
    .bd-placeholder-img-lg {
      font-size: 3.5rem;
    }
  }

  .b-example-divider {
    height: 3rem;
    background-color: rgba(0, 0, 0, 0.1);
    border: solid rgba(0, 0, 0, 0.15);
    border-width: 1px 0;
    box-shadow: inset 0 0.5em 1.5em rgba(0, 0, 0, 0.1),
      inset 0 0.125em 0.5em rgba(0, 0, 0, 0.15);
  }

  .b-example-vr {
    flex-shrink: 0;
    width: 1.5rem;
    height: 100vh;
  }

  .bi {
    vertical-align: -0.125em;
    fill: currentColor;
  }

  .nav-scroller {
    position: relative;
    z-index: 2;
    height: 2.75rem;
    overflow-y: hidden;
  }

  .nav-scroller .nav {
    display: flex;
    flex-wrap: nowrap;
    padding-bottom: 1rem;
    margin-top: -1px;
    overflow-x: auto;
    text-align: center;
    white-space: nowrap;
    -webkit-overflow-scrolling: touch;
  }

  .nav-link {
    color: black;
  }

  .nav-link dropdown-toggle {
    width: 50px;
  }

  .nav-link:hover {
    color: #b3e3c3;
  }

  .used-dropdown {
    list-style: none;
  }

  #navbarDropdown {
    color: #212529;
  }

  .thumbnail-wrap {
    display: block;
  }

  .container-fluid pt-5 {
    width: 1200px;
  }

  .form-control {
    width: 150px;
  }

  #used-addr {
    cursor: pointer;
    border: none;
    background-color: white;
  }

  #used-insert-img-div {
    width: 180px;
  }

  .used-insert-img span {
    font-weight: bolder;
    font-size: large;
    margin: 10px 0 10px 10px;
  }

  .used-insert-img input {
    width: 40%;
    height: 40px;
    margin: 10px 0 10px 145px;
    background-color: #f5f5f5;
    border: 1px solid #c3c2cc;
  }

  #used-insert-price {
    width: 10%;
    margin: 10px 0 10px 145px;
  }

  #used-insert-textarea {
    margin: 10px 0 10px 150px;
    resize: none;
    border: 1px solid #c3c2cc;
  }

  .used-insert-submit {
    float: right;
    margin: 50px 20px 30px 0;
  }

  .used-insert-submit button {
    border: none;
    background-color: #b3e3c3;
    color: black;
    width: 100px;
    height: 50px;
    border-radius: 0.8rem;
  }
  .used-insert-submit button:hover {
    background-color: #95d7ab;
  }

  .used-wish-detailInfo {
    display: flex;
  }

  #used-insert-main {
    display: flex;
  }

  #dropdownMenuButton1 {
    text-align: left;
  }

  #dropdown-opt a:hover {
    color: #212529;
    background-color: #b3e3c3;
  }

  .dropdown {
    margin-left: 115px;
  }

  #dropdownMenuButton1 {
    width: 150px;
  }

  .hidden_image{
    display: none;
  }
</style>