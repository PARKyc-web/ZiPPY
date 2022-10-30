<template>
  <div class="wrapper">
    <move-nav-bar @click="categoryVal=$event.target.innerText"></move-nav-bar>
    
    <h2>이사 견적을 위해 입력한 정보를 확인해주세요.</h2>
    <form id="untactForm" name="untactForm">
      <input type="hidden" name="movingOption" v-model="checkk">
      <input type="hidden" name="commonOption" v-model="commonOption">
      <input type="hidden" name="email" v-model="email">
      <input type="hidden" name="movingMemo" v-model="movingMemo">
      
      <input type="hidden" name="departAddress" v-model="moveAddress.address">
      <input type="hidden" name="arriveAddress" v-model="moveAddress.address2">

      <input type="hidden" name="movingDate" v-model="moveDate.date">
      <input type="hidden" name="movingTime" v-model="moveDate.time">
      <input type="hidden" name="estimateType" v-model="moveEstimateType">

      <input type="hidden" name="departZipCode" v-model="moveAddress.postcode">
      <input type="hidden" name="departDetail" v-model="moveAddress.detailAddress">
      <input type="hidden" name="departExtra" v-model="moveAddress.extraAddress">
      <input type="hidden" name="arriveZipCode" v-model="moveAddress.postcode2">
      <input type="hidden" name="arriveDetail" v-model="moveAddress.detailAddress2">
      <input type="hidden" name="arriveExtra" v-model="moveAddress.extraAddress2">

      <input type="hidden" name="moveType" v-model="moveType">
      <input type="hidden" name="requestDate" v-model="requestDateSend">
      <input type="hidden" name="reservStatus" v-model="reservStatus">



    <!-- 이사유형 -->
    <v-expansion-panels>
      <v-expansion-panel>
        <v-expansion-panel-header v-slot="{ open }">
          <v-row no-gutters>
            <v-col cols="4"> 이사유형 </v-col>
            <v-col cols="8" class="text--secondary">
              <v-fade-transition leave-absolute>
                <span v-if="open" key="0"> 선택한 이사유형 </span>
                <span v-else key="1">
                  이사유형:{{ moveType || "Not set" }}
                </span>
              </v-fade-transition>
            </v-col>
          </v-row>
        </v-expansion-panel-header>

        <v-expansion-panel-content>
          <v-row no-gutters>
            <v-spacer></v-spacer>
            <v-col cols="5">
              <v-select name="moveType" v-model="moveType" :items="types" chips flat solo outlined placeholder="선택한 이사유형 불러오기">
              </v-select>

            </v-col>

          </v-row>
        </v-expansion-panel-content>
      </v-expansion-panel>

      <!-- 이사날짜 -->
      <v-expansion-panel>
        <v-expansion-panel-header v-slot="{ open }">
          <v-row no-gutters>
            <v-col cols="4"> 이사희망일 및 시간 </v-col>
            <v-col cols="8" class="text--secondary">
              <v-fade-transition leave-absolute>
                <span v-if="open">이사희망 날짜와 시간을 선택해주세요.</span>
                <v-row v-else no-gutters style="width: 100%">
                  <v-col cols="6">
                    이사 희망일: {{ moveDate.date || "Not set" }}
                  </v-col>
                  <v-col cols="6">
                    이사 희망시간: {{ moveDate.time || "Not set" }}
                  </v-col>
                </v-row>
              </v-fade-transition>
            </v-col>
          </v-row>
        </v-expansion-panel-header>

        <v-expansion-panel-content>
          <v-row justify="space-around" no-gutters>
            <v-col cols="3">
              <v-menu ref="startMenu" :close-on-content-click="false" :return-value.sync="moveDate.date" offset-y
                min-width="290px">
                <template v-slot:activator="{ on, attrs }">
                  <v-text-field name="movingDate" v-model="moveDate.date" label="이사희망일" prepend-icon="mdi-calendar" readonly
                    v-bind="attrs" v-on="on"></v-text-field>
                </template>
                <v-date-picker v-model="date" no-title scrollable>
                  <v-spacer></v-spacer>
                  <v-btn text color="primary" @click="$refs.startMenu.isActive = false">
                    취소
                  </v-btn>
                  <v-btn text color="primary" @click="$refs.startMenu.save(date)">
                    수정
                  </v-btn>
                </v-date-picker>
              </v-menu>
            </v-col>

            <v-col cols="3">
              <v-menu ref="endMenu" :close-on-content-click="false" :return-value.sync="moveDate.time" offset-y
                min-width="290px">
                <template v-slot:activator="{ on, attrs }">
                  <v-text-field name="movingTime" v-model="moveDate.time" label="이사 희망시간" prepend-icon="mdi-clock" readonly v-bind="attrs"
                    v-on="on"></v-text-field>
                </template>
                <!-- <v-date-picker
                v-model="date"
                no-title
                scrollable
              > -->
                <v-time-picker v-model="time" ampm-in-title format="ampm">
                  <v-spacer></v-spacer>
                  <v-btn text color="primary" @click="$refs.endMenu.isActive = false">
                    취소
                  </v-btn>
                  <v-btn text color="primary" @click="$refs.endMenu.save(time)">
                    수정
                  </v-btn>
                </v-time-picker>
              </v-menu>
            </v-col>
          </v-row>
        </v-expansion-panel-content>
      </v-expansion-panel>

      <!-- 3. 주소 -->
      <v-expansion-panel>
        <v-expansion-panel-header v-slot="{ open }">
          <v-row no-gutters>
            <v-col cols="4"> 이사 출발지 도착지 주소 </v-col>
            <v-col cols="8" class="text--secondary">
              <v-fade-transition leave-absolute>
                <span v-if="open">이사 출발지와 도착지 주소를 확인해주세요.</span>
                <v-row v-else no-gutters style="width: 100%">
                  <v-col cols="6">
                    이사 출발지 주소: {{ moveAddress.address,moveAddress.detailAddress || "Not set" }}
                  </v-col>
                  <v-col cols="6">
                    이사 도착지 주소: {{ moveAddress.address2,moveAddress.detailAddress2 || "Not set" }}
                  </v-col>
                </v-row>
              </v-fade-transition>
            </v-col>
          </v-row>
        </v-expansion-panel-header>

        <v-expansion-panel-content>
          <v-row justify="space-around" no-gutters>
            <v-col cols="3">
              <v-menu ref="startMenu" :close-on-content-click="false" :return-value.sync="moveAddress.address"
                offset-y min-width="290px">
                <template v-slot:activator="{ on, attrs }">
                  <v-text-field v-model="moveAddress.address" label="출발지 주소" prepend-icon="mdi-home" readonly
                    v-bind="attrs" v-on="on"></v-text-field>

                </template>

                <v-card>

                  우편번호 :
                  <input name="departZipCode" v-model="moveAddress.postcode" class="type-2" type="text" id="post1"
                    style="width: 80px; height: 26px" />
                  <button class="custom-btn btn-4" type="button" @click="execDaumPostcode(1)">
                    검색
                  </button>
                  <br />
                  주소 :
                  <input name="departAddress" v-model="moveAddress.address" id="address1" class="type-2" type="text" 
                    style="width: 300px; height: 30px" readonly /><br />
                  상세 :
                  <input v-model="moveAddress.detailAddress" id="detailAddress1" class="type-2" type="text"
                    name="departDetail" style="width: 300px; height: 30px" /><br />
                  참고항목 :
                  <input name="departExtra" v-model="moveAddress.extraAddress" type="text" id="extraAddress1" class="type-2"
                    placeholder="참고항목" />

                  <v-spacer></v-spacer>
                  
                </v-card>
                <!-- </v-date-picker> -->
              </v-menu>
            </v-col>

            <v-col cols="3">
              <v-menu ref="endMenu" :close-on-content-click="false" :return-value.sync="moveAddress.address2" offset-y
                min-width="290px">
                <template v-slot:activator="{ on, attrs }">
                  <v-text-field v-model="moveAddress.address2" label="도착지 주소" prepend-icon="mdi-home" readonly
                    v-bind="attrs" v-on="on"></v-text-field>
                </template>

                <v-card>

                  우편번호 :
                  <input name="arriveZipCode" v-model="moveAddress.postcode2" class="type-2" type="text"  id="post2"
                    style="width: 80px; height: 26px" />
                  <button class="custom-btn btn-4" type="button" @click="execDaumPostcode(2)">
                    검색
                  </button>
                  <br />
                  주소 :
                  <input name="arriveAddress" v-model="moveAddress.address2" id="address2" class="type-2" type="text" 
                    style="width: 300px; height: 30px" readonly /><br />
                  상세 :
                  <input name="arriveDetail" v-model="moveAddress.detailAddress2" id="detailAddress2" class="type-2" type="text"
                    style="width: 300px; height: 30px" /><br />
                  참고항목 :
                  <input name="arriveExtra" v-model="moveAddress.extraAddress2" type="text" id="extraAddress2" class="type-2"
                    placeholder="참고항목" />

                  <v-spacer></v-spacer>
                  
                </v-card>
                <!-- </v-time-picker> -->
              </v-menu>
            </v-col>
          </v-row>
        </v-expansion-panel-content>
      </v-expansion-panel>

      <!-- 집정보 선택 -->
      <v-expansion-panel>
        <v-expansion-panel-header v-slot="{ open }">
          <v-row no-gutters>
            <v-col cols="4"> 집 정보 </v-col>
            <v-col cols="8" class="text--secondary">
              

              <v-fade-transition leave-absolute>
                <span v-if="open">집 정보를 확인해주세요.</span>
                <v-row v-else no-gutters style="width: 100%">
                  <v-col cols="4">
                    거주형태: {{ moveInfo.houseType || "Not set" }}
                  </v-col>
                  <v-col cols="4"> 평수: {{ moveInfo.spaceOfHome || "Not set" }} </v-col>
                  <v-col cols="4"> 층수: {{ moveInfo.floor || "Not set" }} </v-col>
                </v-row>
              </v-fade-transition>
            </v-col>
          </v-row>
        </v-expansion-panel-header>

        <v-expansion-panel-content>
          <v-row no-gutters>
            <v-spacer></v-spacer>
            <v-col cols="10">
              <v-select class="select-home" v-model="moveInfo.houseType" :items="houses" chips flat solo outlined
                placeholder="선택한 집형태 불러오기"></v-select>

              <v-select v-model="moveInfo.spaceOfHome" :items="squares" chips flat solo outlined
                placeholder="선택한 집평수 불러오기">
              </v-select>
              <v-select class="select-home" v-model="moveInfo.floor" :items="floors" chips flat solo outlined
                placeholder="선택한 집층수 불러오기"></v-select>

              
            </v-col>
          </v-row>
        </v-expansion-panel-content>
      </v-expansion-panel>

      <!-- 개수 선택 -->
      <v-expansion-panel>
        <v-expansion-panel-header v-slot="{ open }">
          <v-row no-gutters>
            <v-col cols="4"> 방 개수 정보 </v-col>
            <v-col cols="8" class="text--secondary">
              <v-fade-transition leave-absolute>
                <span v-if="open">방, 화장실, 베란다 개수를 확인해주세요.</span>
                <v-row v-else no-gutters style="width: 100%">
                  <v-col cols="4">
                    방 개수: {{ moveInfo.roomNum || "Not set" }}
                  </v-col>
                  <v-col cols="4">
                    화장실 개수: {{ moveInfo.toilet || "Not set" }}
                  </v-col>
                  <v-col cols="4">
                    베란다 개수: {{ moveInfo.veranda || "Not set" }}
                  </v-col>
                </v-row>
              </v-fade-transition>
            </v-col>
          </v-row>
        </v-expansion-panel-header>

        <v-expansion-panel-content>
          <v-row no-gutters>
            <v-spacer></v-spacer>
            <v-col cols="10">
              <v-select class="select-home" v-model="moveInfo.roomNum" :items="rooms" chips flat solo outlined
                placeholder="선택한 방 개수 불러오기"></v-select>

              <v-select class="select-home" v-model="moveInfo.toilet" :items="toilets" chips flat solo outlined
                placeholder="선택한 화장실 개수 불러오기"></v-select>
              <v-select class="select-home" v-model="moveInfo.veranda" :items="verandas" chips flat solo outlined
                placeholder="선택한 베란다 개수 불러오기"></v-select>

              
            </v-col>
          </v-row>
        </v-expansion-panel-content>
      </v-expansion-panel>

      <!-- 기타 정보 -->
      <v-expansion-panel>
        <v-expansion-panel-header v-slot="{ open }">
          <v-row no-gutters>
            <v-col cols="4"> 기타 시설 정보 </v-col>
            <v-col cols="8" class="text--secondary">
              <v-fade-transition leave-absolute>
                <span v-if="open">1층 별도계단, 엘레베이터, 주차장 유무를 확인해주세요.</span>
                <v-row v-else no-gutters style="width: 100%">
                  <v-col cols="4">
                    1층 별도계단: {{ moveInfo.extraStairs || "Not set" }}
                  </v-col>
                  <v-col cols="4">
                    엘레베이터: {{ moveInfo.elevator || "Not set" }}
                  </v-col>
                  <v-col cols="4">
                    주차장: {{ moveInfo.parkable || "Not set" }}
                  </v-col>
                </v-row>
              </v-fade-transition>
            </v-col>
          </v-row>
        </v-expansion-panel-header>

        <v-expansion-panel-content>
          <v-row no-gutters>
            <v-spacer></v-spacer>
            <v-col cols="10">
              <v-select class="select-home" v-model="moveInfo.extraStairs" :items="extraStairs" chips flat solo outlined
                placeholder="1층 별도 계단"></v-select>

              <v-select class="select-home" v-model="moveInfo.elevator" :items="elevator" chips flat solo outlined
                placeholder="엘레베이터"></v-select>

              <v-select class="select-home" v-model="moveInfo.parkable" :items="parkable" chips flat solo outlined
                placeholder="주차가능 여부"></v-select>

              
            </v-col>
          </v-row>
        </v-expansion-panel-content>
      </v-expansion-panel>


      <!-- 이삿짐 상세옵션 -->


      <v-expansion-panel>
        <v-expansion-panel-header v-slot="{ open }">
          <v-row no-gutters>
            <v-col cols="4"> 이삿짐 상세 정보 </v-col>
            <v-col cols="8" class="text--secondary">
              <v-fade-transition leave-absolute>
                <span v-if="open">각 이삿짐별 개수와 사이즈를 확인해주세요.</span>
                <v-row v-else no-gutters style="width: 100%">
                  <v-col>
                    입력한 이삿짐 상세 정보를 확인해주세요.
                  </v-col>

                </v-row>
              </v-fade-transition>
            </v-col>
          </v-row>
        </v-expansion-panel-header>

        <v-expansion-panel-content>
          <v-row no-gutters>
            <h4>가구</h4>
            <v-spacer></v-spacer>
            <v-col cols="20" >
              <div class="whole-furniture">

              <div class="single">
              <button class="custom-btn btn-4" type="button" @click="addBed()">+</button>&nbsp;&nbsp;&nbsp;<label class="lable"> 침대</label>
             
                <!-- <i class="material-icons md-54">bed</i><br /> -->
                <div v-for="i in moveDetail[0].bedCount" class="lauguage-info">
                  <div class="select-size">
                  <v-select class="select-home" v-model="moveDetail[0].bed[i]" id="i" :items="beds" chips flat solo outlined
                    placeholder="침대 사이즈"></v-select>
                  </div> 
                  <div class="minus-btn"> 
                  <button class="custom-btn btn-4" type="button" @click="removeBed(i)">-</button>
                </div>
                </div>
              
            </div>

            <div class="single">
              <button class="custom-btn btn-4" type="button" @click="addSofa()">+</button>&nbsp;&nbsp;&nbsp;<label class="lable"> 소파</label>
              <div class="furniture">
                <div v-for="i in moveDetail[0].sofaCount" class="lauguage-info">
                  <div class="select-size">
                  <v-select class="select-home" v-model="moveDetail[0].sofa[i]" id="i" :items="sofas" chips flat solo outlined
                    placeholder="소파 사이즈"></v-select>
                  </div>
                  <div class="minus-btn">  
                  <button class="custom-btn btn-4" type="button" @click="removeSofa(i)">-</button>
                </div>
                </div>
              </div>
            </div>

            <div class="single">
              <button class="custom-btn btn-4" type="button" @click="addCloset()">+</button>&nbsp;&nbsp;&nbsp;<label class="lable"> 옷장</label>
              <div class="furniture">
                <div v-for="i in moveDetail[0].closetCount" class="lauguage-info">
                  <v-select class="select-home" v-model="moveDetail[0].closet[i]" id="i" :items="closets" chips flat solo outlined
                    placeholder="옷장 사이즈"></v-select>
                  <button class="custom-btn btn-4" type="button" @click="removeCloset(i)">-</button>
                </div>
              </div>
            </div>
          </div>

          <div class="whole-furniture">
            <div class="single1">
              <button class="custom-btn btn-4" type="button" @click="addClosets()">+</button>&nbsp;&nbsp;&nbsp;<label class="lable"> 서랍장</label>
              <div class="furniture">
                <div v-for="i in moveDetail[0].closetsCount" class="lauguage-info">
                  <v-select class="select-home" v-model="moveDetail[0].closets[i]" id="i" :items="closetss" chips flat solo outlined
                    placeholder="서랍장 사이즈"></v-select>
                  <button class="custom-btn btn-4" type="button" @click="removeClosets(i)">-</button>
                </div>
              </div>
            </div>

            <div class="single1">
              <button class="custom-btn btn-4" type="button" @click="addTable()">+</button>&nbsp;&nbsp;&nbsp;<label class="lable">테이블/책상</label>
              <div class="furniture">
                <div v-for="i in moveDetail[0].tableCount" class="lauguage-info">
                  <v-select class="select-home" v-model="moveDetail[0].table[i]" id="i" :items="tables" chips flat solo outlined
                    placeholder="테이블/책상 사이즈"></v-select>
                  <button class="custom-btn btn-4" type="button" @click="removeTable(i)">-</button>
                </div>
              </div>
            </div>

            <div class="single1">
              <button class="custom-btn btn-4" type="button" @click="addChair()">+</button>&nbsp;&nbsp;&nbsp;<label class="lable"> 의자</label>
              <div class="furniture">
                <div v-for="i in moveDetail[0].chairCount" class="lauguage-info">
                  <v-select class="select-home" v-model="moveDetail[0].chair[i]" id="i" :items="chairs" chips flat solo outlined
                    placeholder="의자 사이즈"></v-select>
                  <button class="custom-btn btn-4" type="button" @click="removeChair(i)">-</button>
                </div>
              </div>
            </div>
          </div>
          
          

              <br>
              <hr><br>
              <h4>가전</h4>

              <div class="whole-application">
              <div class="single">
              <button class="custom-btn btn-4" type="button" @click="addTv()">+</button>&nbsp;&nbsp;&nbsp;<label class="lable"> TV</label>
              <div class="furniture">
                <div v-for="i in moveDetail[0].tvCount" class="lauguage-info">
                  <v-select class="select-home" v-model="moveDetail[0].tv[i]" id="i" :items="tvs" chips flat solo outlined
                    placeholder="TV 사이즈"></v-select>
                  <button class="custom-btn btn-4" type="button" @click="removeTv(i)">-</button>
                </div>
              </div>
            </div>

            <div class="single">
              <button class="custom-btn btn-4" type="button" @click="addPc()">+</button>&nbsp;&nbsp;&nbsp;<label class="lable"> 컴퓨터</label>
              <div class="furniture">
                <div v-for="i in moveDetail[0].pcCount" class="lauguage-info">
                  <v-select class="select-home" v-model="moveDetail[0].pc[i]" id="i" :items="pcs" chips flat solo outlined
                    placeholder="컴퓨터 사이즈"></v-select>
                  <button class="custom-btn btn-4" type="button" @click="removePc(i)">-</button>
                </div>
              </div>
            </div>

            <div class="single">
              <button class="custom-btn btn-4" type="button" @click="addFridge()">+</button>&nbsp;&nbsp;&nbsp;<label class="lable"> 냉장고</label>
              <div class="furniture">
                <div v-for="i in moveDetail[0].fridgeCount" class="lauguage-info">
                  <v-select class="select-home" v-model="moveDetail[0].fridge[i]" id="i" :items="fridges" chips flat solo outlined
                    placeholder="냉장고사이즈"></v-select>
                  <button class="custom-btn btn-4" type="button" @click="removeFridge(i)">-</button>
                </div>
              </div>
            </div>
            </div>

            <div class="whole-application">
            <div class="single">
              <button class="custom-btn btn-4" type="button" @click="addTrolley()">+</button>&nbsp;&nbsp;&nbsp;<label class="lable"> 전자레인지</label>
              <div class="furniture">
                <div v-for="i in moveDetail[0].trolleyCount" class="lauguage-info">
                  <v-select class="select-home" v-model="moveDetail[0].trolley[i]" id="i" :items="trolleys" chips flat solo outlined
                    placeholder="전자레인지 사이즈"></v-select>
                  <button class="custom-btn btn-4" type="button" @click="removeTrolley(i)">-</button>
                </div>
              </div>
            </div>

            <div class="single">
              <button class="custom-btn btn-4" type="button" @click="addAircon()">+</button>&nbsp;&nbsp;&nbsp;<label class="lable"> 에어컨</label>
              <div class="furniture">
                <div v-for="i in moveDetail[0].airconCount" class="lauguage-info">
                  <v-select class="select-home" v-model="moveDetail[0].aircon[i]" id="i" :items="aircons" chips flat solo outlined
                    placeholder="에어컨 사이즈"></v-select>
                  <button class="custom-btn btn-4" type="button" @click="removeAircon(i)">-</button>
                </div>
              </div>
            </div>

            <div class="single">
              <button class="custom-btn btn-4" type="button" @click="addLaundry()">+</button>&nbsp;&nbsp;&nbsp;<label class="lable"> 세탁기</label>
              <div class="furniture">
                <div v-for="i in moveDetail[0].laundryCount" class="lauguage-info">
                  <v-select class="select-home" v-model="moveDetail[0].laundry[i]" id="i" :items="laundrys" chips flat solo outlined
                    placeholder="세탁기 사이즈"></v-select>
                  <button class="custom-btn btn-4" type="button" @click="removeLaundry(i)">-</button>
                </div>
              </div>
            </div>

          </div>

              <br />
              <hr />
              <br />
              <h4>기타</h4>
              <div class="etc-class">
              <v-col cols="10" sm="6" md="3">
                <button id="etc-plus" class="custom-btn btn-4" type="button" @click="addEtc()">+</button><br />
                <div v-for="i in moveDetail[0].etcCount">
                <v-text-field label="Outlined" v-model="moveDetail[0].etcName[i]" placeholder="기타이삿짐 이름" outlined>
                </v-text-field>
                <v-text-field label="Outlined" v-model="moveDetail[0].etcSize[i]" placeholder="이삿짐 사이즈(cm * cm)" outlined>
                </v-text-field>
                <button id="etc-plus" class="custom-btn btn-4" type="button" :id="i-1" @click="removeEtc(i)">-</button>

              </div>
              </v-col>
            </div>

              <br />
              <hr />
              <br />
              <div class="boxes">
              <h4>박스 수량</h4>
              <v-row cols="10" sm="3" md="1">
                <v-select class="select-home" v-model="moveDetail[0].box" :items="boxes" chips flat solo outlined
                  placeholder="선택한 박스 개수를 확인해주세요."></v-select>
              </v-row>
            </div>

              <br />
              <hr />
              <br />
              <div class="photos">
              <h4>전달사항(선택)</h4>
              <br />
              
                <v-textarea background-color="grey lighten-2" color="cyan" v-model="movingMemo">         
                </v-textarea>
              <!-- <v-file-input outlined v-model="moveDetail.filesPhoto" placeholder="Upload your documents" label="사진첨부"
                prepend-icon="mdi-paperclip">
                <template v-slot:selection="{ text }">
                  <v-chip small label color="success">
                    {{ text }}
                  </v-chip>
                </template>
              </v-file-input> -->
            </div>

            </v-col>
          </v-row>
        </v-expansion-panel-content>
      </v-expansion-panel>



      <!-- 집구조 사진 -->
      <v-expansion-panel>
        <v-expansion-panel-header v-slot="{ open }">
          <v-row no-gutters>
            <v-col cols="4"> 집 구조 사진 </v-col>
            <v-col cols="8" class="text--secondary">
              <v-fade-transition leave-absolute>
                <span v-if="open">구도별 첨부 사진을 확인해주세요.</span>
                <v-row v-else no-gutters style="width: 100%">

                  <v-col cols="4">
                    첨부한 집 구조 사진을 확인해주세요.

                  </v-col>
                  
                </v-row>
              </v-fade-transition>
            </v-col>
          </v-row>
        </v-expansion-panel-header>

        <v-expansion-panel-content>
          <v-row no-gutters>
            <v-spacer></v-spacer>
            <v-col cols="10">
              <v-file-input  outlined  v-model="moveImage.files1" placeholder="Upload your documents" label="방 입구 사진첨부"
                multiple prepend-icon="mdi-paperclip">
                <template v-slot:selection="{ text }">
                  <v-chip small label color="success lighten-2">
                    {{ text }}
                  </v-chip>
                </template>
              </v-file-input>

              <v-file-input  outlined  v-model="moveImage.files2" placeholder="Upload your documents" label="방 중앙에서 사진첨부"
                multiple prepend-icon="mdi-paperclip">
                <template v-slot:selection="{ text }">
                  <v-chip small label color="success lighten-2">
                    {{ text }}
                  </v-chip>
                </template>
              </v-file-input>

              <v-file-input  outlined  v-model="moveImage.files3" placeholder="Upload your documents" label="짐 내부 사진첨부"
                multiple prepend-icon="mdi-paperclip">
                <template v-slot:selection="{ text }">
                  <v-chip small label color="success lighten-2">
                    {{ text }}
                  </v-chip>
                </template>
              </v-file-input>
            </v-col>
          </v-row>
        </v-expansion-panel-content>
      </v-expansion-panel>


    </v-expansion-panels>



    <!-- <v-sheet color="white" elevation="3" height="250" width="250"></v-sheet> -->
    <div class="table">

      <table>
        <thead>
          <h4>유의사항을 확인해주세요.</h4>
        </thead>
        <tbody>
          <ul class="agree_ul">
            <li>※ 비대면 견적 서비스는 이사출발지와 도착지의 집구조와, 옮겨야하는 짐의 자세한 정보가 있어야 정확한 견적이 가능합니다. </li>
         
            <li>※ 견적 요청에 기재한 정보가 실제 집구조와 이삿짐 정보와 다를 경우, 이삿날 추가 비용이 발생할 수 있습니다. </li>
          
            <li>※ 부정확한 정보 기재와 소비자의 단순변심으로 인한 추가비용발생에 대해서 본 웹사이트는 책임이 없습니다. </li>
          
          </ul>
        </tbody>
      </table>

      <div class="agree">
        <v-checkbox v-model="ex4" label="유의사항을 확인하고 숙지하였습니다." color="success lighten-3" value="success" hide-details
          class="agree-check"></v-checkbox>
      </div>
    </div>
    <div class="final-btn">
      <v-btn v-bind:disabled="ex4==false"  type="button" color="success lighten-3" elevation="10" @click="finalSend()">확인완료</v-btn>
    </div>
  </form>

  </div>
</template>

<script>
 import MoveNavBar from '@/components/move/MoveNavBar.vue';
 import swal from 'sweetalert2';
export default {
  components: {
    MoveNavBar
  },

    props: ['moveImage', 'moveDetail', 'moveEstimateType', 'moveType', 'moveInfo', 'moveDate', 'moveAddress', 'movingMemo'],

    data: () => ({
      commonOption:"",
      checkk: "",
      email : "",
      // movingMemo: "",
      requestDateSend: new Date().toLocaleDateString(),

      date: null,
      time: null,
      visitDate: null,
      visitTime: null,
      postcode: null,
      postcode2: null,
      reservStatus: '',
      //items
      types: ["소형이사", "가정이사"],
      houses: ["빌라/주택", "오피스텔", "아파트"],
      squares: [
        "10평이하",
        "11-15평",
        "16-20평",
        "21-25평",
        "26-30평",
        "31-35평",
        "36-40평",
        "40평이상",
      ],
      floors: [
        "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15",
        "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
        "30", "30층 이상",
      ],
      rooms: ["원룸", "투룸", "쓰리룸", "쓰리룸이상"],
      toilets: ["1", "2", "3", "4", "5개 이상"],
      verandas: ["0", "1", "2", "3", "4", "5개 이상"],
      extraStairs: ["있음", "없음"],
      elevator: ["있음", "없음"],
      parkable: ["가능", "불가능"],

      // 짐
      beds: ["싱글", "슈퍼싱글", "퀸", "킹", "킹이상"],
      sofas: ["1인용", "2~3인용", "4인용", "5인용", "6인용이상"],
      closets: ["양문형 단품", "(문한짝 기준)2~3자", "4자", "5~6자", "7자 이상"],
      closetss: ["플라스틱 1~2단", "플라스틱 3~4단", "플라스틱 5단 이상", "원목 1~2단", "원목 3~4단", "원목 5단 이상"],
      tables: ["플라스틱 1~2인용", "플라스틱 3~4인용", "플라스틱 4인용 이상", "유리 1~2인용", "유리 3~4인용", "유리 4인용 이상", "원목 1~2인용", "원목 3~4인용", "원목 4인용 이상"],
      chairs: ["플라스틱", "원목", "바퀴달린 책상의자", "접이식"],

      tvs: ["35인치 이하", "35~45인치", "45~55인치", "55~65인치", "65~75인치", "75인치 이상"],
      pcs: ["노트북(랩탑)", "컴퓨터(데스크탑) 본체 + 모니터", "모니터만", "본체만"],
      fridges: ["미니냉장고", "단문형", "양문형", "김치냉장고 단문형", "김치냉장고 양문형"],
      trolleys: ["미니 전자레인지", "일반형 전자레인지", "오븐겸용 전자레인지"],
      aircons: ["인버터형", "스탠드형", "시스템(천장형)"],
      laundrys: ["일반통돌이", "드럼형 저용량", "드럼형 고용량", "건조기"],  
      boxes: ["5개이하", "5-10개", "11-15개", "16-20개", "21-25개", "26-30개", "30개이상"],


      // 우편번호
      postcode: "",
      address: "",
      detailAddress: "",
      extraAddress: "",

      postcode2: "",
      address2: "",
      detailAddress2: "",
      extraAddress2: "",

      // checkbox
      ex4: false,

      furniture: "",

      // 사진
      filesLaug: [],
      files1: [],
      files2: [],
      files3: [],
      img: require("../../assets/box.jpg")
    }),

    computed: {

      lauguageCount: function () {
        return this.furniture.length || '';
      }
    },


    methods: {
      
      //데이터보내기
      finalSend: function(){
        console.log(this.moveImage);
        //moveImage- 이미지 넣기
        var img1 = document.getElementById("images1");
        var img2 = document.getElementById("images2");
        var img3 = document.getElementById("images3");

        //이사기본정보와 이삿짐정보를 Json타입의 String 형태로 넣는다.
        untactForm.movingOption.value= JSON.stringify(this.moveDetail[0])
        untactForm.commonOption.value= JSON.stringify(this.moveInfo) ;

        untactForm.email.value = this.$store.state.loginInfo.email;
        untactForm.reservStatus.value =0;

          // Form data로 input의 모든 값을 담아 보낸다.
        var formData = new FormData(document.querySelector('#untactForm'));  
        var step;
          //formdata로 이미지 보내기 
        for(step=0; step<this.moveImage.files1.length; step++){
          formData.append("images1", this.moveImage.files1[step]);
        }
        for(step=0; step<this.moveImage.files2.length; step++){
          formData.append("images2", this.moveImage.files2[step]);
        }
        for(step=0; step<this.moveImage.files3.length; step++){
          formData.append("images3", this.moveImage.files3[step]);
        }
          //비대면 견적요청 insert
        this.$axios({
          url: "/zippy/move/moveUntactCheck",
          method: "POST",
          data: formData
        }).then(res => {
          console.log(res);
         
            //견적상태변경
          this.$axios({
            url: "/zippy/move/moveStatusUpdateZero",
            method: "POST",
          
            params:{
              estimateNo : this.selectData.estimateNo,
              email : this.selectData.email,
              reservStatus : 0
            },
            // data: formData
          }).then(res => {
            console.log(res);
            alert("견적서 보내기 완료!");
            

          }).catch(err => {
            console.log(err)
          })

        }).catch(err => {
          console.log(err)
        })


        swal.fire({
            title: '입력정보와 유의사항을 확인해주세요.',
            text: "한 번 요청한 견적정보는 수정할 수 없습니다. 이대로 견적 요청을 보내시겠습니까?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#96e5b8',
            cancelButtonColor: '#a9a9a9',
            confirmButtonText: 'YES'
          }).then((result) => {
            if (result.isConfirmed) {

              // 견적요청 후, '요청한 견적보기' 페이지로 정보를 보낸다.
              this.$router.push({
                path: "/mypage/move/moveResult",
                // name: "moveResult",
                params: {
                  moveImage: this.moveImage,
                  moveDetail: this.moveDetail,
                  moveEstimateType: this.moveEstimateType,
                  moveInfo: this.moveInfo,
                  moveType: this.moveType,
                  moveDate: this.moveDate, 
                  moveAddress: this.moveAddress,
                  moveVisit : this.moveVisit,
                  requestDate : this.requestDateSend,
                  email: this.$store.state.loginInfo.email                 
                }
              })

            } 
          })
      }, 

      //짐 추가 및 삭제
      addBed: function () {
        this.moveDetail[0].bed.push();
        this.moveDetail[0].bedCount++;
      },
      removeBed: function (i) {
        if (i > 1) {
          this.moveDetail[0].bed[i]="";
          this.moveDetail[0].bedCount--;
        } else if (i < 1) {
          
          this.moveDetail[0].bedCount = 1;
        }
      },

      addSofa: function(){
        this.moveDetail[0].sofa.push();
        this.moveDetail[0].sofaCount++;        
      },
      removeSofa: function(i){
       if(i>1){
        this.moveDetail[0].sofa[i]="";
        this.moveDetail[0].sofaCount--;
       }else if(i<1){
        this.moveDetail[0].sofaCount = 1;
       }
      },

      addCloset: function(){
        this.moveDetail[0].closet.push();
        this.moveDetail[0].closetCount++;        
      },
      removeCloset: function(i){
       if(i>1){
        this.moveDetail[0].closet[i]="";
        this.moveDetail[0].closetCount--;
       }else if(i<1){
        this.moveDetail[0].closetCount = 1;
       }
      },

      addClosets: function(){
        this.moveDetail[0].closets.push();
        this.moveDetail[0].closetsCount++;        
      },
      removeClosets: function(i){
       if(i>1){
        this.moveDetail[0].closets[i]="";
        this.moveDetail[0].closetsCount--;
       }else if(i<1){
        this.moveDetail[0].closetsCount = 1;
       }
      },

      addTable: function(){
        this.moveDetail[0].table.push();
        this.moveDetail[0].tableCount++;        
      },
      removeTable: function(i){
       if(i>1){
        this.moveDetail[0].table[i]="";
        this.moveDetail[0].tableCount--;
       }else if(i<1){
        this.moveDetail[0].tableCount = 1;
       }
      },

      addChair: function(){
        this.moveDetail[0].chair.push();
        this.moveDetail[0].chairCount++;        
      },
      removeChair: function(i){
       if(i>1){
        this.moveDetail[0].chair[i]="";
        this.moveDetail[0].chairCount--;
       }else if(i<1){
        this.moveDetail[0].chairCount = 1;
       }
      },

      addTv: function(){
        this.moveDetail[0].tv.push();
        this.moveDetail[0].tvCount++;        
      },
      removeTv: function(i){
       if(i>1){
        this.moveDetail[0].tv[i]="";
        this.moveDetail[0].tvCount--;
       }else if(i<1){
        this.moveDetail[0].tvCount = 1;
       }
      },

      addPc: function(){
        this.moveDetail[0].pc.push();
        this.moveDetail[0].pcCount++;        
      },
      removePc: function(i){
       if(i>1){
        this.moveDetail[0].pc[i]="";
        this.moveDetail[0].pcCount--;
       }else if(i<1){
        this.moveDetail[0].pcCount = 1;
       }
      },

      
      addFridge: function(){
        this.moveDetail[0].fridge.push();
        this.moveDetail[0].fridgeCount++;        
      },
      removeFridge: function(i){
       if(i>1){
        this.moveDetail[0].fridge[i]="";
        this.moveDetail[0].fridgeCount--;
       }else if(i<1){
        this.moveDetail[0].fridgeCount = 1;
       }
      },

      addTrolley: function(){
        
        this.moveDetail[0].trolley.push();
        this.moveDetail[0].trolleyCount++;        
      },
      removeTrolley: function(i){
       if(i>1){
        this.moveDetail[0].trolley[i]="";
        this.moveDetail[0].trolleyCount--;
       }else if(i<1){
        this.moveDetail[0].trolleyCount = 1;
       }
      },

      addAircon: function(){
        
        this.moveDetail[0].aircon.push();
        this.moveDetail[0].airconCount++;        
      },
      removeAircon: function(i){
       if(i>1){
        this.moveDetail[0].aircon[i]="";
        this.moveDetail[0].airconCount--;
       }else if(i<1){
        this.moveDetail[0].airconCount = 1;
       }
      },

      addLaundry: function(){
        
        this.moveDetail[0].laundry.push();
        this.moveDetail[0].laundryCount++;        
      },
      removeLaundry: function(i){
       if(i>1){
        this.moveDetail[0].laundry[i]="";
        this.moveDetail[0].laundryCount--;
       }else if(i<1){
        this.moveDetail[0].laundryCount = 1;
       }
      },

      addEtc: function(){
        this.moveDetail[0].etcName.push();
        this.moveDetail[0].etcSize.push();
        this.moveDetail[0].etcCount++;        
      },
      removeEtc: function(i){
       if(i>1){
        this.moveDetail[0].etcName[i]="";
        this.moveDetail[0].etcSize[i]="";
        this.moveDetail[0].etcCount--;
       }else if(i<1){
        this.moveDetail[0].etcCount = 1;
       }
      },

      //우편번호api
      execDaumPostcode(number) {
        console.log(number);
        var postcode = document.querySelector("#post" + number);
        var addr = document.querySelector("#address" + number);
        var detail = document.querySelector("#detailAddress" + number);
        var extra = document.querySelector("#extraAddress" + number);

        new window.daum.Postcode({
          oncomplete: (data) => {
            console.log(data);
            if (data.userSelectedType === "R") {
              // 사용자가 도로명 주소를 선택했을 경우
              // this.moveInfo.addr1.address = data.roadAddress;
              addr.value = data.roadAddress;
            } else {
              // 사용자가 지번 주소를 선택했을 경우(J)
              addr.value = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if (data.userSelectedType === "R") {
              // 법정동명이 있을 경우 추가한다. (법정리는 제외)
              // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
              if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
                detail.value += data.bname;
              }

              // 건물명이 있고, 공동주택일 경우 추가한다.
              if (data.buildingName !== "" && data.apartment === "Y") {
                detail.value +=
                  (detail.value !== "") ? `, ${data.buildingName}` : data.buildingName;
              }
              // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
              if (detail.value !== "") {
                detail.value = `(${detail.value})`;
              }
            } else {
              detail.value = "";
            }
            // 우편번호를 입력한다.
            postcode.value = data.zonecode;
          },
        }).open();

      },

    },
  };
</script>

<style scoped>

@font-face {
    font-family: 'GmarketSans';
    font-weight: 500;
    font-style: normal;
    src: url('https://cdn.jsdelivr.net/gh/webfontworld/gmarket/GmarketSansMedium.eot');
    src: url('https://cdn.jsdelivr.net/gh/webfontworld/gmarket/GmarketSansMedium.eot?#iefix') format('embedded-opentype'),
         url('https://cdn.jsdelivr.net/gh/webfontworld/gmarket/GmarketSansMedium.woff2') format('woff2'),
         url('https://cdn.jsdelivr.net/gh/webfontworld/gmarket/GmarketSansMedium.woff') format('woff'),
         url('https://cdn.jsdelivr.net/gh/webfontworld/gmarket/GmarketSansMedium.ttf') format("truetype");
    font-display: swap;
} 
* {
  font-family: 'GmarketSans';
}

#untactForm{
  margin: 0 100px 100px 100px;
}

.agree_ul{
  list-style: none;
}

h4{
  color: #42b983;
  font-weight: bold;
  text-align: center;
  padding: 50px;
}

/* 짐정보 */
.select-size{
  float: left;
}

.lauguage-info{
  overflow: hidden;
}

.lable{
  font-size: 25px;
  font-weight: bold;
}

.etc-class{
  margin-left: 600px;
}
/* .minus-btn{
  float: left;
} */

/* 이삿짐 상세 */

.whole-furniture{
  display: flex;
}
.whole-application{
  display: flex;
}
  .lauguage-info{
    display: inline-flex;
    height: 150px;
    justify-content: space-between;
  }

.select-home{
  width: 300px;
}

.single{
  width: 400px;
  text-align: center;
  float: left;
  width: 30%;
  margin: 30px;
}
.single1{
  width: 400px;
  text-align: center;
  float: left;
  width: 30%;
  margin: 30px;
}

#etc-plus{
  margin-bottom: 30px;
}
  .move-detail {
    width: 1500px;
  }

  .move-detail-drop {
    width: 1500px;
  }

  .move-furniture1 {
    width: 250px;
    display: inline-block;
    margin: 40px;
  }

  .move-furniture2 {
    width: 250px;
    display: inline-block;
    margin: 40px;
  }



  .material-icons.md-54 {
    font-size: 80px;
  }

  .photos{
    margin-bottom: 50px;
  }

  /* input */

  .type-2 {
    background-color: #fafafa;
    border: 0;
    box-shadow: 0 0 4px rgba(0, 0, 0, 0.3);
    transition: 0.3s box-shadow;
    width: 300px;
    border-radius: 5px;
  }

  .type-2:hover {
    box-shadow: 0 0 4px rgba(0, 0, 0, 0.5);
  }

  input {
    margin: 5px;
  }

  .move-furniture1 {
    display: inline-block;
    margin: 20px;
  }

  .move-furniture2 {
    display: inline-block;
    margin: 20px;
  }

  .type-2 {
    background-color: #fafafa;
    border: 0;
    box-shadow: 0 0 4px rgba(0, 0, 0, 0.3);
    transition: 0.3s box-shadow;
    width: 200px;
    height: 40px;
    border-radius: 5px;
  }

  .type-2:hover {
    box-shadow: 0 0 4px rgba(0, 0, 0, 0.5);
  }




  table {
    width: 100%;
    text-align: center;
  }

  thead {
    text-align: center;
    padding-bottom: 50px;
  }

  .agree {
    text-align: center;
    padding-top: 50px;
    padding-bottom: 50px;
  }

  .agree-check {
    display: inline-block;
  }

  .final-btn {
    text-align: center;
    padding-bottom: 50px;
  }


  h2 {
    text-align: center;
    padding-top: 100px;
    padding-bottom: 100px;
  }

  .drop-btn {
    /* float: right; */
    position: absolute;
    left: 50%;
    top: 50%;
    transform: translate(-50%, -50%);
    margin-left: 500px;
    border: 1px solid #ddd;
    border-radius: 5px;
  }

  .v-select {
    float: left;
    margin: 50px;
    /* margin-left: 100px; */
  }

  .v-expansion-panels {
    padding-bottom: 100px;
  }

  table{
    font-size: 20px;
  }

  /* zip */
  .depart-address {
    margin-top: 100px;
  }

  .move-drop {
    display: inline-block;
    width: 250px;
    height: 200px;
  }

  .move-select-input {
    display: inline-block;
    width: 250px;
    height: 200px;
  }

  /* 버튼 */

  .custom-btn {
    width: 200px;
    height: 100px;
    color: #fff;
    border-radius: 5px;
    padding: 10px 25px;
    font-family: "Lato", sans-serif;
    font-weight: bold;
    /* font-size: 20px; */
    background: transparent;
    cursor: pointer;
    transition: all 0.3s ease;
    position: relative;
    display: inline-block;
    box-shadow: inset 2px 2px 2px 0px rgba(255, 255, 255, 0.5),
      7px 7px 20px 0px rgba(0, 0, 0, 0.1), 4px 4px 5px 0px rgba(0, 0, 0, 0.1);
    outline: none;
  }

  .btn-4 {
    background: rgb(163, 162, 162);
    background: linear-gradient(0deg,
        rgba(163, 162, 162, 1) 0%,
        rgb(163, 162, 162) 100%);
    border: none;
    width: 50px;
    height: 50px;
    font-size: 15px;
  }

  .btn-4:hover {
    /* background: rgb(136, 221, 164);
      background: linear-gradient(0deg, rgba(136, 221, 164, 1) 0%, rgb(131, 221, 161) 100%); */
    box-shadow: 4px 4px 6px 0 rgba(255, 255, 255, 0.5),
      -4px -4px 6px 0 rgba(116, 125, 136, 0.5),
      inset -4px -4px 6px 0 rgba(255, 255, 255, 0.2),
      inset 4px 4px 6px 0 rgba(0, 0, 0, 0.4);
  }

  .btn-3 {
    background: rgb(163, 162, 162);
    background: linear-gradient(0deg,
        rgba(163, 162, 162, 1) 0%,
        rgb(163, 162, 162) 100%);
    border: none;
    height: 50px;
    width: 150px;
    font-size: 20px;
  }

  .btn-3:hover {
    /* background: rgb(136, 221, 164);
      background: linear-gradient(0deg, rgba(136, 221, 164, 1) 0%, rgb(131, 221, 161) 100%); */
    box-shadow: 4px 4px 6px 0 rgba(255, 255, 255, 0.5),
      -4px -4px 6px 0 rgba(116, 125, 136, 0.5),
      inset -4px -4px 6px 0 rgba(255, 255, 255, 0.2),
      inset 4px 4px 6px 0 rgba(0, 0, 0, 0.4);
  }

  /* 라디오 */
  .detail-info-radio {
    display: inline-block;
    width: 250px;
  }

  .test_obj input[type="radio"] {
    display: none;
  }

  .test_obj input[type="radio"]+span {
    display: inline-block;
    padding: 10px 10px;
    border: 1px solid #dfdfdf;
    background-color: #ffffff;
    text-align: center;
    cursor: pointer;
    border-radius: 20px;
    width: 80px;

    box-shadow: 0 0 4px rgba(0, 0, 0, 0.2);
    transition: 0.3s box-shadow;
  }

  .test_obj input[type="radio"]:checked+span {
    background-color: #b3e3c3;
    color: #ffffff;
  }

  /* 드롭 */
  @import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap");

  select {
    -moz-appearance: none;
    -webkit-appearance: none;
    appearance: none;

    font-family: "Noto Sansf KR", sans-serif;
    font-size: 1rem;
    font-weight: 400;
    line-height: 1.5;

    color: #444;
    background-color: #fff;

    padding: 0.6em 1.4em 0.5em 0.8em;
    margin: 0;

    border: 1px solid #aaa;
    border-radius: 0.5em;
    /* box-shadow: 0 1px 0 1px rgba(0,0,0,.2); */
    box-shadow: 0 0 4px rgba(0, 0, 0, 0.2);
    transition: 0.3s box-shadow;
    width: 200px;
  }

  select:hover {
    border-color: #888;
  }

  select:focus {
    border-color: #aaa;
    box-shadow: 0 0 1px 3px rgba(59, 153, 252, 0.7);
    box-shadow: 0 0 0 3px -moz-mac-focusring;
    color: #222;
    outline: none;
  }

  select:disabled {
    opacity: 0.5;
  }

  label {
    font-family: "Noto Sans KR", sans-serif;
    font-size: 1rem;
    font-weight: 600;
    line-height: 1.3;

    color: #444;

    margin-right: 0.5em;
  }

  /* 사진 */
  img {
    width: 300px;
  }
</style>