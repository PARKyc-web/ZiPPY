<template>
  <div id="detail-container">
    <!--slide-->
    <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel" data-bs-interval="false">
      <div class="carousel-indicators">
        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active"
          aria-current="true" aria-label="Slide 1"></button>
        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1"
          aria-label="Slide 2"></button>
        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2"
          aria-label="Slide 3"></button>
      </div>
      <div class="carousel-inner">
        <div class="carousel-item active">
          <img src="http://webimage.10x10.co.kr/image/tenten600/326/T003260178.jpg" class="d-block w-100" alt="...">
        </div>
        <div class="carousel-item">
          <img src="http://webimage.10x10.co.kr/ckeditor/item/202012/20201215_154323_4145.jpg" class="d-block w-100"
            alt="...">
        </div>
        <div class="carousel-item">
          <img
            src="http://thumbnail.10x10.co.kr/webimage/image/add1/250/A002503406_01.jpg?cmd=thumb&w=400&h=400&fit=true&ws=false"
            class="d-block w-100" alt="...">
        </div>
      </div>
      <!--
    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Previous</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Next</span>
    </button>
    -->
    </div>
    <!-- slide end -->
    <!-- 상품 상세정보 -->
    <div id="detail-info">
      <p>예담가구></p>
      <h4 style="display:block; padding-bottom: 30px;">코딩 잘 되는 책상</h4>
      <h3 style="display:inline-block">11,111</h3>
      <h5 style="display:inline-block">원</h5>
      <h5><i class="bi bi-truck" style="color:#B3E3C3;"></i> 배송비<span> 3,000</span>원</h5>
      <div id="total-price">
        <h5 style="padding-right:10px">총 상품금액</h5>
        <h4>14,111</h4>
        <h5>원</h5>
      </div>
      <!-- 옵션 -->
      <div id="detail-opt">
        <div class="dropdown" style="float:left">
          <button class="btn btn-light dropdown-toggle" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown"
            aria-expanded="false" style="width:330px">
            옵션을 선택해주세요
          </button>
          <ul class="dropdown-menu" id="dropdown-opt" aria-labelledby="dropdownMenuButton1" style="width:330px">
            <li><a class="dropdown-item" href="#">white(S size)</a></li>
            <li><a class="dropdown-item" href="#">white(M size)</a></li>
            <li><a class="dropdown-item" href="#">white(L size)</a></li>
          </ul>
        </div>
      </div>
      <!-- 옵션 -->
      <div id="detail-button">
        <button type="button" class="btn btn-primary" style="width:190px">바로구매</button>
        <button type="button" class="btn btn-primary" style="width:63px"><i class="bi bi-cart-fill"></i></button>
        <button type="button" class="btn btn-primary" style="width:63px"><i class="bi bi-heart-fill"></i></button>
      </div>
    </div>
    <!-- 상품 상세정보 -->
    <!-- 탭 -->
    <!-- 탭 메뉴 -->
    <div id="detail-tab">
      <ul class="nav nav-tabs" id="myTab" role="tablist">
        <li class="nav-item" role="presentation">
          <button @click="tab" class="nav-link active" id="info-tab" data-bs-toggle="tab" data-bs-target="#info" type="button"
            role="tab" aria-controls="info" aria-selected="true">상품정보</button>
        </li>
        <li class="nav-item" role="presentation">
          <button @click="tab" class="nav-link" id="review-tab" data-bs-toggle="tab" data-bs-target="#review" type="button"
            role="tab" aria-controls="review" aria-selected="false">상품리뷰(12)</button>
        </li>
        <li class="nav-item" role="presentation">
          <button @click="tab" class="nav-link" id="wishlist-tab" data-bs-toggle="tab" data-bs-target="#qna" type="button" role="tab"
            aria-controls="qna" aria-selected="false">상품문의(13)</button>
        </li>
      </ul>
    </div>
    <!-- 탭 메뉴 끝 -->
    <!-- 상품정보 -->
    <div class="tab-content" id="tabContent">
      <div class="tab-pane show active" id="info" role="tabpanel" aria-labelledby="info-tab">
        여기는 상품정보입니다.여기는 상품정보입니다.여기는 상품정보입니다.여기는 상품정보입니다.여기는 상품정보입니다.
        여기는 상품정보입니다.여기는 상품정보입니다.여기는 상품정보입니다.여기는 상품정보입니다.여기는 상품정보입니다.
        여기는 상품정보입니다.여기는 상품정보입니다.여기는 상품정보입니다.여기는 상품정보입니다.여기는 상품정보입니다.
        여기는 상품정보입니다.여기는 상품정보입니다.여기는 상품정보입니다.여기는 상품정보입니다.여기는 상품정보입니다.
        여기는 상품정보입니다.여기는 상품정보입니다.여기는 상품정보입니다.여기는 상품정보입니다.여기는 상품정보입니다.
        여기는 상품정보입니다.여기는 상품정보입니다.여기는 상품정보입니다.여기는 상품정보입니다.여기는 상품정보입니다.
        여기는 상품정보입니다.여기는 상품정보입니다.여기는 상품정보입니다.여기는 상품정보입니다.여기는 상품정보입니다.
      </div>
      <!-- 상품리뷰 -->
      <div class="tab-pane" id="review" role="tabpanel" aria-labelledby="review-tab">
        <!-- 별점박스 -->
        <div id="star-box">
          <div id="star-left">
            <div style="padding-top:12px">
              <h3 style="padding-right:20px;"><i class="bi bi-star-fill"></i><i class="bi bi-star-fill"></i><i
                  class="bi bi-star-fill"></i><i class="bi bi-star-fill"></i><i class="bi bi-star-half"></i></h3>
            </div>
            <h1 style="font-weight:bold">4.5</h1>
          </div>
          <div id="star-right">
            <div class="progress">
              <div class="progress-bar" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100">
              </div>
            </div>
            <h6>배송</h6>
            <div class="progress">
              <div class="progress-bar" role="progressbar" style="width: 25%" aria-valuenow="25" aria-valuemin="0"
                aria-valuemax="100"></div>
            </div>
            <h6>가격</h6>
            <div class="progress">
              <div class="progress-bar" role="progressbar" style="width: 50%" aria-valuenow="50" aria-valuemin="0"
                aria-valuemax="100"></div>
            </div>
            <h6>디자인</h6>
            <div class="progress">
              <div class="progress-bar" role="progressbar" style="width: 75%" aria-valuenow="75" aria-valuemin="0"
                aria-valuemax="100"></div>
            </div>
            <h6>내구성</h6>
          </div>
        </div>
        <!-- 별점박스 끝 -->
        <hr>
        <!-- 후기박스 -->
        <div id="review-box">
          <div id="review-button-box">
            <p><i class="bi bi-star-fill"></i><i class="bi bi-star-fill"></i><i class="bi bi-star-fill"></i><i
                class="bi bi-star-fill"></i><i class="bi bi-star-fill" style="padding-right:5px;"></i>5.0</p>
            <div id="user-info">
              <p>yed***@gmail.com</p>|<p>2022-10-04</p>|
              <div id="report-review">
                <p>신고 <i class="bi bi-slash-circle-fill"></i></p>
              </div>
            </div>
          </div>
          <p>옵션:white(L size)</p>
          <p>코딩이 잘 되는지 모르겠음 사기당함 코딩이 잘 되는지 모르겠음 사기당함 코딩이 잘 되는지 모르겠음 사기당함 코딩이 잘 되는지 모르겠음 사기당함 코딩이 잘 되는지 모르겠음 사기당함 코딩이 잘
            되는지
            모르겠음 사기당함 </p>
        </div>
        <!-- 후기박스 끝 -->
        <hr>
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
      <!-- 상품리뷰 시작 -->
      <div class="tab-pane" id="qna" role="tabpanel" aria-labelledby="qna-tab">
        <div id="qna-button">
          <p>문의하기를 통해 궁금증을 해결하세요.</p>
          <!-- 모달 버튼 -->
          <button type="button" class="btn btn-primary" data-bs-toggle="modal"
            data-bs-target="#exampleModal">상품문의</button>
          <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel"
            aria-hidden="true">
            <div class="modal-dialog">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title" id="exampleModalLabel">상품문의</h5>
                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                  <form>
                    <div class="mb-3">
                      <label for="recipient-name" class="col-form-label"></label>
                      <div class="dropdown" style="float:left">
                        <button class="btn btn-light dropdown-toggle" type="button" id="dropdownMenuButton1"
                          data-bs-toggle="dropdown" aria-expanded="false">
                          문의유형을 선택해주세요
                        </button>
                        <ul class="dropdown-menu" id="dropdown-opt" aria-labelledby="dropdownMenuButton1">
                          <li><a class="dropdown-item" href="#">상품문의</a></li>
                          <li><a class="dropdown-item" href="#">배송문의</a></li>
                          <li><a class="dropdown-item" href="#">기타문의</a></li>
                        </ul>
                      </div>
                    </div>
                    <div class="mb-3">
                      <label for="message-text" class="col-form-label"></label>
                      <textarea class="form-control" id="message-text"></textarea>
                    </div>
                  </form>
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-primary">등록</button>
                </div>
              </div>
            </div>
          </div>
          <!-- 모달 버튼 끝-->
          <hr>
        </div>
        <div id="qna-content">
          <!-- 아코디언 -->
          <div class="accordion" id="accordionExample">
            <!-- qna1 -->
            <div class="accordion-item">
              <h2 class="accordion-header" id="headingOne">
                <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne"
                  aria-expanded="true" aria-controls="collapseOne">
                  <div id="qna-title">
                    <p style="padding-right:5px">완료</p>|
                    <p style="padding-left:5px">상품문의입니다.</p>
                    <div id="user-info">
                      <p>yed***@gmail.com</p>|
                      <p>2022.10.04</p>
                    </div>
                  </div>
                </button>
              </h2>
              <div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne"
                data-bs-parent="#accordionExample">
                <div class="accordion-body">
                  <div class="qna" id=qna-q>
                    <p>상품이 이상해요</p>
                  </div>
                  <div class="qna" id="qna-a">
                    <p><i class="bi bi-arrow-return-right" style="padding-right: 10px;"></i>그럴리가</p>
                  </div>
                </div>
              </div>
            </div>
            <!-- qna1 end -->
            <!-- qna3 -->
            <div class="accordion-item">
              <h2 class="accordion-header" id="headingThree">
                <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseThree"
                  aria-expanded="true" aria-controls="collapseOne">
                  <div id="qna-title">
                    <p style="padding-right:5px">완료</p>|
                    <p style="padding-left:5px">상품문의입니다.</p>
                    <div id="user-info">
                      <p>yed***@gmail.com</p>|
                      <p>2022.10.04</p>
                    </div>
                  </div>
                </button>
              </h2>
              <div id="collapseThree" class="accordion-collapse collapse show" aria-labelledby="headingThree"
                data-bs-parent="#accordionExample">
                <div class="accordion-body">
                  <div class="qna" id=qna-q>
                    <p>상품이 이상해요</p>
                  </div>
                  <div class="qna" id="qna-a">
                    <p><i class="bi bi-arrow-return-right" style="padding-right: 10px;"></i>그럴리가</p>
                  </div>
                </div>
              </div>
            </div>
            <!-- qna3 end -->
          </div>
          <!-- 아코디언 끝 -->
        </div>
        <hr>
        <div id="seller-content">
          <p style="font-size:smaller">먼저 문의하신 다른 고객님들의 질문을 참조하시면, 유용한 정보를 빠르게 확인하실 수 있습니다.</p>
          <p style="font-size:smaller">휴일 및 공휴일, 영업시간 종료 이후에 남겨주신 문의 사항은, 평일 영업시간에 답변을 해드리고 있습니다.</p>
        </div>
      </div>
      <!-- 상품리뷰 끝 -->
    </div>
    <!-- 탭 내용 끝 -->
    <hr>
  </div>
</template>

<script>
  import $ from 'jquery';
  export default ({
    method: {
      tab: function () {
        let key = "${param.key}";
        if (key === "info") {

          $("#review-tab").removeClass("active");
          $("#qna-tab").removeClass("active");
          $("#info-tab").addClass("active");

          $("#review").removeClass("show active");
          $("#qna").removeClass("show active");
          $("#info").addClass("show active");

        } else if (key === "review") {

          $("#qna-tab").removeClass("active");
          $("#info-tab").removeClass("active");
          $("#review-tab").addClass("active");

          $("#info").removeClass("show active");
          $("#qna").removeClass("show active");
          $("#review").addClass("show active");

        } else if (key === "qna") {

          $("#info-tab").removeClass("active");
          $("#review-tab").removeClass("active");
          $("#qna-tab").addClass("active");

          $("#review").removeClass("show active");
          $("#info").removeClass("show active");
          $("#qna").addClass("show active");

        }
      }
    }
  });
</script>

<style scoped>
   p {
    letter-spacing: -1px;
  }
  #detail-container{
    width:850px;
    display:flex;
    margin:0 auto;
    flex-wrap: wrap;
    padding-top: 100px;
    padding-bottom: 100px;
  }
  /* 디테일 슬라이드 */
  .carousel-inner{
    width:430px;
    height:400px;
    margin: 0 auto;
    border-radius: 1em;
  }
  .carousel-inner img{
    width:430%;
    height:400px;
  }
  /* 상세설명 */
  #detail-info{
    width:410px;
    height:400px;
    padding:40px 40px 0 40px;
  }
  #detail-opt{
    width:330px;
    height:38px;
  }
  #detail-button{
    width:330px;
    clear: both;
  }
  #total-price{
    padding-top: 30px;
    padding-bottom: 10px;
    text-align: right;
  }
  #total-price h5, h4{
    display: inline-block;
  }
  #detail-info span{
    font-weight: bold;
  }
  #detail-info h3{
    font-weight: bold;
  }
  #detail-button {
    text-align: right;
  }
  #detail-button button{
    height: 38px;
  }
  /* 탭 */
  #detail-tab{
    width:840px;
    margin: 0 auto;
  }
  #tabContent{
    padding: 30px;
    min-height: 500px;
    /* 최솟값 지정후 없으면 없다는 표시 띄우기 */
  }
  /* 리뷰 */
  #star-box{
    width:780px;
    height:230px;
    padding:40px;
    text-align: center;
    display: flex;
  }
  #star-left{
    width:390px;
    height:150px;
    padding:48px;
    display:flex;
    color:#212529;
  }
  #star-right{
    padding-left:20px;
    width:390px;
    height:150px;
    text-align: left;;
  }
  .progress-bar{
    width:200px;
    background-color: #B3E3C3;
  }
  .progress{
    width:200px;
  }
  #star-right h6{
    font-size:smaller;
  }
  /* 리뷰 내용 */
  #review-box{
    width:780px;
    padding:20px;
  }
  #review-button-box{
    width:740px;
    height:40px;
    display: flex;
  }
  #user-info{
    display: flex;
    margin-left:auto;
  }
  #user-info p{
    padding: 0 5px 0 5px;
  }
  #report-review:hover{
    text-decoration: underline;
    cursor: pointer;
  }
  /* qna */
  #qna-button{
    display: flex;
    height:38px;
  }
  #qna-button .btn{
    margin-left:auto;
  }
  #qna-content {
    width:780px;
    min-height: 200px;

    padding-top:40px;
    /* 최솟값 지정후 없으면 없다는 표시 띄우기 */
  }
  
  #seller-content{
    font-size:smaller;
    line-height: 2px;
  }
  .accordion-body {
    background-color: #e4e4e4;
    padding:30px;
  }
  .accordion {
    width:780px
  }
  #qna-title{
    display: flex;
    width:780px;
  }
  .accordion-button::after {
    display: none;
  }
  .qna{
    padding:10px;
  }
  #qna-title p{
    margin:0;
  }
</style>

<link href="../../assets/style.css" rel="stylesheet"></link>