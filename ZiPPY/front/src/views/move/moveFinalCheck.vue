<template>
  <div class="wrapper">
    <moveNav></moveNav>
    
      <h2>이사 견적을 위해 입력한 정보를 확인해주세요.</h2>

    <v-expansion-panels>
    <v-expansion-panel>
      <v-expansion-panel-header>
        <template v-slot:default="{ open }">
          <v-row no-gutters>
            <v-col cols="4">
              이사유형
            </v-col>
            <v-col
              cols="8"
              class="text--secondary"
            >
              <v-fade-transition leave-absolute>
                <span
                  v-if="open"
                  key="0"
                >
                  선택한 이사유형
                </span>
                <span
                  v-else
                  key="1"
                >
                  {{ move.name }}
                </span>
              </v-fade-transition>
            </v-col>
          </v-row>
        </template>
      </v-expansion-panel-header>
      <v-expansion-panel-content>
        <v-text-field
          v-model="move.name"
          placeholder="선택한 이사유형 불러오기" readonly="readonly" outlined
        ></v-text-field>
      </v-expansion-panel-content>
    </v-expansion-panel>

    <v-expansion-panel>
      <v-expansion-panel-header v-slot="{ open }">
        <v-row no-gutters>
          <v-col cols="4">
            이사유형
          </v-col>
          <v-col
            cols="8"
            class="text--secondary"
          >
            <v-fade-transition leave-absolute>
              <span
                v-if="open"
                key="0"
              >
                <!-- 선택한 이사유형 -->
              </span>
              <span
                v-else
                key="1"
              >
                {{ move.type }}
              </span>
            </v-fade-transition>
          </v-col>
        </v-row>
      </v-expansion-panel-header>

      <v-expansion-panel-content>
        <v-row no-gutters>
          <v-spacer></v-spacer>
          <v-col cols="5">
            <v-select
              v-model="move.type"
              :items="types"
              chips
              flat
              solo
              outlined
              placeholder="선택한 이사유형 불러오기"
            ></v-select>
           
            <div class="drop-btn">
            <v-btn
            text
            color="secondary"
          >
            취소
          </v-btn>
          <v-btn
            text
            color="primary"
          >
            수정
          </v-btn>
        </div>

          </v-col>
<!-- 
          <v-divider
            vertical
            class="mx-4"
          ></v-divider>

          <v-col cols="3">
            Select your destination of choice
            <br>
            <a href="#">Learn more</a>
          </v-col> 
          <v-btn
            text
            color="secondary"
          >
            취소
          </v-btn>
          <v-btn
            text
            color="primary"
          >
            수정
          </v-btn>-->       
        </v-row>
      </v-expansion-panel-content>
    </v-expansion-panel>

    <v-expansion-panel>
      <v-expansion-panel-header v-slot="{ open }">
        <v-row no-gutters>
          <v-col cols="4">
            이사희망일 및 시간
          </v-col>
          <v-col
            cols="8"
            class="text--secondary"
          >
            <v-fade-transition leave-absolute>
              <span v-if="open">이사희망 날짜와 시간을 선택해주세요.</span>
              <v-row
                v-else
                no-gutters
                style="width: 100%"
              >
                <v-col cols="6">
                  이사 희망일: {{ move.start || 'Not set' }}
                </v-col>
                <v-col cols="6">
                  이사 희망시간: {{ move.end || 'Not set' }}
                </v-col>
              </v-row>
            </v-fade-transition>
          </v-col>
        </v-row>
      </v-expansion-panel-header>
      <v-expansion-panel-content>
        <v-row
          justify="space-around"
          no-gutters
        >
          <v-col cols="3">
            <v-menu
              ref="startMenu"
              :close-on-content-click="false"
              :return-value.sync="move.start"
              offset-y
              min-width="290px"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-text-field
                  v-model="move.start"
                  label="이사희망일"
                  prepend-icon="mdi-calendar"
                  readonly
                  v-bind="attrs"
                  v-on="on"
                ></v-text-field>
              </template>
              <v-date-picker
                v-model="date"
                no-title
                scrollable
              >
                <v-spacer></v-spacer>
                <v-btn
                  text
                  color="primary"
                  @click="$refs.startMenu.isActive = false"
                >
                  취소
                </v-btn>
                <v-btn
                  text
                  color="primary"
                  @click="$refs.startMenu.save(date)"
                >
                  수정
                </v-btn>
              </v-date-picker>
            </v-menu>
          </v-col>

          <v-col cols="3">
            <v-menu
              ref="endMenu"
              :close-on-content-click="false"
              :return-value.sync="move.end"
              offset-y
              min-width="290px"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-text-field
                  v-model="move.end"
                  label="End date"
                  prepend-icon="mdi-clock"
                  readonly
                  v-bind="attrs"
                  v-on="on"
                ></v-text-field>
              </template>
              <!-- <v-date-picker
                v-model="date"
                no-title
                scrollable
              > -->
              <v-time-picker
                  v-model="time"
                  ampm-in-title
                  format="ampm"
                >
                <v-spacer></v-spacer>
                <v-btn
                  text
                  color="primary"
                  @click="$refs.endMenu.isActive = false"
                >
                  취소
                </v-btn>
                <v-btn
                  text
                  color="primary"
                  @click="$refs.endMenu.save(time)"
                >
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
          <v-col cols="4">
            이사 출발지 도착지 주소
          </v-col>
          <v-col
            cols="8"
            class="text--secondary"
          >
            <v-fade-transition leave-absolute>
              <span v-if="open">이사 출발지와 도착지 주소를 확인해주세요.</span>
              <v-row
                v-else
                no-gutters
                style="width: 100%"
              >
                <v-col cols="6">
                  이사 출발지 주소: {{ move.depart || 'Not set' }}
                </v-col>
                <v-col cols="6">
                  이사 도착지 주소: {{ move.arrive || 'Not set' }}
                </v-col>
              </v-row>
            </v-fade-transition>
          </v-col>
        </v-row>
      </v-expansion-panel-header>
      <v-expansion-panel-content>
        <v-row
          justify="space-around"
          no-gutters
        >
          <v-col cols="3">
            <v-menu
              ref="startMenu"
              :close-on-content-click="false"
              :return-value.sync="move.depart"
              offset-y
              min-width="290px"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-text-field
                  v-model="move.depart"
                  label="출발지 주소"
                  prepend-icon="mdi-home"
                  readonly
                  v-bind="attrs"
                  v-on="on"
                ></v-text-field>
              </template>
              <!-- <v-date-picker
                v-model="date"
                no-title
                scrollable
              > -->
              <v-card>
              우편번호 :
                  <input
                    v-model="postcode"
                    class="type-2"
                    type="text"
                    name="zip1"
                    style="width: 80px; height: 26px"
                  />
                  <button
                    class="custom-btn btn-4"
                    type="button"
                    @click="execDaumPostcode()"
                  >
                    검색
                  </button>
                  <br />
                  주소 :
                  <input
                    v-model="address"
                    id="address"
                    class="type-2"
                    type="text"
                    name="addr1"
                    style="width: 300px; height: 30px"
                    readonly
                  /><br />
                  상세 :
                  <input
                    v-model="extraAddress"
                    id="detailAddress"
                    class="type-2"
                    type="text"
                    name="addr2"
                    style="width: 300px; height: 30px"
                  /><br />
                  참고항목 :
                  <input
                    type="text"
                    id="extraAddress"
                    class="type-2"
                    placeholder="참고항목"
                  />
                <v-spacer></v-spacer>
                <v-btn
                  text
                  color="primary"
                  @click="$refs.startMenu.isActive = false"
                >
                  취소
                </v-btn>
                <v-btn
                  text
                  color="primary"
                  @click="$refs.startMenu.save(postcode)"
                >
                  수정
                </v-btn>
              </v-card>
              <!-- </v-date-picker> -->
            </v-menu>
          </v-col>

          <v-col cols="3">
            <v-menu
              ref="endMenu"
              :close-on-content-click="false"
              :return-value.sync="move.arrive"
              offset-y
              min-width="290px"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-text-field
                  v-model="move.arrive"
                  label="도착지 주소"
                  prepend-icon="mdi-home"
                  readonly
                  v-bind="attrs"
                  v-on="on"
                ></v-text-field>
              </template>
              
              <v-card>
                우편번호 :
                <input
                  v-model="postcode2"
                  class="type-2"
                  type="text"
                  name="zip2"
                  style="width: 80px; height: 26px"
                />
            <button
                  class="custom-btn btn-4"
                  type="button"
                  @click="execDaumPostcode2()"
                >           
                  검색
                </button>
                <br />
                주소 :
                <input
                  v-model="address2"
                  id="address2"
                  class="type-2"
                  type="text"
                  name="addr3"
                  style="width: 300px; height: 30px"
                  readonly
                /><br />
                상세 :
                <input
                  v-model="extraAddress2"
                  id="detailAddress2"
                  class="type-2"
                  type="text"
                  name="addr4"
                  style="width: 300px; height: 30px"
                /><br />
                참고항목 :
                <input
                  type="text"
                  id="extraAddress2"
                  class="type-2"
                  placeholder="참고항목"
                />
                <v-spacer></v-spacer>
                <v-btn
                  text
                  color="primary"
                  @click="$refs.endMenu.isActive = false"
                >
                  취소
                </v-btn>
                <v-btn
                  text
                  color="primary"
                  @click="$refs.endMenu.save(postcode2)"
                >
                  수정
                </v-btn>
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
          <v-col cols="4">
            집 정보
          </v-col>
          <v-col
            cols="8"
            class="text--secondary"
          >
            <v-fade-transition leave-absolute>
              <span
                v-if="open"
                key="0"
              >
                <!-- 선택한 이사유형 -->
              </span>
              <span
                v-else
                key="1"
              >
                {{ move.type }}
              </span>
            </v-fade-transition>
          </v-col>
        </v-row>
      </v-expansion-panel-header>

      <v-expansion-panel-content>
        <v-row no-gutters>
          <v-spacer></v-spacer>
          <v-col cols="5">
            <v-select
              v-model="move.type"
              :items="types"
              chips
              flat
              solo
              outlined
              placeholder="선택한 집형태 불러오기"
            ></v-select>
            <v-select
              v-model="move.type"
              :items="types"
              chips
              flat
              solo
              outlined
              placeholder="선택한 집평수 불러오기"
            ></v-select>
            <v-select
              v-model="move.type"
              :items="types"
              chips
              flat
              solo
              outlined
              placeholder="선택한 이사유형 불러오기"
            ></v-select>
           
            <div class="drop-btn">
            <v-btn
            text
            color="secondary"
          >
            취소
          </v-btn>
          <v-btn
            text
            color="primary"
          >
            수정
          </v-btn>
        </div>

          </v-col>
<!-- 
          <v-divider
            vertical
            class="mx-4"
          ></v-divider>

          <v-col cols="3">
            Select your destination of choice
            <br>
            <a href="#">Learn more</a>
          </v-col> 
          <v-btn
            text
            color="secondary"
          >
            취소
          </v-btn>
          <v-btn
            text
            color="primary"
          >
            수정
          </v-btn>-->       
        </v-row>
      </v-expansion-panel-content>
    </v-expansion-panel>

  </v-expansion-panels>

</div>
</template>

<script>
  export default {
    
      data: () => ({
      date: null,
      time: null,
      postcode : null,
      postcode2 : null,
      move: {
        name: '',
        type: null,
        start: null,
        end: null,
        depart : null,
        arrive: null
      },
      types: ['소형이사', '가정이사'],

      // 우편번호
      postcode: "",
      address: "",
      extraAddress: "",
      postcode2: "",
      address2: "",
      extraAddress2: "",
    
    }),
    
    methods: {
    execDaumPostcode() {
      new window.daum.Postcode({
        oncomplete: (data) => {
          if (this.extraAddress !== "") {
            this.extraAddress = "";
          }
          if (data.userSelectedType === "R") {
            // 사용자가 도로명 주소를 선택했을 경우
            this.address = data.roadAddress;
          } else {
            // 사용자가 지번 주소를 선택했을 경우(J)
            this.address = data.jibunAddress;
          }

          // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
          if (data.userSelectedType === "R") {
            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
              this.extraAddress += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if (data.buildingName !== "" && data.apartment === "Y") {
              this.extraAddress +=
                this.extraAddress !== ""
                  ? `, ${data.buildingName}`
                  : data.buildingName;
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if (this.extraAddress !== "") {
              this.extraAddress = `(${this.extraAddress})`;
            }
          } else {
            this.extraAddress = "";
          }
          // 우편번호를 입력한다.
          this.postcode = data.zonecode;
        },
      }).open();
    },
    execDaumPostcode2() {
      new window.daum.Postcode({
        oncomplete: (data) => {
          if (this.extraAddress2 !== "") {
            this.extraAddress2 = "";
          }
          if (data.userSelectedType === "R") {
            // 사용자가 도로명 주소를 선택했을 경우
            this.address2 = data.roadAddress;
          } else {
            // 사용자가 지번 주소를 선택했을 경우(J)
            this.address2 = data.jibunAddress;
          }

          // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
          if (data.userSelectedType === "R") {
            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
              this.extraAddress2 += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if (data.buildingName !== "" && data.apartment === "Y") {
              this.extraAddress2 +=
                this.extraAddress2 !== ""
                  ? `, ${data.buildingName}`
                  : data.buildingName;
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if (this.extraAddress2 !== "") {
              this.extraAddress2 = `(${this.extraAddress2})`;
            }
          } else {
            this.extraAddress2 = "";
          }
          // 우편번호를 입력한다.
          this.postcode2 = data.zonecode;
        },
      }).open();
    },
  },
    
  }
</script>

<style scoped>
  h2{
    text-align: center;
    padding-top: 100px;
    padding-bottom: 100px;
  }
  .drop-btn{
    float: right;
    margin-right: 100px;
  }
  .v-select{
    float: left;  
    /* margin-left: 100px; */
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
  background: linear-gradient(
    0deg,
    rgba(163, 162, 162, 1) 0%,
    rgb(163, 162, 162) 100%
  );
  border: none;
  width: 80px;
  height: 32px;
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
  background: linear-gradient(
    0deg,
    rgba(163, 162, 162, 1) 0%,
    rgb(163, 162, 162) 100%
  );
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
</style>