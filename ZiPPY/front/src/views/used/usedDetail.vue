<template>
  <div>
    <nav-bar @click="search($event,cate)"></nav-bar>
    <div id="container">
      <div>
        <div class="used-main-title">
          <h3>판매중인 중고제품</h3>
        </div>
        <hr />
        <div id="used-cate-main">{{product.productCategory}}</div>
      </div>
      <hr />
      <div id="used-cate-update">
        <div>
        </div>
        <div v-if="this.$store.state.loginInfo != null && this.$store.state.loginInfo.email == this.product.email">
          <v-btn width="90px" class="mr-2" outlined color="#64c481" @click="goUpdate(product.productNo)">
            수정
          </v-btn>
          <v-btn width="90px" depressed color=#B3E3C3 @click="delProduct()">
            삭제
          </v-btn>
        </div>
      </div>
      <div id="used-detail-main">
        <div id="used-img-slide">
          <div id="used-detail-img">
            <!-- slide start -->
            <div id="detail-container">
              <v-carousel hide-delimiters>
                <v-carousel-item v-for="(img,i) in imgs" :key="i" :src="img.src"></v-carousel-item>
              </v-carousel>
            </div>
            <!-- slide end -->
          </div>
          <div id="used-detail-total">
            <div id="used-product-title">
              <div id="used-wish-heart">
                <div>
                  {{product.productName}}
                </div>
                <div>
                  <button>
                    <!--heart-->
                    <div>
                      <v-btn v-if="heart==0" class="mx-2" color='#D6D6D6' fab depressed dark small
                        @click="changeHeart()">
                        <v-icon dark>
                          mdi-heart
                        </v-icon>
                      </v-btn>
                      <v-btn v-if="heart==1" class="mx-2" color='#FF4063' fab depressed dark small
                        @click="changeHeart()">
                        <v-icon dark>
                          mdi-heart
                        </v-icon>
                      </v-btn>
                    </div>
                  </button>
                </div>
              </div>
              <div id="used-product-price">
                {{product.productPrice | comma}}원
                <hr />
                <div>
                  <div id="used-wish-eye">
                    <div id="used-view-wish">
                      <img class="used-wish-view-img"
                        src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACwAAAAaCAYAAADMp76xAAAAAXNSR0IArs4c6QAABAdJREFUWAm9mFtIFFEYx9tZ11UW1tLoaoGEPShqq3ahgogyIgnqQXqIgih6qKgEH4JIqCgIIoowIrSn6i0irOxCQdAN7wb2IiSlSUZuGJGyumu/b9lZZo8zs7ObdeBwvvNd/uc/53zznWFcs9Js7e3tczVNWzs1NbUKiErGfJfLNYcxVyCRg8g/GAeZdiC3eTyeN2VlZd/Enm5zpRLY09Pjm5yc3EnMbghUMbpTiYd8BP8X9Dt+v/9uYWHhz1TixdcR4YGBgezh4eFD+J+gz5XAGWijYFzKycm5nArxpIQ5+hqAr9AXzgBJM4ggqXWyvLz8uplR1VkShmgOR3iVo9+jBv2LOWs9pu+H+JAdvilhyC4j6AldxqSNhT7g1Oh2u59mZWV9loDx8fGl4XB4C+IBHrpIdA7ad7C2V1RUvLPynUa4u7s7wIvVQsB8qyCDfgK5jgUaWChs0MdFyLo7OjoOo7hI98QN1sJvsHaB+cDMJYFwV1fXCnblJY5+M2dFN8GOVgcCgWeK3nQKdhXYDzE6IR2GdA2k76lgmq7o7OxcBGAzcydkJazOKVlxjvnWieyguTmZ25y21PiEFt3h/v7+rJGRkddYyhOsFhOe/gMvR6lVGliEzZL0YGPep5DTw16vd2VJScmAjhnd4WAweBaFI7KxwEaVLCQyIHOafB2ULrLo9IVkjMU0GnVJ5PmhUOim0UejIqwGuNaoTCZLNVB9yNFTkUikHqzF0kUWnepnFqv6GOdgbWYDDuo6jaduYOLWFU5Gvgk+qX4A73ei08ue6ms3B/ui3LbiozExLUd2AOxSQnWx850h2+f8/PyQYGksfoRxMhVguRRUf06qyYnOLFaNM87BjdAP0KMbq1Fu2phcMDolk2M3WIIbOGf5JjgD1hfpIosuwYmJWazqo8yvGG++6NH29vZmjo2NPcdxveJsOoXQ/yprXcKpsrLyt04kWtaKi4tDPp9vB0T6dIPdSN4Xxa5bO7dpNomR2GkGEwVchjIyMrYbyYpbwstDGSqkHL0CdJ4Jhqr6l1ezfNhvhGynumj8ahYDOSc7vI7+UeZJmke+DajjR3lAy7IoNvERX/CcfEd8pRBsMCMrfBJ2WCdITi8gpx8xD+g6u1FyGvtff15KSlLjt5aWllpumClhIdfX1+cdHR09D0gtu2TpZ/cgKdqasrOzj/M+/bKLS0qEb4JN5PU1QJbbAaVrY0M+UQKPkY73nWAkJSwgkoe84fsQ6+lLRDcD7Stkz3FV35Aq5RTPEWEdLFavt7HQXnTVPEimbnM4ThDbQtytvLy85oKCgnGHcXG3lAjHoxAogbNJlTWIq6VDQn6k5DLmih+y/EgJMsqPlFaOvZW3/y0v1A+xp9v+ADhPuomDsZuZAAAAAElFTkSuQmCC"
                        width="21" height="13" alt="상품 상태 아이콘" />
                      <div class="used-wish-view-count">{{product.views}}</div>
                    </div>
                    <div id="used-detail-report">
                      <used-report :productNo="this.product.productNo"></used-report>
                    </div>
                  </div>
                  <div class="used-issell-location">
                    <ul>
                      <div class="used-issell-location">
                        <div>
                          <li>판매여부</li>
                        </div>
                        <div v-if="product.isSell == 0" class="used-sell-location">
                          판매중
                        </div>
                        <div v-if="product.isSell == 1" class="used-sell-location">
                          판매완료
                        </div>
                        <div v-if="product.isSell == 2" class="used-sell-location">
                          예약중
                        </div>
                      </div>
                      <div class="used-issell-location">
                        <div>
                          <li>거래지역</li>
                        </div>
                        <div class="used-sell-location">{{product.productLocation}}</div>
                      </div>
                    </ul>
                  </div>
                  <div id="used-profile-chat">
                    <div>
                      <button @click="goUserPage(product.email)" id="used-detail-pofile">
                        <i class="fa-solid fa-user"> {{product.nickName}}</i>
                      </button>
                    </div>
                    <div>
                      <v-btn width="160" depressed color=#B3E3C3>
                        <i class="fa-solid fa-comments" @click="createChat()"> 채팅하기</i>
                      </v-btn>
                      <!-- <button class="used-detail-wish" width="30px">
                        <i class="fa-solid fa-comments" @click="createChat()"> 채팅하기</i>
                      </button> -->
                    </div>
                  </div>
                </div>
              </div>
              <input type="hidden" v-model="product.email">
            </div>
          </div>
        </div>
        <hr />
      </div>
      <div id="used-detail-info-div">
        <h3 id="used-detail-info">상품정보</h3>
        <hr />
      </div>
      <div id="used-product-info">
        {{product.productInfo}}
      </div>
    </div>
  </div>
</template>

<script>
  import axios from 'axios';
  import navBar from '../../components/used/navBar.vue';
  import swal from 'sweetalert2';
  import usedReport from '../../components/used/usedReport.vue';

  export default {
    components: {
      navBar,
      usedReport
    },
    data: () => ({
      imgs: [{
          src: "http://file3.instiz.net/data/file3/2022/06/08/2/6/f/26fe0e3efee7315868ff30668e109e1d.jpg"
        },
        {
          src: "http://file3.instiz.net/data/file3/2022/06/08/7/7/b/77b348b96cef2c41cfae7a1293ca395e.jpg"
        },
        {
          src: "http://file3.instiz.net/data/file3/2022/06/08/b/2/2/b2221c8f6fd58a1670652ac5300299f1.jpg"
        },
        {
          src: "http://file3.instiz.net/data/file3/2022/06/08/a/4/8/a484eeb9b529d2d86a983d75beb86cae.jpg"
        },
        {
          src: "http://file3.instiz.net/data/file3/2022/06/08/7/e/1/7e113a4442c27945a2a379401c5021c8.jpg"
        },
        {
          src: "http://file3.instiz.net/data/file3/2022/06/08/e/f/7/ef70fcab688d843508f786052ebaf9a6.jpg"
        },
        {
          src: "http://file3.instiz.net/data/file3/2022/06/08/3/6/b/36bf72e0e424d860262d5cb8e50e4b69.jpg"
        },
        {
          src: "http://file3.instiz.net/data/file3/2022/06/08/b/8/e/b8e6359f0fd89cbf68ac1dbd7d9399e2.jpg"
        },
        {
          src: "http://file3.instiz.net/data/file3/2022/06/08/2/c/2/2c2f759ae3ff1d3cc9470a988ffe7ebf.jpg"
        },
      ],
      product: "",
      email: "",
      img: {},
      data: {
        email: "",
        serviceId: "",
        bookmarkNo: "",
        serviceType: 1
      },
      heart: 0,
      wish: "",
      cate : ""
    }),
    filters: {
      comma(val) {
        return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ',')
      }
    },
    created() {
      axios({
          url: "/zippy/used/detail",
          method: "GET",
          params: {
            pNo: this.$route.query.pNo
          }
        }).then(res => {
          this.product = res.data;
          this.data.serviceId = this.product.productNo;
          this.data.email = this.$store.state.loginInfo.email;
        }).catch(error => {
          console.log(error);
        }),
        axios({
          url: "/zippy/used/getImg",
          methods: "GET",
          params: {
            pNo: this.$route.query.pNo
          }
        }).then(res => {
          console.log(res);
          this.img = res.data;
          console.log(this.img);
        }).catch(err => {
          console.log(err)
        })
      if (this.$store.state.loginInfo != null) {
        axios({
          url: "/zippy/common/wishOne",
          method: "GET",
          params: {
            email: this.$store.state.loginInfo.email,
            sId: this.$route.query.pNo,
            serviceType: this.data.serviceType
          }
        }).then(res => {
          this.wish = res.data;
          if (res.data != "") {
            this.heart = 1;
          } else if (res.data == "") {
            this.heart = 0;
          }
        }).catch(err => {
          console.log(err)
        })
      }
    },
    methods: {
      delProduct() {
        swal.fire({
          icon: 'success',
          title: '삭제되었습니다 !',
          showConfirmButton: false,
          timer: 1500
        });
        axios({
          url: "/zippy/used/del",
          method: "DELETE",
          params: {
            pNo: this.$route.query.pNo
          }
        }).then(res => {
          window.location.assign('/used');

        }).catch(err => {
          console.log(err)
        })
      },
      goUserPage(email) {
        this.$router.push('/used/user?email=' + email);
      },
      rewrite() {
        axios({
          url: "/zippy/common/wishOne",
          method: "GET",
          params: {
            email: this.$store.state.loginInfo.email,
            sId: this.$route.query.pNo,
            serviceType: this.data.serviceType
          }
        }).then(res => {
          this.wish = res.data;
          if (res.data != "") {
            this.heart = 1;
          } else if (res.data == "") {
            this.heart = 0;
          }
        }).catch(err => {
          console.log(err)
        })
      },
      changeHeart() {
        if (this.$store.state.loginInfo != null) {
          if (this.heart == 0) { //찜x일때
            this.addWish();
            this.heart = 1; //찜on으로 변경
            swal.fire({
              icon: 'success',
              title: '찜 목록에 추가되었습니다.',
              showConfirmButton: false,
              timer: 1500
            });
          } else { //찜on 일 떄
            this.delWish();
            this.heart = 0; //찜x로 변경
            swal.fire({
              icon: 'success',
              title: '찜목록에서 삭제되었습니다.',
              showConfirmButton: false,
              timer: 1500
            });
          }
        } else {
          swal.fire({
            icon: 'warning',
            title: '로그인 정보가 필요합니다.',
            showConfirmButton: false,
            timer: 1500
          });
        }
      },
      goUpdate(no) {
        this.$router.push('/used/update?pNo=' + no);
      },
      search: function (e) {
        var cate = e.target.innerText;
        sessionStorage.setItem("cate",cate);
        this.$router.push('/used');
      },
      addWish: function () {
        console.log("찜 추가추가")
        axios({
          url: "/zippy/common/addWish",
          method: "POST",
          headers: {
            "Content-Type": "application/json; charset=utf-8"
          },
          data: JSON.stringify(this.data)
        }).then(res => {
          
          this.rewrite();
        }).catch(err => {
          console.log(err)
        })
      },
      delWish: function () {
        let bNo = [];
        bNo.push(this.wish.bookmarkNo);
        axios({
          url: "/zippy/common/delWish",
          method: "DELETE",
          data: {
            bNo: bNo
          }
        }).then(res => {
        }).catch(err => {
          console.log(err)
        })
      },

      createChat: async function () {
        alert("채팅방 생성 실행!");
        if (this.$store.state.loginInfo == null) {
          swal.fire({
            icon: 'warning',
            title: '로그인 정보가 필요합니다.',
            showConfirmButton: false,
            timer: 1500
          });
        } else {
          var temp = await this.$axios({
            url: "/zippy/chat/room",
            method: "POST",
            data: {
              user1: this.$store.state.loginInfo.email,
              user2: this.product.email,
              chatType: 1,
              productNo : this.$route.query.pNo
            }
          });
          console.log(temp);
        }
      },
      report() {
        this.report.email = this.$store.state.loginInfo.email;
        this.report.serviceId = this.$route.query.pNo;
        axios({
          url: "/zippy/common/addReport",
          method: "POST",
          headers: {
            "Content-Type": "application/json; charset=utf-8"
          },
          data: JSON.stringify(this.report)
        }).then(res => {
        }).catch(err => {
          console.log(err)
        })
      }
    }
  }
</script>

<style scoped>
  #container {
    width: 1200px;
    margin: 0 auto;
  }

  #used-product-info {
    margin-bottom: 50px;
  }

  .used-up-del-btn {
    margin-left: 5px;
  }

  #used-wish-heart {
    display: flex;
    justify-content: space-between;
  }

  .used-main-title {
    margin: 50px;
  }

  /* css 디테일 슬라이드 */
  .v-carousel {
    width: 430px;
    border-radius: 1.5em;
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
    width: 150px;
  }

  #used-cate-update {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 10px;
  }

  #used-cate-update button {
    margin-left: 1px;
  }

  #used-img-slide {
    display: flex;
    padding: 30px 0;
  }

  #used-detail-img {
    width: 450px;
    height: 500px;
  }

  #used-detail-info {
    color: black;
  }

  #used-product-title {
    font-size: 24px;
    padding-left: 30px;
  }

  #used-product-price {
    font-size: 40px;
  }

  #bi bi-heart {
    width: 20px;
    height: 20px;
  }

  .used-issell-location {
    font-size: 15px;
    display: flex;
  }

  .used-issell-location ul li {
    color: rgb(153, 153, 153);
    margin-bottom: 20px;
    margin-right: 10px;
  }

  .used-sell-location {
    display: flex;
  }

  #used-detail-report {
    color: rgb(204, 204, 204);
    font-size: 16px;
    display: inline-block;
    -webkit-box-align: center;
    align-items: center;
    border: none;
    margin-bottom: 20px;
  }

  #used-wish-eye {
    justify-content: space-between;
    display: flex;
    padding-left: 10px;
  }

  #used-detail-total {
    width: 750px;
  }

  #used-profile-chat {
    display: flex;
    margin-top: 95px;
    justify-content: space-between;
    border-top: 1px solid #c8c9ca;
  }

  #used-view-wish {
    display: flex;
  }

  #used-detail-img {
    width: 430px;
    height: 430px;
    padding-right: 40px;
  }

  #used-detail-img img {
    width: 100%;
    height: 100%;
  }

  #used-detail-info-div {
    width: 1200px;
  }

  #used-detail-pofile {
    border: none;
    background-color: white;
    font-size: x-large;
  }

  #used-detail-main {
    margin-bottom: 60px;
  }

  .used-detail-wish {
    border: none;
    color: white;
    background-color: #b3e3c3;
    font-size: x-large;
    border-radius: 10px;
    margin-left: 10px;
    width: 150px;
  }

  #used-detail-info-div {
    margin-bottom: 30px;
  }

  .used-wish-view-img {
    margin-right: 5px;
    margin-top: 5px;
  }

  .used-wish-view-count {
    font-size: medium;
    color: rgb(204, 204, 204);
    margin-right: 5px;
  }

  #used-cate-main {
    text-align: center;
    font-size: xx-large;
  }
</style>