<template>
    <div>
      <div id="download">
        <h1 v-if="item.user1 == $store.state.loginInfo.email">
          <img>{{item.user2Name}}</h1>
  
        <h1 v-if="item.user2 == $store.state.loginInfo.email">
          <img>{{item.user1Name}}</h1>
  
        <v-btn v-if="item.user2 == $store.state.loginInfo.email" id="used-soldout" width="90px" depressed color=#B3E3C3
          @click="soldOut()">
          판매완료
        </v-btn>
        <div id="download-btn" class="ml-auto">
          <button type="button" class="form-control"><a :href="txtFile">TXT 파일 다운로드</a></button>
          <button type="button" class="form-control"><a :href="pdfFile">PDF 파일 다운로드</a></button>
        </div>
  
      </div><br>
      <div id="chatBody" style="overflow:auto;">
        <ul class="list-group">
          <li class="list-group-item" v-for="msg in messages">
            <div v-if="msg.sender == sender" style="text-align: right; ">
              <div class="ccard">
                <v-card class="mx-auto" max-width="400" outlined shaped style="background-color: #b3e3c3;">
                  <v-list-item three-line>
                    <v-list-item-content>
                      <div class="text-overline mb-4">
                        {{msg.sender}}<small>-{{msg.time}}</small>
                      </div>
                      <v-list-item-title class="">
                        {{msg.message}}
                      </v-list-item-title>
                    </v-list-item-content>
                  </v-list-item>
                </v-card>
              </div>
            </div>
            <div class="ccard">
              <div v-if="msg.sender != sender" style="text-align: left;">
                <v-card class="mx-auto" max-width="400" outlined shaped>
                  <v-list-item three-line>
                    <v-list-item-content>
                      <div class="text-overline mb-4">
                        {{msg.sender}}<small>-{{msg.time}}</small>
                      </div>
                      <v-list-item-title class="">
                        {{msg.message}}
                      </v-list-item-title>
                    </v-list-item-content>
                  </v-list-item>
                </v-card>
              </div>
            </div>
          </li>
        </ul>
        <div id="bottom"></div>
      </div><br>
  
      <div class="input-group">
        <div class="input-group-prepend">
          <label class="input-group-text">내용</label>
        </div>
        <input type="text" class="form-control" v-model="message" v-on:keypress.enter="sendMessage">
        <div class="input-group-append">
          <button type="button" @click="sendMessage"><i class="fa-regular fa-paper-plane fa-2x"></i></button>
        </div>
      </div>
    </div>  
  </template>
  
  <script>
  import Stomp from 'webstomp-client'
  import SockJS from 'sockjs-client'
  
var reconnect = 0;
  
    // vue.js
    export default {
      props: ['item', 'value'],
      data() {
        return {
          txtFile : "/zippy/chat/txtFile/" + this.roomId,
          pdfFile : "/zippy/chat/pdfFile/" + this.roomId,

          room: {},
          sender: '',
          message: '',
          messages: [],
          isConnect: false,
          list : {
            isSell : 1,
            dealRecord : "",
            productNo : ""
          }
        }
      },
      watch: {
        value: function () {
          if (this.roomId == this.value.roomId) {
            this.messages.push(this.value);
            console.log(this.roomId + "WATCH!!! RUN!!");
          }
          this.goToBottom();
        }
      },
      created() {        
        this.sender = this.$store.getters.getName;        
        this.findRoomInfo();
        this.loadContent();
      },
  
  
      methods: {
        goToBottom: function () {
          var body = document.querySelector("#chatBody");
          body.scrollTop = body.scrollHeight;
        },

        findRoomInfo : function(){
            var out = this;
            this.$axios({
                url : "/zippy/chat/roomInfo",
                method: "GET",
                params:{
                    roomId : this.$route.query.roomId
                }
            }).then(res =>{
                out.room = res.data;                
            })
        },
  
        loadContent: async function () {
          var temp = await this.$axios({
            url: "/zippy/chat/room/load",
            params: {
              roomId: this.room.chatRoomNo
            }
          });
          this.messages = temp.data;
          console.log(this.messages);
          this.goToBottom();
        },
        
        sendMessage: function () {
          if (this.message == '') {
  
          } else {
            this.$ws.send("/app/chat/message", JSON.stringify({
              type: 'TALK',
              roomId: this.roomId,
              sender: this.sender,
              message: this.message,
              time: this.getTime()
            }));
            this.message = '';
          }
        },
  
        recvMessage: function (recv) {
          this.messages.push({
            "type": recv.type,
            "sender": recv.type == 'ENTER' ? '[알림]' : recv.sender,
            "message": recv.message,
            "time": recv.time
          })
        },
  
        getTime: function () {
          var now = new Date();
          var str = "" + now.getFullYear() + "/" + (now.getMonth() + 1) + "/" + now.getDate() + "-" +
            now.getHours() + ":" + now.getMinutes()
  
          return str;
        },
        soldOut() {
          this.list.dealRecord = this.item.user1;
          this.list.productNo = this.data.productNo;
          axios({
            url: "/zippy/used/soldot",
            method: "POST",
            headers: {
              "Content-Type": "application/json; charset=utf-8"
            },
            data: JSON.stringify(this.data)
          }).then(res => {
            console.log(res);
          }).catch(error => {
            console.log(error);
          })
        }
      }
    }
  </script>
  
  <style scoped>
    #container {
      height: 70vh;
    }
  
    h5 {
      display: inline-block;
    }
  
    p {
      margin: 0px;
    }
  
    li {
      list-style: none;
    }
  
    #used-soldout {
      margin-top: 15px;
      margin-left: 20px;
    }
  
    .list-group {
      padding: 0;
    }
  
    #chatBody {
      height: 70vh;
      border: 1px solid black;
    }
  
    #chatBody {
      -ms-overflow-style: none;
      /* IE and Edge */
      scrollbar-width: none;
      /* Firefox */
    }
  
    #chatBody::-webkit-scrollbar {
      display: none;
      /* Chrome, Safari, Opera*/
    }
  
    .container {
      width: 40%;
    }
  
    .fa-paper-plane {
      margin-left: 10px;
      color: #B3E3C3;
    }
  
    .ccard {
      display: inline-block;
    }
  
    .list-group-item {
      border: none;
    }
  
    #download {
      width: 100%;
      /* display: -webkit-box; */
      display: flex;
    }
  
    #download h1 {
      display: inline-block;
  
    }
  
    #download-btn {
      display: flex;
    }
  
    #download button {
      width: 100px;
    }
  
    a {
      text-decoration: none;
    }
  </style>