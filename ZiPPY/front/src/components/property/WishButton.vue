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
        data: {
          email: "",
          serviceId: "",
          bookmarkNo: -1,
          serviceType: 0
        },
      }
    },
    created() {
      if (this.$store.state.loginInfo) {
        axios({
          url: "http://localhost:8090/zippy/common/wishOne",
          method: "GET",
          params: {
            email: this.$store.state.loginInfo.email,
            sId: this.productId,
            serviceType: this.data.serviceType
          }
        }).then(response => {
            // 성공했을 때
            console.log('wishState success!');
            this.data.email = this.$store.state.loginInfo.email;
            this.data.serviceId = this.productId;
            
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
          //찜x
          if (this.heart == 0) this.addWish();
          //찜on
          else this.delWish();
        } else {
          Swal.fire({
            icon: 'warning',
            title: '로그인이 필요한 기능입니다.',
            showConfirmButton: false,
            timer: 1500
          })
        }
      },
      addWish() {
        axios({
          url: "http://localhost:8090/zippy/common/addWish",
          method: "POST",
          headers: {
            "Content-Type": "application/json; charset=utf-8"
          },
          data: JSON.stringify(this.data)
        }).then(res => {
          this.heart = 1; //찜on
          Swal.fire({
            icon: 'success',
            title: '관심매물에 등록되었습니다.',
            showConfirmButton: false,
            timer: 1500
          })
        }).catch(err => {
          console.log(err)
        })
      },
      delWish() {
        let bNo = [];
        console.log(this.data.bookmarkNo)
        bNo.push(this.data.bookmarkNo);
        console.log(bNo);
        axios({
          url: "http://localhost:8090/zippy/common/delWish",
          method: "DELETE",
          data: {
            bNo: bNo
          }
        }).then(res => {
          this.heart = 0; //찜x
          Swal.fire({
            icon: 'error',
            title: '관심매물에서 삭제되었습니다.',
            showConfirmButton: false,
            timer: 1500
          })
        }).catch(err => {
          console.log(err)
        })
      }
    }
  }
</script>