<template>          
      <div id="container">        
          <div class="used-main-title">
            <h3>일반회원 조회</h3>
          </div>
  
          <div id="used-add-drop-search">           
            <div>
              <div class="search">
                <input type="text" placeholder="검색어 입력" id="used-main-search-input" v-model="word"
                  @keyup.enter="total();">
                <img @click="total()" src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/icon/search.png">
              </div>
            </div>
          </div>
        
  
        <hr>                     
        <b-table striped hover :items="items" :fields="fields"></b-table>
        <div class="text-center">
          <v-pagination v-model="page" :length="pageCount" circle color="#B3E3C3"></v-pagination>
        </div>      
      </div>
  </template>
  
  <script>
    export default {
        created(){
            this.$axios({
                url:"/admin/general",
                params: {
                    pageNum : 1
                }
            }).then(res => {
                console.log(res);
                console.log(res.data);
            })
        },
      data() {
        return {
          // Note 'isActive' is left out and will not appear in the rendered table
          fields: [
            {
              key: 'last_name',
              sortable: true
            },
            {
              key: 'first_name',
              sortable: true
            },
            {
              key: 'age',
              label: 'Person age',
              sortable: true,
              // Variant applies to the whole column, including the header and footer
            }
          ],
          items: [
            { age: 40, first_name: 'Dickerson', last_name: 'Macdonald' },
            { age: 21, first_name: 'Larsen', last_name: 'Shaw' },
            { age: 89, first_name: 'Geneva', last_name: 'Wilson' },
            { age: 38, first_name: 'Jami', last_name: 'Carney' }
          ]
        }
      }
    }
  </script>
  <style scoped>
    #container {
      width: 1200px;
      margin: 0 auto;
    }
  
    #used-show-cate {
      margin-top: 30px;
    }
  
    #used-main-dropbox {
      margin-top: 40px;
    }
  
    .search {
      position: relative;
      width: 300px;
    }
  
    .search img:hover {
      cursor: pointer;
    }
  
    .search input {
      width: 100%;
      border: 1px solid #bbb;
      border-radius: 8px;
      padding: 10px 12px;
      font-size: 14px;
    }
  
    .search img {
      position: absolute;
      width: 17px;
      top: 10px;
      right: 12px;
      margin: 0;
    }
  
    .used-main-card {
      border: 1px solid #eeeeee;
      width: 194px;
      height: auto;
      display: inline-block;
      margin: 20px;
    }
  
    v-container:hover {
      cursor: pointer;
    }
  
    .v-overflow-btn {
      float: right;
      width: 150px;
    }
  
    ::v-deep .my-2 .v-label {
      font-size: 13px;
      color: rgb(0, 0, 0, 0.87)
    }
  
    .used-main-card:hover {
      cursor: pointer;
    }
  
    .used-main-title {
      margin: 50px;
    }
  
    .used-main-search-btn {
      background-color: #B3E3C3;
    }
  
    .used-main-card-title {
      overflow: hidden;
      padding-bottom: 20px;
      font-weight: 500;
      text-overflow: ellipsis;
    }
  
    .used-main-card-cont {
      padding: 15px 10px
    }
  
    .used-main-price-date {
      display: flex;
      justify-content: space-between;
    }
  
    .used-main-card-price {
      font-size: 16px;
      font-weight: 600;
      text-overflow: ellipsis;
      white-space: nowrap;
      overflow: hidden;
    }
  
    .used-main-price-date span {
      color: #888888;
      font-size: smaller;
    }
  
    .form-check {
      margin-top: 30px;
      margin-bottom: 20px;
    }
  
    .form-check-input {
      border: 1px solid #B3E3C3;
    }
  
    .form-check-input:checked {
      background-color: #B3E3C3;
      border: 1px solid #B3E3C3;
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
      align-items: center;
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
  
  
    #dropdownMenuButton {
      text-align: left;
    }
  
    #dropdown-opt {
      width: 300px;
    }
  
    #dropdown-opt a:hover {
      color: #212529;
      background-color: #B3E3C3;
    }
  
    #used-soldot-drop {
      display: flex;
      justify-content: space-between;
    }
  
    input#chk_top {
      display: none;
  
    }
  
    /*input 바로 다음의 label*/
    input#chk_top+label {
      cursor: pointer;
    }
  
    /*input 바로 다음의 label:before 에 체크하기 전 CSS 설정*/
    input#chk_top+label:before {
      content: "";
      display: inline-block;
      width: 17px;
      height: 17px;
      line-height: 17px;
      border: 1px solid #cbcbcb;
      vertical-align: middle;
      /*체크 전과 체크 후 높이 차이 때문에 설정*/
    }
  
    .chk_box {
      margin-top: 30px;
    }
  
    /*checked된 input 바로 다음의 label:before 에 체크 후 CSS 설정*/
    input#chk_top:checked+label:before {
      content: "\f00c";
      /*폰트어썸 유니코드*/
      font-family: "Font Awesome 5 free";
      /*폰트어썸 아이콘 사용*/
      font-weight: 900;
      /*폰트어썸 설정*/
      color: #fff;
      background-color: #B3E3C3;
      border-color: #B3E3C3;
      font-size: 13px;
      text-align: center;
    }
  </style>