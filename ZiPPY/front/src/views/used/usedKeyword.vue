<template>
  <div>
    <div id="container">
      <div>
        <div class="used-main-title">
          <h3>키워드 및 키워드 지역</h3>
        </div>
      </div>
      <hr />
      <div>
        <div class="used-keyword-maincont">
          <div>
            <div class="used-keyword-location"><span>키워드 {{showKey.length}}/10</span></div>
            <div>
              <div class="used-input">
                <input id="keyinputid" class="form-control" type="search" placeholder="키워드" aria-label="Search"
                  v-model="data.keyword" />
                <input id="keyinputid" class="form-control" type="search" placeholder="키워드지역(시군구나 읍면동)"
                  aria-label="Search" v-model="data.keywordLocation" @keyup="enterkey()" />
                <button class="submitBtn" type="button" @click="addKey()">등록</button>
                <!-- <button class="submitBtn" type="button" @click="addKey()">등록</button> -->
              </div>
              <div class="used-input">
              </div>
            </div>
            <div class="used-key-flex">
              <div class="used-keyword-content" v-for="list in showKey">
                <div>
                  키워드 : {{list.keyword}}, 키워드 지역 : {{list.keywordLocation}}
                  <div class="used-keyword-close">
                    <i @click="delKey(list.keywordNo)" class="fa-solid fa-circle-xmark"></i>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <input type="hidden" v-model="data.email">
    </div>
  </div>
</template>

<script>
  import axios from 'axios';
  import navBar from '../../components/used/navBar.vue';
  import swal from 'sweetalert2';

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
        email: "used@naver.com",
        keyword: "",
        keywordLocation: "",
        keywordNo: ""
      },
      showKey: []
    }),
    created() {
      console.log(this.data.email)
      axios({
        url: "http://localhost:8090/zippy/used/keyword",
        method: "GET",
        params: {
          email: this.data.email
        }
      }).then(res => {
        console.log(res);
        this.showKey = res.data;
      }).catch(error => {
        console.log(error);
      })
    },
    methods: {
      addKey: function () {
        if ((this.data.keyword == "" || this.data.keyword == null) || (this.data.keywordLocation == "" || this.data
            .keywordLocation == null)) {
          swal.fire({
            icon: 'warning',
            title: '빈 값은 입력할 수 없습니다.',
            showConfirmButton: false,
            timer: 1500
          });
          console.log(this.showKey.length)
          return false;
        }
        if (this.showKey.length == 10) {
          swal.fire({
            icon: 'warning',
            title: '입력 가능한 키워드(지역)수는 10개입니다.',
            showConfirmButton: false,
            timer: 1500
          });
          return false;
        }
        axios({
          url: "http://localhost:8090/zippy/used/addKeyword",
          method: "POST",
          headers: {
            "Content-Type": "application/json; charset=utf-8"
          },
          data: JSON.stringify(this.data)
        }).then(res => {
          console.log("======", res);
          this.showKey.push({
            "keyword": this.data.keyword,
            "keywordLocation": this.data.keywordLocation,
            "keywordNo": res.data
          });
          console.log(this.data);

          this.data.keyword = "";
          this.data.keywordLocation = "";
        }).catch(err => {
          console.log(err)
        })
      },
      delKey: function (kNo) {
        axios({
          url: "http://localhost:8090/zippy/used/delKeyword",
          method: "DELETE",
          params: {
            kNo
          }
        }).then(res => {
          console.log(res.data);
          for (let i in this.showKey) {
            if (this.showKey[i].keywordNo == kNo) {
              this.showKey.splice(i, 1);
              return
            }
          }
        }).catch(err => {
          console.log(err)
        })
      },
      enterkey: function () {
        if (window.event.keyCode == 13) {
          this.addKey();
        }
      }
    }
  };
</script>

<style scoped>
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

  #keyinputid {
    margin-left: 3px;
  }


  .used-key-flex {
    width: 1200px;
    margin-top: 20px;
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