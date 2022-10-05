template = `
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
          <h3>키워드 및 키워드 지역</h3>
        </div>
      </div>
      <hr>
      <div>
        <div class="used-keyword-maincont">
          <div>
            <div class="used-keyword-location"><span>키워드 0/30</span></div>
            <div>
              <form class="d-flex">
                <input class="form-control" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-primary" type="submit" style="margin-right: 10px;"><i
                    class="bi bi-search"></i></button>
              </form>
            </div>
            <div>
              <div class="used-keyword-content">자전거<div class="used-keyword-close"><i class="bi bi-x-circle"></i>
                </div>
              </div>
            </div>
          </div>
        </div>
        <hr>
        <div class="used-keyword-maincont">
          <div>
            <div>
              <div class="used-keyword-location"><span>키워드 지역 0/3</span></div>
            </div>
            <div>
              <form class="d-flex">
                <input class="form-control" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-primary" type="submit" style="margin-right: 10px;"><i
                    class="bi bi-search"></i></button>
              </form>
            </div>
          </div>
          <div>
            <div class="used-keyword-content">대구<div class="used-keyword-close"><i class="bi bi-x-circle"></i>
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