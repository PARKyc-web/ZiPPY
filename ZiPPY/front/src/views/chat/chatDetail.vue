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
    <div></div>
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
    data() {
            return {
                roomId: '',
                room: {},
                sender: '',
                message: '',
                messages: []                
            }
        },
        mounted() {
            this.roomId = this.$route.query.roomId;
            this.sender = this.$route.query.sender;      
            this.loadContent();      
            // this.findRoom();
            this.connect();
        },
        methods: {
            loadContent : async function(){
              var temp = await this.$axios({
                url : "/chat/room/load",
                params: {
                    roomId : this.$route.query.roomId
                }
              });

            //   for(itm in temp.data){
            //     this.messages.unshift(item);
            //   }
              this.messages = temp.data;
              console.log(temp);
            },

            findRoom: function() {                
                var temp = this.$axios.get('/chat/room/'+this.roomId).then(response => { 
                    console.log(response);
                    this.room = response.data; 
                }).finally(res =>{                    
                    console.log(res);
                    this.connect();
                })
            },
            sendMessage: function() {                
                ws.send("/app/chat/message", JSON.stringify({type:'TALK', roomId:this.roomId, sender:this.sender, 
                                                                          message:this.message, time:this.getTime()}));
                this.message = '';
            },
            recvMessage: function(recv) {
                this.messages.unshift({"type":recv.type,"sender":recv.type=='ENTER'?'[알림]':recv.sender,"message":recv.message, "time":recv.time})
            },

            getTime : function(){
                var now = new Date();
                var str = ""+ now.getFullYear() + "/" + now.getMonth() + "/" + now.getDate() + "-"
                        + now.getHours() + ":" + now.getMinutes()

                return str;
            },

            connect : function(){
                var outside = this;
                sock = new SockJS("http://localhost:8090/zippy/ws/chat");
                ws = Stomp.over(sock);                
                ws.connect({}, function(frame) {
                    // debugger                                
                    ws.subscribe("/topic/chat/room/"+outside.roomId, function(message) {
                        var recv = JSON.parse(message.body);
                        console.log(recv);
                        outside.recvMessage(recv);                        
                    });             
                ws.send("/app/chat/message", JSON.stringify({type:'ENTER', roomId:outside.roomId, sender:outside.sender}));
                }, function(error) {
                    if(reconnect++ <= 5) {
                        setTimeout(function() {
                            console.log("connection reconnect");
                            sock = new SockJS("http://localhost:8090/zippy/ws/chat");
                            ws = Stomp.over(sock);
                            outside.connect();
                        },10*1000);
                    }
                });                
            }
        }   
}
</script>

<style scoped>
h5 {
    display: inline-block;
}
p {
    margin: 0px;
}
</style>