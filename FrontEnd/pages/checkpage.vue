<template>
  <div class="container">
    <el-upload
      class="el"
      action=""
      multiple
      :limit="1"
      :on-exceed="handleExceed" 
      list-type="picture-card"
      accept="image/jpeg,image/png"
      :auto-upload="false"
      :on-change="onUploadChange"
   >
      <i class="el-icon-plus"></i>
      
    </el-upload>

    <button @click="submit" class="button"></button>
    
    
    
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
      };
    },
    methods: {
      onUploadChange(file){
        const isImage = (file.raw.type === 'image/jpeg' || file.raw.type === 'image/png');
        const isLimited5M = file.size / 1024 / 1024 < 5;

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
        console.log(this.result);//图片的base64数据

        this.database64 = this.result;
        sessionStorage.setItem('data64',this.database64);
        }
      },

      submit(){
        this.database64 = sessionStorage.getItem('data64');
        let data64base = this.database64;
        console.log(data64base);
        let data = {
            'data64base':data64base,
        }
        axios({
          method:'post',
          url:'http://localhost:8000/api/update',
          data: Qs.stringify(data),
        }).then(res =>{
          this.score = res.data.str_score;
        }).catch((err) =>{
          
        })
      },

    
    
      handleExceed(){
        this.$message.error('上传数量限制为1!')
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
  margin-top: 15vh;
}
.title {
  font-size: 2rem;
}

.button{

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
</style>
