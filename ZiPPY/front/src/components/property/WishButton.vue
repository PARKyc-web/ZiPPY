<template>
  <div id="heart">
    <v-btn v-if="heart==0" class="mx-2" color='#D6D6D6' fab depressed dark x-small @click="changeHeart()">
      <v-icon dark small>
        mdi-heart
      </v-icon>
    </v-btn>
    <v-btn v-if="heart==1" class="mx-2" color='#FF4063' fab depressed dark x-small @click="changeHeart()">
      <v-icon dark small>
        mdi-heart
      </v-icon>
    </v-btn>
  </div>
</template>

<script>
  import Swal from 'sweetalert2';
  import axios from 'axios';

  export default {
    props: ['productId'],
    data() {
      return {
        heart: 0,
      }
    },
    created() {
      if (this.$store.state.loginInfo) {
        axios({
            url: "http://localhost:8090/zippy/property/wishState",
            method: "GET",
            params: {
              email: this.$store.state.loginInfo.email,
              serviceId: this.productId
            }
          }).then(response => {
            // 성공했을 때
            console.log('wishState success!');
            if (response.data) {
              this.heart = 1;
            } 
          })
          .catch(error => {
            // 에러가 났을 때
            console.log('wishState fail!');
            console.log(error);
          })
      }
    },
    methods: {
      changeHeart() {
        if (this.$store.state.loginInfo) {
          if (this.heart == 0) { //찜x
            this.heart = 1; //찜on
            Swal.fire({
              icon: 'success',
              title: '관심매물에 등록되었습니다.',
              showConfirmButton: false,
              timer: 1500
            })
          } else { //찜on
            this.heart = 0; //찜x
            Swal.fire({
              icon: 'error',
              title: '관심매물에서 삭제되었습니다.',
              showConfirmButton: false,
              timer: 1500
            })
          }
        } else {
          Swal.fire({
            icon: 'warning',
            title: '로그인이 필요한 기능입니다.',
            showConfirmButton: false,
            timer: 1500
          })
        }

      },
    }
  }
</script>