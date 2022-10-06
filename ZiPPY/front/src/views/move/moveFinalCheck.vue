<template>
  <div class="wrapper">
    <div class="text-center d-flex pb-4">
      <v-btn @click="all">
        전체보기
      </v-btn>
      <div>{{ panel }}</div>
      <v-btn @click="none">
        펼침닫기
      </v-btn>
      <h2>이사 견적을 위해 입력한 정보를 확인해주세요.</h2>
    </div>

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
            Start and end dates
          </v-col>
          <v-col
            cols="8"
            class="text--secondary"
          >
            <v-fade-transition leave-absolute>
              <span v-if="open">When do you want to travel?</span>
              <v-row
                v-else
                no-gutters
                style="width: 100%"
              >
                <v-col cols="6">
                  Start date: {{ move.start || 'Not set' }}
                </v-col>
                <v-col cols="6">
                  End date: {{ move.end || 'Not set' }}
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
                  label="Start date"
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
                  Cancel
                </v-btn>
                <v-btn
                  text
                  color="primary"
                  @click="$refs.startMenu.save(date)"
                >
                  OK
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
                  @click="$refs.endMenu.isActive = false"
                >
                  Cancel
                </v-btn>
                <v-btn
                  text
                  color="primary"
                  @click="$refs.endMenu.save(date)"
                >
                  OK
                </v-btn>
              </v-date-picker>
            </v-menu>
          </v-col>
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
      move: {
        name: '',
        type: null,
        start: null,
        end: null,
      },
      types: ['소형이사', '가정이사', 'Chile', 'Denmark', 'Ecuador', 'France'],

      // return :{
      //   panel: [],
      //   items: 5,
      // }
    }),
    
    // methods: {
    //   // Create an array the length of our items
    //   // with all values as true
    //   all () {
    //     this.panel = [...Array(this.items).keys()].map((k, i) => i)
    //   },
    //   // Reset the panel
    //   none () {
    //     this.panel = []
    //   },
    // },
  }
</script>

<style scoped>
  .drop-btn{
    float: right;
    margin-right: 100px;
  }
  .v-select{
    float: left; 
    margin-left: 100px;
  }
</style>