<template>
    <div class="container">
      <div id="download">
        <h1 v-if="room.user1 == $store.state.loginInfo.email">
          <img :src="'/zippy/common/img/member/' + room.user2Pic" style="width: 80px; height: 80px;">{{room.user2Name}}</h1>
          
        <h1 v-if="room.user2 == $store.state.loginInfo.email">
          <img :src="'/zippy/common/img/member/' + room.user1Pic" style="width: 80px; height: 80px;">{{room.user1Name}}</h1>
          
          <insert-rv :buyier="room.user1" :serviceId="room.productNo" 
                      v-if="room.isSell == 1 && room.user1 == $store.state.loginInfo.email"></insert-rv>
        <v-btn v-if="room.user2 == $store.state.loginInfo.email && room.isSell == 0" id="used-soldout" width="90px" depressed color=#B3E3C3
          @click="soldOut()">
          판매완료
        </v-btn>        
        <div id="download-btn" class="ml-auto">
          <button type="button" class="form-control"><a :href="'/zippy/chat/txtFile/' + room.chatRoomNo">TXT 파일 다운로드</a></button>
          <button type="button" class="form-control"><a :href="'/zippy/chat/pdfFile/' + room.chatRoomNo">PDF 파일 다운로드</a></button>
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
import Stomp from 'webstomp-client';
import SockJS from 'sockjs-client';
import axios from 'axios';

import insertRv from'@/components/used/insertRv.vue';

var sock;
var ws;
var reconnect = 0;
  
    // vue.js
    export default {    
      components: {
        insertRv
      } , 
      data() {
        return {
          txtFile : "/zippy/chat/txtFile/",
          pdfFile : "/zippy/chat/pdfFile/",          

          room: {},
          sender: '',
          message: '',
          messages: [],          
          subId : "",
          list : {
            isSell : 1,
            dealRecord : "",
            productNo : ""
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
      updated(){
        var body = document.getElementById("chatBody");          
        body.scrollTop = body.scrollHeight;
      },
  
      methods: {
        goToBottom: function () {
          var body = document.getElementById("chatBody");          
          body.scrollTop = body.scrollHeight;          
        },

        getRoomInfo : function(){
            var out = this;           
            this.$axios({
                url : "/zippy/chat/oneRoom",
                method: "GET",
                params:{
                    roomId : this.$route.query.roomId
                }
            }).then(res =>{
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

        connect : function(){
          sock = new SockJS("http://localhost:8090/zippy/ws/chat");          
          ws = Stomp.over(sock);
          var out = this;
          ws.connect({}, function(frame){
            ws.subscribe("/topic/chat/room/" + out.$route.query.roomId, function(message){                                
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
            url:"/zippy/chat/sell",
            method : "PUT",
            data :{
              chatRoomNo : this.room.chatRoomNo,
              productNo : this.room.productNo,
              isSell: 1 
            }
          }).then(res =>{
            this.room.isSell = res.data;
            console.log(res.data);
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
      padding: 0;
    }
  
    #download button {
      width: 100px;
    }
  
    a {
      text-decoration: none;
    }
  </style>