<template>
    <div id="con">
        <div class="container">
            <b-list-group>
                <b-list-group-item class="flex-column align-items-start" v-for="(item, i) in chatRooms"
                    @click="enterChatRoom(item)">
                    <div id="chat-con">
                        <!--  style="display:flex" -->
                        <div id="userImage" style="float:left">
                            <img v-if="$store.state.loginInfo.email == item.user1"
                                :src="'/zippy/common/img/member/' + item.user2Pic"
                                style="border-radius: 50%; width: 80px; height: 80px;">

                            <img v-else="$store.state.loginInfo.email != item.user1"
                                :src="'/zippy/common/img/member/' + item.user1Pic"
                                style="border-radius: 50%; width: 80px; height: 80px;">
                        </div>
                        <!-- 이름 시간-->
                        <div id="name" style="width:437px; height:20px;">
                            <div style="float:left">
                            <h6 class="pl-2" style="width:100px" v-if="$store.state.loginInfo.email == item.user1">
                                {{item.user2Name}}
                            </h6>
                            <h6 class="pl-2" v-else>{{item.user1Name}}</h6>
                         </div>
                        <small style="float:right">{{item.lastTime}}</small>
                        <div style="width:437px">
                        </div>
                    </div>
                    
                    {{item.lastChat}}

                    </div>
                    <!-- <small>Donec id elit non mi porta.</small> -->
                </b-list-group-item>
            </b-list-group>
        </div>
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
                    console.log("asdasdasd", res.data);
                }).catch(error => {
                    console.log(error);
                })
            },

            enterChatRoom: function (item) {
                this.$router.push({
                    name: "chatDetail",
                    query: {
                        roomId: item.chatRoomNo
                    }
                });
            },
        }
    }
</script>

<style scoped>
    #chat-con{
        display:flex;
        flex-wrap: wrap;
    }

    #con {
        border: 1px solid #D6D6D6;
        width: 35%;
        margin: 0 auto;
        margin-top: 50px;
        min-height: 600px;
    }

    #container {
        height: 70vh;
    }

    .align-items-start:hover {
        background-color: #B3E3C3;
        cursor:pointer;
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
        width: 100%;
    }

    .fa-paper-plane {
        margin-left: 10px;
        color: #B3E3C3;
    }
</style>