<template>
  <div>
  <h1>여기에 프로필사진이랑 상대방의 이름{{$route.query.sender}}</h1>
  <div class="chatBody" style="overflow:auto;">
    <ul class="list-group">
      <li class="list-group-item" v-for="msg in messages">
        <div v-if="msg.sender == sender" style="text-align: right;">
          <h5>{{msg.sender}}</h5><small>({{msg.time}})</small>
          <p>{{msg.message}}</p>
        </div>

        <div v-if="msg.sender != sender" style="text-align: left;">
          <h5>{{msg.sender}}</h5><small>({{msg.time}})</small>
          <p>{{msg.message}}</p>
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

  // websocket & stomp initialize
  // var sock = new SockJS("http://localhost:8090/zippy/ws/chat");
  // var ws = Stomp.over(sock);
  var sock;
  var ws;
  var reconnect = 0;

  // vue.js
  export default {
    props : ['roomId'],
    data() {
      return {        
        room: {},
        sender: '',
        message: '',
        messages: []
      }
    },
    mounted() {      
      this.sender = this.$store.getters.getName;
      this.loadContent();
      // this.findRoom();
      this.connect();      
    },
    methods: {
      goToBottom : function(){
        var body = document.querySelector(".chatBody");
        body.scrollTop = body.scrollHeight;        
      },

      loadContent: async function () {
        var temp = await this.$axios({
          url: "/chat/room/load",
          params: {
            roomId: this.roomId
          }
        });

        //   for(itm in temp.data){
        //     this.messages.unshift(item);
        //   }
        this.messages = temp.data;       
        this.goToBottom();
      },

      findRoom: function () {
        var temp = this.$axios.get('/chat/room/' + this.roomId).then(response => {          
          this.room = response.data;
        }).finally(res => {          
          this.connect();
        })
      },
      sendMessage: function () {
        if (this.message == '') {

        } else {
          ws.send("/app/chat/message", JSON.stringify({
            type: 'TALK',
            roomId: this.roomId,
            sender: this.sender,
            message: this.message,
            time: this.getTime()
          }));
          this.message = '';
          this.goToBottom();
        }
      },
      recvMessage: function (recv) {
        // this.messages.unshift({
        //     "type": recv.type,
        //     "sender": recv.type == 'ENTER' ? '[알림]' : recv.sender,
        //     "message": recv.message,
        //     "time": recv.time
        // })
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
        var str = "" + now.getFullYear() + "/" + (now.getMonth()+1) + "/" + now.getDate() + "-" +
          now.getHours() + ":" + now.getMinutes()

        return str;
      },

      connect: function () {
        var outside = this;
        sock = new SockJS("http://localhost:8090/zippy/ws/chat");
        ws = Stomp.over(sock);
        ws.connect({}, function (frame) {
          // debugger                    
          ws.subscribe("/topic/chat/room/" + outside.roomId, function (message) {
            var recv = JSON.parse(message.body);
            // console.log(recv);
            outside.recvMessage(recv);
          });
          ws.send("/app/chat/message", JSON.stringify({
            type: 'ENTER',
            roomId: outside.roomId,
            sender: outside.sender,
            time: outside.getTime()
          }));
        }, function (error) {
          if (reconnect++ <= 5) {
            setTimeout(function () {
              console.log("connection reconnect");
              sock = new SockJS("http://localhost:8090/zippy/ws/chat");
              ws = Stomp.over(sock);
              outside.connect();
            }, 10 * 1000);
          }
        });
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

  .list-group {
    padding: 0;
  }

  .chatBody {
    height: 70vh;
    border: 1px solid black;
  }

  .chatBody {
    -ms-overflow-style: none;
    /* IE and Edge */
    scrollbar-width: none;
    /* Firefox */
  }

  .chatBody::-webkit-scrollbar {
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
</style>