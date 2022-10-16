<template>
  <form>
    <div>
      <nav-bar @click="search($event)"></nav-bar> 
      <div id="container">
        <div>
          <div class="used-main-title">
            <h3>상품 수정</h3>
          </div>
        </div>
        <div class="used-insert-addr">
          <button id="used-addr">
            <i class="bi bi-geo-alt"></i>
          </button>
        </div>
        <hr />
        <div>
          <div>
            <div id="used-insert-main">
              <div class="used-insert-img" id="used-insert-img-div">
                <span>이미지</span> 0/6
              </div>
              <div>
                <form>
                  <label htmlFor="profile-upload" />
                  <input type="file" id="profile-upload" multiple accept="image/*" />
                </form>
              </div>
            </div>
          </div>
        </div>
        <hr />
        <div class="used-insert-img">
          <span>제목
            <input id="used-product-name" type="text" placeholder="상품 제목을 2글자 이상 입력해주세요"
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
              <v-select v-model="select" :items="items" item-text="name" item-value="value"
                :label="product.productCategory" color="#212529" persistent-hint single-line dense width="50">
              </v-select>
            </div>
          </div>
        </div>
        <hr />
        <div class="used-insert-img">
          <span>가격<input id="used-insert-price" type="text" v-model="product.productPrice" /> 원</span>
        </div>
        <hr />
        <div class="used-wish-detailInfo">
          <div class="used-insert-img">
            <span>설명</span>
          </div>
          <div>
            <textarea id="used-insert-textarea" cols="110" v-model="product.productInfo" rows="10"></textarea>
          </div>
        </div>
        <div class="used-insert-submit">
          <button type="button" @click="updateSubmit()">수정</button>
        </div>
      </div>
    </div>
  </form>
</template>

<script>
  import axios from 'axios';
  import navBar from '../../components/used/navBar.vue';

  export default {
    components: {
      navBar
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
      select: '',
      product: {
        image: "test"
      }
    }),
    created() {
      axios({
        url: "http://localhost:8088/zippy/used/detail",
        methods: "GET",
        params: {
          pNo: this.$route.query.pNo
        }
      }).then(res => {
        console.log(res);
        this.product = res.data;
      }).catch(error => {
        console.log(error);
      })
    },
    methods: {
      // updateSubmit: function () {
      //   console.log(this.product)
      //   axios({
      //     url: "http://localhost:8088/zippy/used/update",
      //     method: "POST",
      //     headers: {
      //       "Content-Type": "application/json; charset=utf-8"
      //     },
      //     data: JSON.stringify(this.product)
      //   }).then(res => {
      //     console.log(res);
      //     window.location.assign('/used/detail?pNo='+this.$route.query.pNo);
      //   }).catch(err => {
      //     console.log(err)
      //   })
      // },
      updateSubmit: function () {
        console.log(this.product)
        axios({
          url: "http://localhost:8088/zippy/used/update",
          method: "POST",
          headers: {
            "Content-Type": "application/x-www-form-urlencoded; charset=utf-8"
          },
          data: this.product
        }).then(res => {
          console.log(res);
          // window.location.assign('/used/detail?pNo='+this.$route.query.pNo);
        }).catch(err => {
          console.log(err)
        })
      }
    }
  };
</script>

<style>
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
    color: white;
    width: 100px;
    height: 50px;
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
</style>