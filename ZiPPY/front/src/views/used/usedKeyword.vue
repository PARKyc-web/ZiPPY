<template>
  <div id="container">
    <nav-bar @click="search($event)"></nav-bar>
    <div>
      <div class="used-main-title">
        <h3>키워드 및 키워드 지역</h3>
      </div>
    </div>
    <hr />
    <div>
      <div class="used-keyword-maincont">
        <div>
          <div class="used-keyword-location"><span>키워드 {{keywordValue.length}}/10</span></div>
          <div>
            <div class="used-input">
              <input id="keyinputid" class="form-control" type="search" placeholder="키워드" aria-label="Search"
                v-model="data.keyword" />
                <input id="keyinputid" class="form-control" type="search" placeholder="키워드지역" aria-label="Search"
                  v-model="data.keywordLocation" @keyup="enterkey()" />
                  <button class="submitBtn" type="button" @click="addKey()">등록</button>
              <!-- <button class="submitBtn" type="button" @click="addKey()">등록</button> -->
            </div>
            <div class="used-input">
            </div>
          </div>
          <div class="used-key-flex">
            <div class="used-keyword-content" v-for="value in keywordValue">
              {{value}}
              <div class="used-keyword-close">
                <i @click="DelKey()" class="fa-solid fa-circle-xmark"></i>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- <hr /> -->
    <!-- <div class="used-keyword-maincont">
      <div>
        <div>
          <div class="used-keyword-location">
            <span>키워드 지역 0/3</span>
          </div>
        </div>
        <div>

        </div>
      </div>
      <div class="used-key-flex">
        <div class="used-keyword-content" v-for="loc in locationvalue">
          {{loc}}
          <div class="used-keyword-close">
            <i class="fa-solid fa-circle-xmark" @click="DelLoc()"></i>
          </div>
        </div>
      </div>
    </div> -->
    <input type="hidden" v-model="data.email">
    <!-- <div v-if="data.length != 0" v-for="key in showKey">
      {{key.keyword}}
      {{key.keywordLocation}}
    </div> -->
  </div>
</template>

<script>
  import axios from 'axios';
  import navBar from '../../components/used/navBar.vue';

  export default {
    components: {
      navBar
    },
    data: () => ({
      keywordValue: [],
      locationvalue: [],
      keyword: "",
      location: "",
      data: {
        email: "zippy@naver.com",
        keyword: "",
        keywordLocation: ""
      },
      showKey: []
    }),
    // created() {
    //   console.log(this.data.email)
    //   axios({
    //     url: "http://localhost:8088/zippy/used/keyword",
    //     method: "GET",
    //     params: {
    //       email: this.data.email
    //     }
    //   }).then(res => {
    //     console.log(res);
    //     this.showKey = res.data;
    //   }).catch(error => {
    //     console.log(error);
    //   })
    // },
    methods: {
      addKey: function () {
        this.keywordValue.push(this.data.keyword);
        // console.log(this.data.keyword.length);
        // console.log(this.keywordValue.length)
        // console.log(this.keywordValue)
        axios({
          url: "http://localhost:8088/zippy/used/addKeyword",
          method: "POST",
          headers: {
            "Content-Type": "application/json; charset=utf-8"
          },
          data: JSON.stringify(this.data)
        }).then(res => {
          console.log(res);
          console.log(this.data);
          this.data.keyword = "";
          this.data.keywordLocation = "";
        }).catch(err => {
          console.log(err)
        })
      },
      addLoc: function () {
        this.locationvalue.push(this.data.keywordLocation);
        console.log(this.locationvalue.length)
        axios({
          url: "http://localhost:8088/zippy/used/addKeyword",
          method: "POST",
          headers: {
            "Content-Type": "application/json; charset=utf-8"
          },
          data: JSON.stringify(this.data)
        }).then(res => {
          console.log(res);
          console.log(this.data);
          this.data.keywordLocation = "";
        }).catch(err => {
          console.log(err)
        })
      },
      DelKey: function () {
        this.showKey.pop();
        axios({
          url: "http://localhost:8088/zippy/used/delKeyword",
          method: "DELETE",
          params: {
            // kNo: 
          }
        }).then(res => {
          console.log(res);
        }).catch(err => {
          console.log(err)
        })
      },
      DelLoc: function () {
        this.locationvalue.pop();
        axios({
          url: "http://localhost:8088/zippy/used/delKeyword",
          method: "DELETE",
          params: {
            // kNo: 
          }
        }).then(res => {
          console.log(res);
        }).catch(err => {
          console.log(err)
        })
      },
      enterkey: function () {
        if (window.event.keyCode == 13) {
          this.addKey();
          this.data.keyword = "";
        }
      }
    }
  };
</script>

<style>
  #container {
    width: 1200px;
    margin: 0 auto;
  }

  .submitBtn {
    border: none;
    background-color: #b3e3c3;
    color: white;
    width: 150px;
    height: 50px;
    margin-left: 1px;
    border-radius: 5px;
  }
  #keyinputid{
    margin-left: 3px;
  }


  .used-key-flex {
    width: 1200px;
  }

  .used-input {
    width: 700px;
    display: flex;
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
    float: right;
    width: 50px;
  }

  .nav-link:hover {
    color: #b3e3c3;
  }

  .nav-item dropdown {
    float: right;
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
    width: 100px;
    display: inline-block;
  }

  .used-keyword-div {
    border: none;
    border-radius: 10px;
    padding: 5px;
    width: 120px;
    margin: 10px;
    background-color: #b3e3c3;
    font-weight: bolder;
    overflow: hidden;
    text-align: center;
  }

  .used-keyword-close {
    float: right;
    padding-left: 10px;
  }

  .used-keyword-close:hover {
    cursor: pointer;
  }

  .used-keyword-loc-div {
    border: none;
    border-radius: 10px;
    padding: 5px;
    width: 150px;
    margin: 10px;
    background-color: #b3e3c3;
    font-weight: bolder;
    overflow: hidden;
    text-align: center;
  }

  .used-keyword-content {
    border: none;
    border-radius: 10px;
    padding: 5px;
    width: fit-content;
    margin: 10px;
    background-color: #b3e3c3;
    font-weight: bolder;
    display: inline-block;
  }

  .used-keyword-location {
    font-weight: bolder;
    font-size: larger;
    margin-bottom: 20px;
  }

  .used-keyword-maincont {
    width: 1024px;
    margin-left: 30px;
  }
</style>