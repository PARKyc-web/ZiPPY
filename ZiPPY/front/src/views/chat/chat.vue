<template>
<div class="container" v-cloak>
    <div class="row">
        <div class="col-md-12">
            <h3>채팅방 리스트</h3>
        </div>
    </div>
    <div class="input-group">
        <div class="input-group-prepend">
            <label class="input-group-text">방제목</label>
        </div>
        <input type="text" class="form-control" v-model="room_name" v-on:keyup.enter="createRoom">
        <div class="input-group-append">
            <button class="btn btn-primary" type="button" @click="createRoom">채팅방 개설</button>
        </div>
    </div>
    <ul class="list-group">
        <li class="list-group-item list-group-item-action" v-for="item in chatrooms" v-bind:key="item.roomId" v-on:click="enterRoom(item.roomId)">
            {{item.roomName}}
        </li>
    </ul>

    <ul class="list-group">
        <li class="list-group-item list-group-item-action" v-for="item in customChatrooms" v-bind:key="item.chatRoomNo" v-on:click="enterRoom(item.chatRoomNo)">
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

export default{
    data() {
        return{
            room_name : '',
            chatrooms: [
            ],
            customChatrooms:[]
        }
        },
        created() {
            this.findAllRoom();
        },
        methods: {
            findAllRoom: function() {
                this.$axios.get('/chat/rooms').then(response => { this.chatrooms = response.data; });

                this.$axios({
                    url: "/chat/customRoom",
                    params:{
                        email : "test1@email.com"
                    }
                }).then(res =>{
                    console.log(res);
                    this.customChatrooms = res.data;
                }).catch(error =>{
                    console.log(error);
                })
            },
            createRoom: function() {
                if("" === this.room_name) {
                    alert("방 제목을 입력해 주십시요.");
                    return;
                } else {
                    var params = new URLSearchParams();
                    params.append("name",this.room_name);
                    this.$axios.post('/chat/room', params)
                        .then(
                            response => {
                                alert(response.data.roomName+"방 개설에 성공하였습니다.")
                                this.room_name = '';
                                this.findAllRoom();
                            }
                        )
                        .catch( response => { alert("채팅방 개설에 실패하였습니다."); } );
                }
            },
            enterRoom: function(roomId) {
                var sender = prompt('대화명을 입력해 주세요.');

                this.$router.push({
                    name : "chatDetail",
                    query : {sender : sender, roomId: roomId}
                })
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