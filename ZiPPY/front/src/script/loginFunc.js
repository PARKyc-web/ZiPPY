import axios from "axios";
import { type } from "jquery";

export default {

    email_redundancy: async function () {        
        var user_email = document.querySelector("#inputEmail").value;
        var redundancy = 14156;

        const test = await axios({
            url: "http://localhost:8090/zippy/validation/emailRedundancy",
            method: "GET",
            params: {
                email: user_email
            }
        }).then(res => {
            redundancy = res.data;            
        });
        
        return redundancy;
    },

    /**
     * Eamil 인증을 위한 Email 전송 인증번호 6자리
     */
    email_validation: async function () {
        var reg = /([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
        var email = document.querySelector("#inputEmail").value;
        var email_code = 1234567890;
        const redundancy = await this.email_redundancy();
        console.log(redundancy);
        if (reg.test(email)) {

            if (redundancy == 0) {
                document.querySelector("#email-confirm-btn").disabled = false;
                alert("Email을 전송하였습니다.");

                const temp = await axios({
                    url : "http://localhost:8090/zippy/validation/email",
                    params : {
                        email : email
                    }
                }).then(res =>{
                    console.log("Axios 실행완료 : ", res);
                    email_code = res.data;
                    console.log("emailCode 값 : ", email_code);

                }).catch(error =>{
                    console.log(error);
                })         

                return {
                    email: email,
                    email_code: email_code
                };

            } else {
                alert("이미 사용중인 이메일입니다!");
            }

        } else {
            alert("이메일 양식을 맞춰주세요!");
        }
    },

    /**
     * 인증번호와 유저가 입력한 숫자가 동일한지 확인하는 메소드
     */
    email_valid_check: function (email_code) {
        var code = document.querySelector("#emailAuthentication");

        if (email_code == code.value) {
            alert("이메일인증 성공!");
            document.querySelector("#inputEmail").disabled = true;
            document.querySelector("#emailAuthentication").disabled = true;
            document.querySelector("#email-confirm-btn").disabled = true;
            document.querySelector("#email_code").disabled = true;

            return true;

        } else {
            alert("인증번호가 틀렸습니다!");
            return false;
        }

    },

    /**
     * 비밀번호가 사이트 규정에 맞는지 확인하는 메소드
     */
    password_validation: function () {
        var reg =
            /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-+]).{8,20}$/;
        var password = document.querySelector("#password").value;
        var result = reg.test(password);

        if (result) {
            this.pass_valid = reg.test(password);
            document.getElementsByClassName("passwordCheck")[0].style.display =
                "none";
        } else {
            document.getElementsByClassName("passwordCheck")[0].style.display =
                "block";
        }
        return result;
    },


    /**
     * 비밀번호와 비밀번호 재확인이 동일한지 확인하는 메소드
     */
    password_confirm: function () {
        var password = document.querySelector("#password").value;
        var confirm = document.querySelector("#passwd-confirm").value;
        var result = (password == confirm) ? true : false;

        if (result) {
            document.getElementsByClassName("passwordCheck")[1].style.display =
                "none";
        } else {
            document.getElementsByClassName("passwordCheck")[1].style.display =
                "block";
        }

        return result;
    },

    phone_validation: async function () {
        var reg = /^010[0-9]{8}$/;
        var number = document.querySelector("#phone").value;
        var phone_code = 0;
        console.log(number);
        console.log(typeof(number));
        
        if (reg.test(number)) {
            alert("인증번호를 전송하였습니다!");
            document.querySelector("#phoneNumberBtn").disabled = false;
            const temp = await axios({                
              url : "http://localhost:8090/zippy/validation/phone",
              method : "GET",
              params : {
                phone : number
              }

            }).then(res => {
              console.log(res);
              phone_code = res.data;

            }).catch(error =>{
              console.log(error);
            });
            return {
                phone: number,
                phone_code: phone_code
            }

        } else {
            alert("휴대폰 번호를 확인해주세요!");
            console.log(number);
        }
    },

    /**
     * 핸드폰으로 보낸 인증번호가 동일한지 확인하는 메소드
     */
    phone_valid_check: function (phoneCode) {
        console.log("phone-valid-check");
        var input_phone = document.querySelector("#phone");
        var valid = document.querySelector("#phoneAuthentication");
        var send_btn = document.querySelector("#phone_code");
        var valid_btn = document.querySelector("#phoneNumberBtn");
        if (valid.value == phoneCode) {
            input_phone.disabled = true;
            valid.disabled = true;
            send_btn.disabled = true;
            valid_btn.disabled = true;
            alert("인증이 완료되었습니다!");
            return true;

        } else {
            alert("인증번호가 일치하지 않습니다!");
        }
    }
}