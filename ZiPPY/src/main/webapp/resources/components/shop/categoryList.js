let template =
`
<div id="main-container">
  <!-- 카테고리 -->
  <div id="category">
    <hr>
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
    <hr>
  </div>
  <!-- 카테고리 끝 -->
  <!-- list container -->
  <div id="list-container">
      <!-- 옵션 -->
      <div class="dropdown">
        <button class="btn btn-light dropdown-toggle" style="margin-top:15px; width:370x;" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
          인기순
        </button>
        <ul class="dropdown-menu" id="dropdown-opt" aria-labelledby="dropdownMenuButton1">
          <li><a class="dropdown-item" href="#">인기순</a></li>
          <li><a class="dropdown-item" href="#">최신순</a></li>
          <li><a class="dropdown-item" href="#">리뷰순</a></li>
          <li><a class="dropdown-item" href="#">별점순</a></li>
        </ul>
      </div>
      <!--옵션 끝-->
      <!-- 상품리스트 -->
      <div id="product-list">
          <div id="product-info" v-for="i in 6" :key="6">
            <div id="product-img">
              <img src="http://webimage.10x10.co.kr/ckeditor/item/202012/20201215_154323_4145.jpg">
            </div>
            <div id="product-about">
              <h6 id="small" style="color:#aaa">예담가구</h6>
              <h6 id="product-name">딱딱의자</h6>
              <h6>11,111</h6>
              <h6 id="small"><i class="bi bi-star-fill"></i> 4.5(10)</h6>
            </div>
          </div>
        </div>
        <!-- 상품리스트 끝 -->
        <!-- 페이지네이션 -->
        <div id="pagination">
          <nav aria-label="...">
            <ul class="pagination justify-content-center">
              <li class="page-item disabled">
                <a class="page-link" href="#" tabindex="-1" aria-disabled="true">&lt;</a>
              </li>
              <li class="page-item"><a class="page-link" href="#">1</a></li>
              <li class="page-item active" aria-current="page">
                <a class="page-link" href="#">2</a>
              </li>
              <li class="page-item"><a class="page-link" href="#">3</a></li>
              <li class="page-item">
                <a class="page-link" href="#">&gt;</a>
              </li>
           </ul>
        </nav>
      </div>
        <!-- 페이지네이션 끝 -->
    </div>
    <!-- list container end -->
  </div>`
  
    export default {
    name: 'category-list',
    template : template
}