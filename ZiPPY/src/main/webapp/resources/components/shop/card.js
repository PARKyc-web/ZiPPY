let template=`
<section>
  <div id="main-category">
  <h4 class="text-center">BEST</h4>
  </div>
  <div class="container-fluid pt-5">
  <div class="row px-xl-5 pb-3">
      <div class="col-lg-3 col-md-6 pb-1" v-for=" i in 8" :key="i">
          <div class="cat-item d-flex flex-column border mb-4">
              <a href="" class="cat-img position-relative overflow-hidden mb-3">
                  <img class="img-fluid" src="https://www.westelm.co.kr/upload/goods/G100007617/G100007617_01_1_1000.jpg" alt="">
              </a>
              <h6 class>예담가구</h6>
              <h6 class="fw-bold">푹신푹신 침대</h6>
              <h5 class="fw-bold text-right">1,000,000<span class="fw-normal">원</span ></h5>
          </div>
      </div>      
  </div>
  </div>
</div>
</section>`;

  export default {
    name: 'card',
    template : template
}