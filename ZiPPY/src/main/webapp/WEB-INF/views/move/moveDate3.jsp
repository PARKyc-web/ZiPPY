<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>

<head>
  <title>Move Home</title>
  <meta charset="UTF-8">

  <!--  -->
  <link rel="preconnect" href="https://fonts.googleapis.com" />
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
  <link rel="stylesheet" href="cssMouseDetail.css" />
  <!--  -->

  <script src="https://cdn.jsdelivr.net/npm/vue@2.7.8/dist/vue.js"></script>

  <!-- CSS only -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
  <!-- JavaScript Bundle with Popper -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous">
  </script>

  <!-- 컴포넌트 사용 -->
  <script type="module" src="<%=request.getContextPath()%>/resources/js/app.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/style.css">

<style type="text/css">
  /* 캘린더 */
  * {
    margin: 0;
    padding: 0;
    font-family: sans-serif;
    box-sizing: border-box;
  }

  .move-cal-body {
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: 100vh;
  }

  .move-calendar {
    width: 600px;
    margin: 50px;
  }

  .move-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }

  .move-year-month {
    font-size: 35px;
  }

  .move-nav {
    display: flex;
    border: 1px solid #333333;
    border-radius: 5px;
  }

  .move-nav-btn {
    width: 28px;
    height: 30px;
    border: none;
    font-size: 16px;
    line-height: 34px;
    background-color: transparent;
    cursor: pointer;
  }

  .move-go-today {
    width: 75px;
    border-left: 1px solid #333333;
    border-right: 1px solid #333333;
  }


  .move-days {
    display: flex;
    margin: 25px 0 10px;
  }

  .move-day {
    width: calc(100% / 7);
    text-align: center;
  }

  .move-dates {
    display: flex;
    flex-flow: row wrap;
    height: 500px;
    border-top: 1px solid #333333;
    border-right: 1px solid #333333;
  }

  .move-date {
    width: calc(100% / 7);
    padding: 15px;
    text-align: right;
    border-bottom: 1px solid #333333;
    border-left: 1px solid #333333;
  }

  .move-day:nth-child(7n + 1),
  .move-date:nth-child(7n + 1) {
  color: #D13E3E;
  }

  .move-day:nth-child(7n),
  .move-date:nth-child(7n) {
  color: #396EE2;
  }

  /* 카드 */
  #calendarForm {
    margin: 0 auto;
    text-align: center;
  }

  .container-fluid {
    width: 500px;
    margin: 0 auto;
    text-align: center;
  }

  .row {
    width: 500px;
  }

  .cat-item {
    width: 500px;
    text-align: center;
  }

  /* 버튼 */
  .frame {
    width: 90%;
    margin: 100px auto;
    text-align: center;
  }

  #selectBtn-bday {
    margin: 70px;
  }

  .custom-btn {
    width: 200px;
    height: 100px;
    color: #fff;
    border-radius: 5px;
    padding: 10px 25px;
    font-family: 'Lato', sans-serif;
    font-weight: bold;
    font-size: 20px;
    background: transparent;
    cursor: pointer;
    transition: all 0.3s ease;
    position: relative;
    display: inline-block;
    box-shadow: inset 2px 2px 2px 0px rgba(255, 255, 255, .5),
      7px 7px 20px 0px rgba(0, 0, 0, .1),
      4px 4px 5px 0px rgba(0, 0, 0, .1);
    outline: none;
  }


  .btn-1 {
    background: rgb(144, 198, 235);
    background: linear-gradient(0deg, rgba(144, 198, 235, 1) 0%, rgb(127, 196, 243) 100%);
    border: none;
  }

  .btn-1:hover {
    /* background: rgb(63, 153, 243);
      background: linear-gradient(0deg, rgba(63, 153, 243, 1) 0%, rgba(2, 126, 251, 1) 100%); */
    box-shadow: 4px 4px 6px 0 rgba(255, 255, 255, .5),
      -4px -4px 6px 0 rgba(116, 125, 136, .5),
      inset -4px -4px 6px 0 rgba(255, 255, 255, .2),
      inset 4px 4px 6px 0 rgba(0, 0, 0, .4);
  }


  .btn-2 {
    background: rgb(rgb(150, 229, 184));
    background: linear-gradient(0deg, rgba(150, 229, 184, 1) 0%, rgb(150, 229, 184) 100%);
    border: none;

  }

  .btn-2:before {
    height: 0%;
    width: 2px;
  }

  .btn-2:hover {
    box-shadow: 4px 4px 6px 0 rgba(255, 255, 255, .5),
      -4px -4px 6px 0 rgba(116, 125, 136, .5),
      inset -4px -4px 6px 0 rgba(255, 255, 255, .2),
      inset 4px 4px 6px 0 rgba(0, 0, 0, .4);
  }

  .btn-3 {
    background: rgb(163, 162, 162);
    background: linear-gradient(0deg, rgba(163, 162, 162, 1) 0%, rgb(163, 162, 162) 100%);
    border: none;
    height: 50px;
    width: 150px;

  }

  .btn-3:hover {
    /* background: rgb(136, 221, 164);
      background: linear-gradient(0deg, rgba(136, 221, 164, 1) 0%, rgb(131, 221, 161) 100%); */
    box-shadow: 4px 4px 6px 0 rgba(255, 255, 255, .5),
      -4px -4px 6px 0 rgba(116, 125, 136, .5),
      inset -4px -4px 6px 0 rgba(255, 255, 255, .2),
      inset 4px 4px 6px 0 rgba(0, 0, 0, .4);
  }
</style>

</head>

<body>

  <!-- 컴포넌트 사용 -->
  <div class="move-cal-body">
    <div id="app">
      <main-header></main-header>
      <main-nav></main-nav>
    </div>

    <div id="calendarForm">
      <form name="해당 폼의 이름" action="값을 보낼 주소" method="post">
        <!-- 카드 -->
        <div class="container-fluid pt-5">
          <div class="row px-xl-5 pb-3">
            <div class="col-lg-4 col-md-6 pb-1">
              <div class="cat-item d-flex flex-column border mb-4" style="padding: 30px;">
                <h3>이사 희망 날짜를 선택해주세요.</h3>
                <br>
                <div>
                  <!-- <input type='date' name='movingDate' /> -->
                  <div class="move-calendar">
                    <div class="move-header">
                      <div class="move-year-month"></div>
                      <div class="move-nav">
                        <button class="move-nav-btn go-prev">&lt;</button>
                        <button class="move-nav-btn go-today">Today</button>
                        <button class="move-nav-btn go-next">&gt;</button>
                      </div>
                    </div>
                    <div class="move-main">
                      <div class="move-days">
                        <div class="move-day">일</div>
                        <div class="move-day">월</div>
                        <div class="move-day">화</div>
                        <div class="move-day">수</div>
                        <div class="move-day">목</div>
                        <div class="move-day">금</div>
                        <div class="move-day">토</div>
                      </div>
                      <div class="move-dates"></div>
                    </div>
                  </div>

                </div>
                <br>
                <br>
                <hr>
                <br>
                <br>
                <h3>이사 희망 시간을 선택해주세요. 집에가고싶다!!!!!</h3>
                <br>
                <div>
                  <input type='time' name='movingTime' />
                </div>
                <div>
                  <button id="selectBtn-bday" class="custom-btn btn-3">선택완료</button>
                </div>

              </div>
            </div>
          </div>
        </div>
      </form>
    </div>
  </div>


</body>
<script>
  const date = new Date();

  const viewYear = date.getFullYear();
  const viewMonth = date.getMonth();

  document.querySelector('.move-year-month').textContent = `${viewYear}년 ${viewMonth + 1}월`;

  const prevLast = new Date(viewYear, viewMonth, 0);
  const thisLast = new Date(viewYear, viewMonth + 1, 0);

  const PLDate = prevLast.getDate();
  const PLDay = prevLast.getDay();

  const TLDate = thisLast.getDate();
  const TLDay = thisLast.getDay();

  const prevDates = [];
  const thisDates = [...Array(TLDate + 1).keys()].slice(1);
  const nextDates = [];

  if (PLDay !== 6) {
    for (let i = 0; i < PLDay + 1; i++) {
      prevDates.unshift(PLDate - i);
    }
  }

  for (let i = 1; i < 7 - TLDay; i++) {
    nextDates.push(i);
  }

  const dates = prevDates.concat(thisDates, nextDates);

  dates.forEach((date, i) => {
    dates[i] = `<div class="move-date">${date}</div>`;
  })

  document.querySelector('.move-dates').innerHTML = dates.join('');
</script>

</html>