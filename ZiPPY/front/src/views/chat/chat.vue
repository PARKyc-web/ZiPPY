<template>
    <div class="container" v-cloak>
        <div class="row">
            <div class="col-md-12">
                <h3>채팅방 리스트</h3>
            </div>
        </div>
        <ul class="list-group">
            <li class="list-group-item list-group-item-action" v-for="item in chatRooms" :key="item.chatRoomNo"
                v-on:click="enterRoom(item.chatRoomNo)">
                <h4>방번호 : {{item.chatRoomNo}}</h4>
                <h6>유저1 : {{item.user1}}</h6>
                <h6>유저2 : {{item.user2}}</h6>
                <h5>마지막 대화 : {{item.lastChat}}</h5>
                <small>{{item.lastTime}}</small>
            </li>
        </ul>
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
                    url: "/chat/room",
                    params: {
                        email: "test1@email.com"
                    }
                }).then(res => {
                    console.log(res);
                    this.chatRooms = res.data;
                }).catch(error => {
                    console.log(error);
                })
            },
            enterRoom: function (roomId) {
                var sender = prompt('대화명을 입력해 주세요.');
                // var sender = this.$store.getters.getName;
                this.$router.push({
                    name: "chatDetail",
                    query: {
                        sender: sender,
                        roomId: roomId
                    }
                })
                console.log(sender);
            }
        }

    }
</script>

<style scoped>
    input {
        border: 1px solid black;
    }

    h5 {
        margin: 0px;
    }
</style>