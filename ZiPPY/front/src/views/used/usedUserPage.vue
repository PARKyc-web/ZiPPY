<template>
    <div>
        <div id="container">
            <div 프로필 부분>
                <div 이미지 부분>

                </div>
                <div 판매자정보 부분>
                    <div 판매자 이름></div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import axios from 'axios';
    import navBar from '../../components/used/navBar.vue';
    export default {
        components: {
            navBar
        },
        data: () => ({

        })
    }
</script>

<style scoped>
    #container {
        width: 1200px;
        margin: 0 auto;
    }
</style>