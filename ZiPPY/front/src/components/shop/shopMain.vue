<template>
  <div>
    <!-- 캐러셀 시작 -->
    <div>
      <v-carousel
        cycle
        height="500"
        hide-delimiter-background
        :show-arrows="false"
        class="mt-5"
      >
        <v-carousel-item v-for="(slide, i) in slides" :key="i">
          <v-img :src="imgs[i]" height="100%">
            <v-row class="fill-height" align="center" justify="center">
              <div class="text-h2" id="slide-text">
                {{ slide }}
              </div>
            </v-row>
          </v-img>
        </v-carousel-item>
      </v-carousel>
    </div>
    <!-- 캐러셀 끝 -->
    <div id="main-background"></div>
    <!-- 아이템 리스트 -->
    <div class="main-item-list">
      <div id="list-name">
        <h2 style="color: #b3e3c3">#BEST</h2>
      </div>
      <div id="main-item" v-for="i in 4" :key="4">
        <div id="main-product-img">
          <img
            src="http://webimage.10x10.co.kr/ckeditor/item/202012/20201215_154323_4145.jpg"
          />
        </div>
        <div class="product-about" id="left">
          <h6 id="product-name">딱딱의자</h6>
          <h6>11,111</h6>
        </div>
      </div>
    </div>
    <div class="main-item-color" id="main-color1">
      <div class="main-item-list">
        <div id="list-name" style="margin: 0 auto">
          <h2 style="color: #212529; text-align: center">이런 건 어떠세요?</h2>
        </div>
        <div id="main-item" v-for="i in 4" :key="4">
          <div id="main-product-img">
            <img
              src="http://webimage.10x10.co.kr/ckeditor/item/202012/20201215_154323_4145.jpg"
            />
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
  data() {
    return {
      imgs: [
        "https://cdn1.epicgames.com/ue/product/Screenshot/SF039-1920x1080-466e1033381d9706717c5d877d67bd29.jpeg?resize=1&w=1920",
        "https://i.pinimg.com/originals/e3/50/d2/e350d23264d7c1bbc0b9c03489e9367d.jpg",
        "https://rare-gallery.com/mocahbig/22117-Living-Room-FurnitureRoom-4k-Ultra-HD-Wallpaper.jpg",
        "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/best-accent-chair-1647958244.jpg",
        "https://images6.alphacoders.com/647/647568.jpg",
      ],
      slides: ["First", "Second", "Third", "Fourth", "Fifth"],
    };
  },
};
</script>

<style scoped>
/* 슬라이드 */
.v-carousel {
  width: 70%;
  border-radius: 1.5em;
  margin: 0 auto;
  position: relative;
  z-index: 1;
}
#main-background {
  width: 100%;
  height: 450px;
  background-color: #b3e3c3;
  position: absolute;
  top: 0;
  z-index: 0;
}
#slide-text {
  color: #fff;
  margin: 300px 0 0 200px;
  font-weight: bold;
}
/* 아이템 리스트 */
/*** 아마 공통? ***/
#left {
  text-align: left;
}

.product-about {
  margin-top: 10px;
}

#product-name:hover {
  text-decoration: underline;
  cursor: pointer;
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
  background-color: #f7f7f7;
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
