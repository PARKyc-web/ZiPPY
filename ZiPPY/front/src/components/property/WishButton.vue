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

  export default {
    data() {
      return {
        heart: 0,
      }
    },
    methods: {
      changeHeart() {
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
      },
    }
  }
</script>