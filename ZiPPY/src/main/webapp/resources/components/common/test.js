let template = `<!-- Navigation-->
<div>
<nav class="navbar navbar-expand-lg navbar-light">
<div class="container px-4 px-lg-5">
<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
<div class="collapse navbar-collapse" id="navbarSupportedContent">
   <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
       <li class="nav-item"><a class="nav-link active" aria-current="page" href="#!">홈</a></li>
       <!--dropdown-->
       <li class="nav-item dropdown">
           <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">카테고리</a>
           <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
               <li><a class="dropdown-item" href="#!">침대</a></li>
               <li><a class="dropdown-item" href="#!">매트리스/토퍼</a></li>
               <li><a class="dropdown-item" href="#!">테이블/식탁/책상</a></li>
               <li><a class="dropdown-item" href="#!">서랍/수납장</a></li>
               <li><a class="dropdown-item" href="#!">책장</a></li>
               <li><a class="dropdown-item" href="#!">의자</a></li>
               <li><a class="dropdown-item" href="#!">거울</a></li>
               <li><a class="dropdown-item" href="#!">조명</a></li>
               <li><a class="dropdown-item" href="#!">소품</a></li>
           </ul>
       </li>
   </ul>
</div>
  <!--Search-->
  <form class="d-flex">
    <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
    <button class="btn btn-outline-success" type="submit">Search</button>
  </form>
  <!--cart-->
  <div id="cart">
    <form class="d-flex">
      <button class="btn btn-outline-dark" type="submit">
          <i class="bi-cart-fill me-1"></i>
          장바구니
          <span class="badge bg-dark text-white ms-1 rounded-pill">0</span>
      </button>
    </form>
  </div>
</nav>
<!--slide-->
<div id="slide">
  <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
    <div class="carousel-indicators">
      <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
      <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
      <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
    </div>
    <div class="carousel-inner">
      <div class="carousel-item active" data-bs-interval="2000">
        <img src="https://i.pinimg.com/originals/75/e5/7d/75e57dcdc8d24fa66618e5be2de5df26.jpg" class="d-block w-100" alt="...">
      </div>
      <div class="carousel-item" data-bs-interval="2000">
        <img src="https://bucketplace-v2-development.s3.amazonaws.com/uploads/cards/snapshots/156464359778234508.jpeg" class="d-block w-100" alt="...">
      </div>
      <div class="carousel-item" data-bs-interval="2000">
        <img src="https://i.pinimg.com/originals/57/cd/0f/57cd0f38e0361a93c57128c551fb5df8.jpg" class="d-block w-100" alt="...">
      </div>
    </div>
    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
      <span class="carousel-control-prev-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Previous</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
      <span class="carousel-control-next-icon" aria-hidden="true"></span>
      <span class="visually-hidden">Next</span>
    </button>
  </div>
</div>
<!-- Product List -->
<section>
<h4>BEST</h4>
<div class="container-fluid pt-5">
<div class="row px-xl-5 pb-3">
    <div class="col-lg-4 col-md-6 pb-1">
        <div class="cat-item d-flex flex-column border mb-4" style="padding: 30px;">
            <a href="" class="cat-img position-relative overflow-hidden mb-3">
                <img class="img-fluid" src="https://www.westelm.co.kr/upload/goods/G100007617/G100007617_01_1_1000.jpg" alt="">
            </a>
            <h6 class>예담가구</h6>
            <h6 class="fw-bold">푹신푹신 침대</h5>
            <h5 class="fw-bold text-right">1,000,000<span class="fw-normal">원</span ></h5>
        </div>
    </div>
    <div class="col-lg-4 col-md-6 pb-1">
        <div class="cat-item d-flex flex-column border mb-4" style="padding: 30px;">
            <a href="" class="cat-img position-relative overflow-hidden mb-3">
                <img class="img-fluid" src="https://www.westelm.co.kr/upload/goods/G100007617/G100007617_01_1_1000.jpg" alt="">
            </a>
            <h6 class>예담가구</h6>
            <h6 class="fw-bold">푹신푹신 침대</h5>
            <h5 class="fw-bold text-right">1,000,000<span class="fw-normal">원</span ></h5>
        </div>
    </div>
    <div class="col-lg-4 col-md-6 pb-1">
        <div class="cat-item d-flex flex-column border mb-4" style="padding: 30px;">
            <a href="" class="cat-img position-relative overflow-hidden mb-3">
                <img class="img-fluid" src="https://www.westelm.co.kr/upload/goods/G100007617/G100007617_01_1_1000.jpg" alt="">
            </a>
            <h6 class>예담가구</h6>
            <h6 class="fw-bold">푹신푹신 침대</h5>
            <h5 class="fw-bold text-right">1,000,000<span class="fw-normal">원</span ></h5>
        </div>
    </div>
    <div class="col-lg-4 col-md-6 pb-1">
        <div class="cat-item d-flex flex-column border mb-4" style="padding: 30px;">
            <a href="" class="cat-img position-relative overflow-hidden mb-3">
                <img class="img-fluid" src="https://www.westelm.co.kr/upload/goods/G100007617/G100007617_01_1_1000.jpg" alt="">
            </a>
            <h6 class>예담가구</h6>
            <h6 class="fw-bold">푹신푹신 침대</h5>
            <h5 class="fw-bold text-right">1,000,000<span class="fw-normal">원</span ></h5>
        </div>
    </div>
    <div class="col-lg-4 col-md-6 pb-1">
        <div class="cat-item d-flex flex-column border mb-4" style="padding: 30px;">
            <a href="" class="cat-img position-relative overflow-hidden mb-3">
                <img class="img-fluid" src="https://www.westelm.co.kr/upload/goods/G100007617/G100007617_01_1_1000.jpg" alt="">
            </a>
            <h6 class>예담가구</h6>
            <h6 class="fw-bold">푹신푹신 침대</h5>
            <h5 class="fw-bold text-right">1,000,000<span class="fw-normal">원</span ></h5>
        </div>
    </div>
    <div class="col-lg-4 col-md-6 pb-1">
        <div class="cat-item d-flex flex-column border mb-4" style="padding: 30px;">
            <a href="" class="cat-img position-relative overflow-hidden mb-3">
                <img class="img-fluid" src="https://www.westelm.co.kr/upload/goods/G100007617/G100007617_01_1_1000.jpg" alt="">
            </a>
            <h6 class>예담가구</h6>
            <h6 class="fw-bold">푹신푹신 침대</h5>
            <h5 class="fw-bold text-right">1,000,000<span class="fw-normal">원</span ></h5>
        </div>
    </div>
</div>
</div>
</section>
<!-- Categories End --></div>`

export default {
  template
}