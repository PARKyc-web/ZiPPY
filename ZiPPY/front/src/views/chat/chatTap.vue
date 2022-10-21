<template>
    <div class="container" v-cloak>
        <div>
            <b-card no-body>
                <b-tabs card vertical> <!-- pills -->
                    <b-tab :title="item.user2Name" v-for="item in chatRooms">                    
                        <chatDetail :roomId="item.chatRoomNo" :item="item"></chatDetail>
                    </b-tab>   
                </b-tabs>
            </b-card>
        </div>        
    </div>
</template>

<script>   
import chatDetail from '@/components/chat/chatDetail.vue'

    export default {
        data(){
            return {                
                chatRooms : [],
            }

        },
        
        created() {
            this.findAllRoom();
        },

        components :{
            chatDetail
        },

        methods: {            
            findAllRoom: function () {
                this.$axios({
                    url: "/chat/room",
                    params: {                        
                        email : this.$store.state.loginInfo.email
                    }
                }).then(res => {                    
                    this.chatRooms = res.data;
                    console.log(res.data);
                }).catch(error => {
                    console.log(error);
                })
            },
            enterRoom: function (roomId) {                
                var sender = this.$store.getters.getName;
                this.$router.push({
                    name: "chatDetail",
                    query: {
                        sender: sender,
                        roomId: roomId
                    }
                })                
            },

            connect: function (no) {
                console.log(no);
                var outside = this;
                // sock = new SockJS("http://localhost:8090/zippy/ws/chat");
                // ws = Stomp.over(sock);
                console.log("구독 전에 실행!")
                this.$ws.connect({}, function (frame) {
                // debugger                    
                outside.$ws.subscribe("/topic/chat/room/" + no, function (message) {
                    console.log("구독 성공!")
                    var recv = JSON.parse(message.body);
                    // console.log(recv);
                    outside.recvMessage(recv);
                });
                outside.$ws.send("/app/chat/message", JSON.stringify({
                    type: 'ENTER',
                    roomId: outside.roomId,
                    sender: outside.sender,
                    time: outside.getTime()
                }));
                }, function (error) {
                if (reconnect++ <= 5) {
                    setTimeout(function () {
                    console.log("connection reconnect");
                    // outside.$sock = new SockJS("http://localhost:8090/zippy/ws/chat");
                    // outside.$ws = Stomp.over(sock);
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