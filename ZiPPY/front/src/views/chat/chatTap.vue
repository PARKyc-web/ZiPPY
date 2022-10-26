<template>
<div class="container">
    <b-list-group>
        <b-list-group-item class="flex-column align-items-start" v-for="(item, i) in chatRooms" @click="enterChatRoom(item)">
            <img :src="'/zippy/common/img/member/test.jpg'" style="border-radius: 50%; width: 100px; height: 100px;">
            <div class="d-flex w-100  justify-content-between">                
                <h5 class="mb-1" v-if="$store.state.loginInfo.email == item.user1">{{item.user2Name}}</h5>
                <h5 class="mb-1" v-else>{{item.user1Name}}</h5>                
                <small>{{item.lastTime}}</small>
            </div>
            <p class="mb-1">
            {{item.lastChat}}
            </p>
            <!-- <small>Donec id elit non mi porta.</small> -->
        </b-list-group-item>
    </b-list-group>
</div>
</template>

<script>
    export default {
        data() {
            return {
                chatRooms: []                
            }
        },

        created() {            
            this.findAllRoom();             
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

            enterChatRoom: function(item){
                this.$router.push({
                    name: "chatDetail",
                    query : {
                        roomId : item.chatRoomNo
                    }
                });
            },
            
            connect: function (i) {
                var outside = this;       

                this.$sock = new SockJS("http://localhost:8090/zippy/ws/chat");
                this.$ws = Stomp.over(this.$sock);

                this.$ws.connect({}, function (frame) {                
                out.$ws.subscribe("/topic/chat/room/" + no, function(message) {                    
                    var recv = JSON.parse(message.body);
                    console.log(recv);
                    outside.recvMessage(recv);
                    console.log("connnnnnect");
                })  
                    console.log("connnnnnect");
                }, function (error) {
                    console.log(error);
                });

                // this.$ws.connect({}, function (frame) {        
                //     this.$ws.subscribe("/topic/chat/room/" + no, function(message) {         
                //         console.log("구독 후 데이터를 받았니?");
                //         console.log("asdfasdfsadfsadf :: ", message.headers.subscription);
                //         outside.sub_id[i] = message.headers.subscription;
                //         console.log(outside.sub_id[i]);
                //         var recv = JSON.parse(message.body);
                //         console.log(recv);
                //         outside.recvMessage(recv);
                //         console.log("connnnnnect");
                //     }                    
                // }, function (error) {
                //     console.log(error);
                // });        
                
                // var no = this.chatRooms[i].chatRoomNo;
                // this.$ws.unsubscribe(this.sub_id[i]);

                this.$ws.subscribe("/topic/chat/room/" + no, function(message) {      
                    console.log("구독 후 데이터를 받았니?");
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

    .align-items-start:hover{
        background-color: #B3E3C3;
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