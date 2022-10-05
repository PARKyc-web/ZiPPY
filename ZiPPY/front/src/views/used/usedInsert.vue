<template>
  <form action="post">
      <div id="container">
        <div class="nav-scroller bg-body shadow-sm">
          <nav class="nav" aria-label="Secondary navigation">
            <a class="nav-link active" aria-current="page" href="#">전체</a> <a class="nav-link" href="#">침실</a>
            <a class="nav-link" href="#">옷장/수납</a>
            <a class="nav-link" href="#">주방</a> <a class="nav-link" href="#">욕실</a>
            <a class="nav-link" href="#">서재</a> <a class="nav-link" href="#">다용도실</a>
          </nav>
        </div>
        <div>
          <div class="used-main-title">
            <h3>상품 등록</h3>
          </div>
        </div>
        <div class="used-insert-addr">
          <button id="used-addr">
            <i class="bi bi-geo-alt"></i>
          </button>
        </div>
        <hr>
        <div>
          <div>
            <div id="used-insert-main">
              <div class="used-insert-img" id="used-insert-img-div"><span>이미지</span> 0/6</div>
              <input type="file" class="real-upload" accept="image/*" required multiple>
              <div class="upload">이미지 등록</div>
              <ul class="image-preview"></ul>
            </div>
          </div>
          <hr>
          <div class="used-insert-img">
            <span>제목 <input type="text" placeholder="상품 제목을 2글자 이상 입력해주세요">
            </span>
          </div>
          <hr>
          <div id="used-insert-main">
            <div class="used-insert-img">
              <span>카테고리</span>
            </div>
            <div class="dropdown">
              <button class="btn btn-light dropdown-toggle" type="button" id="dropdownMenuButton1"
                data-bs-toggle="dropdown" aria-expanded="false">
                분류
              </button>
              <ul class="dropdown-menu" id="dropdown-opt" aria-labelledby="dropdownMenuButton1">
                <li><a class="dropdown-item" href="#">침실</a></li>
                <li><a class="dropdown-item" href="#">옷장/수납</a></li>
                <li><a class="dropdown-item" href="#">주방</a></li>
                <li><a class="dropdown-item" href="#">욕실</a></li>
                <li><a class="dropdown-item" href="#">서재</a></li>
                <li><a class="dropdown-item" href="#">다용도실</a></li>
              </ul>
            </div>

          </div>
          <hr>
          <div class="used-insert-img">
            <span>가격 <input id="used-insert-price" type="text"> 원</span>
          </div>
          <hr>
          <div class="used-wish-detailInfo">
            <div class="used-insert-img">
              <span>설명</span>
            </div>
            <div>
              <textarea id="used-insert-textarea" cols="110" rows="10"></textarea>
            </div>
          </div>
          <div class="used-insert-submit">
            <button>등록</button>
          </div>
        </div>
      </div>
    </form>
</template>

<script>
  export default{

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
        width: 50px;
      }

      .nav-link:hover {
        color: #B3E3C3;
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

      #used-addr {
        cursor: pointer;
        border: none;
        background-color: white;
      }

      #used-insert-img-div {
        width: 180px;
      }

      .used-insert-img span {
        font-weight: bolder;
        font-size: large;
        margin: 10px 0 10px 10px;
      }

      .upload:nth-child(2) {
        color: grey;
      }

      .used-insert-img input {
        width: 70%;
        margin: 10px 0 10px 145px;
      }

      #used-insert-price {
        width: 10%;
        margin: 10px 0 10px 145px;
      }

      #used-insert-textarea {
        margin: 10px 0 10px 150px;
        resize: none;
      }

      .used-insert-submit {
        float: right;
        margin: 50px 20px 30px 0;
      }

      .used-insert-submit button {
        border: none;
        background-color: #B3E3C3;
        color: white;
        width: 100px;
        height: 50px;
      }

      .used-wish-detailInfo {
        display: flex;
      }

      li {
        list-style: none;
      }

      img {
        width: 200px;
        height: 200px;
      }

      .real-upload {
        display: none;
      }

      .upload {
        width: 200px;
        height: 200px;
        background-color: #B3E3C3;
        cursor: pointer;
        line-height: 200px;
        text-align: center;
        color: white;
        margin-left: 20px;
      }

      .image-preview {
        width: 800px;
        height: 200px;
        background-color: white;
        display: flex;
        flex-wrap: wrap;
        gap: 20px;
      }

      #used-insert-main {
        display: flex;
      }

      #dropdownMenuButton1 {
        text-align: left;
      }

      #dropdown-opt a:hover {
        color: #212529;
        background-color: #B3E3C3;
      }
      .dropdown{
        margin-left: 115px;
      }
      #dropdownMenuButton1{
        width: 150px;
      }
</style>