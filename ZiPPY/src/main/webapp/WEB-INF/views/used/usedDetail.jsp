<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
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
      #used-cate-update{
        display: flex;
          justify-content: space-between;
          align-items: center;
      }
      #used-cate-update button{
        border: 1px solid grey ;
        background-color: white;
        border-radius: 10px;
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
      </div>
      <hr>
      <div id="used-cate-update">
        <div>
          <button >침대</button>
        </div>
        <div>
          <button>수정하기</button>
        </div>
      </div>
      <div 그림 설명 버튼>
        <div 슬라이드 그림 및 설명>
          <div 그림>
            <div 설명>
              <div 가격>
                <div 찜 조회수 신고하기 판매여부 거래지역>
                  <div 찜 조회수 신고하기>
                    <div 판매여부 거래지역>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div 프로필 채팅버튼>
          <div>프로필</div>
          <div>
            <button>채팅</button>
          </div>
        </div>
      </div>
      <hr>
      <h3>상품정보</h3>
      <hr>
      <div>
        상세설명
      </div>
    </div>
</body>
</html>