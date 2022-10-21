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