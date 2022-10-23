<template>
    <v-row justify="center">
        <v-dialog v-model="dialog" persistent max-width="290">
            <template v-slot:activator="{ on, attrs }">
                <div id="reportButton" v-bind="attrs" v-on="on">
                    <img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAB4AAAAeCAYAAAA7MK6iAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAbBJREFUeNrsls9LAkEcxb+au+JSGIEQyGbBdg881CmConsE9Qd07S/p0qG69RcUdOzSoUt0UujS3c0kEQULU7R17c2wyhazNbvaesgHH9xZh3kzb35tpNfrUV/5fJ7+Stls9ks5FqCNKTDv/L6AjyAdiUrWi4AdcAXewDMwwTu4AQdAHbXxCrh3TJm55vpPAVvgHDyC7VEZM6M7sCbRlgGuwaGM8U9zvAkunbn0M/8nzvNpkBHr4MKnqVvHYDWI8RGY84wpFiNFUX5L8sxZlNJRL4E9UeVkMknpdJoSiQQvt9ttKpfLVK1WhVsXbIBb2RHvi96nUikyDGNgyhSPxymTyZCu614D2/UT9bqooqZpnrm6OyNYoNLGy6KKxWKRR/td3W6XCoWCV/sLfoyFJ5Bt29zAfbb3O9TpdLzaZzFND3NkcjUaDapUKoNyvV6nWq0ms7eHM2YqlUrUarXIsiwyTTPwbeX7dmJRs8hVVeXmoRkzNZtNzjCK0pg0MQ5NosX1BKwRetjCb6lcLje2ES+GHDk72G1m/MCu2hCNZ8HrZDv9j33MVtlMiJ58X38KMADfFnDPWur9bAAAAABJRU5ErkJggg=="
                        width="15" height="15" alt="신고 아이콘" />신고하기
                </div>
            </template>
            <v-card>
                <v-card-title class="text-h7">
                    <b>신고하기</b>
                </v-card-title>
                <v-card-text>신고 사유를 입력해주세요.</v-card-text>
                <v-card-text>
                    <v-radio-group v-model="reportContents">
                        <v-radio label="판매금지 물풉입니다(마약,술,담배,칼 등)" value="판매금지 물풉입니다(마약,술,담배,칼 등)"
                            color="green lighten-3"></v-radio>
                        <v-radio label="허위로 기재된 글입니다." value="허위로 기재된 글입니다." color="green lighten-3"></v-radio>
                    </v-radio-group>
                </v-card-text>
                <v-card-actions>
                    <v-spacer></v-spacer>
                    <v-btn color="green accent-3" text @click="dialog = false">
                        취소
                    </v-btn>
                    <v-btn color="green accent-3" text @click="report">
                        신고
                    </v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </v-row>
</template>

<script>
    import Swal from 'sweetalert2';
    import axios from 'axios';
    export default {
        props: ['productNo'],
        data() {
            return {
                dialog: false,
                reportContents: ''
            }
        },
        methods: {
            report() {
                if (this.$store.state.loginInfo) {
                    axios({
                            url: "http://localhost:8090/zippy/common/addReport",
                            method: "POST",
                            params: {
                                email: this.$store.state.loginInfo.email,
                                reportType: '신고',
                                reportContents: this.reportContents,
                                serviceType: 1,
                                serviceId: this.productNo
                            },
                        }).then(response => {
                            // 성공했을 때
                            console.log('addReport success!');

                            Swal.fire({
                                icon: 'success',
                                title: '신고가 완료되었습니다.',
                                showConfirmButton: false,
                                timer: 1500
                            })
                        })
                        .catch(error => {
                            // 에러가 났을 때
                            console.log('addReport fail!');
                            console.log(error);
                        })
                } else {
                    Swal.fire({
                        icon: 'warning',
                        title: '로그인이 필요한 기능입니다.',
                        showConfirmButton: false,
                        timer: 1500
                    })
                }
                this.dialog = false;
            }
        }
    }
</script>