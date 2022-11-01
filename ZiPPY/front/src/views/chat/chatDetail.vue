<template>
  <div style="border-top: 1px solid #D6D6D6;">
    <div>
      <div class="container" style="margin-top:100px">
        <div id="chat">
        <div id="download">
          <h4 v-if="room.user1 == $store.state.loginInfo.email">
            <img :src="'/zippy/common/img/member/' + room.user2Pic"
              style="border-radius:50%; width: 80px; height: 80px;" class="mr-5">{{room.user2Name}}</h4>

          <h4 v-if="room.user2 == $store.state.loginInfo.email">
            <img :src="'/zippy/common/img/member/' + room.user1Pic"
              style="border-radius:50%; width: 80px; height: 80px;" class="mr-5">{{room.user1Name}}</h4>

          <insert-rv :buyier="room.user1" :serviceId="room.productNo"
            v-if="room.isSell == 1 && room.user1 == $store.state.loginInfo.email"></insert-rv>
          <v-btn class="mt-5" v-if="room.user2 == $store.state.loginInfo.email && room.isSell == 0" id="used-soldout" width="90px"
            depressed color=#F7F7F7 @click="soldOut()">
            판매완료
          </v-btn>
          <div id="download-btn" class="ml-auto">
            <v-btn type="button" color="#F7F7F7" depressed style="height:30px; width:80px; font-size:small"><a
                :href="'/zippy/chat/txtFile/' + room.chatRoomNo">TXT<v-icon>mdi-download</v-icon></a></v-btn>
            <v-btn class="ml-3" color="#F7F7F7" type="button" depressed
              style="height:30px; width:80px; font-size:small"><a :href="'/zippy/chat/pdfFile/' + room.chatRoomNo">PDF
                <v-icon>mdi-download</v-icon></a></v-btn>
            <!-- <button type="button" class="form-control" style="he ight:30px; width:80px; font-size:small"><a :href="'/zippy/chat/txtFile/' + room.chatRoomNo">TXT<v-icon>mdi-download</v-icon></a></button>
          <button type="button" class="form-control" style="height:30px; width:80px; font-size:small"><a :href="'/zippy/chat/pdfFile/' + room.chatRoomNo">PDF<v-icon>mdi-download</v-icon></a></button> -->
          </div>
        </div><br>
        <div id="chatBody" style="overflow:auto;">
          <ul class="list-group">
            <li class="list-group-item" v-for="msg in messages">

              <div v-if="msg.sender == sender" style="text-align: right; ">
                <div class="ccard">
                  <v-card class="mx-auto" max-width="400" outlined style="background-color: #fff; border-radius: 20px 20px 0 20px;">
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
                  <v-card class="mx-auto" max-width="400" outlined style="background-color: #fff; border-radius: 0 20px 20px 20px;">
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
        </div><br>
      </div>

        <div class="input-group">
          <div class="input-group-prepend">
          </div>
          <input type="text" placeholder="메세지를 입력해주세요" class="form-control" v-model="message" v-on:keypress.enter="sendMessage" style="border:0;">
          <div class="input-group-append">
            <v-btn depressed color="#F7F7F7" height="45px" @click="sendMessage">전송</v-btn>
          </div>
        </div>
      </div>
      <!-- container -->
    </div>
    </div>
</template>

<script>
  import Stomp from 'webstomp-client';
  import SockJS from 'sockjs-client';
  import axios from 'axios';

  import insertRv from '@/components/used/insertRv.vue';

  var sock;
  var ws;
  var reconnect = 0;

  // vue.js
  export default {
    components: {
      insertRv
    },
    data() {
      return {
        room: {},
        sender: '',
        message: '',
        messages: [],
        subId: "",
        list: {
          isSell: 1,
          dealRecord: "",
          productNo: ""
        }
      }
    },
    watch: {
      messages: function () {
        this.goToBottom();
      }
    },
    created() {
      this.sender = this.$store.getters.getName;
      this.getRoomInfo();
      this.loadContent();
      this.connect();
    },
    updated() {
      var body = document.getElementById("chatBody");
      body.scrollTop = body.scrollHeight;
    },

    methods: {
      goToBottom: function () {
        var body = document.getElementById("chatBody");
        body.scrollTop = body.scrollHeight;
      },

      getRoomInfo: function () {
        var out = this;
        this.$axios({
          url: "/zippy/chat/oneRoom",
          method: "GET",
          params: {
            roomId: this.$route.query.roomId
          }
        }).then(res => {
          out.room = res.data;
          console.log(out.room);
        })
      },

      loadContent: async function () {
        var temp = await this.$axios({
          url: "/zippy/chat/room/load",
          params: {
            roomId: this.$route.query.roomId
          }
        });
        this.messages = temp.data;
        console.log(this.messages);
      },

      connect: function () {
        sock = new SockJS("http://52.79.149.41:90/zippy/ws/chat");
        ws = Stomp.over(sock);
        var out = this;
        ws.connect({}, function (frame) {
          ws.subscribe("/topic/chat/room/" + out.$route.query.roomId, function (message) {
            var recv = JSON.parse(message.body);
            console.log(recv);
            out.recvMessage(recv);
            console.log(frame)
            console.log(frame.headers.id);
          })

        })
        this.goToBottom();
      },

      sendMessage: function () {
        var out = this;
        if (this.message == '') {
          console.log("빈값이 들어감");
        } else {
          ws.send("/app/chat/message", JSON.stringify({
            type: 'TALK',
            roomId: out.$route.query.roomId,
            sender: out.sender,
            message: out.message,
            time: out.getTime()
          }));
          this.message = '';
        }
        this.goToBottom();
      },

      recvMessage: function (recv) {
        this.messages.push({
          "type": recv.type,
          "sender": recv.type == 'ENTER' ? '[알림]' : recv.sender,
          "message": recv.message,
          "time": recv.time
        })
        this.goToBottom();
      },

      getTime: function () {
        var now = new Date();
        var str = "" + now.getFullYear() + "/" + (now.getMonth() + 1) + "/" + now.getDate() + "-" +
          now.getHours() + ":" + now.getMinutes()

        return str;
      },
      soldOut() {
        this.list.dealRecord = this.room.user1;
        this.list.productNo = this.room.productNo;
        console.log(this.list);
        axios({
          url: "/zippy/used/soldot",
          method: "POST",
          headers: {
            "Content-Type": "application/json; charset=utf-8"
          },
          data: JSON.stringify(this.list)
        }).then(res => {
          console.log(res);
        }).catch(error => {
          console.log(error);
        })

        axios({
          url: "/zippy/chat/sell",
          method: "PUT",
          data: {
            chatRoomNo: this.room.chatRoomNo,
            productNo: this.room.productNo,
            isSell: 1
          }
        }).then(res => {
          this.room.isSell = res.data;
          console.log(res.data);
        })
      }
    }
  }
</script>

<style scoped>
  a:link {
    color: black;
    text-decoration: none;
  }

  a:visited {
    color: black;
    text-decoration: none;
  }

  a:hover {
    color: black;
    text-decoration: none;
  }

  a:active {
    color: black;
    text-decoration: none;
  }

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
    height: 55vh;
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
    width: 35%;
    min-height: 800px;
    max-height: 800px;
    margin-bottom: 150px;
    box-shadow: 3px 5px 5px 1px #D6D6D6;
    padding:0;
    
  }

  #chat{
    background-color: #B3E3C3;
    padding: 40px 20px 20px 20px;
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
    max-height: 500px;
    background-color: #B3E3C3;
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
    padding: 0;
  }

  #download button {
    width: 100px;
  }

  a {
    text-decoration: none;
  }
</style>