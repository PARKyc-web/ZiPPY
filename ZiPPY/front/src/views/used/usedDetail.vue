<template>
  <div>
    <nav-bar @click="search($event)"></nav-bar>
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
          <div></div>
        </div>
        <div
          v-if="this.$store.state.loginInfo != null && this.$store.state.loginInfo.email == this.product.email">
          <button @click="goUpdate(product.productNo)">수정하기</button>
        </div>
      </div>
      <div id="used-detail-main">
        <div id="used-img-slide">
          <div id="used-detail-img">
            <!-- slide start -->
            <div id="detail-container">
              <v-carousel height="400" hide-delimiter-background :show-arrows="false" class="mt-5">
                <v-carousel-item v-for="(img, i) in imgs" :key="i">
                  <v-img :src="imgs[i]" height="100%">
                    <v-row class="fill-height" align="center" justify="center">
                    </v-row>
                  </v-img>
                </v-carousel-item>
              </v-carousel>
            </div>
            <!-- slide end -->
          </div>
          <div id="used-detail-total">
            <div id="used-product-title">
              {{product.productName}}
              <div id="used-product-price">
                {{product.productPrice | comma}}원
                <hr />
                <div 찜 조회수 신고하기 판매여부 거래지역>
                  <div id="used-wish-eye">
                    <div id="used-view-wish">
                      <img class="used-wish-view-img"
                        src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACwAAAAaCAYAAADMp76xAAAAAXNSR0IArs4c6QAABAdJREFUWAm9mFtIFFEYx9tZ11UW1tLoaoGEPShqq3ahgogyIgnqQXqIgih6qKgEH4JIqCgIIoowIrSn6i0irOxCQdAN7wb2IiSlSUZuGJGyumu/b9lZZo8zs7ObdeBwvvNd/uc/53zznWFcs9Js7e3tczVNWzs1NbUKiErGfJfLNYcxVyCRg8g/GAeZdiC3eTyeN2VlZd/Enm5zpRLY09Pjm5yc3EnMbghUMbpTiYd8BP8X9Dt+v/9uYWHhz1TixdcR4YGBgezh4eFD+J+gz5XAGWijYFzKycm5nArxpIQ5+hqAr9AXzgBJM4ggqXWyvLz8uplR1VkShmgOR3iVo9+jBv2LOWs9pu+H+JAdvilhyC4j6AldxqSNhT7g1Oh2u59mZWV9loDx8fGl4XB4C+IBHrpIdA7ad7C2V1RUvLPynUa4u7s7wIvVQsB8qyCDfgK5jgUaWChs0MdFyLo7OjoOo7hI98QN1sJvsHaB+cDMJYFwV1fXCnblJY5+M2dFN8GOVgcCgWeK3nQKdhXYDzE6IR2GdA2k76lgmq7o7OxcBGAzcydkJazOKVlxjvnWieyguTmZ25y21PiEFt3h/v7+rJGRkddYyhOsFhOe/gMvR6lVGliEzZL0YGPep5DTw16vd2VJScmAjhnd4WAweBaFI7KxwEaVLCQyIHOafB2ULrLo9IVkjMU0GnVJ5PmhUOim0UejIqwGuNaoTCZLNVB9yNFTkUikHqzF0kUWnepnFqv6GOdgbWYDDuo6jaduYOLWFU5Gvgk+qX4A73ei08ue6ms3B/ui3LbiozExLUd2AOxSQnWx850h2+f8/PyQYGksfoRxMhVguRRUf06qyYnOLFaNM87BjdAP0KMbq1Fu2phcMDolk2M3WIIbOGf5JjgD1hfpIosuwYmJWazqo8yvGG++6NH29vZmjo2NPcdxveJsOoXQ/yprXcKpsrLyt04kWtaKi4tDPp9vB0T6dIPdSN4Xxa5bO7dpNomR2GkGEwVchjIyMrYbyYpbwstDGSqkHL0CdJ4Jhqr6l1ezfNhvhGynumj8ahYDOSc7vI7+UeZJmke+DajjR3lAy7IoNvERX/CcfEd8pRBsMCMrfBJ2WCdITi8gpx8xD+g6u1FyGvtff15KSlLjt5aWllpumClhIdfX1+cdHR09D0gtu2TpZ/cgKdqasrOzj/M+/bKLS0qEb4JN5PU1QJbbAaVrY0M+UQKPkY73nWAkJSwgkoe84fsQ6+lLRDcD7Stkz3FV35Aq5RTPEWEdLFavt7HQXnTVPEimbnM4ThDbQtytvLy85oKCgnGHcXG3lAjHoxAogbNJlTWIq6VDQn6k5DLmih+y/EgJMsqPlFaOvZW3/y0v1A+xp9v+ADhPuomDsZuZAAAAAElFTkSuQmCC"
                        width="21" height="13" alt="상품 상태 아이콘" />
                      <div class="used-wish-view-count">{{product.views}}</div>
                    </div>
                    <button id="used-detail-report">
                      <img
                        src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAbBJREFUeNrsls9LAkEcxb+au+JSGIEQyGbBdg881CmConsE9Qd07S/p0qG69RcUdOzSoUt0UujS3c0kEQULU7R17c2wyhazNbvaesgHH9xZh3kzb35tpNfrUV/5fJ7+Stls9ks5FqCNKTDv/L6AjyAdiUrWi4AdcAXewDMwwTu4AQdAHbXxCrh3TJm55vpPAVvgHDyC7VEZM6M7sCbRlgGuwaGM8U9zvAkunbn0M/8nzvNpkBHr4MKnqVvHYDWI8RGY84wpFiNFUX5L8sxZlNJRL4E9UeVkMknpdJoSiQQvt9ttKpfLVK1WhVsXbIBb2RHvi96nUikyDGNgyhSPxymTyZCu614D2/UT9bqooqZpnrm6OyNYoNLGy6KKxWKRR/td3W6XCoWCV/sLfoyFJ5Bt29zAfbb3O9TpdLzaZzFND3NkcjUaDapUKoNyvV6nWq0ms7eHM2YqlUrUarXIsiwyTTPwbeX7dmJRs8hVVeXmoRkzNZtNzjCK0pg0MQ5NosX1BKwRetjCb6lcLje2ES+GHDk72G1m/MCu2hCNZ8HrZDv9j33MVtlMiJ58X38KMADfFnDPWur9bAAAAABJRU5ErkJggg=="
                        width="15" height="15" alt="신고 아이콘" />신고하기
                    </button>
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
                      <button id="used-detail-pofile">
                        <i class="fa-solid fa-user"> {{product.nickName}}</i>
                      </button>
                    </div>
                    <div>
                      <button @click="addWish()" class="used-detail-wish">
                        <i class="fa-regular fa-heart"> 찜</i>
                      </button>
                      <button class="used-detail-wish" width="30px">
                        <i class="fa-solid fa-comments"> 채팅하기</i>
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <hr />
      </div>
      <div id="used-detail-info-div">
        <h3 id="used-detail-info">상품정보</h3>
        <hr />
      </div>
      <div>
        {{product.productInfo}}
      </div>
    </div>
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
      imgs: [
        "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcnRs2T%2FbtrG3rPqyGx%2FgvSvyKfokqo8yQomsVjuQK%2Fimg.jpg",
        "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2F5uP60%2FbtrG1yIxNDo%2FMkSJGDgRBgyPwKLI9ScpQ0%2Fimg.jpg",
        "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fq1sNB%2FbtrGYzH9LU7%2FjjirX71ixJbzVZnpWFztZ0%2Fimg.jpg",
        "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcTUnnS%2FbtrGYAmNubZ%2FSJK5DEd6123GknUvGn6ZDK%2Fimg.jpg",
        "https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2F87q2z%2FbtrG5rIA9pm%2FtaxgrZ6iQb1pJdwUltMls1%2Fimg.jpg",
      ],
      product: "",
      email: "",
      img: {},
      // data : {
      //   email : this.$store.state.loginInfo.email,
      //   serviceId : this.$route.query.pNo
      // }
    }),
    filters: {
      comma(val) {
        return String(val).replace(/\B(?=(\d{3})+(?!\d))/g, ',')
      }
    },
    created() {
      axios({
          url: "http://localhost:8090/zippy/used/detail",
          methods: "GET",
          params: {
            pNo: this.$route.query.pNo
          }
        }).then(res => {
          console.log(res);
          this.product = res.data;
          console.log(this.$store.state.loginInfo.email);
          console.log(this.$route.query.pNo)
        }).catch(error => {
          console.log(error);
        }),
        axios({
          url: "http://localhost:8090/zippy/used/getImg",
          methods: "GET",
          params: {
            pNo: this.$route.query.pNo
          }
        }).then(res => {
          console.log(res);
          this.img = res.data;
          console.log(this.img)
        }).catch(err => {
          console.log(err)
        })
    },
    methods: {
      goUpdate(no) {
        console.log(this.product.productNo);
        this.$router.push('/used/update?pNo=' + no);
      },
      search: function (e) {
        var categoryVal = e.target.innerText;
        axios({
          url: "http://localhost:8090/zippy/used/main",
          methods: "GET",
          params: {
            keyword: "",
            location: "",
            category: categoryVal,
            checked: "",
            dropbox: ""
          }
        }).then(res => {
          console.log(res);
          this.data = res.data;
        }).catch(err => {
          console.log(err)
        })
      },
      addWish: function () {
        console.log(this.product.email);
        console.log(this.product.productNo);
        axios({
          url: "http://localhost:8090/zippy/common/addWish",
          method: "POST",
          headers: {
            "Content-Type": "application/json; charset=utf-8"
          },
          data: JSON.stringify(this.data)
        }).then(res => {
          console.log(res);
        }).catch(err => {
          console.log(err)
        })
      },
    }
  }
</script>

<style>
  #container {
    width: 1200px;
    margin: 0 auto;
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
    border: none;
    color: white;
    background-color: #b3e3c3;
    font-size: x-large;
    border-radius: 10px;
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