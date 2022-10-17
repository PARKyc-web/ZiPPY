<template>
    <div>
        <img src="@/assets/page_404.jpg" @click="home()">    
    </div>
</template>

<script>
export default{
    methods :{
        home : function(){
            this.$router.push("/home");
        }
    }
}
</script>
<style scoped>
div {
    text-align: center;    
}
img {
    margin-top: 50px;
}

button {    
    width: 200px;
    height: 50px;        
    color : white;
    background-color: aquamarine;
}
</style>