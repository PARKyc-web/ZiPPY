<template>
  <div id="container">
    <nav-bar @click="search($event)"></nav-bar>
    <div>
      <div class="used-main-title">
        <h3>키워드 및 키워드 지역</h3>
      </div>
    </div>
    <hr />
    <div>
      <div class="used-keyword-maincont">
        <div>
          <div class="used-keyword-location"><span>키워드 0/30</span></div>
          <div>
            <form class="d-flex">
              <input class="form-control" type="search" placeholder="Search" aria-label="Search" />
              <button class="btn btn-primary" type="submit" style="margin-right: 10px">
                <i class="fa-solid fa-magnifying-glass"></i>
              </button>
            </form>
          </div>
          <div>
            <div class="used-keyword-content">
              자전거
              <div class="used-keyword-close">
                <i class="fa-solid fa-circle-xmark"></i>
              </div>
            </div>
          </div>
        </div>
      </div>
      <hr />
      <div class="used-keyword-maincont">
        <div>
          <div>
            <div class="used-keyword-location">
              <span>키워드 지역 0/3</span>
            </div>
          </div>
          <div>
            <form class="d-flex">
              <input class="form-control" type="search" placeholder="Search" aria-label="Search" />
              <button class="btn btn-primary" type="submit" style="margin-right: 10px">
                <i class="fa-solid fa-magnifying-glass"></i>
              </button>
            </form>
          </div>
        </div>
        <div>
          <div class="used-keyword-content">
            대구
            <div class="used-keyword-close"><i class="fa-solid fa-circle-xmark"></i></div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import axios from 'axios';
  import navBar from '../../components/used/navBar.vue';

  export default {};
</script>

<style>
  #container {
    width: 1200px;
    margin: 0 auto;
  }
  .d-flex{
    width: 400px;
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

  @media (min-width: 768px) {
    .bd-placeholder-img-lg {
      font-size: 3.5rem;
    }
  }

  .b-example-divider {
    height: 3rem;
    background-color: rgba(0, 0, 0, 0.1);
    border: solid rgba(0, 0, 0, 0.15);
    border-width: 1px 0;
    box-shadow: inset 0 0.5em 1.5em rgba(0, 0, 0, 0.1),
      inset 0 0.125em 0.5em rgba(0, 0, 0, 0.15);
  }

  .b-example-vr {
    flex-shrink: 0;
    width: 1.5rem;
    height: 100vh;
  }

  .bi {
    vertical-align: -0.125em;
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
    color: #b3e3c3;
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
    width: 100px;
  }

  .used-keyword-div {
    border: none;
    border-radius: 10px;
    padding: 5px;
    width: 120px;
    margin: 10px;
    background-color: #b3e3c3;
    font-weight: bolder;
    overflow: hidden;
    text-align: center;
  }

  .used-keyword-close {
    float: right;
  }

  .used-keyword-close:hover {
    cursor: pointer;
  }

  .used-keyword-loc-div {
    border: none;
    border-radius: 10px;
    padding: 5px;
    width: 150px;
    margin: 10px;
    background-color: #b3e3c3;
    font-weight: bolder;
    overflow: hidden;
    text-align: center;
  }

  .used-keyword-content {
    border: none;
    border-radius: 10px;
    padding: 5px;
    width: 100px;
    margin: 10px;
    background-color: #b3e3c3;
    font-weight: bolder;
  }

  .used-keyword-location {
    font-weight: bolder;
    font-size: larger;
    margin-bottom: 20px;
  }

  .used-keyword-maincont {
    width: 1024px;
    margin-left: 30px;
  }
</style>