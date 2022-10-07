<template>
  <div id="main-container">
    <!-- 카테고리 -->
    <div id="category">
      <hr />
      <div id="category-list">
        <h6>침대</h6>
        <h6>매트리스/토퍼</h6>
        <h6>소파</h6>
        <h6>테이블/식탁/책상</h6>
        <h6>서랍/수납장</h6>
        <h6>책장</h6>
        <h6>의자</h6>
        <h6>거울</h6>
        <h6>조명</h6>
      </div>
      <hr />
    </div>
    <!-- 카테고리 끝 -->
    <!-- list container -->
    <div id="list-container">
      <!-- 옵션 -->
      <v-container>
        <v-overflow-btn
          id="drop"
          class="my-2"
          :items="items"
          label="판매순"
          dense
        ></v-overflow-btn>
      </v-container>
      <!-- 상품리스트 -->
      <div id="product-list">
        <div id="product-info" v-for="i in 6" :key="6">
          <div id="product-img">
            <img
              src="http://webimage.10x10.co.kr/ckeditor/item/202012/20201215_154323_4145.jpg"
            />
          </div>
          <div class="product-about">
            <h6 class="product-seller-name">예담가구</h6>
            <h6 class="product-seller-name" id="product-name">딱딱의자</h6>
            <h5 class="pt-1">11,111</h5>
            <h6 class="product-seller-name">
              <v-icon style="font-size: 15px">mdi-star</v-icon> 4.5(10)
            </h6>
          </div>
        </div>
      </div>
      <!-- 상품리스트 끝 -->
      <!-- 페이지네이션 -->
      <div class="pb-5">
        <div class="text-center">
          <v-pagination
            v-model="page"
            :length="4"
            circle
            color="#B3E3C3"
          ></v-pagination>
        </div>
      </div>
      <!-- 페이지네이션 끝 -->
    </div>
    <!-- list container end -->
  </div>
</template>

<script>
export default {
  data: () => ({
    items: ["판매순", "최신순", "리뷰순", "별점순"],
    page: 1,
  }),
};
</script>

<style scoped>
#main-container {
  display: flex;
}
/* 카테고리 */
#category {
  width: 400px;
  height: 600px;
  padding: 60px;
}
#category h6 {
  padding: 10px;
}
#category-list h6 {
  font-weight: bold;
}
#category-list h6:hover {
  color: #858585;
  cursor: pointer;
}
/* 드롭다운(옵션) */
.v-overflow-btn {
  float: right;
  width: 100px;
}
::v-deep .my-2 .v-label {
  font-size: 13px;
  color: rgb(0, 0, 0, 0.87);
}
/* 키워드 */
#keyword {
  text-align: center;
  margin-top: 50px;
}
#keyword span {
  color: #b3e3c3;
}
/* 상품리스트 */
#list-container {
  width: 920px;
  margin-left: 50px;
}
#list-container h5,
h4,
h6 {
  font-weight: bold;
  margin-bottom: 3px;
}
#product-list {
  clear: both;
  margin-right: auto;
  display: flex;
  flex-wrap: wrap;
}
#product-list i {
  color: #b3e3c3;
}
.product-seller-name {
  font-size: smaller;
  color: black;
  font-weight: normal;
}
#product-info {
  height: 440px;
  padding-right: 25px;
  margin-bottom: 50px;
}
#product-name:hover {
  text-decoration: underline;
  cursor: pointer;
}
.product-about {
  margin-top: 10px;
}
#product-info:hover {
  cursor: pointer;
}
#product-info span {
  color: 858585;
}
#product-info:nth-child(3n) {
  padding-right: 0;
}
#product-info img {
  width: 290px;
  height: 320px;
  max-width: 100%;
  transition: all 0.2s linear;
  border-radius: 0.8em;
}
#product-img {
  width: 290px;
  height: 320px;
  margin: 0px auto;
  overflow: hidden;
  border-radius: 0.8em;
}
#product-info:hover img {
  transform: scale(1.05);
}
</style>
