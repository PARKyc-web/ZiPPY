let template = `
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
        <label id="sold-out">
          <p class="chk_box">
            <input type="checkbox" id="chk_top" />
            <label for="chk_top"> 판매완료된 제품 보기</label>
          </p>
        </label>
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
            <div class="col-lg-3 col-md-6 pb-1">
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
            <div class="col-lg-3 col-md-6 pb-1">
              <div class="cat-item d-flex flex-column border mb-4" style="padding: 30px;">
                <a href="" class="cat-img position-relative overflow-hidden mb-3">
                  <img class="img-fluid"
                    src="https://blog.kakaocdn.net/dn/bGu5TC/btrGZLBBodm/QunLukZnzgvWcoygDFziO0/img.jpg" alt="">
                </a>
                <h4 class>햄찌</h4>
                <div class="used-img-price">
                  <div>
                    <h6 class="fw-bold text-right">1,000,000<span class="fw-normal">원</span></h6>
                  </div>
                  <div>
                    <h6 class="fw-bold">2022.09.29</h6>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-lg-3 col-md-6 pb-1">
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
            <div class="col-lg-3 col-md-6 pb-1">
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
            <div class="col-lg-3 col-md-6 pb-1">
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
            <div class="col-lg-3 col-md-6 pb-1">
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
            <div class="col-lg-3 col-md-6 pb-1">
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
            <div class="col-lg-3 col-md-6 pb-1">
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
`;

export default {	
    template
}