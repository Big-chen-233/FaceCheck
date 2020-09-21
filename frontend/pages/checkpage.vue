<template>
  <div class="container" ref="container">
    <div class="welcome">欢迎来到靓仔鉴定系统</div>
    <div class="notice">在这里，你可以选择上传一张照片</div>
    <div class="notice" style="margin-top:0.5vh">输入对应的昵称</div>
    <div class="notice" style="margin-top:0.5vh">然后点击按钮进行PK</div>

    <el-upload
      style="overflow: hidden;justify-content:center;align-items: center;position: relative;"
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
      :file-list="imageList"
   >
      <i class="el-icon-plus"></i>

    </el-upload>
    <input type="text" class="input" v-model="name" placeholder="请输入昵称">
    <div class="warning">注意：只能上传jpg和png格式</div>
    <div class="warning" style="margin-top:0.5vh">且照片大小不得超过2MB</div>
    <div class="warning" style="margin-top:0.5vh">你可以不输入昵称但这看起来不太好</div>

    <button @click="submit" class="checkbutton">进行颜值PK!</button>



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
        imageList:[],
        name:'',
        expression:'',
        emotion:'',
        eye_status:'',
        gender:'',
      };
    },
    computed:{
      uploadDisabled() {
        return this.judge;
      },
    },
    mounted(){
      this.init();
    },
    methods: {
      init(){
        let windowH = window.screen.height;
        this.$refs.container.style.height = windowH + 'px';
        console.log(windowH);
      },

      onUploadChange(file){
        const isImage = (file.raw.type === 'image/jpeg' || file.raw.type === 'image/png');   //数据格式
        const isLimited2M = file.size / 1024 / 1024 < 2;                        //数据大小

        if(!isImage){
          this.$message.error('仅支持上传图片格式!');
          return false;
        }
        if(!isLimited2M){
          this.$message.error('上传图片不得大于2MB!');
        }

        var reader = new FileReader();
        reader.readAsDataURL(file.raw);
        reader.onload = function(e){
        // console.log(this.result);             //图片的base64数据

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

        let data64base = this.database64;
        // console.log(data64base);
        let data = {
            'nickname':this.name,
            'data64base':data64base,
        }
        if(data64base == null || data64base == ''){   //无图片直接点击PK处理
          this.$message.error('请先上传图片再进行PK!');
        }else{
          axios({
            method:'post',
            url:'http://localhost:8080/api/update',
            data:data,
          }).then(res =>{
            this.score = res.data.user.beauty;
            this.expression = res.data.user.expression;
            this.emotion = res.data.user.emotion;
            this.eye_status = res.data.user.eye_status;
            this.gender = res.data.user.gender;

            sessionStorage.removeItem('data64');                   //清空sessionStorage
            this.imageList = []; //请求成功后移除图片
            this.judge = false; //移除图片后添加边框出现
            this.$router.push('/result');
            localStorage.setItem('mypic',data64base);
            localStorage.setItem('score',this.score);
            localStorage.setItem('name',this.name);
            console.log(localStorage.getItem('name'));
            this.name = ''; //提交成功后还原数据
            localStorage.setItem('expression',this.expression);
            localStorage.setItem('emotion',this.emotion);
            localStorage.setItem('face_shape',this.face_shape);
            localStorage.setItem('eye_status',this.eye_status);
            localStorage.setItem('gender',this.gender);

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
  background:#F5FFFA;
}

.el{
  margin-top: 3vh;
}
.welcome {
  margin-top: 8vh;
  font-size:1.5rem;
}

.notice{
  margin-top: 3vh;
  text-align: center;
}

.input{
  margin-top: 2vh;
  line-height: 2rem;
  border-radius: 5px;
  border: solid 1px gray;
  text-align: center;
}

.warning{
  margin-top: 3vh;
  /* width:80vw; */
  color:red;
  text-align: center;
}



.checkbutton{
  width: 60vw;
  max-width: 16rem;
  min-width: 13rem;
  background-color: orange;
  color: white;
  font-size: 1.2rem;
  outline: 0;
  border-radius: 15px;
  height:3.2rem;
  line-height: 28px;
  margin-top: 5vh;
  border:0;
  box-shadow: 0 !important;
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
@media screen and (max-height: 600px){
  .welcome{
    margin-top: 5vh;
  }
  .notice{
    margin-top: 2vh;
  }
  .checkbutton{
    margin-top: 4vh;
  }
}

@media screen and (max-width: 300px){
  .welcome{
    margin-top: 7vh;
    font-size: 1.4rem;
  }
  .notice{
    margin-top: 2vh;
    font-size: 0.93rem;
  }
  .warning{
    font-size: 0.85rem;
  }
}
</style>
