<template>
  <div id="container">
    <nav-bar @click="search($event)"></nav-bar>
    <div>
      <div class="used-main-title">
        <h3>찜 목록</h3>
      </div>
    </div>
    <div id="used-usedwishckdel">
      <div id="used-wish-chkbox">
        <p class="chk_box">
          <input type="checkbox" id="chk_top" />
          <label for="chk_top"></label>
        </p>
      </div>
      <div id="used-wish-del-div">
        <button class="used-wish-del-btn">선택삭제</button>
      </div>
    </div>
    <hr />
    <div class="used-wish-cont">
      <div class="used-wish-cont-div">
        <div id="used-wish-product">
          <div class="used-wish-img-1">
            <img
              src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcnRs2T%2FbtrG3rPqyGx%2FgvSvyKfokqo8yQomsVjuQK%2Fimg.jpg"
              width="200px"
              height="200px"
              alt=""
            />
            <div id="used-wish-info">
              <div>
                <button class="used-wish-heart">
                  <i class="bi bi-heart-fill"></i>
                </button>
              </div>
              <div><span>너무 하기 싫네요</span></div>
              <div class="used-wish-card-cont"><span>999,999원</span></div>
              <hr />
              <div>
                <span>거래지역</span>
                <span>대구</span>
              </div>
            </div>
          </div>
          <div></div>
        </div>
      </div>
      <div class="used-wish-cont-div">
        <div id="used-wish-product">
          <div class="used-wish-img-1">
            <img
              src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcnRs2T%2FbtrG3rPqyGx%2FgvSvyKfokqo8yQomsVjuQK%2Fimg.jpg"
              width="200px"
              height="200px"
              alt=""
            />
            <div id="used-wish-info">
              <div>
                <button class="used-wish-heart">
                  <i class="bi bi-heart-fill"></i>
                </button>
              </div>
              <div><span>너무 하기 싫네요</span></div>
              <div class="used-wish-card-cont"><span>999,999원</span></div>
              <hr />
              <div>
                <span>거래지역</span>
                <span>대구</span>
              </div>
            </div>
          </div>
          <div></div>
        </div>
      </div>
      <div class="used-wish-cont-div">
        <div id="used-wish-product">
          <div class="used-wish-img-1">
            <img
              src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcnRs2T%2FbtrG3rPqyGx%2FgvSvyKfokqo8yQomsVjuQK%2Fimg.jpg"
              width="200px"
              height="200px"
              alt=""
            />
            <div id="used-wish-info">
              <div>
                <button class="used-wish-heart">
                  <i class="bi bi-heart-fill"></i>
                </button>
              </div>
              <div><span>너무 하기 싫네요</span></div>
              <div class="used-wish-card-cont"><span>999,999원</span></div>
              <hr />
              <div>
                <span>거래지역</span>
                <span>대구</span>
              </div>
            </div>
          </div>
          <div></div>
        </div>
      </div>
      <div class="used-wish-cont-div">
        <div id="used-wish-product">
          <div class="used-wish-img-1">
            <img
              src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcnRs2T%2FbtrG3rPqyGx%2FgvSvyKfokqo8yQomsVjuQK%2Fimg.jpg"
              width="200px"
              height="200px"
              alt=""
            />
            <div id="used-wish-info">
              <div>
                <button class="used-wish-heart">
                  <i class="bi bi-heart-fill"></i>
                </button>
              </div>
              <div><span>너무 하기 싫네요</span></div>
              <div class="used-wish-card-cont"><span>999,999원</span></div>
              <hr />
              <div>
                <span>거래지역</span>
                <span>대구</span>
              </div>
            </div>
          </div>
          <div></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import axios from 'axios';
  import navBar from '../../components/used/navBar.vue';
  
export default {};
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

input#chk_top {
  display: none;
}

/*input 바로 다음의 label*/
input#chk_top + label {
  cursor: pointer;
}

/*input 바로 다음의 label:before 에 체크하기 전 CSS 설정*/
input#chk_top + label:before {
  content: "";
  display: inline-block;
  width: 17px;
  height: 17px;
  line-height: 17px;
  border: 1px solid #cbcbcb;
  vertical-align: middle;
  /*체크 전과 체크 후 높이 차이 때문에 설정*/
}

/*checked된 input 바로 다음의 label:before 에 체크 후 CSS 설정*/
input#chk_top:checked + label:before {
  content: "\f00c";
  /*폰트어썸 유니코드*/
  font-family: "Font Awesome 5 free";
  /*폰트어썸 아이콘 사용*/
  font-weight: 900;
  /*폰트어썸 설정*/
  color: #fff;
  background-color: #b3e3c3;
  border-color: #b3e3c3;
  font-size: 13px;
  text-align: center;
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
}

.used-wish-del-btn:hover {
  background-color: #b3e3c3;
}

#used-wish-del-div {
  display: flex;
  margin-left: 10px;
}

#used-usedwishckdel {
  display: flex;
}

#used-wish-product {
  display: inline-block;
  border: 1px solid grey;
  width: 500px;
  height: 200px;
  margin-right: 20px;
}

.used-wish-heart {
  float: right;
  background-color: white;
  border: none;
}

#used-wish-info {
  padding: 15px;
  width: 500px;
}

.used-wish-img-1 {
  display: flex;
}

.used-wish-cont {
  padding: 20px;
  width: 1100px;
  margin: 0 auto;
  display: inline;
}

.used-wish-card-cont {
  font-size: large;
  font-weight: bolder;
  margin-top: 5px;
}
</style>
