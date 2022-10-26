<template>
    <div style="border-top:1px solid #D6D6D6;">
    <div class="container" v-cloak style="margin-top:20px">
        <div v-if="chatRooms.length != 0">
            <b-card no-body>
                <b-tabs card vertical>
                    <!-- pills -->
                    <b-tab :title="(item.user1 == $store.state.loginInfo.email) ? item.user2Name : item.user1Name" 
                            v-for="(item, i) in chatRooms" @click="connect(i)">                            
                        <chatDetail :roomId="item.chatRoomNo" :item="item" :value="msg" @input="msg = recvMessage()"></chatDetail>
                    </b-tab>
                </b-tabs>
            </b-card>
        </div>
        <!-- <div v-else>
            채팅 기록이 없습니다!
        </div> -->
    </div>
</div>
</template>

<script>
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'
import chatDetail from '@/components/chat/chatDetail.vue'

    export default {
        data() {
            return {                
                msg : "",
                chatRooms: [],       
                sub_id : []         
            }
        },

        created() {
            var out = this;
            this.findAllRoom();
            this.$sock = new SockJS("http://localhost:8090/zippy/ws/chat");
            this.$ws = Stomp.over(this.$sock);
            this.$ws.connect({}, function (frame) {                
                out.$ws.subscribe("FAKE1234", function(message) {                    
                    console.log("FAKE SUB");
                })
                console.log("connnnnnect");
            }, function (error) {
                console.log(error);
            });            
        },

        components: {
            chatDetail
        },

        methods: {
            findAllRoom: function () {
                this.$axios({
                    url: "/zippy/chat/room",
                    params: {
                        email: this.$store.state.loginInfo.email
                    }
                }).then(res => {
                    this.chatRooms = res.data;
                    console.log(res.data);
                }).catch(error => {
                    console.log(error);
                })
            },
            recvMessage: function (recv) {
                return this.msg = {
                "type": recv.type,        
                "roomId" : recv.roomId,        
                "sender": recv.type == 'ENTER' ? '[알림]' : recv.sender,
                "message": recv.message,
                "time": recv.time
                }                
            },
            connect: function (i) {
                var outside = this;       

                // this.$sock = new SockJS("http://localhost:8090/zippy/ws/chat");
                // this.$ws = Stomp.over(this.$sock);
                this.$ws.connect({}, function (frame) {                
                    outside.$ws.subscribe("FAKE1234", function(message) {                    
                        console.log("FAKE SUB");
                    })
                    console.log("connnnnnect");
                }, function (error) {
                    console.log(error);
                });        
                
                var no = this.chatRooms[i].chatRoomNo;
                this.$ws.unsubscribe(this.sub_id[i]);

                this.$ws.subscribe("/topic/chat/room/" + no, function(message) {      
                    console.log("asdfasdfsadfsadf :: ", message.headers.subscription);
                    outside.sub_id[i] = message.headers.subscription;
                    console.log(outside.sub_id[i]);
                    var recv = JSON.parse(message.body);
                    console.log(recv);
                    outside.recvMessage(recv);
                });
                
                this.$ws.send("/app/chat/message", JSON.stringify({
                    type: 'ENTER',
                    roomId: no,
                    sender: outside.sender,
                    time: outside.getTime()
                }));
            },

            getTime: function () {
                var now = new Date();
                var str = "" + now.getFullYear() + "/" + (now.getMonth() + 1) + "/" + now.getDate() + "-" +
                    now.getHours() + ":" + now.getMinutes()

                return str;
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