<template>
  <div class="container">
    <div class="welcome">欢迎来到靓仔鉴定系统</div>
    <div class="notice">在这里，你可以选择上传一张照片</div>
    <div class="notice" style="margin-top:0.5vh">然后点击按钮进行PK</div>

    <el-upload
      class="el"
      :class="{disabled:uploadDisabled}"
      action=""
      multiple
      :limit="1"
      :on-exceed="handleExceed" 
      list-type="picture-card"
      accept="image/jpeg,image/png"
      :auto-upload="false"
      :on-change="onUploadChange"
      :on-remove="onUploadRemove"
   >
      <i class="el-icon-plus"></i>
      
    </el-upload>

    <div class="warning">注意：只能上传jpg和png格式</div>
    <div class="warning" style="margin-top:0.5vh">且照片大小不得超过5MB</div>

    <button @click="submit" class="checkbutton">进行颜值PK!</button>
    
    
    
    <div class="footer">靓仔小组提供技术支持</div>
  </div>
</template>

<script>
  import axios from 'axios'
  import Qs from 'qs'
  export default {
    data() {
      return {
        database64:'',
        score:'',
        judge:false,
      };
    },
    computed:{
      uploadDisabled() {
        return this.judge;
      },
    },
    methods: {
      onUploadChange(file){
        const isImage = (file.raw.type === 'image/jpeg' || file.raw.type === 'image/png');   //数据格式
        const isLimited5M = file.size / 1024 / 1024 < 5;                        //数据大小

        if(!isImage){
          this.$message.error('仅支持上传图片格式!');
          return false;
        }
        if(!isLimited5M){
          this.$message.error('上传图片不得大于5MB!');
        }

        var reader = new FileReader();
        reader.readAsDataURL(file.raw);
        reader.onload = function(e){
        console.log(this.result);             //图片的base64数据

        this.database64 = this.result;
        sessionStorage.setItem('data64',this.database64);   //储存base64数据
        }
        this.judge = true;      //上传图片后添加边框消失
      },
      onUploadRemove(file){
        sessionStorage.removeItem('data64');//移除图片时清空图片数据
        this.judge = false;     //移除图片后添加边框出现
      },

      submit(){
        this.database64 = sessionStorage.getItem('data64');   //读取base64数据
        sessionStorage.removeItem('data64');                   //清空sessionStorage
        let data64base = this.database64;
        console.log(data64base);
        let data = {
            'data64base':data64base,
        }
        if(data64base == null || data64base == ''){   //无图片直接点击PK处理
          this.$message.error('请先上传图片再进行PK!');
        }else{
          axios({
            method:'post',
            url:'http://localhost:8080/api/update',
            data:data
          }).then(res =>{
            this.score = res.data.str_score;
          }).catch((err) =>{
            this.$message.error('连接失败');
          })
        }
      },

    
    
      handleExceed(){     //上传数量限制提示
        this.$message.error('上传数量限制为1!');
      }
    }
  }
</script>

<style>
.container {
  width: 100vw;
  height: 100vh;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
}

.el{
  margin-top: 3vh;
}
.welcome {
  margin-top: 5vh;
  font-size:1.7rem;
}

.notice{
  /* width: 80vw; */
  margin-top: 5vh;
}

.warning{
  margin-top: 3vh;
  /* width:80vw; */
  color:red;
}



.checkbutton{
  width: 60vw;
  max-width: 16rem;
  min-width: 13rem;
  background-color: #FF0000;
  color: white;
  font-size: 1.2rem;
  outline: 0;
  border-radius: 15px;
  height:3.2rem;
  line-height: 28px;
  margin-top: 5vh;
  border:0;
}

.footer{
  position: absolute;
  bottom: 2rem;
  justify-self: end;
  font-size: 1rem;
  line-height: 22px;
  color: #A6A6A6;
  text-align: center;
}

.disabled .el-upload--picture-card {
    display: none;
}
</style>
