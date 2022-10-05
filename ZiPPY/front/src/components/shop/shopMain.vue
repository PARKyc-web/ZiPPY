<template>
  <div>
    <div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel">
      <div class="carousel-indicators">
        <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active"
          aria-current="true" aria-label="Slide 1"></button>
        <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1"
          aria-label="Slide 2"></button>
        <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2"
          aria-label="Slide 3"></button>
      </div>
      <div class="carousel-inner">
        <div class="carousel-item active">
          <img
            src="https://media.wired.com/photos/5f6e51ec1518e2504fb04020/master/w_2580%2Cc_limit/Gear-Eliot-sofa-SOURCE-Joybird.jpg"
            class="d-block w-100" alt="...">
          <div class="carousel-caption d-none d-md-block">
            <h3>가구는 역시 ZIPPY</h3>
            <p>Some representative placeholder content for the first slide.</p>
          </div>
        </div>
        <div class="carousel-item">
          <img
            src="https://media.architecturaldigest.com/photos/620d0e4a330119b3eec5f131/3:2/w_3000,h_2000,c_limit/2-15%20best%20desk%20lamps%20v2.jpg"
            class="d-block w-100" alt="...">
          <div class="carousel-caption d-none d-md-block">
            <h3>가구는 역시 ZIPPY</h3>
            <p>Some representative placeholder content for the second slide.</p>
          </div>
        </div>
        <div class="carousel-item">
          <img
            src="https://sc01.alicdn.com/kf/Hf5802540a4b142d89f9ef6026e2f8a4fX/231809263/Hf5802540a4b142d89f9ef6026e2f8a4fX.jpg"
            class="d-block w-100" alt="...">
          <div class="carousel-caption d-none d-md-block">
            <h3>가구는 역시 ZIPPY</h3>
            <p>Some representative placeholder content for the third slide.</p>
          </div>
        </div>
      </div>
      <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators"
        data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
      </button>
      <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators"
        data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
      </button>
    </div>
    <!-- 아이템 리스트 -->
    <div class="main-item-list">
      <div id="list-name">
        <h2 style="color:#aaa">#BEST</h2>
      </div>
      <div id="main-item" v-for="i in 4" :key="4">
        <div id="main-product-img">
          <img src="http://webimage.10x10.co.kr/ckeditor/item/202012/20201215_154323_4145.jpg">
        </div>
        <div class="product-about" id="left">
          <h6 id="product-name">딱딱의자</h6>
          <h6>11,111</h6>
        </div>
      </div>
    </div>
    <div class="main-item-color" id="main-color1">
      <div class="main-item-list">
        <div id="list-name" style="margin:0 auto">
          <h2 style="color:#fff; text-align: center;">이런 건 어떠세요?</h2>
        </div>
        <div id="main-item" v-for="i in 4" :key="4">
          <div id="main-product-img">
            <img src="http://webimage.10x10.co.kr/ckeditor/item/202012/20201215_154323_4145.jpg">
          </div>
          <div class="product-about" id="left">
            <h6 id="product-name">딱딱의자</h6>
            <h6>11,111</h6>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {

  };
</script>

<style scoped>
  /* 슬라이드 */
  #default-container {
    width: 70%;
    margin: 0 auto;
  }

  .carousel-inner {
    width: 70%;
    height: 400px;
    margin: 0 auto;
    border-radius: 1.5em;
  }

  .carousel-inner img {
    width: 70%;
    height: 400px;
  }

  /* 아이템 리스트 */
  /*** 아마 공통? ***/
  #left{
    text-align: left;
  }
  .product-about{
  margin-top: 10px;
  }
  #product-name:hover{
  text-decoration: underline;
  cursor:pointer;
  }
  /*** 아마 공통? ***/

  .main-item-list {
    width: 70%;
    height: 530px;
    margin: 0 auto;
    margin-top: 60px;
    text-align: center;
  }

  #list-name {
    padding: 70px 30px 30px 0;
  }

  #list-name h2 {
    font-weight: bold;
  }

  .main-item-color {
    height: 530px;
    margin-top: 150px;
  }

  #main-color1 {
    background-color: #B3E3C3;
  }

  #main-item {
    padding-right: 21px;
    display: inline-block;
    cursor: pointer;
  }

  #main-product-img img {
    width: 270px;
    height: 300px;
    max-width: 100%;
    transition: all 0.2s linear;
    border-radius: 0.8em;
  }

  #main-product-img {
    width: 270px;
    height: 300px;
    border-radius: 0.8em;
    margin: 0px auto;
    overflow: hidden;
  }

  #main-product-img:hover img {
    transform: scale(1.05);
  }
</style>