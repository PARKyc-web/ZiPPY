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
            <h3>상품 수정</h3>
          </div>
        </div>
        <div class="used-insert-addr">
          <button id="used-addr">
            <i class="bi bi-geo-alt"></i>
          </button>
        </div>
        <hr>
        <div>
          <div class="used-insert-img"><span>이미지 0/6</span></div>
          <div id="used-insert-main">
          </div>
          <hr>
          <div class="used-insert-img">
            <span>제목 <input type="text" placeholder="상품 제목을 2글자 이상 입력해주세요">
            </span>
          </div>
          <hr>
          <div class="used-insert-img">
            <span>카테고리</span>
          </div>
          <hr>
          <div class="used-insert-img">
            <span>가격 <input id="used-insert-price" type="text"> 원</span>
          </div>
          <hr>
          <div class="used-insert-img">
            <span>설명</span>
          </div>
          <textarea id="used-insert-textarea"  cols="80" rows="10"></textarea>
          <div class="used-insert-submit">
            <button>수정</button>
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

      .used-insert-img span {
        font-weight: bolder;
        font-size: x-large;
        margin: 10px 0 10px 10px;
      }

      .upload:nth-child(2) {
        color: grey;
      }

      .used-insert-img input {
        width: 70%;
        margin: 10px 0 10px 100px;
      }

      #used-insert-price {
        width: 10%;
        margin: 10px 0 10px 100px;
      }

      #used-insert-textarea {
        margin: 10px 0 10px 165px;
      }

      .used-insert-submit {
        float: right;
        margin-top: 300px;
      }

      .used-insert-submit button {
        border: none;
        background-color: #B3E3C3;
        color: white;
        width: 100px;
        height: 50px;
      }
</style>