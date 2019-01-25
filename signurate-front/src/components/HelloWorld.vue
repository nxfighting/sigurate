<template>

  <el-row>
    <el-col :span="24">
      <el-button style="width: 90%;" type="primary" v-on:click="handleLogin">
        <span>签到</span>
      </el-button>
    </el-col>

    <el-col :span="24" style="min-height: 15px;"></el-col>
    <el-col :span="24">
      <el-button style="width: 90%;" type="primary" v-on:click="handleLoginOut">
        <span>签退</span>
      </el-button>
    </el-col>
    <el-col :span="24">
      <el-button style="width: 90%;" type="primary" v-on:click="handleLoginForgot">
        <span>补打</span>
      </el-button>
    </el-col>
    <el-col :span="24" style="min-height: 15px;"></el-col>
    <el-col :span="24">
      <el-button style="width: 90%;" type="primary" v-on:click="handleLoginRecords">
        <span>签到记录</span>
      </el-button>
    </el-col>
  </el-row>
</template>

<script>
export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App',
      SERVER_URL:'http://localhost:8102/api',
    }
  },
  methods:{
    handleLogin(){
      let _this =this;
      _this.$axios.get(_this.SERVER_URL+'/sign/in').then(function(res){
        _this.$alert(res.data,'成功')
      },function(res){
        _this.$alert(res,'错误');
      })
    },
    handleLoginOut(){
      let _this =this;
      this.$axios.get(_this.SERVER_URL+'/sign/out').then(function(res){
        _this.$alert(res.data,'成功')
      },function(res){
        _this.$alert(res,'错误');
      })
    },
    handleLoginForgot:function(){

    },
    handleLoginRecords(){
      this.$router.push("/item")
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
