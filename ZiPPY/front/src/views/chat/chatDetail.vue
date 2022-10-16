<template>
<div class="container" id="cApp" v-cloak>
    <h1>{{$route.query.roomId}}</h1>
    <h1>{{$route.query.sender}}</h1>

    <div>
        <!-- <h2>{{room}}</h2> -->
    </div>
    <div class="input-group">
        <div class="input-group-prepend">
            <label class="input-group-text">내용</label>
        </div>
        <input type="text" class="form-control" v-model="message" v-on:keypress.enter="sendMessage">
        <div class="input-group-append">
            <button class="btn btn-primary" type="button" @click="sendMessage">보내기</button>
        </div>
    </div>
    <ul class="list-group">
        <li class="list-group-item" v-for="message in messages">
            {{message.sender}} - {{message.message}}
        </li>
    </ul>
    <button @click="connect()">Connect</button>
    <div></div>
</div>
</template>

<script>
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'

// websocket & stomp initialize
var sock = new SockJS("http://localhost:8090/zippy/ws/chat");
var ws = Stomp.over(sock);
var reconnect = 0;

// vue.js
export default {
    data() {
            return {
                roomId: '',
                room: {},
                sender: '',
                message: '',
                messages: []
            }
        },
        created() {
            this.roomId = this.$route.query.roomId;
            this.sender = this.$route.query.sender;            
            var temp = this.findRoom();
            this.connect();        
        },
        methods: {
            findRoom: async function() {
                var temp = await this.$axios.get('/chat/room/'+this.roomId).then(response => { 
                    console.log(response);
                    this.room = response.data; 
                });
            },
            sendMessage: function() {
                ws.send("/app/chat/message", JSON.stringify({type:'TALK', roomId:this.roomId, sender:this.sender, message:this.message}));
                this.message = '';
            },
            recvMessage: function(recv) {
                this.messages.unshift({"type":recv.type,"sender":recv.type=='ENTER'?'[알림]':recv.sender,"message":recv.message})
            },

            connect : function(){
                var outside = this;
                console.log("123");
                ws.connect({}, function(frame) {
                    console.log("구독전에 실행하는거123");
                    ws.subscribe("/topic/chat/room/"+outside.roomId, function(message) {
                        var recv = JSON.parse(message.body);
                        console.log(recv);
                        outside.recvMessage(recv);
                        console.log("대화 구독함");
                    });
                    console.log("123123123");
                var connectData = JSON.stringify({type:'ENTER', roomId:outside.roomId, sender:outside.sender});                                
                ws.send("/app/chat/message", connectData);
                }, function(error) {
                    if(reconnect++ <= 5) {
                        setTimeout(function() {
                            console.log("connection reconnect");
                            sock = new SockJS("http://localhost:8090/zippy/ws/chat");
                            ws = Stomp.over(sock);
                            this.connect();
                        },10*1000);
                    }
                });                
            }
        }   
}
</script>