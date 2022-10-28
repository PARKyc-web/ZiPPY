<template>
  <div id="container">
    <div class="used-main-title">
      <h3>관심매물 목록</h3>
    </div>

    <div id="used-usedwishckdel">
      <div id="used-wish-chkbox">
        <v-checkbox v-model="uncheck" @click="selectAll()" id="ckAll" color="#b3e3c3"></v-checkbox>
      </div>
      <div id="used-wish-del-div">
        <button @click="delWish()" class="used-wish-del-btn">선택삭제</button>
      </div>
    </div>
    <hr>
    <div class="row" style="margin-left:35px ;">
      <v-card v-if="properties.length != 0" v-for="item in properties" style="width: 45%; margin: 1%; padding: 20px;"
        @click="goHouseDetail(item.productId)">
        <v-card-text>

          <div id="used-wish-info">
            <div>
              <button class="used-wish-heart">
                <i class="bi bi-heart-fill"></i>
              </button>
            </div>
            <div id="wish-card-ckbox">
              <div style="float: right">
                <v-checkbox id="ck" v-model="ckList" color="#b3e3c3" :value="item.bookmarkNo" @click.stop="test()">
                </v-checkbox>
              </div>
            </div>
          </div>

          <table>
            <tr>
              <td style="width: 50%;"><img :src="'http://localhost:8090/zippy/common/img/property/' + item.mainImg"
                  style="width: 100%; height: 100%" /></td>
              <td style="width: 50%;">
                <div style="margin-left:15px">
                  <v-row align="center" class="mx-0">
                    <div>
                      <v-chip class="ma-2" color="green lighten-2" small outlined style="left: -10px; top: 10px">매물번호
                        {{item.productId}}</v-chip>
                    </div>
                  </v-row>
                  <v-card-title style="font-weight: bold;">{{item.saleType}} {{item.price | oneHundredMillion}}
                  </v-card-title>
                  <p class="card_contents">{{item.houseType}} · {{item.houseName}}</p>
                  <p class="card_contents">{{item.sigungu}}</p>
                  <p class="card_contents">{{item.areaExclusive}}m² · {{item.floor}}층</p>
                  <p class="card_contents">{{item.detailContents}}</p>
                </div>
              </td>
            </tr>
          </table>
        </v-card-text>
      </v-card>
    </div>

    <div class="text-center" style="margin: 25px;">
      <v-pagination v-model="page" :length="pageCount" circle color="#B3E3C3" total-visible="5"></v-pagination>
    </div>

  </div>

</template>

<script>
  import axios from 'axios';

  export default {
    data() {
      return {
        properties: [],
        page: 1,
        pageCount: 1,

        ckList: [],
        uncheck: ""
      }
    },
    created() {
      this.getPropertyList();
    },
    watch: {
      page() {
        this.getPropertyList();
      }
    },
    filters: {
      oneHundredMillion: function (price) {
        let result = "";

        if (price.length > 4) {
          result += price.substr(0, price.length - 4) + '억';
          price = price.substr(price.length - 4, price.length);
          if (price == '0000') return result;
        }
        price = price.substr(price.length - 4, price.length - 3) + ',' + price.substr(price.length - 3, price.length);
        if (price[0] == '0') {
          price = price.substr(price.length - 3, price.length);
        }
        result += price;

        return result;
      }
    },
    methods: {
      getPropertyList() {
        axios({
            url: "/zippy/property/getAllWishProperties",
            method: "GET",
            params: {
              email: this.$store.state.loginInfo.email,
              pageNum: this.page
            }
          }).then(response => {
            // 성공했을 때
            this.properties = response.data.list;
            this.pageCount = response.data.pages;
          })
          .catch(error => {
            // 에러가 났을 때
            console.log(error);
          })
      },

      test: function () {
        var ck = document.querySelector("#ck").checked;
        if (ck == true) {
          this.uncheck = false;
        }
      },
      goHouseDetail(productId) {
        this.$router.push({
          name: 'HouseDetail',
          query: {
            productId: productId
          }
        })
      },
      selectAll: function () {
        let isCheck = document.querySelector('#ckAll').checked;
        if (isCheck == false) {
          for (let i of this.properties) {
            this.ckList.push(i.bookmarkNo);
          }
        } else if (isCheck == true) {
          this.ckList.splice(0);
        }
      },
      // 선택 삭제 & 전체 삭제 ?
      delWish: function () {
        axios({
          url: "/zippy/common/delWish",
          method: "DELETE",
          data: {
            bNo: this.ckList
          }
        }).then(res => {
          let arr = [];
          for (let i of this.properties) {
            if (this.ckList.indexOf(i.bookmarkNo) < 0) {
              arr.push(i);
            }
          }
          this.properties = arr;
          this.ckList = [];
        }).catch(err => {
          console.log(err)
        })
      }
    }

  };
</script>

<style scoped>
  #container {
    width: 1200px;
    margin: 0 auto;
  }


  .used-main-title {
    margin: 50px;
  }


  #main_title {
    margin-top: 25px;
    margin-bottom: 25px;
    font-weight: bold;
  }

  .contents {
    margin-top: 10px;
    margin-left: 35px;
    margin-right: 35px;
  }

  .card_contents {
    margin-left: 20px;
    font-weight: bold;
  }

  .used-wish-del-btn {
    padding: 0px 10px px;
    height: 28px;
    border: 1px solid rgb(238, 238, 238);
    display: flex;
    -webkit-box-align: center;
    align-items: center;
    -webkit-box-pack: center;
    justify-content: center;
    font-size: 13px;
    background: rgb(255, 255, 255);
    color: rgb(136, 136, 136);
    margin-top: 19px;
  }

  .used-wish-del-btn:hover {
    background-color: #b3e3c3;
  }

  #used-wish-del-div {
    margin-left: 10px;
    float: right;
  }

  #used-usedwishckdel {
    display: flex;
  }


  .used-wish-heart {
    float: right;
    background-color: white;
    border: none;
  }
</style>