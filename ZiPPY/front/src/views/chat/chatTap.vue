<template>
    <div id="con">
        <h5 class="ml-4 mt-5">채팅</h5>
        <div class="container">
            <b-list-group>
                <b-list-group-item class="flex-column align-items-start" style="border:0; padding:17px" v-for="(item, i) in chatRooms"
                    @click="enterChatRoom(item)">
                    <!-- chat con -->
                    <div id="chat-con">
                        <!--  style="display:flex" -->
                        <div id="userImage" style="float:left;">
                            <img v-if="$store.state.loginInfo.email == item.user1"
                                :src="'/zippy/common/img/member/' + item.user2Pic"
                                style="border-radius: 50%; width: 80px; height: 80px;">

                            <img v-else="$store.state.loginInfo.email != item.user1"
                                :src="'/zippy/common/img/member/' + item.user1Pic"
                                style="border-radius: 50%; width: 80px; height: 80px;">
                        </div>
                        <!-- 이름 시간-->
                        <div id="name" style="width:85%; height:20px;">
                            <div style="float:left">
                                <div class="pl-2" style="width:300px" v-if="$store.state.loginInfo.email == item.user1">
                                    {{item.user2Name}}
                                </div>
                                <div class="pl-2" v-else>{{item.user1Name}}</div>
                            </div>
                            <div style="float:right; font-size:small">{{item.lastTime}}</div>
                            <div style="width:439px">
                            </div>
                            <!-- chat con-->
                            <br>
                            <div class="ml-2" style="color:gray; word-break: break-all;">
                            {{item.lastChat}}
                            </div>    
                        </div>


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
@font-face {
    font-family: 'GmarketSans';
    font-weight: 500;
    font-style: normal;
    src: url('https://cdn.jsdelivr.net/gh/webfontworld/gmarket/GmarketSansMedium.eot');
    src: url('https://cdn.jsdelivr.net/gh/webfontworld/gmarket/GmarketSansMedium.eot?#iefix') format('embedded-opentype'),
         url('https://cdn.jsdelivr.net/gh/webfontworld/gmarket/GmarketSansMedium.woff2') format('woff2'),
         url('https://cdn.jsdelivr.net/gh/webfontworld/gmarket/GmarketSansMedium.woff') format('woff'),
         url('https://cdn.jsdelivr.net/gh/webfontworld/gmarket/GmarketSansMedium.ttf') format("truetype");
    font-display: swap;
} 
h5 {
  font-family: 'GmarketSans';
}
    #chat-con {
        display: flex;
        flex-wrap: wrap;
    }

    #con {
        width: 35%;
        margin: 0 auto;
        margin-top: 50px;
        min-height: 700px;
        background-color:#B3E3C3;
        box-shadow: 3px 5px 5px 1px #D6D6D6;
    }

    #container {
        height: 70vh;
        padding: 2%;
    }

    .align-items-start:hover {
        background-color: #F7F7F7;
        cursor: pointer;
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
        min-height: 60vh;
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