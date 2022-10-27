<template>
<div class="container">
    <b-list-group>
        <b-list-group-item class="flex-column align-items-start" v-for="(item, i) in chatRooms" @click="enterChatRoom(item)">
            <div class="d-flex w-100 justify-content-around">
                <!--  style="display:flex" -->
                <div id="userImage">
                    <img v-if="$store.state.loginInfo.email == item.user1" 
                            :src="'/zippy/common/img/member/' + item.user2Pic"
                            style="border-radius: 50%; width: 100px; height: 100px;">
                            
                    <img v-else="$store.state.loginInfo.email != item.user1" 
                            :src="'/zippy/common/img/member/' + item.user1Pic"
                            style="border-radius: 50%; width: 100px; height: 100px;">
                </div>
                <div>
                    <div class="d-flex w-100  justify-content-between">                
                        <h5 class="mb-1" v-if="$store.state.loginInfo.email == item.user1">{{item.user2Name}}</h5>
                        <h5 class="mb-1" v-else>{{item.user1Name}}</h5>                
                        <small>{{item.lastTime}}</small>
                    </div>
                    <p class="mb-1">
                    {{item.lastChat}}
                    </p>
                </div>
            </div>
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
                    console.log("asdasdasd", res.data);
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