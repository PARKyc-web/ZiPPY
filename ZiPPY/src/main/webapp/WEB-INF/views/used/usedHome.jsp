<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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

      @media (min-width : 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }

      #used-add-drop-search {
        display: flex;
        justify-content: space-between;
        height: 40px;
        margin-top: 10px;
      }

      .b-example-divider {
        height: 3rem;
        background-color: rgba(0, 0, 0, .1);
        border: solid rgba(0, 0, 0, .15);
        border-width: 1px 0;
        box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em rgba(0, 0, 0, .15);
      }

      .b-example-vr {
        flex-shrink: 0;
        width: 1.5rem;
        height: 100vh;
      }

      .bi {
        vertical-align: -.125em;
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
        color: #B3E3C3;
      }

      .nav-item dropdown {
        float: right;
      }

      .used-dropdown {
        list-style: none;
      }

      .dropdown-menu a:hover {
        background-color: #B3E3C3;
      }

      #navbarDropdown {
        color: #212529;
      }

      .thumbnail-wrap {
        display: block;
      }

      #sold-out {
        float: right;
        padding-top: 22px;
        padding-right: 20px;
      }

      .card-li {
        border: 1px solid black;
        list-style: none;
        float: left;
        margin: 30px 10px 10px 10px;
      }

      #used-price-date {
        display: flex;
        justify-content: space-between;

      }

      .used-img-price {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin: 20px;
      }

      .container-fluid pt-5 {
        width: 1200px;
      }

      .form-control {
        width: 150px;
      }

      .used-addr {
        cursor: pointer;
      }

      .cat-item d-flex flex-column border mb-4 {
        border-radius: 10px;
      }

      #used-addr {
        border: none;
        background-color: white;
      }
    </style>
</head>
<body>
	<div id="container">
      <div class="nav-scroller bg-body shadow-sm">
        <nav class="nav" aria-label="Secondary navigation">
          <a class="nav-link active" aria-current="page" href="#">전체</a> <a class="nav-link" href="#">침실</a> <a
            class="nav-link" href="#">옷장/수납</a>
          <a class="nav-link" href="#">주방</a> <a class="nav-link" href="#">욕실</a>
          <a class="nav-link" href="#">서재</a> <a class="nav-link" href="#">다용도실</a>
        </nav>
      </div>
      <div>
        <div class="used-main-title">
          <h3>판매중인 중고제품</h3>
        </div>
        <div id="used-add-drop-search">
          <button id="used-addr">
            <i class="bi bi-geo-alt"></i>
          </button>
          <div>
            <!--Search-->
            <form class="d-flex">
              <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
              <button class="btn btn-outline-success" type="submit"><i class="bi bi-search"></i></button>
            </form>
          </div>
        </div>
      </div>
      </nav>

      <hr>
      <div id="used-soldot-drop">
        <label id="sold-out"><input type="checkbox" name="판매완료된 상품 보기"> 판매완료된 상품 보기</label>
        <nav class="navbar navbar-expand-lg navbar-light">
          <ul class="used-dropdown">
            <!--dropdown-->
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown"
                aria-expanded="false">정렬</a>
              <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                <li><a class="dropdown-item" href="#!">최고가순</a></li>
                <li><a class="dropdown-item" href="#!">최저가순</a></li>
                <li><a class="dropdown-item" href="#!">판매자평점순</a></li>
              </ul>
            </li>
          </ul>
        </nav>
      </div>
      <div>
        <div class="container-fluid pt-5">
          <div class="row px-xl-5 pb-3">
            <div class="col-lg-4 col-md-6 pb-1">
              <div class="cat-item d-flex flex-column border mb-4" style="padding: 30px;">
                <a href="" class="cat-img position-relative overflow-hidden mb-3">
                  <img class="img-fluid"
                    src="https://blog.kakaocdn.net/dn/bGu5TC/btrGZLBBodm/QunLukZnzgvWcoygDFziO0/img.jpg" alt="">
                </a>
                <h4 class>햄찌</h4>
                <div class="used-img-price">
                  <div>
                    <h5 class="fw-bold text-right">1,000,000<span class="fw-normal">원</span></h5>
                  </div>
                  <div>
                    <h6 class="fw-bold">2022.09.29</h6>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-lg-4 col-md-6 pb-1">
              <div class="cat-item d-flex flex-column border mb-4" style="padding: 30px;">
                <a href="" class="cat-img position-relative overflow-hidden mb-3">
                  <img class="img-fluid"
                    src="https://blog.kakaocdn.net/dn/bGu5TC/btrGZLBBodm/QunLukZnzgvWcoygDFziO0/img.jpg" alt="">
                </a>
                <h4 class>햄찌</h4>
                <div class="used-img-price">
                  <div>
                    <h5 class="fw-bold text-right">1,000,000<span class="fw-normal">원</span></h5>
                  </div>
                  <div>
                    <h6 class="fw-bold">2022.09.29</h6>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-lg-4 col-md-6 pb-1">
              <div class="cat-item d-flex flex-column border mb-4" style="padding: 30px;">
                <a href="" class="cat-img position-relative overflow-hidden mb-3">
                  <img class="img-fluid"
                    src="https://blog.kakaocdn.net/dn/bGu5TC/btrGZLBBodm/QunLukZnzgvWcoygDFziO0/img.jpg" alt="">
                </a>
                <h4 class>햄찌</h4>
                <div class="used-img-price">
                  <div>
                    <h5 class="fw-bold text-right">1,000,000<span class="fw-normal">원</span></h5>
                  </div>
                  <div>
                    <h6 class="fw-bold">2022.09.29</h6>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-lg-4 col-md-6 pb-1">
              <div class="cat-item d-flex flex-column border mb-4" style="padding: 30px;">
                <a href="" class="cat-img position-relative overflow-hidden mb-3">
                  <img class="img-fluid"
                    src="https://blog.kakaocdn.net/dn/bGu5TC/btrGZLBBodm/QunLukZnzgvWcoygDFziO0/img.jpg" alt="">
                </a>
                <h4 class>햄찌</h4>
                <div class="used-img-price">
                  <div>
                    <h5 class="fw-bold text-right">1,000,000<span class="fw-normal">원</span></h5>
                  </div>
                  <div>
                    <h6 class="fw-bold">2022.09.29</h6>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-lg-4 col-md-6 pb-1">
              <div class="cat-item d-flex flex-column border mb-4" style="padding: 30px;">
                <a href="" class="cat-img position-relative overflow-hidden mb-3">
                  <img class="img-fluid"
                    src="https://blog.kakaocdn.net/dn/bGu5TC/btrGZLBBodm/QunLukZnzgvWcoygDFziO0/img.jpg" alt="">
                </a>
                <h4 class>햄찌</h4>
                <div class="used-img-price">
                  <div>
                    <h5 class="fw-bold text-right">1,000,000<span class="fw-normal">원</span></h5>
                  </div>
                  <div>
                    <h6 class="fw-bold">2022.09.29</h6>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-lg-4 col-md-6 pb-1">
              <div class="cat-item d-flex flex-column border mb-4" style="padding: 30px;">
                <a href="" class="cat-img position-relative overflow-hidden mb-3">
                  <img class="img-fluid"
                    src="https://blog.kakaocdn.net/dn/bGu5TC/btrGZLBBodm/QunLukZnzgvWcoygDFziO0/img.jpg" alt="">
                </a>
                <h4 class>햄찌</h4>
                <div class="used-img-price">
                  <div>
                    <h5 class="fw-bold text-right">1,000,000<span class="fw-normal">원</span></h5>
                  </div>
                  <div>
                    <h6 class="fw-bold">2022.09.29</h6>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
</body>
</html>